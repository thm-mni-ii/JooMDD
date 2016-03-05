/**
 */
package de.thm.icampus.ejsl.generator.ps.JoomlaExtensionGenerator;

import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.FrontendSection
import de.thm.icampus.ejsl.eJSL.IndexPage
import de.thm.icampus.ejsl.eJSL.PageReference
import de.thm.icampus.ejsl.eJSL.Section
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.ejsl.generator.ps.EntityGenerator
import de.thm.icampus.ejsl.generator.ps.JoomlaEntityGenerator.JoomlaEntityGenerator
import de.thm.icampus.ejsl.generator.ps.JoomlaUtil.Slug
import java.util.ArrayList
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.ejsl.eJSL.Language
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import java.util.Calendar
import java.util.List
import java.util.LinkedList
import de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator.FieldsGenerator
import de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator.TableGeneratorTemplate
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator.PageGeneratorClient
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameterGroup
import java.util.HashSet
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.ejsl.eJSL.Reference
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedReference

public class ComponentGenerator extends AbstractExtensionGenerator {

	private String slug
	private ExtendedComponent extendeComp
	private String class_name
	private JoomlaEntityGenerator entgen

	new(ExtendedComponent component, IFileSystemAccess fsa) {
		this.fsa = fsa;
		this.slug = component.name
		this.noPrefixName = this.slug
		this.name = "com_" + this.slug

		this.extendeComp = component
		this.class_name = this.noPrefixName.toFirstUpper
		this.extendeComp.formatName
		entgen = new JoomlaEntityGenerator(component.allExtendedEntity, "#__" + component.name, false)
	}

	def void formatName(Component component) {
		component.name = Slug.slugify(component.name)
	}

	override generate() {
		generateJoomlaDirectory("")

		/*
		 *  indexPage variable will be used for manifest <submenu> tag
		 *  In XTEND there are some bugs with instanceof command
		 *  For this reason all index pages of section will be
		 *  find and saved here
		 */
		var indexPages = extendeComp.backEndExtendedPagerefence.map [t|
			if(t.extendedPage.extendedDynamicPageInstance != null) t.extendedPage.extendedDynamicPageInstance
		];

		generateFile(name + ".xml", extendeComp.xmlContent(indexPages))

		// Generate language files
		for (lang : extendeComp.languages) {
			val ldir = lang.name
			generateFile("language/site/" + ldir + "/" + ldir + "." + name + ".ini",
				lang.languageFileContent(extendeComp.frontEndExtendedPagerefence))
			generateFile("language/site/" + ldir + "/" + ldir + "." + name + ".sys.ini",
				lang.languageFileContent(extendeComp.frontEndExtendedPagerefence))
			generateFile("language/admin/" + ldir + "/" + ldir + "." + name + ".ini",
				lang.languageFileContent(extendeComp.backEndExtendedPagerefence))
			generateFile("language/admin/" + ldir + "/" + ldir + "." + name + ".sys.ini",
				lang.languageFileContent(extendeComp.backEndExtendedPagerefence))
		}

			// Generate sql stuff
		generateJoomlaDirectory("admin/sql")
		generateFile("admin/sql/install.mysql.utf8.sql", entgen.dogenerate)
		generateFile("admin/sql/uninstall.mysql.utf8.sql", entgen.sqlAdminSqlUninstallContent(extendeComp.name))
		generateJoomlaDirectory("admin/sql/updates")
		generateJoomlaDirectory("admin/sql/updates/mysql")
		generateFile("admin/sql/updates/mysql/1.0.1.mysql.utf8.sql", sqlAdminSqlUpdateContent(extendeComp.name, true))
		
		// Generate backend section 
		if (extendeComp.backEndExtendedPagerefence != null) {
			generateBackendSection
		}

		// Generate frontend section 
		if (extendeComp.frontEndExtendedPagerefence != null) {
			generateFrontendSection
		}

	
		return ""
	}

	def CharSequence sqlAdminSqlUpdateContent(String component, boolean isupdate) {
		entgen.update = isupdate
		return entgen.dogenerate;
	}

	def CharSequence languageFileContent(Language lang,
		EList<ExtendedPageReference> pagerefList) '''
		«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»="«extendeComp.name.toFirstUpper»"
		«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_HOME="Home"
		«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_FORM_LBL_NONE_ID="ID"
		«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_FORM_LBL_NONE_CHECKED_OUT="Checked out"
		«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_FORM_LBL_NONE_CHECKED_OUT_TIME="Checked out Time"
		«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_FORM_LBL_NONE_ORDERING="Ordering"
		«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_FORM_LBL_NONE_CREATED_BY="Created By"
		«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_FORM_LBL_NONE_STATE="state"
		«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_JSTATUS="state"
		«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_JFIELD_PUBLISHED_DESC="State Description"
		JPUBLISHED="published"
		JUNPUBLISHED="unpublished"
		JARCHIVED="archived"
		JTRASHED="trashed"
		
		«FOR ExtendedPageReference pag : pagerefList»
			«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_TITLE_«Slug.slugify(pag.page.name).toUpperCase»="«pag.page.name.toFirstUpper»"
			«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_VIEW_«Slug.slugify(pag.page.name).toUpperCase»_TITLE="«pag.page.name.toFirstUpper»"
			«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_VIEW_«Slug.slugify(pag.page.name).toUpperCase»_DESC="«pag.page.name.toFirstUpper»"
		«ENDFOR»
		
		«FOR ExtendedPageReference dynamicPagereference : pagerefList.filter[t | t.extendedPage.extendedDynamicPageInstance != null]»
			
			«FOR ExtendedEntity ent: dynamicPagereference.extendedPage.extendedDynamicPageInstance.extendedEntityList»
				«FOR ExtendedAttribute attr: ent.allattribute»
					«Slug.nameExtensionBind("com", extendeComp.name).toUpperCase»_FORM_LBL_«Slug.slugify(ent.name).toUpperCase»_«Slug.slugify(attr.name).toUpperCase»="«Slug.slugify(attr.name).toFirstUpper»"
				«ENDFOR»
			«ENDFOR»
		«ENDFOR»
		«FOR e : lang.keyvaluepairs»
			«Slug.generateKeysName(extendeComp,e.name)»="«e.value»"
		«ENDFOR»
	'''

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
		    	<license>GPL 2.0</license>
		    «ENDIF»
		    «IF (component.manifest.version != null)»
		    	<version>«component.manifest.version»</version>
		    «ENDIF»
		    «IF (component.manifest.description != null)»
		    	<description>«component.manifest.description»</description>
		    «ENDIF»
		    <version>1.0.1</version>
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
					alias="«page.name.toFirstUpper»"
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
		generateJoomlaDirectory("/site")
		generateFile("site/" + noPrefixName + ".php", extendeComp.phpSiteContent)
		generateFile("site/controller.php", extendeComp.phpSiteControllerContent)
		generateFile("site/router.php", extendeComp.phpSiteRouterContent)
		generateJoomlaDirectory("site/views")
		generateJoomlaDirectory("site/models")
		generateJoomlaDirectory("site/models/fields")
		generateFields("site/models/fields")
		generateJoomlaDirectory("site/models/forms")

