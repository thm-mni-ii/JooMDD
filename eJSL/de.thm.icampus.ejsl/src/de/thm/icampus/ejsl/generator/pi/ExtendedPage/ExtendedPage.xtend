package de.thm.icampus.ejsl.generator.pi.ExtendedPage

import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.StaticPage
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameter
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameterGroup

interface ExtendedPage extends Page {
	
	def StaticPage getStaticPageInstance()
	def ExtendedDynamicPage getExtendedDynamicPageInstance()
	def EList<ExtendedParameter>getExtendedGlobalParamater()
	def EList<ExtendedParameter>getExtendedLocalParameter()
	def EList<ExtendedParameterGroup>getExtendedParameterGroup()
}