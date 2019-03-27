package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaPageGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.StaticLanguage
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes
import de.thm.icampus.joomdd.ejsl.generator.pi.util.MappingEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.util.FKAttribute

/**
 * This class contains the templates to generate the necessary code for views.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
public abstract class DynamicPageTemplate extends AbstractPageGenerator {

    /**
     * Generate the manifest file for the menu item of a component. 
     * @param String 			   pagename  contains the name of a page
     * @param ExtendedDynamicPage page		 contains the extended instance of a dynamicpage
     * @param ExtendedComponent   component contains the compoenent name
     */
    def CharSequence xmlSiteTemplateContent(String pagename, ExtendedDynamicPage page, ExtendedComponent component) '''
        <?xml version="1.0" encoding="utf-8"?>
        <metadata>
            <layout title="«Slug.addLanguage(component.languages, newArrayList("com", component.name, "VIEW", pagename, "TITLE"), pagename)»" option="View">
                <message><![CDATA[«Slug.addLanguage(component.languages, newArrayList("com", component.name, "VIEW", pagename, "DESC"), StaticLanguage.getCommonDescriptionFor(pagename))»]]></message>
            </layout>	
        	 «IF !page.containsParamertergroup("params") »
        	     <fields name="params">
        	       «genSettingForIndexPage(pagename,page,component)»
        	 «ELSE»
        	     <fields name="request">
        	     «genSettingForDetailsPage(pagename,page,component)»
        	     </fields>
        	     <fields name="params">
        	 «ENDIF»
        	       «IF page.extendedLocalParametersListe.length>0 && !page.containsParamertergroup("local") »
        	           <fieldset name="local" label="«Slug.addLanguage(component.languages, newArrayList("com", component.name, page.name), StaticLanguage.PARAMS_LOCAL_LABEL)»">
        	               «generateParameter(page.extendedLocalParametersListe, component)»
        	           </fieldset>
        	       «ENDIF»
        	       «IF page.extendedGlobalParametersListe.length>0 &&  !page.containsParamertergroup("global")»
        	           <fieldset name="global" label="«Slug.addLanguage(component.languages, newArrayList("com", component.name, page.name), StaticLanguage.PARAMS_GLOBAL_LABEL)»">
        	               «generateParameter(page.extendedGlobalParametersListe, component)»
        	           </fieldset>
        	       «ENDIF»
        	       «FOR ExtendedParameterGroup e : page.extendedParametersGroupsListe»
        	           <fieldset name="«e.name.toLowerCase»"  label="«Slug.addLanguage(component.languages, newArrayList("com", component.name, "FIELDSET", page.name, e.name), e.name)»"> 
        	               «generateParameter(e.extendedParameterList, component)»
        	           </fieldset>
        	       «ENDFOR»
            </fields>
        </metadata>
    	     '''

    /**
     * Generate the setting parameters for the configuration of a menu item. The admin can select an item.
     * 
     * @param String              pagename  contains the page name
     * @param ExtendedDynamicPage page      contains the extended instance of a dynamicpage
     * @parem ExtendedComponent   component contains the instance of a component
     * 
     */
    def CharSequence genSettingForDetailsPage(String pagename, ExtendedDynamicPage page,
        ExtendedComponent component) '''
        <fieldset name="request"
            addfieldpath="/components/«Slug.nameExtensionBind("com", component.name).toLowerCase»/models/fields">
            <field name="«page.extendedEntityList.get(0).primaryKey.name»" type="«page.extendedEntityList.get(0).name»"
                label="«Slug.addLanguage(component.languages, newArrayList("com", component.name, "FILTER", page.extendedEntityList.get(0).name, page.extendedEntityList.get(0).ownExtendedAttributes.get(0).name, "LABEL"), page.extendedEntityList.get(0).ownExtendedAttributes.get(0).name)»"
                required="true"
                edit="true"
                clear="false"
                description="«Slug.addLanguage(component.languages, newArrayList("com", component.name, "FILTER", page.extendedEntityList.get(0).name, page.extendedEntityList.get(0).ownExtendedAttributes.get(0).name, "DESC"), StaticLanguage.getCommonDescriptionFor(page.extendedEntityList.get(0).ownExtendedAttributes.get(0).name))»"
                valueColumn="«page.extendedEntityList.get(0).primaryKey.name»"
                textColumn="«page.extendedEntityList.get(0).getFirstUniqueKey().name»">
                <option value="">«Slug.addLanguage(component.languages, newArrayList("com", component.name, "OPTION", "SELECT", page.extendedEntityList.get(0).getFirstUniqueKey().name), page.extendedEntityList.get(0).getFirstUniqueKey().name)»</option>
            </field>
        </fieldset>
    '''

    /**
     * Generate the Setting parameter for the configuration of a menu item from a indexpage.
     * 
     * @param String              pagename  contains the page name
     * @param ExtendedDynamicPage page      contains the extended instance of a dynamicpage
     * @parem ExtendedComponent   component contains the instance of a component
     * 
     */
    def CharSequence genSettingForIndexPage(String pagename, ExtendedDynamicPage page, ExtendedComponent component) '''
        <fieldset name="basic" label="«Slug.addLanguage(component.languages, newArrayList("com", component.name, page.name), StaticLanguage.ORDERING_LABEL)»">
            «IF page !== null»
                <field name="ordering" type="list"
                    label="«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.ORDERING_LABEL)»"
                    description="«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.ORDERING_DESC)»"
                    class="inputbox"
                    default="«page.extendedEntityList.get(0).primaryKey.name»">
                    <option value="«page.extendedEntityList.get(0).primaryKey.name»">«page.extendedEntityList.get(0).primaryKey.name»</option>  
                    «FOR ExtendedAttribute attr: page.extendFiltersList»
                        <option value="«attr.name.toLowerCase»">«Slug.addLanguage(component.languages, newArrayList("com", component.name, "FORM", "LBL", page.extendedEntityList.get(0).name, attr.name), attr.name)»</option>
                    «ENDFOR»
                </field>
            «ENDIF»
            <field name="direction" type="list"
                label="«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.DIRECTION)»"
                description="«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.DIRECTION_DESC)»"
                class="inputbox"
                size="1"
                default="ASC">
                <option value="ASC">«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.DIRECTION_ASC)»</option>
                <option value="DESC">«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.DIRECTION_DESCENDING)»</option>
            </field>
            <field
                name="start"
                type="int"
                default="0"
                label="«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.START_LABEL)»"
                description="«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.START_DESC)»" />
            <field
                name="limit"
                type="int"
                default="10"
                label="«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.LIMIT_LABEL)»"
                description="«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.LIMIT_DESC)»" />
            <field
                name="search"
                type="text"
                label="«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.SEARCH_LABEL)»"
                description="«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.SEARCH_DESC)»" />
            <field name="state" type="list"
                label="JSTATUS"
                description="JFIELD_PUBLISHED_DESC"
                class="inputbox"
                size="1"
                default="">
                <option value="">JSELECT</option>
                <option value="1">JPUBLISHED</option>
                <option value="0">JUNPUBLISHED</option>
                <option value="2">JARCHIVED</option>
                <option value="-2">JTRASHED</option>
            </field>
        </fieldset>
        «IF component !== null && page !== null»
            <fieldset name="filter"
                label="«Slug.addLanguage(component.languages, newArrayList("com", component.name), StaticLanguage.FILTER_LABEL)»">
                <field
                    name="created_by"
                    addfieldpath="components/«Slug.nameExtensionBind("com",component.name).toLowerCase»/models/fields"
                    type="«component.name.toLowerCase»user"
                    label="JGLOBAL_FIELD_CREATED_BY_LABEL"
                    description="JGLOBAL_FIELD_CREATED_BY_DESC"
                    entity="«page.extendedEntityList.get(0).name»">
                    <option value="">JSELECT</option>
                </field>
                «Slug.generateFilterFields(page, component.languages, component, '''com_«component.name»''', true, true, false, false)»
            </fieldset>
        «ENDIF»
    '''

    override CharSequence generateTemplate(ExtendedDynamicPage page, ExtendedComponent component) '''
    '''

    override CharSequence generateParameter(EList<ExtendedParameter> listParams, ExtendedComponent component) '''
        «FOR param : listParams»
            «Slug.writeParameter(param,component)»
        «ENDFOR»
    '''

    /**
     * Generate the manifest file for the formular of a details page.
     * 
     * @param String              pagename  contains the page name
     * @param ExtendedDynamicPage page      contains the extended instance of a dynamicpage
     * @parem ExtendedComponent   component contains the instance of a component 
     */
    def CharSequence xmlAdminFields(ExtendedDynamicPage page, ExtendedComponent component, String name) '''
        <?xml version="1.0" encoding="utf-8"?>
        <form>
            <field name="created_by" type="hidden" default="" 
                label="JGLOBAL_FIELD_CREATED_BY_LABEL"
                description="JGLOBAL_FIELD_CREATED_BY_DESC"  /> 
            «FOR ExtendedEntity e : page.extendedEntityList»
                «FOR ExtendedAttribute attr : e.ownExtendedAttributes»
                    «writeAttribute(e, attr, component, page)»
                «ENDFOR»
            «ENDFOR»
           
            <fields name="params">
                <fieldset name="local" label="«Slug.addLanguage(component.languages, newArrayList("com", component.name, page.name), StaticLanguage.PARAMS_LOCAL_LABEL)»">
                    «generateParameter(page.extendedLocalParametersListe, component)»
                </fieldset>
                <fieldset name="global" label="«Slug.addLanguage(component.languages, newArrayList("com", component.name, page.name), StaticLanguage.PARAMS_GLOBAL_LABEL)»">
                    «generateParameter(page.extendedGlobalParametersListe, component)»
                </fieldset>
                «FOR ExtendedParameterGroup e : page.extendedParametersGroupsListe»
                <fieldset name="«e.name.toLowerCase»" label="«Slug.addLanguage(component.languages, newArrayList("com", component.name, "FIELDSET", page.name, e.name), e.name)»">
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
                        section="«page.name.toLowerCase»"/>
                </fieldset>
            </fields>
        </form>
    '''

    /**
     * parse the attribute type and generate the template for the manifest file and create a field, when it don't exist
     * @param ExtendedEntity      entity     contains the instance of a entity
     * @param ExtendedAttribute   attr       contains a attribute of 
     * @param ExtendedComponent   component  conatains the instance of a component
     * @param ExtendedDynamicPage page       contains the instance of a details page	
     * 
     */
    def CharSequence writeAttribute(ExtendedEntity entity, ExtendedAttribute attr, ExtendedComponent component,
        ExtendedDynamicPage page) {
        if (attr.isPreserve)
            return ''''''
        var ExtendedDetailPageField field = Slug.getEditedFieldsForattribute(page, attr)
        var EList<KeyValuePair> options = new BasicEList<KeyValuePair>
        if (field !== null) {
            options = field.attributes
            if (field.fieldtype !== null) {
                return writeFieldType(field, entity, component, page)
            }
        }
        var String type = getHtmlTypeOfAttribute(page, attr, entity, component)
        var StringBuffer result = new StringBuffer
        if (attr.name.equalsIgnoreCase("state")) {
            return '''
                <field name="state" type="list"
                    label="JSTATUS"
                    description="JFIELD_PUBLISHED_DESC"
                    class="inputbox"
                    size="1"
                    default="1">
                    <option value="1">JPUBLISHED</option>
                    <option value="0">JUNPUBLISHED</option>
                    <option value="2">JARCHIVED</option>
                    <option value="-2">JTRASHED</option>
                </field> 
            '''
        }
        val String[] type_temp_array = type.split('''"'''.toString())
        val String type_temp = type_temp_array.get(1)

        var fieldLabel = Slug.addLanguage(component.languages,
            newArrayList("com", component.name, "FORM", "LBL", entity.name, attr.name, "LABEL"), attr.name)
        var fieldDescription = Slug.addLanguage(component.languages,
            newArrayList("com", component.name, "FORM", "LBL", entity.name, attr.name, "DESC"),
            StaticLanguage.getCommonDescriptionFor(attr.name))
        switch (type_temp) {
            case "multiselect",
            case "select",
            case "list": {
                result.append('''
                    <field name="«attr.name»"
                            type="list" 
                            «IF type.contains("multiselect")»
                                multiple ="true"
                            «ENDIF»
                            id="«attr.name»"
                            label="«fieldLabel»"
                            description="«fieldDescription»"
                            «FOR KeyValuePair kvpair : options»
                                «kvpair.name» = "«kvpair.value»"
                            «ENDFOR»
                            >
                            «FOR KeyValuePair kv : field.values»
                                <option value="«kv.name»">«Slug.addLanguage(component.languages, newArrayList("com", component.name, page.name, attr.name, kv.value, "OPTION"), kv.value)»</option>
                            «ENDFOR»
                        </field> 
                ''')
            }
            case "imagepicker": {
                result.append('''
                    <field name="«attr.name»"
                        type="imageloader"
                        accept="image/*"
                        path="«page.name»_image_path"
                        id="«attr.name»"
                        label="«fieldLabel»"
                        description="«fieldDescription»"
                        «FOR KeyValuePair kvpair : options»
                            «kvpair.name» = "«kvpair.value»"
                        «ENDFOR»
                        />
                ''')
            }
            case "filepicker": {
                result.append('''
                    <field name="«attr.name»"
                        type="fileloader"
                        path="«page.name»_file_path"
                        id="«attr.name»"
                        label="«fieldLabel»"
                        description="«fieldDescription»"
                        «FOR KeyValuePair kvpair : options»
                            «kvpair.name» = "«kvpair.value»"
                       «ENDFOR»
                        />
                ''')
            }
            case "checkbox": {
                result.append(''' 
                    <field name="«attr.name»"
                        type="checkboxes" 
                        id="«attr.name»"
                        label="«fieldLabel»"
                        description="«fieldDescription»"
                        «FOR KeyValuePair kvpair : options»
                            «kvpair.name» = "«kvpair.value»"
                        «ENDFOR»
                        >
                        «FOR KeyValuePair kv : field.values»
                            <option value="«kv.name»">«Slug.addLanguage(component.languages, newArrayList("com", component.name, page.name, attr.name, kv.value, "OPTION"), kv.value)»</option>
                        «ENDFOR»
                    </field> 
                ''')
            }
            case "radiobutton": {
                result.append(''' 
                    <field name="«attr.name»"
                        type="radio"
                        id="«attr.name»"
                        label="«fieldLabel»"
                        description="«fieldDescription»"
                        «FOR KeyValuePair kvpair : options»
                            «kvpair.name» = "«kvpair.value»"
                        «ENDFOR»
                        >
                        «FOR KeyValuePair kv : field.values»
                            <option value="«kv.name»">«Slug.addLanguage(component.languages, newArrayList("com", component.name, page.name, attr.name, kv.value, "OPTION"), kv.value)»</option>
                        «ENDFOR»
                    </field> 
                ''')
            }
            case "Yes_No_Buttons": {
                result.append(''' 
                    <field name="«attr.name»"
                        type="radio" 
                        id="«attr.name»"
                        label="«fieldLabel»"
                        description="«fieldDescription»"
                        default="0"
                        «FOR KeyValuePair kvpair : options»
                            «kvpair.name» = "«kvpair.value»"
                        «ENDFOR»
                        >
                        <option value="1">JYES</option>
                        <option value="0">JNO</option>
                    </field> 
                ''')
            }
            default: {
                result.append('''  
                    <field name="«attr.name»"
                        «type»
                        id="«attr.name»"
                        label="«fieldLabel»"
                        description="«fieldDescription»"
                        «FOR KeyValuePair kvpair : options»
                            «kvpair.name» = "«kvpair.value»"
                        «ENDFOR»
                    />
                ''');
            }
        }
        return result.toString
    }

    def CharSequence writeFieldType(ExtendedDetailPageField field, ExtendedEntity entity, ExtendedComponent component,
        ExtendedDynamicPage page) {
        var Attribute refAttr = field.fieldtype
        var String type = getHtmlTypeOfAttribute(page, field.extendedAttribute, entity, component)
        var Entity refEntity = field.fieldtype.eContainer as Entity
        var ExtendedReference ref = entity.searchRefWithAttr(field.attribute, refEntity)

        var fieldName = field.attribute.name

        if (ref !== null && ref.entity instanceof MappingEntity === false) {
            fieldName = ref.referenceAttribute
        }

        var fieldLabel = Slug.addLanguage(component.languages,
            newArrayList("com", component.name, "FORM", "LBL", entity.name, fieldName, "LABEL"), fieldName)
        var fieldDescription = Slug.addLanguage(component.languages,
            newArrayList("com", component.name, "FORM", "LBL", entity.name, fieldName, "DESC"),
            StaticLanguage.getCommonDescriptionFor(fieldName))

        if (field.extendedAttribute.theBaseElementOfUniquePair &&
            field.extendedAttribute.instance instanceof FKAttribute === false) {
            return '''
                <field name="«field.attribute.name»"
                    «Slug.getTypeName(field.type)»
                    id="«field.attribute.name»"
                    label="«fieldLabel»"
                    description="«fieldDescription»"
                    «FOR KeyValuePair kvpair : field.attributes»
                        «kvpair.name» = "«kvpair.value»"
                    «ENDFOR»
                />
            '''
        }
        if (ref !== null) {
            switch (ref.upper) {
                case "1": {
                    return '''
                        <field name="«field.attribute.name»"
                            type="«component.name»reference"
                            id="«field.attribute.name»"
                            label="«fieldLabel»"
                            description="«fieldDescription»"
                            tables="{'table':'#__«component.name»_«entity.name»', 'foreignTable':'#__«component.name»_«ref.entity.name»'}"
                            referenced_keys="«writeRefrence(ref)»"
                            valueColumn="«ref.referencedAttribute»"
                            primary_key_name="«entity.primaryKey.name»"
                                 «FOR KeyValuePair kvpair : field.attributes»
                                     «kvpair.name» = "«kvpair.value»"
                                 «ENDFOR»
                        />
                    '''
                }
                case "*",
                case "-1": {

                    var listOfref = entity.searchListRefWithAttr(field.attribute)
                    return ''' 
                        <field name="«field.attribute.name»"
                            «type»
                            id="«field.attribute.name»"
                            label="«fieldLabel»"
                            description="«fieldDescription»"
                            «FOR KeyValuePair kvpair : field.attributes»
                                «kvpair.name» = "«kvpair.value»"
                            «ENDFOR»
                            />
                        «FOR ExtendedReference refItem : listOfref»
                            «var languageKey = Slug.getNReferenceLanguageKey(component, refItem, entity.name)»
                            <field name="«refItem.entity.name»_id"
                                type="«entity.name»To«refItem.entity.name»"
                                id="«refItem.entity.name»_id"
                                label="«Slug.addLanguage(component.languages, newArrayList(languageKey), refItem.entity.name)»"
                                description="«Slug.addLanguage(component.languages, newArrayList(languageKey), StaticLanguage.getCommonDescriptionFor(refItem.entity.name))»"
                            />
                            «FOR Attribute attr: Slug.getOtherAttribute(refItem)»
                                <field name="«attr.name»"
                                    type="hidden"
                                    id="«attr.name»"
                                />
                            «ENDFOR»
                        «ENDFOR»
                    '''
                }
                default: {
                }
            }
        }

        return ""
    }

    def String writeRefrence(ExtendedReference reference) {
        return '''{ 'key': '«reference.referenceIDAttribute»', 'ref': '«reference.referencedIDAttribute»'}'''
    }

    /**
     * return the html type of a attribute
     * for simple attribute type="type"
     * for the attribute of reference type="hidden"
     * for the others only the name of the type
     * @param ExtendedAttribute   attr       contains a attribute of 
     * @param ExtendedComponent   component  conatains the instance of a component
     * @param ExtendedDynamicPage dynP       contains the instance of a details page
     * 
     */
    public def String getHtmlTypeOfAttribute(ExtendedDynamicPage dynP, ExtendedAttribute attr, ExtendedEntity en,
        ExtendedComponent com) {
        var StringBuffer buff = new StringBuffer
        
        for (ExtendedReference ref : en.allExtendedReferences.filter[ t |
            t.upper.equalsIgnoreCase("1")
        ]) {
            if (ref.extendedAttributes.get(0).name.equalsIgnoreCase(attr.name)) {
                buff.append('''type="«en.name + "to" +ref.entity.name»"''')
                return buff.toString
            }
        }
        
        if (!dynP.extendedEditedFieldsList.empty) {
            var ExtendedDetailPageField field = Slug.getEditedFieldsForattribute(dynP, attr)
            if (field !== null && field.type !== null) {
                return Slug.getTypeName(field.type, field.extendedAttribute)
            }
        }
        
        buff.append('''type="hidden"''')
        return buff.toString.toLowerCase;
    }
}
