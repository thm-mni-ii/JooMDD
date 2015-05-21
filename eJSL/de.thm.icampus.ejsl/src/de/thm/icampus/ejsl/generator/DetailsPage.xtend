package de.thm.icampus.ejsl.generator

import de.thm.icampus.ejsl.eJSL.DetailsPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.Section
import org.eclipse.xtext.generator.IFileSystemAccess

class DetailsPageTemplate extends DynamicPageTemplate {
	
	private DetailsPage dpage
	private Component  com
	private String sec
	private DetailsPageTemplateBackendHelper backHelp
	private DetailsPageTemplateFrontEndHelper frontHelp
	private String path
	private String pagename
	
	new(DetailsPage dp, Component cp, String section, String path,IFileSystemAccess fsa){
		
		dpage = dp
		com = cp
		sec = section
		backHelp = new DetailsPageTemplateBackendHelper(dpage, com, sec)
		frontHelp = new DetailsPageTemplateFrontEndHelper(dpage, com, sec)
		this.path = path
		pagename = dpage.name.toLowerCase
		this.fsa = fsa
	}
	
	def void generateView(){
		
		if(sec.compareTo("admin")==0){
		  generateJoomlaDirectory(path +"/" + pagename)
		  generateFile(path+"/" + pagename +"/"+ "view.html.php", generateAdminViewClass())
		  generateJoomlaDirectory(path +"/" + pagename +"/" + "tmpl" )
		  generateFile(path+"/" + pagename+"/" + "tmpl"+"/" + "edit.php" , generateAdminViewLayout())
		 
		 }
		 else{
		 generateJoomlaDirectory(path+"/" + pagename+"edit")
		  generateFile(path+"/" + pagename+"edit"+"/"+ "view.html.php", generateSiteView(true))
		  generateJoomlaDirectory(path+"/" + pagename+ "edit"+"/" + "tmpl")
		   generateFile(path+"/" + pagename+ "edit"+"/" + "tmpl"+"/" + "default.php" , generateSiteViewLayoutEdit())
		   generateFile(path+"/" + pagename + "edit"+"/" + "tmpl"+"/" + "default.xml" , xmlSiteTemplateContent(dpage,com,com.name))
		   
		   generateJoomlaDirectory(path+"/" + pagename)
		  generateFile(path+"/" + pagename+"/" + "view.html.php", generateSiteView(false))
		  generateJoomlaDirectory(path +"/"+ pagename+"/" + "tmpl")
		   generateFile(path +"/"+ pagename+"/"  + "tmpl"+"/" + "default.php" , generateSiteViewLayoutShow())
		   generateFile(path +"/"+ pagename+"/" + "tmpl"+"/" + "default.xml" , xmlSiteTemplateContent(dpage,com,com.name))
		 }
		
	}
	def void generateController(){
		if(sec.compareTo("admin")==0){
		  generateFile(path+"/" + pagename + ".php", generateAdminController())
		 }else{
		  generateFile(path+"/" + pagename +"edit" +".php", generateSiteController(true))
		  generateFile(path+"/" + pagename+ ".php", generateSiteController(false))
		}
	}
	def void generateModel(){
		if(sec.compareTo("admin")==0){
		
		  generateFile(path+"/" + pagename + ".php", generateAdminModel())
		   generateFile(path + "/forms"+"/" + pagename + ".xml", xmlAdminFields(dpage,com,com.name))
		 }else{
		
		  generateFile(path+"/" + pagename+"edit"+ ".php", generateSiteModelEdit())
		   
		  generateFile(path+"/" + pagename  + ".php", generateSiteModelShow)
		  generateFile(path + "/forms"+"/" + pagename + ".xml", xmlAdminFields(dpage,com,com.name))
		}
	}
	
	def CharSequence generateAdminController()'''
	   «generateFileDoc(dpage,com,true)»
		jimport('joomla.application.component.controllerform');
		
		/**
		 * «dpage.name.toFirstUpper» controller class.
		 */
		class «com.name.toFirstUpper»Controller«dpage.name.toFirstUpper» extends JControllerForm
		{
		
		    function __construct() {
		        $this->view_list = '«dpage.name.toFirstUpper»';
		        parent::__construct();
		    }
		
		}
	'''
	def CharSequence generateModelLoadFormDataFunction()'''
	
	
	/**
	 * Method to get the data that should be injected in the form.
	 *
	 * @return	mixed	The data for the form.
	 * @since	1.6
	 */
	protected function loadFormData()
	{
		// Check the session for previously entered form data.
		$data = JFactory::getApplication()->getUserState('«Slug.nameExtensionBind("com",com.name.toLowerCase)».edit.«dpage.name.toLowerCase».data', array());

		if (empty($data)) {
			$data = $this->getItem();
		}

		return $data;
	}
	'''
	
