package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery

class Select {
    Column column = null
    
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
        '''"«this.column»«IF this.alias !== null» AS «this.alias»«ENDIF»"'''
    }
}