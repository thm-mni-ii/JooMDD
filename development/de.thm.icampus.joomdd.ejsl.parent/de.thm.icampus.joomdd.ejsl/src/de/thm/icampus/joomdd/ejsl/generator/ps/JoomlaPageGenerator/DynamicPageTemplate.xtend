/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import org.eclipse.emf.common.util.EList

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
public abstract class DynamicPageTemplate extends AbstractPageGenerator {
	
    
    def CharSequence xmlSiteTemplateContent(String pagename, ExtendedDynamicPage page, ExtendedComponent component) '''
<?xml version="1.0" encoding="utf-8"?>
<metadata>
    <layout title="«Slug.nameExtensionBind("com", component.name).toUpperCase»_VIEW_«pagename.toUpperCase»_TITLE" option="View">
        <message><![CDATA[«Slug.nameExtensionBind("com", component.name).toUpperCase»_VIEW_«pagename.toUpperCase»_DESC]]></message>
    </layout>
    <fields name="params">
    «IF page.instance instanceof IndexPage»
     «genSettingForIndexPage(pagename,page,component)»
     «ELSE»
     «genSettingForDetailsPage(pagename,page,component)»
	«ENDIF»

  <fieldset name="local" label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_«page.name.toUpperCase»_PARAMS_LOCAL__LABEL">
	 «generateParameter(page.extendedLocalParametersListe, component)»
	</fieldset>
	 <fieldset name="global" label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_«page.name.toUpperCase»_PARAMS_GLOBAL__LABEL">
	«generateParameter(page.extendedGlobalParametersListe, component)»
   </fieldset>
    «FOR ExtendedParameterGroup e : page.extendedParametersGroupsListe »
    <fieldset name="«e.name.toLowerCase»"  label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FIELDSET_«page.name.toUpperCase»_«e.name.toUpperCase»" 
    «generateParameter(e.extendedParameterList, component)»
    «generateParameter(e.extendedParameterList,component)»
    </fieldset>
    «ENDFOR»
   
</fields>
</metadata>
    '''
    //todo Put the Settings for Details Page here
    def CharSequence genSettingForDetailsPage(String pagename, ExtendedDynamicPage page, ExtendedComponent component)'''

	<fieldset name="request"
		addfieldpath="/administrator/components/«Slug.nameExtensionBind("com", component.name).toLowerCase»/models/fields">

		<field name="id" type="«page.extendedEntityList.get(0).name.toLowerCase»"
			label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FILTER_«page.extendedEntityList.get(0).name.toUpperCase»_«page.extendedEntityList.get(0).extendedAttributeList.get(0).name.toUpperCase»"
			required="true"
			edit="true"
			clear="false"
		description="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FILTER_«page.extendedEntityList.get(0).name.toUpperCase»_«page.extendedEntityList.get(0).extendedAttributeList.get(0).name.toUpperCase»_DESC"
       valueColumn="id"
       textColumn="«page.extendedEntityList.get(0).extendedAttributeList.get(0).name.toLowerCase»"
      >
  <option value="">JOPTION_SELECT_«page.extendedEntityList.get(0).extendedAttributeList.get(0).name.toUpperCase»</option>
  </field>
		</fieldset>
	
'''
def CharSequence genSettingForIndexPage(String pagename, ExtendedDynamicPage page, ExtendedComponent component)'''
 
   <fieldset name="basic" label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_«page.name.toUpperCase»_ORDERING_LABEL">
   <field name="template_layout" type="list"
           label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_TEMPLATE_LAYOUT"
       description="«Slug.nameExtensionBind("com", component.name).toUpperCase»_TEMPLATE_LAYOUT_DESC"
       class="inputbox"
       size="1"
       default="1">
       <option value="2">JTEMPLATE_LAYOUT_TABLE</option>
       <option value="1">JTEMPLATE_LAYOUT_LIST</option>
	</field> 
	   «IF page != null»
	   <field name="ordering" type="list"
   	        label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_ORDERING"
   	        description="«Slug.nameExtensionBind("com",component.name).toUpperCase»_JFIELD_ORDERING_DESC"
   	        class="inputbox"
   	        default="id">
   	      <option value="id">ID</option>  
   	   «FOR ExtendedAttribute attr: page.extendFiltersList»
	     <option value="«attr.name.toLowerCase»">«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«Slug.slugify(page.extendedEntityList.get(0).name).toUpperCase»_«attr.name.toUpperCase»</option>
        «ENDFOR»
	    </field>
	    «ENDIF»
	    <field name="direction" type="list"
   	        label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_DIRECTION"
   	        description="«Slug.nameExtensionBind("com",component.name).toUpperCase»_JFIELD_DIRECTION_DESC"
   	        class="inputbox"
   	        size="1"
   	        default="ASC">
   	        <option value="ASC">«Slug.nameExtensionBind("com",component.name).toUpperCase»_DIRECTION_ASC</option>
   	        <option value="DESC">«Slug.nameExtensionBind("com",component.name).toUpperCase»_DIRECTION_DESC</option>
   	     </field>
	    		   	        
	   <field
			name="start"
			type="int"
			default="0"
			label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_START_LABEL"
			description="«Slug.nameExtensionBind("com",component.name).toUpperCase»_START_DESC" />
		 <field
			name="limit"
			type="int"
			default="10"
			label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_LIMIT_LABEL"
			description="«Slug.nameExtensionBind("com",component.name).toUpperCase»_LIMIT_DESC" />
		 <field
			name="search"
			type="text"
			label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_SEARCH_LABEL"
			description="«Slug.nameExtensionBind("com",component.name).toUpperCase»_SEARCH_DESC" />
	   
	    <field name="state" type="list"
	   	        label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_JSTATUS"
	   	        description="«Slug.nameExtensionBind("com",component.name).toUpperCase»_JFIELD_PUBLISHED_DESC"
	   	        class="inputbox"
	   	        size="1"
	   	        default="1">
	   	        <option value="1">JPUBLISHED</option>
	   	        <option value="0">JUNPUBLISHED</option>
	   	        <option value="2">JARCHIVED</option>
	   	        <option value="-2">JTRASHED</option>
	   	    </field>
	</fieldset>

	«IF component != null && page !=null»
	
	<fieldset name="filter"
				label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_«page.name.toUpperCase»_FILTER_LABEL">
	 <field
      name="created_by"
       addfieldpath="administrator/components/«Slug.nameExtensionBind("com",component.name).toLowerCase»/models/fields"
      type="«component.name.toLowerCase»user"
      label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FILTER_CREATED_BY"
      description="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FILTER_CREATED_BY"
       entity = "«page.extendedEntityList.get(0).name.toLowerCase»"
      >
      <option value="">JOPTION_SELECT_CREATED_BY</option>
      </field>
    «FOR ExtendedAttribute attr : page.extendFiltersList»
     <field
        addfieldpath="administrator/components/«Slug.nameExtensionBind("com",component.name).toLowerCase»/models/fields"
          name="«attr.name»"
          type="«page.extendedEntityList.get(0).name.toLowerCase»"
          label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FILTER_«page.name.toUpperCase»_«attr.name.toUpperCase»"
          description="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FILTER_«page.name.toUpperCase»_«attr.name.toUpperCase»"
           valueColumn="«attr.name.toLowerCase»"
           textColumn="«attr.name.toLowerCase»"
          >
      <option value="">JOPTION_SELECT_«attr.name.toUpperCase»</option>
  </field>
 «ENDFOR»
  </fieldset>

	«ENDIF»
		
    '''
		override  CharSequence generateTemplate(ExtendedDynamicPage page, ExtendedComponent component) '''
		
		'''
		override CharSequence generateParameter(EList<ExtendedParameter>listParams, ExtendedComponent component)'''
		«FOR param : listParams»
		«Slug.writeParameter(param)»
		«ENDFOR»
		'''
		def CharSequence xmlAdminFields(ExtendedDynamicPage page, ExtendedComponent component, String name) '''
		<?xml version="1.0" encoding="utf-8"?>
		<form>
			<field name="id" type="hidden" default="0" label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FORM_LBL_NONE_ID"
				readonly="true" class="readonly"
				description="JGLOBAL_FIELD_ID_DESC" /> 

				<field name="created_by" type="hidden" default="" 
				label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FORM_LBL_NONE_CREATED_BY"
				description="«Slug.nameExtensionBind("com", component.name).toUpperCase»_FORM_LBL_NONE_CREATED_BY"  /> 
					
					«FOR ExtendedEntity e : page.extendedEntityList»
					«FOR ExtendedAttribute attr : e.extendedAttributeList»
					«writeAttribute(e,attr,component,page)»
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
					
				 <fields name="params">
			  <fieldset name="local" label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_«page.name.toUpperCase»_PARAMS_LOCAL__LABEL">
	        	 «generateParameter(page.extendedLocalParametersListe, component)»
	        	</fieldset>
        	 <fieldset name="global" label="«Slug.nameExtensionBind("com", component.name).toUpperCase»_«page.name.toUpperCase»_PARAMS_GLOBAL__LABEL">
	        	«generateParameter(page.extendedGlobalParametersListe, component)»
	           </fieldset>
	            «FOR ExtendedParameterGroup e : page.extendedParametersGroupsListe »
            <fieldset name="«e.name.toLowerCase»"  label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FIELDSET_«page.name.toUpperCase»_«e.name.toUpperCase»" 
	            «generateParameter(e.extendedParameterList, component)»
	            «generateParameter(e.extendedParameterList,component)»
            </fieldset>
	            «ENDFOR»
				</fields>
			
			    <fields>
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
			 </fields>
		</form>
   		 '''
   	def CharSequence writeAttribute(ExtendedEntity entity,ExtendedAttribute attr, ExtendedComponent component, ExtendedDynamicPage page){
   	 
   	 var String type = getHtmlTypeOfAttribute(page,attr,entity,component)
   	 var StringBuffer result = new StringBuffer
   	  var ExtendedDetailPageField field =  Slug.getEditedFieldsForattribute(page, attr)
   	 switch(type){
   	 	case "multiselect" , case "select":{
   	 		result.append(''' 
 		  	 <field name="«attr.name.toLowerCase»"
 		  	  type="list" 
 		  	  «IF type.equalsIgnoreCase("multiselect")»
 		  	  multiple
 		  	  «ENDIF»
 		  	 id="«attr.name.toLowerCase»"
 		     label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«entity.name.toUpperCase»_«attr.name.toUpperCase»"
 		  	 description="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«entity.name.toUpperCase»_«attr.name.toUpperCase»_DESC"
 		  	>
 		  	«FOR KeyValuePair kv: field.extrasKeyValue»
 		  	<option value="«kv.value»">«page.name.toUpperCase»_«attr.name.toUpperCase»_«kv.name.toUpperCase»_OPTION</option>
 		  	«ENDFOR»
 		  	</field> 
   	 		''')
   	 		
   	 	}
   	 	case "checkbox":{
   	 		  result.append(''' 
 		  	 <field name="«attr.name.toLowerCase»"
 		  	  type="checkboxes" 
 		  	 id="«attr.name.toLowerCase»"
 		     label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«entity.name.toUpperCase»_«attr.name.toUpperCase»"
 		  	 description="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«entity.name.toUpperCase»_«attr.name.toUpperCase»_DESC"
 		  	>
 		  	«FOR KeyValuePair kv: field.extrasKeyValue»
 		  	<option value="«kv.value»">«page.name.toUpperCase»_«attr.name.toUpperCase»_«kv.name.toUpperCase»_OPTION</option>
 		  	«ENDFOR»
 		  	</field> 
   	 		''')
   	 	}
   	 	case "radiobutton":{
   	 			result.append(''' 
   	 		  	 <field name="«attr.name.toLowerCase»"
   	 		  	  type="radio" 
   	 		  	 id="«attr.name.toLowerCase»"
   	 		     label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«entity.name.toUpperCase»_«attr.name.toUpperCase»"
   	 		  	 description="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«entity.name.toUpperCase»_«attr.name.toUpperCase»_DESC"
   	 		  	>
   	 		  	«FOR KeyValuePair kv: field.extrasKeyValue»
   	 		  	<option value="«kv.value»">«page.name.toUpperCase»_«attr.name.toUpperCase»_«kv.name.toUpperCase»_OPTION</option>
   	 		  	«ENDFOR»
   	 		  	</field> 
   	 		''')
   	 		
   	 	}
   	 	case"Yes_No_Buttons":{
   	 		result.append(''' 
   	 		  	 <field name="«attr.name.toLowerCase»"
   	 		  	  type="radio" 
   	 		  	 id="«attr.name.toLowerCase»"
   	 		     label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«entity.name.toUpperCase»_«attr.name.toUpperCase»"
   	 		  	 description="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«entity.name.toUpperCase»_«attr.name.toUpperCase»_DESC"
   	 		  	 default="0"
   	 		  	>
   	 		  	<option value="1">JYES</option>
 				<option value="0">JNO</option>
   	 		  	</field> 
   	 		''')
   	 	}
   	 	default:{
   	 		result.append('''  
   	 		 <field name="«attr.name.toLowerCase»"
   	 		 «type»
   	 		  id="«attr.name.toLowerCase»"
   	 		  label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«entity.name.toUpperCase»_«attr.name.toUpperCase»"
   	 		  description="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FORM_LBL_«entity.name.toUpperCase»_«attr.name.toUpperCase»_DESC"
   	 		/> 
   	 		''');
   	 	}
   	 }
   	 
   	 return result.toString
   	}
   
   		 
   public def String getHtmlTypeOfAttribute(ExtendedDynamicPage dynP,ExtendedAttribute attr, ExtendedEntity en,ExtendedComponent com){
   		var StringBuffer buff = new StringBuffer
   		
   			for(ExtendedReference ref: en.extendedReference.filter[t | t.upper.equalsIgnoreCase("1")]){
   			if(ref.extendedAttribute.get(0).name.equalsIgnoreCase(attr.name)){
   				buff.append('''type ="«en.name + "to" +ref.entity.name»«en.extendedReference.indexOf(ref)»"''')
   				return buff.toString
   			}
   		}
   		if(!dynP.extendedEditedFieldsList.empty){
   			  var ExtendedDetailPageField field =  Slug.getEditedFieldsForattribute(dynP, attr)
   				    if(field != null && field.type != null)
   					return Slug.getTypeName(field.type, field.extendedAttribute)
   						
   		}
   	
   		buff.append('''type ="hidden"''')
   		return buff.toString;
   	}
	

} // DynamicPage
