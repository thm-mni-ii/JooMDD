package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery

import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import java.util.HashMap
import java.util.ArrayList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.MappingEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedReferenceImpl
import org.eclipse.xtend.lib.annotations.Accessors
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug

/**
 * Represents a database query,
 * containing the select, origin table, joins and aliases.
 */
class Query {   
    
    /**
     * The main table, used in the from clause.
     */
    @Accessors(PUBLIC_SETTER, PUBLIC_GETTER)
    Table mainTable
    
    /**
     * The columns to select.
     */
    ArrayList<Select> mainSelect = newArrayList
    
    /**
     * A list of joins to get referenced columns.
     */
    @Accessors(PUBLIC_GETTER)
    ArrayList<LeftJoin> joinList = newArrayList
  
    /**
     * A list of table aliases.
     */
    HashMap<String, Integer> aliasList = newHashMap
    
    /**
     * The component which holds the data.
     */
    ExtendedComponent component
        
    new(ExtendedComponent component) {
       this.component = component
    }
    
    new() {
    }
    
    /**
     * This methods is responsible to create unique aliases.
     * 
     * @param String alias
     * 
     * @return String
     */
    def getUniqueAlias(String alias) {
        var aliasCounter = aliasList.getOrDefault(alias, 0)
        var uniqueAlias = alias
        
        if (aliasCounter > 0) {
            uniqueAlias = '''«uniqueAlias»«aliasCounter»'''
        }
        
        aliasCounter++;
        aliasList.put(alias, aliasCounter);
        
        return uniqueAlias
    }
    
