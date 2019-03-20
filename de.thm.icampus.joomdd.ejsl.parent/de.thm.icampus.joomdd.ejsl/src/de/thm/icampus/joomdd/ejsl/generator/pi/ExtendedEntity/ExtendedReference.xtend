package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity

import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute

/**
 * This interface defines methods, which are needed to access further information on a Reference between Entities
 * 
 * @see Reference
 */
interface ExtendedReference extends Reference {
	
    def void setParentProperties(Reference reference)
    
	/**
	 * Returns all ExtendedAttributes specified in this ExtendedReference
	 * 
	 * @return list of ExtendedAttributes
	 */
	def EList<ExtendedAttribute>getExtendedAttributes()
	
	/**
	 * Returns all ExtendedAttributes specified in the connected Entity
	 * 
	 * @return list of ExtendedAttributes
	 */	
	def EList<ExtendedAttribute>getReferencedExtendedAttributes()
	
	/**
	 * Returns destination Entity of this reference
	 * 
	 * @return destination Entity
	 */	
	def Entity getDestinationEntity()
	
	/**
	 * Returns source Entity of this reference
	 * 
	 * @return source Entity
	 */	
	def Entity getSourceEntity()
	
	/**
     * The function will return the id attribute name of this reference
     * We assume that the reference contains two attributes.
     * The first one is the modeled relation.
     * The second is the actual reference that will be used between ids.
     * The latter will be used to display an text value instead of an id.
     * 
     * @return String
     */
	def String getReferenceIDAttribute()
	
	/**
     * The function will return the referenced id attribute name of this reference
     * We assume that the reference contains two attributes.
     * The first one is the modeled relation.
     * The second is the actual reference that will be used between ids.
     * The latter will be used to display an text value instead of an id.
     * 
     * @return String
     */
    def String getReferencedIDAttribute()
    
    /**
     * The function will return the attribute name of this reference
     * We assume that the reference contains two attributes.
     * The first one is the modeled relation.
     * The second is the actual reference that will be used between ids.
     * The latter will be used to display an text value instead of an id.
     * 
     * @return String
     */
    def String getReferenceAttribute()
    
    /**
     * The function will return the referenced attribute name of this reference
     * We assume that the reference contains two attributes.
     * The first one is the modeled relation.
     * The second is the actual reference that will be used between ids.
     * The latter will be used to display an text value instead of an id.
     * 
     * @return String
     */
    def String getReferencedAttribute()
}