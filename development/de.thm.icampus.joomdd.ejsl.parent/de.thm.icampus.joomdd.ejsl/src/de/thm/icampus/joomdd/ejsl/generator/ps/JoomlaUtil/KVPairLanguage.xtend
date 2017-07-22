/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil

import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair
import de.thm.icampus.joomdd.ejsl.eJSL.impl.KeyValuePairImpl
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KV Pair Language</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getKVPairLanguage()
 * @model
 * @generated
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
	
} // KVPairLanguage
