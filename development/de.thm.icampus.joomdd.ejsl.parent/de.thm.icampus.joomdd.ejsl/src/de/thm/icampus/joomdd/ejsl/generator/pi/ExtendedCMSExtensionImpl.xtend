package de.thm.icampus.joomdd.ejsl.generator.pi

import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension
import de.thm.icampus.joomdd.ejsl.eJSL.impl.CMSExtensionImpl
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import org.eclipse.emf.common.util.BasicEList

class ExtendedCMSExtensionImpl extends CMSExtensionImpl implements ExtendedCMSExtension{
	CMSExtension instance
	EList<ExtendedParameter> globalparameterExtended
	EList<ExtendedParameterGroup> paramtergroupExtended
	EList<ExtendedEntity> entitiesExtended
	EList<ExtendedExtensions> extensionsExtended
	
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
			paramtergroupExtended = new BasicEList<ExtendedParameterGroup>
			globalparameterExtended = new BasicEList<ExtendedParameter>
			
			
		
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