package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug
import org.eclipse.xtext.generator.IFileSystemAccess2

/**
 * This class contains the templates to generate the necessary code for view templates.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 * 
 */
class IndexPageTemplate extends DynamicPageTemplate {

	private ExtendedDynamicPage ipage
	private ExtendedComponent com
	private String sec
	private IndexPageTemplateAdminHelper helperAdmin
	private IndexPageTemplateSiteHelper frontHelp
	private String path
	private String pagename

	new(ExtendedDynamicPage dp, ExtendedComponent cp, String section, String path,IFileSystemAccess2 fsa) {
		ipage = dp
		com = cp
		sec = section
		helperAdmin = new IndexPageTemplateAdminHelper(ipage,com,sec)
		frontHelp = new IndexPageTemplateSiteHelper(ipage,com,sec)
		this.path = path
		pagename = Slug.slugify(dp.name.toLowerCase)
		this.fsa = fsa
	}
	
	def void generateView() {
	    if(sec.equalsIgnoreCase("admin")) {
	        generateFile(path+"/" + pagename +"/"+ "view.html.php", generateViewBackend())
	        generateFile(path+"/" + pagename+"/" + "tmpl"+"/" + "default.php" , generateAdminViewLayoutBackend())
	    } else {
	        generateFile(path+"/" + pagename+"/" + "view.html.php", generateSiteView())
	        generateFile(path +"/"+ pagename+"/"  + "tmpl"+"/" + "default.php" , generateSiteViewLayoutShow())
	        generateFile(path +"/"+ pagename+"/" + "tmpl"+"/" + "default.xml" , xmlSiteTemplateContent(pagename, ipage,com))
	    }
	}
	
	def CharSequence generateSiteViewLayoutShow() '''
	    «generateFileDoc(ipage,com)»
	    
	    «Slug.generateUses(newArrayList("LayoutHelper", "Route", "Factory", "Html", "Text"))»
	    
	    «frontHelp.genViewTemplateInit»
	    «frontHelp.genViewTemplateHead»
	'''	
	 
	def void generateController() {
	    if(sec.equalsIgnoreCase("admin")){
	        generateFile(path+"/" + pagename + ".php", generateAdminController())
	    } else {
	        generateFile(path+"/" + pagename + ".php", generateSiteController())
	    }
	}
	
	def CharSequence generateSiteController() '''
	    «generateFileDoc(ipage, com)»
	    
	    «Slug.generateRestrictedAccess()»
	    
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
	        public function getModel($name = '«ipage.name.toFirstUpper»', $prefix = '«com.name.toFirstUpper»Model', $config = array())
	        {
	            $model = parent::getModel($name, $prefix, array('ignore_request' => true));

	            return $model;
	        }
	    }
	'''
	
	def void generateModel() {
	    if (sec.compareTo("admin")==0) {
	        generateFile(path+"/" + pagename + ".php", generateAdminModel())
	        generateFile(path+"/forms/"+ "filter_" + pagename + ".xml",  generateAdminModelForms())
	    } else {
	        generateFile(path+"/" + pagename  + ".php", generateSiteModelShow)
	        generateFile(path + "/forms"+"/" +"filter_" + pagename + ".xml", generateAdminModelForms)
	    } 
	}
	
	
	def CharSequence generateSiteModelShow() '''
	    «generateFileDoc(ipage, com)»
	    «Slug.generateRestrictedAccess()»
	    «Slug.generateUses(newArrayList("ModelList", "ComponentHelper", "Factory"))»
	    
	    /**
	     * Methods supporting a list of Data.
	     * @generated
	     */
	    class «com.name.toFirstUpper»Model«ipage.name.toFirstUpper» extends ListModel
	    {
	        «Slug.genLinkedInfo(ipage,com)»
	        «helperAdmin.genAdminModelConstruct»
	        «helperAdmin.genAdminModelGetItem»
	        «helperAdmin.genAdminModelPopulateState»
	        «helperAdmin.genAdminModelGetListQuery(ipage.filters)»
	        «helperAdmin.genGetIdOfReferenceItem»
	    }
	'''
	
