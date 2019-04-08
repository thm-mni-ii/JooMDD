package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.DatabaseQuery

import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Represents a table in a database query.
 */
class Table {
    
    /**
     * The table name
     */
    @Accessors(PUBLIC_GETTER)
    String name
    
    /**
     * The table alias, this should be unique.
     */
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