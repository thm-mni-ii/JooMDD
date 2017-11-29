package de.thm.icampus.joomdd.ejsl.generator.ps

import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.ExtensionGeneratorHandler

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

    new(EList<Extension> list, 
    	String pathToGenerate, 
    	IFileSystemAccess2 fsa, 
    	String modelName, 
    	String rootPath
    ) {
        extensions = list
        fileGen = fsa
        path = pathToGenerate + modelName + "/"
        this.modelName = modelName
        this.rootPath = rootPath
    }

    override dogenerate() {
        for (ext : extensions) {
            // Call of Joomla extension generator
            var ExtensionGeneratorHandler joomlaExtHandler
            joomlaExtHandler = new ExtensionGeneratorHandler(fileGen, ext, path, rootPath)
            joomlaExtHandler.generateExtension
            // -----------------------------------
        }
    }
}
