package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4

import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.eJSL.ExtensionPackage
import de.thm.icampus.joomdd.ejsl.eJSL.Library
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.eJSL.Plugin
import de.thm.icampus.joomdd.ejsl.eJSL.Template
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedComponentImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedModuleImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedPluginImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPlugin
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedLibrary
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedLibraryImpl
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.Slug
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedExtensionPackage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedExtensionPackageImpl
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.PackageGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.ComponentGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.ModuleGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.PluginGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.LibraryGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.TemplateGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.AbstractExtensionGenerator

/**
 * This class represents the interface between the JooMDD generator and the Joomla-specific generator templates. 
 * Depending on the required extension type it calls the specific extension type classes which contain the templates 
 * and generate the specific code.
 * 
 * @author Dennis Priefer, Dieudonne Timma Meyatchie
 */
public class ExtensionGeneratorHandler {
    AbstractExtensionGenerator extensionsgenerator
    IFileSystemAccess2 fsa
    Extension ext
    String path
    String rootpath

    new(IFileSystemAccess2 access, Extension extens, String path, String rootPath) {
        fsa = access
        ext = extens
        this.path = path
        this.rootpath = rootPath
    }

    public def AbstractExtensionGenerator getExtensiongenerator() {
        return this.extensionsgenerator
    }

    def void setExtensiongenerator(AbstractExtensionGenerator value) {
        this.extensionsgenerator = value
    }

    def void setPath(String path) {
        this.path = path
    }

    public def CharSequence generateExtension() {
        switch ext {
            ExtensionPackage: {
                var ExtendedExtensionPackage tempext 
                tempext = new ExtendedExtensionPackageImpl(ext as ExtensionPackage)
                extensionsgenerator = new PackageGenerator(tempext, fsa,
                    path + Slug.nameExtensionBind("pkg", tempext.name).toLowerCase + "/j4/", rootpath)
            }
            Component: {
                var ExtendedComponent tempext = new ExtendedComponentImpl(ext as Component)
                extensionsgenerator = new ComponentGenerator(tempext, fsa,
                    path + Slug.nameExtensionBind("com", tempext.name).toLowerCase + "/j4/new/" +
                        Slug.nameExtensionBind("com", tempext.name).toLowerCase + "/",
                    path + Slug.nameExtensionBind("com", tempext.name).toLowerCase + "/j4/update/")
            }
            Module: {
                var ExtendedModule tempext = new ExtendedModuleImpl(ext as Module)
                extensionsgenerator = new ModuleGenerator(tempext, fsa,
                    path + Slug.nameExtensionBind("mod", tempext.name).toLowerCase + "/j4/")
            }
            Plugin: {
                var ExtendedPlugin tempext = new ExtendedPluginImpl(ext as Plugin)
                extensionsgenerator = new PluginGenerator(tempext, fsa,
                    path + Slug.nameExtensionBind("plg", tempext.name).toLowerCase + "/j4/")
            }
            Library: {
                var ExtendedLibrary tempext = new ExtendedLibraryImpl(ext as Library)
                extensionsgenerator = new LibraryGenerator(tempext, fsa,
                    path + Slug.nameExtensionBind("lib", tempext.name).toLowerCase + "/j4/")
            }
            Template: {
                var Template tempext = ext as Template
                extensionsgenerator = new TemplateGenerator(tempext, 
                	fsa, path + tempext.name.toLowerCase + "/j4/"
                )
            }
            default: {
                System.out.println("ExtensionGeneratorHandler default")
            }
        }

        return extensionsgenerator.generate
    }

    def setExtension(Extension extensions) {
        this.ext = extensions;
    }

}
