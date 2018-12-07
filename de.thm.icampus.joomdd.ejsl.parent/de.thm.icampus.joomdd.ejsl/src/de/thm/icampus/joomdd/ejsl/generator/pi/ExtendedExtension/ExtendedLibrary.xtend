package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.joomdd.ejsl.eJSL.Library
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import org.eclipse.emf.common.util.EList

interface ExtendedLibrary extends Library {
	
	def EList<ExtendedEntity> getExtendedEntities()
	def String extensionName()
}