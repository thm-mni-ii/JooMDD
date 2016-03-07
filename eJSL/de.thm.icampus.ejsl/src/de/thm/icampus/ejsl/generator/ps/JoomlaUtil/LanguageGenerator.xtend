package de.thm.icampus.ejsl.generator.ps.JoomlaUtil

import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.ejsl.generator.ps.JoomlaExtensionGenerator.AbstractExtensionGenerator
import de.thm.icampus.ejsl.eJSL.Language
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.ejsl.eJSL.KeyValuePair

class LanguageGenerator extends AbstractExtensionGenerator  {
	
	new(IFileSystemAccess fileAccess){
		fsa = fileAccess
	}
	
	def genComponentLanguage(ExtendedComponent component, String root){
	
		for (lang : component.languages) {
			val ldir = lang.name
		fsa.generateFile(root+"/language/site/" + ldir + "/" + ldir + "." + Slug.nameExtensionBind("com", component.name).toLowerCase + ".ini",
				 languageFileContent(component,lang,component.frontEndExtendedPagerefence))
			fsa.generateFile(root+"/language/site/" + ldir + "/" + ldir + "." + Slug.nameExtensionBind("com", component.name).toLowerCase + ".sys.ini",
				 languageFileContent(component,lang,component.frontEndExtendedPagerefence))
			fsa.generateFile(root + "/language/admin/" + ldir + "/" + ldir + "." + Slug.nameExtensionBind("com", component.name).toLowerCase + ".ini",
				languageFileContent(component,lang,component.backEndExtendedPagerefence))
			fsa.generateFile(root + "/language/admin/" + ldir + "/" + ldir + "." + Slug.nameExtensionBind("com", component.name).toLowerCase + ".sys.ini",
				languageFileContent(component,lang,component.backEndExtendedPagerefence))
		}
	}
	
	private def CharSequence languageFileContent(ExtendedComponent com,Language language, EList<ExtendedPageReference> pagerefList)'''
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
	«Slug.nameExtensionBind("com", com.name).toUpperCase»_ITEM_SAVED_SUCCESSFULLY="The Data are saved sucessfully"
	«Slug.nameExtensionBind("com", com.name).toUpperCase»_ITEM_DELETED_SUCCESSFULLY="The Data are deleted sucessfully"
	«Slug.nameExtensionBind("com", com.name).toUpperCase»_DELETE_MESSAGE="Do you want to delete the Data?"
	JPUBLISHED="published"
	JUNPUBLISHED="unpublished"
	JARCHIVED="archived"
	JTRASHED="trashed"
	JOPTION_SELECT_CREATED_BY = "select a user"
	JFIELD_RULES_LABEL= "Rules"
			
		«FOR ExtendedPageReference pag : pagerefList»
	«Slug.nameExtensionBind("com", com.name).toUpperCase»_TITLE_«Slug.slugify(pag.page.name).toUpperCase»="«pag.page.name.toFirstUpper»"
	«Slug.nameExtensionBind("com", com.name).toUpperCase»_VIEW_«Slug.slugify(pag.page.name).toUpperCase»_TITLE="«pag.page.name.toFirstUpper»"
	«Slug.nameExtensionBind("com", com.name).toUpperCase»_VIEW_«Slug.slugify(pag.page.name).toUpperCase»_DESC="«pag.page.name.toFirstUpper»"
		«ENDFOR»
		
		«FOR ExtendedPageReference dynamicPagereference : pagerefList.filter[t | t.extendedPage.extendedDynamicPageInstance != null]»
			
			«FOR ExtendedEntity ent: dynamicPagereference.extendedPage.extendedDynamicPageInstance.extendedEntityList»
				«FOR ExtendedAttribute attr: ent.allattribute»
	«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_«Slug.slugify(ent.name).toUpperCase»_«Slug.slugify(attr.name).toUpperCase»="«Slug.slugify(attr.name).toFirstUpper»"
				«ENDFOR»
			«ENDFOR»
		«ENDFOR»
		«FOR ExtendedPageReference dynamicPagereference : pagerefList.filter[t | t.extendedPage.extendedDynamicPageInstance != null]»
			«FOR ExtendedAttribute attr: dynamicPagereference.extendedPage.extendedDynamicPageInstance.extendFiltersList»
	JOPTION_SELECT_«Slug.slugify(attr.name).toUpperCase»="select a «Slug.slugify(attr.name).toFirstUpper»"
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
	public def genModuletLanguage(ExtendedModule extmod, String root){
	
		var String name = Slug.nameExtensionBind("mod",extmod.name).toLowerCase
		for (lang : extmod.languages) {
			val ldir = lang.name
			fsa.generateFile(root+"/language/" + ldir + "/" + ldir + "." + name + ".ini", languageModuleFileGen(extmod,lang.keyvaluepairs))
			fsa.generateFile(root+"/language/" + ldir + "/" + ldir + "." + name + ".sys.ini", languageModuleFileGen(extmod, lang.keyvaluepairs))
		}
		
	}
	
	def CharSequence languageModuleFileGen(ExtendedModule extmod, EList<KeyValuePair> list) '''
	COM_MODULES_FILTER_FIELDSET_LABEL = "filter"
	MOD_«extmod.name.toUpperCase»_ORDERING = "Ordering"
	MOD_«extmod.name.toUpperCase»_JFIELD_ORDERING_DESC = "Ordering description"
	MOD_«extmod.name.toUpperCase»_DIRECTION = "Direction"
	MOD_«extmod.name.toUpperCase»_JFIELD_DIRECTION_DESC = "sort the result in a Direction"
	MOD_«extmod.name.toUpperCase»_ASC="ASC"
	MOD_«extmod.name.toUpperCase»_DESC="DESC"
	MOD_«extmod.name.toUpperCase»_START_LABEL="Start Index"
	MOD_«extmod.name.toUpperCase»_START_DESC="the index of First data Item"
	MOD_«extmod.name.toUpperCase»_LIMIT_LABEL="Limit"
	MOD_«extmod.name.toUpperCase»_LIMIT_DESC="Limit the number of Data in view"
	MOD_«extmod.name.toUpperCase»_SEARCH_LABEL="Search"
	MOD_«extmod.name.toUpperCase»_SEARCH_DESC="Search Data"
	MOD_«extmod.name.toUpperCase»_JSTATUS="Status"
	MOD_«extmod.name.toUpperCase»_JFIELD_PUBLISHED_DESC="Status"
	MOD_«extmod.name.toUpperCase»_FILTER_CREATED_BY="Created by"
	MOD_«extmod.name.toUpperCase»_FILTER_CREATED_BY="Created by"
	JOPTION_SELECT_CREATED_BY="select a user"
	«FOR ExtendedAttribute attr: extmod.extendedPageReference.extendedPage.extendedDynamicPageInstance.extendFiltersList»
	MOD_«extmod.name.toUpperCase»_FORM_LBL_«attr.name.toUpperCase» = "«attr.name»"
	MOD_«extmod.name.toUpperCase»_FILTER_«attr.name.toUpperCase» = "«attr.name»"
	MOD_«extmod.name.toUpperCase»_FILTER_«attr.name.toUpperCase»_DESC = "«attr.name»"
	JOPTION_SELECT_«attr.name.toUpperCase»="select a «attr.name»"
	«ENDFOR»
	
	'''
	
	
}