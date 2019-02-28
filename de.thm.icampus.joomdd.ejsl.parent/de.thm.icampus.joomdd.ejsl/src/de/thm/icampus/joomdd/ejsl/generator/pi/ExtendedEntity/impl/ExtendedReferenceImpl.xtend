package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.ReferenceImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.eJSL.Entity

class ExtendedReferenceImpl extends ReferenceImpl implements ExtendedReference {
	
	EList <ExtendedAttribute> extendedAttributes
	EList <ExtendedAttribute> referencedExtendedAttributes
	Entity toEntity
	Entity fromEntity
	
	new(Reference e, Entity from){
		this.attribute = e.attribute
		this.attributerefereced = e.attributerefereced
		entity = e.entity
		toEntity = e.entity
		if(from === null){
			fromEntity = e.attribute.get(0).eContainer as Entity
		}else{
			fromEntity = from
		}
		this.preserve = e.preserve
		this.upper = e.upper
		this.lower = e.lower
		
		initLists()
	}
	
	def initLists() {
		extendedAttributes = new BasicEList<ExtendedAttribute>()
		referencedExtendedAttributes = new BasicEList<ExtendedAttribute>()
		extendedAttributes.addAll( attribute.map[t | new ExtendedAttributeImpl(t)])
		referencedExtendedAttributes.addAll(attributerefereced.map[t | new ExtendedAttributeImpl(t) ])
	}
	
	override getExtendedAttributes() {
		return this.extendedAttributes
	}
	
	override getReferencedExtendedAttributes() {
		return this.referencedExtendedAttributes
	}
	
	override getDestinationEntity() {
		return this.toEntity
	}
	override getSourceEntity() {
		return this.fromEntity
	}
	
}