    /**
     * This function completes the query to get all items from the database.
     * 
     * @param ExtendedDynamicPage indexpage
     * @param ExtendedEntity mainEntity
     * @param String separator
     * 
     * @return String
     */
    def getListQuery(ExtendedDynamicPage indexpage, ExtendedEntity mainEntity, String separator, boolean forList) {
        var mainEntityNameAlias = this.getUniqueAlias(indexpage.entities.get(0).name)
        this.mainTable = new Table('''#__«this.component.name»_«indexpage.entities.get(0).name»''', mainEntityNameAlias)
        
        // editor
        var ucAlias = this.getUniqueAlias("uc")
        var ucSelectColumn = new Column(ucAlias, '''name''')
        var ucSelect = new Select(ucSelectColumn, '''editor''')
        
        var ucJoinTable = new Table('''#__users''', ucAlias)
        var ucJoinFromColumn = new Column(this.mainTable.alias, '''checked_out''')
        var ucJoinToColumn = new Column(ucAlias, '''id''')
        var ucJoin = new LeftJoin(ucJoinTable, ucJoinFromColumn, ucJoinToColumn)
        this.joinList.add(ucJoin)
        
        // created_by
        var createdByAlias = this.getUniqueAlias("created_by")
        var createdBySelectColumn = new Column(createdByAlias, '''name''')
        var createdBySelect = new Select(createdBySelectColumn, '''created_by''')
        
        var createdByJoinTable = new Table('''#__users''', createdByAlias)
        var createdByJoinFromColumn = new Column(this.mainTable.alias, '''created_by''')
        var createdByJoinToColumn = new Column(createdByAlias, '''id''')
        var createdByJoin = new LeftJoin(createdByJoinTable, createdByJoinFromColumn, createdByJoinToColumn)
        this.joinList.add(createdByJoin)
        
        // user
        var userAlias = this.getUniqueAlias("user")
        var userSelectColumn = new Column(userAlias, '''name''')
        var userSelect = new Select(userSelectColumn, '''user''')
        
        var userJoinTable = new Table('''#__users''', userAlias)
        var userJoinFromColumn = new Column(this.mainTable.alias, '''created_by''')
        var userJoinToColumn = new Column(userAlias, '''id''')
        var userJoin = new LeftJoin(userJoinTable, userJoinFromColumn, userJoinToColumn)
        this.joinList.add(userJoin)
        
        var selectAndJoins = this.createSelectAndJoins(indexpage.extendedEntityList.get(0).allExtendedReferences, indexpage.entities.get(0).name)
        var queryForNToM = this.createQueryForNToM(indexpage.extendedEntityList.get(0), separator)
        
        var whereStateColumn = new Column(this.mainTable.alias, '''state''')
        var whereState = '''"«whereStateColumn» = " . (int) $published'''
        var whereState1 = '''"«whereStateColumn» IN (0, 1)"'''
        
        var whereCreatedByColumn = new Column(this.mainTable.alias, '''created_by''')
        var whereCreatedBy = '''"«whereCreatedByColumn» = '" . $db->escape($created_by) . "'"'''
        
        var whereFilter = indexpage.extendFiltersList.map[ filterAttribute | 
            var whereColumn = getWhereColumnName(filterAttribute)
            var whereStatement = '''"«whereColumn» = '" . $db->escape($«filterAttribute.name») . "'"'''
    
            return '''
                    // Filter by «whereColumn»
                    if (!empty($«filterAttribute.name»)) {
                        $query->where(«whereStatement»);
                    }
                    '''
        ].join('''

        ''')
        
        var searchFilterStatementList = newArrayList
        for (filterAttribute : indexpage.extendedTableColumnList) {
            var whereColumn = getWhereColumnName(filterAttribute)
            searchFilterStatementList.add('''
            // Search by «whereColumn»
            "«whereColumn» LIKE $search"''')
        }
        var searchFilter = '''
                    $query->andWhere(
                        array(
                            «searchFilterStatementList.join(''',
                        ''')»
                        )
                    );
                    '''
        
        return '''
        $query->from('`«this.mainTable.name»` AS «this.mainTable.alias»');

        // Join over the users for the checked out user
        $query->select("«ucSelect»");
        $query->join(«ucJoin»);

        // Join over the user field 'created_by'
        $query->select("«createdBySelect»");
        $query->join(«createdByJoin»);

        // Join over the user field 'user'
        $query->select("«userSelect»");
        $query->join(«userJoin»);

        «selectAndJoins»
        «queryForNToM»

        «this.createGroupBy(indexpage.extendedEntityList.get(0))»

        «IF forList === true»
        // Filter by published state
        if (is_numeric($published)) {
            $query->where(«whereState»);
        } elseif ($published === '') {
            $query->where(«whereState1»);
        }

        // Filter by User
        if (!empty($created_by)) {
            $query->where(«whereCreatedBy»);
        }
        
        «whereFilter»

        // Filter by search in attribute
        if (!empty($search)) {
            if (stripos($search, '«mainEntity.primaryKey.name»:') === 0) {
                $query->where('«this.mainTable.alias».«mainEntity.primaryKey.name» = ' . (int) substr($search, 3));
            } else {
                $search = $db->Quote('%' . $db->escape($search, true) . '%');

                «searchFilter»
            }
        }

        // Add the list ordering clause.
        if ($orderCol && $orderDirn) {
            $query->order($db->escape($orderCol . ' ' . $orderDirn));
        }
        «ELSE»
        $query->where("«this.mainTable.alias».«mainEntity.primaryKey.name» = $pk");
        «ENDIF»
        '''
    }
    
    /**
     * This method takes care to get the right aliases for the given attribute.
     * In case the attribute is a referenced attribute we have to use the referenced table alias.
     * 
     * @param ExtendedAttribute attribute
     * 
     * @return Column
     */
    def private Column getWhereColumnName(ExtendedAttribute attribute) {
        var join = this.joinList.findFirst[ join |
            if (join.reference !== null && join.reference.entity instanceof MappingEntity === false) {
                return join.reference.referenceAttribute.equals(attribute.name)
            } else {
                return false
            }
        ]
        
        var tableAlias = this.mainTable.alias
        var columnName = attribute.name
        
        // We found a reference and have to use the referenced table alias.
        if (join !== null) {                
            tableAlias = join.toTable.alias
            columnName = join.reference.referencedAttribute
        }
        
        var column = new Column(tableAlias, columnName)
        
        return column
    }
    
