/**
 */
package de.thm.icampus.ejsl.generator;

import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.DynamicPage
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.eJSL.Parameter

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Page</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getDynamicPage()
 * @model
 * @generated
 */
public class DynamicPageTemplate extends AbstractPageGenerator {
	
	
	
	
	def CharSequence generateFileDoc(Page page, Component component)'''
	<?php
		
		* @version v0.0.1
		* @category Joomla component
		* @package «(component.eContainer as Package).name»
		* @subpackage com_«Slug.slugify(component.name)».«if(page.eContainer instanceof BackendSection) "admin" else "site"»
		* @name «component.name»View
		«FOR author : component.manifest.authors»
			* @author «author.name», <«author.authoremail»>
		«ENDFOR»
		* @copyright «component.manifest.copyright»
		* @license «component.manifest.license»
		
	'''

	def  CharSequence generateView(Page page, Component component) '''
		<?php
		/**
		«generateFileDoc(page,component)»
		* @description «(page as DynamicPage).entities.get(0).name»View for «component.name»
		*/
		defined('_JEXEC') or die('Restricted access');
		jimport('joomla.application.component.view');
		jimport('joomla.filesystem.path');
		/**
		* «component.name»View class for component com_«Slug.slugify(component.name)»
		*
		* @category Joomla.Component.«if(page.eContainer instanceof BackendSection) "Admin" else "Site"»
		* @package com_«Slug.slugify(component.name)».«if(page.eContainer instanceof BackendSection) "admin" else "site"»
		* @since Class available since Release x.x.x
		*/
		class «component.name»View«(page as DynamicPage).entities.get(0).name» extends JViewLegacy
		{
		/**
		* @var JObject A cache for the available actions.
		* @since 1.6
		*/
		protected static $actions;
		/**
		* Method to get display
		*
		* @param Object $tpl template
		*
		* @return void
		*/
		public function display($tpl = null)
		{
		$uid = JRequest::getVar('id');
		$data = new ArrayObject();
		$model = $this->getModel();
		$data = $model->getData($uid);
		$this->assignRef('data', $data);
		parent::display($tpl);
		}
		}
	'''

def static CharSequence generateController(Page page, Component component) '''
		<?php
		/**
		* @version v0.0.1
		* @category Joomla component
		* @package «(component.eContainer as Package).name»
		* @subpackage com_«Slug.slugify(component.name)».«if(page.eContainer instanceof BackendSection) "admin" else "site"»
		* @name THMSocialNetworkViewController«(page as DynamicPage).entities.get(0).name»
		* @description «(page as DynamicPage).entities.get(0).name»Controller for THM Social Network
		«FOR author : component.manifest.authors»
			* @author «author.name», <«author.authoremail»>
		«ENDFOR»
		* @copyright «component.manifest.copyright»
		* @license «component.manifest.license»
		*/
		defined('_JEXEC') or die();
		jimport('joomla.application.component.controller');
		/**
		* «component.name»Controller class for component com_«Slug.slugify(component.name)»
		*
		* @category Joomla.Component.Admin
		* @package com_«Slug.slugify(component.name)».«if(page.eContainer instanceof BackendSection) "admin" else "site"»
		* @since Class available since Release x.x.x
		*/
		class «component.name»Controller«(page as DynamicPage).entities.get(0).name» extends JControllerLegacy
		{
		/**
		* constructor (registers additional tasks to methods)
		*
		* @generated
		*/
		public function __construct()
		{
			parent::__construct();
			$this->registerTask('publish', '');
			$this->registerTask('unpublish', '');;
		}
		}
	'''
    
    def CharSequence xmlSiteTemplateContent(Page page,Component component, String name) '''
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
                	«generateParameter(page.globalparameters)»
                    «generateParameter(page.localparameters)»
                </fieldset>
            </fields>
        </metadata>
    '''
		def  CharSequence generateTemplate(Page page, Component component) '''
		'''
		def CharSequence generateParameter(EList<Parameter>listParams)'''
		«FOR param : listParams»
		 <field
		 name="«param.name»"
		 type="«getTypeName(param.dtype)»"
		 label="«param.label »"
		 description="«param.descripton»"
		 />
		«ENDFOR»
		'''
	
	override getLinkClient() {
	}

	override setLinkClient(LinkGeneratorClient value) {
	}

	override generatePage() {
	}

} // DynamicPage
