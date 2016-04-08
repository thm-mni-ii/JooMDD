package de.thm.icampus.joomdd.ejsl.ressourceTransformator

import de.thm.icampus.joomdd.ejsl.eJSL.Type
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes

class Util {
	
	def static copyType(Type type) {
		switch type{
			DatatypeReference:{
				var DatatypeReference old_type = type as DatatypeReference
				var DatatypeReference new_type = EJSLFactory.eINSTANCE.createDatatypeReference
				new_type.type = old_type.type
				return new_type
			}
			StandardTypes:{
				var StandardTypes old_type = type as StandardTypes
				var StandardTypes new_type = EJSLFactory.eINSTANCE.createStandardTypes
					new_type.type =  old_type.type
					new_type.notnull = old_type.notnull
					new_type.^default = old_type.^default
					return new_type
					
					
			}
		}
		
	}
	
}