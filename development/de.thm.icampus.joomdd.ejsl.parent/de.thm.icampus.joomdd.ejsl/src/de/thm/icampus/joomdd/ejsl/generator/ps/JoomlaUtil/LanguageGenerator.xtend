package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil

import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair
import de.thm.icampus.joomdd.ejsl.eJSL.Language
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.AbstractExtensionGenerator
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2

class LanguageGenerator extends AbstractExtensionGenerator {

	new(IFileSystemAccess2 fileAccess) {
		fsa = fileAccess
	}

	def genComponentLanguage(ExtendedComponent component, String root) {

		for (lang : component.languages) {
			val ldir = lang.name
			fsa.generateFile(
				root + "/language/site/" + ldir + "/" + ldir + "." +
					Slug.nameExtensionBind("com", component.name).toLowerCase + ".ini",
				languageFileContent(component, lang, component.frontEndExtendedPagerefence))
			fsa.generateFile(
				root + "/language/site/" + ldir + "/" + ldir + "." +
					Slug.nameExtensionBind("com", component.name).toLowerCase + ".sys.ini",
				languageFileContent(component, lang, component.frontEndExtendedPagerefence))
			fsa.generateFile(
				root + "/language/admin/" + ldir + "/" + ldir + "." +
					Slug.nameExtensionBind("com", component.name).toLowerCase + ".ini",
				languageFileContent(component, lang, component.backEndExtendedPagerefence))
			fsa.generateFile(
				root + "/language/admin/" + ldir + "/" + ldir + "." +
					Slug.nameExtensionBind("com", component.name).toLowerCase + ".sys.ini",
				languageFileContent(component, lang, component.backEndExtendedPagerefence))
		}
	}

