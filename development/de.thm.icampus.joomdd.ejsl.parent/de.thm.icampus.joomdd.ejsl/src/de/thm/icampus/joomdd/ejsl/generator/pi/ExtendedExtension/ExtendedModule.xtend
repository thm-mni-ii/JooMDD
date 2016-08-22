package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.joomdd.ejsl.eJSL.Module

interface ExtendedModule extends Module {
	
	
	def ExtendedPageReference getExtendedPageReference()
	def String getExtendedComponentName()
	
	
}