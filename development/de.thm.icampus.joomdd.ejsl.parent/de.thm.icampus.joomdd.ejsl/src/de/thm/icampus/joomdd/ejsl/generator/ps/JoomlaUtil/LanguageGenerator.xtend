package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil

import de.thm.icampus.joomdd.ejsl.eJSL.Language
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.AbstractExtensionGenerator
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule

class LanguageGenerator extends AbstractExtensionGenerator {
	new(IFileSystemAccess2 fileAccess) {
		fsa = fileAccess
		
		
	}

	def genComponentLanguage(ExtendedComponent component, String root) {

		for (lang : component.languages) {
			val ldir = lang.name
			var EList<KVPairLanguage> languagesWordsFront = new BasicEList<KVPairLanguage>()
			var EList<KVPairLanguage> languagesWordsBack = new BasicEList<KVPairLanguage>()
			
			languageFileContent(languagesWordsFront, component,lang, component.frontEndExtendedPagerefence)
			languageFileContent(languagesWordsBack, component,lang, component.backEndExtendedPagerefence)
			
			for(keys: lang.keyvaluepairs){
				if(!keysContains(keys, languagesWordsFront)){
					languagesWordsFront.add(new KVPairLanguage(keys) )
					languagesWordsBack.add(new KVPairLanguage(keys) )
				}
			}
			if(!lang.sys){
				fsa.generateFile(
				root +"/components/"+ component.extensionName + "/language/" + ldir + "/" + ldir + "." +
					Slug.nameExtensionBind("com", component.name).toLowerCase + ".ini",
				fileLangGen(languagesWordsFront))
				fsa.generateFile(
				root + "/administrator/components/"+ component.extensionName + "/language/" + ldir + "/" + ldir + "." +
					Slug.nameExtensionBind("com", component.name).toLowerCase + ".ini",
					fileLangGen(languagesWordsBack))
			}else{
			
			fsa.generateFile(
				root +"/components/"+ component.extensionName+ "/language/" + ldir + "/" + ldir + "." + 
					Slug.nameExtensionBind("com", component.name).toLowerCase + ".sys.ini",
				fileLangGen(languagesWordsFront))
			
			fsa.generateFile(
				root  + "/administrator/components/"+ component.extensionName + "/language/" + ldir + "/" + ldir + "." +
					Slug.nameExtensionBind("com", component.name).toLowerCase + ".sys.ini",
					fileLangGen(languagesWordsBack))
					}
		}
	}
	
