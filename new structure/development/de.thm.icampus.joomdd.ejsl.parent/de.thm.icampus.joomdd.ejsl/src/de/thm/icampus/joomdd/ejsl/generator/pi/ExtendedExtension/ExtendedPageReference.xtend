package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.joomdd.ejsl.eJSL.PageReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage

interface ExtendedPageReference extends PageReference {
	
	def ExtendedPage getExtendedPage()
	
}