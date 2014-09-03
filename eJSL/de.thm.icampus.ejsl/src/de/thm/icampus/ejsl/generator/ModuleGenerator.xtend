/**
 */
package de.thm.icampus.ejsl.generator


import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.ProtectedRegion
import de.thm.icampus.ejsl.generator.Slug

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Generator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eJSLGenerator.ModuleGenerator#getPageClient <em>Page Client</em>}</li>
 * </ul>
 * </p>
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getModuleGenerator()
 * @model
 * @generated
 */
public class ModuleGenerator extends AbstractExtensionGenerator {
	
	PageGeneratorClient pageClient
	
	public def PageGeneratorClient getPageClient(){
		return this.pageClient
	}

	
	public def void setPageClient(PageGeneratorClient value){
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
	



} // ModuleGenerator
