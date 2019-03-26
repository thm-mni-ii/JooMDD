package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaEntityGenerator

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug
import java.util.LinkedList
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.joomdd.ejsl.generator.pi.util.MappingEntity

/**
 * This class contains the templates to generate the fiel cardinalities.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
class JoomlaEntityGenerator {
    List<ExtendedEntity> entities
    String extendsionN
    boolean update

    new(EList<ExtendedEntity> entityList, String extendsionName, boolean updateScript) {
        entities = entityList
        extendsionN = extendsionName
        update = updateScript
    }

    def dogenerate() {
        return sqlAdminSqlInstallContent(extendsionN, update)
    }

    def setUpdate(boolean updateScript) {
        this.update = updateScript
    }

    def dogenerate(String path, IFileSystemAccess fileGen) {
        if (update) {
            fileGen.generateFile(path + "/update.sql", generateUpdateScript(extendsionN))
            fileGen.generateFile(path + "/install.sql", sqlAdminSqlInstallContent(extendsionN, update))
        } else {
            fileGen.generateFile(path + "/install.sql", sqlAdminSqlInstallContent(extendsionN, update))
        }

        fileGen.generateFile(path + "/uninstall.sql", sqlAdminSqlUninstallContent(extendsionN))
    }

    public def CharSequence sqlAdminSqlInstallContent(String extensionName, boolean isupdate) {
        var StringBuffer result = new StringBuffer;
        for (ExtendedEntity e : entities) {
            result.append(generateSQLTable(e, isupdate, extensionName));
        }
        for (ExtendedEntity e : entities.filter[ e | e.references.empty === false && e.references.forall[ r | r.upper.equalsIgnoreCase("-1") ] === false]) {
            result.append(generateSQLConstraints(e, extensionName));
        }

        return result.toString
    }
    
    def generateSQLConstraints(ExtendedEntity entity, String extensionName)
        '''
        ALTER TABLE `«Slug.databaseName(extensionName, entity.name)»`
            «entity.allRefactoryReference.filter[ reference | !reference.preserve && reference.upper.equalsIgnoreCase("1") ].map[ reference |
                '''
                ADD CONSTRAINT `«extensionName»_«entity.name»_ibfk_«entity.allRefactoryReference.indexOf(reference)»` FOREIGN KEY(«reference.getReferenceIDAttribute») REFERENCES `«Slug.databaseName(extensionName, Slug.slugify(reference.entity.name))»` («reference.getReferencedIDAttribute»)
                ON UPDATE CASCADE
                ON DELETE CASCADE'''
            ].join(''', 
            ''')»
        ;
        '''

    def CharSequence generateSQLTable(ExtendedEntity table, boolean isupdate, String componentName) {
        
        var List<ExtendedAttribute> filteredAllExtendedAttributes
        
        if (table.instance instanceof MappingEntity) {
            val referenceAttributeList = table.references.map[ reference |
                reference.attribute.get(0).name
            ]
        
            filteredAllExtendedAttributes = table.allExtendedAttributes.filter[ t |
                !t.isPreserve && referenceAttributeList.contains(t.name) === false
            ].toList
        } else {
            filteredAllExtendedAttributes = table.allExtendedAttributes.filter[ t |
                !t.isPreserve
            ].toList
        }
        
        // Remove duplicate attribute names.
        var referenceIDAttributeList = table.allExtendedReferences.stream.map[ reference |
            reference.getReferenceIDAttribute
        ].distinct.toArray
        
        return '''
            «IF isupdate»
            DROP TABLE IF EXISTS `«Slug.databaseName(componentName, table.name)»`;
            «ENDIF»
            CREATE TABLE  IF NOT EXISTS `«Slug.databaseName(componentName, table.name)»` (
                «FOR a : filteredAllExtendedAttributes»
                `«a.name»` «a.generatorType»,
                «ENDFOR»
                «FOR ExtendedAttribute a : table.ownExtendedAttributes»
                «IF a.isunique»
                UNIQUE KEY («a.name»«if(a.withattribute !== null)''',«a.withattribute.name»'''»),
                «ENDIF»
                «ENDFOR»
                «FOR referenceIDAttribute : referenceIDAttributeList»
                INDEX(«referenceIDAttribute»),
                «ENDFOR»
                PRIMARY KEY (`«table.primaryKey.name»`)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
        '''
    }

    public def CharSequence sqlAdminSqlUninstallContent(String extensionName) {
        var LinkedList<ExtendedEntity> visited = new LinkedList<ExtendedEntity>;
        visited.addAll(entities)
        var StringBuffer result = new StringBuffer;
        result.append("SET foreign_key_checks = 0;")
        result.append("\n\r")
        while (!visited.empty) {
            result.
                append('''DROP TABLE IF EXISTS `«Slug.databaseName(extensionName, (visited.removeLast.name))»`;''');
            result.append("\n\r")
        }
        return result.toString
    }

    public def CharSequence generateUpdateScript(String extensionName) 
    '''
        «FOR ExtendedEntity en : entities.filter[t | !t.preserve]»
        ALTER TABLE `«Slug.databaseName(extensionName, en.name)»`
            «FOR ExtendedAttribute attr: en.allRefactoryAttribute»
                «IF attr.name != en.allRefactoryAttribute.getMylastAttribute.name»
                    ADD COLUMN IF NOT EXISTS `«attr.name»`  «attr.generatorType» «if(attr.isprimary) " PRIMARY KEY"»
                    «var ExtendedAttribute after = getAfterAttribute(attr,en)»
                    «IF after !== null»
                        AFTER «after.name»,
                    «ELSE»
                        ,
                    «ENDIF»
                «ELSE»
                    ADD COLUMN IF NOT EXISTS `«attr.name»`  «attr.generatorType»
                    «var ExtendedAttribute after = getAfterAttribute(attr,en)»
                    «IF after !== null»
                        AFTER «after.name»
                    «ENDIF»
                «ENDIF»
            «ENDFOR»
        ;
        
        «FOR ExtendedAttribute attr: en.allRefactoryAttribute.filter[t | t.isunique && !t.preserve]»
            CREATE UNIQUE INDEX `«extensionName»_«en.name»_ibih_«attr.name»«if(attr.withattribute !== null) "_" + attr.withattribute.name»` ON `«Slug.databaseName(extensionName, en.name)»`  (`«attr.name»` «if(attr.withattribute !== null)''',`«attr.withattribute.name»`'''»);
        «ENDFOR»
        
        «IF !en.references.isEmpty»
            «generateSQLConstraints(en, extensionName)»
        «ENDIF»
        «ENDFOR»
        
    '''

    def ExtendedAttribute getMylastAttribute(Iterable<ExtendedAttribute> list) {
        return list.get(list.size - 1)
    }

    def ExtendedReference getMylastReference(Iterable<ExtendedReference> list) {
        return list.get(list.size - 1)
    }

    def getAfterAttribute(ExtendedAttribute attribute, ExtendedEntity ent) {
        if (ent.ownExtendedAttributes.get(0).name.equalsIgnoreCase(attribute.name)) {
            return null
        }
        for (ExtendedAttribute attr : ent.ownExtendedAttributes) {
            if (attr.name.equalsIgnoreCase(attribute.name)) {
                var int index = ent.ownExtendedAttributes.indexOf(attr)
                return ent.ownExtendedAttributes.get(index - 1)
            }
        }
    }
}
