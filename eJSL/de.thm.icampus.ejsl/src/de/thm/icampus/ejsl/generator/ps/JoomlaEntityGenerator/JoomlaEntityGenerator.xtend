package de.thm.icampus.ejsl.generator.ps.JoomlaEntityGenerator

import de.thm.icampus.ejsl.generator.ps.EntityGenerator
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import java.util.HashSet
import de.thm.icampus.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.ejsl.eJSL.Reference
import org.eclipse.xtext.generator.IFileSystemAccess

class JoomlaEntityGenerator extends EntityGenerator{
	EList<ExtendedEntity> entities
	String extendsionN
	boolean update
	new(EList<ExtendedEntity> entityList, String extendsionName, boolean updateScript){
		entities = entityList
		extendsionN = extendsionName
		update = updateScript
	}
	override dogenerate(){
		return sqlAdminSqlInstallContent(extendsionN,update)
	}
	def setUpdate(boolean updateScript){
		this.update = updateScript
	}
	
	override dogenerate(String path, IFileSystemAccess fileGen) {
		fileGen.generateFile(path + "/install",sqlAdminSqlInstallContent(extendsionN,update))
		fileGen.generateFile(path+ "/uninstal",sqlAdminSqlUninstallContent(extendsionN))
	}
	
	 def CharSequence sqlAdminSqlInstallContent(String extensionName, boolean isupdate) {
        
        var HashSet<ExtendedEntity> visited = new HashSet<ExtendedEntity>();
        var StringBuffer result = new StringBuffer;
        while(visited.size != entities.size){
	        for (ExtendedEntity e:entities){
	        	if(e.references.empty && !visited.contains(e)){
	        		result.append(generateSQLTable(e, isupdate, extensionName));
	        		visited.addAll(e);
	        	}
	        	if(!visited.contains(e) && !e.references.empty && isAllreferenVisited(e.references, visited) ){
	        
	        	   result.append(generateSQLTable(e, isupdate,extensionName))
	        	   visited.addAll(e);
	        	}
	        }
	       }
         return result.toString
     
   }
	
	def boolean isAllreferenVisited(EList<Reference> list, HashSet<ExtendedEntity> entities) {
		
		for(Reference r: list){
			if(!entities.contains(r.entity))
			return false
		}
		return true
	}
    
    def CharSequence generateSQLTable(ExtendedEntity table, boolean isupdate, String componentName)'''
    «IF !isupdate»
    DROP TABLE IF EXISTS `#__«componentName.toLowerCase»_«table.name.toLowerCase»`;
    «ENDIF»

   CREATE TABLE «IF isupdate» IF NOT EXISTS «ENDIF»`#__«componentName.toLowerCase»_«table.name.toLowerCase»` (
    `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `asset_id` INT(10) UNSIGNED NOT NULL DEFAULT '0',    
    `ordering` INT(11)  NOT NULL ,
    `state` TINYINT(1)  NOT NULL ,
    `checked_out` INT(11)  NOT NULL,
    `checked_out_time` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	`created_by` INT(11)  NOT NULL ,
	«FOR a:table.allattribute»
		`«a.name.toLowerCase»` «a.generatorType.toLowerCase»,
	«ENDFOR»
	`published` TINYINT(1),
	`params` TEXT,
«FOR attr:table.allattribute»
«IF attr.hasReference»
	«attr.genReference(componentName)»,
«ENDIF»
«ENDFOR»
PRIMARY KEY (`id`)
«FOR a:table.extendedAttributeList»
«IF a.isunique»
,  UNIQUE KEY («a.name»)
«ENDIF»
«ENDFOR»
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
'''
	
   def CharSequence sqlAdminSqlUninstallContent(String extensionName) '''
        «/*val entities=component.eAllContents.toIterable.filter(typeof(Entity))*/»
        SET foreign_key_checks = 0;
        
        «FOR e:entities»
        	DROP TABLE IF EXISTS `#__«extensionName.toLowerCase»_«e.name.toLowerCase»`;
        «ENDFOR»
    '''
}