package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.ExtensionPackageImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedExtensionPackage
import de.thm.icampus.joomdd.ejsl.eJSL.ExtensionPackage
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedExtensions
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil
import org.eclipse.emf.common.util.BasicEList

class ExtendedExtensionPackageImpl extends ExtensionPackageImpl implements ExtendedExtensionPackage {
	
	ExtensionPackage instance
	EList<ExtendedExtensions> extendedExtensionList
	String extensionName 
	new(ExtensionPackage ext){
		this.name = PlattformUtil.slugify(ext.name)
		this.manifest = ext.manifest
		this.languages = ext.languages
		this.instance = ext
		this.extensions = ext.extensions
		extendedExtensionList = new BasicEList<ExtendedExtensions>
		extendedExtensionList.addAll(ext.extensions.map[t | new ExtendedExtensionsImpl(t)])
		extensionName = "pkg_" + this.name.toLowerCase 
		
		
	}
	
	override getIntance() {
		return instance
	}
	
	override getExtendedExtensions() {
		return this.extendedExtensionList
	}
	
	override extensionName() {
		return extensionName
	}
	
}