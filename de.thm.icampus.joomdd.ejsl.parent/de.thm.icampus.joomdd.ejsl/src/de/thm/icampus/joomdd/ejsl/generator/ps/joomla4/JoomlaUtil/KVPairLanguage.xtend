package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil

import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair

/**
 * This class generates the key value pairs in language files.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 * 
 */
public class KVPairLanguage extends KVPairInterface {
	public KeyValuePair kv
	
	new(KeyValuePair kv){
		this.kv = kv
	}
	new (String key, String value){
		kv = EJSLFactory.eINSTANCE.createKeyValuePair
		kv.name = key
		kv.value = value
	}
	override generateKVPair() {
		return '''«kv.name.toUpperCase»="«kv.value»"'''
	}
	
	override equals(Object o) {
		if (o instanceof KVPairLanguage) {
			return this.kv.name.equals(o.kv.name)
		}
		return false
	}	
}
