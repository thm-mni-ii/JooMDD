package de.thm.icampus.ejsl.generator.pi.ExtendedPage.impl

import de.thm.icampus.ejsl.eJSL.impl.PageImpl
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.StaticPage
import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage

class ExtendedPageImpl extends PageImpl implements ExtendedPage {
	
	Page instance
	new(Page page){
		instance = page
	}
	
	override getStaticPageInstance() {
		if(instance instanceof StaticPage)
		return instance as StaticPage
	}
	
	override getExtendedDynamicPageInstance() {
		if(instance instanceof DynamicPage)
		   return new ExtendedDynamicPageImpl(instance)
	}
	
}