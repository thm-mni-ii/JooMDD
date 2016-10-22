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
			extensionsExtended = new BasicEList<ExtendedExtensions>
			parametergroupExtended = new BasicEList<ExtendedParameterGroup>
			globalparameterExtended = new BasicEList<ExtendedParameter>
			parametergroupExtended.addAll(this.parametergroups.map(t | new ExtendedParameterGroupImpl(t)))
			globalparameterExtended.addAll(this.globalparameters.map[t | new ExtendedParameterImpl(t)])
			entitiesExtended.addAll(this.feature.entities.map[t | new ExtendedEntityImpl(t)])
			pagesextended.addAll(this.feature.pages.map(t | new ExtendedPageImpl(t)))
			
			
			
		
	}
	
	override getGlobalparameterExtended() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getparamterGroupExtended() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getEntitiesextended() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getPageExtended() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getExtensionsExtended() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}