	private def CharSequence languageFileContent(ExtendedComponent com, Language language,
		EList<ExtendedPageReference> pagerefList) '''
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_LABEL="«com.name.toFirstUpper»"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_DESC="«com.name.toFirstUpper» «com.manifest.description»"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»="«com.name.toFirstUpper»"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_HOME="Home"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_NONE_ID="ID"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_NONE_CHECKED_OUT="Checked out"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_NONE_CHECKED_OUT_TIME="Checked out Time"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_NONE_ORDERING="Ordering"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_NONE_CREATED_BY="Created By"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_NONE_STATE="state"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_JSTATUS="state"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_JFIELD_PUBLISHED_DESC="State Description"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_EDIT_ITEM="Edit"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_DELETE_ITEM="Delete"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_ADD_ITEM="Add"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_ITEM_SAVED_SUCCESSFULLY="The data are saved sucessfully"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_ITEM_DELETED_SUCCESSFULLY="The data are deleted sucessfully"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_DELETE_MESSAGE="Do you want to delete the Data?"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_N_ITEMS_PUBLISHED="The data are published sucessfully"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_TEMPLATE_LAYOUT="Template Layout"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_TEMPLATE_LAYOUT_DESC="Choice a Layout for the Indexpage"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_DIRECTION = "Direction"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_DIRECTION_ASC = "ASC"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_DIRECTION_DESC ="DESC"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_JFIELD_DIRECTION_DESC ="Direction"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_START_LABEL = "Start"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_START_LABEL_DESC ="Begin index for Data"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_LIMIT_LABEL ="Limit"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_LIMIT_LABEL_DESC ="The number of Dataitem in the View"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_SEARCH_LABEL ="Search"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_SEARCH_LABEL_DESC ="Search Data"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_CREATED_BY="Created By"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_ORDERING = "Ordering"
		«Slug.nameExtensionBind("com", com.name).toUpperCase»_ORDERING_DESC ="Ordering description"
			
		
		JTEMPLATE_LAYOUT_LIST="List Layout"
		JTEMPLATE_LAYOUT_TABLE="Table layout"
		JOPTION_SELECT_LIMIT = "limit"
		JPUBLISHED="published"
		JUNPUBLISHED="unpublished"
		JGLOBAL_ACTION_PERMISSIONS_LABEL = "Permission"
		JARCHIVED="archived"
		JTRASHED="trashed"
		JOPTION_SELECT_CREATED_BY = "Select a user"
		JFIELD_RULES_LABEL= "Rules"
				
			«FOR ExtendedPageReference pag : pagerefList»
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_TITLE_«Slug.slugify(pag.page.name).toUpperCase»="«pag.page.name.toFirstUpper»"
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_VIEW_«Slug.slugify(pag.page.name).toUpperCase»_TITLE="«pag.page.name.toFirstUpper»"
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_VIEW_«Slug.slugify(pag.page.name).toUpperCase»_DESC="«pag.page.name.toFirstUpper» description"
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_ALIAS_«pag.page.name.toUpperCase»="«pag.page.name.toFirstUpper»"
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_«pag.page.name.toUpperCase»_PARAMS_LOCAL__LABEL = "Local Parameter"
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_«pag.page.name.toUpperCase»_PARAMS_GLOBAL__LABEL= "Global Parameter"
			
			«IF pag.extendedPage.intance instanceof DetailsPage»
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_TITLE_«Slug.slugify(pag.page.name).toUpperCase»EDIT = "«pag.page.name.toFirstUpper»edit"
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_VIEW_«Slug.slugify(pag.page.name).toUpperCase»EDIT_TITLE=  "«pag.page.name.toFirstUpper»edit"
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_VIEW_«Slug.slugify(pag.page.name).toUpperCase»EDIT_DESC= "«pag.page.name.toFirstUpper»edit description"
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_ALIAS_«pag.page.name.toUpperCase»EDIT="«pag.page.name.toFirstUpper»edit"
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_«pag.page.name.toUpperCase»EDIT_PARAMS_LOCAL__LABEL = "Local Parameter"
			«Slug.nameExtensionBind("com", com.name).toUpperCase»_«pag.page.name.toUpperCase»EDIT_PARAMS_GLOBAL__LABEL= "Global Parameter"
			«ENDIF»
			
			«ENDFOR»
			
			«FOR ExtendedPageReference dynamicPagereference : pagerefList.filter[t | t.extendedPage.extendedDynamicPageInstance != null]»
				«var ExtendedDynamicPage dtPage = dynamicPagereference.extendedPage.extendedDynamicPageInstance as ExtendedDynamicPage »
				«FOR ExtendedEntity ent: dtPage.extendedEntityList»
					«Slug.nameExtensionBind("com", com.name).toUpperCase»_«ent.name.toUpperCase» = "«ent.name.toFirstUpper»"
					JGRID_HEADING_«ent.primaryKey.name.toUpperCase» = "«ent.primaryKey.name»"
					
					«Slug.nameExtensionBind("com", com.name).toUpperCase»_SELECT_«ent.name.toUpperCase» = "Select a «ent.name.toFirstUpper»"
					«FOR ExtendedAttribute attr: ent.allattribute»
						« var ExtendedDetailPageField field =  Slug.getEditedFieldsForattribute(dtPage, attr) »
						«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_«Slug.slugify(ent.name).toUpperCase»_«Slug.slugify(attr.name).toUpperCase»="«Slug.slugify(attr.name).toFirstUpper»"
						«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_«Slug.slugify(ent.name).toUpperCase»_«Slug.slugify(attr.name).toUpperCase»_DESC="Description of «Slug.slugify(attr.name).toFirstUpper»"
						«IF field != null && field.values != null»
							«FOR KeyValuePair kv: field.values»
								«dtPage.name.toUpperCase»_«attr.name.toUpperCase»_«kv.name.toUpperCase»_OPTION = «kv.name»
							«ENDFOR»
						«ENDIF»	
					«ENDFOR»
				 «FOR ExtendedReference ref: ent.extendedReference.filter[t | t.upper.equalsIgnoreCase("-1")]»
				 «var Entity refEntity = Slug.getOtherEntityToMapping(ref)»
				  «Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_«Slug.slugify(ent.name).toUpperCase»_«refEntity.name.toUpperCase» = "«refEntity.name.toFirstUpper»"
				 «ENDFOR»
				«ENDFOR»
			«ENDFOR»
			«FOR ExtendedPageReference dynamicPagereference : pagerefList.filter[t | t.extendedPage.extendedDynamicPageInstance != null]»
			    «IF dynamicPagereference.extendedPage.extendedDynamicPageInstance.instance instanceof IndexPage»
			    «Slug.nameExtensionBind("com", com.name).toUpperCase»_«dynamicPagereference.extendedPage.name.toUpperCase»_ORDERING_LABEL = "Ordering"
			     «Slug.nameExtensionBind("com", com.name).toUpperCase»_«dynamicPagereference.extendedPage.name.toUpperCase»_FILTER_LABEL = "Filter"
			    «ENDIF»
				«FOR ExtendedAttribute attr: dynamicPagereference.extendedPage.extendedDynamicPageInstance.extendFiltersList»
					JOPTION_SELECT_«Slug.slugify(attr.name).toUpperCase»="Select a «Slug.slugify(attr.name).toFirstUpper»"
					«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_«dynamicPagereference.extendedPage.name.toUpperCase»_«Slug.slugify(attr.name).toUpperCase»= "«Slug.slugify(attr.name).toFirstUpper»"
					«Slug.nameExtensionBind("com", com.name).toUpperCase»_FILTER_«dynamicPagereference.extendedPage.extendedDynamicPageInstance.extendedEntityList.get(0).name.toUpperCase»_«Slug.slugify(attr.name).toUpperCase»= "«Slug.slugify(attr.name).toFirstUpper»"
					«ENDFOR»
					«Slug.nameExtensionBind("com", com.name).toUpperCase»_«dynamicPagereference.extendedPage.name.toUpperCase»_ACTIONS="Actions"
				«ENDFOR»
				«FOR e : language.keyvaluepairs»
					«Slug.generateKeysName(com,e.name)»="«e.value»"
					«ENDFOR»
				'''

