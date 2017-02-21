/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.generator.ps.EntityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.LanguageGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.generator.ps.PageGenerator
import java.util.Calendar
import java.util.List
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2

/**
 * This class contains the Template to generate the Folders and Files for a component.
 * 
 * @author Dieudonne Timma
 */
public class ComponentGenerator extends AbstractExtensionGenerator {

	private String slug
	private ExtendedComponent extendeComp
	private String class_name
	private String updatePath
    
    /**
     * this Constructor initial  all the parameters to generate a component
     * Format the name of the extension
     * 
     * @param ExtendedComponent   component     contains the instance of a component
     * @param IFileSystemAccess2  fsa 	   	    create the files and folder
     * @param String			  path		    contains the Path for the extension
     * @param String              updatePath	contains the path for the update folder
     * 		
     */
	new(ExtendedComponent component, IFileSystemAccess2 fsa, String path, String updatePath) {
		this.fsa = fsa;
		this.slug = component.name
		this.noPrefixName = this.slug
		this.name = "com_" + this.slug

		this.extendeComp = component
		this.class_name = this.noPrefixName.toFirstUpper
		this.extendeComp.name = Slug.slugify(extendeComp.name)
		this.path = path
		this.updatePath = updatePath
	}
	
	/**
	 * this methode generate the folder structure of a component and the installation script
	 * 
	 */
	override generate() {
		println("component path " +path);
		generateJoomlaDirectory(path+"")

		
		var indexPages = extendeComp.backEndExtendedPagerefence.map [t|
			if(t.extendedPage.extendedDynamicPageInstance != null) t.extendedPage.extendedDynamicPageInstance
		];

        // Generate the the installation path for a compoenent
		generateFile(path+ name + ".xml", extendeComp.xmlContent(indexPages))
		generateFile(path+ "script.php", generateScript(extendeComp.instance, name))

		// Generate language folders and files
		var LanguageGenerator langgen = new LanguageGenerator(fsa)
		langgen.genComponentLanguage(extendeComp,path)

			// Generate sql folders
		generateJoomlaDirectory(path+"admin/sql")
		generateJoomlaDirectory(path+"admin/sql/updates")
		generateJoomlaDirectory(path+"admin/sql/updates/mysql")
		
		//Generate media folder
		generateJoomlaDirectory(path+"media")
		generateJoomlaDirectory(path+"media/css")
		generateJoomlaDirectory(path+"media/images")
		generateJoomlaDirectory(path+"media/js")
		generateFile( path+"media/js/setForeignKeys.js", genScriptForForeignKeys)
		generateFile( path+"media/js/setMultipleForeignKeys.js", genScriptForMultipleForeignKeys)
		var ComponentHelperGenerator help = new ComponentHelperGenerator(extendeComp)
		
		generateFile( path+"media/js/bootsnip.js", help.genBootsnipJS)
		generateFile( path+"media/css/bootsnip.css",help.genBootsnipCSS)
		//Generate images folder
		for(detailsPages : indexPages.filter[t| t.detailsPage]){
			generateJoomlaDirectory(path+"media/" + detailsPages.name.toLowerCase)
			generateJoomlaDirectory(path+"media/" + detailsPages.name.toLowerCase+ "/images")
			generateJoomlaDirectory(path+"media/" + detailsPages.name.toLowerCase + "/files")
		}
		
		

		// Generate frontend section 
		if (extendeComp.frontEndExtendedPagerefence != null) {
			generateFrontendSection
		}
	   // Generate backend section 
		if (extendeComp.backEndExtendedPagerefence != null) {
			generateBackendSection
		}
	  
	   
	
		return ""
	}

	
	/**
	 * Generate the manifest file for  a Joomla component for more informations
	 * see the https://docs.joomla.org/J3.x:Developing_an_MVC_Component/Developing_a_Basic_Component#helloworld.xml
	 * 
	 * @param ExtendedComponent         component	Containt a component
	 * @param List<ExtendedDynamicPage> dymPages	Containt all ExtendedDynamic of a Component
	 * 
	 * @return  Charsequence code for the manifest file
	 */
	def CharSequence xmlContent(ExtendedComponent component, List<ExtendedDynamicPage> dymPages) '''
		<?xml version="1.0" encoding="utf-8"?>
		<extension type="component" version="3.3" method="upgrade">
		    <name>«component.name»</name>
		    «Slug.generateAuthors(component.manifest.authors)»            
		    «IF (component.manifest.creationdate != null)»
		    	<creationDate>«component.manifest.creationdate»</creationDate>
		    «ELSE»
		    	<creationDate>«Calendar::instance.get(Calendar::YEAR)»</creationDate>
		    «ENDIF»
		    «IF (component.manifest.copyright != null)»
		    	<copyright>«component.manifest.copyright»</copyright>
		    «ENDIF»
		    
		    «IF (component.manifest.license != null)»
		    	<license>«component.manifest.license»</license>
		    «ENDIF»
		    «IF (component.manifest.version != null)»
		    	<version>«component.manifest.version»</version>
		    	«ELSE»
		    	<version>1.0.1</version>
		    «ENDIF»
		    «IF (component.manifest.description != null)»
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
		     <media destination="«name»" folder="media">
		     	    <folder>images</folder>
					<folder>js</folder>
					<folder>css</folder>
					«FOR page : dymPages.filter[t | t.detailsPage]»
					<folder>«page.name.toLowerCase»</folder>
					«ENDFOR»
					<filename>index.html</filename>
		      </media>
		      
		     
		    
		    <!-- Site Main File Copy Section -->
		    <files folder="site">
		        <filename>index.html</filename>
		        <filename>«noPrefixName».php</filename>
		        <filename>controller.php</filename>
		        <!-- Additional Files -->
		        <folder>views</folder>
		        <folder>models</folder>
		        <folder>controllers</folder>		       
		    </files>
		    
		    <languages>
		    	«FOR lang : component.languages»
		    		<language tag="«lang.name»">language/site/«lang.name»/«lang.name».«this.name».ini</language>
		    		<language tag="«lang.name»">language/site/«lang.name»/«lang.name».«this.name».sys.ini</language>
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
				<files folder="admin">
				    <!-- Admin Main File Copy Section -->
				    <filename>index.html</filename>
				    <filename>«noPrefixName».php</filename>
				    <filename>controller.php</filename>
					<filename>access.xml</filename>
					<filename>config.xml</filename>
					         <!-- SQL Files Section -->
					         <folder>sql</folder>
					         <!-- Table Files Section -->
					         <folder>tables</folder>
					         <!-- Model Files Section -->
					         <folder>models</folder>
					         <!-- View Files Section -->
					         <folder>views</folder>
					         <folder>controllers</folder>
					         <folder>helpers</folder>
					         <folder>assets</folder>
					     </files>
				
				<languages >
				  	«FOR lang : component.languages»
				  		<language tag="«lang.name»">language/admin/«lang.name»/«lang.name».«this.name».ini</language>
				  		<language tag="«lang.name»">language/admin/«lang.name»/«lang.name».«this.name».sys.ini</language>
				  	«ENDFOR»
				</languages>
				  </administration>
		</extension>
	'''
    /**
     * Generate all the Folders and Files for the frontend of the Component.
     * For that, he use the entity an page generator.
     * 
     */
	private def void generateFrontendSection() {

		// Generate frontend section
		generateJoomlaDirectory(path+"/site")
		generateFile( path +"site/" + noPrefixName + ".php", extendeComp.phpSiteContent)
		generateFile( path +"site/controller.php", extendeComp.phpSiteControllerContent)
		generateFile( path +"site/router.php", extendeComp.phpSiteRouterContent)
		generateJoomlaDirectory(path+"site/views")
		generateJoomlaDirectory(path+"site/models")
		generateJoomlaDirectory(path+"site/models/fields")
		generateJoomlaDirectory(path+"site/models/forms")

		generateJoomlaDirectory(path+"site/views")
		generateJoomlaDirectory(path+"site/assets")
		

		generateJoomlaDirectory(path+"site/controllers")
        var EntityGenerator entitygen = new EntityGenerator(extendeComp,path + "site/",fsa,false)
		
		entitygen.dogenerate()
        
		
		var EList<ExtendedPage> tempPageList = new BasicEList()
		
		for (pageref : extendeComp.frontEndExtendedPagerefence) {
           tempPageList.add(pageref.extendedPage)
		}
		var PageGenerator pgGen = new PageGenerator(extendeComp, tempPageList,fsa,path,"site",false)
		pgGen.dogenerate
		generateUpdatePages(tempPageList,"site")
		
	}
	 /**
     * Generate all the Folders and Files for the frontend of the Component.
     * For that, he use the entity an page generator.
     * 
     */
	private def void generateBackendSection() {
		var indexPages = extendeComp.backEndExtendedPagerefence.map [t|
			if(t.extendedPage.extendedDynamicPageInstance != null) t.extendedPage.extendedDynamicPageInstance
		];
		generateJoomlaDirectory(path+"admin")
		generateFile( path +"admin/" + noPrefixName + ".php", extendeComp.phpAdminContent)
		generateFile( path +"admin/controller.php", extendeComp.phpAdminControllerContent)

		generateFile( path +"admin/access.xml", extendeComp.xmlAccessContent)
		generateFile( path +"admin/config.xml", extendeComp.xmlConfigContent(indexPages))
		generateJoomlaDirectory(path+"admin/assets")
		
		

		generateJoomlaDirectory(path+"admin/views")
		println(slug)
		var tempSlug = slug + "s"
		generateJoomlaDirectory(path+"admin/views/" + tempSlug)
		generateFile( path +"admin/views/" + tempSlug + "/view.html.php", extendeComp.phpAdminViewContent)
		generateJoomlaDirectory(path+"admin/views/" + tempSlug + "/tmpl")
		generateFile( path +"admin/views/" + tempSlug + "/tmpl/default.php", extendeComp.phpAdminTemplateContent)

		generateJoomlaDirectory(path+"admin/models")
		generateJoomlaDirectory(path+"admin/models/fields")
		generateJoomlaDirectory(path+"admin/tables")

		generateJoomlaDirectory(path+"admin/views")

		generateJoomlaDirectory(path+"admin/controllers")
		generateJoomlaDirectory(path+"admin/helpers/")
		var ComponentHelperGenerator help = new ComponentHelperGenerator(extendeComp)
		generateFile( path +"admin/helpers/" + extendeComp.name.toLowerCase + ".php", help.generate)

		var EntityGenerator entitygen  = new EntityGenerator(extendeComp,path + "admin/",fsa,true)
		
		entitygen.dogenerate()
		
		// commented out old model generation code
		
		var EList<ExtendedPage> tempPageList = new BasicEList()
		
		for (pageref : extendeComp.backEndExtendedPagerefence) {
           tempPageList.add(pageref.extendedPage)
		}
		var PageGenerator pgGen = new PageGenerator(extendeComp, tempPageList,fsa,path,"admin",false)
		pgGen.dogenerate
		generateUpdatePages(tempPageList,"admin")
		
	}
	/**
	 * Generate the update part of the component, this content only the folder, that the component need for a page.
	 * But the Structur of a page ist different, if page is for the frontent or backend
	 * 
	 * @param  EList<ExtendedPage> pageRefList  List of the pages
	 * @param  String 			   section       section of a component(Backend or Frontend)
	 */
    public def void generateUpdatePages(EList<ExtendedPage> pageRefList, String section){
    	
    	var PageGenerator pgGenUpdate= new PageGenerator(extendeComp, pageRefList,fsa, updatePath,section,true)
		pgGenUpdate.dogenerate
	
    }
    /**
     * Containt the Template for entry point for the frontend of a component.
     * For more information see component_name.php.
     * 
     * @param Component component Containt the instance of a ejsl component
     * @return Charsequence
     */
	def CharSequence phpSiteContent(Component component) '''
		<?php
		     «Slug.generateFileDoc(component,true)»
		    
		    
		    // import joomla controller library
		    jimport('joomla.application.component.controller');
		    
		    // Get an instance of the controller prefixed by «class_name»
		    $controller = JControllerLegacy::getInstance('«class_name»');
		    
		    // Perform the Request task
		    $input = JFactory::getApplication()->input;
		    $controller->execute($input->getCmd('task'));
		    
		    // Redirect if set by the controller
		    $controller->redirect();
	'''
	
