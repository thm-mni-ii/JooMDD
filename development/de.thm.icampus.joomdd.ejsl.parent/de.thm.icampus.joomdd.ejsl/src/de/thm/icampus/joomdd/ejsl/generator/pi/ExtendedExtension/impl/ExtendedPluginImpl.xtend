package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.PluginImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPlugin
import de.thm.icampus.joomdd.ejsl.eJSL.Plugin
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil

class ExtendedPluginImpl extends PluginImpl implements ExtendedPlugin {
	
	EList<ExtendedEntity> extendedEntities
	EList<ExtendedParameter> extendedParameter
	String extensionName 
	new(Plugin plug){
		this.name = PlattformUtil.slugify(plug.name)
		this.manifest = plug.manifest
		this.type = plug.type
		this.languages = plug.languages
		this.entities = plug.entities
		this.localparameters = plug.localparameters
		extensionName =  "plg_" + this.name.toLowerCase
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
	
	override extensionName() {
		return extensionName
	}
	
}