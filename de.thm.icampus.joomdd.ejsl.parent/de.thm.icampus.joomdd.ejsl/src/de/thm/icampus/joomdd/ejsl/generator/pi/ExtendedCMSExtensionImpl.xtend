package de.thm.icampus.joomdd.ejsl.generator.pi

import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension
import de.thm.icampus.joomdd.ejsl.eJSL.impl.CMSExtensionImpl
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterGroupImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl.ExtendedPageImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedExtensions
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedExtensionsImpl

class ExtendedCMSExtensionImpl extends CMSExtensionImpl implements ExtendedCMSExtension{
	CMSExtension instance
	EList<ExtendedParameter> globalparameterExtended
	EList<ExtendedParameterGroup> parametergroupExtended
	EList<ExtendedEntity> entitiesExtended
	EList<ExtendedExtensions> extensionsExtended
	EList<ExtendedPage>pagesextended
	
	new(CMSExtension model){
		instance = model
		this.datatypes = model.datatypes
		this.globalparameters = model.globalparameters
		this.parametergroups = model.parametergroups
		this.feature = model.feature
		this.extensions = model.extensions
		init()
	}
	
	def init() {
			val EList<Reference> ref = new BasicEList<Reference>
			this.feature.entities.forEach[t | ref.addAll(t.references)]
			entitiesExtended = new BasicEList<ExtendedEntity>
			pagesextended = new BasicEList<ExtendedPage>
			extensionsExtended = new BasicEList<ExtendedExtensions>
			parametergroupExtended = new BasicEList<ExtendedParameterGroup>
			globalparameterExtended = new BasicEList<ExtendedParameter>
			parametergroupExtended.addAll(this.parametergroups.map(t | new ExtendedParameterGroupImpl(t)))
			globalparameterExtended.addAll(this.globalparameters.map[t | new ExtendedParameterImpl(t)])
			entitiesExtended.addAll(this.feature.entities.map[t | new ExtendedEntityImpl(t)])
			pagesextended.addAll(this.feature.pages.map[t | new ExtendedPageImpl(t)])
			extensionsExtended.addAll(this.extensions.map[t | new ExtendedExtensionsImpl(t)])
			
			
			
		
	}
	
	override getGlobalparameterExtended() {
		return globalparameterExtended
	}
	
	override getparameterGroupExtended() {
		return parametergroupExtended
	}
	
	override getEntitiesextended() {
		return entitiesExtended
	}
	
	override getPageExtended() {
		return pagesextended
	}
	
	override getExtensionsExtended() {
		return extensionsExtended
	}
	
}