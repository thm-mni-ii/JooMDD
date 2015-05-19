package de.thm.icampus.ejsl.generator

import de.thm.icampus.ejsl.eJSL.DetailsPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.Section

class DetailsPageTemplate extends DynamicPageTemplate {
	
	private DetailsPage dpage
	private Component  com
	private Section sec
	private DetailsPageTemplateBackendHelper backHelp
	private DetailsPageTemplateFrontEndHelper frontHelp
	
	new(DetailsPage dp, Component cp, Section section){
		
		dpage = dp
		com = cp
		sec = section
		backHelp = new DetailsPageTemplateBackendHelper(dpage, com, sec)
		frontHelp = new DetailsPageTemplateFrontEndHelper(dpage, com, sec)
	}
	
	def CharSequence generateAdminController()'''
	
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
	def CharSequence generateAdminModel()'''
	
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
		«backHelp.generateAdminModelFormFunction()»
		«backHelp.generateAdminModelLoadFormDataFunction()»
		«backHelp.generateAdminModelGetItemFunction()»
		«backHelp.generateAdminModelprepareTableFunction()»
	}
		'''

	
	
	def generateAdminViewClass()'''
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
	jimport('joomla.application.component.modelitem');
	jimport('joomla.event.dispatcher');
	
	/**
	 * Model to show a Dataitem
	 */
	class «com.name.toFirstUpper»Model«dpage.name.toFirstUpper» extends JModelItem {
		
	}
	'''
	def CharSequence generateSiteModelEdit()'''
	jimport('joomla.application.component.modelform');
	jimport('joomla.event.dispatcher');
	
	/**
	 * Model to Edit  a Dataitem
	 */
	class «com.name.toFirstUpper»Model«dpage.name.toFirstUpper»Edit extends JModelForm {
		var $_item = null;
	}
	'''
	
}