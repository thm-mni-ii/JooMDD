package de.thm.icampus.ejsl.generator.pi.ExtendedPage.impl

import de.thm.icampus.ejsl.eJSL.impl.PageImpl
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.StaticPage
import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.ejsl.generator.pi.util.PlattformIUtil

class ExtendedPageImpl extends PageImpl implements ExtendedPage {
	
	Page instance
	new(Page page){
		instance = page
		this.name = PlattformIUtil.slugify(page.name)
		this.links = page.links
		this.globalparameters = page.globalparameters
		this.localparameters = page.localparameters
		this.parametergroups = page.parametergroups
		}
	
	override getStaticPageInstance() {
		if(instance instanceof StaticPage)
		return instance as StaticPage
		
		return null
	}
	
	override getExtendedDynamicPageInstance() {
		if(instance instanceof DynamicPage)
		   return new ExtendedDynamicPageImpl(instance)
		return null
	}
	
}