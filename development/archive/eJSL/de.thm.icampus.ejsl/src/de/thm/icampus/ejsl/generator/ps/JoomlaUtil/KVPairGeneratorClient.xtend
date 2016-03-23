/**
 */
package de.thm.icampus.ejsl.generator.ps.JoomlaUtil


import org.eclipse.emf.ecore.EObject;


public class KVPairGeneratorClient  {
	
	KVPairInterface kvPair
	
	public def KVPairInterface getKvPair(){
		return this.kvPair 
	}

	
	public def void setKvPair(KVPairInterface value){
		this.kvPair = value;
	}
	
	public def CharSequence generate(){
		return this.kvPair.generateKVPair
	}
	

} // KVPairGeneratorClient