	def CharSequence generateModelGetFormFunction()'''
	
	/**
	 * Method to get the record form.
	 *
	 * @param	array	$data		An optional array of data for the form to interogate.
	 * @param	boolean	$loadData	True if the form is to load its own data (default case), false if not.
	 * @return	JForm	A JForm object on success, false on failure
	 * @since	1.6
	 */
	public function getForm($data = array(), $loadData = true)
	{
		// Initialise variables.
		$app	= JFactory::getApplication();

		// Get the form.
		$form = $this->loadForm('«Slug.nameExtensionBind("com",com.name.toLowerCase)».«dpage.name.toLowerCase»', '«dpage.name.toLowerCase»', array('control' => 'jform', 'load_data' => $loadData));

		if (empty($form)) {
			return false;
		}

		return $form;
	}
	'''
	def CharSequence generateAdminModel()'''
	«generateFileDoc(dpage,com,true)»
	
	jimport('joomla.application.component.modeladmin');

	/**
	 * The Model To schow the Details of a «dpage.name.toFirstUpper»  
	 */
	class «com.name.toFirstUpper»Model«dpage.name.toFirstUpper» extends JModelAdmin
	{
		/**
		 * @var		string	The prefix to use with controller messages.
		 * @since	1.6
		 */
		protected $text_prefix = '«Slug.nameExtensionBind("com".toUpperCase, com.name.toUpperCase)»';
		«backHelp.generateAdminModelTableFunction()»
		«generateModelGetFormFunction()»
		«generateModelLoadFormDataFunction()»
		«backHelp.generateAdminModelGetItemFunction()»
		«backHelp.generateAdminModelprepareTableFunction()»
	}
		'''

	
	
	def generateAdminViewClass()'''
	«generateFileDoc(dpage,com, true)»
		jimport('joomla.application.component.view');

		/**
		 * View to edit a «dpage.name»
		 */
		class «com.name.toFirstUpper»View«dpage.name.toFirstUpper» extends JViewLegacy {
		
		    protected $state;
		    protected $item;
		    protected $form;
		    
		    «backHelp.generateAdminViewDisplay()»
		    «backHelp.generateAdminViewAddToolbar()»
		}		
			'''
	
	
	
	def CharSequence generateAdminViewLayout()'''
	«generateFileDoc(dpage,com, false)»
		JHtml::addIncludePath(JPATH_COMPONENT . '/helpers/html');
		JHtml::_('behavior.tooltip');
		JHtml::_('behavior.formvalidation');
		JHtml::_('formbehavior.chosen', 'select');
		JHtml::_('behavior.keepalive');
		
		// Import CSS
		$document = JFactory::getDocument();
		$document->addStyleSheet('components/«Slug.nameExtensionBind("com", com.name.toLowerCase)»/assets/css/«com.name.toLowerCase».css');
		?>
		<script type="text/javascript">
		    js = jQuery.noConflict();
		    js(document).ready(function() {
		        
		    });
		
		    Joomla.submitbutton = function(task)
		    {
		        if (task == '«dpage.name.toLowerCase».cancel') {
		            Joomla.submitform(task, document.getElementById('«dpage.name.toLowerCase»-form'));
		        }
		        else {
		            
		            if (task != '«dpage.name.toLowerCase».cancel' && document.formvalidator.isValid(document.id('«dpage.name.toLowerCase»-form'))) {
		                
		                Joomla.submitform(task, document.getElementById('«dpage.name.toLowerCase»-form'));
		            }
		            else {
		                alert('<?php echo $this->escape(JText::_('JGLOBAL_VALIDATION_FORM_FAILED')); ?>');
		            }
		        }
		    }
		</script>
		«backHelp.generateAdminViewLayoutFormular()»
	'''
	
