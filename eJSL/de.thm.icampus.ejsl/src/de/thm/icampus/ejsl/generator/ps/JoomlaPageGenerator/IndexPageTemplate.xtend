package de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.ejsl.generator.ps.JoomlaUtil.Slug
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute

class IndexPageTemplate extends DynamicPageTemplate {

	private ExtendedDynamicPage ipage
	private ExtendedComponent com
	private String sec
	private IndexPageTemplateAdminHelper helperAdmin
	private IndexPageTemplateSiteHelper frontHelp
	private String path
	private String pagename

	new(ExtendedDynamicPage dp, ExtendedComponent cp, String section, String path,IFileSystemAccess fsa) {

		ipage = dp
		com = cp
		sec = section
		helperAdmin = new IndexPageTemplateAdminHelper(ipage,com,sec)
		frontHelp = new IndexPageTemplateSiteHelper(ipage,com,sec)
		this.path = path
		pagename = dp.name.toLowerCase
		this.fsa = fsa
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
		generateFile(path+"/forms/"+ "filter_" + pagename + ".xml",  generateAdminModelForms())
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
		
		«IF !ipage.entities.get(0).references.empty»
		«Slug.genLinkedInfo(ipage,com)»
		«ENDIF»
		«helperAdmin.genAdminModelConstruct»
		«helperAdmin.genAdminModelGetItem»
		«helperAdmin.genAdminModelGetListQuery(ipage.filters)»
		«helperAdmin.genAdminModelSaveOrder»
		«helperAdmin.genAdminModelPopulateState»
	 «IF !ipage.entities.get(0).references.empty»
		«helperAdmin.genGetIdOfReferenceItem»
	«ENDIF»
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
	«generateFileDoc(ipage.instance,com,true)»
	
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
	
   def CharSequence generateAdminModelForms() '''
   <?xml version="1.0" encoding="utf-8"?>
   <form>
       <fields name="filter">
           <field
                   name="search"
                   type="text"
                   label="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_SEARCH_DESC"
               description="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_SEARCH_DESC"
               hint="JSEARCH_FILTER"
               />
       <field
               name="status"
               type="status"
               label="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_PUBLISHED"
               description="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_PUBLISHED_DESC"
   
                   onchange="this.form.submit();"
                   >
               <option value="">JOPTION_SELECT_PUBLISHED</option>
           </field>
        <field
              name="created_by"
              type="«com.name.toLowerCase»user"
              label="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_CREATED_BY"
              description="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_CREATED_BY"
               entity = "«ipage.extendedEntityList.get(0).name.toLowerCase»"
              onchange="this.form.submit();"
              >
          <option value="">JOPTION_SELECT_ATTRIBUTE</option>
      </field>
          «FOR ExtendedAttribute attr : ipage.extendFiltersList»
           <field
                name="«attr.name»"
                type="«ipage.extendedEntityList.get(0).name.toLowerCase»"
                label="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_«attr.name.toUpperCase»"
                description="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_«attr.name.toUpperCase»"
                 valueColumn="«attr.name.toLowerCase»"
                 textColumn="«attr.name.toLowerCase»"
                onchange="this.form.submit();"
                >
            <option value="">JOPTION_SELECT_ATTRIBUTE</option>
        </field>
          «ENDFOR»
            </fields>
          </form>
   '''
	
		

}
