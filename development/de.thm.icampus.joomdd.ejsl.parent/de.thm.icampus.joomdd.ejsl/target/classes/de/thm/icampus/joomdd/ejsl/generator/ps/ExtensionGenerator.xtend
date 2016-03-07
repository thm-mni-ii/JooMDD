package de.thm.icampus.joomdd.ejsl.generator.ps

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.ExtensionGeneratorClient

class ExtensionGenerator extends AbstracteGenerator{
	
	EList<de.thm.icampus.joomdd.ejsl.eJSL.Extension> extensions
	new(EList<Extension> list) {
		extensions= list
	}
	
	override dogenerate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override dogenerate(String path, IFileSystemAccess fileGen) {
		
		for(ext: extensions){
			
		var ExtensionGeneratorClient extClient = new ExtensionGeneratorClient(fileGen,ext)
		extClient.generateExtension
		}
	}
	
}