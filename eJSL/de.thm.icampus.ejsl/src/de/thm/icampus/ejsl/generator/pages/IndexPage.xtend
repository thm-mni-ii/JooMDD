package de.thm.icampus.ejsl.generator.pages

import de.thm.icampus.ejsl.eJSL.IndexPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.Section
import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.Entity
import java.util.List
import de.thm.icampus.ejsl.eJSL.Attribute
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.ejsl.generator.util.Slug

class IndexPageTemplate extends DynamicPageTemplate {

	private IndexPage ipage
	private Component com
	private String sec
	private IndexPageTemplateAdminHelper helperAdmin
	private IndexPageTemplateSiteHelper frontHelp
	private String path
	private String pagename

	new(IndexPage dp, Component cp, String section, String path,IFileSystemAccess fsa) {

		ipage = dp
		com = cp
		sec = section
		helperAdmin = new IndexPageTemplateAdminHelper(ipage,com,sec)
		frontHelp = new IndexPageTemplateSiteHelper(ipage,com,sec)
		this.path = path
		pagename = dp.name.toLowerCase
		this.fsa = fsa
		ipage.formatName
	}
	
	def void formatName(IndexPage page){
		page.name = Slug.slugify(page.name)
		for(Entity e: page.entities){
			for(Attribute attr : e.attributes){
				attr.name = Slug.slugify(attr.name)
			}
		}
		}
	
	def void generateView(){
	
	 if(sec.compareTo("admin")==0){
	  generateJoomlaDirectory(path +"/" + pagename)
	  generateFile(path+"/" + pagename +"/"+ "view.html.php", generateViewBackend())
	  generateJoomlaDirectory(path +"/" + pagename +"/" + "tmpl" )
	  generateFile(path+"/" + pagename+"/" + "tmpl"+"/" + "default.php" , generateAdminViewLayoutBackend())
	}else{
		  generateJoomlaDirectory(path+"/" + pagename)
		  generateFile(path+"/" + pagename+"/" + "view.html.php", generateSiteView())
		  generateJoomlaDirectory(path +"/"+ pagename+"/" + "tmpl")
		   generateFile(path +"/"+ pagename+"/"  + "tmpl"+"/" + "default.php" , generateSiteViewLayoutShow())
		   generateFile(path +"/"+ pagename+"/" + "tmpl"+"/" + "default.xml" , xmlSiteTemplateContent(pagename, ipage,com))
	} 
	
	}
	
	def CharSequence generateSiteViewLayoutShow() '''
	«generateFileDoc(ipage,com, false)»
	«frontHelp.genViewTemplateInit»
	«frontHelp.genViewTemplateHead»
	'''
	
	
		 
	def void generateController(){
		if(sec.compareTo("admin")==0){
		 generateFile(path+"/" + pagename + ".php", generateAdminController())
		 }else{
		 	generateFile(path+"/" + pagename + ".php", generateSiteController())
		 	
		 }
	}
	
	def CharSequence generateSiteController() '''
	<?php
	 «generateFileDoc(ipage, com,true)»
	// No direct access.
		defined('_JEXEC') or die;
		
		require_once JPATH_COMPONENT.'/controller.php';
		
		/**
		 * Sliders list controller class.
		 */
		class «com.name.toFirstUpper»Controller«ipage.name.toFirstUpper» extends «com.name.toFirstUpper»Controller
		{
			/**
			 * Proxy for getModel.
			 * @since	1.6
			 */
			public function &getModel($name = '«ipage.name.toFirstUpper»', $prefix = '«com.name.toFirstUpper»Model', $config = array())
			{
				$model = parent::getModel($name, $prefix, array('ignore_request' => true));
				return $model;
			}
		}
	'''
	
	def void generateModel(){
		if(sec.compareTo("admin")==0){
		generateFile(path+"/" + pagename + ".php", generateAdminModel())
		}else{
		 generateFile(path+"/" + pagename  + ".php", generateSiteModelShow)
		  generateFile(path + "/forms"+"/" + pagename + ".xml", xmlAdminFields(ipage,com,com.name))
			
		}
		 
	}
	
