package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.ExtensionImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedExtensions
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedLibrary
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedExtensionPackage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedCMSExtension
import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.eJSL.Plugin
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.Library
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil
import de.thm.icampus.joomdd.ejsl.eJSL.impl.ExtensionPackageImpl
import de.thm.icampus.joomdd.ejsl.eJSL.ExtensionPackage

class ExtendedExtensionsImpl extends ExtensionImpl implements ExtendedExtensions {
	
	EList<ExtendedModule> modulesExtended
	EList<ExtendedComponent> componentExtended
	EList<ExtendedLibrary> libraryExtended
	EList<ExtendedExtensionPackage> extensionPackageExtended
	Extension instance
	ExtendedCMSExtension container
	
	new(Extension ext, ExtendedCMSExtension cmsExt){
		instance = ext
		container = cmsExt
		this.languages = ext.languages
		this.manifest = ext.manifest
		this.name = PlattformUtil.slugify(ext.name)
		
	}
	
	new(Extension ext) {
		instance = ext
		container = null
		this.languages = ext.languages
		this.manifest = ext.manifest
		this.name = PlattformUtil.slugify(ext.name)
	}
	

	
	override getModulExtended() {
		return new ExtendedModuleImpl(instance as Module)
	}
	
	override getExtensionPackageExtended() {
		return new ExtendedExtensionPackageImpl(instance as ExtensionPackage)
	}
	
	override getPluginExtended() {
		return new ExtendedPluginImpl(instance as Plugin)
	}
	
	override getInstance() {
		return instance
	}
	
	override getcomponentExtended() {
		return new ExtendedComponentImpl(instance as Component)
	}
	
	override getLibraryExtended() {
		return new ExtendedLibraryImpl(instance as Library)
	}
	
	override extensionName() {
		switch(instance) {
			Component:{
				return "com_" + this.name.toLowerCase
			}
			Module:{
				return "mod_" + this.name.toLowerCase
			}
			ExtensionPackage:{
				return "pkg_" + this.name.toLowerCase
			}
			Library:{
				return "lib_" + this.name.toLowerCase
			}
			Plugin:{
				return "plg_" + this.name.toLowerCase
			}
		}
	}
	
}