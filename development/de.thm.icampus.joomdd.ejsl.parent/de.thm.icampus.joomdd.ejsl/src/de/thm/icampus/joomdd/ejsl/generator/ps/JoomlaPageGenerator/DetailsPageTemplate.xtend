package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import org.eclipse.xtext.generator.IFileSystemAccess
import java.io.File
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute

class DetailsPageTemplate extends   DynamicPageTemplate {
	
	private ExtendedDynamicPage dpage
	private ExtendedComponent  com
	private String sec
	private   DetailsPageTemplateBackendHelper backHelp
	private   DetailsPageTemplateFrontEndHelper frontHelp
	private String path
	private String pagename
	
	new(ExtendedDynamicPage dp, ExtendedComponent cp, String section, String path,IFileSystemAccess fsa){
		
		dpage = dp
		com = cp
		sec = section
		backHelp = new   DetailsPageTemplateBackendHelper(dpage, com, sec)
		frontHelp = new   DetailsPageTemplateFrontEndHelper(dpage, com, sec)
		this.path = path
		pagename = dpage.name.toLowerCase
		this.fsa = fsa
	}
	
	
	
	def void generateView(){
		
		if(sec.equalsIgnoreCase("admin")){
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
		   generateFile(path+"/" + pagename + "edit"+"/" + "tmpl"+"/" + "default.xml" , xmlSiteTemplateContent(pagename+ "edit", dpage, com))
		   
		   generateJoomlaDirectory(path+"/" + pagename)
		  generateFile(path+"/" + pagename+"/" + "view.html.php", generateSiteView(false))
		  generateJoomlaDirectory(path +"/"+ pagename+"/" + "tmpl")
		   generateFile(path +"/"+ pagename+"/"  + "tmpl"+"/" + "default.php" , generateSiteViewLayoutShow())
		   generateFile(path +"/"+ pagename+"/" + "tmpl"+"/" + "default.xml" , xmlSiteTemplateContent(pagename, dpage,com))
		 }
		
	}
	def void generateController(){
		if(sec.equalsIgnoreCase("admin")){
		  generateFile(path+"/" + pagename + ".php", generateAdminController())
		 }else{
		  generateFile(path+"/" + pagename +"edit" +".php", generateSiteController(true))
		  generateFile(path+"/" + pagename+ ".php", generateSiteController(false))
		}
	}
	def void generateModel(){
		if(sec.equalsIgnoreCase("admin")){
		
		  generateFile(path+"/" + pagename + ".php", generateAdminModel())
		  
		   generateFile(path + "/forms"+"/" + dpage.extendedEntityList.get(0).name.toLowerCase + ".xml", xmlAdminFields(dpage,com,com.name))
		 }else{
		
		  generateFile(path+"/" + pagename+"edit"+ ".php", generateSiteModelEdit())
		   
		  generateFile(path+"/" + pagename  + ".php", generateSiteModelShow)
		  generateFile(path + "/forms"+"/" + dpage.extendedEntityList.get(0).name.toLowerCase + ".xml", xmlAdminFields(dpage,com,com.name))
		}
	}
	
	def CharSequence generateAdminController()'''
	   «generateFileDoc(dpage,com,true)»
		jimport('joomla.application.component.controllerform');
		
		/**
		 * «dpage.name.toFirstUpper» controller class.
		 * @generated
		 */
		class «com.name.toFirstUpper»Controller«dpage.name.toFirstUpper» extends JControllerForm
		{
		
		    function __construct() {
		    	«var IndexPage inPage = Slug.getPageForAll(dpage, com) »
		    	«IF inPage != null»
		        $this->view_list = '«Slug.getPageForAll(dpage, com).name.toLowerCase»';
		        «ELSE»
		         $this->view_list = '<Put the View Name>';
		        «ENDIF»
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
	 * @generated
	 */
	protected function loadFormData()
	{
		// Check the session for previously entered form data.
		$data = JFactory::getApplication()->getUserState('«Slug.nameExtensionBind("com",com.name.toLowerCase)».edit.«dpage.extendedEntityList.get(0).name.toLowerCase».data', array());

		if (empty($data)) {
			$data = $this->getItem();
		}

		return $data;
	}
	'''
	def CharSequence generateModelGetItemFunction() '''
	/**
	 * Method to get a single record.
	 *
	 * @param	integer	The id of the primary key.
	 *
	 * @return	mixed	Object on success, false on failure.
	 * @since	1.6
	 * @generated
	 */
	public function getItem($pk = null)
	{
		    $pk = (!empty($pk)) ? $pk : (int) $this->getState('«dpage.name.toLowerCase».id');
        $table = $this->getTable();

        if ($pk > 0)
        {
            try{
               // Attempt to load the row.
               $return = $table->load($pk);
           }catch (Exception $e){
               // Check for a table object error.
               throw new Exception('Database Failur:  no element Found'. $e . $return);
           }
        }

       // Convert to the JObject before adding other data.
        $properties = $table->getProperties(1);
        $item =  ArrayHelper::toObject($properties);

        if (property_exists($item, 'params'))
        {
            $registry = new Registry;
            $registry->loadString($item->params);
            $item->params = $registry->toArray();
        }

		return $item;
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
	 * @generated
	 */
	public function getForm($data = array(), $loadData = true)
	{
		// Initialise variables.
		$app	= JFactory::getApplication();

		// Get the form.
		$form = $this->loadForm('«Slug.nameExtensionBind("com",com.name.toLowerCase)».«dpage.name.toLowerCase»', '«dpage.extendedEntityList.get(0).name.toLowerCase»', array('control' => 'jform', 'load_data' => $loadData));

		if (empty($form)) {
			return false;
		}

		return $form;
	}
	'''
	def CharSequence generateAdminModel()'''
	«generateFileDoc(dpage,com,true)»
	
	jimport('joomla.application.component.modeladmin');
	use Joomla\Utilities\ArrayHelper;
	use Joomla\Registry\Registry;

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
		«generateModelGetItemFunction()»
		«generateModelAdminSaveData()»
		«generateModelReferenceSave()»
		«backHelp.generateAdminModelprepareTableFunction()»
	}
		'''
	
