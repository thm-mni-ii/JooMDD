/**
 */
package de.thm.icampus.ejsl.generator;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import de.thm.icampus.ejsl.generator.Slug
import de.thm.icampus.ejsl.generator.ProtectedRegion



 abstract public class  AbstractExtensionGenerator{
	
	
	
	public def EList<ProtectedRegion> getProtectedRegions();
	
	public def void setProtectedRegions( EList<ProtectedRegion> myprotectedRegions )

	
	def Slug getSlug()
	def void setSlug(Slug slug);

	
	
	def KVPairGeneratorClient getKvPairClient();
	def void setKvPairClient(KVPairGeneratorClient e);

	public def CharSequence generate();

} // AbstractExtensionGenerator