	def CharSequence generateSiteController(Boolean isedit)'''
	«generateFileDoc(dpage,com,true)»
	
	require_once JPATH_COMPONENT . '/controller.php';

	/**
	 * «dpage.name.toFirstUpper» controller class to «if(isedit)  "Edit" else "Show"» a Item .
	 */
	class «com.name.toFirstUpper»Controller«if(isedit) dpage.name.toFirstUpper + "Edit" else dpage.name.toFirstUpper» extends «com.name.toFirstUpper»Controller {
		«frontHelp.generateSiteControllerEdit(isedit)»
		«IF isedit»
		«frontHelp.generateSiteControllerSave»
		«frontHelp.generateSiteControllerCancel»
		«ELSE»
		«frontHelp.generateSiteControllerRemove»
		«ENDIF»
		}
	'''
	def CharSequence generateSiteModelShow()'''
	«generateFileDoc(dpage,com,true)»
	
	jimport('joomla.application.component.modelitem');
	jimport('joomla.event.dispatcher');
	
	/**
	 * Model to show a Dataitem
	 */
	class «com.name.toFirstUpper»Model«dpage.name.toFirstUpper» extends JModelItem {
		«frontHelp.generateSiteModelPopulatestate()»
		«frontHelp.generateSiteModelgetData(false)»
		«frontHelp.generateSiteModelCheckin()»
		«frontHelp.generateSiteModelCheckout()»
		«frontHelp.generateSiteModelgetCategory()»
		«frontHelp.generateSiteModelpublish()»
		«frontHelp.generateSiteModelDelete()»
	}
	'''
	def CharSequence generateSiteModelEdit()'''
	«generateFileDoc(dpage,com,true)»
	
	jimport('joomla.application.component.modelform');
	jimport('joomla.event.dispatcher');
	
	/**
	 * Model to Edit  a Dataitem
	 */
	class «com.name.toFirstUpper»Model«dpage.name.toFirstUpper»Edit extends JModelForm {
		var $_item = null;
		«frontHelp.generateSiteModelPopulatestate()»
		«frontHelp.generateSiteModelgetData(true)»
		«frontHelp.generateSiteModelCheckin()»
		«frontHelp.generateSiteModelCheckout()»
		«frontHelp.generateSiteModelgetTable()»
		«generateModelGetFormFunction()»
		«generateModelLoadFormDataFunction()»
		«frontHelp.generateSiteModelSave()»
		«frontHelp.generateSiteModelDelete()»
		
	}
	'''
	
	def CharSequence generateSiteView(Boolean isedit)'''
	«generateFileDoc(dpage,com,true)»
	
	jimport('joomla.application.component.view');

/**
 * View to « if(isedit) "Edit" else "Show"» edit
 */
class «com.name.toFirstUpper»View« if(isedit)dpage.name.toFirstUpper + "Edit" else dpage.name.toFirstUpper  » extends JViewLegacy {

    protected $state;
    protected $item;
    protected $form;
    protected $params;
    «frontHelp.generateSiteViewDisplay(isedit)»
    «frontHelp.generateSiteViewprepareDocument()»
    } 
	'''
	def CharSequence generateSiteViewLayoutEdit()'''
	«generateFileDoc(dpage,com,false)»
	JHtml::_('behavior.keepalive');
	JHtml::_('behavior.tooltip');
	JHtml::_('behavior.formvalidation');
	JHtml::_('formbehavior.chosen', 'select');
	
	//Load admin language file
	$lang = JFactory::getLanguage();
	$lang->load('«Slug.nameExtensionBind("com", com.name).toLowerCase»', JPATH_ADMINISTRATOR);
	$doc = JFactory::getDocument();
	$doc->addScript(JUri::base() . '/components/«Slug.nameExtensionBind("com", com.name).toLowerCase»/assets/js/form.js');
	
	
	?>
	</style>
	<script type="text/javascript">
	    getScript('//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js', function() {
	        jQuery(document).ready(function() {
	            jQuery('#form-«dpage.name.toLowerCase»').submit(function(event) {
	                
	            });
	
	            
	        });
	    });
	
	</script>
	
	<div class="«dpage.name.toLowerCase»-edit front-end-edit">
	    <?php if (!empty($this->item->id)): ?>
	        <h1>Edit <?php echo $this->item->id; ?></h1>
	    <?php else: ?>
	        <h1>Add</h1>
	    <?php endif; ?>
	    «frontHelp.generateSiteViewLayoutEditForm()»
	'''
	def CharSequence generateSiteViewLayoutShow()'''
	«generateFileDoc(dpage,com,false)»
	/Load admin language file
$lang = JFactory::getLanguage();
$lang->load('«Slug.nameExtensionBind("com", com.name).toLowerCase»', JPATH_ADMINISTRATOR);
$canEdit = JFactory::getUser()->authorise('core.edit', '«Slug.nameExtensionBind("com", com.name).toLowerCase».' . $this->item->id);
if (!$canEdit && JFactory::getUser()->authorise('core.edit.own', '«Slug.nameExtensionBind("com", com.name).toLowerCase»' . $this->item->id)) {
	$canEdit = JFactory::getUser()->id == $this->item->created_by;
}
?>
	«frontHelp.generateSiteViewLayoutShow»
	 '''
}