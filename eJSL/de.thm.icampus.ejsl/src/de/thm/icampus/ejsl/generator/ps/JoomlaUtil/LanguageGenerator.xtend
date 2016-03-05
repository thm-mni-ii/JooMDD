package de.thm.icampus.ejsl.generator.ps.JoomlaUtil

import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.ejsl.generator.ps.JoomlaExtensionGenerator.AbstractExtensionGenerator
import de.thm.icampus.ejsl.eJSL.Language
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute

class LanguageGenerator extends AbstractExtensionGenerator  {
	
	ExtendedComponent com
	String path
	IFileSystemAccess fsa
	new(IFileSystemAccess fileAccess){
		fsa = fileAccess
	}
	
	def genComponentLanguage(ExtendedComponent component, String root){
		this.com = component
		this.path = root
		for (lang : com.languages) {
			val ldir = lang.name
		fsa.generateFile(path+"/language/site/" + ldir + "/" + ldir + "." + Slug.nameExtensionBind("com", component.name).toLowerCase + ".ini",
				lang.languageFileContent(com.frontEndExtendedPagerefence))
			fsa.generateFile(path+"/language/site/" + ldir + "/" + ldir + "." + Slug.nameExtensionBind("com", component.name).toLowerCase + ".sys.ini",
				lang.languageFileContent(com.frontEndExtendedPagerefence))
			fsa.generateFile(path + "/language/admin/" + ldir + "/" + ldir + "." + Slug.nameExtensionBind("com", component.name).toLowerCase + ".ini",
				lang.languageFileContent(com.backEndExtendedPagerefence))
			fsa.generateFile(path + "/language/admin/" + ldir + "/" + ldir + "." + Slug.nameExtensionBind("com", component.name).toLowerCase + ".sys.ini",
				lang.languageFileContent(com.backEndExtendedPagerefence))
		}
	}
	
	def CharSequence languageFileContent(Language language, EList<ExtendedPageReference> pagerefList)'''
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
			JPUBLISHED="published"
			JUNPUBLISHED="unpublished"
			JARCHIVED="archived"
			JTRASHED="trashed"
			JOPTION_SELECT_CREATED_BY = "select a user"
			
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
		«ENDFOR»
		«FOR e : language.keyvaluepairs»
			«Slug.generateKeysName(com,e.name)»="«e.value»"
		«ENDFOR»
	'''
	
	override generate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}