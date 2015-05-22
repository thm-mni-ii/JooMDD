/**
 */
package de.thm.icampus.ejsl.generator;

import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.DynamicPage
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.eJSL.Parameter
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.Attribute

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
	
	
	
	
	def CharSequence generateFileDoc(Page page, Component component, boolean denied)'''
	<?php
		/**
		* @version v0.0.1
		* @category Joomla component
		* @subpackage com_«Slug.slugify(component.name)».«if(page.eContainer instanceof BackendSection) "admin" else "site"»
		* @name «component.name»View
		«FOR author : component.manifest.authors»
			* @author «author.name», <«author.authoremail»>
		«ENDFOR»
		* @copyright «component.manifest.copyright»
		* @license «component.manifest.license»
		
		«IF denied»
		defined('_JEXEC') or die('Restricted access');
		«ENDIF»
		*/
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
		def CharSequence xmlAdminFields(DynamicPage page,Component component, String name) '''
				<?xml version="1.0" encoding="utf-8"?>
				<form>
					<fieldset>
					  <field name="id" type="text" default="0" label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FORM_LBL_NONE_ID"
				readonly="true" class="readonly"
				description="JGLOBAL_FIELD_ID_DESC" /> 

				<field name="created_by" type="createdby" default="" 
				label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FORM_LBL_NONE_CREATED_BY"
				description="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FORM_LBL_NONE_CREATED_BY"  /> 
					
					«FOR Entity e : page.entities»
					«FOR Attribute attr : e.attributes»
					 <field name="«attr.name.toLowerCase»" 
					 type="«Slug.getTypeName(attr.htmltype).toLowerCase»" 
					 label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«page.name.toUpperCase»_«attr.name.toUpperCase»"
					 /> 
					«ENDFOR»
					«ENDFOR»
					</fieldset> 

					 <fieldset name="accesscontrol">
					<field name="asset_id" type="hidden" filter="unset" />
					<field name="rules"
					type="rules"
					label="JFIELD_RULES_LABEL"
					translate_label="false"
					filter="rules"
					validate="rules"
					class="inputbox"
					component="«Slug.nameExtensionBind("com",component.name).toLowerCase»"
					section="«page.name.toLowerCase»"
					/>

				</fieldset>
				</form>
   		 '''
	
	override getLinkClient() {
	}

	override setLinkClient(LinkGeneratorClient value) {
	}

	override generatePage() {
	}

} // DynamicPage