	def languageFileContent(ExtendedComponent component, Language language, EList<ExtendedPageReference> list) {
	
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	private def void  languageFileContent(EList<KVPairLanguage> languagesWords, ExtendedComponent com, Language language,
		EList<ExtendedPageReference> pagerefList) {
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_LABEL",com.name.toFirstUpper))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DESC",com.manifest.description))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase,com.name.toFirstUpper))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_HOME","Home"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_ID","ID"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_CHECKED_OUT","Checked out"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_CHECKED_OUT_TIME","Checked out Time"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_ORDERING","Ordering"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_CREATED_BY","Created By"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_STATE","state"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_JSTATUS","state"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_JFIELD_PUBLISHED_DESC","State Description"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_EDIT_ITEM","Edit"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DELETE_ITEM","Delete"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_ADD_ITEM","Add"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_ITEM_SAVED_SUCCESSFULLY","The data are saved sucessfully"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_ITEM_DELETED_SUCCESSFULLY","The data are deleted sucessfully"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DELETE_MESSAGE","Do you want to delete the Data?"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_N_ITEMS_PUBLISHED","The data are published sucessfully"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_TEMPLATE_LAYOUT","Template Layout"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_TEMPLATE_LAYOUT_DESC","Choice a Layout for the Indexpage"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DIRECTION","Direction"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DIRECTION_ASC","ASC"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DIRECTION_DESC" ,"DESC"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_JFIELD_DIRECTION_DESC" ,"Direction"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_START_LABEL","Start"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_START_LABEL_DESC","Begin index for Data"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_LIMIT_LABEL" ,"Limit"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_LIMIT_LABEL_DESC" ,"The number of Dataitem in the View"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_SEARCH_LABEL" ,"Search"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_SEARCH_LABEL_DESC" ,"Search Data"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FILTER_CREATED_BY","Created By"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_ORDERING","Ordering"))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_ORDERING_DESC" ,"Ordering description"))
			
		
		languagesWords.add(new KVPairLanguage("JTEMPLATE_LAYOUT_LIST","List Layout"))
		languagesWords.add(new KVPairLanguage("JTEMPLATE_LAYOUT_TABLE","Table layout"))
		languagesWords.add(new KVPairLanguage("JOPTION_SELECT_LIMIT","limit"))
		languagesWords.add(new KVPairLanguage("JPUBLISHED","published"))
		languagesWords.add(new KVPairLanguage("JUNPUBLISHED","unpublished"))
		languagesWords.add(new KVPairLanguage("JGLOBAL_ACTION_PERMISSIONS_LABEL","Permission"))
		languagesWords.add(new KVPairLanguage("JARCHIVED","archived"))
		languagesWords.add(new KVPairLanguage("JTRASHED","trashed"))
		languagesWords.add(new KVPairLanguage("JOPTION_SELECT_CREATED_BY", "Select a user"))
		languagesWords.add(new KVPairLanguage("JFIELD_RULES_LABEL","Rules"))
		for( ExtendedPageReference pag : pagerefList){
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase + "_TITLE_" + Slug.slugify(pag.page.name).toUpperCase ,pag.page.name.toFirstUpper))
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase + "_VIEW_" + Slug.slugify(pag.page.name).toUpperCase+"_TITLE",pag.page.name.toFirstUpper))
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_VIEW_" + Slug.slugify(pag.page.name).toUpperCase + "_DESC",pag.page.name.toFirstUpper+" description"))
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase + "_ALIAS_" +pag.page.name.toUpperCase,pag.page.name.toFirstUpper))
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_"+ pag.page.name.toUpperCase +"_PARAMS_LOCAL__LABEL" , "Local Parameter"))
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_" + pag.page.name.toUpperCase + "_PARAMS_GLOBAL__LABEL", "Global Parameter"))
			
			if( pag.extendedPage.intance instanceof DetailsPage){
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_TITLE_" + Slug.slugify(pag.page.name).toUpperCase +"EDIT",pag.page.name.toFirstUpper+"edit"))
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_VIEW_"+ Slug.slugify(pag.page.name).toUpperCase+"EDIT_TITLE",pag.page.name.toFirstUpper+"edit"))
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_VIEW_" + Slug.slugify(pag.page.name).toUpperCase+"EDIT_DESC",pag.page.name.toFirstUpper+"edit description"))
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_ALIAS_"+pag.page.name.toUpperCase + "EDIT",pag.page.name.toFirstUpper+"edit"))
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_"+pag.page.name.toUpperCase+"EDIT_PARAMS_LOCAL__LABEL" ,"Local Parameter"))
			languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_"+ pag.page.name.toUpperCase +"EDIT_PARAMS_GLOBAL__LABEL", "Global Parameter"))
			}
			
			}
			
			for(ExtendedPageReference dynamicPagereference : pagerefList.filter[t | t.extendedPage.extendedDynamicPageInstance != null]){
				var ExtendedDynamicPage dtPage = dynamicPagereference.extendedPage.extendedDynamicPageInstance as ExtendedDynamicPage
				for( ExtendedEntity ent: dtPage.extendedEntityList){
					languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_"+ent.name.toUpperCase,ent.name.toFirstUpper))
					languagesWords.add(new KVPairLanguage("JGRID_HEADING_" + ent.primaryKey.name.toUpperCase,ent.primaryKey.name))
					
					languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_SELECT_"+ ent.name.toUpperCase, "Select a " + ent.name.toFirstUpper))
					for(ExtendedAttribute attr: ent.allExtendedAttributes){
						 var ExtendedDetailPageField field =  Slug.getEditedFieldsForattribute(dtPage, attr) 
						languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_FORM_LBL_"+ Slug.slugify(ent.name).toUpperCase+"_"+Slug.slugify(attr.name).toUpperCase,Slug.slugify(attr.name).toFirstUpper))
						languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_FORM_LBL_" + Slug.slugify(ent.name).toUpperCase+"_"+Slug.slugify(attr.name).toUpperCase+"_DESC","Description of " + Slug.slugify(attr.name).toFirstUpper))
						if( field != null && field.values != null){
							for( KeyValuePair kv: field.values){
								languagesWords.add(new KVPairLanguage(dtPage.name.toUpperCase+"_"+attr.name.toUpperCase+"_"+kv.name.toUpperCase+"_OPTION",kv.name))
							}
						}
					}
				for( ExtendedReference ref: ent.allExtendedReferences.filter[t | t.upper.equalsIgnoreCase("-1")]){
				 var Entity refEntity = Slug.getOtherEntityToMapping(ref)
				languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_FORM_LBL_"+Slug.slugify(ent.name).toUpperCase+"_"+refEntity.name.toUpperCase,refEntity.name.toFirstUpper))
				}
				}
			}
			for(ExtendedPageReference dynamicPagereference : pagerefList.filter[t | t.extendedPage.extendedDynamicPageInstance != null]){
			    if( dynamicPagereference.extendedPage.extendedDynamicPageInstance.instance instanceof IndexPage){
			    languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase+"_" + dynamicPagereference.extendedPage.name.toUpperCase+"_ORDERING_LABEL" , "Ordering"))
			     languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_" + dynamicPagereference.extendedPage.name.toUpperCase+"_FILTER_LABEL" , "Filter"))
			   }
				for( ExtendedAttribute attr: dynamicPagereference.extendedPage.extendedDynamicPageInstance.extendFiltersList){
					languagesWords.add(new KVPairLanguage("JOPTION_SELECT_"+ Slug.slugify(attr.name).toUpperCase, "Select a "+Slug.slugify(attr.name).toFirstUpper))
					languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_FILTER_"+dynamicPagereference.extendedPage.name.toUpperCase+"_"+Slug.slugify(attr.name).toUpperCase,Slug.slugify(attr.name).toFirstUpper))
					languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase+"_FILTER_"+dynamicPagereference.extendedPage.extendedDynamicPageInstance.extendedEntityList.get(0).name.toUpperCase+"_"+Slug.slugify(attr.name).toUpperCase, Slug.slugify(attr.name).toFirstUpper))
					}
					languagesWords.add(new KVPairLanguage(com.extensionName.toUpperCase +"_"+dynamicPagereference.extendedPage.name.toUpperCase+"_ACTIONS","Actions"))
				}
				
	}

	override generate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	public def genModuletLanguage(ExtendedModule extmod, String root) {

		var String name = Slug.nameExtensionBind("mod", extmod.name).toLowerCase
		for (lang : extmod.languages) {
			var EList<KVPairLanguage> languagesWords = new BasicEList<KVPairLanguage>()
			languageModuleFileGen(languagesWords, extmod)
			for(keys: lang.keyvaluepairs){
				if(!keysContains(keys, languagesWords)){
					languagesWords.add(new KVPairLanguage(keys) )
				}
			}
			val ldir = lang.name
			if(lang.sys){
				fsa.generateFile(root + "/language/" + ldir + "/" + ldir + "." + name + ".sys.ini",
				fileLangGen(languagesWords))
			}else{
				fsa.generateFile(root + "/language/" + ldir + "/" + ldir + "." + name + ".ini",
				fileLangGen( languagesWords))
			}

		}

	}
	
	def CharSequence fileLangGen( EList<KVPairLanguage>  list) '''
	«FOR KVPairLanguage kv: list»
	«kv.generateKVPair»
	«ENDFOR»
	'''
	
	def boolean keysContains(KeyValuePair pair,EList<KVPairLanguage> languagesWords) {
		for(KVPairLanguage kvl: languagesWords){
			if(kvl.kv.name == pair.name)
			return true
		}
		return false
	}

	private def void  languageModuleFileGen(EList<KVPairLanguage> languagesWords,ExtendedModule extmod) {
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("mod", extmod.name).toUpperCase +"_LABEL",extmod.name.toFirstUpper))
		languagesWords.add(new KVPairLanguage(Slug.nameExtensionBind("mod", extmod.name).toUpperCase+"_DESC",extmod.name.toFirstUpper + " "+ extmod.manifest.description))
		
		languagesWords.add(new KVPairLanguage("COM_MODULES_FILTER_FIELDSET_LABEL" , "Filter"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_ORDERING","Ordering"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_JFIELD_ORDERING_DESC" , "Ordering description"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_DIRECTION", "Direction"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+ "_JFIELD_DIRECTION_DESC" , "Sort the result in a Direction"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_ASC","ASC"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_DESC", "DESC"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_START_LABEL","Start Index"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_START_DESC","The index of First data Item"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_LIMIT_LABEL","Limit"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_LIMIT_DESC","Limit the number of Data in view"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_SEARCH_LABEL","Search"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_SEARCH_DESC","Search Data"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_JSTATUS","Status"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_JFIELD_PUBLISHED_DESC","Status"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_FILTER_CREATED_BY","Created by"))
		languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_FILTER_CREATED_BY","Created by"))
		languagesWords.add(new KVPairLanguage("JOPTION_SELECT_CREATED_BY","select a user"))
		var ExtendedDynamicPage dtPage = extmod.extendedPageReference.extendedPage.extendedDynamicPageInstance 
		for(ExtendedAttribute attr : extmod.extendedPageReference.extendedPage.extendedDynamicPageInstance.extendFiltersList){
			 var ExtendedDetailPageField field =  Slug.getEditedFieldsForattribute(dtPage, attr) 
			languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_FORM_LBL_"+ attr.name.toUpperCase, attr.name.toFirstUpper))
			languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_FILTER_" + attr.name.toUpperCase, attr.name.toFirstUpper))
			languagesWords.add(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_FILTER_"+ attr.name.toUpperCase+"_DESC ", attr.name.toFirstUpper))
			languagesWords.add(new KVPairLanguage("JOPTION_SELECT_" +  attr.name.toUpperCase,"Select a "+ attr.name))
			if(field != null){
				for(KeyValuePair kv: field.values){
					languagesWords.add(new KVPairLanguage( dtPage.name.toUpperCase + "_"+attr.name.toUpperCase+ "_"+kv.name.toUpperCase+"_OPTION",kv.name))
			}
			}
		}
		
	}

}
