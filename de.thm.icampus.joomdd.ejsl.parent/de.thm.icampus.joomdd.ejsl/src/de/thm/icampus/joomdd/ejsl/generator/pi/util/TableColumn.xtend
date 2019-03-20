package de.thm.icampus.joomdd.ejsl.generator.pi.util

import org.eclipse.xtend.lib.annotations.Accessors

class TableColumn {
    @Accessors(PUBLIC_GETTER)
    String tableName
    
    String columnName
    
    @Accessors(PUBLIC_GETTER)
    String type
    
    new(String tableName, String columnName) {
        newInstance(tableName, columnName, tableName)
    }
    
    new(String tableName, String columnName, String type) {
        newInstance(tableName, columnName, type)
    }
    
    def private newInstance(String tableName, String columnName, String type) {
        this.tableName = tableName
        this.columnName = columnName
        this.type = type
    }
    
    override toString() {
        '''«this.tableName».«this.columnName»'''
    }
}