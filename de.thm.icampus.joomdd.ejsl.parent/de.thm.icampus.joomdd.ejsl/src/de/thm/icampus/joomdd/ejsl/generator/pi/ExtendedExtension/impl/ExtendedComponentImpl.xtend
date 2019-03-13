package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.joomdd.ejsl.eJSL.BackendSection
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.eJSL.Feature
import de.thm.icampus.joomdd.ejsl.eJSL.FrontendSection
import de.thm.icampus.joomdd.ejsl.eJSL.PageReference
import de.thm.icampus.joomdd.ejsl.eJSL.impl.ComponentImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl.ExtendedDynamicPageImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterGroupImpl
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl.ExtendedPageImpl
import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil
import java.util.ArrayList
import java.util.HashMap
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.StaticLanguageValue
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug

class ExtendedComponentImpl extends ComponentImpl implements ExtendedComponent {
	
	Component instance
	EList<ExtendedPageReference> backEndPagesReference
	EList<ExtendedPageReference> fronEndpagesReference
	EList<ExtendedEntity> allextendedEntity
	EList<ExtendedParameterGroup> extendedParamaterGroups
	EList<ExtendedPage> allExtendedPage
	EList <ExtendedDynamicPage> allDynamicPage
	String extensionName 
	boolean hasFileOrImageLoader = false;
	new(Component comp){
		instance = comp
		this.name = PlattformUtil.slugify(comp.name)
		this.languages = comp.languages
		this.manifest = comp.manifest
		this.globalParamter = comp.globalParamter
		this.sections = comp.sections
		extensionName = "com_" + this.name.toLowerCase
		
		initListen()
	}
	
	def initListen() {
		backEndPagesReference = new BasicEList<ExtendedPageReference>
		fronEndpagesReference = new BasicEList<ExtendedPageReference>
		allExtendedPage = new BasicEList<ExtendedPage>
		allDynamicPage = new BasicEList<ExtendedDynamicPage>
		allextendedEntity = new BasicEList<ExtendedEntity>
		for( s: this.sections){
			switch s {
				BackendSection :{
					backEndPagesReference.addAll(s.pageRef.map[t|new ExtendedPageReferenceImpl(t)])
					for(ExtendedPageReference pf: backEndPagesReference.filter[t | t.page instanceof DynamicPage]){
						var ExtendedDynamicPage extp = pf.extendedPage.extendedDynamicPageInstance
						for(ExtendedEntity entBackend: extp.extendedEntityList){
							if(allextendedEntity.filter[t | t.name == entBackend.name].size == 0){
								allextendedEntity.add(entBackend)
								for(ExtendedEntity refEntity: PlattformUtil.getAllReferenceOfEntity(entBackend)){
									if(allextendedEntity.filter[t | t.name.equalsIgnoreCase(refEntity.name)].empty){
										allextendedEntity.add(refEntity)
									}
								}
							}
						}
					}
				}
				FrontendSection:{
					fronEndpagesReference.addAll(s.pageRef.map[t|new ExtendedPageReferenceImpl(t)])
					for(ExtendedPageReference pf: fronEndpagesReference.filter[t | t.page instanceof DynamicPage]){
						var ExtendedDynamicPage extp = pf.extendedPage.extendedDynamicPageInstance
						for(ExtendedEntity entFrontend: extp.extendedEntityList){
							if(allextendedEntity.filter[t | t.name == entFrontend.name].size == 0){
							allextendedEntity.add(entFrontend)
							for(ExtendedEntity refEntity: PlattformUtil.getAllReferenceOfEntity(entFrontend)){
									if(allextendedEntity.filter[t | t.name.equalsIgnoreCase(refEntity.name)].empty){
										allextendedEntity.add(refEntity)
									}
								}
							}
						}
					}	
					}
			}
		}
		for(ExtendedEntity ent: allextendedEntity){
			if(ent == null)
			allextendedEntity.remove(ent)
		}
		 extendedParamaterGroups = new BasicEList<ExtendedParameterGroup>
		 extendedParamaterGroups.addAll(this.globalParamter.map[t| new ExtendedParameterGroupImpl(t)])
		 
		 allExtendedPage.addAll(backEndPagesReference.map[t| t.extendedPage  ])
		 allExtendedPage.addAll(fronEndpagesReference.map[t | if(!allExtendedPage.contains(t)) t.extendedPage])
		 allDynamicPage.addAll((allExtendedPage.filter[t | t.extendedDynamicPageInstance != null]).map[ t | new ExtendedDynamicPageImpl(t.intance as DynamicPage)])
//		 if(instance.eContainer != null)
//		 initPagesForMappingsEntity()
      if( allDynamicPage.filter[t | t != null && t.detailsPage && t.haveFiletoLoad] .size >0 ){
      	hasFileOrImageLoader = true;
      }
					
       	
	}
	
