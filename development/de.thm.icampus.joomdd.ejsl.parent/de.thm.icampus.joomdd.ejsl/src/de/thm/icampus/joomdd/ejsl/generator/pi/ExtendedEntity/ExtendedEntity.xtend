package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity

import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute

interface ExtendedEntity extends Entity {
	
	def EList<ExtendedAttribute> getExtendedAttributeList()
	def EList<ExtendedAttribute> getExtendedParentAttributeList()
	def Entity getInstance()
	def EList<ExtendedAttribute> getAllattribute()
	def EList<ExtendedReference> getExtendedReference()
	def boolean haveIdAttribute()
	def void putNewAttributeInEntity(Attribute e)
	def ExtendedAttribute searchIdAttribute()
	def EList<ExtendedReference>getallReferenceToEntity()
	def ExtendedAttribute getAttributeByName(String name)
	def EList<ExtendedEntity> getallEntityFromReferences()
	
	
}