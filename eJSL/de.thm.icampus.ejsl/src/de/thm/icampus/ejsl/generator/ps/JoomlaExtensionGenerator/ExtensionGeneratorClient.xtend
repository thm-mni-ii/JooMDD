/**
 */
package de.thm.icampus.ejsl.generator.ps.JoomlaExtensionGenerator

import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.Extension
import de.thm.icampus.ejsl.eJSL.ExtensionPackage
import de.thm.icampus.ejsl.eJSL.Library
import de.thm.icampus.ejsl.eJSL.Module
import de.thm.icampus.ejsl.eJSL.Plugin
import de.thm.icampus.ejsl.eJSL.Template
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedComponent

public class  ExtensionGeneratorClient  {
	
	AbstractExtensionGenerator extensionsgenerator
	IFileSystemAccess fsa
	Extension ext
	String path
	
	new(IFileSystemAccess access) {
		fsa=access
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
					extensionsgenerator = new PackageGenerator(tempext,fsa)
				}
			Component :{
				var ExtendedComponent tempext  = new ExtendedComponentImpl(ext as Component)
					extensionsgenerator = new ComponentGenerator(tempext,fsa)
			}
			Module :{
				var Module tempext  =  ext as Module
					extensionsgenerator = new ModuleGenerator(tempext,fsa)
			}
			Plugin :{
				var Plugin tempext  =  ext as Plugin
					extensionsgenerator = new PluginGenerator(tempext,fsa)
			}
			Library :{
				var Library tempext  =  ext as Library
					extensionsgenerator = new LibraryGenerator(tempext,fsa)
			}
			Template :{
				var Template tempext  =  ext as Template
					extensionsgenerator = new TemplateGenerator(tempext,fsa)
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
