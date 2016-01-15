package de.thm.icampus.ejsl.generator.pi.ExtendedPage

import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.StaticPage

interface ExtendedPage extends Page {
	
	def StaticPage getStaticPageInstance()
	def ExtendedDynamicPage getExtendedDynamicPageInstance()
}