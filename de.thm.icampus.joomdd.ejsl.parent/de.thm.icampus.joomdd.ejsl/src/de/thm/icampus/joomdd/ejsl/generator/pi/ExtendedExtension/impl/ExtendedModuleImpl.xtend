package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.ModuleImpl
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.eJSL.ComponentReference
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.CoreComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil
import java.util.ArrayList
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.StaticLanguageValue
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug

class ExtendedModuleImpl extends ModuleImpl implements ExtendedModule {
	
	ExtendedPageReference extendedReference
	String comName
	String extensionName 
	new(Module mod){
		this.name = PlattformUtil.slugify(mod.name)
		this.manifest = mod.manifest
		this.languages = mod.languages
		this.pageRef = mod.pageRef
		extendedReference = new ExtendedPageReferenceImpl(pageRef)
		comName = init(mod.pageRef.pagescr)
		extensionName = "mod_" + this.name.toLowerCase
	}
	
	def init(ComponentReference reference) {
		if(reference == null)
		  return null
		 if(reference.ref != null)
	    return PlattformUtil.slugify(reference.ref.name)
	  if(reference.core != null)
	  return PlattformUtil.slugify(reference.core.getName())
	 
	}
	
	override getExtendedPageReference() {
		return extendedReference
	}

	override getExtendedComponentName() {
		return comName
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
                    println('''ExtendedComponentImpl: The given key «upperCaseKey» with the value  «languageValue» is already defined with another value «alreadyDefinedKey.value»''')
                }
            }
        ]
        
        return upperCaseKey
    }	
}