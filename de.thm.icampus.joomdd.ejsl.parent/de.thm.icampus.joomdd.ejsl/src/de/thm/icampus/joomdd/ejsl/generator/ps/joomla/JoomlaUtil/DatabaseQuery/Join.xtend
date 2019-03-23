package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import org.eclipse.xtend.lib.annotations.Accessors

class Join {
    @Accessors(PUBLIC_GETTER)
    Table toTable
    
    @Accessors(PUBLIC_GETTER)
    Column toColumn
    
    Column fromColumn
    
    String type = "LEFT"
    
    @Accessors(PUBLIC_GETTER)
    ExtendedReference reference
    
     new(Table toTable, Column fromColumn, Column toColumn) {
        this.newInstance(toTable, fromColumn, toColumn, null)    
    }
    
    new(Table toTable, Column fromColumn, Column toColumn, ExtendedReference reference) {
        this.newInstance(toTable, fromColumn, toColumn, reference)    
    }
    
    def private newInstance(Table toTable, Column fromColumn, Column toColumn, ExtendedReference reference) {
        this.toTable = toTable
        this.fromColumn = fromColumn
        this.toColumn = toColumn
        this.reference = reference
    }
    
    override toString() {
        '''"«this.type»", "«this.toTable» ON «this.fromColumn» = «this.toColumn»"'''
    }
}