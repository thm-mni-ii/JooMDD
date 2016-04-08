package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.StaticPageImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedStaticPage
import de.thm.icampus.joomdd.ejsl.eJSL.StaticPage
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterGroupImpl

class ExtendedStaticPageImpl extends StaticPageImpl implements ExtendedStaticPage {
	
	StaticPage instance
	EList<ExtendedParameter> extendedGlobalParameter
	EList<ExtendedParameter> extendedLocalParamater
	EList<ExtendedParameterGroup> extendedParamterGroup
	new(StaticPage page){
		instance = page
		this.name = PlattformIUtil.slugify(page.name)
		this.links = page.links
		this.HTMLBody = page.HTMLBody
		this.globalparameters = page.globalparameters
		this.localparameters = page.localparameters
		this.parametergroups = page.parametergroups
		this.preserve = page.preserve
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
	
	override getExtendedGlobalParamater() {
		return extendedGlobalParameter
	}
	
	override getExtendedLocalParameter() {
		return extendedLocalParamater
	}
	
	override getExtendedParameterGroup() {
		return extendedParamterGroup
	}
	
	override getInstance() {
		return instance
	}
	
}
