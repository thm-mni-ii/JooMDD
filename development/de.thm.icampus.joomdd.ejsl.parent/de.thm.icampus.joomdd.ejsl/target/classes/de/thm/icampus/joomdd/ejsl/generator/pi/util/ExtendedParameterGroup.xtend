package de.thm.icampus.joomdd.ejsl.generator.pi.util

import de.thm.icampus.joomdd.ejsl.eJSL.ParameterGroup
import org.eclipse.emf.common.util.EList

interface ExtendedParameterGroup extends ParameterGroup {
	
	def EList<ExtendedParameter> getExtendedParameterList()
}