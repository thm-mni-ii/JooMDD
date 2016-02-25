package de.thm.icampus.ejsl.generator.pi.ExtendedEntity

import de.thm.icampus.ejsl.eJSL.Reference
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.eJSL.Entity

interface ExtendedReference extends Reference {
	
	def EList<ExtendedAttribute>getExtendedAttribute()
	def EList<ExtendedAttribute>getExtendedAttributeReferenced()
	def Entity getExtendedToEntity()
	def Entity getExtendedFromEntity()
}