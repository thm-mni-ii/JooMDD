package de.thm.icampus.ejsl.generator.pi.ExtendedEntity

import de.thm.icampus.ejsl.eJSL.Reference
import org.eclipse.emf.common.util.EList

interface ExtendedReference extends Reference {
	
	def EList<ExtendedAttribute>getExtendedAttribute()
	def EList<ExtendedAttribute>getExtendedAttributeReferenced()
	def ExtendedEntity getExtendedEntity()
}