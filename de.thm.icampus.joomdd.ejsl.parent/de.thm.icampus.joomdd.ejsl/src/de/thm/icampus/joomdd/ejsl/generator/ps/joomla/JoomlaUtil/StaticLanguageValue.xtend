package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil

import org.eclipse.xtend.lib.annotations.Accessors

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