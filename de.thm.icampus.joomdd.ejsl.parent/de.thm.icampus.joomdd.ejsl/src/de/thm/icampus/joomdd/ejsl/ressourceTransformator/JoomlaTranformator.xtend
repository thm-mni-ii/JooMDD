package de.thm.icampus.joomdd.ejsl.ressourceTransformator

import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel
import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypeKinds
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.eJSL.Type
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.eJSL.Link
import de.thm.icampus.joomdd.ejsl.eJSL.ContextLink
import de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter
import de.thm.icampus.joomdd.ejsl.eJSL.StaticPage
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage
import de.thm.icampus.joomdd.ejsl.eJSL.Datatype
import org.eclipse.emf.ecore.EObject

/**
 *  Transforme and complete the data of a ejls instance for the generator
 */
class JoomlaTranformator {
	
	EJSLModel instance
	CMSExtension cmsExtension
	EList<Entity> entityList
	
	
	
	new(EJSLModel model){
		this.instance = model
		
		
	}
	

	/**
	 * Transforme and complete and comple the data of the entities and pages,
	 * for the entities, it solve keys pairs and create the primary attribute. 
	 * For the pages complete the pagelinks.
	 */
	def void completeCMSExtension(){
		
		switch(instance.ejslPart){
			CMSExtension:{
				cmsExtension = instance.ejslPart as CMSExtension
				entityList = cmsExtension.feature.entities
				completeEntity()
			}
		}
	}
	
	/**
	 * 
	 * 
	 */
	 private def void completeEntity(){
    	
    	for(Entity ent: entityList){
    		completeJoomlaAttributeOfEntity(ent)
    	}
    	
    	
    }
	/**
	 * Create a primary attribute, when it is not exist,
	 * replace the id symbol with the primary keys,
	 * complete the reference with the new generated primary keys or the keys pair
	 * and create many attributes for the joomnla specified generator
	 * @param Entity ent contains a entity
	 */
	private def completeJoomlaAttributeOfEntity(Entity ent) {
	
		if(!haveAttribute(ent,"asset_id" )){
		var Attribute asset_id = EJSLFactory.eINSTANCE.createAttribute
			asset_id.name = "asset_id"
			var StandardTypes type_asset_id = EJSLFactory.eINSTANCE.createStandardTypes
			type_asset_id.type =  StandardTypeKinds.INTEGER
			type_asset_id.notnull = true
			type_asset_id.^default = "0"
			asset_id.type = type_asset_id
			ent.attributes.add(asset_id)
		}
		if(!haveAttribute(ent,"state")){				
		var Attribute state = EJSLFactory.eINSTANCE.createAttribute
			state.name = "state"
			var StandardTypes type_state = EJSLFactory.eINSTANCE.createStandardTypes
			type_state.type =  StandardTypeKinds.BOOLEAN
			type_state.notnull = true
			state.type = type_state
      		ent.attributes.add(state)	
		}
		if(!haveAttribute(ent,"ordering")){		
		var Attribute ordering = EJSLFactory.eINSTANCE.createAttribute
			ordering.name = "ordering"
			var StandardTypes type_ordering = EJSLFactory.eINSTANCE.createStandardTypes
			type_ordering.type =  StandardTypeKinds.INTEGER
			type_ordering.notnull = true
			ordering.type = type_ordering
			ent.attributes.add(ordering)
		}
		if(!haveAttribute(ent,"checked_out_time")){	
		var Attribute checked_out_time = EJSLFactory.eINSTANCE.createAttribute
			checked_out_time.name = "checked_out_time"
			var StandardTypes type_checked_out_time= EJSLFactory.eINSTANCE.createStandardTypes
			type_checked_out_time.type =  StandardTypeKinds.DATETIME
			type_checked_out_time.notnull = true
			type_checked_out_time.^default = "0000-00-00 00:00:00"
			checked_out_time.type = type_checked_out_time
			ent.attributes.add(checked_out_time)
		}
		if(!haveAttribute(ent,"checked_out")){	
		var Attribute checked_out = EJSLFactory.eINSTANCE.createAttribute
			checked_out.name = "checked_out"
			var StandardTypes type_checked_out = EJSLFactory.eINSTANCE.createStandardTypes
			type_checked_out.type =  StandardTypeKinds.INTEGER
			type_checked_out.notnull = true
			type_checked_out.^default = "0"
			checked_out.type = type_checked_out
			ent.attributes.add(checked_out)
		}	
		if(!haveAttribute(ent,"created_by")){	
		var Attribute created_by = EJSLFactory.eINSTANCE.createAttribute
			created_by.name = "created_by"
			var StandardTypes type_created_by = EJSLFactory.eINSTANCE.createStandardTypes
			type_created_by.type =  StandardTypeKinds.INTEGER
			type_created_by.notnull = true
			type_created_by.^default = "0"
			created_by.type = type_created_by
			ent.attributes.add(created_by)
		}
		if(!haveAttribute(ent,"published")){	
		var Attribute published = EJSLFactory.eINSTANCE.createAttribute
			published.name = "published"
			var StandardTypes type_published = EJSLFactory.eINSTANCE.createStandardTypes
			type_published.type =  StandardTypeKinds.BOOLEAN
			published.type = type_published
			ent.attributes.add(published)
		}
		if(!haveAttribute(ent,"params")){	
		var Attribute params = EJSLFactory.eINSTANCE.createAttribute
			params.name = "params"
			var StandardTypes type_params = EJSLFactory.eINSTANCE.createStandardTypes
			type_params.type =  StandardTypeKinds.TEXT
			params.type = type_params
			ent.attributes.add(params)}
		
		
	}
	
	
		/**
	 * Check if the entity contains a attribute
	 * @param Entity entity        contains a entity
	 * @param String attributeNam  contains a attribute name
	 */
	private def boolean haveAttribute(Entity entity, String attributeName){
		for(Attribute e: entity.attributes){
			if(e.name.equals(attributeName))
			return true
		}
		return false
	}
	
	
}
