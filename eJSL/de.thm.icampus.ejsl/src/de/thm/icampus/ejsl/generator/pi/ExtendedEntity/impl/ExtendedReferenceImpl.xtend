package de.thm.icampus.ejsl.generator.pi.ExtendedEntity.impl

import de.thm.icampus.ejsl.eJSL.impl.ReferenceImpl
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.ejsl.eJSL.Reference
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import org.eclipse.emf.common.util.BasicEList

class ExtendedReferenceImpl extends ReferenceImpl implements ExtendedReference {
	
	 EList <ExtendedAttribute> extendedAttribute
	  EList <ExtendedAttribute> extendedAttributerefereced
	  ExtendedEntity extendedEntity
	new(Reference e){
		this.attribute = e.attribute
		this.attributerefereced = e.attributerefereced
		entity = e.entity
		extendedEntity = new ExtendedEntityImpl(e.entity)
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
	
	override getExtendedEntity() {
		return this.extendedEntity
	}
	
	
	
}