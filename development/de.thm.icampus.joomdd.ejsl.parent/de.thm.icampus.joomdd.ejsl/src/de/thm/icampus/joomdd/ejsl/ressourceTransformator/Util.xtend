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
	def static String slugify(String str) {
		var res = str
		res = res.replaceAll("[^\\pL\\d]+", "_")
		res = res.replaceAll("ä", "ae")
		res = res.replaceAll("ö", "ou")
		res = res.replaceAll("ü", "ue")
		res = res.replaceAll("ß", "ss")
		res = res.replaceAll("[^-\\w]+", '')
		res = res.toLowerCase()
		trim(res, "_".charAt(0))
	}
	def static String trim(String str, char c) {
		var a = 0
		var z = str.length
		
		while (a < z && str.charAt(a) == c) {
			a = a + 1
		}
		
		do {
			z = z - 1
		} while (z > a && str.charAt(z) == c); 
		
		str.substring(a, z+1)
	}
	
	
}