package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil

import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaExtensionGenerator.AbstractExtensionGenerator
import de.thm.icampus.joomdd.ejsl.eJSL.Language
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
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
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedExtensionPackage
import org.eclipse.xtext.EcoreUtil2
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage
import de.thm.icampus.joomdd.ejsl.services.EJSLGrammarAccess
import org.eclipse.emf.ecore.util.EcoreUtil

class LanguageGenerator extends AbstractExtensionGenerator {
	new(IFileSystemAccess2 fileAccess) {
		fsa = fileAccess
	}

	def genComponentLanguage(ExtendedComponent component, String root) {
	   for (lang : component.languages) {
			val ldir = lang.name
			var EList<KVPairLanguage> languagesWordsFront = languageFileContent(component,lang, component.frontEndExtendedPagerefence)
			var EList<KVPairLanguage> languagesWordsBack = languageFileContent(component,lang, component.backEndExtendedPagerefence)
						
			val languagesWordsBackOrigin = languagesWordsBack.clone
			
			var languagesWordsFrontAdds = languagesWordsFront.filter[ l | 
				languagesWordsBackOrigin.contains(l) === false
			].toList
			
			languagesWordsBack.addAll(languagesWordsFrontAdds)
			
			
			if(!lang.sys) {
				fsa.generateFile(
					root +"/components/"+ component.extensionName + "/language/" + ldir + "/" + ldir + "." +
					Slug.nameExtensionBind("com", component.name).toLowerCase + ".ini",
					fileLangGen(languagesWordsFront)
				)
				fsa.generateFile(
					root + "/administrator/components/"+ component.extensionName + "/language/" + ldir + "/" + ldir + "." +
						Slug.nameExtensionBind("com", component.name).toLowerCase + ".ini",
					fileLangGen(languagesWordsBack)
				)
			} else {
				fsa.generateFile(
					root +"/components/"+ component.extensionName+ "/language/" + ldir + "/" + ldir + "." + 
						Slug.nameExtensionBind("com", component.name).toLowerCase + ".sys.ini",
					fileLangGen(languagesWordsFront)
				)
				
				fsa.generateFile(
					root  + "/administrator/components/"+ component.extensionName + "/language/" + ldir + "/" + ldir + "." +
						Slug.nameExtensionBind("com", component.name).toLowerCase + ".sys.ini",
					fileLangGen(languagesWordsBack)
				)
			}
		}
	}

