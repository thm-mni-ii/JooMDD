package de.thm.icampus.joomdd.ejsl.generator.ps

import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2

/**
 * This class is responsible for calling the platform-specific code generators. 
 * 
 * @author Dennis Priefer, Dieudonne Timma Meyatchie
 */
class ExtensionGenerator extends AbstractGenerator {

    EList<Extension> extensions
    String path
    IFileSystemAccess2 fileGen
    String modelName
    String rootPath
    String platform

    new(EList<Extension> list, 
    	String pathToGenerate, 
    	IFileSystemAccess2 fsa, 
    	String modelName, 
    	String rootPath,
    	String platform
    ) {
        extensions = list
        fileGen = fsa
        path = pathToGenerate + "/"
        this.modelName = modelName
        this.rootPath = rootPath
        this.platform = platform;
    }

    override dogenerate() {    	
        for (ext : extensions) {
        	switch(this.platform)
        	{
        		case "j3": {
        			var de.thm.icampus.joomdd.ejsl.generator.ps.joomla.ExtensionGeneratorHandler joomlaExtHandler
	            	joomlaExtHandler = new de.thm.icampus.joomdd.ejsl.generator.ps.joomla.ExtensionGeneratorHandler(fileGen, ext, path, rootPath, this.modelName, true)
	            	joomlaExtHandler.generateExtension
        		}
        		case "j4": {
        			var de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.ExtensionGeneratorHandler joomlaExtHandler
	            	joomlaExtHandler = new de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.ExtensionGeneratorHandler(fileGen, ext, path, rootPath, this.modelName, true)
	            	joomlaExtHandler.generateExtension
        		}
        	}
        }
    }
}
