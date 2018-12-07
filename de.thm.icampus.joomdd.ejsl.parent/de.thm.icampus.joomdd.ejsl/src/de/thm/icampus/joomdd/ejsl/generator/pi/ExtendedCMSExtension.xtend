package de.thm.icampus.joomdd.ejsl.generator.pi

import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedExtensions

interface ExtendedCMSExtension extends CMSExtension {
	
	def EList<ExtendedParameter> getGlobalparameterExtended()
	def EList<ExtendedParameterGroup> getparameterGroupExtended()
	def EList<ExtendedEntity> getEntitiesextended()
	def EList<ExtendedPage> getPageExtended()
	def EList<ExtendedExtensions> getExtensionsExtended()
	
}