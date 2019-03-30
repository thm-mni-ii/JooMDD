package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.Slug
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2

/**
 * This class contains the templates to generate the files and necessary code or pages.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
class DetailsPageTemplate extends DynamicPageTemplate {
	
	ExtendedDynamicPage dpage
	ExtendedComponent  com
	String sec
	DetailsPageTemplateBackendHelper backHelp
	DetailsPageTemplateFrontEndHelper frontHelp
	String path
	String modelPath
    String viewPath
    String tmplPath
    String controllerPath
	String pagename
	String formPath
	ExtendedEntity mainEntity
	
	new(ExtendedDynamicPage dp, ExtendedComponent cp, String section, String path,IFileSystemAccess2 fsa) {
		dpage = dp
		com = cp
		sec = section
		backHelp = new DetailsPageTemplateBackendHelper(dpage, com, sec)
		frontHelp = new DetailsPageTemplateFrontEndHelper(dpage, com, sec)
		this.path = path
        this.modelPath = path + "/Model"
        this.viewPath = path + "/View"
        this.tmplPath = path + "/tmpl"
        this.controllerPath = path + "/Controller"
        this.formPath = path + "/forms"
		pagename = Slug.slugify(dpage.name.toLowerCase)
		this.fsa = fsa
		mainEntity = dp.extendedEntityList.get(0)
	}
	
	def void generateView() {
	    if(sec.equalsIgnoreCase("admin")) {
	        generateFile(viewPath + "/" + pagename.toFirstUpper + "/" + "HtmlView.php", generateAdminViewClass())
	        generateFile(tmplPath + "/" + pagename + "/" + "edit.php" , generateAdminViewLayout())
		 } else {
		     if(!dpage.extendedTableColumnList.empty && !dpage.extendedEditedFieldsList.isEmpty) {
		         generateFrontEndEditView(pagename+"edit")
		         generateFrontEndShowView(pagename+"edit")
		     } else {
		         if( !dpage.extendedEditedFieldsList.isEmpty) {
		             generateFrontEndEditView(pagename)
		         } else {
		             generateFrontEndShowView(pagename)
		         }
		     }
		 }
	}
	
	def void generateFrontEndShowView(String editPagename) {
	    generateFile(viewPath + "/" + pagename.toFirstUpper + "/" + "HtmlView.php", generateSiteView(false, ''))
	    generateFile(tmplPath + "/" + pagename + "/" + "default.php" , generateSiteViewLayoutShow(editPagename))
	    generateFile(tmplPath + "/" + pagename + "/" + "default.xml" , xmlSiteTemplateContent(pagename, dpage,com))
	}
	
	def void generateFrontEndEditView(String editPagename) {
	    generateFile(viewPath + "/" + editPagename.toFirstUpper + "/" + "HtmlView.php", generateSiteView(true,editPagename))
	    generateFile(tmplPath + "/" + editPagename + "/" + "default.php" , generateSiteViewLayoutEdit(editPagename))
	    generateFile(tmplPath + "/" + editPagename + "/" + "default.xml" , xmlSiteTemplateContent(editPagename, dpage, com)) 
	}
	
	def void generateController() {
	    if(sec.equalsIgnoreCase("admin")) {
	        generateFile(controllerPath + "/" + pagename.toFirstUpper + "Controller.php", generateAdminController())
	    } else {
	        generateFile(controllerPath + "/" + pagename.toFirstUpper +"edit" +"Controller.php", generateSiteController(true))
	        generateFile(controllerPath + "/" + pagename.toFirstUpper+ "Controller.php", generateSiteController(false))
	    }
	}
	
	def void generateModel() {
	    if(sec.equalsIgnoreCase("admin")) {
	        generateFile(modelPath + "/" + pagename.toFirstUpper + "Model.php", generateAdminModel())
	        generateFile(formPath + "/" + dpage.extendedEntityList.get(0).name.toLowerCase + ".xml", xmlAdminFields(dpage,com,com.name))
	    } else {
	        generateFile(formPath + "/" + dpage.extendedEntityList.get(0).name.toLowerCase + ".xml", xmlSiteFields(dpage,com,com.name))
		 	
		 	if(!dpage.extendedTableColumnList.empty && !dpage.extendedEditedFieldsList.isEmpty) {	
		 	    generateFile(modelPath + "/" + pagename.toFirstUpper + "edit" + "Model.php", generateSiteModelEdit(pagename+"edit"))
		 	    generateFile(modelPath + "/" + pagename.toFirstUpper  + "Model.php", generateSiteModelShow)		  
		 	} else {
		 	    if (!dpage.extendedEditedFieldsList.isEmpty) {
		 	        generateFile(modelPath + "/" + pagename.toFirstUpper + "Model.php", generateSiteModelEdit(pagename))
		 	    } else {
		 	        generateFile(modelPath + "/" + pagename.toFirstUpper + "Model.php", generateSiteModelShow)		  
		 	    }
		 	}
		}
	}
	
	def CharSequence generateAdminController()'''
		«generateFileDoc(dpage,com)»
		
		«Slug.generateNamespace(com.name, "Administrator", "Controller")»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ControllerForm", "Text", "ComponentHelper", "HelperMedia", "Route", "Factory", "MVCFactoryInterface"))»

		jimport('joomla.filesystem.file');
		/**
		 * «dpage.name.toFirstUpper» controller class.
		 * @generated
		 */
		class «dpage.name.toFirstUpper»Controller extends FormController
		{
		    public function __construct()
		    {
		        «var IndexPage inPage = Slug.getPageForAll(dpage, com) »
		        «IF inPage !== null»
		        $this->view_list = '«Slug.getPageForAll(dpage, com).name.toLowerCase»';
		        «ELSE»
		        $this->view_list = '<Put the View Name>';
		        «ENDIF»
		        parent::__construct($config, $factory, $app, $input);
		    }
		    «IF dpage.haveFiletoLoad»
		    «backHelp.genAdminControllerSave()»
		    «ENDIF»
		}
	'''
	
	def CharSequence generateModelLoadFormDataFunction()'''	
		/**
		 * Method to get the data that should be injected in the form.
		 *
		 * @return  mixed  The data for the form.
		 * @since 1.6
		 * @generated
		 */
		protected function loadFormData()
		{
		    // Check the session for previously entered form data.
		    $data = Factory::getApplication()->getUserState('«Slug.nameExtensionBind("com",com.name.toLowerCase)».edit.«dpage.extendedEntityList.get(0).name.toLowerCase».data', array());

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
		 * @param   integer  The id of the primary key.
		 *
		 * @return  mixed   Object on success, false on failure.
		 * @since 1.6
		 * @generated
		 */
		public function getItem($pk = null)
		{
		    $app = Factory::getApplication();
		    $pk = (!empty($pk)) ? $pk : $app->input->getInt("«mainEntity.primaryKey.name»");
		    $table = $this->getTable('«dpage.entities.get(0).name.toFirstUpper»', 'Administrator');
		    if ($pk > 0) {
		        try {
		            // Attempt to load the row.
		            $table->load($pk);
		        } catch (Exception $e) {
		            // Check for a table object error.
		            throw new Exception('Database failure: No element found'. $e);
		        }
		    }

		    // Convert to the CMSObject before adding other data.
		    $properties = $table->getProperties(1);
		    $item =  ArrayHelper::toObject($properties);

		    if (property_exists($item, 'params')) {
		        $registry = new Registry;
		        if($item->params !== null) {
		            $registry->loadString($item->params);
		            $item->params = $registry->toArray();
		        }
		        else {
		            $item->params = array();
		        }
		    }

		    return $item;
		}
	'''

	def CharSequence generateModelGetFormFunction()'''
		/**
		 * Method to get the record form.
		 *
		 * @param  array  $data  An optional array of data for the form to interogate.
		 * @param  boolean  $loadData  True if the form is to load its own data (default case), false if not.
		 * @return  Form  A Form object on success, false on failure
		 * @since 1.6
		 * @generated
		 */
		public function getForm($data = array(), $loadData = true)
		{
		    // Get the form.
		    $form = $this->loadForm(
		        '«Slug.nameExtensionBind("com",com.name.toLowerCase)».«dpage.name.toLowerCase»',
		        '«dpage.extendedEntityList.get(0).name.toLowerCase»',
		        array('control' => 'jform', 'load_data' => $loadData)
		    );

		    if (empty($form)) {
		        return false;
		    }

		    return $form;
		}
	'''

	def CharSequence generateAdminModel()'''
		«generateFileDoc(dpage,com)»
		
		«Slug.generateNamespace(com.name, "Administrator", "Model")»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ModelAdmin", "ArrayHelper", "Registry", "Table", "Form", "Factory"))»
		use Joomla\Component\«com.name.toLowerCase»\Administrator\Helper\«com.name.toLowerCase.toFirstUpper»Helper;

		/**
		 * The model to schow the details of «dpage.name.toFirstUpper»
		 */
		class «dpage.name.toFirstUpper»Model extends AdminModel
		{
		    /**
		     * @var    string  The prefix to use with controller messages.
		     * @since  1.6
		    */
		    protected $text_prefix = '«Slug.nameExtensionBind("com".toUpperCase, com.name.toUpperCase)»';
		    «backHelp.generateAdminModelTableFunction()»
		    «generateModelGetFormFunction()»
		    «generateModelLoadFormDataFunction()»
		    «generateModelGetItemFunction()»
		    «IF mainEntity.allExtendedReferences.filter[t | t.upper.equalsIgnoreCase("-1")].size>0 || dpage.haveFiletoLoad»

		    «generateModelAdminSaveData()»

			«ENDIF»
		    «generateModelReferenceSave()»
		    «backHelp.generateAdminModelprepareTableFunction()»
		}
	'''
	
	/**
	 * Generate the code for the save-methode of a model in backend. 
	 */
	def CharSequence generateModelAdminSaveData() '''
		public function save($data)
		{
		    «IF dpage.haveFiletoLoad»
		    $files = Factory::getApplication()->input->files->get("jform", array(), 'array');
		    $item = $this->getItem();
		    if (isset($files) && count($files) > 0) {
		        $params = ComponentHelper::getParams('«Slug.nameExtensionBind("com",com.name).toLowerCase»');
		        foreach ($files as $keys => $file) {
		            if (!empty($file)) {
		                if (strpos($file['type'],"image")!==false) {
		                    $path = $params->get("«dpage.name.toLowerCase»_image_path");
		                } else {
		                    $path = $params->get("«dpage.name.toLowerCase»_file_path");
		                }
		                $data[$keys] = «this.com.componentHelperClassName»::uploadFiles($file,$path,$item->$keys);
		            }
		        }
		    }
		    «ENDIF»
		    «IF mainEntity.allExtendedReferences.filter[t | t.upper.equalsIgnoreCase("-1")].size>0»
		    $inputs = Factory::getApplication()->input->get("jform", array(), 'array');
		    if (parent::save($data)) {
		        if (empty($inputs["«mainEntity.primaryKey.name»"]) || $inputs["«mainEntity.primaryKey.name»"] == 0) {
		            $inputs["«mainEntity.primaryKey.name»"]= $this->getState($this->getName() . ".id");
		        }
		        «FOR ExtendedReference ref: dpage.extendedEntityList.get(0).allExtendedReferences»
		        «IF ref.upper.equalsIgnoreCase("*") || ref.upper.equalsIgnoreCase("-1")»
		        $this->set«ref.entity.name»($inputs);
		    	«ENDIF»
		    	«ENDFOR»
		    } else {
		        return false;
		    }
		    return true;
		    «ELSE»
		    return parent::save($data);        
		    «ENDIF»
		}
	'''

	def CharSequence generateModelReferenceSave()'''
	    «FOR ExtendedReference ref: dpage.extendedEntityList.get(0).allExtendedReferences»
	    «IF ref.upper.equalsIgnoreCase("*") || ref.upper.equalsIgnoreCase("-1")»
	    public function set«ref.entity.name»($inputs)
	    {
	        «var EList<Attribute> referenceAttr = Slug.getOtherAttribute(ref)»
	        «FOR Attribute attr : referenceAttr»
	        $«attr.name» = json_decode($inputs['«attr.name»']);
	        «ENDFOR»
	        $«ref.entity.name»_id = json_decode($inputs['«ref.entity.name»_id']);
	        «FOR ExtendedAttribute toAttr: ref.extendedAttributes»
	        $«toAttr.name»= $inputs['«toAttr.name»'];
	        «ENDFOR»
	        if («Slug.transformAttributeListInString("!empty($", referenceAttr ,"&&", ")")») {
	            if (!empty($«ref.entity.name»_id)) {
	                foreach ($«ref.entity.name»_id as $item) {
	                    if (intval($item) != 0) {
	                        $mappingTableDelete = $this->getTable("«ref.entity.name.toFirstLower»");
	                        $mappingTableDelete->delete($item);
	                    }
	                }
	            }
	            $mappingTable = $this->getTable("«ref.entity.name.toFirstLower»");
	            for ($index = 0; $index < count($«referenceAttr.get(0).name»); $index++) {
	                $dataToSave = array();
	                «FOR Attribute attr: referenceAttr»
	                $dataToSave["«attr.name»"] = $«attr.name»[$index];
	                «ENDFOR»
	                «FOR ExtendedAttribute toattr: ref.extendedAttributes»
	                $dataToSave["«ref.referencedExtendedAttributes.get(ref.extendedAttributes.indexOf(toattr)).name»"] = $«toattr.name»;
	                «ENDFOR»
	                $dataToSave["state"]=1;
	                $mappingTable->save($dataToSave);
	                $mappingTable->reset();
	            }
	        }
	    }
	    «ENDIF»
	    «ENDFOR»
	'''
	
	def generateAdminViewClass()'''
		«generateFileDoc(dpage,com)»
		
		«Slug.generateNamespace(com.name, "Administrator", "View\\" + dpage.name.toFirstUpper)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ViewLegacy", "Factory", "Text", "ToolbarHelper", "Html"))»
		
		/**
		 * View to edit a «dpage.name»
		 */
		class HtmlView extends BaseHtmlView
		{
		    protected $state;
		    protected $item;
		    protected $form;
		
		    «backHelp.generateAdminViewDisplay()»
		    «backHelp.generateAdminViewAddToolbar()»
		}
	'''
	
	def CharSequence generateAdminViewLayout()'''
		«generateFileDoc(dpage,com)»
		
		«Slug.generateUses(newArrayList("Text", "Route", "Factory", "Html"))»
		
		HTMLHelper::addIncludePath(JPATH_COMPONENT . '/Helper/html');
		HTMLHelper::_('behavior.tooltip');
		HTMLHelper::_('behavior.formvalidation');
		HTMLHelper::_('formbehavior.chosen', 'select');
		HTMLHelper::_('behavior.keepalive');
		
		// Import CSS
		$document = Factory::getDocument();
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
		        } else {
		            if (
		                task != '«dpage.name.toLowerCase».cancel'
		                && document.formvalidator.isValid(document.id('«dpage.name.toLowerCase»-form'))
		            ) {
		                Joomla.submitform(task, document.getElementById('«dpage.name.toLowerCase»-form'));
		            } else {
		                alert('<?php echo $this->escape(Text::_('JGLOBAL_VALIDATION_FORM_FAILED')); ?>');
		            }
		        }
		    }
		</script>
		«backHelp.generateAdminViewLayoutForm()»
	'''
	
	def CharSequence generateSiteController(Boolean isedit)'''
		«generateFileDoc(dpage,com)»
		
		«Slug.generateNamespace(com.name, "Site", "Controller")»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("Text", "Route", "Session", "Factory"))»
		
		/**
		 * «dpage.name.toFirstUpper» controller class to «if(isedit)  "Edit" else "Show"» a Item .
		 */
		class «if(isedit) dpage.name.toFirstUpper + "Edit" else dpage.name.toFirstUpper»Controller extends DisplayController
		{
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
		«generateFileDoc(dpage,com)»
		
		«Slug.generateNamespace(com.name, "Site", "Model")»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ModelItem", "Factory", "ArrayHelper", "Registry", "Table", "Text"))»
		
		jimport('joomla.event.dispatcher');
		
		/**
		 * Model to show a Dataitem
		 */
		class «dpage.name.toFirstUpper»Model extends ItemModel
		{
		    «frontHelp.generateSiteModelPopulatestate()»

		    «frontHelp.generateModelGetItemFunction»
		    
		    «frontHelp.generateModelGetDataFunction»

		    «frontHelp.generateSiteModelCheckin()»

		    «frontHelp.generateSiteModelgetTable»

		    «frontHelp.generateSiteModelCheckout()»

		    «frontHelp.generateSiteModelgetCategory()»

		    «frontHelp.generateSiteModelpublish()»

		    «frontHelp.generateSiteModelDelete()»
		}
	'''
	def CharSequence generateSiteModelEdit(String editPageName)'''
		«generateFileDoc(dpage,com)»
		
		«Slug.generateNamespace(com.name, "Site", "Model")»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ModelForm", "Factory", "ArrayHelper", "Registry", "Table", "Form", "Text"))»
		
		jimport('joomla.event.dispatcher');
		
		/**
		 * Model to Edit  a Dataitem
		 */
		class «editPageName.toFirstUpper»Model extends FormModel
		{
		    «frontHelp.generateSiteModelPopulatestate()»

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
	
	def CharSequence generateSiteView(Boolean isedit, String editPageName)'''
		«generateFileDoc(dpage,com)»
		
		«Slug.generateNamespace(com.name, "Site", "View\\" + if(isedit) editPageName.toFirstUpper else dpage.name.toFirstUpper)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("ViewLegacy", "Factory", "Text", "Html"))»
		
		/**
		 * View to « if(isedit) "Edit" else "Show"» «dpage.extendedEntityList.get(0).name»
		 */
		class HtmlView extends BaseHtmlView
		{
		    protected $state;
		    protected $item;
		    protected $form;
		    protected $params;
		    «frontHelp.generateSiteViewDisplay(isedit, editPageName)»
		    «frontHelp.generateSiteViewprepareDocument()»
		}
	'''
	def CharSequence generateSiteViewLayoutEdit(String editPageName)'''
		«generateFileDoc(dpage,com)»
		
		«Slug.generateUses(newArrayList("Text", "Html", "Factory", "Route"))»
		
		HTMLHelper::_('behavior.keepalive');
		HTMLHelper::_('behavior.tooltip');
		HTMLHelper::_('behavior.formvalidation');
		HTMLHelper::_('formbehavior.chosen', 'select');
		
		//Load admin language file
		$lang = Factory::getLanguage();
		$lang->load('«Slug.nameExtensionBind("com", com.name).toLowerCase»', JPATH_ADMINISTRATOR);
		$doc = Factory::getDocument();
		
		?>
		
		<div class="«dpage.name.toLowerCase»-edit front-end-edit">
		    <?php if (!empty($this->item->«mainEntity.primaryKey.name»)) : ?>
		        <h1>Edit <?php echo $this->item->«mainEntity.primaryKey.name»; ?></h1>
		    <?php else : ?>
		        <h1>Add</h1>
		    <?php endif; ?>
		    «frontHelp.generateSiteViewLayoutEditForm(editPageName)»
	'''
	def CharSequence generateSiteViewLayoutShow(String editPageName)'''
		«generateFileDoc(dpage,com)»
		
		«Slug.generateUses(newArrayList("Factory", "ComponentHelper", "Uri", "Text", "Route"))»
		
		//Load admin language file
		$lang = Factory::getLanguage();
		$lang->load('«Slug.nameExtensionBind("com", com.name).toLowerCase»', JPATH_ADMINISTRATOR);
		$params = ComponentHelper::getParams('«com.extensionName»');
		$image_path = $params->get('«dpage.name»_image_path');
		$file_path = $params->get('«dpage.name»_file_path');
		$iconpath = Uri::root() . 'media/media/images/mime-icon-32/';
		$canEdit = Factory::getUser()->authorise('core.edit', '«Slug.nameExtensionBind("com", com.name).toLowerCase».' . $this->item->«mainEntity.primaryKey.name»);
		if (!$canEdit && Factory::getUser()->authorise('core.edit.own', '«Slug.nameExtensionBind("com", com.name).toLowerCase»' . $this->item->«mainEntity.primaryKey.name»)) {
		    $canEdit = Factory::getUser()->id == $this->item->created_by;
		}
		?>
		«frontHelp.generateSiteViewLayoutShow(editPageName)»
	'''
}
