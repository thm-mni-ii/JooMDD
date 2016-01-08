package de.thm.icampus.ejsl.generator.transformer

import de.thm.icampus.ejsl.eJSL.Type
import de.thm.icampus.ejsl.eJSL.DatatypeReference
import de.thm.icampus.ejsl.eJSL.StandardTypes

class TransformerUtil {
	
	
	
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