	/**
	 * Generate the code for the main controller of a joomla component, see controller.php. 
	 * 
	 * @param ExtendedComponent component  containt a intansce of a extended ejsl component
	 * 
	 * @return Charsequence
	 * 
	 */
	def CharSequence phpSiteControllerContent(ExtendedComponent component) '''
		<?php
		     «Slug.generateFileDoc(component,true)»
		    
		    // import Joomla controller library
		    jimport('joomla.application.component.controller');
		    
		    /**
		     * General Controller of «component.name» component
		     */
		    class «class_name»Controller extends JControllerLegacy
		    {
		            /**
		             * display task
		             *
		             * @return void
		             */
		            function display($cachable = false) 
		            {
		                    // set default view if not set
		                    $input = JFactory::getApplication()->input;
		                    $input->set('view', $input->getCmd('view', '«component.name»'));
		    
		                    // call parent behavior
		                    parent::display($cachable);
		            }
		    }
	'''
	 /**
     * Containt the Template for entry point for the banckend of a component.
     * For more information see component_name.php.
     * 
     * @param Component component Containt the instance of a ejsl component
     * @return Charsequence
     */
	def CharSequence phpAdminContent(ExtendedComponent component) '''
		<?php
		 «Slug.generateFileDoc(component,true)»
		
		// Access check.
		if (!JFactory::getUser()->authorise('core.manage', '«Slug::nameExtensionBind("com",component.name )»')) 
		{
			throw new Exception(JText::_('JERROR_ALERTNOAUTHOR'));
		}
		
		// Include dependancies
		jimport('joomla.application.component.controller');
		
		// Get an instance of the controller prefixed by «Slug::nameExtensionBind("com",component.name )»
		$controller	= JControllerLegacy::getInstance('«component.name.toFirstUpper»');
		$controller->execute(JFactory::getApplication()->input->get('task'));
		$controller->redirect();
	 '''
	 /**
     * Containt the Template for the main controller of a component.
     * For more information see controller.php.
     * 
     * @param ExtendedComponent component Containt the extended instance of a ejsl component
     * @return Charsequence
     */
	def CharSequence phpAdminControllerContent(ExtendedComponent component) '''
		<?php
		     «Slug.generateFileDoc(component,true)»
		    
		    // import Joomla controller library
		    jimport('joomla.application.component.controller');
		    
		    /**
		     * General Controller of «class_name» component
		     */
		    class «class_name»Controller extends JControllerLegacy
		    {
		            /**
		             * display task
		             *
		             * @return void
		             */
		             public function display($cachable = false, $urlparams = false) 
		             {
		             	
		             	  require_once JPATH_COMPONENT . '/helpers/«component.name.toLowerCase».php';
		             	  $view = JFactory::getApplication()->input->getCmd('view', '«class_name»s');
		             	  JFactory::getApplication()->input->set('view', $view);
		             	  parent::display($cachable, $urlparams);
		             	  return $this;
		             	}
		    }
	'''

