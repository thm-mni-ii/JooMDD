package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.ExtensionPackageImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedExtensionPackage
import de.thm.icampus.joomdd.ejsl.eJSL.ExtensionPackage
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedExtensions

class ExtendedExtensionPackageImpl extends ExtensionPackageImpl implements ExtendedExtensionPackage {
	
	ExtensionPackage instance
	EList<ExtendedExtensions> extendedExtensionList
	
	new(ExtensionPackage ext){
		this.name = ext.name
		this.manifest = ext.manifest
		this.languages = ext.languages
		this.instance = ext
		this.extensions = ext.extensions
		extendedExtensionList.addAll(ext.extensions.map[t | new ExtendedExtensionsImpl(t)])
		
		
	}
	
	override getIntance() {
		return instance
	}
	
	override getExtendedExtensions() {
		return this.extendedExtensionList
	}
	
}