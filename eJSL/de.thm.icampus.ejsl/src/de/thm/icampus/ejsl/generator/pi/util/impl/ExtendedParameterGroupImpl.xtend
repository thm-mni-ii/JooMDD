package de.thm.icampus.ejsl.generator.pi.util.impl

import de.thm.icampus.ejsl.eJSL.impl.ParameterGroupImpl
import de.thm.icampus.ejsl.eJSL.ParameterGroup
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameterGroup
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameter
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.ejsl.generator.pi.util.PlattformIUtil

class ExtendedParameterGroupImpl extends ParameterGroupImpl implements ExtendedParameterGroup {
	
	ParameterGroup instance
	EList<ExtendedParameter> extendedParameterList
	new(ParameterGroup group){
		instance = group
		this.name = PlattformIUtil.slugify(group.name)
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