	def initPagesForMappingsEntity() {
		var Feature container = (instance.eContainer as CMSExtension).feature
		var EList<Page> page = container.pages
		for(ExtendedEntity ent : allextendedEntity){
			if(allDynamicPage.filter[t | t.extendedEntityList.get(0).name.equalsIgnoreCase(ent.name)].empty && ent.isGenerated){
			   var IndexPage inPage = (page.filter[t | t.name.equalsIgnoreCase(ent.name+"List")]).get(0) as IndexPage
			   var DetailsPage detailsPage = (page.filter[t | t.name.equalsIgnoreCase(ent.name+"Details")]).get(0) as DetailsPage
			   var ExtendedPage pageList = new ExtendedPageImpl(inPage)
			   var ExtendedPage pageDetails = new ExtendedPageImpl(detailsPage)
			   backEndPagesReference.add(creaPageReference(pageList))
			   backEndPagesReference.add(creaPageReference(pageDetails))
			    fronEndpagesReference.add(creaPageReference(pageList))
			   fronEndpagesReference.add(creaPageReference(pageDetails))
			   allExtendedPage.add(pageDetails)
			   allExtendedPage.add(pageList)
			}
			
		}
	
	}
	
	private def ExtendedPageReference creaPageReference(ExtendedPage page) {
		var PageReference reference = EJSLFactory.eINSTANCE.createPageReference
		reference.page = page.intance
		
		return new ExtendedPageReferenceImpl(reference)
	}
	
	
	
	override getFrontEndExtendedPagerefence() {
		return this.fronEndpagesReference
	}
	
	override getBackEndExtendedPagerefence() {
		return this.backEndPagesReference
   	}
	
	override getAllExtendedEntity() {
		return this.allextendedEntity
	}
	
	override getInstance() {
		return this.instance
	}
	
	override getExtendedParameterGroupList() {
		return extendedParamaterGroups
	}
	
	override getAllExtendedPage() {
		return allExtendedPage
	}
	
	override extensionName() {
		return extensionName.toLowerCase
	}
	override hasFileToload(){
		return hasFileOrImageLoader;
	}
    
    /**
     * You can use this function to add a static language variable.
     * These are variables that have a constant value. 
     */
    override String addLanguage(ArrayList<String> keyArray, StaticLanguageValue staticLanguageValue) {
        keyArray.add(staticLanguageValue.key)
        return addLanguage(keyArray, staticLanguageValue.value)
    }
    
    /**
     * You can add language key-value pairs using this function.
     * The given key (array) will only be added if the key does not exist already.
     * 
     * @param ArrayList keyArray
     * @param String    value
     * @return String   Returns the to upper case representation of the given keyArray
     */
    override String addLanguage(ArrayList<String> keyArray, String value) {
        val String upperCaseKey = keyArray.map[ k | 
            Slug.slugify(k)
        ].join("_").toUpperCase
        var String tmpValue = value
        
        // Use the key as value when the value is empty.
        if (value.trim.empty === true) {
            tmpValue = upperCaseKey        
        }
        
        // We need this (constant) variable for the loop below.
        val String languageValue = tmpValue
        
        // Iterate over each language and add the key if it does not exist.
        this.languages.forEach[ l | 
            // Add the new key only if it does not exist already
            var alreadyDefinedKey = l.keyvaluepairs.findFirst[ kv | 
                kv.name.equalsIgnoreCase(upperCaseKey)
            ]
            if (alreadyDefinedKey === null)
            {
                // Create the new key-value pair and add it to the language
                var keyValuePair  = EJSLFactory.eINSTANCE.createKeyValuePair
                keyValuePair.name = upperCaseKey
                keyValuePair.value = languageValue
                l.keyvaluepairs.add(keyValuePair)
            }
            else
            {
                if (alreadyDefinedKey.value.equalsIgnoreCase(languageValue) === false) {
                    println('''ExtendedComponentImpl: The given key «upperCaseKey» with the value  «languageValue» is already defined with another value «alreadyDefinedKey.value»''')
                }
            }
        ]
        
        return upperCaseKey
    }	
}