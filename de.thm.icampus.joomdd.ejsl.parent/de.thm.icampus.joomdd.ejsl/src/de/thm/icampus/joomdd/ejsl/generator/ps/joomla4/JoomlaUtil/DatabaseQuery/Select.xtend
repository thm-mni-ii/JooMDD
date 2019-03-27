package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.DatabaseQuery

/**
 * Represents a database select.
 */
class Select {
    
    /**
     * The column to select
     */
    Column column = null
    
    /**
     * The columns alias in the select statement.
     */
    String alias = null
    
    new(Column column) {
        this.column = column
    }
    
    new(Column column, String alias) {
        this.newInstance(column, alias)
    }
    
    new(String columnTableWithName, String alias) {
        newInstance(new Column(columnTableWithName), alias)
    }
    
    def newInstance(Column column, String alias) {
        this.column = column
        this.alias = alias
    }
    
    override toString() {
        '''«this.column»«IF this.alias !== null» AS «this.alias»«ENDIF»'''
    }
}