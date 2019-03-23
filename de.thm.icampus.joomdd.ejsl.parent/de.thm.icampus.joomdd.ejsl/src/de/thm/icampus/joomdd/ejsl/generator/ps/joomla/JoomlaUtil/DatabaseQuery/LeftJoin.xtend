package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Representation of a left join.
 */
class LeftJoin {
    
    /**
     * The table to join to.
     */
    @Accessors(PUBLIC_GETTER)
    Table toTable
    
    /**
     * The column to join to, will be used in the ON clause.
     */
    @Accessors(PUBLIC_GETTER)
    Column toColumn
    
    /**
     * The column from the origin table, will be used in the ON clause.
     */
    Column fromColumn
    
    /**
     * The join type.
     */
    String type = "LEFT"
    
    /**
     * This variable holds the reference which this join represents.
     */
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