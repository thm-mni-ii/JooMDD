/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.PageGeneratorClient
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.LanguageGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import java.util.Calendar
import java.util.List
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.joomdd.ejsl.generator.ps.EntityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.PageGenerator
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList

public class ComponentGenerator extends AbstractExtensionGenerator {

	private String slug
	private ExtendedComponent extendeComp
	private String class_name
	private String updatePath
    
	new(ExtendedComponent component, IFileSystemAccess fsa, String path, String updatePath) {
		this.fsa = fsa;
		this.slug = component.name
		this.noPrefixName = this.slug
		this.name = "com_" + this.slug

		this.extendeComp = component
		this.class_name = this.noPrefixName.toFirstUpper
		this.extendeComp.formatName
		this.path = path
		this.updatePath = updatePath
	}

	def void formatName(Component component) {
		component.name = Slug.slugify(component.name)
	}

	override generate() {
		println("component path " +path);
		generateJoomlaDirectory(path+"")

		/*
		 *  indexPage variable will be used for manifest <submenu> tag
		 *  In XTEND there are some bugs with instanceof command
		 *  For this reason all index pages of section will be
		 *  find and saved here
		 */
		var indexPages = extendeComp.backEndExtendedPagerefence.map [t|
			if(t.extendedPage.extendedDynamicPageInstance != null) t.extendedPage.extendedDynamicPageInstance
		];

		generateFile(path+ name + ".xml", extendeComp.xmlContent(indexPages))

		// Generate language files
		var LanguageGenerator langgen = new LanguageGenerator(fsa)
		langgen.genComponentLanguage(extendeComp,path)

			// Generate sql stuff
		generateJoomlaDirectory(path+"admin/sql")
		generateJoomlaDirectory(path+"admin/sql/updates")
		generateJoomlaDirectory(path+"admin/sql/updates/mysql")
		
		

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

	
	
	def CharSequence xmlContent(ExtendedComponent component, List<ExtendedDynamicPage> indexPages) '''
		<?xml version="1.0" encoding="utf-8"?>
		<extension type="component" version="3.3" method="upgrade">
		    <name>«component.name»</name>
		    «component.manifest.authors.generate»            
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
				«FOR page : indexPages.filter[t | !t.detailsPage]»
					
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
		generateFile( path +"site/assets/" + "setForeignKeys.js", genScriptForForeignKeys)
		generateFile( path +"site/assets/" + "setMultipleForeignKeys.js", genScriptForMultipleForeignKeys)

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

	private def void generateBackendSection() {
		generateJoomlaDirectory(path+"admin")
		generateFile( path +"admin/" + noPrefixName + ".php", extendeComp.phpAdminContent)
		generateFile( path +"admin/controller.php", extendeComp.phpAdminControllerContent)

		generateFile( path +"admin/access.xml", extendeComp.xmlAccessContent)
		generateFile( path +"admin/config.xml", extendeComp.xmlConfigContent)
		generateJoomlaDirectory(path+"admin/assets")
		generateFile( path +"admin/assets/" + "setForeignKeys.js", genScriptForForeignKeys)
		generateFile( path +"admin/assets/" + "setMultipleForeignKeys.js", genScriptForMultipleForeignKeys)
		

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
		generateFile( path +"admin/helpers/" + extendeComp.name.toLowerCase + ".php", generateHelperComponent)

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

    public def void generateUpdatePages(EList<ExtendedPage> pageRefList, String section){
    	
    	var PageGenerator pgGenUpdate= new PageGenerator(extendeComp, pageRefList,fsa, updatePath,section,true)
		pgGenUpdate.dogenerate
	
    }
	def CharSequence phpSiteContent(Component component) '''
		<?php
		     «Slug.generateFileDoc(component,true)»
		    
		    // Get parameters
		    // $.. = $params->get(..);
		    
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

	def CharSequence phpSiteViewContent(ExtendedComponent component) '''
		<?php
		     «Slug.generateFileDoc(component,true)»
		    
		    // import Joomla view library
		    jimport('joomla.application.component.view');
		    
		    /**
		     * HTML View class for the «class_name» Component
		     */
		    class «class_name»View«class_name» extends JView
		    {
		        // Overwriting JView display method
		        function display($tpl = null) 
		        {
		            // Assign data to the view
		            $this->msg = $this->get('Msg');
		            
		            // Check for errors.
		            if (count($errors = $this->get('Errors'))) 
		            {
		                JLog::add(implode('<br />', $errors), JLog::WARNING, 'jerror');
		                return false;
		            }
		            // Display the view
		            parent::display($tpl);
		        }
		    }
	'''

	def CharSequence xmlSiteTemplateContent(ExtendedComponent component) '''
		<?xml version="1.0" encoding="utf-8"?>
		<metadata>
		    <layout title="«name.toUpperCase»_VIEW_DEFAULT_TITLE">
		        <message><![CDATA[«name.toUpperCase»_VIEW_DEFAULT_DESC]]></message>
		    </layout>
		    <fields
		        name="request"
		        addfieldpath="administrator/components/«name»/models/fields"
		    >
		        <fieldset name="request">
		            <field
		                name="id"
		                type="«name»"
		                label="«name.toUpperCase»_FIELD_GREETING_LABEL"
		                description="«name.toUpperCase»_FIELD_GREETING_DESC"
		            />
		        </fieldset>
		    </fields>
		</metadata>
	'''

	def CharSequence phpSiteTemplateContent(ExtendedComponent component) '''
		<?php
		    // No direct access to this file
		    defined('_JEXEC') or die('Restricted access');
		?>
		<h1><?php echo $this->msg; ?></h1>
	'''

	/**
	 * generate simple frontendModel file contents,
	 * which extend from JModelItem in general and access single items (not lists)
	 */
	def CharSequence phpSiteModelContent(ExtendedComponent component, ExtendedPage pageref) '''
		<?php
		    «Slug.generateFileDoc(component,true)»
		     
		    // import Joomla modelitem library
		    jimport('joomla.application.component.modelitem');
		     
		    /**
		     * «class_name» Model
		     */
		    class «component.name.toFirstUpper»Model«pageref.name.toFirstUpper» extends JModelItem
		    {
		            /**
		             * @var array messages
		             */
		            protected $messages;
		     
		            /**
		             * Returns a reference to the a Table object, always creating it.
		             *
		             * @param       type    The table type to instantiate
		             * @param       string  A prefix for the table class name. Optional.
		             * @param       array   Configuration array for model. Optional.
		             * @return      JTable  A database object
		             * @since       2.5
		             */
		            public function getTable($type = '«class_name»', $prefix = '«class_name»Table', $config = array()) 
		            {
		                    return JTable::getInstance($type, $prefix, $config);
		            }
		            /**
		             * Get the message
		             * @param  int    The corresponding id of the message to be retrieved
		             * @return string The message to be displayed to the user
		             */
		            public function getMsg($id = 1) 
		            {
		                    if (!is_array($this->messages))
		                    {
		                            $this->messages = array();
		                    }
		     
		                    if (!isset($this->messages[$id])) 
		                    {
		                            //request the selected id
		                            $jinput = JFactory::getApplication()->input;
		                            $id = $jinput->get('id', 1, 'INT' );
		     
		                            // Get a Table«class_name» instance
		                            $table = $this->getTable();
		     
		                            // Load the message
		                            $table->load($id);
		     
		                            // Assign the message
		                            $this->messages[$id] = $table->greeting;
		                    }
		     
		                    return $this->messages[$id];
		            }
		    }
	'''

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
	def CharSequence phpAdminSimpleModelContent(ExtendedComponent component,
		ExtendedPage pageref) '''
		<?php
		
		 «Slug.generateFileDoc(component,true)»
		
		jimport('joomla.application.component.modeladmin');
		
		class «component.name.toFirstUpper»Model«pageref.name.toFirstUpper» extends JModelAdmin
		{
		}
	 '''

	def CharSequence phpAdminTemplateContent(
		Component component
	) '''
<?php
 «Slug.generateFileDoc(component,true)»
?>
<div >
	</div>
	<p class="text-center"> <h1><?php echo JText::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»') . " ". JText::_('«Slug.nameExtensionBind("com", component.name).toUpperCase»_HOME'); ?> </h1> </p> 
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
			
    '''

	def CharSequence phpAdminViewContent(
		ExtendedComponent component
	) '''
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

	def CharSequence xmlConfigContent(ExtendedComponent component) '''
		<?xml version="1.0" encoding="utf-8"?>
		<config>
			<fieldset name="component" label="«name.toUpperCase»_LABEL" description="«name.toUpperCase»_DESC">
			
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

	

	def CharSequence generateHelperComponent() '''
    <?php
     «Slug.generateFileDoc(extendeComp,true)»

/**
 * «extendeComp.name.toUpperCase»  helper.
 */
class «extendeComp.name.toFirstUpper»Helper {

    /**
     * Configure the Linkbar.
     */
    public static function addSubmenu($vName = '') {
    	«FOR ExtendedPageReference pg : extendeComp.backEndExtendedPagerefence.filter[t| t.extendedPage.extendedDynamicPageInstance != null && !t.extendedPage.extendedDynamicPageInstance.isDetailsPage]»
    	
    		JHtmlSidebar::addEntry(
    		
    		JText::_('«Slug.nameExtensionBind("com",extendeComp.name).toUpperCase»_TITLE_«pg.extendedPage.name.toUpperCase»'),
    		'index.php?option=«Slug.nameExtensionBind("com",extendeComp.name).toLowerCase»&view=«pg.extendedPage.name.toLowerCase»',
    		$vName == '«pg.extendedPage.name.toLowerCase»'
    		);
    	     		
    	  
        «ENDFOR»

        		}

    /**
     * Gets a list of the actions that can be performed.
     *
     * @return	JObject
     * @since	1.6
     */
    public static function getActions() {
        $user = JFactory::getUser();
        $result = new JObject;

        $assetName = '«Slug::nameExtensionBind('com', extendeComp.name)»';

        $actions = array(
            'core.admin', 'core.manage', 'core.create', 'core.edit', 'core.edit.own', 'core.edit.state', 'core.delete'
        );

        foreach ($actions as $action) {
            $result->set($action, $user->authorise($action, $assetName));
        }

        return $result;
    }


}
    '''

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
	def CharSequence genScriptForForeignKeys()'''
	 «Slug.generateFileDoc(extendeComp,false)»
	 
	function setValueForeignKeys(element) {
	
	    var data = JSON.parse(element.value);
	    var item;
	
	    for(item in data){
	        jQuery("#"+item).attr("value",data[item]);
	    }
	   }
	'''
	def CharSequence genScriptForMultipleForeignKeys()'''
	 «Slug.generateFileDoc(extendeComp,false)»
	 
	jQuery(document).ready(function() {
			jQuery("select[generated='true']").each(function(){
				jQuery(this).trigger('onchange');
			})
		});
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
