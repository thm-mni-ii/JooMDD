package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity

import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute

/**
 * This interface defines methods, which are needed to access further information on an Entity.
 * 
 * @see Entity
 */
interface ExtendedEntity extends Entity {
	
	/**
	 * Returns all defined ExtendedAttributes on this ExtendedEntity.
	 * 
	 * @return list of own ExtendedAttributes
	 */
	def EList<ExtendedAttribute> getOwnExtendedAttributes()
	
	/**
	 * Returns all defined ExtendedAttributes on the parent ExtendedEntity.
	 * 
	 * @return list of own ExtendedAttributes
	 */
	def EList<ExtendedAttribute> getParentExtendedAttributes()
	
	/**
	 * Returns the instance of Entity, which is extended by this class.
	 * 
	 * @return instance of Entity
	 */
	def Entity getInstance()
	
	/**
	 * Returns all ExtendedAttribute instances belonging to this.
	 * 
	 * @return list of belonging ExtendedAttribute instances
	 */
	def EList<ExtendedAttribute> getAllExtendedAttributes()
	
	/**
	 * Returns all ExtendedReference instances belonging to this.
	 * 
	 * @return list of belonging ExtendedReference instances
	 */
	def EList<ExtendedReference> getAllExtendedReferences()
	
	/**
	 * Returns whether this has an attribute to identify itself
	 * 
	 * @return whether this has an ID attribute
	 */
	def boolean hasIdAttribute()
	
	/**
	 * Adds a new Attribute. After that it belongs to this.
	 * 
	 * @param e Attribute to add
	 */
	def void addNewAttribute(Attribute e)
	
	/**
	 * Searches all attributes and returns the identifier attribute. Null if not available.
	 * 
	 * @return ID attribute if available otherwise null
	 */
	def ExtendedAttribute searchIdAttribute()
	
	/**
	 * Returns all ExtendedReference instances, which are connected to only this instance.
	 * 
	 * @return list of ExtendReference instances
	 */
	def EList<ExtendedReference>getAllExtendedReferencesToEntity()
	
	/**
	 * Searches and returns an ExtendedAttribute specified by its name. 
	 * 
	 * @param name String to specify name of ExtendedAttribute
	 * @return ExtendedAttribute specified by name, null if not available
	 */
	def ExtendedAttribute getExtendedAttributeByName(String name)
	
	/**
	 * TODO
	 */
	def EList<ExtendedAttribute> getRefactoryAttribute()
	
	/**
	 * TODO
	 */
	def EList<ExtendedReference>getRefactoryReference()
	
	/**
	 * TODO
	 */
	def boolean isGenerated()
	
	/**
	 * Returns the primary key attribute of this
	 * 
	 * @return the primary ExtendedAttribute
	 */
	def ExtendedAttribute getPrimaryKey()
	
}