/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.eJSL.ExtensionPackage
import de.thm.icampus.joomdd.ejsl.eJSL.Library
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.eJSL.Plugin
import de.thm.icampus.joomdd.ejsl.eJSL.Template
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedComponentImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedModuleImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedPluginImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPlugin
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedLibrary
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedLibraryImpl
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug

public class  ExtensionGeneratorClient  {
	
	AbstractExtensionGenerator extensionsgenerator
	IFileSystemAccess fsa
	Extension ext
	String path
	
	new(IFileSystemAccess access, Extension extens) {
		fsa=access
		ext=extens
	}

	
	
	
	public def AbstractExtensionGenerator getExtensiongenerator(){
		return this.extensionsgenerator
	}

	
	def void setExtensiongenerator(AbstractExtensionGenerator value){
		this.extensionsgenerator = value
	}
	
	def void setPath(String path) {
		this.path = path
	}
	
	public def CharSequence generateExtension(){
		switch ext {
			ExtensionPackage : {
					var ExtensionPackage tempext  =  ext as ExtensionPackage
					extensionsgenerator = new PackageGenerator(tempext,fsa,"Extensions/" + Slug.nameExtensionBind("pkg", tempext.name).toLowerCase +"/")
				}
			Component :{
				var ExtendedComponent tempext  = new ExtendedComponentImpl(ext as Component)
					extensionsgenerator = new ComponentGenerator(tempext,fsa,"Extensions/" + Slug.nameExtensionBind("com", tempext.name).toLowerCase +"/")
			}
			Module :{
				var ExtendedModule tempext  =  new ExtendedModuleImpl(ext as Module)
					extensionsgenerator = new ModuleGenerator(tempext,fsa,"Extensions/" + Slug.nameExtensionBind("mod", tempext.name).toLowerCase +"/")
			}
			Plugin :{
				var ExtendedPlugin tempext  =  new ExtendedPluginImpl(ext as Plugin)
					extensionsgenerator = new PluginGenerator(tempext,fsa, "Extensions/" + Slug.nameExtensionBind("plg", tempext.name).toLowerCase +"/")
			}
			Library :{
				var ExtendedLibrary tempext  =  new ExtendedLibraryImpl(ext as Library)
					extensionsgenerator = new LibraryGenerator(tempext,fsa,"Extensions/" + Slug.nameExtensionBind("lib", tempext.name).toLowerCase +"/")
			}
			Template :{
				var Template tempext  =  ext as Template
					extensionsgenerator = new TemplateGenerator(tempext,fsa,"Extensions/" + tempext.name.toLowerCase +"/")
			}
			default : {
				System.out.println("ExtensionGeneratorClient default")
			}	
		}
		if(path != null) {
			extensionsgenerator.setPath(this.path)
		}
		return extensionsgenerator.generate
	}
	
	def setExtension(Extension extensions) {
		this.ext = extensions;
	}

} // ExtensionGeneratorClient
