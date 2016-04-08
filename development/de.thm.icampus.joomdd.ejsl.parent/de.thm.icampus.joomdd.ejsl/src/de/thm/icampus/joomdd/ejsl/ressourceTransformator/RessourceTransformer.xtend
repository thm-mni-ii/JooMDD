package de.thm.icampus.joomdd.ejsl.ressourceTransformator

import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel
import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension
import de.thm.icampus.joomdd.ejsl.eJSL.Feature
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.eJSL.DetailPageField
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypes
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypeKinds
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes
import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.eJSL.Type
import java.util.Iterator

class RessourceTransformer {
	EJSLModel modelInstance
	CMSExtension cmsextension
	Feature featurs
	new (EJSLModel model){
		modelInstance = model
		
		cmsextension = model.ejslPart as CMSExtension
		featurs = cmsextension.feature
		
	}
	def dotransformation(){
		createMappingsTable(featurs.entities)
		completeDetailsPage();
		completeIndexPage();
		var JoomlaTranformator jt = new JoomlaTranformator(modelInstance)
		jt.completeCMSExtension
		
	}
	
	def completeIndexPage() {
			for(Page page: featurs.pages.filter[t | t instanceof IndexPage]){
			var IndexPage dp = page as IndexPage
			completeColUmnAndFilterList(dp)
		}
	}
	
	def completeDetailsPage(){
		
		for(Page page: featurs.pages.filter[t | t instanceof DetailsPage]){
			var DetailsPage dp = page as DetailsPage
			completeTableColumnAndEditedFields(dp)
			for(DetailPageField field: dp.editfields){
				if(field.htmltype == null){
				  field.htmltype = parseAttributeType(field.attribute).htmltype
				    
				   println(field.attribute+ " Html Type  "+field.htmltype + " -> " + parseAttributeType(field.attribute).htmltype)
				}
			}
		}
	}
	
	private def completeTableColumnAndEditedFields(DetailsPage page) {
		if(page.editfields.empty && !page.tablecolumns.empty){
			for(Attribute attr: page.tablecolumns){
				page.editfields.add(parseAttributeType(attr))
			}
		}
		if(!page.editfields.empty && page.tablecolumns.empty){
			
			for( DetailPageField editedAttr: page.editfields){
				page.tablecolumns.add(editedAttr.attribute)
			}
		}
		if(page.editfields.empty && page.tablecolumns.empty){
			for(Attribute attr: page.entities.get(0).attributes){
				page.tablecolumns.add(attr)
				page.editfields.add(parseAttributeType(attr))
			}
		}
	}
	private def completeColUmnAndFilterList(IndexPage page) {
		if(page.filters.empty && !page.tablecolumns.empty){
			for(Attribute attr: page.tablecolumns){
				page.filters.add(attr)
			}
		}
		if(!page.filters.empty && page.tablecolumns.empty){
			
			for(Attribute attr: page.filters){
				page.tablecolumns.add(attr)
			}
		}
		if(page.filters.empty&& page.tablecolumns.empty){
			for(Attribute attr: page.entities.get(0).attributes){
				page.tablecolumns.add(attr)
				page.filters.add(attr)
			}
		}
	}
	
	//todo   
	def void createMappingsTable(EList<Entity> entitiesList){ 
		
		var EList<Entity> newEntity = new BasicEList<Entity>()
		for(Entity ent: entitiesList){
			var EList<Reference> toDeleteReference = new BasicEList<Reference>()
			var EList<Reference>toAddReference = new BasicEList<Reference>()
			for(Reference ref: ent.references){
				 if(ref.entity.haveReferenTo(ent,ref.upper)){
				 	if(!ref.entity.existingReferenceBetweenEntity(ent,newEntity)){
				 		var String mappingEntityName = "mapping_" + ent.name + "_" + ref.entity.name
				 		var Entity mappingEntity = EJSLFactory.eINSTANCE.createEntity
				 		mappingEntity.name = mappingEntityName
				 		mappingEntity.attributes.addAll(ref.attribute.map[t | copyAttribute(t, ent)])
				 		mappingEntity.attributes.addAll(ref.attributerefereced.map[t | copyAttribute(t, ref.entity)])
				 		mappingEntity.references.addAll(solveReference(ref,mappingEntity,ent,ref.entity))
				 		newEntity.add(mappingEntity)
				 		println("generateMappingsTable " + mappingEntityName)
				 		deleteReferenceToEntity(ref.entity, ent,mappingEntity)
				 		toDeleteReference.add(ref)
				 		toAddReference.add(createNewReverseReference(mappingEntity, ent,ref))
				 	}
				 }
			}
			if(!toDeleteReference.isEmpty)
			ent.references.removeAll(toDeleteReference)
			
			if(!toAddReference.isEmpty)
			ent.references.addAll(toAddReference)
			
		}
		entitiesList.addAll(newEntity)
		
	}
	
	
	