	def CharSequence generateModelAdminSaveData() '''
	public function save($data){
	$inputs =& JFactory::getApplication()->input->get("jform", array(), 'array');
	
	«FOR ExtendedReference ref: dpage.extendedEntityList.get(0).extendedReference»
	«IF ref.upper.equalsIgnoreCase("*") || ref.upper.equalsIgnoreCase("-1")»
	 $this->set«ref.entity.name»($inputs);
	«ENDIF»
	«ENDFOR»
	
	return parent::save($data);
	}
	'''
	
	def CharSequence generateModelReferenceSave()'''
	«FOR ExtendedReference ref: dpage.extendedEntityList.get(0).extendedReference»
	«IF ref.upper.equalsIgnoreCase("*") || ref.upper.equalsIgnoreCase("-1")»
	 public function set«ref.entity.name»($inputs){
	 	«var EList<Attribute> referenceAttr = Slug.getOtherAttribute(ref)»
	 	«FOR Attribute attr : referenceAttr»
	 		$«attr.name.toLowerCase» = json_decode($inputs['«attr.name.toLowerCase»']);
	 	«ENDFOR»
	 		$«ref.entity.name.toLowerCase»_id = json_decode($inputs['«ref.entity.name.toLowerCase»_id']);
	 		«FOR ExtendedAttribute toAttr: ref.extendedAttribute»
	 		$«toAttr.name.toLowerCase»= $inputs['«toAttr.name.toLowerCase»'];
	 		«ENDFOR»

	 		if(«Slug.transformAttributeListInString("!empty(", referenceAttr ,"&&", ")")»){
	 			foreach( $«ref.entity.name.toLowerCase»_id as $item){
	 				if(intval($item) != 0){
	 					$mappingTableDelete = $this->getTable("«ref.entity.name.toFirstLower»");
	 					$mappingTableDelete->delete($item);
	 				}
	 			}
	            for($index =0; $index< count($«referenceAttr.get(0).name.toLowerCase»); $index++){
	         
	 				$mappingTable = $this->getTable("«ref.entity.name.toFirstLower»");
	 				$dataToSave = array();
	 				«FOR Attribute attr: referenceAttr»
	 				$dataToSave["«attr.name.toLowerCase»"] = $«attr.name.toLowerCase»[$count];
	 				«ENDFOR»
	 				«FOR ExtendedAttribute toattr: ref.extendedAttribute»
	 				$dataToSave["«ref.extendedAttributeReferenced.get(ref.extendedAttribute.indexOf(toattr)).name.toLowerCase»"] = $«toattr.name.toLowerCase»;
	 				«ENDFOR»
	 				$dataToSave["state"]=1;
	 				$mappingTable->save($dataToSave);
	 			
	            }
	 			
	 
	 		}
	 	}
	«ENDIF»
	«ENDFOR»
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
		«frontHelp.generateSiteControllerRemove»
		«ELSE»
		«frontHelp.generateSiteControllerRemove»
		«ENDIF»
		}
	'''
	def CharSequence generateSiteModelShow()'''
	«generateFileDoc(dpage,com,true)»
	
	jimport('joomla.application.component.modelitem');
	jimport('joomla.event.dispatcher');
	use Joomla\Utilities\ArrayHelper;
	use Joomla\Registry\Registry;
	
	/**
	 * Model to show a Dataitem
	 */
	class «com.name.toFirstUpper»Model«dpage.name.toFirstUpper» extends JModelItem {
		«frontHelp.generateSiteModelPopulatestate()»
		«generateModelGetItemFunction»
		«frontHelp.generateSiteModelCheckin()»
		«frontHelp.generateSiteModelgetTable»
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
	use Joomla\Utilities\ArrayHelper;
	use Joomla\Registry\Registry;
	
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
		«generateModelGetItemFunction()»
		«generateModelLoadFormDataFunction()»
		«frontHelp.generateSiteModelSave()»
		«generateModelReferenceSave()»
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
	//Load admin language file
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