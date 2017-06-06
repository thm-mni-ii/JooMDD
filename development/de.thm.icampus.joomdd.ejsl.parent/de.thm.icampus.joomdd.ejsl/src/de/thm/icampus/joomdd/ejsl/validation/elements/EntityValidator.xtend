package de.thm.icampus.joomdd.ejsl.validation.elements

import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.validation.util.Keywords
import java.util.HashSet
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

/**
 * This class contains validation rules about entities.
 *
 */
class EntityValidator extends AbstractDeclarativeValidator {
	
	public static val ENTITY_ATTRIBUTE_AMBIGUOUS = 'ambiguousAttrName'
	public static val ENTITY_AMBIGOUS = 'ambiguousEntity'
	public static val ENTITY_MISSING_ATTRIBUTES = 'missingAttributes'
	public static val ENTITY_NAME_FORBIDDEN = 'forbiddenEntityname'
	public static val ENTITY_FORBIDDEN_UNDERSCORE = 'forbiddenUnderscoreEntityname'
	public static val ENTITY_MISSING_REFERENCE = 'missingReference'
	public static val ENTITY_REFERENCE_ATTRIBUTE_NOT_PRIMARY = 'notPrimaryReference'
	public static val ENTITY_MISSING_PRIMARY_ATTRIBUTE = 'missingPrimaryAttribute'
	
    public override register(EValidatorRegistrar registrar) {}
	
	/**
	 * Checks if the existing entities of the model have different/unique names
	 */
	@Check
	def checkEntitiesAreUnique(EJSLModel model) {
		var entities = new HashSet<String>

		for (entity : model.getEjslPart.getFeature.getEntities) {
			if (!entities.add(entity.name)) {
				error(
					'Entity names must be unique.',
					entity,
					EJSLPackage.Literals.ENTITY__NAME,
					de.thm.icampus.joomdd.ejsl.validation.elements.EntityValidator.ENTITY_AMBIGOUS
				)
			}
		}
	}
	
	/**
	 * Checks if the existing attributes from the available entities have different/unique names
	 */
	@Check
	def checkEntityAttributesAreUnique(Entity entity) {
		var attributes = new HashSet<String>

		for (attribute : entity.attributes) {
			if (!attributes.add(attribute.getName)) {
				error(
					'Attribute names must be unique.',
					attribute,
					EJSLPackage.Literals.ATTRIBUTE__NAME,
					de.thm.icampus.joomdd.ejsl.validation.elements.EntityValidator.ENTITY_ATTRIBUTE_AMBIGUOUS
				)
			}
		}
	}
	
	/**
	 * Checks if entity has attributes
	 */
	@Check
	def checkEntityHasAttributes(Entity entity) {
		if (entity.attributes.size == 0 || entity.attributes == null) {
			error(
					'Entity must have attributes with at least one attribute in it.',
					entity,
					EJSLPackage.Literals.ENTITY__NAME,
					de.thm.icampus.joomdd.ejsl.validation.elements.EntityValidator.ENTITY_MISSING_ATTRIBUTES
				)
		}
	}
	
	/**
	 * Checks if the name of an attribute fulfills certain conventions.
	 * The name can't be id, ordering, state, checked_out_time, created_by and checked_out
	 */	
	@Check
	def checkAttributename(Entity entity) {
		for (attribute : entity.attributes) {			
//			if (attribute.name == "id") {				
//				error(
//					'\"id\" is not a valid attribute name!',
//					attribute,
//					EJSLPackage.Literals.ATTRIBUTE__NAME,
//					AMBIGUOUS_ATTRIBUTE_NAME
//				)
//			}			
			if(attribute.name == "ordering"||attribute.name =="state"||attribute.name =="checked_out" ||
				attribute.name == "checked_out_time" ||attribute.name == "created_by"){ 				
				warning("Attribute name should not be: " + attribute.name +"!",
					attribute,
					EJSLPackage.Literals.ATTRIBUTE__NAME,
					de.thm.icampus.joomdd.ejsl.validation.elements.EntityValidator.ENTITY_ATTRIBUTE_AMBIGUOUS
				)				
			}
		}
	}
	
//	/**
//	 * Checks if the name of a entity contains a underscore
//	 */
//	@Check
//	def checkNoUnderscoreInEntityName(EJSLModel model) {
//		for (entity : model.getEjslPart.getFeature.getEntities) {
//			if (entity.name.contains('_')) {
//				error(
//					'Entity name ' + entity.name + ' contains a underscore',
//								entity,
//								EJSLPackage.Literals.ENTITY__NAME,
//								de.thm.icampus.joomdd.ejsl.validation.elements.EntityValidator.ENTITY_FORBIDDEN_UNDERSCORE
//				)
//			}
//		}
//	}
	
