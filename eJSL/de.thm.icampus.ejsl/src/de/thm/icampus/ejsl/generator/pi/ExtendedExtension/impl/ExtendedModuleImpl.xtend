package de.thm.icampus.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.ejsl.eJSL.impl.ModuleImpl
import de.thm.icampus.ejsl.eJSL.Module
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.ejsl.generator.pi.util.PlattformIUtil

class ExtendedModuleImpl extends ModuleImpl implements ExtendedModule {
	
	ExtendedPageReference extendedReference
	
	new(Module mod){
		this.name = PlattformIUtil.slugify(mod.name)
		this.manifest = mod.manifest
		this.languages = mod.languages
		this.pageRef = mod.pageRef
		extendedReference = new ExtendedPageReferenceImpl(pageRef)
	}
	
	override getExtendedPageReference() {
		return extendedReference
	}
	
}