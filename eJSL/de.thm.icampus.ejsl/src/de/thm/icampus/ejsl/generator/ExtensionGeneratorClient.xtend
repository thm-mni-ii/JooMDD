/**
 */
package de.thm.icampus.ejsl.generator

import org.eclipse.emf.ecore.EObject;


public class  ExtensionGeneratorClient  {
	
	AbstractExtensionGenerator extensions 
	
	public def AbstractExtensionGenerator getExtension(){
		return this.extensions
	}

	
	def void setExtension(AbstractExtensionGenerator value){
		this.extensions = value
	}
	
	public def CharSequence generateExtension(){
		return extensions.generate
	}

} // ExtensionGeneratorClient
