package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.ModuleImpl
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.eJSL.ComponentReference
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.CoreComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil

class ExtendedModuleImpl extends ModuleImpl implements ExtendedModule {
	
	ExtendedPageReference extendedReference
	String comName
	new(Module mod){
		this.name = PlattformUtil.slugify(mod.name)
		this.manifest = mod.manifest
		this.languages = mod.languages
		this.pageRef = mod.pageRef
		extendedReference = new ExtendedPageReferenceImpl(pageRef)
		comName = init(mod.pageRef.pagescr)
	}
	
	def init(ComponentReference reference) {
	  if(reference.core != null)
	  return reference.core.getName()
	  if(reference.ref != null)
	    return reference.ref.name
	}
	
	override getExtendedPageReference() {
		return extendedReference
	}

	override getExtendedComponentName() {
		return comName
	}
	
}