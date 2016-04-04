package de.thm.icampus.joomdd.ejsl.generator.ps

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.ExtensionGeneratorClient

class ExtensionGenerator extends AbstracteGenerator{
	
	EList<Extension> extensions
	String path
	IFileSystemAccess fileGen
	String modelName
	
	new(EList<Extension> list, String pathToGenerate,  IFileSystemAccess fsa, String modelName) {
		extensions= list
		fileGen = fsa
		path = pathToGenerate + modelName +"/"
		this.modelName= modelName
	}
	
	override dogenerate() {

	for(ext: extensions){
			
		var ExtensionGeneratorClient extClient = new ExtensionGeneratorClient(fileGen,ext,path)
		extClient.generateExtension
		}
	}
	
	
	
}