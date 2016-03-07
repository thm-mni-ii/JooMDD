package de.thm.icampus.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.ejsl.eJSL.impl.ModuleImpl
import de.thm.icampus.ejsl.eJSL.Module
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.ejsl.generator.pi.util.PlattformIUtil
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedComponent

class ExtendedModuleImpl extends ModuleImpl implements ExtendedModule {
	
	ExtendedPageReference extendedReference
	ExtendedComponent com
	new(Module mod){
		this.name = PlattformIUtil.slugify(mod.name)
		this.manifest = mod.manifest
		this.languages = mod.languages
		this.pageRef = mod.pageRef
		extendedReference = new ExtendedPageReferenceImpl(pageRef)
		com = new ExtendedComponentImpl(mod.pageRef.pagescr)
	}
	
	override getExtendedPageReference() {
		return extendedReference
	}
	
	override getExtendedComponent() {
		return com
	}
	
}