	/**
	 * returns the content of a simple backendSection model file
	 * that extends from JModelAdmin and provides methods 
	 * to handle (load,edit...) one data item
	 */
	def CharSequence phpAdminSimpleModelContent(ExtendedComponent component,ExtendedPage pageref) '''
		<?php
		
		 «Slug.generateFileDoc(component,true)»
		
		jimport('joomla.application.component.modeladmin');
		
		class «component.name.toFirstUpper»Model«pageref.name.toFirstUpper» extends JModelAdmin
		{
		}
	 '''
	/**
	 * return the code for the layout for the main view of the component
	 * @param Component component content the instance of a component
	 */
	def CharSequence phpAdminTemplateContent(Component component) '''
		<?php
		 «Slug.generateFileDoc(component,true)»
		?>
		<div >
			</div>
			<p class="text-center"> <h1><?php echo "Welcome to ". JText::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»') . " ". JText::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»_HOME'); ?> </h1>
			<h4>«component.manifest.description»</h4>
			 </p> 
			<div id="cpanel" class='cpanel'>
			<?php foreach ($this->views as $view)
			{
			?>
			    <div class="icon">
			        <h3><a href='<?php echo $view['url']; ?>'
			            <span><?php echo $view['title']; ?></span>
			        </a></h3>
			        <br />
			    </div>
			<?php
			}
			?>
		</div>  
		<p>This component is generated with the Joomdd tools, for more information <a target="_blank" href="https://github.com/icampus/JooMDD">see here</a></p>	
    '''
	/**
	 * return the code for the  main view of the component
	 * @param Component component content the instance of a component
	 */
	def CharSequence phpAdminViewContent(ExtendedComponent component) '''
		<?php
		«Slug.generateFileDoc(component,true)»
		// import Joomla view library
		jimport('joomla.application.component.view');
		 
		/**
		 * «class_name» View
		 */
		class «class_name»View«class_name»s extends JViewLegacy
		{
		
		 /** Method to get display
		 *
		 * @param   Object  $tpl  template
		 *
		 * @return void
		 * @generated
		 */
		    public function display($tpl = null)
		    {
		        if (!JFactory::getUser()->authorise('core.administrator'))
		        {
		            return JError::raiseWarning(404, JText::_('JERROR_ALERTNOAUTHOR'));
		        }
		
		        JHtml::_('behavior.tooltip');
		
		        $document = JFactory::getDocument();
		
		        JHtml::_('tabs.start');
		
		        $application = JFactory::getApplication("administrator");
		        $this->option = $application->scope;
		
		        $this->addToolBar();
		
		        $this->addViews();
		
		        parent::display($tpl);
		    }
		
		/**
		 * creates a joomla administratoristrative tool bar
		 *
		 * @return void
		 * @generated
		 */
		    private function addToolBar()
		    {
		        JToolBarHelper::title(JText::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»') . ': ' . JText::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»_HOME'), 'logo');
		        JToolBarHelper::preferences('«Slug.nameExtensionBind("com", component.name).toLowerCase»');
		    }
		
		/**
		 * creates html elements for the main menu
		 *
		 * @return void
		 * @generated
		 */
		    private function addViews()
		    {
		        $views = array();
		«FOR ExtendedPageReference pg : component.backEndExtendedPagerefence.filter[t | t.extendedPage.extendedDynamicPageInstance != null && !t.extendedPage.extendedDynamicPageInstance.isDetailsPage ]»
		
			$views['«pg.extendedPage.name.toLowerCase»'] = array();
			$views['«pg.extendedPage.name.toLowerCase»']['title'] = JText::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»_TITLE_«pg.extendedPage.name.toUpperCase»');
			$views['«pg.extendedPage.name.toLowerCase»']['url'] = "index.php?option=«Slug.nameExtensionBind("com", component.name).toLowerCase»&view=«pg.extendedPage.name.toLowerCase»";
		«ENDFOR»
		      
		$this->views = $views;
		}
		}
    '''
	/**
	 *Generate the code for access manifest of a component
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
	 * Generate the code for pageactions for each view in the component
	 */
	def CharSequence xmlAccessContentPage() '''
		
			«FOR dyn : extendeComp.allExtendedPage»
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
	 * Generate the code for configuration parameter of a component
	 * @param ExtendedComponent 		component content the instance of a component
	 * @param List<ExtendedDynamicPage> dynPages  containt all dynamics pages of the component
	 */
	def CharSequence xmlConfigContent(ExtendedComponent component, List<ExtendedDynamicPage> dynPages) '''
		<?xml version="1.0" encoding="utf-8"?>
		<config>
			<fieldset name="component" label="«name.toUpperCase»_LABEL" description="«name.toUpperCase»_DESC">
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
			«FOR detailsPage : dynPages.filter[t | t.isDetailsPage]»
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
					description="JCONFIG_PERMISSIONS_DESC"
					>
			
