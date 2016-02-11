package de.thm.icampus.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.ejsl.eJSL.Plugin
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameter
import org.eclipse.emf.common.util.EList

interface ExtendedPlugin extends Plugin {
	
	def EList<ExtendedEntity> getExtendedEntities()
	def EList<ExtendedParameter> getExtendedParameter()
}