package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage

import de.thm.icampus.joomdd.ejsl.eJSL.StaticPage
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup

interface ExtendedStaticPage extends StaticPage {
    def EList<ExtendedParameter>getExtendedGlobalParamater()
	def EList<ExtendedParameter>getExtendedLocalParameter()
	def EList<ExtendedParameterGroup>getExtendedParameterGroup()	
	def StaticPage getInstance()
}