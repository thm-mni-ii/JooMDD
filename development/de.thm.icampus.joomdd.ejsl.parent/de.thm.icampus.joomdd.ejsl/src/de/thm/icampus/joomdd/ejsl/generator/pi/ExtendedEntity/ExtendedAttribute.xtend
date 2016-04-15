package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter

interface ExtendedAttribute extends Attribute {
	
	
	def String generatorType()
	def Entity getEntity()
	def Attribute getInstance()
	def boolean isreferenced()
	def void setIsreferenced(boolean value)
	def String htmlType()
	
	
}