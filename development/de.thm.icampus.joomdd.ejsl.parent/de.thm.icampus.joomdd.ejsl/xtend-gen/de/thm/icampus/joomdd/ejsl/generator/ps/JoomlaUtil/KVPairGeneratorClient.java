package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil;

import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.KVPairInterface;

@SuppressWarnings("all")
public class KVPairGeneratorClient {
  private KVPairInterface kvPair;
  
  public KVPairInterface getKvPair() {
    return this.kvPair;
  }
  
  public void setKvPair(final KVPairInterface value) {
    this.kvPair = value;
  }
  
  public CharSequence generate() {
    return this.kvPair.generateKVPair();
  }
}
