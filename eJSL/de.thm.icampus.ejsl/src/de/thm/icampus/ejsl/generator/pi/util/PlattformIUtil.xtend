package de.thm.icampus.ejsl.generator.pi.util

import de.thm.icampus.ejsl.eJSL.Type
import de.thm.icampus.ejsl.eJSL.DatatypeReference
import de.thm.icampus.ejsl.eJSL.StandardTypes
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.Reference
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.impl.ExtendedAttributeImpl

class PlattformIUtil {
	
	
	
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
	static def ExtendedAttribute transformAttribute(Attribute ejslAttribute){
		var Entity entity = ejslAttribute.eContainer as Entity
		var Reference ref = null
		for(Reference e: entity.references){
			if(e.attribute.name == ejslAttribute.name )
				ref = e
		}
		
		return new ExtendedAttributeImpl (ejslAttribute, ref)
	}
}