/**
 */
package de.thm.icampus.ejsl.generator.pages;

import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.DynamicPage
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.eJSL.Parameter
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.ParameterGroup
import de.thm.icampus.ejsl.generator.util.Slug
import de.thm.icampus.ejsl.eJSL.Reference

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
            <layout title="«Slug.nameExtensionBind("com", component.name).toUpperCase»_VIEW_«page.name.toUpperCase»_TITLE" option="View">
                <message><![CDATA[«Slug.nameExtensionBind("com", component.name).toUpperCase»_VIEW_«page.name.toUpperCase»_DESC]]></message>
            </layout>
            <fields
                name="request"
                addfieldpath="administrator/components/«name»/models/fields"
            >
                <fieldset name="request">
                	«generateParameter(page.globalparameters, component)»
                    «generateParameter(page.localparameters, component)»
                    «FOR ParameterGroup e : page.parametergroups »
                    «generateParameter(e.globalparameters, component)»
                    «generateParameter(e.parameters,component)»
                    «ENDFOR»
                </fieldset>
            </fields>
        </metadata>
    '''
		def  CharSequence generateTemplate(Page page, Component component) '''
		'''
		def CharSequence generateParameter(EList<Parameter>listParams, Component component)'''
		«FOR param : listParams»
		 <field
		 name="«param.name»"
		 type="«getTypeName(param.dtype)»"
		 label="«Slug.generateKeysName(component, param.label) »"
		 description="«Slug.generateKeysName(component,param.descripton)»"
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
					 type="«getHtmlTypeOfAttribute(attr,e,component).toLowerCase»" 
					 label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«e.name.toUpperCase»_«attr.name.toUpperCase»"
					 /> 
					«ENDFOR»
					«ENDFOR»
					   <field name="state" type="list"
					        label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_JSTATUS"
					        description="«Slug.nameExtensionBind("com", component.name).toUpperCase»_JFIELD_PUBLISHED_DESC"
					        class="inputbox"
					        size="1"
					        default="1">
					        <option value="1">JPUBLISHED</option>
					        <option value="0">JUNPUBLISHED</option>
					        <option value="2">JARCHIVED</option>
					        <option value="-2">JTRASHED</option>
					    </field> 
         <field name="published" type="hidden" filter="unset" />
		<field name="checked_out" type="hidden" filter="unset" />
        <field name="checked_out_time" type="hidden" filter="unset" /> 
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
   		 
   	def String getHtmlTypeOfAttribute(Attribute attr, Entity en,Component com){
   		
   		for(Reference ref: en.references){
   			if(ref.attribute.equals(attr)){
   				return en.name + "To" +ref.entity.name
   			}
   		}
   		
   		return Slug.getTypeName(attr.htmltype);
   	}
	
	override getLinkClient() {
	}



	override generatePage() {
	}
	
	override setLinkClient(LinkGeneratorClient value) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

} // DynamicPage
