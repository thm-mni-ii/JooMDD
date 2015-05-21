package de.thm.icampus.ejsl.generator

import de.thm.icampus.ejsl.eJSL.IndexPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.Section
import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.Entity
import java.util.List
import de.thm.icampus.ejsl.eJSL.Attribute

class IndexPageTemplate extends DynamicPageTemplate {

	private IndexPage ipage
	private Component com
	private Section sec

	new(IndexPage dp, Component cp, Section section) {

		ipage = dp
		com = cp
		sec = section
	}

	def CharSequence generateViewBackend(IndexPage page, Component component, Entity entity) '''
		<?php
		/**
		«generateFileDoc(page, component,true)»
		* @description «page.entities.get(0).name»View for «component.name»
		*/
		defined('_JEXEC') or die('Restricted access');
		jimport('joomla.application.component.view');
		jimport('joomla.filesystem.path');
		/**
		* «component.name»View class for component com_«Slug.slugify(component.name)»
		*
		* @category Joomla.Component. Admin
		* @package com_«Slug.slugify(component.name)»."admin"
		* @since Class available since Release x.x.x
		*/
		class «component.name.toFirstUpper»View«page.name.toFirstUpper» extends JViewLegacy
		{
		 	protected $items;
		
			protected $pagination;
		
			protected $state;
		/**
		* @var JObject A cache for the available actions.
		* @generated
		*
		*/
		protected static $actions;
		/**
		* Method to get display
		*
		* @param Object $tpl template
		*
		* @return void
		* @generated
		*/
		public function display($tpl = null)
		{
			$this->state		= $this->get('State');
			$this->items		= $this->get('Items');
			$this->pagination	= $this->get('Pagination');
			
			«page.name.toFirstUpper»Helper::addSubmenu('«page.name»');
			
			// Check for errors.
			if (count($errors = $this->get('Errors')))
			{
				JError::raiseError(500, implode("\n", $errors));
				return false;
			}
		
			$this->addToolbar();
			$this->sidebar = JHtmlSidebar::render();
			parent::display($tpl);
		}
		
		 protected function addToolbar() {
		      require_once JPATH_COMPONENT . '/helpers/«page.name».php';
		
		      $state = $this->get('State');
		      $canDo = MddHelper::getActions($state->get('filter.category_id'));
		
		      JToolBarHelper::title(JText::_('«page.name»'));
		
		      //Check if the form exists before showing the add/edit buttons
		      $formPath = JPATH_COMPONENT_ADMINISTRATOR . '/views/«page.name»';
		      if (file_exists($formPath)) {
		
		    if ($canDo->get('core.create')) {
		        JToolBarHelper::addNew('«page.name».add', 'JTOOLBAR_NEW');
		    }
		
		          if ($canDo->get('core.edit') && isset($this->items[0])) {
		    JToolBarHelper::editList('«page.name».edit', 'JTOOLBAR_EDIT');
		          }
		      }
		
		      if ($canDo->get('core.edit.state')) {
		
		    if (isset($this->items[0]->state)) {
		        JToolBarHelper::divider();
		        JToolBarHelper::custom('«page.name»s.publish', 'publish.png', 'publish_f2.png', 'JTOOLBAR_PUBLISH', true);
		        JToolBarHelper::custom('«page.name»s.unpublish', 'unpublish.png', 'unpublish_f2.png', 'JTOOLBAR_UNPUBLISH', true);
		    } else if (isset($this->items[0])) {
		        //If this component does not use state then show a direct delete button as we can not trash
		        JToolBarHelper::deleteList('', '«page.name»s.delete', 'JTOOLBAR_DELETE');
		    }
		
		          if (isset($this->items[0]->state)) {
		    JToolBarHelper::divider();
		    JToolBarHelper::archiveList('«page.name»s.archive', 'JTOOLBAR_ARCHIVE');
		          }
		          if (isset($this->items[0]->checked_out)) {
		              JToolBarHelper::custom('«page.name»s.checkin', 'checkin.png', 'checkin_f2.png', 'JTOOLBAR_CHECKIN', true);
		          }
		      }
		
		      //Show trash and delete for components that uses the state field
		      if (isset($this->items[0]->state)) {
		    if ($state->get('filter.state') == -2 && $canDo->get('core.delete')) {
		        JToolBarHelper::deleteList('', '«page.name»s.delete', 'JTOOLBAR_EMPTY_TRASH');
		        JToolBarHelper::divider();
		    } else if ($canDo->get('core.edit.state')) {
		        JToolBarHelper::trash('«page.name»s.trash', 'JTOOLBAR_TRASH');
		        JToolBarHelper::divider();
		    }
		      }
		
		      if ($canDo->get('core.admin')) {
		    JToolBarHelper::preferences('com_«component.name»');
		      }
		
		      //Set sidebar action - New in 3.0
		      JHtmlSidebar::setAction('index.php?option=com_mdd&view=«page.name»s');
		
		      $this->extra_sidebar = '';
		      
		JHtmlSidebar::addFilter(
		
			JText::_('JOPTION_SELECT_PUBLISHED'),
		
			'filter_published',
		
			JHtml::_('select.options', JHtml::_('jgrid.publishedOptions'), "value", "text", $this->state->get('filter.state'), true)
		
		);
		
		//Filter for the field user
		$this->extra_sidebar .= '<small><label for="filter_user">User</label></small>';
		$this->extra_sidebar .= JHtmlList::users('filter_user', $this->state->get('filter.user'), 1, 'onchange="this.form.submit();"');
		  }
		
		protected function getSortFields()
		{
			return array(
			'a.id' => JText::_('JGRID_HEADING_ID'),
			«readFields(entity, component)»
			);
		}
			
			}
	'''

	def CharSequence readFields(Entity entities, Component com) {
		var StringBuffer buff = new StringBuffer
		var int count = 0;
		for (Attribute e : entities.attributes) {
			if (count >= 5)
				buff.append('a.' + e.name + ''' => JText::_('COM_«com.name.toUpperCase»_«e.name.toUpperCase»'),''')
		}
		return buff.toString
	}

		

}
