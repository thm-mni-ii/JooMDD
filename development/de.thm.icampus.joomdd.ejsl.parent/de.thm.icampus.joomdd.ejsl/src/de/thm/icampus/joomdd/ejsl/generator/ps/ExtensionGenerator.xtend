package de.thm.icampus.joomdd.ejsl.generator.ps

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.ExtensionGeneratorClient
import org.eclipse.xtext.generator.IFileSystemAccess2

class ExtensionGenerator extends AbstracteGenerator{
	
	EList<Extension> extensions
	String path
	IFileSystemAccess2 fileGen
	String modelName
	String rootPath
	
	new(EList<Extension> list, String pathToGenerate,  IFileSystemAccess2 fsa, String modelName, String rootPath) {
		extensions= list
		fileGen = fsa
		path = pathToGenerate + modelName +"/"
		this.modelName= modelName
		this.rootPath = rootPath
	}
	
	override dogenerate() {

	for(ext: extensions){
			
		var ExtensionGeneratorClient extClient = new ExtensionGeneratorClient(fileGen,ext,path, rootPath)
		extClient.generateExtension
		}
	}
	
	
	
}