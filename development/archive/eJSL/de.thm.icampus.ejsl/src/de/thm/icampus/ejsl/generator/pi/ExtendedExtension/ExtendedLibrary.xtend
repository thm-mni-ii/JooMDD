package de.thm.icampus.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.ejsl.eJSL.Library
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import org.eclipse.emf.common.util.EList

interface ExtendedLibrary extends Library {
	
	def EList<ExtendedEntity> getExtendedEntities()
}