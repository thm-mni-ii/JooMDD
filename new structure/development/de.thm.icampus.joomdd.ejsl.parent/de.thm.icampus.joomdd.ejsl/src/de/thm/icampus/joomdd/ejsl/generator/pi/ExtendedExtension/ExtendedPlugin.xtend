package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.joomdd.ejsl.eJSL.Plugin
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import org.eclipse.emf.common.util.EList

interface ExtendedPlugin extends Plugin {
	
	def EList<ExtendedEntity> getExtendedEntities()
	def EList<ExtendedParameter> getExtendedParameter()
}