package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.joomdd.ejsl.eJSL.Module
import java.util.ArrayList
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.StaticLanguageValue

interface ExtendedModule extends Module {
	def ExtendedPageReference getExtendedPageReference()
	def String getExtendedComponentName()
	def String extensionName()
    def ExtendedComponent getComponent()
}