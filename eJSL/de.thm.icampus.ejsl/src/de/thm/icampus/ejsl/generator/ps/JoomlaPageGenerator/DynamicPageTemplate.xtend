/**
 */
package de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator;

import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.DynamicPage
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.eJSL.Parameter
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.ParameterGroup
import de.thm.icampus.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.ejsl.eJSL.Reference
import de.thm.icampus.ejsl.eJSL.IndexPage
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedReference

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
	
	
	
	
	def CharSequence generateFileDoc(DynamicPage page, ExtendedComponent component, boolean denied)'''
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
		*/
		«IF denied»
		defined('_JEXEC') or die('Restricted access');
		«ENDIF»
		
	'''


    
    def CharSequence xmlSiteTemplateContent(String pagename, ExtendedDynamicPage page, ExtendedComponent component) '''
        <?xml version="1.0" encoding="utf-8"?>
        <metadata>
            <layout title="«Slug.nameExtensionBind("com", component.name).toUpperCase»_VIEW_«pagename.toUpperCase»_TITLE" option="View">
                <message><![CDATA[«Slug.nameExtensionBind("com", component.name).toUpperCase»_VIEW_«pagename.toUpperCase»_DESC]]></message>
            </layout>
            <fields
                name="request"
                addfieldpath="administrator/components/«Slug.nameExtensionBind("com", component.name).toLowerCase»/models/fields"
            >
           
            «IF page instanceof IndexPage»
             <fieldset name="request">
                <field name="template_layout" type="list"
                label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_TEMPLATE_LAYOUT"
		        description="«Slug.nameExtensionBind("com", component.name).toUpperCase»_TEMPLATE_LAYOUT_DESC"
		        class="inputbox"
		        size="1"
		        default="1">
		        <option value="1">JTEMPLATE_LAYOUT_LIST</option>
		        <option value="2">JTEMPLATE_LAYOUT_TABLE</option>
				</field> 
				</fieldset>
			«ENDIF»
            	«generateParameter(page.extendedGlobalParametersListe, component)»
                «generateParameter(page.extendedLocalParametersListe, component)»
                «FOR ExtendedParameterGroup e : page.extendedParametersGroupsListe »
                <fieldset name="«e.name.toLowerCase»"  label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_«page.name.toUpperCase»_«e.name.toUpperCase»" 
                «generateParameter(e.extendedParameterList, component)»
                «generateParameter(e.extendedParameterList,component)»
                </fieldset>
                «ENDFOR»
               
            </fields>
        </metadata>
    '''
		def  CharSequence generateTemplate(ExtendedDynamicPage page, ExtendedComponent component) '''
		'''
		def CharSequence generateParameter(EList<ExtendedParameter>listParams, ExtendedComponent component)'''
		«FOR param : listParams»
		 <field
		 name="«param.name»"
		 type="«Slug.getTypeName(param.dtype)»"
		 label="«Slug.generateKeysName(component, param.label) »"
		 description="«Slug.generateKeysName(component,param.descripton)»"
		 />
		«ENDFOR»
		'''
		def CharSequence xmlAdminFields(ExtendedDynamicPage page, ExtendedComponent component, String name) '''
				<?xml version="1.0" encoding="utf-8"?>
				<form>
					<fieldset>
					  <field name="id" type="text" default="0" label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FORM_LBL_NONE_ID"
				readonly="true" class="readonly"
				description="JGLOBAL_FIELD_ID_DESC" /> 

				<field name="created_by" type="hidden" default="" 
				label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FORM_LBL_NONE_CREATED_BY"
				description="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FORM_LBL_NONE_CREATED_BY"  /> 
					
					«FOR ExtendedEntity e : page.extendedEntityList»
					«FOR ExtendedAttribute attr : e.extendedAttributeList»
					 <field name="«attr.name.toLowerCase»" id="«attr.name.toLowerCase»"
					«getHtmlTypeOfAttribute(page,attr,e,component)»
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
   		 
   	def CharSequence getHtmlTypeOfAttribute(ExtendedDynamicPage dynP,ExtendedAttribute attr, ExtendedEntity en,ExtendedComponent com){
   		var StringBuffer buff = new StringBuffer
   		
   			for(ExtendedReference ref: en.extendedReference){
   			if(ref.extendedAttribute.get(0).name.equalsIgnoreCase(attr.name)){
   				buff.append('''type ="«en.name + "to" +ref.entity.name»«en.extendedReference.indexOf(ref)»"''')
   				return buff.toString
   			}
   		}
   		if(!dynP.extendedEditedFieldsList.empty){
   			for(ExtendedDetailPageField field:dynP.extendedEditedFieldsList ){
   				if(field.extendedAttribute.name.equalsIgnoreCase(attr.name)){
   					
   					
   					return Slug.getTypeName(field.type, field.extendedAttribute)
   				}
   			}
   		}
   	
   		buff.append('''type ="hidden"''')
   		return buff.toString;
   	}
	
	override getLinkClient() {
	}



	override generatePage() {
	}
	
	override setLinkClient(LinkGeneratorClient value) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

} // DynamicPage
