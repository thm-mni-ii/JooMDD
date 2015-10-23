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
	private IndexPageTemplateHelper helper
	private String path
	private String pagename

	new(IndexPage dp, Component cp, String section, String path,IFileSystemAccess fsa) {

		ipage = dp
		com = cp
		sec = section
		helper = new IndexPageTemplateHelper(ipage,com,sec)
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
		
	  generateJoomlaDirectory(path +"/" + pagename)
	  generateFile(path+"/" + pagename +"/"+ "view.html.php", generateViewBackend())
	  generateJoomlaDirectory(path +"/" + pagename +"/" + "tmpl" )
	  generateFile(path+"/" + pagename+"/" + "tmpl"+"/" + "default.php" , generateAdminViewLayoutBackend())
		 
	}
		 
	def void generateController(){
		
		 generateFile(path+"/" + pagename + ".php", generateAdminController())
	}
	
	def void generateModel(){
		
		generateFile(path+"/" + pagename + ".php", generateAdminModel())
		 
	}

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
			
			«helper.genAdminViewDisplay»
			«helper.genAdminViewAddtoolbar»
			«helper.genAdminViewSortFields»
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
	«helper.genAdminViewLayoutHeader»
	«helper.genAdminViewLayoutForm»
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
		«helper.genAdminModelConstruct»
		«helper.genAdminModelGetItem»
		«helper.genAdminModelGetListQuery(ipage.filters)»
		«helper.genAdminModelSaveOrder»
		«helper.genAdminModelPopulateState»
		«helper.genGetIdOfReferenceItem»
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
		«helper.genAdminControllerContructer»
		«helper.genAdminControllerSaveOrdering»
		«helper.genAdminControllerGetModel»
	}
	 '''

	

		

}
