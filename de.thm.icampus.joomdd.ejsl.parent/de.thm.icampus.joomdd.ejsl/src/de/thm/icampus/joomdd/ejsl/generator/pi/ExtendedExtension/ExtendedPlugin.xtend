package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.joomdd.ejsl.eJSL.Plugin
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import org.eclipse.emf.common.util.EList
import java.util.ArrayList
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.StaticLanguageValue

interface ExtendedPlugin extends Plugin {
	
	def EList<ExtendedEntity> getExtendedEntities()
	def EList<ExtendedParameter> getExtendedParameter()
	def String extensionName()
    def String addLanguage(ArrayList<String> keyArray, String value)
    def String addLanguage(ArrayList<String> keyArray, StaticLanguageValue value)
}