	/**
	 * Check entity name does not match a Xtext keyword
	 * TODO make this work, xtext validation runs before this validation...
	 */
	@Check(FAST)
	def checkEntityNameIsNoXtextKeyword(EJSLModel model) {
		for (entity : model.ejslPart.feature.entities) {
			if (Keywords.RESERVED_KEYWORDS.contains(entity.name)){
				error(
					'Entity name ' + entity.name + ' is a reserved keyword.',
								entity,
								EJSLPackage.Literals.ENTITY__NAME,
								de.thm.icampus.joomdd.ejsl.validation.elements.EntityValidator.ENTITY_NAME_FORBIDDEN
				)
			}
		}
	}
	
	/**
	 * Check Entity with other Entity as Attribute has a Reference to it
	 */
	@Check
	def checkAttributeReferenceToEntity(EJSLModel model){
		var entities = new HashSet<String>
		var refs = new HashSet<String>
		
		// Save all Entity names
		for (entity : model.getEjslPart.getFeature.getEntities) {
			entities.add(entity.name)
		}
		
		// Run through all Entities
		for (entity : model.getEjslPart.getFeature.getEntities) {
			
			// Run through all attributes of that Entity
			for (attribute : entity.attributes) {
				
				// Check for every attribute if there is a equal named Entity
				for (eName : entities) {
					
					if (attribute.name.toLowerCase == eName.toLowerCase) {			
						
						// Check if Reference exists
						for (references : entity.references){
							refs.add(references.entity.name.toLowerCase)
						}
						
						// When no Reference to that Entity exists, show a warning message
						if (refs.add(eName.toLowerCase)) {
							warning(
								'Attribute ' + attribute.name + ' should have a Reference to matching Entity. Make sure referenced Entity is defined above',
								attribute,
								EJSLPackage.Literals.ATTRIBUTE__NAME,
								de.thm.icampus.joomdd.ejsl.validation.elements.EntityValidator.ENTITY_MISSING_REFERENCE
							)
						}
					}
					
					// Reset references
					refs.clear
				}
			}
		}
	}
	
	/**
	 * Validates if the reference to an attribute leads on a primary attribtue
	 */	
	@Check
	def refToAttributeMustBePrimary(Reference reference){
		for (attribute : reference.attributerefereced) {
			if(!attribute.isunique && !attribute.isprimary){
				error(
					'The referenced attribute has to be a unique attribute.',
					reference,
					EJSLPackage.Literals.REFERENCE__ATTRIBUTEREFERECED,
					de.thm.icampus.joomdd.ejsl.validation.elements.EntityValidator.ENTITY_REFERENCE_ATTRIBUTE_NOT_PRIMARY
				)
			}
		}
	}
	
	/**
	 * Checks if at least one Primary attribute exists in the attributes of an entity
	 */	
	@Check
	def checkPrimaryAttributeExist(Entity entity) {
		var hasPrimary = false;
		
		if (entity.attributes.size != 0) {
			for (attribute : entity.attributes) {
			if(attribute.isunique){
				hasPrimary = true;
			}
		}
		if(!hasPrimary){	// if no primary attribute is found
			error(
					'Attributes must have a primary attribute.',
					entity.attributes.get(0),
					EJSLPackage.Literals.ATTRIBUTE__NAME,
					de.thm.icampus.joomdd.ejsl.validation.elements.EntityValidator.ENTITY_MISSING_PRIMARY_ATTRIBUTE
				)
		}
		}	
	}
}
