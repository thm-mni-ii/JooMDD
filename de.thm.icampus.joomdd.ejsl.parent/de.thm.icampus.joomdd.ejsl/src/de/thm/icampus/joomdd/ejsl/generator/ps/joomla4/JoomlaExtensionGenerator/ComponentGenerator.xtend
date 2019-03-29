package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.EntityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.PageGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.LanguageGenerator
import java.util.Calendar
import java.util.LinkedList
import java.util.List
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.StaticLanguage

/**
 * This class contains the templates to generate the necessary folders and files for a Joomla component.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
public class ComponentGenerator extends AbstractExtensionGenerator {

	String slug
	ExtendedComponent extendedComp
	String class_name
	String updatePath
	String sitePath 
	String adminPath
	String mediaPath 
 
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
		this.sitePath = newArrayList(path, "components", this.extendedComp.extensionName).join("/").toLowerCase
		this.adminPath = newArrayList(path, "administrator", "components", this.extendedComp.extensionName).join("/").toLowerCase
		this.mediaPath = newArrayList(path, "media", extendedComp.extensionName).join("/").toLowerCase
	}
	
	/**
	 * Generates the folder structure and the installation script of a component.
	 * 
	 */
	override generate() {
		var List<ExtendedDynamicPage>  indexPages = new LinkedList<ExtendedDynamicPage>()
		for(ExtendedPageReference ext : extendedComp.backEndExtendedPagerefence) {
            if(ext.extendedPage.extendedDynamicPageInstance !== null) {
                indexPages.add(ext.extendedPage.extendedDynamicPageInstance)  
            } 
  		}

	    val languages = extendedComp.languages.filter[ language | 
	    	language.sys === false
	    ]
	    
	    val sysLanguages = extendedComp.languages.filter[ language | 
	    	language.sys
	    ]
	    
	    var filteredLanguages = languages.filter[ language | 
	    	sysLanguages.exists[ sysLanguage | 
	    		sysLanguage.name.equals(language.name)
	    	] === false
	    ]
	    
	    var synteticSysLanguages = filteredLanguages.map[ language |
	    	var tmpLanguage = EcoreUtil2.copy(language)
	    	tmpLanguage.sys = true
	    	tmpLanguage
	    ]
	    
	    extendedComp.languages.addAll(synteticSysLanguages.toList)

        // Generate the the installation path for a compoenent
		generateFile(path + name.toLowerCase + ".xml", extendedComp.xmlContent(indexPages))
		generateFile(path + "script.php", generateScript(extendedComp, name))

		//Generate media folder
		generateEmptyDirectory(newArrayList(mediaPath, "images").join("/"))
		generateFile(newArrayList(mediaPath, "js", "setforeignkeys.js").join("/"), genScriptForForeignKeys)
		generateFile(newArrayList(mediaPath, "js", "setmultipleforeignkeys.js").join("/"), genScriptForMultipleForeignKeys)
		var ComponentHelperGenerator help = new ComponentHelperGenerator(extendedComp)
		
		generateFile(newArrayList(mediaPath, "js", "bootsnip.js").join("/"), help.genBootsnipJS)
		generateFile(newArrayList(mediaPath, "css", "bootsnip.css").join("/"), help.genBootsnipCSS)
		
		//Generate images folder
		for(detailsPages : indexPages.filter[t| t.detailsPage && t.haveFiletoLoad]) {
			generateEmptyDirectory(newArrayList(mediaPath, detailsPages.name.toLowerCase, "images").join("/"))
			generateEmptyDirectory(newArrayList(mediaPath, detailsPages.name.toLowerCase, "files").join("/"))
		}

		// Generate frontend section 
		if (extendedComp.frontEndExtendedPagerefence !== null && extendedComp.frontEndExtendedPagerefence.empty === false) {
			generateFrontendSection
		}
	    // Generate backend section 
		if (extendedComp.backEndExtendedPagerefence !== null  && extendedComp.backEndExtendedPagerefence.empty === false) {
			generateBackendSection
		}
		
        // Generate language folders and files
        var LanguageGenerator langgen = new LanguageGenerator(fsa)
        langgen.genComponentLanguage(extendedComp,path)
        
		return ""
	}

	
	/**
	 * Generates the manifest file for a Joomla component. 
	 * For more information see the site: https://docs.joomla4.org/J3.x:Developing_an_MVC_Component/
	 * Developing_a_Basic_Component#helloworld.xml
	 * 
	 * @param ExtendedComponent         component	Containt a component
	 * @param List<ExtendedDynamicPage> dymPages	Containt all ExtendedDynamic of a Component
	 * 
	 * @return  Charsequence code for the manifest file
	 */
	def CharSequence xmlContent(ExtendedComponent component, List<ExtendedDynamicPage> dymPages) {
	    
	    var sectionManifest = newArrayList
	    
	    // Check for a frontend section
	    if (component.frontEndExtendedPagerefence.empty === false) {
	        var frontendManifest = frontendManifest(component)
	        sectionManifest.add(frontendManifest)
	    }
	    
        // Check for a backend section
        if (component.backEndExtendedPagerefence.empty === false) {
            sectionManifest.add(backendManifest(component, dymPages))
        }
        else
        {
            // Joomla requires at least an empty administration tag
            sectionManifest.add('''
                <administration>
                </administration>
            ''')
        }
        
	    '''
        <?xml version="1.0" encoding="utf-8"?>
        <extension type="component" version="4.0" method="upgrade">
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
            <namespace>Joomla\Component\«noPrefixName.toFirstUpper»</namespace>
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
            <media destination="«extendedComp.extensionName»" folder="media/«extendedComp.extensionName»">
                <folder>images</folder>
                <folder>js</folder>
                <folder>css</folder>
                «FOR page : dymPages.filter[t | t !== null && t.detailsPage && t.haveFiletoLoad]»
                <folder>«page.name.toLowerCase»</folder>
                «ENDFOR»
            </media>
            «FOR section : sectionManifest»

            «section»
            «ENDFOR»
        </extension>
        '''
	}
    
    def backendManifest(ExtendedComponent component, List<ExtendedDynamicPage> dymPages) '''
    <administration>
        <!-- Administration Menu Section -->
        <menu>«Slug.addLanguage(component.languages, newArrayList("com", component.name), component.name)»</menu>
        <submenu>
            «FOR page : dymPages.filter[t | !t.detailsPage]»
            <menu link="option=«Slug.addLanguage(component.languages, newArrayList("com", component.name), component.name)»&amp;view=«page.name.toLowerCase»" 
                alias="«Slug.addLanguage(component.languages, newArrayList("com", component.name, "ALIAS", page.name), page.name)»"
                view="«page.name.toLowerCase»">«Slug.addLanguage(component.languages, newArrayList("com", component.name, "TITLE", page.name), page.name)»</menu>
            «ENDFOR»
        </submenu>
        <files folder="administrator/components/«extendedComp.extensionName»">
            <filename>access.xml</filename>
            <filename>config.xml</filename>
            <folder>Controller</folder>
            <folder>Extension</folder>
            <folder>Field</folder>
            <folder>forms</folder>
            <folder>Helper</folder>
            <folder>helpers</folder>
            <folder>Model</folder>
            <folder>Service</folder>
            <folder>services</folder>
            <folder>Table</folder>
            <folder>sql</folder>
            <folder>tmpl</folder>
            <folder>View</folder>
            <folder>language</folder>
        </files>

        «componentManifestLanguages(component, true)»
    </administration>
    '''
    
    def frontendManifest(ExtendedComponent component) '''
    <!-- Site Main File Copy Section -->
    <files folder="components/«extendedComp.extensionName»">
        <filename>router.php</filename>
        <folder>Controller</folder>
        <folder>Dispatcher</folder>
        <folder>forms</folder>
        <folder>Helper</folder>
        <folder>helpers</folder>
        <folder>Model</folder>
        <folder>Service</folder>
        <folder>tmpl</folder>
        <folder>View</folder>
        <folder>language</folder>
    </files>
    «componentManifestLanguages(component, false)»
    '''

	private def componentManifestLanguages(ExtendedComponent component, boolean admin) '''
	<languages>
	    «FOR lang : component.languages»
	    «IF !lang.sys»
	    <language tag="«lang.name»">«IF admin»administrator/«ENDIF»components/«extendedComp.extensionName»/language/«lang.name»/«lang.name».«extendedComp.extensionName».ini</language>
	    «ELSE»
	    <language tag="«lang.name»">«IF admin»administrator/«ENDIF»components/«extendedComp.extensionName»/language/«lang.name»/«lang.name».«extendedComp.extensionName».sys.ini</language>
        «ENDIF»
        «ENDFOR»
	</languages>
	'''

    /**
     * Generates all the folders and files for the frontend of a component.
     * Uses the entity an page generator.
     * 
     */
	private def void generateFrontendSection() {
		// Generate frontend section
        generateFile( sitePath + "/Controller/DisplayController.php", extendedComp.phpSiteControllerContent)
        generateFile( sitePath + "/Dispatcher/Dispatcher.php", extendedComp.phpSiteDispatcherContent)
		generateFile( sitePath + "/router.php", extendedComp.phpSiteRouterContent)
		        
        var EntityGenerator entitygen = new EntityGenerator(extendedComp,sitePath + "/", fsa, false)
		entitygen.dogenerate()

		var EList<ExtendedPage> tempPageList = new BasicEList()
		
		for (pageref : extendedComp.frontEndExtendedPagerefence) {
           tempPageList.add(pageref.extendedPage)
		}
		
		var PageGenerator pgGen 
        pgGen = new PageGenerator(extendedComp, tempPageList, fsa, sitePath, "site", false)
		pgGen.dogenerate
		generateUpdatePages(tempPageList, "site")
	}
	
	def CharSequence phpSiteDispatcherContent(ExtendedComponent component) '''
        <?php
        «Slug.generateFileDocSite(component)»
        
        «Slug.generateNamespace(noPrefixName, "Site", "Dispatcher")»
        
        «Slug.generateRestrictedAccess»
        
        «Slug.generateUses(newArrayList("ComponentDispatcher", "Text"))»
        
        /**
         * ComponentDispatcher class for com_«noPrefixName»
         *
         * @since  4.0.0
         */
        class Dispatcher extends ComponentDispatcher
        {
            /**
             * Dispatch a controller task. Redirecting the user if appropriate.
             *
             * @return  void
             *
             * @since   4.0.0
             */
            public function dispatch()
            {
                parent::dispatch();
            }
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
  		
		generateFile( adminPath + "/Controller/DisplayController.php", extendedComp.phpAdminControllerContent)
		generateFile( adminPath + "/access.xml", extendedComp.xmlAccessContent)
		generateFile( adminPath + "/config.xml", extendedComp.xmlConfigContent(indexPages))
		
		var tempSlug = slug
        generateFile(adminPath + "/View/" + tempSlug.toFirstUpper + "/HtmlView.php", 
            extendedComp.phpAdminViewContent)
        generateFile(adminPath + "/tmpl/" + tempSlug + "/default.php", 
            extendedComp.phpAdminTemplateContent
        )
		
		var ComponentHelperGenerator help = new ComponentHelperGenerator(extendedComp)
		generateFile( adminPath + "/Helper/" + extendedComp.name.toLowerCase.toFirstUpper + "Helper.php", 
            help.generate
        )

        // Generate services
        generateFile( adminPath + "/services/provider.php", 
            serviceContent
        )
        
        // Generate extension
        generateFile( adminPath + "/Extension/" + noPrefixName.toFirstUpper + "Component.php", 
            extensionContent
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
	
	def String extensionContent() '''
    	<?php
    	/**
    	 * @package     Joomla.Administrator
    	 * @subpackage  «this.name»
    	 *
    	 * @copyright   Copyright (C) 2005 - 2019 Open Source Matters, Inc. All rights reserved.
    	 * @license     GNU General Public License version 2 or later; see LICENSE.txt
    	 */
    	
    	namespace Joomla\Component\«noPrefixName.toFirstUpper»\Administrator\Extension;
    	
    	defined('JPATH_PLATFORM') or die;
    	
    	use Joomla\CMS\Association\AssociationServiceInterface;
    	use Joomla\CMS\Association\AssociationServiceTrait;
    	use Joomla\CMS\Extension\BootableExtensionInterface;
    	use Joomla\CMS\Extension\MVCComponent;
    	use Joomla\CMS\HTML\HTMLRegistryAwareTrait;
    	use Joomla\Component\«noPrefixName.toFirstUpper»\Administrator\Service\HTML\Menus;
    	use Psr\Container\ContainerInterface;
    	
    	/**
    	 * Component class for com_menus
    	 *
    	 * @since  4.0.0
    	 */
    	class «noPrefixName.toFirstUpper»Component extends MVCComponent implements
    	    BootableExtensionInterface, AssociationServiceInterface
    	{
    	    use AssociationServiceTrait;
    	    use HTMLRegistryAwareTrait;
    	
    	    /**
    	     * Booting the extension. This is the function to set up the environment of the extension like
    	     * registering new class loaders, etc.
    	     *
    	     * If required, some initial set up can be done from services of the container, eg.
    	     * registering HTML services.
    	     *
    	     * @param   ContainerInterface  $container  The container
    	     *
    	     * @return  void
    	     *
    	     * @since   4.0.0
    	     */
    	    public function boot(ContainerInterface $container)
    	    {

    	    }
    	}
    	'''
	
	def String serviceContent() '''
    	<?php
    	/**
    	 * @package     Joomla.Administrator
    	 * @subpackage  com_contact
    	 *
    	 * @copyright   Copyright (C) 2005 - 2019 Open Source Matters, Inc. All rights reserved.
    	 * @license     GNU General Public License version 2 or later; see LICENSE.txt
    	 */
    	
    	defined('_JEXEC') or die;
    	
    	use Joomla\CMS\Association\AssociationExtensionInterface;
    	use Joomla\CMS\Categories\CategoryFactoryInterface;
    	use Joomla\CMS\Dispatcher\ComponentDispatcherFactoryInterface;
    	use Joomla\CMS\Extension\ComponentInterface;
    	use Joomla\CMS\Extension\Service\Provider\ComponentDispatcherFactory;
    	use Joomla\CMS\Extension\Service\Provider\MVCFactory;
    	use Joomla\CMS\HTML\Registry;
    	use Joomla\CMS\MVC\Factory\MVCFactoryInterface;
    	use Joomla\Component\«noPrefixName.toFirstUpper»\Administrator\Helper\AssociationsHelper;
    	use Joomla\Component\«noPrefixName.toFirstUpper»\Administrator\Extension\«noPrefixName.toFirstUpper»Component;
    	use Joomla\DI\Container;
    	use Joomla\DI\ServiceProviderInterface;
    	/**
    	 * The contact service provider.
    	 *
    	 * @since  4.0.0
    	 */
    	return new class implements ServiceProviderInterface
    	{
    	    /**
    	     * Registers the service provider with a DI container.
    	     *
    	     * @param   Container  $container  The DI container.
    	     *
    	     * @return  void
    	     *
    	     * @since   4.0.0
    	     */
    	    public function register(Container $container)
    	    {
    	        $container->registerServiceProvider(new MVCFactory('\\Joomla\\Component\\«noPrefixName.toFirstUpper»'));
    	        $container->registerServiceProvider(new ComponentDispatcherFactory('\\Joomla\\Component\\«noPrefixName.toFirstUpper»'));
    	
    	        $container->set(
    	            ComponentInterface::class,
    	            function (Container $container)
    	            {
    	                $component = new «noPrefixName.toFirstUpper»Component($container->get(ComponentDispatcherFactoryInterface::class));
    	
    	                $component->setRegistry($container->get(Registry::class));
    	                $component->setMVCFactory($container->get(MVCFactoryInterface::class));
    	
    	                return $component;
    	            }
    	        );
    	    }
    	};
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
		«Slug.generateFileDocSite(component)»
		
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
		«Slug.generateFileDocSite(component)»
		
		«Slug.generateNamespace(component.name, "Site", "Controller")»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("BaseController", "Factory"))»
		
		/**
		 * General Controller of «component.name» component
		 */
		class DisplayController extends BaseController
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
		        $vName = $input->getCmd('view', '«component.name»');
		        $input->set('view', $vName);

		        $safeurlparams = array();

		        // call parent behavior
		        parent::display($cachable, $safeurlparams);

		        return $this;
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
		«Slug.generateFileDocAdmin(component)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ControllerLegacy", "Text", "Factory"))»
		
		// Access check.
		if (!Factory::getUser()->authorise('core.manage', '«Slug::nameExtensionBind("com",component.name )»')) {
		    throw new Exception(Text::_('JERROR_ALERTNOAUTHOR'));
		}
		
		// Get an instance of the controller prefixed by «Slug::nameExtensionBind("com",component.name )»
		$controller = BaseController::getInstance('«component.name.toFirstUpper»');
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
		«Slug.generateFileDocAdmin(component)»
		
		«Slug.generateNamespace(component.name, "Administrator", "Controller")»

		«Slug.generateRestrictedAccess()»

		«Slug.generateUses(newArrayList("BaseController", "Factory"))»

		/**
		 * General Controller of «class_name» component
		 */
		class DisplayController extends BaseController
		{
		    /**
		     * The default view.
		     *
		     * @var    string
		     * @since  1.6
		     */
		    protected $default_view = '«component.name»';
		    
		    /**
		     * The extension for which the categories apply.
		     *
		     * @var    string
		     * @since  1.6
		     */
		    protected $extension;
		
		    /**
		     * Constructor.
		     *
		     * @param   array                $config   An optional associative array of configuration settings.
		     * @param   MVCFactoryInterface  $factory  The factory.
		     * @param   CMSApplication       $app      The JApplication for the dispatcher
		     * @param   \JInput              $input    Input
		     *
		     * @since   3.0
		     */
		    public function __construct($config = array(), MVCFactoryInterface $factory = null, $app = null, $input = null)
		    {
		        parent::__construct($config, $factory, $app, $input);
		
		        // Guess the Text message prefix. Defaults to the option.
		        if (empty($this->extension))
		        {
		            $this->extension = $this->input->get('extension', '«this.name»');
		        }
		    }
		
		    /**
		     * Method to display a view.
		     *
		     * @param   boolean  $cachable   If true, the view output will be cached
		     * @param   array    $urlparams  An array of safe URL parameters and their variable types, for valid values see {@link \JFilterInput::clean()}.
		     *
		     * @return  \Joomla\Component\Categories\Administrator\Controller\DisplayController  This object to support chaining.
		     *
		     * @since   1.5
		     */
		    public function display($cachable = false, $urlparams = array())
		    {
		        // Get the document object.
		        $document = $this->app->getDocument();
		
		        // Set the default view name and format from the Request.
		        $vName   = $this->input->get('view', '«noPrefixName.toFirstLower»');
		        $vFormat = $document->getType();
		        $lName   = $this->input->get('layout', 'default', 'string');
		        $id      = $this->input->getInt('id');
		
		        // Get and render the view.
		        if ($view = $this->getView($vName, $vFormat))
		        {
		            // Get the model for the view.
		            $model = $this->getModel($vName, 'Administrator', array('name' => $vName . '.' . substr($this->extension, 4)));
		
		            // Push the model into the view (as default).
		            $view->setModel($model, true);
		            $view->setLayout($lName);
		
		            // Push document object into the view.
		            $view->document = $document;
		
		            // Load the submenu.
		            //CategoriesHelper::addSubmenu($model->getState('filter.extension'));
		            $view->display();
		        }
		
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
		«Slug.generateNamespace(component.name, "Administrator", "Model")»
		
		«Slug.generateFileDocAdmin(component)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ModelAdmin"))»
		
		class «pageref.name.toFirstUpper»Model extends AdminModel
        {
        }
	'''
	 
	/**
	 * Returns the code for the layout of the main view of the component
	 * @param Component component content the instance of a component
	 */
	def CharSequence phpAdminTemplateContent(ExtendedComponent component) '''
		<?php
		«Slug.generateFileDocAdmin(component)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("Text"))»
		?>
		<p class="text-center"> <h1><?php echo "Welcome to ". Text::_('«Slug.addLanguage(component.languages, newArrayList("com", component.name), component.name)»') . " ". Text::_('«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.HOME)»'); ?> </h1>
		    <h4>«component.manifest.description»</h4>
		</p> 
		<div id="cpanel" class='cpanel'>
		    <?php
		    foreach ($this->views as $view) {
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
		«Slug.generateFileDocAdmin(component)»

		«Slug.generateNamespace(component.name, "Administrator", "View\\" + class_name.toFirstUpper)»

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
		        \JToolBarHelper::itle(Text::_('«Slug.addLanguage(component.languages, newArrayList("com", component.name), component.name)»') . ': ' . Text::_('«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.HOME)»'), 'logo');
		        \JToolBarHelper::eferences('«Slug.nameExtensionBind("com", component.name).toLowerCase»');
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
		        $views['«pg.extendedPage.name.toLowerCase»']['title'] = Text::_('«Slug.addLanguage(component.languages, newArrayList("com", component.name, "TITLE", pg.extendedPage.name), pg.extendedPage.name)»');
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
		            label="JGLOBAL_MAXIMUM_UPLOAD_SIZE_LIMIT"
		            description="JGLOBAL_MAXIMUM_UPLOAD_SIZE_LIMIT" />
		        <field
		            name="accept_format"
		            type="text"
		            size="50"
		            default="bmp,csv,doc,gif,ico,jpg,jpeg,odg,odp,ods,odt,pdf,png,ppt,swf,txt,xcf,xls,BMP,CSV,DOC,GIF,ICO,JPG,JPEG,ODG,ODP,ODS,ODT,PDF,PNG,PPT,SWF,TXT,XCF,XLS"
		            label="COM_MEDIA_FIELD_LEGAL_EXTENSIONS_LABEL"
		            description="COM_MEDIA_FIELD_LEGAL_EXTENSIONS_DESC" />
		        «FOR detailsPage : dynPages.filter[t | t.isDetailsPage && t.haveFiletoLoad]»
		        <field
		            name="«detailsPage.name.toLowerCase»_file_path"
		            type="text"
		            size="50"
		            default="media/«name.toLowerCase»/«detailsPage.name.toLowerCase»/files"
		            label="COM_MEDIA_FIELD_PATH_FILE_FOLDER_LABEL"
		            description="COM_MEDIA_FIELD_PATH_FILE_FOLDER_DESC" />
		        <field
		            name="«detailsPage.name.toLowerCase»_image_path"
		            type="text"
		            size="50"
		            default="media/«name.toLowerCase»/«detailsPage.name.toLowerCase»/images"
		            label="COM_MEDIA_FIELD_PATH_IMAGE_FOLDER_LABEL"
		            description="COM_MEDIA_FIELD_PATH_IMAGE_FOLDER_DESC" />
		         «ENDFOR»
		     «ENDIF»
		    </fieldset>
		    «FOR g : component.extendedParameterGroupList»
		    <fieldset name="«g.name.toLowerCase»" label="«Slug.addLanguage(component.languages, newArrayList("com", component.name, g.name, "LABEL"), g.name)»" description="«Slug.addLanguage(component.languages, newArrayList("com", component.name, g.name, "DESC"), StaticLanguage.getCommonDescriptionFor(g.name))»">
		        «FOR p:g.extendedParameterList»
		        «Slug.writeParameter(p,component)»
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
		            component="«Slug.nameExtensionBind("com", extendedComp.name)»"
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
		«Slug.generateFileDocSite(component)»
		
		«Slug.generateRestrictedAccess()»
		
		/**
		 * @param   array  A named array
		 * @return  array
		 */
		function «component.name.toFirstUpper»BuildRoute(&$query)
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
		 * @return array
		 */
		function «component.name.toFirstUpper»ParseRoute($segments)
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
		«Slug.generateFileDocAdmin(extendedComp)»
		
		/**
		 * This function set the value of reference for a foreign attribute
		 */
		function setValueForeignKeys(element)
		{
		    var data = JSON.parse(element.value);
		    var item;
		    for (item in data) {
		        jQuery("#"+item).attr("value",data[item]);
		    }
		}
	'''
	
	/**
	 * Returns the javascript code to set the values of many references of a foreign attribute
	 *  
	 */
	def CharSequence genScriptForMultipleForeignKeys()'''
	    «Slug.generateFileDocAdmin(extendedComp)»

	    jQuery(document).ready(function () {
	        jQuery("select[generated='true']").each(function () {
	            jQuery(this).trigger('onchange');
	        })
	    });

	    /**
	     * This function set the values of references for many foreign attributes
	     */
	    function setMultipleValueForeignKeys(element)
	    {
	        var data = [];
	        var id = "#" + element.id + " option:selected";
	        jQuery(id).each(function () {
	            data.push(JSON.parse(jQuery(this).prop("value")));
	        });
	        if (data.length == 0) {
	            return;
	        }
	        var allkeys = Object.keys(data[0])
	        var all_item = [];
	        for (var a = 0; a< allkeys.length; a++) {
	            all_item[allkeys[a]] = [];
	        }
	        for (var i =0; i<data.length; i++) {
	            var attr_obj = data[i];
	            var attr_obj_keys = Object.keys(attr_obj);
	            for (var j =0; j< attr_obj_keys.length; j++) {
	                var attr_key_value = attr_obj_keys[j];
	                var attr_value = attr_obj[attr_key_value][0];
	                all_item[attr_key_value].push(attr_value);
	            }
	        }
	        for (var c =0; c < allkeys.length; c++) {
	            var value = all_item[allkeys[c]];
	            jQuery("#"+ allkeys[c]).attr("value",JSON.stringify(value));
	        }
	    }
	'''
}
