package de.thm.icampus.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.impl.ComponentImpl
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.ejsl.generator.pi.util.PlattformIUtil
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.ejsl.eJSL.Section
import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.FrontendSection
import de.thm.icampus.ejsl.eJSL.PageReference
import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.ejsl.generator.pi.util.impl.ExtendedParameterGroupImpl

class ExtendedCompoenetImpl extends ComponentImpl implements ExtendedComponent {
	
	Component instance
	EList<ExtendedPageReference> backEndPagesReference
	EList<ExtendedPageReference> fronEndpagesReference
	EList<ExtendedEntity> allextendedEntity
	EList<ExtendedParameterGroup> extendedParamaterGroups
	new(Component comp){
		instance = comp
		this.name = PlattformIUtil.slugify(comp.name)
		this.languages = comp.languages
		this.manifest = comp.manifest
		this.globalParamter = comp.globalParamter
		this.sections = comp.sections
		initListen()
	}
	
	def initListen() {
		backEndPagesReference = new BasicEList<ExtendedPageReference>
		fronEndpagesReference = new BasicEList<ExtendedPageReference>
		allextendedEntity = new BasicEList<ExtendedEntity>
		for( s: this.sections){
			switch s {
				BackendSection :{
					backEndPagesReference.addAll(s.pageRef.map[t|new ExtendedPageReferenceImpl(t)])
					for(ExtendedPageReference pf: backEndPagesReference.filter[t | t.page instanceof DynamicPage]){
						var ExtendedDynamicPage extp = pf.extendedPage.extendedDynamicPageInstance
						allextendedEntity.addAll(extp.extendedEntityList.filter[t|!allextendedEntity.contains(t)])
					}
				}
				FrontendSection:{
					fronEndpagesReference.addAll(s.pageRef.map[t|new ExtendedPageReferenceImpl(t)])
					for(ExtendedPageReference pf: fronEndpagesReference.filter[t | t.page instanceof DynamicPage]){
						var ExtendedDynamicPage extp = pf.extendedPage.extendedDynamicPageInstance
						allextendedEntity.addAll(extp.extendedEntityList.filter[t|!allextendedEntity.contains(t)])
					}	
					}
			}
		}
		 extendedParamaterGroups = new BasicEList<ExtendedParameterGroup>
		 extendedParamaterGroups.addAll(this.globalParamter.map[t| new ExtendedParameterGroupImpl(t)])
       	
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
	
}