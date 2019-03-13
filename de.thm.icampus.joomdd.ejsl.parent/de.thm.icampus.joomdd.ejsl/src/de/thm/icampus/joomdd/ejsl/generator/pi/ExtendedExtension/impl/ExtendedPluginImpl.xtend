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
import java.util.ArrayList
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.StaticLanguageValue
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug

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

    /**
     * You can use this function to add a static language variable.
     * These are variables that have a constant value. 
     */
    override String addLanguage(ArrayList<String> keyArray, StaticLanguageValue staticLanguageValue) {
        keyArray.add(staticLanguageValue.key)
        return addLanguage(keyArray, staticLanguageValue.value)
    }
    
    /**
     * You can add language key-value pairs using this function.
     * The given key (array) will only be added if the key does not exist already.
     * 
     * @param ArrayList keyArray
     * @param String    value
     * @return String   Returns the to upper case representation of the given keyArray
     */
    override String addLanguage(ArrayList<String> keyArray, String value) {
        val String upperCaseKey = keyArray.map[ k | 
            Slug.slugify(k)
        ].join("_").toUpperCase
        var String tmpValue = value
        
        // Use the key as value when the value is empty.
        if (value.trim.empty === true) {
            tmpValue = upperCaseKey        
        }
        
        // We need this (constant) variable for the loop below.
        val String languageValue = tmpValue
        
        // Iterate over each language and add the key if it does not exist.
        this.languages.forEach[ l | 
            // Add the new key only if it does not exist already
            var alreadyDefinedKey = l.keyvaluepairs.findFirst[ kv | 
                kv.name.equalsIgnoreCase(upperCaseKey)
            ]
            if (alreadyDefinedKey === null)
            {
                // Create the new key-value pair and add it to the language
                var keyValuePair  = EJSLFactory.eINSTANCE.createKeyValuePair
                keyValuePair.name = upperCaseKey
                keyValuePair.value = languageValue
                l.keyvaluepairs.add(keyValuePair)
            }
            else
            {
                if (alreadyDefinedKey.value.equalsIgnoreCase(languageValue) === false) {
                    println('''ExtendedComponentImpl: The given key «upperCaseKey» with the value «languageValue» is already defined with another value «alreadyDefinedKey.value»''')
                }
            }
        ]
        
        return upperCaseKey
    }   
	
}