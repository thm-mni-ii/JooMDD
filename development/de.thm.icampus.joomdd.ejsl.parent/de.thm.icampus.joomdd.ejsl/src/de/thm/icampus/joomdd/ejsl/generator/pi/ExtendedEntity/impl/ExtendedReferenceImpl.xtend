package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.ReferenceImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.eJSL.Entity

class ExtendedReferenceImpl extends ReferenceImpl implements ExtendedReference {
	
	 EList <ExtendedAttribute> extendedAttribute
	  EList <ExtendedAttribute> extendedAttributerefereced
	   Entity toEntity
	  Entity fromEntity
	
	new(Reference e, Entity from){
		this.attribute = e.attribute
		this.attributerefereced = e.attributerefereced
		entity = e.entity
		toEntity = e.entity
		this.legacy = e.legacy
		fromEntity = from
		initList()
		
	}
	
	def initList() {
		extendedAttribute = new BasicEList<ExtendedAttribute>()
		extendedAttributerefereced = new BasicEList<ExtendedAttribute>()
		extendedAttribute.addAll( attribute.map[t | new ExtendedAttributeImpl(t)])
		extendedAttributerefereced.addAll(attributerefereced.map[t | new ExtendedAttributeImpl(t) ])
	}
	
	override getExtendedAttribute() {
		return this.extendedAttribute
	}
	
	override getExtendedAttributeReferenced() {
		return this.extendedAttributerefereced
	}
	
	override getExtendedToEntity() {
		return this.toEntity
	}
	override getExtendedFromEntity() {
		return this.fromEntity
	}
	
	
	
	
}