	private def EList<Reference> solveReference(Reference reference, Entity mappingEntity,Entity fromEntity,Entity toEntity ) {
		var Reference nRef =  EJSLFactory.eINSTANCE.createReference
		nRef.attribute.addAll(mappingEntity.attributes.filter[t | isAttributeOfEntity(t,fromEntity.name)])
		nRef.entity = fromEntity
		nRef.attributerefereced.addAll(reference.attribute)
		nRef.lower = "0"
		nRef.upper = "1"
		var Reference mRef =  EJSLFactory.eINSTANCE.createReference
		mRef.attribute.addAll(mappingEntity.attributes.filter[t | isAttributeOfEntity(t,toEntity.name)])
		mRef.entity = toEntity
		mRef.attributerefereced.addAll(reference.attributerefereced)
		mRef.lower = "0"
		mRef.upper = "1"
		var EList<Reference> result = new BasicEList<Reference>()
		result.add(nRef)
		result.add(mRef)
		return result
		
	}
	
	private def isAttributeOfEntity(Attribute attribute, String entityName) {
		if(attribute.name.startsWith(entityName + "_"))
		return true
		
		return false
	}
	
	
	
	private def Attribute copyAttribute(Attribute attribute, Entity entity) {
		var Attribute newAttr = EJSLFactory.eINSTANCE.createAttribute
		newAttr.name = entity.name + "_" + attribute.name
		newAttr.type = Util.copyType(attribute.type)

		return newAttr
	}

	
	
	private def boolean existingReferenceBetweenEntity(Entity to, Entity from,EList<Entity> newEntityList){
		var String mappingNameForward = "mapping_" + to.name + "_" + from.name
		var String mappingNameReverse= "mapping_" +from.name + "_" +  to.name
		if(newEntityList.size ==0 )
		return false
		for(Entity ent: newEntityList){
			if(ent.name.equals(mappingNameForward) || ent.name.equals(mappingNameReverse))
			return true
		}
		return false
		
	}
	private def boolean deleteReferenceToEntity(Entity to, Entity from, Entity newEntity){
		var Reference newref = EJSLFactory.eINSTANCE.createReference
		var Reference oldRef = to.references.filter[t | t.entity.name == from.name].get(0)
		newref.attribute.addAll(oldRef.attribute)
		newref.attributerefereced.addAll(newEntity.attributes.filter[t | isAttributeOfEntity(t,to.name)])
		newref.entity = newEntity
		newref.lower = "0"
		newref.upper = "*"
		to.references.remove(oldRef)
		to.references.add(newref)
		return false
		
	}
	private def Reference createNewReverseReference(Entity from, Entity to, Reference oldref){
		var Reference newref = EJSLFactory.eINSTANCE.createReference
		newref.attribute.addAll(oldref.attribute)
		newref.entity= from
		newref.attributerefereced.addAll(from.attributes.filter[t | isAttributeOfEntity(t, to.name)])
		newref.lower = "1"
		newref.upper = "-1"
		return newref
	}
	
	private def boolean haveReferenTo(Entity to, Entity from, String upperValue){
		for(Reference ref : to.references){
			if(ref.entity.name == from.name && !upperValue.equalsIgnoreCase("1") && !ref.upper.equalsIgnoreCase("1"))
			return true
		}
		return false 
	}

	
	private def DetailPageField parseAttributeType(Attribute attribute) {
		var DetailPageField editField = EJSLFactory.eINSTANCE.createDetailPageField
		switch attribute.type{
			DatatypeReference :{
				var SimpleHTMLTypes result = EJSLFactory.eINSTANCE.createSimpleHTMLTypes
				result.htmltype = SimpleHTMLTypeKinds.get("Text_Field")
				editField.attribute = attribute
				editField.htmltype = result
				return editField
			}
			StandardTypes:{
				var StandardTypes temptyp = attribute.type as StandardTypes
				var SimpleHTMLTypes result = EJSLFactory.eINSTANCE.createSimpleHTMLTypes
				result.htmltype = SimpleHTMLTypeKinds.get(parsingType(temptyp))
				editField.attribute = attribute
				editField.htmltype = result
				return editField
			}
		}
	}
	
	private def String parsingType(StandardTypes eJSlType) {
		
		    var String value = "";
		switch (eJSlType.type.getName()){
			case "Integer" :{
				value = "Integer"
			}
			case "Boolean" :{
			value = "Yes_No_Buttons"
				
			}
			case "Textarea" :{
				value = "Textarea"
			}
			case "Textfield" :{
				value = "Text_Field"
			} 
			case "Time":{
				value = "Datepicker"
			}
			case "Date":{
				value = "Datepicker"
			}
			case "Datetime" :{
				value = "Datepicker"
				}
			case "Link" :{
				value = "Text_Field"
			}
			case "Image":{
				value = "Imagepicker"
			}
			case "File" :{
				value = "Filepicker"
			}
			case "Label":{
				value = "Text_Field_NE"
			}
			
		}

		return value
	}
	
}