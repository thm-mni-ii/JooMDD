package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaUtil

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