					<field
						name="rules"
						type="rules"
						label="JCONFIG_PERMISSIONS_LABEL"
						filter="rules"
						validate="rules"
						component="«Slug.nameExtensionBind("com",extendeComp.name)»"
						section="component" />
				</fieldset>
		
			</config>
		 '''

	
  
	
   /**
    * Generate the code for the Route rule in frontend
    * 
    * @param ExtendedComponent component  containt the isntance of a component
    */
	def CharSequence phpSiteRouterContent(ExtendedComponent component) '''
		 <?php
		   «Slug.generateFileDoc(component,true)»
		  /**
		 * @param	array	A named array
		 * @return	array
		 */
		function «component.name.toFirstUpper»BuildRoute(&$query) {
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
		 * @param	array	A named array
		 * @param	array
		 *
		 *
		 */
		function «component.name.toFirstUpper»ParseRoute($segments) {
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
	 * return the code javascript code to set the value of a reference of a foreign attribute 
	 *  
	 */
	def CharSequence genScriptForForeignKeys()'''
	 «Slug.generateFileDoc(extendeComp,false)»
	 /**
	 * this function set the value of reference for a foreign attribute 
	 */
	function setValueForeignKeys(element) {
	
	    var data = JSON.parse(element.value);
	    var item;
	
	    for(item in data){
	        jQuery("#"+item).attr("value",data[item]);
	    }
	   }
	'''
	/**
	 * return the code javascript code to set the values of many reference of a foreigns attributes 
	 *  
	 */
	def CharSequence genScriptForMultipleForeignKeys()'''
	 «Slug.generateFileDoc(extendeComp,false)»
	 
	jQuery(document).ready(function() {
			jQuery("select[generated='true']").each(function(){
				jQuery(this).trigger('onchange');
			})
		});
		
		/**
		  * this function set the values of references for many foreign attributes 
		  */
	function setMultipleValueForeignKeys(element) {
	
	    var data = [];
			var id = "#" + element.id + " option:selected";
			jQuery(id).each(function (){
				data.push(JSON.parse(jQuery(this).prop("value")));
			});
			if(data.length == 0)
				return;
	
	    var allkeys = Object.keys(data[0])
			var all_item = [];
	
			for(var a = 0; a< allkeys.length; a++){
				all_item[allkeys[a]] = [];
			}
	
	    for(var i =0; i<data.length; i++){
			var attr_obj = data[i];
			var attr_obj_keys = Object.keys(attr_obj);
			for(var j =0; j< attr_obj_keys.length; j++){
				var attr_key_value = attr_obj_keys[j];
				var attr_value = attr_obj[attr_key_value][0];
				all_item[attr_key_value].push(attr_value);
	
			}
	    }
			for(var c =0; c < allkeys.length; c++){
				var value = all_item[allkeys[c]];
				jQuery("#"+ allkeys[c]).attr("value",JSON.stringify(value));
	
			}
	   }
	'''

} // ComponentGenerator
