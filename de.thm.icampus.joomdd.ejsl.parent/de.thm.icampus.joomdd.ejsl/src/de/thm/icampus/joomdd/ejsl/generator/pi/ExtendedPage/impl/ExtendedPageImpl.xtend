package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.PageImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.eJSL.StaticPage
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterGroupImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedStaticPage
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil

class ExtendedPageImpl extends PageImpl implements ExtendedPage {
	
	Page instance
	EList<ExtendedParameter> extendedGlobalParameter
	EList<ExtendedParameter> extendedLocalParamater
	EList<ExtendedParameterGroup> extendedParamterGroup
	new(Page page){
		instance = page
		this.name = PlattformUtil.slugify(page.name)
		this.links = page.links
		this.globalparameters = page.globalparameters
		this.localparameters = page.localparameters
		this.parametergroups = page.parametergroups
		initList()
		}
	
	def initList() {
		extendedGlobalParameter = new BasicEList<ExtendedParameter>()
		extendedLocalParamater = new BasicEList<ExtendedParameter>()
		extendedParamterGroup = new BasicEList<ExtendedParameterGroup> 
		
		extendedGlobalParameter.addAll(this.globalparameters.map[para | new ExtendedParameterImpl(para)])
		extendedLocalParamater.addAll(this.localparameters.map[par| new ExtendedParameterImpl(par)])
		extendedParamterGroup.addAll(this.parametergroups.map[t | new ExtendedParameterGroupImpl(t)])
	}
	
	override getStaticPageInstance() {
		if(instance instanceof StaticPage)
		return new ExtendedStaticPageImpl(instance)
		
		return null
	}
	
	override getExtendedDynamicPageInstance() {
		if(instance instanceof DynamicPage)
		   return new ExtendedDynamicPageImpl(instance)
		return null
	}
	
	override getExtendedGlobalParamater() {
		return extendedGlobalParameter
	}
	
	override getExtendedLocalParameter() {
		return extendedLocalParamater
	}
	
	override getExtendedParameterGroup() {
		return extendedParamterGroup
	}
	
	override getIntance() {
		return instance
	}
	
}