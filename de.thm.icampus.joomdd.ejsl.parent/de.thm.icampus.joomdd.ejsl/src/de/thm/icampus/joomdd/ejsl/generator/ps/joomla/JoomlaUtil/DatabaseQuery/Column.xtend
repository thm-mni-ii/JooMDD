package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery

class Column {
    /**
     * The table name can be null.
     * If so, the column name might contain a variable containing the table plus colum name.
     */
    String tableName
    
    String columnName
    
    new(String tableName, String columnName) {
        this.tableName = tableName
        this.columnName = columnName
    }
    
    new(String tableNamePlusColumnName) {
        this.columnName = tableNamePlusColumnName
    }
    
    override toString() {
        '''«IF this.tableName !== null»«this.tableName».«ENDIF»«this.columnName»'''
    }
}