    /**
     * This method creates the select and joins to get a column form a referenced table
     * with a mapping table inbetween.
     * 
     * @param ExtendedEntity entity
     * @param String separator
     * 
     * @return String
     */
    def createQueryForNToM(ExtendedEntity entity, String separator) {
        val entityName = entity.name
        var queries = newArrayList
        
        // Iterate 
        for (extendedReference : entity.allExtendedReferences.filter[reference | reference.entity instanceof MappingEntity]){
            val reference = extendedReference.destinationEntity.references.findFirst[ r | 
                r.entity.name.equals(entityName) === false
            ]
            val referenceEntityName = reference.entity.name
            
            var query = ''''''
            
            val mappingEntity = extendedReference.entity
                        
            // Create join from main table to mapping table
            var mappingEntityName = mappingEntity.name
            var mappingAlias = getUniqueAlias(mappingEntityName)
            query += createJoin(extendedReference, mappingAlias)
            
            // Create join from mapping table to referenced table
            var destinationReference = mappingEntity.references.map[ ref |
                new ExtendedReferenceImpl(ref, mappingEntity)
            ].findFirst[ ref |
                ref.entity !== extendedReference.sourceEntity
            ]
            
            if (destinationReference === null) {
                throw new UnsupportedOperationException
            }
            
            var destinationEntityName = destinationReference.entity.name
            var destinationAlias = getUniqueAlias(destinationEntityName)
            query += createJoin(destinationReference, destinationAlias, mappingAlias)
            
            var referencedAttributeName = destinationReference.referencedAttribute
            
            query = '''
                
                // Select the referenced field «destinationAlias».«referencedAttributeName»
                $query->select('GROUP_CONCAT(«destinationAlias».«referencedAttributeName» SEPARATOR "«separator»") AS «referenceEntityName»_«referencedAttributeName»');
                «query»'''
            queries.add(query)
        }
        
        return queries.join
    }
    
    /**
     * This method will create the given join type by joinType for the given references
     * 
     * @param EList<ExtendedReference> extendedReference
     * @param String componentName
     * @param String entityName
     * @param String joinType
     * 
     * @return String
     */
    def createSelectAndJoins(EList<ExtendedReference> extendedReference, String entityName) {        
        // Iterate over all references that do not reference to a mapping table.
        var createdSelectAndJoins = extendedReference.filter[reference | 
            reference.destinationEntity instanceof MappingEntity === false
        ].map[ ref |
            val destinationEntityName = ref.destinationEntity.name
            val alias = getUniqueAlias(destinationEntityName)   
            var selectColumn = new Column(alias, ref.referencedAttribute)         
            var select = new Select(selectColumn, ref.referenceAttribute)
            
            return '''
                    // Select the referenced field «ref.referencedAttribute».
                    $query->select("«select»");
                    «this.createJoin(ref, alias)»
                    '''
        ]
        
        return createdSelectAndJoins.join('''

        ''')
    }
    
    /**
     * This method creates a left join from the main table to the given referenced table.
     * 
     * @param ExtendedReference reference
     * @param String toAlias
     * 
     * @return String
     */
    def private createJoin(ExtendedReference reference, String toAlias) {
        createJoin(reference, toAlias, this.mainTable.alias)
    }
    
    /**
     * This method creates a left join from the given table to the given referenced table.
     * 
     * @param ExtendedReference reference
     * @param String toAlias
     * @param String fromAlias
     * 
     * @return String
     */
    def private createJoin(ExtendedReference reference, String toAlias, String fromAlias) {
        var toTable = new Table(Slug.databaseName(this.component.name, reference.destinationEntity.name), toAlias)
        var fromColumn = new Column(fromAlias, reference.referenceIDAttribute)
        var toColumn = new Column(toAlias, reference.referencedIDAttribute)
        var join = new LeftJoin(toTable, fromColumn, toColumn, reference)
        this.joinList.add(join)
        
        return '''
                $query->join(«join»);
                '''
    }
    
    /**
     * Creates the group statement for a database query.
     * 
     * @param ExtendedEntity entity
     * 
     * @return String
     */
    def createGroupBy(ExtendedEntity entity) {
        '''$query->group('«this.mainTable.alias».«entity.attributes.findFirst[a | a.isprimary].name»');'''
    }
    
    /**
     * Adds the given select to the select list.
     * 
     * @param Select select
     * 
     * @return boolean
     */
    def addToMainSelect(Select select) {
        this.mainSelect.add(select)
    }
    
    /**
     * Gets a comma separated list of all stored selects.
     * 
     * @return String
     */
    def String getMainSelect() {
        return this.mainSelect.join(''',
        ''')
    }
}