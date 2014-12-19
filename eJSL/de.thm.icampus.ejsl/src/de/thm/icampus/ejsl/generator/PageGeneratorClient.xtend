/**
    */
package de.thm.icampus.ejsl.generator

import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.eJSL.Page

public class PageGeneratorClient {
	def static CharSequence generateStaticPage(Page sp) '''
		INSERT INTO `#__content`
		(title, alias, introtext, state, created, created_by, attribs, metadata, version)
		VALUES
		(`«sp.name»`, `«sp.name.toLowerCase»`
		, `«sp.hashCode»`
		, 1
		, GETDATE()
		, 1
		, `{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","alternative_readmore":"","article_layout":""}`
		, `{"robots":"","author":"","rights":"","xreference":""}`
		, 1
		)
	'''

	def static CharSequence generateView(Page page, Component component) '''
		<?php
		/**
		* @version v0.0.1
		* @category Joomla component
		* @package «(component.eContainer as Package).name»
		* @subpackage com_«Slug.slugify(component.name)».«if(page.eContainer instanceof BackendSection) "admin" else "site"»
		* @name «component.name»View
		* @description «(page as DynamicPage).entities.get(0).name»View for «component.name»
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
		* @category Joomla.Component.«if(page.eContainer instanceof BackendSection) "Admin" else "Site"»
		* @package com_«Slug.slugify(component.name)».«if(page.eContainer instanceof BackendSection) "admin" else "site"»
		* @since Class available since Release x.x.x
		*/
		class «component.name»View«(page as DynamicPage).entities.get(0).name» extends JView
		{
		/**
		* @var JObject A cache for the available actions.
		* @since 1.6
		*/
		protected static $actions;
		/**
		* Method to get display
		*
		* @param Object $tpl template
		*
		* @return void
		*/
		public function display($tpl = null)
		{
		$uid = JRequest::getVar('id');
		$data = new ArrayObject();
		$model = $this->getModel();
		$data = $model->getData($uid);
		$this->assignRef('data', $data);
		parent::display($tpl);
		}
		}
	'''

	def static CharSequence generateTemplate(Page page, Component component) '''
		'''

	def static CharSequence generateController(Page page, Component component) '''
		<?php
		/**
		* @version v0.0.1
		* @category Joomla component
		* @package «(component.eContainer as Package).name»
		* @subpackage com_«Slug.slugify(component.name)».«if(page.eContainer instanceof BackendSection) "admin" else "site"»
		* @name THMSocialNetworkViewController«(page as DynamicPage).entities.get(0).name»
		* @description «(page as DynamicPage).entities.get(0).name»Controller for THM Social Network
		«FOR author : component.manifest.authors»
			* @author «author.name», <«author.authoremail»>
		«ENDFOR»
		* @copyright «component.manifest.copyright»
		* @license «component.manifest.license»
		*/
		defined('_JEXEC') or die();
		jimport('joomla.application.component.controller');
		/**
		* «component.name»Controller class for component com_«Slug.slugify(component.name)»
		*
		* @category Joomla.Component.Admin
		* @package com_«Slug.slugify(component.name)».«if(page.eContainer instanceof BackendSection) "admin" else "site"»
		* @since Class available since Release x.x.x
		*/
		class «component.name»Controller«(page as DynamicPage).entities.get(0).name» extends JController
		{
		/**
		* constructor (registers additional tasks to methods)
		*
		*/
		public function __construct()
		{
		parent::__construct();
		$this->registerTask('publish', '');
		$this->registerTask('unpublish', '');;
		}
		}
	'''
} // PageGeneratorClient

