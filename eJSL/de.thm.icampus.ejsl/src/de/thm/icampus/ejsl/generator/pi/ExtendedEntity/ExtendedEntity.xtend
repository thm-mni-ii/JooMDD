package de.thm.icampus.ejsl.generator.pi.ExtendedEntity

import de.thm.icampus.ejsl.eJSL.Entity
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.eJSL.Attribute

interface ExtendedEntity extends Entity {
	
	def EList<ExtendedAttribute> getExtendedAttributeList()
	def EList<ExtendedAttribute> getExtendedParentAttributeList()
	def Entity getInstance()
	def EList<ExtendedAttribute> getAllattribute()
	def boolean haveIdAttribute()
	def void putNewAttributeInEntity(Attribute e)
	def ExtendedAttribute searchIdAttribute()
	
	
}