	private def EList<KVPairLanguage> languageFileContent(ExtendedComponent com, Language language,
		EList<ExtendedPageReference> pagerefList) {
		
		var EList<KVPairLanguage> languagesWords = new BasicEList<KVPairLanguage>()
		
        for(keyValuePair: language.keyvaluepairs){
            if(!keysContains(keyValuePair, languagesWords)){
                languagesWords.add(new KVPairLanguage(keyValuePair) )
            }
        }		
		
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_LABEL",com.name.toFirstUpper))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DESC",com.manifest.description))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_UPDATE_TEXT","The update is succesfull"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase,com.name.toFirstUpper))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_HOME","Home"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_ID","ID"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_CHECKED_OUT","Checked out"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_CHECKED_OUT_TIME","Checked out Time"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_ORDERING","Ordering"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_CREATED_BY","Created By"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FORM_LBL_NONE_STATE","state"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_JSTATUS","state"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_JFIELD_PUBLISHED_DESC","State Description"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_EDIT_ITEM","Edit"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DELETE_ITEM","Delete"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_ADD_ITEM","Add"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_ITEM_SAVED_SUCCESSFULLY","The data are saved sucessfully"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_ITEM_DELETED_SUCCESSFULLY","The data are deleted sucessfully"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DELETE_MESSAGE","Do you want to delete the Data?"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_N_ITEMS_PUBLISHED","The data are published sucessfully"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_TEMPLATE_LAYOUT","Template Layout"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_TEMPLATE_LAYOUT_DESC","Choice a Layout for the Indexpage"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DIRECTION","Direction"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DIRECTION_ASC","ASC"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_DIRECTION_DESC" ,"DESC"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_JFIELD_DIRECTION_DESC" ,"Direction"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_START_LABEL","Start"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_START_LABEL_DESC","Begin index for Data"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_LIMIT_LABEL" ,"Limit"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_LIMIT_LABEL_DESC" ,"The number of Dataitem in the View"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_SEARCH_LABEL" ,"Search"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_SEARCH_LABEL_DESC" ,"Search Data"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_FILTER_CREATED_BY","Created By"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_ORDERING","Ordering"))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("com", com.name).toUpperCase+ "_ORDERING_DESC" ,"Ordering description"))
			
		
		languagesWords.addsLanguageKeys(new KVPairLanguage("JTEMPLATE_LAYOUT_LIST","List Layout"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("JTEMPLATE_LAYOUT_TABLE","Table layout"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("JOPTION_SELECT_LIMIT","limit"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("JPUBLISHED","published"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("JUNPUBLISHED","unpublished"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("JGLOBAL_ACTION_PERMISSIONS_LABEL","Permission"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("JARCHIVED","archived"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("JTRASHED","trashed"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("JOPTION_SELECT_CREATED_BY", "Select a user"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("JFIELD_RULES_LABEL","Rules"))
		for( ExtendedPageReference pag : pagerefList){
			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase + "_TITLE_" + Slug.slugify(pag.page.name).toUpperCase ,pag.page.name.toFirstUpper))
			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase + "_VIEW_" + Slug.slugify(pag.page.name).toUpperCase+"_TITLE",pag.page.name.toFirstUpper))
			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_VIEW_" + Slug.slugify(pag.page.name).toUpperCase + "_DESC",pag.page.name.toFirstUpper+" description"))
			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase + "_ALIAS_" +pag.page.name.toUpperCase,pag.page.name.toFirstUpper))
			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_"+ pag.page.name.toUpperCase +"_PARAMS_LOCAL__LABEL" , "Local Parameter"))
			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_" + pag.page.name.toUpperCase + "_PARAMS_GLOBAL__LABEL", "Global Parameter"))
			
			if( pag.extendedPage.intance instanceof DetailsPage){
    			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_TITLE_" + Slug.slugify(pag.page.name).toUpperCase +"EDIT",pag.page.name.toFirstUpper+"edit"))
    			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_VIEW_"+ Slug.slugify(pag.page.name).toUpperCase+"EDIT_TITLE",pag.page.name.toFirstUpper+"edit"))
    			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_VIEW_" + Slug.slugify(pag.page.name).toUpperCase+"EDIT_DESC",pag.page.name.toFirstUpper+"edit description"))
    			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_ALIAS_"+pag.page.name.toUpperCase + "EDIT",pag.page.name.toFirstUpper+"edit"))
    			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_"+pag.page.name.toUpperCase+"EDIT_PARAMS_LOCAL__LABEL" ,"Local Parameter"))
    			languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_"+ pag.page.name.toUpperCase +"EDIT_PARAMS_GLOBAL__LABEL", "Global Parameter"))
			}
		}
			var EList<ExtendedPageReference> temp = new BasicEList<ExtendedPageReference>();
			temp.addAll(pagerefList.filter[t | t.extendedPage.extendedDynamicPageInstance !== null])
		for(ExtendedPageReference dynamicPagereference : temp){
			var ExtendedDynamicPage dtPage = dynamicPagereference.extendedPage.extendedDynamicPageInstance as ExtendedDynamicPage
			for( ExtendedEntity ent: dtPage.extendedEntityList) {
				languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_"+ent.name.toUpperCase,ent.name.toFirstUpper))
				languagesWords.addsLanguageKeys(new KVPairLanguage("JGRID_HEADING_" + ent.primaryKey.name.toUpperCase,ent.primaryKey.name))
				
				languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_SELECT_"+ ent.name.toUpperCase, "Select a " + ent.name.toFirstUpper))
				for(ExtendedAttribute attr: ent.allExtendedAttributes){
					var ExtendedDetailPageField field =  Slug.getEditedFieldsForattribute(dtPage, attr) 
					languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_FORM_LBL_"+ Slug.slugify(ent.name).toUpperCase+"_"+Slug.slugify(attr.name).toUpperCase,Slug.slugify(attr.name).toFirstUpper))
					languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_FORM_LBL_" + Slug.slugify(ent.name).toUpperCase+"_"+Slug.slugify(attr.name).toUpperCase+"_DESC","Description of " + Slug.slugify(attr.name).toFirstUpper))
					if( field !== null && field.values !== null){
						for( KeyValuePair kv: field.values){
							languagesWords.addsLanguageKeys(new KVPairLanguage(dtPage.name.toUpperCase+"_"+attr.name.toUpperCase+"_"+kv.value.toUpperCase+"_OPTION",kv.value))
						}
					}
				}
    			for( ExtendedReference ref: ent.allExtendedReferences.filter[t | t.upper.equalsIgnoreCase("-1")]){
    			    var Entity refEntity = Slug.getOtherEntityToMapping(ref)
    			    if(refEntity !== null)
    			    languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_FORM_LBL_"+Slug.slugify(ent.name).toUpperCase+"_"+refEntity.name.toUpperCase,refEntity.name.toFirstUpper))
    			}
			}
		}
		for(ExtendedPageReference dynamicPagereference : pagerefList.filter[t | t.extendedPage.extendedDynamicPageInstance !== null]){
		    if( dynamicPagereference.extendedPage.extendedDynamicPageInstance.instance instanceof IndexPage){
			    languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase+"_" + dynamicPagereference.extendedPage.name.toUpperCase+"_ORDERING_LABEL" , "Ordering"))
			    languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_" + dynamicPagereference.extendedPage.name.toUpperCase+"_FILTER_LABEL" , "Filter"))
			}
			
			var searchDescriptionAttributes = newArrayList			
			for( ExtendedAttribute attr: dynamicPagereference.extendedPage.extendedDynamicPageInstance.extendFiltersList){
			    languagesWords.addsLanguageKeys(new KVPairLanguage("JOPTION_SELECT_"+ Slug.slugify(attr.name).toUpperCase, "Select a "+Slug.slugify(attr.name).toFirstUpper))
				languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_FILTER_"+dynamicPagereference.extendedPage.name.toUpperCase+"_"+Slug.slugify(attr.name).toUpperCase,Slug.slugify(attr.name).toFirstUpper))
				languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_FILTER_"+dynamicPagereference.extendedPage.extendedDynamicPageInstance.extendedEntityList.get(0).name.toUpperCase+"_"+Slug.slugify(attr.name).toUpperCase, Slug.slugify(attr.name).toFirstUpper))
				
                var attrEntityName = attr.entity.name
                // TODO: There might be more than one entity.
                val pageEntityName = dynamicPagereference.extendedPage.extendedDynamicPageInstance.entities.get(0).name
                
                var String searchDescriptionAttributeName
				if (pageEntityName.equals(attrEntityName) === false){
				    var reference = attr.entity.references.findFirst[ r |
				        r.entity.name.equals(pageEntityName) === false
				    ]
				    				    
				    searchDescriptionAttributeName = reference.entity.name
				}
				else
				{
                    searchDescriptionAttributeName = attr.name 
				}
				
				searchDescriptionAttributes.add(Slug.slugify(searchDescriptionAttributeName).toFirstUpper) 
			}
			
			if (searchDescriptionAttributes.empty === false){
			    var lastElement = searchDescriptionAttributes.last
                searchDescriptionAttributes.remove(lastElement)
                var searchDestription = '''Search in «searchDescriptionAttributes.join(", ")»«IF searchDescriptionAttributes.size > 0» and «ENDIF»«lastElement».'''
                languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_FILTER_SEARCH_"+dynamicPagereference.extendedPage.name.toUpperCase+"_DESC", searchDestription))
            }
            languagesWords.addsLanguageKeys(new KVPairLanguage(com.extensionName.toUpperCase +"_"+dynamicPagereference.extendedPage.name.toUpperCase+"_ACTIONS","Actions"))
			
		}
		
		return languagesWords
	}
	
	def void addsLanguageKeys(EList<KVPairLanguage> list, KVPairLanguage language){
		for(KVPairLanguage g : list){
			if(g.kv.name.equals(language.kv.name)){
				return;
			}
		}
		list.add(language);
	}

	override generate() {
	    throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	public def genModuleLanguage(ExtendedModule extmod, String root) {
		var String name = Slug.nameExtensionBind("mod", extmod.name).toLowerCase
		for (lang : extmod.languages) {
		    var EList<KVPairLanguage> languagesWords = new BasicEList<KVPairLanguage>()
			languageModuleFileGen(languagesWords, extmod)
			for(keys: lang.keyvaluepairs){
				if(!keysContains(keys, languagesWords)){
					languagesWords.addsLanguageKeys(new KVPairLanguage(keys) )
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
	
	public def genPackageLanguage(ExtendedExtensionPackage extpkg, String root) {
        var String name = Slug.nameExtensionBind("pkg", extpkg.name).toLowerCase
        for (lang : extpkg.languages) {
            var EList<KVPairLanguage> languagesWords = new BasicEList<KVPairLanguage>()
            languagePackageFileGen(languagesWords, extpkg)
            for(keys: lang.keyvaluepairs){
                if(!keysContains(keys, languagesWords)){
                    languagesWords.addsLanguageKeys(new KVPairLanguage(keys) )
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
			if(kvl.kv.name.equals(pair.name)){
			    return true    
			}
		}
		return false
	}

	private def void  languageModuleFileGen(EList<KVPairLanguage> languagesWords,ExtendedModule extmod) {
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("mod", extmod.name).toUpperCase +"_LABEL",extmod.name.toFirstUpper))
		languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("mod", extmod.name).toUpperCase+"_DESC",extmod.name.toFirstUpper + " "+ extmod.manifest.description))
		
		languagesWords.addsLanguageKeys(new KVPairLanguage("COM_MODULES_FILTER_FIELDSET_LABEL" , "Filter"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_ORDERING","Ordering"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_JFIELD_ORDERING_DESC" , "Ordering description"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_DIRECTION", "Direction"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+ "_JFIELD_DIRECTION_DESC" , "Sort the result in a Direction"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_ASC","ASC"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_DESC", "DESC"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_START_LABEL","Start Index"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_START_DESC","The index of First data Item"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_LIMIT_LABEL","Limit"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_LIMIT_DESC","Limit the number of Data in view"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_SEARCH_LABEL","Search"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_SEARCH_DESC","Search Data"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_JSTATUS","Status"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_JFIELD_PUBLISHED_DESC","Status"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_FILTER_CREATED_BY","Created by"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_FILTER_CREATED_BY","Created by"))
		languagesWords.addsLanguageKeys(new KVPairLanguage("JOPTION_SELECT_CREATED_BY","select a user"))
		var ExtendedDynamicPage dtPage = extmod.extendedPageReference.extendedPage.extendedDynamicPageInstance 
		for(ExtendedAttribute attr : extmod.extendedPageReference.extendedPage.extendedDynamicPageInstance.extendFiltersList) {
			var ExtendedDetailPageField field =  Slug.getEditedFieldsForattribute(dtPage, attr) 
			languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_FORM_LBL_"+ attr.name.toUpperCase, attr.name.toFirstUpper))
			languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_FILTER_" + attr.name.toUpperCase, attr.name.toFirstUpper))
			languagesWords.addsLanguageKeys(new KVPairLanguage("MOD_" + extmod.name.toUpperCase+"_FILTER_"+ attr.name.toUpperCase+"_DESC ", attr.name.toFirstUpper))
			languagesWords.addsLanguageKeys(new KVPairLanguage("JOPTION_SELECT_" +  attr.name.toUpperCase,"Select a "+ attr.name))
			if(field !== null){
				for(KeyValuePair kv: field.values){
					languagesWords.addsLanguageKeys(new KVPairLanguage( dtPage.name.toUpperCase + "_"+attr.name.toUpperCase+ "_"+kv.name.toUpperCase+"_OPTION",kv.name))
			    }
			}
		}
	}
	
	private def void  languagePackageFileGen(EList<KVPairLanguage> languagesWords,ExtendedExtensionPackage extpkg) {
        languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("pkg", extpkg.name).toUpperCase +"_LABEL",extpkg.name.toFirstUpper))
        languagesWords.addsLanguageKeys(new KVPairLanguage(Slug.nameExtensionBind("pkg", extpkg.name).toUpperCase+"_DESC",extpkg.name.toFirstUpper + " "+ extpkg.manifest.description))
        
        languagesWords.addsLanguageKeys(new KVPairLanguage("COM_MODULES_FILTER_FIELDSET_LABEL" , "Filter"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_ORDERING","Ordering"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_JFIELD_ORDERING_DESC" , "Ordering description"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_DIRECTION", "Direction"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+ "_JFIELD_DIRECTION_DESC" , "Sort the result in a Direction"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_ASC","ASC"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_DESC", "DESC"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_START_LABEL","Start Index"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_START_DESC","The index of First data Item"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_LIMIT_LABEL","Limit"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_LIMIT_DESC","Limit the number of Data in view"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_SEARCH_LABEL","Search"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_SEARCH_DESC","Search Data"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_JSTATUS","Status"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_JFIELD_PUBLISHED_DESC","Status"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_FILTER_CREATED_BY","Created by"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("PKG_" + extpkg.name.toUpperCase+"_FILTER_CREATED_BY","Created by"))
        languagesWords.addsLanguageKeys(new KVPairLanguage("JOPTION_SELECT_CREATED_BY","select a user"))
    }
}
