package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.EntityImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.eJSL.Feature
import de.thm.icampus.joomdd.ejsl.eJSL.Reference

class ExtendedEntityImpl extends EntityImpl implements ExtendedEntity {
	
	Entity instance
	 EList<ExtendedAttribute> extendedAttributeList
	 EList<ExtendedAttribute> extendedParentAttributeList
	 EList<ExtendedAttribute> allAttribute
	 EList<ExtendedReference> extendedReference
	 EList<ExtendedReference> allReferenceToEntity
	new(Entity entity){
		entity.name = PlattformIUtil.slugify(entity.name)
		this.name = entity.name
		this.supertype = entity.supertype
		this.attributes = entity.attributes
		this.references = entity.references
		instance = entity
		initListen()
		
	}
	override getExtendedAttributeList() {
		return extendedAttributeList

	}
	
	override getExtendedParentAttributeList() {
		return extendedParentAttributeList
	}
	
	override getInstance() {
		return instance

	}
	
	override getAllattribute() {
		return allAttribute
	}
	
	def void initListen(){
		extendedAttributeList = new BasicEList<ExtendedAttribute>
		allAttribute = new BasicEList<ExtendedAttribute>
		allReferenceToEntity = new BasicEList<ExtendedReference>
		extendedReference = new BasicEList<ExtendedReference>
		extendedAttributeList.addAll(this.attributes.map[t| PlattformIUtil.transformAttribute(t)])
		extendedParentAttributeList = searchAttributeParent()
		
		allAttribute.addAll(extendedAttributeList)
		allAttribute.addAll(extendedParentAttributeList)
		extendedReference.addAll(references.map[t | new ExtendedReferenceImpl(t, this.instance)])
		var EList<Entity> allEntity = (instance.eContainer as Feature).entities
		for(Entity ent: allEntity){
			if(ent.references != null){
			var Iterable<Reference> listRef = ent.references.filter[t | t.entity.name == instance.name]
			for(Reference ref: listRef)
			allReferenceToEntity.add( new ExtendedReferenceImpl(ref, ent))
			
			}
		}
		
		
		
		
	}
	
	def  EList<ExtendedAttribute> searchAttributeParent() {
		var EList<ExtendedAttribute> result = new BasicEList<ExtendedAttribute>
		var Entity parent = this.supertype
		
		while(parent != null){
			result.addAll(parent.attributes.map[t|PlattformIUtil.transformAttribute(t)])
			parent = parent.supertype
			
		}
		
		return result
	}
	 override boolean haveIdAttribute(){
    	for(attr: extendedAttributeList){
    		if(attr.name.toLowerCase.equalsIgnoreCase("id")){
    			attr.name = "id"
    			return true
    		}
    	}
    	return false
    }
				
	override putNewAttributeInEntity(Attribute e) {
		attributes.add(e)
		allAttribute.add(PlattformIUtil.transformAttribute(e))	}
		
		override searchIdAttribute() {
			for( attr: extendedAttributeList){
				if(attr.name.toLowerCase.equalsIgnoreCase("id")){
					return attr
				}
			}
			return null;
		}
		
		override getExtendedReference() {
			return extendedReference
		}
		
		override getallReferenceToEntity() {
			return allReferenceToEntity
		}
		
		override getAttributeByName(String name) {
			for(ExtendedAttribute attr: extendedAttributeList){
				if(attr.name.equalsIgnoreCase(name))
				   return attr;
			}
			return null
		}
		
	
}