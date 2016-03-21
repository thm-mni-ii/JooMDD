package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage

import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.eJSL.StaticPage
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup

interface ExtendedPage extends Page {
	
	def ExtendedStaticPage getStaticPageInstance()
	def ExtendedDynamicPage getExtendedDynamicPageInstance()
	def EList<ExtendedParameter>getExtendedGlobalParamater()
	def EList<ExtendedParameter>getExtendedLocalParameter()
	def EList<ExtendedParameterGroup>getExtendedParameterGroup()
}