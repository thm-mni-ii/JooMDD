package de.thm.icampus.ejsl.generator.pi.ExtendedEntity

import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.Reference
import de.thm.icampus.ejsl.eJSL.Entity

interface ExtendedAttribute extends Attribute {
	
	
	def String generatorType()
	def Reference getReference()
	def Entity getEntity()
	def Attribute getInstance()
	
}