	def CharSequence generateViewBackend() '''
	    «generateFileDoc(ipage, com)»
	    
	    «Slug.generateRestrictedAccess()»
	    
	    «Slug.generateUses(newArrayList("ViewLegacy", "Text"))»
	    
	    /**
	     * @description «ipage.entities.get(0).name»View for «com.name»
	     */
	    jimport('joomla.filesystem.path');
	    /**
	     * «com.name»View class for component com_«Slug.slugify(com.name)»
	     *
	     * @category Joomla.Component. Admin
	     * @package com_«Slug.slugify(com.name)»."admin"
	     * @generated
	     */
	    class «com.name.toFirstUpper»View«ipage.name.toFirstUpper» extends HtmlView
	    {
	        protected $items;
	        protected $pagination;
	        protected $state;
	        «helperAdmin.genAdminViewDisplay»
	        «helperAdmin.genAdminViewAddtoolbar»
	    }
	'''
	
	def CharSequence generateAdminViewLayoutBackend() ''' 
	    «generateFileDoc(ipage,com)»
	    
	    «Slug.generateUses(newArrayList("LayoutHelper", "Route", "Factory", "Html"))»
	    
	    HTMLHelper::addIncludePath(JPATH_COMPONENT.'/helpers/html');
	    HTMLHelper::_('bootstrap.tooltip');
	    HTMLHelper::_('behavior.multiselect');
	    HTMLHelper::_('formbehavior.chosen', 'select');

	    // Import CSS
	    $document = Factory::getDocument();
	    «helperAdmin.genAdminViewLayoutHeader»
	    «helperAdmin.genAdminViewLayoutForm»
	'''
	
	def CharSequence generateAdminModel()'''
	    «generateFileDoc(ipage, com)»
	    
	    «Slug.generateRestrictedAccess()»

	    «Slug.generateUses(newArrayList("ModelList", "ComponentHelper", "Factory"))»

	    /**
	     * Methods supporting a list of Data.
	     * @generated
	     */
	    class «com.name.toFirstUpper»Model«ipage.name.toFirstUpper» extends ListModel
	    {
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
	    «generateFileDoc(ipage, com)»
	    
	    «Slug.generateRestrictedAccess()»

	    «Slug.generateUses(newArrayList("ControllerAdmin", "ResponseJson", "Factory"))»
	    
	    /**
	     * «ipage.name.toFirstUpper» list controller .
	     * @generated
	     */
	    class «com.name.toFirstUpper»Controller«ipage.name.toFirstUpper» extends AdminController
	    {
	        «helperAdmin.genAdminControllerContructer»
	        «helperAdmin.genAdminControllerSaveOrdering»
	        «helperAdmin.genAdminControllerGetModel»
	    }
	'''
	
	def CharSequence generateSiteView()'''
	    «generateFileDoc(ipage.instance,com)»
	    
	    «Slug.generateRestrictedAccess()»

	    «Slug.generateUses(newArrayList("ViewLegacy", "Text", "Factory"))»

	    /**
	     * View to  Show the Data
	     */
	    class «com.name.toFirstUpper»View«ipage.name.toFirstUpper» extends HtmlView
	    {
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
                        name="state"
                        type="status"
                        label="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_PUBLISHED"
                        description="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_PUBLISHED_DESC"
                        onchange="this.form.submit();">
                        <option value="">JOPTION_SELECT_PUBLISHED</option>
                    </field>
                    <field
                        name="created_by"
                        type="componentuser"
                        label="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_CREATED_BY"
                        description="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_CREATED_BY"
                        entity = "«ipage.extendedEntityList.get(0).name.toLowerCase»"
                        onchange="this.form.submit();">
                        <option value="">JOPTION_SELECT_CREATED_BY</option>
                    </field>
                    «FOR ExtendedAttribute attr : ipage.extendFiltersList»
                    <field
                        name="«attr.name»"
                        type="«ipage.extendedEntityList.get(0).name.toLowerCase»"
                        label="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_«attr.name.toUpperCase»"
                        description="«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_«attr.name.toUpperCase»"
                        valueColumn="«attr.entity.name.toLowerCase».«attr.name.toLowerCase»"
                        textColumn="«attr.entity.name.toLowerCase».«attr.name.toLowerCase»"
                        onchange="this.form.submit();">
                        <option value="">JOPTION_SELECT_«attr.name.toUpperCase»</option>
                    </field>
                    «ENDFOR»
                </fields>
                <fields name="list">
                    <field name="limit" id="limit" class="input-medium" default="25" onchange="this.form.submit();" type="limitbox" >
                    <option value="">JOPTION_SELECT_LIMIT</option>
                </field>
            </fields>
        </form>
    '''
}