	override generate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	public def genModuletLanguage(ExtendedModule extmod, String root) {

		var String name = Slug.nameExtensionBind("mod", extmod.name).toLowerCase
		for (lang : extmod.languages) {
			val ldir = lang.name
			fsa.generateFile(root + "/language/" + ldir + "/" + ldir + "." + name + ".ini",
				languageModuleFileGen(extmod, lang.keyvaluepairs))
			fsa.generateFile(root + "/language/" + ldir + "/" + ldir + "." + name + ".sys.ini",
				languageModuleFileGen(extmod, lang.keyvaluepairs))
		}

	}

	def CharSequence languageModuleFileGen(ExtendedModule extmod,
		EList<KeyValuePair> list) '''
		«Slug.nameExtensionBind("mod", extmod.name).toUpperCase»_LABEL="«extmod.name.toFirstUpper»"
		«Slug.nameExtensionBind("mod", extmod.name).toUpperCase»_DESC="«extmod.name.toFirstUpper» «extmod.manifest.description»"
		
		COM_MODULES_FILTER_FIELDSET_LABEL = "Filter"
		MOD_«extmod.name.toUpperCase»_ORDERING = "Ordering"
		MOD_«extmod.name.toUpperCase»_JFIELD_ORDERING_DESC = "Ordering description"
		MOD_«extmod.name.toUpperCase»_DIRECTION = "Direction"
		MOD_«extmod.name.toUpperCase»_JFIELD_DIRECTION_DESC = "Sort the result in a Direction"
		MOD_«extmod.name.toUpperCase»_ASC="ASC"
		MOD_«extmod.name.toUpperCase»_DESC="DESC"
		MOD_«extmod.name.toUpperCase»_START_LABEL="Start Index"
		MOD_«extmod.name.toUpperCase»_START_DESC="The index of First data Item"
		MOD_«extmod.name.toUpperCase»_LIMIT_LABEL="Limit"
		MOD_«extmod.name.toUpperCase»_LIMIT_DESC="Limit the number of Data in view"
		MOD_«extmod.name.toUpperCase»_SEARCH_LABEL="Search"
		MOD_«extmod.name.toUpperCase»_SEARCH_DESC="Search Data"
		MOD_«extmod.name.toUpperCase»_JSTATUS="Status"
		MOD_«extmod.name.toUpperCase»_JFIELD_PUBLISHED_DESC="Status"
		MOD_«extmod.name.toUpperCase»_FILTER_CREATED_BY="Created by"
		MOD_«extmod.name.toUpperCase»_FILTER_CREATED_BY="Created by"
		JOPTION_SELECT_CREATED_BY="select a user"
		«var ExtendedDynamicPage dtPage = extmod.extendedPageReference.extendedPage.extendedDynamicPageInstance »
		«FOR ExtendedAttribute attr : extmod.extendedPageReference.extendedPage.extendedDynamicPageInstance.extendFiltersList»
			« var ExtendedDetailPageField field =  Slug.getEditedFieldsForattribute(dtPage, attr) »
			MOD_«extmod.name.toUpperCase»_FORM_LBL_«attr.name.toUpperCase» = "«attr.name.toFirstUpper»"
			MOD_«extmod.name.toUpperCase»_FILTER_«attr.name.toUpperCase» = "«attr.name.toFirstUpper»"
			MOD_«extmod.name.toUpperCase»_FILTER_«attr.name.toUpperCase»_DESC = "«attr.name.toFirstUpper»"
			JOPTION_SELECT_«attr.name.toUpperCase»="Select a «attr.name»"
			«IF field != null»
				«FOR KeyValuePair kv: field.values»
					«dtPage.name.toUpperCase»_«attr.name.toUpperCase»_«kv.name.toUpperCase»_OPTION = «kv.name»
				«ENDFOR»
			«ENDIF»	
		«ENDFOR»
		
	'''

}
