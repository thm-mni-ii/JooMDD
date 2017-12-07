package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator;

import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.AbstractExtensionGenerator
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.EntityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.LanguageGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.PageGenerator
import java.util.Calendar
import java.util.List
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2
import java.util.LinkedList

/**
 * This class contains the templates to generate the necessary folders and files for a Joomla component.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
public class ComponentGenerator extends AbstractExtensionGenerator {

	private String slug
	private ExtendedComponent extendedComp
	private String class_name
	private String updatePath
	private String sitePath 
	private String adminPath
	private String mediaPath 
 
    /**
     * this Constructor collects the initial parameters to generate a component
     * Format the name of the extension
     * 
     * @param ExtendedComponent   component   contains the instance of a component
     * @param IFileSystemAccess2  fsa         create the files and folder
     * @param String              path        contains the Path for the extension
     * @param String              updatePath  contains the path for the update folder
     * 
     */
	new (ExtendedComponent component, IFileSystemAccess2 fsa, String path, String updatePath) {
		this.fsa = fsa;
		this.slug = component.name
		this.noPrefixName = this.slug
		this.name = "com_" + this.slug

		this.extendedComp = component
		this.class_name = this.noPrefixName.toFirstUpper
		this.extendedComp.name = Slug.slugify(extendedComp.name)
		this.path = path
		this.updatePath = updatePath
		this.sitePath = path + "components/" + this.extendedComp.extensionName
		this.adminPath = path + "administrator/components/" + this.extendedComp.extensionName
		this.mediaPath =  path + "media/" + extendedComp.extensionName
	}
	
	/**
	 * Generates the folder structure and the installation script of a component.
	 * 
	 */
	override generate() {
		println("component path " +path);

		var List<ExtendedDynamicPage>  indexPages = new LinkedList<ExtendedDynamicPage>()
		for(ExtendedPageReference ext : extendedComp.backEndExtendedPagerefence) {
            if(ext.extendedPage.extendedDynamicPageInstance !== null) {
                indexPages.add(ext.extendedPage.extendedDynamicPageInstance)  
            } 
  		}

        // Generate the the installation path for a compoenent
		generateFile(path + name + ".xml", extendedComp.xmlContent(indexPages))
		generateFile(path + "script.php", generateScript(extendedComp, name))

		// Generate language folders and files
		var LanguageGenerator langgen = new LanguageGenerator(fsa)
		langgen.genComponentLanguage(extendedComp,path)

		//Generate media folder
		generateEmptyDirectory(mediaPath+"/images")
		generateFile( mediaPath + "/js/setForeignKeys.js", genScriptForForeignKeys)
		generateFile( mediaPath + "/js/setMultipleForeignKeys.js", genScriptForMultipleForeignKeys)
		var ComponentHelperGenerator help = new ComponentHelperGenerator(extendedComp)
		
		generateFile( mediaPath + "/js/bootsnip.js", help.genBootsnipJS)
		generateFile( mediaPath + "/css/bootsnip.css",help.genBootsnipCSS)
		
		//Generate images folder
		for(detailsPages : indexPages.filter[t| t.detailsPage && t.haveFiletoLoad]) {
			generateEmptyDirectory(mediaPath + "/" + detailsPages.name.toLowerCase + "/images")
			generateEmptyDirectory(mediaPath + "/" + detailsPages.name.toLowerCase + "/files")
		}

		// Generate frontend section 
		if (extendedComp.frontEndExtendedPagerefence !== null) {
			generateFrontendSection
		}
	    // Generate backend section 
		if (extendedComp.backEndExtendedPagerefence !== null) {
			generateBackendSection
		}
		return ""
	}

	
	/**
	 * Generates the manifest file for a Joomla component. 
	 * For more information see the site: https://docs.joomla.org/J3.x:Developing_an_MVC_Component/
	 * Developing_a_Basic_Component#helloworld.xml
	 * 
	 * @param ExtendedComponent         component	Containt a component
	 * @param List<ExtendedDynamicPage> dymPages	Containt all ExtendedDynamic of a Component
	 * 
	 * @return  Charsequence code for the manifest file
	 */
	def CharSequence xmlContent(ExtendedComponent component, List<ExtendedDynamicPage> dymPages) '''
		<?xml version="1.0" encoding="utf-8"?>
		<extension type="component" version="3.5" method="upgrade">
		    <name>«component.name»</name>
		    «Slug.generateAuthors(component.manifest.authors)»
		    «IF (component.manifest.creationdate !== null)»
		    <creationDate>«component.manifest.creationdate»</creationDate>
		    «ELSE»
		    <creationDate>«Calendar::instance.get(Calendar::YEAR)»</creationDate>
		    «ENDIF»
		    «IF (component.manifest.copyright !== null)»
		    <copyright>«component.manifest.copyright»</copyright>
		    «ENDIF»
		
		    «IF (component.manifest.license !== null)»
		    <license>«component.manifest.license»</license>
		    «ENDIF»
		    «IF (component.manifest.version !== null)»
		    <version>«component.manifest.version»</version>
		    «ELSE»
		    <version>1.0.1</version>
		    «ENDIF»
		    «IF (component.manifest.description !== null)»
		    <description>«component.manifest.description»</description>
		    «ENDIF»
		
		    <scriptfile>script.php</scriptfile>
		    <!-- Install Section -->
		    <install>
		        <sql>
		            <file driver="mysql" charset="utf8">sql/install.mysql.utf8.sql</file>
		        </sql>
		    </install>
		
		    <!-- Uninstall Section -->
		    <uninstall>
		        <sql>
		            <file driver="mysql" charset="utf8">sql/uninstall.mysql.utf8.sql</file>
		        </sql> 
		    </uninstall>
		
		    <!-- Update Section -->
		    <update>
		        <schemas>
		            <schemapath type="mysql">sql/updates/mysql</schemapath>
		        </schemas>
		    </update>
		    <media destination="«name»" folder="media/«extendedComp.extensionName»">
		        <folder>images</folder>
		        <folder>js</folder>
		        <folder>css</folder>
		        «FOR page : dymPages.filter[t | t !== null && t.detailsPage && t.haveFiletoLoad]»
		        <folder>«page.name.toLowerCase»</folder>
		        «ENDFOR»
		    </media>
		
		    <!-- Site Main File Copy Section -->
		    <files folder="components/«extendedComp.extensionName»">
		        <filename>«noPrefixName».php</filename>
		        <filename>controller.php</filename>
		        <filename>dispatcher.php</filename>
		        <!-- Additional Files -->
		        <folder>View</folder>
		        <folder>Model</folder>
		        <folder>language</folder>
		        <folder>Controller</folder>		       
		    </files>
		    
		    <languages>
		        «FOR lang : component.languages»
		        «IF !lang.sys»
		        <language tag="«lang.name»">components/«extendedComp.extensionName»/language/«lang.name»/«lang.name».«this.name».ini</language>
		        «ELSE»
		        <language tag="«lang.name»">components/«extendedComp.extensionName»/language/«lang.name»/«lang.name».«this.name».sys.ini</language>
		        «ENDIF»
		        «ENDFOR»
		    </languages>
		
		    <administration>
		        <!-- Administration Menu Section -->
		        <menu>«Slug.nameExtensionBind("com",component.name).toUpperCase»</menu>
		        <submenu>
		            «FOR page : dymPages.filter[t | !t.detailsPage]»
		            <menu link="option=«Slug.nameExtensionBind("com",component.name).toLowerCase»&amp;view=«page.name.toLowerCase»" 
		                alias="«Slug.nameExtensionBind("com", component.name).toUpperCase»_ALIAS_«page.name.toUpperCase»"
		                view="«page.name.toLowerCase»">«Slug.nameExtensionBind("com", component.name).toUpperCase»_TITLE_«page.name.toUpperCase»</menu>
		    «ENDFOR»
		        </submenu>
		        <!-- Administration Main File Copy Section -->
		        <files folder="administrator/components/«extendedComp.extensionName»">
		            <!-- Admin Main File Copy Section -->
		            <filename>«noPrefixName».php</filename>
		            <filename>controller.php</filename>
		            <filename>dispatcher.php</filename>
		            <filename>access.xml</filename>
		            <filename>config.xml</filename>
		            <!-- SQL Files Section -->
		            <folder>sql</folder>
		            <!-- Table Files Section -->
		            <folder>Table</folder>
		            <!-- Model Files Section -->
		            <folder>Model</folder>
		            <!-- View Files Section -->
		            <folder>View</folder>
		            <folder>language</folder>
		            <folder>Controller</folder>
		            <folder>Helper</folder>
		        </files>
		
		        <languages>
		            «FOR lang : component.languages»
		            «IF !lang.sys»
		            <language tag="«lang.name»">administrator/components/«extendedComp.extensionName»/language/«lang.name»/«lang.name».«this.name».ini</language>
		            «ELSE»
		            <language tag="«lang.name»">administrator/components/«extendedComp.extensionName»/language/«lang.name»/«lang.name».«this.name».sys.ini</language>
		            «ENDIF»
		            «ENDFOR»
		        </languages>
		    </administration>
		</extension>
	'''

    /**
     * Generates all the folders and files for the frontend of a component.
     * Uses the entity an page generator.
     * 
     */
	private def void generateFrontendSection() {
		// Generate frontend section
		generateFile( sitePath + "/" + noPrefixName + ".php", extendedComp.phpSiteContent)
		generateFile( sitePath + "/controller.php", extendedComp.phpSiteControllerContent)
		generateFile( sitePath + "/dispatcher.php", extendedComp.phpSiteDispatcherContent)
		generateFile( sitePath + "/router.php", extendedComp.phpSiteRouterContent)
		        
        var EntityGenerator entitygen = new EntityGenerator(extendedComp,sitePath + "/",fsa,false)
		entitygen.dogenerate()

		var EList<ExtendedPage> tempPageList = new BasicEList()
		
		for (pageref : extendedComp.frontEndExtendedPagerefence) {
           tempPageList.add(pageref.extendedPage)
		}
		
		var PageGenerator pgGen 
        pgGen = new PageGenerator(extendedComp, tempPageList,fsa,sitePath,"site",false)
		pgGen.dogenerate
		generateUpdatePages(tempPageList,"site")
	}
	
	def CharSequence phpSiteDispatcherContent(ExtendedComponent component) '''
		<?php
		/**
		 *
		 */
		 
		«Slug.generateRestrictedAccess»
		
		use Joomla\CMS\Dispatcher\Dispatcher;
		
		/**
		 * Dispatcher class
		 */
		class «class_name»Dispatcher extends Dispatcher
		{
			/**
			 * The extension namespace
			 *
			 * @var    string
			 */
			protected $namespace = '\\';
		}
	'''
	
    /**
     * Generates all the folders and files for the frontend of a component.
     * Uses the entity and page generator.
     * 
     */
	private def void generateBackendSection() {
        var List<ExtendedDynamicPage> indexPages = new LinkedList<ExtendedDynamicPage>()
		for(ExtendedPageReference ext: extendedComp.backEndExtendedPagerefence) {
			if(ext.extendedPage.extendedDynamicPageInstance !== null) {
                indexPages.add(ext.extendedPage.extendedDynamicPageInstance)   
			}
  		}
  		// Generate sql folders
		
		generateFile( adminPath + "/" + noPrefixName + ".php", extendedComp.phpAdminContent)
		generateFile( adminPath + "/controller.php", extendedComp.phpAdminControllerContent)
		generateFile( sitePath + "/dispatcher.php", extendedComp.phpAdminDispatcherContent)
		generateFile( adminPath + "/access.xml", extendedComp.xmlAccessContent)
		generateFile( adminPath + "/config.xml", extendedComp.xmlConfigContent(indexPages))
		
		var tempSlug = slug + "s"
		generateFile(adminPath + "/View/" + Slug.capitalize(tempSlug) + "/HtmlView.php", 
		    extendedComp.phpAdminViewContent)
		generateFile(adminPath + "/tmpl/" + tempSlug + "/default.php", 
		    extendedComp.phpAdminTemplateContent
		)
		
		var de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.ComponentHelperGenerator help = new de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.ComponentHelperGenerator(extendedComp)
		generateFile( adminPath + "/Helper/" + extendedComp.name.toLowerCase + ".php", 
		    help.generate
		)

		var EntityGenerator entitygen
		entitygen = new EntityGenerator(extendedComp, adminPath + "/", fsa, true)
		entitygen.dogenerate()
		
		var EList<ExtendedPage> tempPageList = new BasicEList()
		for (pageref : extendedComp.backEndExtendedPagerefence) {
           tempPageList.add(pageref.extendedPage)
		}
		
		var PageGenerator pgGen
		pgGen = new PageGenerator(extendedComp, tempPageList, fsa, adminPath, "admin", false)
		pgGen.dogenerate
		generateUpdatePages(tempPageList, "admin")
		
	}
	
	def CharSequence phpAdminDispatcherContent(ExtendedComponent component) '''
		<?php
		/**
		 *
		 */
		 
		«Slug.generateRestrictedAccess»
		
		use Joomla\CMS\Dispatcher\Dispatcher;
		
		/**
		 * Dispatcher class
		 */
		class «class_name»Dispatcher extends Dispatcher
		{
			/**
			 * The extension namespace
			 *
			 * @var    string
			 */
			protected $namespace = '\\';
		}
	'''
	
	/**
	 * Generates the update part of the component, this contains only the folder which is needed
	 * by the component for a page.
	 * However, the structure of a page is different, if a page is used for the frontent or backend.
	 * 
	 * @param  EList<ExtendedPage> pageRefList  List of the pages
	 * @param  String 			   section       section of a component(Backend or Frontend)
	 */
    public def void generateUpdatePages (EList<ExtendedPage> pageRefList, String section) {
    	var PageGenerator pgGenUpdate
    	pgGenUpdate= new PageGenerator(extendedComp, pageRefList,fsa, updatePath, section,true)
		pgGenUpdate.dogenerate 
    }
    
    /**
     * Contains the template for the frontend's entry point of a component.
     * For more information see component_name.php.
     * 
     * @param Component component Containt the instance of a ejsl component
     * @return Charsequence
     */
	def CharSequence phpSiteContent(Component component) '''
		<?php
		«Slug.generateFileDoc(component)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ControllerLegacy", "Factory"))»
		
		// Get an instance of the controller prefixed by «class_name»
		$controller = BaseController::getInstance('«class_name»');
		
		// Perform the Request task
		$input = Factory::getApplication()->input;
		$controller->execute($input->getCmd('task'));
		
		// Redirect if set by the controller
		$controller->redirect();
	'''
	
	/**
	 * Generates the code for the main controller of a Joomla component, see controller.php. 
	 * 
	 * @param ExtendedComponent component  containt a intansce of a extended ejsl component
	 * 
	 * @return Charsequence
	 * 
	 */
	def CharSequence phpSiteControllerContent(ExtendedComponent component) '''
		<?php
		«Slug.generateFileDoc(component)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ControllerLegacy", "Factory"))»
		
		/**
		 * General Controller of «component.name» component
		 */
		class «class_name»Controller extends BaseController
		{
		    /**
		     * display task
		     *
		     * @return  void
		     */
		    public function display($cachable = false, $urlparams = array())
		    {
		        // set default view if not set
		        $input = Factory::getApplication()->input;
		        $input->set('view', $input->getCmd('view', '«component.name»'));
		
		        // call parent behavior
		        parent::display($cachable);
		    }
		}
	'''
	
	/**
     * Contains the template for the entry point of the backend of a component.
     * For more information see component_name.php.
     * 
     * @param Component component Containt the instance of a ejsl component
     * @return Charsequence
     */
	def CharSequence phpAdminContent(ExtendedComponent component) '''
		<?php
		«Slug.generateFileDoc(component)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ControllerLegacy", "Text", "Factory"))»
		
		// Access check.
		if (!Factory::getUser()->authorise('core.manage', '«Slug::nameExtensionBind("com",component.name )»')) {
		    throw new Exception(Text::_('JERROR_ALERTNOAUTHOR'));
		}
		
		// Get an instance of the controller prefixed by «Slug::nameExtensionBind("com",component.name )»
		$controller	= BaseController::getInstance('«component.name.toFirstUpper»');
		$controller->execute(Factory::getApplication()->input->get('task'));
		$controller->redirect();
	'''
	 
	/**
     * Contains the template for the main controller of a component.
     * For more information see controller.php.
     * 
     * @param ExtendedComponent component Containt the extended instance of a ejsl component
     * @return Charsequence
     */
	def CharSequence phpAdminControllerContent(ExtendedComponent component) '''
		<?php
		«Slug.generateFileDoc(component)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ControllerLegacy", "Factory"))»
		
		/**
		 * General Controller of «class_name» component
		 */
		class «class_name»Controller extends BaseController
		{
		    /**
		     * display task
		     *
		     * @return  void
		     */
		    public function display($cachable = false, $urlparams = array())
		    {
		        require_once JPATH_COMPONENT . '/Helper/«component.name.toLowerCase».php';
		        $view = Factory::getApplication()->input->getCmd('view', '«class_name»s');
		        Factory::getApplication()->input->set('view', $view);
		        parent::display($cachable, $urlparams);
		        return $this;
		    }
		}
	'''

	/**
	 * Returns the content of a simple backendSection model file that extends from AdminModel 
	 * and provides methods to handle (load, edit...) of one data item
	 */
	def CharSequence phpAdminSimpleModelContent(ExtendedComponent component,ExtendedPage pageref) '''
		<?php
		
		«Slug.generateFileDoc(component)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ModelAdmin"))»
		
		class «component.name.toFirstUpper»Model«pageref.name.toFirstUpper» extends AdminModel
		{
		}
	'''
	 
	/**
	 * Returns the code for the layout of the main view of the component
	 * @param Component component content the instance of a component
	 */
	def CharSequence phpAdminTemplateContent(Component component) '''
		<?php
		«Slug.generateFileDoc(component)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("Text"))»
		?>
		<p class="text-center"> <h1><?php echo "Welcome to ". Text::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»') . " ". Text::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»_HOME'); ?> </h1>
		    <h4>«component.manifest.description»</h4>
		</p> 
		<div id="cpanel" class='cpanel'>
		    <?php 
		    foreach ($this->views as $view)
		    {
		    ?>
		        <div class="icon">
		            <h3>
		                <a href='<?php echo $view['url']; ?>'
		                    <span><?php echo $view['title']; ?></span>
		                </a>
		            </h3>
		            <br />
		        </div>
		    <?php
		    }
		    ?>
		</div>  
		<p>This component is generated with the Joomdd tools, for more information <a target="_blank" href="https://github.com/icampus/JooMDD">see here</a></p>	
	  '''
    
	/**
	 * Returns the code for the main view of a component
	 * @param Component component content the instance of a component
	 */
	def CharSequence phpAdminViewContent(ExtendedComponent component) '''
		<?php
		«Slug.generateFileDoc(component)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ViewLegacy", "Text", "Factory", "Html"))»

		 
		/**
		 * «class_name» View
		 */
		class HtmlView extends BaseHtmlView
		{
		
		    /** Method to get display
		     *
		     * @param   Object  $tpl (template)
		     *
		     * @return  void
		     */
		    public function display($tpl = null)
		    {
		        if (!Factory::getUser()->authorise('core.administrator')) {
		            return JError::raiseWarning(404, Text::_('JERROR_ALERTNOAUTHOR'));
		        }
		
		        HTMLHelper::_('behavior.tooltip');
		
		        $document = Factory::getDocument();
		
		        HTMLHelper::_('tabs.start');
		
		        $application = Factory::getApplication("administrator");
		        $this->option = $application->scope;
		
		        $this->addToolBar();
		
		        $this->addViews();
		
		        parent::display($tpl);
		    }
		
		    /**
		     * creates a joomla administrator tool bar
		     *
		     * @return  void
		     */
		    private function addToolBar()
		    {
		        JToolBarHelper::title(Text::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»') . ': ' . Text::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»_HOME'), 'logo');
		        JToolBarHelper::preferences('«Slug.nameExtensionBind("com", component.name).toLowerCase»');
		    }
		
		    /**
		 * creates html elements for the main menu
		 *
		 * @return    void
		 */
		    private function addViews()
		    {
		        $views = array();
		        «FOR ExtendedPageReference pg : component.backEndExtendedPagerefence.filter[t | t.extendedPage.extendedDynamicPageInstance !== null && !t.extendedPage.extendedDynamicPageInstance.isDetailsPage ]»
		        $views['«pg.extendedPage.name.toLowerCase»'] = array();
		        $views['«pg.extendedPage.name.toLowerCase»']['title'] = Text::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»_TITLE_«pg.extendedPage.name.toUpperCase»');
		        $views['«pg.extendedPage.name.toLowerCase»']['url'] = "index.php?option=«Slug.nameExtensionBind("com", component.name).toLowerCase»&view=«pg.extendedPage.name.toLowerCase»";
		    «ENDFOR»
		    $this->views = $views;
		    }
		}
	  '''
    
	/**
	 *Generates the code for the access manifest of a component
	 * @param ExtendedComponent component content the instance of a component
	 */
	def CharSequence xmlAccessContent(ExtendedComponent component) '''
		<?xml version="1.0" encoding="utf-8"?>
		<access component="«name»">
		    <section name="component">
		     <action name="core.admin" title="JACTION_ADMIN" description="JACTION_ADMIN_COMPONENT_DESC" />
		     <action name="core.manage" title="JACTION_MANAGE" description="JACTION_MANAGE_COMPONENT_DESC" />
		     <action name="core.create" title="JACTION_CREATE" description="JACTION_CREATE_COMPONENT_DESC" />
		     <action name="core.delete" title="JACTION_DELETE" description="JACTION_DELETE_COMPONENT_DESC" />
		     <action name="core.edit" title="JACTION_EDIT" description="JACTION_EDIT_COMPONENT_DESC" />
		     <action name="core.edit.state" title="JACTION_EDITSTATE" description="JACTION_EDITSTATE_COMPONENT_DESC" />
		     <action name="core.edit.own" title="JACTION_EDITOWN" description="JACTION_EDITOWN_COMPONENT_DESC" />
		    </section>
		    «xmlAccessContentPage()»
		</access>
	'''
	 
	/**
	 * Generates the code for the pageactions of each view in the component
	 */
	def CharSequence xmlAccessContentPage() '''
		«FOR dyn : extendedComp.allExtendedPage»
			<section name="«dyn.name.toLowerCase»">
			    <action name="core.create" title="JACTION_CREATE" description="JACTION_CREATE_COMPONENT_DESC" />
			    <action name="core.delete" title="JACTION_DELETE" description="JACTION_DELETE_COMPONENT_DESC" />
			    <action name="core.edit" title="JACTION_EDIT" description="JACTION_EDIT_COMPONENT_DESC" />
			    <action name="core.edit.state" title="JACTION_EDITSTATE" description="JACTION_EDITSTATE_COMPONENT_DESC" />
			    <action name="core.edit.own" title="JACTION_EDITOWN" description="JACTION_EDITOWN_COMPONENT_DESC" />
			</section>
		«ENDFOR»
	'''
	
	/**
	 * Generates the code for the configuration parameters of a component
	 * @param ExtendedComponent 		component content the instance of a component
	 * @param List<ExtendedDynamicPage> dynPages  containt all dynamics pages of the component
	 */
	def CharSequence xmlConfigContent(ExtendedComponent component, List<ExtendedDynamicPage> dynPages) '''
		<?xml version="1.0" encoding="utf-8"?>
		<config>
		    <fieldset name="component" label="«name.toUpperCase»_LABEL" description="«name.toUpperCase»_DESC">
		        «IF  component.hasFileToload»
		        <field
		            name="upload_maxsize"
		            type="text"
		            size="50"
		            default="10"
		            label="«name.toUpperCase»_FIELD_MAXIMUM_SIZE_LABEL"
		            description="«name.toUpperCase»_FIELD_MAXIMUM_SIZE_DESC" />
		        <field
		            name="accept_format"
		            type="text"
		            size="50"
		            default="bmp,csv,doc,gif,ico,jpg,jpeg,odg,odp,ods,odt,pdf,png,ppt,swf,txt,xcf,xls,BMP,CSV,DOC,GIF,ICO,JPG,JPEG,ODG,ODP,ODS,ODT,PDF,PNG,PPT,SWF,TXT,XCF,XLS"
		            label="«name.toUpperCase»_FIELD_ACCEPT_FORMAT_LABEL"
		            description="«name.toUpperCase»_FIELD_ACCEPT_FORMAT_DESC" />
		        «FOR detailsPage : dynPages.filter[t | t.isDetailsPage && t.haveFiletoLoad]»
		        <field
		            name="«detailsPage.name.toLowerCase»_file_path"
		            type="text"
		            size="50"
		            default="media/«name.toLowerCase»/«detailsPage.name.toLowerCase»/files"
		            label="«name.toUpperCase»_«detailsPage.name.toUpperCase»_PATH_FILE_FOLDER_LABEL"
		            description="«name.toUpperCase»_«detailsPage.name.toUpperCase»PATH_FILE_FOLDER_DESC" />
		        <field
		            name="«detailsPage.name.toLowerCase»_image_path"
		            type="text"
		            size="50"
		            default="media/«name.toLowerCase»/«detailsPage.name.toLowerCase»/images"
		            label="«name.toUpperCase»_«detailsPage.name.toUpperCase»_PATH_IMAGE_FOLDER_LABEL"
		            description="«name.toUpperCase»_«detailsPage.name.toUpperCase»_PATH_IMAGE_FOLDER_DESC" />
		         «ENDFOR»
		     «ENDIF»
		    </fieldset>
		    «FOR g : component.extendedParameterGroupList»
		    <fieldset name="«g.name.toLowerCase»" label="«g.name.toUpperCase»_LABEL" description="«g.name.toUpperCase»_DESC">
		        «FOR p:g.extendedParameterList»
		        «Slug.writeParameter(p)»
		        «ENDFOR»
		    </fieldset>
		    «ENDFOR»
		    <fieldset
		        name="permissions"
		        label="JCONFIG_PERMISSIONS_LABEL"
		        description="JCONFIG_PERMISSIONS_DESC">
		        <field
		            name="rules"
		            type="rules"
		            label="JCONFIG_PERMISSIONS_LABEL"
		            filter="rules"
		            validate="rules"
		            component="«Slug.nameExtensionBind("com",extendedComp.name)»"
		            section="component" />
		    </fieldset>
		</config>
	'''

   /**
    * Generates the code for the route rule in the frontend
    * 
    * @param ExtendedComponent component  containt the isntance of a component
    */
	def CharSequence phpSiteRouterContent(ExtendedComponent component) '''
		<?php
		«Slug.generateFileDoc(component)»
		
		«Slug.generateRestrictedAccess()»
		
		/**
		 * @param   array  A named array
		 * @return  array
		 */
		public function «component.name.toFirstUpper»BuildRoute(&$query)
		{
		    $segments = array();
		
		    if (isset($query['task'])) {
		        $segments[] = implode('/', explode('.', $query['task']));
		        unset($query['task']);
		    }
		    if (isset($query['view'])) {
		        $segments[] = $query['view'];
		        unset($query['view']);
		    }
		    if (isset($query['id'])) {
		        $segments[] = $query['id'];
		        unset($query['id']);
		    }
		
		    return $segments;
		}
		
		/**
		 * @param  array  A named array
		 *
		 */
		public function «component.name.toFirstUpper»ParseRoute($segments)
		{
		    $vars = array();
		
		    // view is always the first element of the array
		    $vars['view'] = array_shift($segments);
		
		    while (!empty($segments)) {
		        $segment = array_pop($segments);
		        if (is_numeric($segment)) {
		            $vars['id'] = $segment;
		        } else {
		            $vars['task'] = $vars['view'] . '.' . $segment;
		        }
		    }
		
		    return $vars;
		}
	'''
	
	/**
	 * Returns the javascript code to set the value of a reference of a foreign attribute 
	 *  
	 */
	def CharSequence genScriptForForeignKeys()'''
		«Slug.generateFileDoc(extendedComp)»
		/**
		 * this function set the value of reference for a foreign attribute 
		 */
		function setValueForeignKeys(element) {
		    var data = JSON.parse(element.value);
		    var item;
		    for(item in data) {
		        jQuery("#"+item).attr("value",data[item]);
		    }
		}
	'''
	
	/**
	 * Returns the javascript code to set the values of many references of a foreign attribute
	 *  
	 */
	def CharSequence genScriptForMultipleForeignKeys()'''
	    «Slug.generateFileDoc(extendedComp)»

	    jQuery(document).ready(function() {
	        jQuery("select[generated='true']").each(function() {
	            jQuery(this).trigger('onchange');
	        })
	    });

	    /**
	     * this function set the values of references for many foreign attributes 
	     */
	    function setMultipleValueForeignKeys(element) {
	        var data = [];
	        var id = "#" + element.id + " option:selected";
	        jQuery(id).each(function () {
	            data.push(JSON.parse(jQuery(this).prop("value")));
	        });
	        if(data.length == 0) {
	            return;
	        }
	        var allkeys = Object.keys(data[0])
	        var all_item = [];
	        for(var a = 0; a< allkeys.length; a++) {
	            all_item[allkeys[a]] = [];
	        }
	        for(var i =0; i<data.length; i++) {
	            var attr_obj = data[i];
	            var attr_obj_keys = Object.keys(attr_obj);
	            for(var j =0; j< attr_obj_keys.length; j++) {
	                var attr_key_value = attr_obj_keys[j];
	                var attr_value = attr_obj[attr_key_value][0];
	                all_item[attr_key_value].push(attr_value);
	            }
	        }
	        for(var c =0; c < allkeys.length; c++) {
	            var value = all_item[allkeys[c]];
	            jQuery("#"+ allkeys[c]).attr("value",JSON.stringify(value));
	        }
	    }
	'''
}
