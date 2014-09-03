/**
 */
package de.thm.icampus.ejsl.generator;

import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.Slug
import de.thm.icampus.ejsl.generator.ProtectedRegion

public class ComponentGenerator extends AbstractExtensionGenerator {
	
	PageGeneratorClient pageClient;
	
	def PageGeneratorClient getPageClient(){
		return this.pageClient
	}

	
	def void setPageClient(PageGeneratorClient value){
		this.pageClient = value;
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
	

	
	
	
	
} // ComponentGenerator