	def CharSequence generateSiteModelShow() '''
	«generateFileDoc(ipage, com, true)»
	
	jimport('joomla.application.component.modellist');

	/**
	 * Methods supporting a list of Data.
	 * @generated
	 */
	class «com.name.toFirstUpper»Model«ipage.name.toFirstUpper» extends JModelList {
		
		«Slug.genLinkedInfo(ipage,com)»
		«helperAdmin.genAdminModelConstruct»
		«helperAdmin.genAdminModelGetItem»
		«helperAdmin.genAdminModelGetListQuery(ipage.filters)»
		«helperAdmin.genGetIdOfReferenceItem»
	}
	
	'''
	


	def CharSequence generateViewBackend() '''
		«generateFileDoc(ipage, com,true)»
		/**
		* @description «ipage.entities.get(0).name»View for «com.name»
		*/
		defined('_JEXEC') or die('Restricted access');
		jimport('joomla.application.component.view');
		jimport('joomla.filesystem.path');
		/**
		* «com.name»View class for component com_«Slug.slugify(com.name)»
		*
		* @category Joomla.Component. Admin
		* @package com_«Slug.slugify(com.name)»."admin"
		* @generated
		*/
		class «com.name.toFirstUpper»View«ipage.name.toFirstUpper» extends JViewLegacy
		{
		 	protected $items;
		
			protected $pagination;
		
			protected $state;
			
			«helperAdmin.genAdminViewDisplay»
			«helperAdmin.genAdminViewAddtoolbar»
			«helperAdmin.genAdminViewSortFields»
		}
	'''
	def CharSequence generateAdminViewLayoutBackend() ''' 
	«generateFileDoc(ipage,com, false)»
	JHtml::addIncludePath(JPATH_COMPONENT.'/helpers/html');
	JHtml::_('bootstrap.tooltip');
	JHtml::_('behavior.multiselect');
	JHtml::_('formbehavior.chosen', 'select');
	
	// Import CSS
	$document = JFactory::getDocument();
	«helperAdmin.genAdminViewLayoutHeader»
	«helperAdmin.genAdminViewLayoutForm»
	'''
	
	def CharSequence generateAdminModel()'''
	«generateFileDoc(ipage, com, true)»
	
	jimport('joomla.application.component.modellist');

	/**
	 * Methods supporting a list of Data.
	 * @generated
	 */
	class «com.name.toFirstUpper»Model«ipage.name.toFirstUpper» extends JModelList {
		
		«Slug.genLinkedInfo(ipage,com)»
		«helperAdmin.genAdminModelConstruct»
		«helperAdmin.genAdminModelGetItem»
		«helperAdmin.genAdminModelGetListQuery(ipage.filters)»
		«helperAdmin.genAdminModelSaveOrder»
		«helperAdmin.genAdminModelPopulateState»
		«helperAdmin.genGetIdOfReferenceItem»
	}
	
	'''
	
	def CharSequence generateAdminController()'''
	«generateFileDoc(ipage, com, true)»

	jimport('joomla.application.component.controlleradmin');
	
	/**
	 * «ipage.name.toFirstUpper» list controller .
	 * @generated
	 */
	class «com.name.toFirstUpper»Controller«ipage.name.toFirstUpper» extends JControllerAdmin
	{
		«helperAdmin.genAdminControllerContructer»
		«helperAdmin.genAdminControllerSaveOrdering»
		«helperAdmin.genAdminControllerGetModel»
	}
	 '''
 def CharSequence generateSiteView()'''
	«generateFileDoc(ipage,com,true)»
	
	jimport('joomla.application.component.view');

/**
 * View to  Show the Data
 */
class «com.name.toFirstUpper»View«ipage.name.toFirstUpper» extends JViewLegacy {

    protected $state;
    protected $item;
    protected $form;
    protected $params;
    «frontHelp.generateSiteViewDisplay()»
    «frontHelp.generateSiteViewprepareDocument()»
    } 
	'''
	

		

}
