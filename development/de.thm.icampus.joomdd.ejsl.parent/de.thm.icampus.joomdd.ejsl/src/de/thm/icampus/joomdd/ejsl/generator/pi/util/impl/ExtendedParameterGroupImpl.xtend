package de.thm.icampus.joomdd.ejsl.generator.pi.util.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.ParameterGroupImpl
import de.thm.icampus.joomdd.ejsl.eJSL.ParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil

class ExtendedParameterGroupImpl extends ParameterGroupImpl implements ExtendedParameterGroup {
	
	ParameterGroup instance
	EList<ExtendedParameter> extendedParameterList
	new(ParameterGroup group){
		instance = group
		this.name = PlattformUtil.slugify(group.name)
		this.label = group.label
		this.globalparameters =group.globalparameters
		initListen()
	}
	
	def initListen() {
		extendedParameterList = new BasicEList<ExtendedParameter>()
		
		extendedParameterList.addAll(this.globalparameters.map[t| new ExtendedParameterImpl(t)])
	}
	
	override getExtendedParameterList() {
		return extendedParameterList
	}
	
}