package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.joomdd.ejsl.eJSL.ExtensionPackage
import org.eclipse.emf.common.util.EList

interface ExtendedExtensionPackage extends ExtensionPackage {
	
	def ExtensionPackage getIntance()
	def EList<ExtendedExtensions> getExtendedExtensions()
	
}