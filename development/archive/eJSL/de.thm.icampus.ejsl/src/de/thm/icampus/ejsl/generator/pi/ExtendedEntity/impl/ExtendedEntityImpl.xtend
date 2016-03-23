package de.thm.icampus.ejsl.generator.pi.ExtendedEntity.impl

import de.thm.icampus.ejsl.eJSL.impl.EntityImpl
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.ejsl.eJSL.Entity
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.generator.pi.util.PlattformIUtil

class ExtendedEntityImpl extends EntityImpl implements ExtendedEntity {
	
	Entity instance
	 EList<ExtendedAttribute> extendedAttributeList
	 EList<ExtendedAttribute> extendedParentAttributeList
	 EList<ExtendedAttribute> allAttribute
	new(Entity entity){
		this.name = PlattformIUtil.slugify(entity.name)
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
		extendedAttributeList.addAll(this.attributes.map[t| PlattformIUtil.transformAttribute(t)])
		extendedParentAttributeList = searchAttributeParent()
		
		allAttribute.addAll(extendedAttributeList)
		allAttribute.addAll(extendedParentAttributeList)
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
	
}