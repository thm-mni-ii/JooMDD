package de.thm.icampus.ejsl.generator.pi.util

import de.thm.icampus.ejsl.eJSL.ParameterGroup
import org.eclipse.emf.common.util.EList

interface ExtendedParameterGroup extends ParameterGroup {
	
	def EList<ExtendedParameter> getExtendedParameterList()
}