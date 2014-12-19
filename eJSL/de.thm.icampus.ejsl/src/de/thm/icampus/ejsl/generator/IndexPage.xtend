package de.thm.icampus.ejsl.generator

import de.thm.icampus.ejsl.eJSL.IndexPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.Section
import de.thm.icampus.ejsl.eJSL.BackendSection

class IndexPageTemplate extends DynamicPageTemplate{
	
	private IndexPage ipage
	private Component  com
	private Section sec
	
	new(IndexPage dp, Component cp, Section section){
		
		ipage = dp
		com = cp
		sec = section
	}
	
	def  CharSequence generateViewBackend(IndexPage page, Component component) '''
		<?php
		/**
		* @version v0.0.1
		* @category Joomla component
		* @package «(component.eContainer as Package).name»
		* @subpackage com_«Slug.slugify(component.name)».admin
		* @name «component.name»View
		* @description «page.entities.get(0).name»View for «component.name»
		«FOR author : component.manifest.authors»
			* @author «author.name», <«author.authoremail»>
		«ENDFOR»
		* @copyright «component.manifest.copyright»
		* @license «component.manifest.license»
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
		class «component.name»View«page.entities.get(0).name» extends JViewLegacy
		{
		 	protected $items;

			protected $pagination;

			protected $state;
		/**
		* @var JObject A cache for the available actions.
		* @since 1.6
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
		
		}
	'''
	
	
}