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
import java.util.ArrayList
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.StaticLanguageValue
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug

class ExtendedModuleImpl extends ModuleImpl implements ExtendedModule {
	
	ExtendedPageReference extendedReference
	String comName
	String extensionName 
	new(Module mod){
		this.name = PlattformUtil.slugify(mod.name)
		this.manifest = mod.manifest
		this.languages = mod.languages
		this.pageRef = mod.pageRef
		extendedReference = new ExtendedPageReferenceImpl(pageRef)
		comName = init(mod.pageRef.pagescr)
		extensionName = "mod_" + this.name.toLowerCase
	}
	
	def init(ComponentReference reference) {
		if(reference == null)
		  return null
		 if(reference.ref != null)
	    return PlattformUtil.slugify(reference.ref.name)
	  if(reference.core != null)
	  return PlattformUtil.slugify(reference.core.getName())
	 
	}
	
	override getExtendedPageReference() {
		return extendedReference
	}

	override getExtendedComponentName() {
		return comName
	}
	
	override extensionName() {
		return extensionName
	}
}