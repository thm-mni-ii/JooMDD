package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.joomdd.ejsl.eJSL.Extension

interface ExtendedExtensions  extends Extension{
	
	def ExtendedComponent getcomponentExtended()
	def ExtendedModule getModulExtended()
	def ExtendedLibrary getLibraryExtended()
	def ExtendedExtensionPackage getExtensionPackageExtended()
	def ExtendedPlugin getPluginExtended()
	def Extension getInstance()
	
}