		generateJoomlaDirectory("site/views")
		generateJoomlaDirectory("site/assets")
		generateFile("site/assets/" + "setForeignKeys.js", genScriptForForeignKeys)

		generateJoomlaDirectory("site/controllers")

		val pagerefs = extendeComp.frontEndExtendedPagerefence
		for (pageref : pagerefs) {
			pageref.extendedPage.generatePage(path + "site", "site")
		}
	}

	private def void generateBackendSection() {
		generateJoomlaDirectory("admin")
		generateFile("admin/" + noPrefixName + ".php", extendeComp.phpAdminContent)
		generateFile("admin/controller.php", extendeComp.phpAdminControllerContent)

		generateFile("admin/access.xml", extendeComp.xmlAccessContent)
		generateFile("admin/config.xml", extendeComp.xmlConfigContent)
		generateJoomlaDirectory("admin/assets")
		generateFile("admin/assets/" + "setForeignKeys.js", genScriptForForeignKeys)
		

		generateJoomlaDirectory("admin/views")
		println(slug)
		var tempSlug = slug + "s"
		generateJoomlaDirectory("admin/views/" + tempSlug)
		generateFile("admin/views/" + tempSlug + "/view.html.php", extendeComp.phpAdminViewContent)
		generateJoomlaDirectory("admin/views/" + tempSlug + "/tmpl")
		generateFile("admin/views/" + tempSlug + "/tmpl/default.php", extendeComp.phpAdminTemplateContent)

		generateJoomlaDirectory("admin/models")
		generateJoomlaDirectory("admin/models/fields")
		generateFields("admin/models/fields")
		generateJoomlaDirectory("admin/tables")
		generateTable("admin/tables/")

		generateJoomlaDirectory("admin/views")

		generateJoomlaDirectory("admin/controllers")
		generateJoomlaDirectory("admin/helpers/")
		generateFile("admin/helpers/" + extendeComp.name.toLowerCase + ".php", generateHelperComponent)

		
		// commented out old model generation code
		val pagerefs = extendeComp.backEndExtendedPagerefence
		for (pageref : pagerefs) {
			pageref.extendedPage.generatePage(path + "admin", "admin")

		}
	}

	def generateFields(String fieldspath) {
		
		for (ExtendedEntity ent : extendeComp.allExtendedEntity) {
			var FieldsGenerator fieldEntity = new FieldsGenerator(extendeComp, ent)
			generateFile( fieldspath + "/" + ent.name.toLowerCase + ".php",fieldEntity.genFieldsForEntity)
			for (ExtendedReference ref : ent.extendedReference) {
				var index = ent.extendedReference.indexOf(ref)
				var FieldsGenerator fieldReference = new FieldsGenerator(ref, extendeComp, ent,index)
				generateFile(
					fieldspath + "/" + fieldReference.getnameField.toLowerCase  +
						".php", fieldReference.genRefrenceField)
			}
		}
		generateFile(fieldspath + "/" + extendeComp.name.toLowerCase+"user.php", FieldsGenerator.genFieldsForUserView(extendeComp) )
	}

	def generateTable(String path) {

		for (ExtendedEntity ent : extendeComp.allExtendedEntity) {
			var TableGeneratorTemplate table = new TableGeneratorTemplate(extendeComp, ent)
			generateFile(path + "/" + ent.name.toLowerCase + ".php", table.genClassTable)

		}

	}

	def generatePage(ExtendedPage pageref, String path, String section) {
		var PageGeneratorClient pageGen = new PageGeneratorClient(pageref, extendeComp, path, section, fsa)
		pageGen.generate
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
			«FOR g : component.extendedParameterGroupList»
				«FOR p:g.extendedParameterList»
					«writeParameter(p)»
				«ENDFOR»
			«ENDFOR»
			</fieldset>
		
			</config>
		 '''

	def CharSequence writeParameter(
		ExtendedParameter param) '''
		<field
		name="«param.name»"
		type="«Slug.getTypeName(param)»"
		default="«param.defaultvalue»"
		label="«param.label»"
		description="«param.descripton»"
		>
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
    		
    		JText::_('«pg.extendedPage.name.toUpperCase»'),
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

} // ComponentGenerator
