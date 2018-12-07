package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity

import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.Entity

/**
 * This interface defines methods, which are needed to access further information on a Reference between Entities
 * 
 * @see Reference
 */
interface ExtendedReference extends Reference {
	
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
	
}