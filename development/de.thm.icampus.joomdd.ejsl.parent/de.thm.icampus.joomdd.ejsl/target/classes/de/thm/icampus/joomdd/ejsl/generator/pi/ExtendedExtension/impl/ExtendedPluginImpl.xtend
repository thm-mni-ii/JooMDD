package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.PluginImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPlugin
import de.thm.icampus.joomdd.ejsl.eJSL.Plugin
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterImpl

class ExtendedPluginImpl extends PluginImpl implements ExtendedPlugin {
	
	EList<ExtendedEntity> extendedEntities
	EList<ExtendedParameter> extendedParameter
	new(Plugin plug){
		this.name = PlattformIUtil.slugify(plug.name)
		this.manifest = plug.manifest
		this.type = plug.type
		this.languages = plug.languages
		this.entities = plug.entities
		this.localparameters = plug.localparameters
		init()
	}
	
	
	def void init(){
		extendedEntities = new BasicEList<ExtendedEntity>()
	    extendedParameter = new BasicEList<ExtendedParameter>()
	    extendedEntities.addAll(entities.map[t | new ExtendedEntityImpl(t)])
	    extendedParameter.addAll(localparameters.map[t | new ExtendedParameterImpl(t)])
	}
	override getExtendedEntities() {
		return extendedEntities
	}
	
	override getExtendedParameter() {
		return extendedParameter
	}
	
}