package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery

import org.eclipse.xtend.lib.annotations.Accessors
import java.util.ArrayList

class Table {
    @Accessors(PUBLIC_GETTER)
    String name
    
    @Accessors(PUBLIC_GETTER)
    String alias
    
    new (String name, String alias) {
        this.name = name
        this.alias = alias
    }
    
    new (String name) {
        this.name = name
    }
    
    override toString() {
        '''«name»«IF this.alias !== null» AS «alias»«ENDIF»'''
    }
}