package de.thm.icampus.ejsl.ressourceTransformator

import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.EJSLModel
import de.thm.icampus.ejsl.eJSL.CMSExtension
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.EJSLFactory
import de.thm.icampus.ejsl.eJSL.StandardTypes
import de.thm.icampus.ejsl.eJSL.StandardTypeKinds
import de.thm.icampus.ejsl.eJSL.Reference

class JoomlaTranformator {
	
	EJSLModel instance
	CMSExtension cmsExtension
	EList<Entity> entityList
	
	new(EJSLModel model){
		this.instance = model
		
		
	}
	

	
	def void completeCMSExtension(){
		
		switch(instance.ejslPart){
			CMSExtension:{
				cmsExtension = instance.ejslPart as CMSExtension
				entityList = cmsExtension.feature.entities
				completeEntity()
			}
		}
	}
	

	 private def void completeEntity(){
    	
    	for(Entity ent: entityList){
    		completeAttributeOfEntity(ent)
    	}
    	for(Entity ent: entityList){
    		completeReferenceOfEntity(ent)
    		putTheReferenceAttribute(ent)
    	}
    	
    }
	
	private def completeAttributeOfEntity(Entity ent) {
		if(!ent.haveIdAttribute()){
			var Attribute id = EJSLFactory.eINSTANCE.createAttribute
			id.name = "id"
			var StandardTypes typeid = EJSLFactory.eINSTANCE.createStandardTypes
			typeid.type =  StandardTypeKinds.INTEGER
			typeid.notnull = true
			typeid.autoincrement = true
			id.type = typeid
			ent.attributes.add(id)
			
		}
		var Attribute asset_id = EJSLFactory.eINSTANCE.createAttribute
			asset_id.name = "asset_id"
			var StandardTypes type_asset_id = EJSLFactory.eINSTANCE.createStandardTypes
			type_asset_id.type =  StandardTypeKinds.INTEGER
			type_asset_id.notnull = true
			type_asset_id.^default = "0"
			asset_id.type = type_asset_id
			ent.attributes.add(asset_id)
						
		var Attribute state = EJSLFactory.eINSTANCE.createAttribute
			state.name = "state"
			var StandardTypes type_state = EJSLFactory.eINSTANCE.createStandardTypes
			type_state.type =  StandardTypeKinds.BOOLEAN
			type_state.notnull = true
			state.type = type_state
      		ent.attributes.add(state)	
				
		var Attribute ordering = EJSLFactory.eINSTANCE.createAttribute
			ordering.name = "ordering"
			var StandardTypes type_ordering = EJSLFactory.eINSTANCE.createStandardTypes
			type_ordering.type =  StandardTypeKinds.INTEGER
			type_ordering.notnull = true
			ordering.type = type_ordering
			ent.attributes.add(ordering)
			
		var Attribute checked_out_time = EJSLFactory.eINSTANCE.createAttribute
			checked_out_time.name = "checked_out_time"
			var StandardTypes type_checked_out_time= EJSLFactory.eINSTANCE.createStandardTypes
			type_checked_out_time.type =  StandardTypeKinds.DATETIME
			type_checked_out_time.notnull = true
			type_checked_out_time.^default = "0000-00-00 00:00:00"
			checked_out_time.type = type_checked_out_time
			ent.attributes.add(checked_out_time)
			
		var Attribute checked_out = EJSLFactory.eINSTANCE.createAttribute
			checked_out.name = "checked_out"
			var StandardTypes type_checked_out = EJSLFactory.eINSTANCE.createStandardTypes
			type_checked_out.type =  StandardTypeKinds.INTEGER
			type_checked_out.notnull = true
			checked_out.type = type_checked_out
			ent.attributes.add(checked_out)
			
		var Attribute created_by = EJSLFactory.eINSTANCE.createAttribute
			created_by.name = "created_by"
			var StandardTypes type_created_by = EJSLFactory.eINSTANCE.createStandardTypes
			type_created_by.type =  StandardTypeKinds.INTEGER
			type_created_by.notnull = true
			created_by.type = type_created_by
			ent.attributes.add(created_by)
		
		var Attribute published = EJSLFactory.eINSTANCE.createAttribute
			published.name = "published"
			var StandardTypes type_published = EJSLFactory.eINSTANCE.createStandardTypes
			type_published.type =  StandardTypeKinds.BOOLEAN
			published.type = type_published
			ent.attributes.add(published)
		
		var Attribute params = EJSLFactory.eINSTANCE.createAttribute
			params.name = "params"
			var StandardTypes type_params = EJSLFactory.eINSTANCE.createStandardTypes
			type_params.type =  StandardTypeKinds.TEXTAREA
			params.type = type_params
			ent.attributes.add(params)
		
		for(Attribute attr: ent.attributes){
			 if(attr.id){
			 	attr.withattribute = ent.searchIdAttribute
			 }
		}
	}
	
	private def Attribute searchIdAttribute(Entity entity){
		for(Attribute e: entity.attributes){
			if(e.name.equalsIgnoreCase("id"))
			return e
		}
	}
	
	private def boolean haveIdAttribute(Entity entity){
		for(Attribute e: entity.attributes){
			if(e.name.equalsIgnoreCase("id"))
			return true
		}
		return false
	}
	
	private def void  completeReferenceOfEntity(Entity ent) {
		
		for(Reference ref : ent.references){
			if(ref.id){
				if(ent.searchIdAttribute != null){
				ref.attributerefereced.add(ref.entity.searchIdAttribute)
				ref.id=false
				
				}
			}
		}
	}
	
	def void putTheReferenceAttribute( Entity ent){
		for(Reference ref:ent.references ){
			var Entity referenceEntity = ref.entity
			for(Attribute attrRef: ref.attributerefereced){
				var Attribute uniqWith = attrRef.withattribute
				if(uniqWith != null && getAttributeReferenceForId(ent, referenceEntity,uniqWith) == null){
					var Attribute newAttribute = EJSLFactory.eINSTANCE.createAttribute
					newAttribute.name = referenceEntity.toString.toLowerCase + "_" + uniqWith.name
					newAttribute.type = uniqWith.type
					ent.attributes.add(newAttribute)
					ref.attribute.add(newAttribute)
					ref.attributerefereced.add(uniqWith)
				}
			}
		}
	}
	
	def Attribute getAttributeReferenceForId(Entity ent, Entity referencedEntity, Attribute referenced){
		for(Attribute a:ent.attributes ){
			System.out.println("Hallo " + a.name)
			System.out.println("Hallo " + ent.name)
			System.out.println("Hallo2 " + referencedEntity.name)
			System.out.println("Hallo3 " + referenced.name)
			
			if(a.name.equalsIgnoreCase(referencedEntity.toString.toLowerCase +  "_"+ referenced.name))
			return a;
		}
		for(Reference ref:ent.references ){
			
			if(ref.entity.name == ent.name && ref.attributerefereced.contains(referenced)){
				var int index = ref.attributerefereced.indexOf(referenced)
				return ref.attribute.get(index)
			}
		}
		return null
	}
	
}
