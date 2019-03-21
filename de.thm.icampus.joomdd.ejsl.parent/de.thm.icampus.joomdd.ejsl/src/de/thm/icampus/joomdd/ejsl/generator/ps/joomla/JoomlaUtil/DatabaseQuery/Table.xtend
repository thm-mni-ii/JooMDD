package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery

import org.eclipse.xtend.lib.annotations.Accessors

class Table {
    @Accessors(PUBLIC_GETTER)
    String name
    
    @Accessors(PUBLIC_GETTER)
    String alias
    
    new (String name, String alias) {
        this.name = name
        this.alias = alias
    }
    
    override toString() {
        '''«name» AS «alias»'''
    }
}