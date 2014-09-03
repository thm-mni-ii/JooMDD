/**
 */
package de.thm.icampus.ejsl.generator

import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.ProtectedRegion
import de.thm.icampus.ejsl.generator.Slug

public class PackageGenerator extends AbstractExtensionGenerator {
	
	ExtensionGeneratorClient extensionClient

	public def ExtensionGeneratorClient getExtensionClient(){
		return this.extensionClient
	}

	
	public def void setExtensionClient(ExtensionGeneratorClient value){
		this.extensionClient = value;
	}
	
	override getProtectedRegions() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override setProtectedRegions(EList<ProtectedRegion> myprotectedRegions) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getSlug() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override setSlug(Slug slug) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getKvPairClient() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override setKvPairClient(KVPairGeneratorClient e) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override generate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	

} // PackageGenerator
