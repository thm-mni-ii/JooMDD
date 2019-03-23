package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil

import org.eclipse.xtend.lib.annotations.Accessors

/**
 * This class represents the key value combination in language files.
 */
class StaticLanguageValue {
    
    @Accessors(PUBLIC_GETTER)
    String key
    
    @Accessors(PUBLIC_GETTER)
    String value
    
    new(String key, String value){
        this.key = key
        this.value = value
    }
}