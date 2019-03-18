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
	EList<ExtendedEntity> allExtendedEntity
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
		allExtendedEntity = new BasicEList<ExtendedEntity>
		for( s: this.sections){
			switch s {
				BackendSection :{
					backEndPagesReference.addAll(s.pageRef.map[t|new ExtendedPageReferenceImpl(t)])
					for(ExtendedPageReference pf: backEndPagesReference.filter[t | t.page instanceof DynamicPage]){
						var ExtendedDynamicPage extp = pf.extendedPage.extendedDynamicPageInstance
						for(ExtendedEntity entBackend: extp.extendedEntityList){
							if(allExtendedEntity.filter[t | t.name == entBackend.name].size == 0){
								allExtendedEntity.add(entBackend)
								for(ExtendedEntity refEntity: PlattformUtil.getAllReferenceOfEntity(entBackend)){
									if(allExtendedEntity.filter[t | t.name.equalsIgnoreCase(refEntity.name)].empty){
										allExtendedEntity.add(refEntity)
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
							if(allExtendedEntity.filter[t | t.name == entFrontend.name].size == 0){
							allExtendedEntity.add(entFrontend)
							for(ExtendedEntity refEntity: PlattformUtil.getAllReferenceOfEntity(entFrontend)){
									if(allExtendedEntity.filter[t | t.name.equalsIgnoreCase(refEntity.name)].empty){
										allExtendedEntity.add(refEntity)
									}
								}
							}
						}
					}	
					}
			}
		}
		for(ExtendedEntity ent: allExtendedEntity){
			if(ent == null)
			allExtendedEntity.remove(ent)
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
		for(ExtendedEntity ent : allExtendedEntity){
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
		return this.allExtendedEntity
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
}