package de.thm.icampus.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.ejsl.eJSL.PageReference
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedPage

interface ExtendedPageReference extends PageReference {
	
	def ExtendedPage getExtendedPage()
	
}