package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaUtil

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
	
}
