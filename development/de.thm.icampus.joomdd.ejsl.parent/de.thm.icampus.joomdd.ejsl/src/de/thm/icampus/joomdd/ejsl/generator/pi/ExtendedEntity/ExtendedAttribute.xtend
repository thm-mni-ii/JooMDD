package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.Entity

/**
 * This interface defines methods, which are needed to access further information on an Attribute.
 * 
 * @see Attribute
 */
interface ExtendedAttribute extends Attribute {
	
	/**
	 * Returns all typeinformation of this attribute.
	 * 
	 * @return all typeinformation
	 */
	def String generatorType()
	
	/**
	 * Returns the associated entity. This attribute belongs to the returned entity.
	 * 
	 * @return the associated entity
	 */
	def Entity getEntity()
	
	/**
	 * Returns the instance of Attribute, which is extended by this class.
	 * 
	 * @return instance of Attribute
	 */
	def Attribute getInstance()
	
	/**
	 * Returns if this ExtendedAttribute is referenced.
	 * 
	 * @return true if referenced else false
	 */
	def boolean isReferenced()
	
	/**
	 * Set if this ExtendedAttribute is referenced.
	 * 
	 * @param value boolean if this attribute is referenced
	 */
	def void setIsReferenced(boolean value)
	
	/**
	 * Returns the html string representation of this type. It differentiates between an referenced attribute and an standardtype.
	 * 
	 * @return the html-type in string representation
	 */
	def String htmlType()
	
}