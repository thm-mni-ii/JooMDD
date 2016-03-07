package de.thm.icampus.ejsl.generator.ps.JoomlaEntityGenerator

import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.ejsl.generator.ps.JoomlaUtil.Slug
import java.util.LinkedList
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess

class JoomlaEntityGenerator {
	EList<ExtendedEntity> entities
	String extendsionN
	boolean update
	new(EList<ExtendedEntity> entityList, String extendsionName, boolean updateScript){
		entities = entityList
		extendsionN = extendsionName
		update = updateScript
	}
	def dogenerate(){
		return sqlAdminSqlInstallContent(extendsionN,update)
	}
	def setUpdate(boolean updateScript){
		this.update = updateScript
	}
	
	def dogenerate(String path, IFileSystemAccess fileGen) {
		fileGen.generateFile(path + "/install.sql",sqlAdminSqlInstallContent(extendsionN,update))
		fileGen.generateFile(path+ "/uninstal.sql",sqlAdminSqlUninstallContent(extendsionN))
	}
	
	 public def CharSequence sqlAdminSqlInstallContent(String extensionName, boolean isupdate) {
        
        var LinkedList<String> visited = new LinkedList<String>();
        var StringBuffer result = new StringBuffer;
        while(visited.size < entities.size ){
	        for (ExtendedEntity e:entities){
	        	if(e.extendedReference.empty && !visited.contains(e.name)){
	        		result.append(generateSQLTable(e, isupdate, extensionName));
	        		visited.add(e.name);
	        	}
	         if(!visited.contains(e.name) && !e.references.empty && isAllreferenVisited(e.extendedReference, visited) ){
	        
	        	   result.append(generateSQLTable(e, isupdate,extensionName))
	        	   visited.add(e.name);
	        	   
	        	}
	        }
	        
	       }
         return result.toString
     
   }
	
	def boolean isAllreferenVisited(EList<ExtendedReference> list, List<String> visited) {
		
		for(ExtendedReference r: list){
			if(!visited.contains(r.extendedToEntity.name)){
			return false
			
			}
		}
		return true
	}
	
	
    
    def CharSequence generateSQLTable(ExtendedEntity table, boolean isupdate, String componentName)'''
    «IF !isupdate»
    DROP TABLE IF EXISTS `«componentName.toLowerCase»_«table.name.toLowerCase»`;
    «ENDIF»

   CREATE TABLE «IF isupdate» IF NOT EXISTS «ENDIF»`«componentName.toLowerCase»_«table.name.toLowerCase»` (
	«FOR a:table.allattribute»
		`«a.name.toLowerCase»` «a.generatorType.toLowerCase»,
	«ENDFOR»
	PRIMARY KEY (`id`)
	«FOR a:table.extendedAttributeList»
	«IF a.isunique»
	,UNIQUE KEY («a.name»«if(a.withattribute != null)''',«a.withattribute.name»'''»)
	«ENDIF» 
	«ENDFOR»
	«FOR ref:table.references»
	,INDEX(«Slug.transformAttributeListInString(ref.attribute,  ', ')»)
	«ENDFOR»
	«FOR ref:table.references»
	,CONSTRAINT `«componentName.toLowerCase»_«table.name.toLowerCase»_ibfk_«table.references.indexOf(ref)»` FOREIGN KEY(«Slug.transformAttributeListInString(ref.attribute,  ',')») REFERENCES `«componentName.toLowerCase»_«Slug.slugify(ref.entity.name.toLowerCase)»` («Slug.transformAttributeListInString(ref.attributerefereced, ', ')»)
	    ON UPDATE CASCADE
	    ON DELETE CASCADE
	«ENDFOR»

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
'''
	
   public def CharSequence sqlAdminSqlUninstallContent(String extensionName) {
        
        var LinkedList<String> visited = new LinkedList<String>();
        var StringBuffer result = new StringBuffer;
        result.append("SET foreign_key_checks = 0;")
         result.append("\n\r")
        while(visited.size < entities.size ){
	        for (ExtendedEntity e:entities){
	        	if(e.extendedReference.empty && !visited.contains(e.name)){	        		
	        		visited.add(e.name);
	        	}
	         if(!visited.contains(e.name) && !e.references.empty && isAllreferenVisited(e.extendedReference, visited) ){
	        	   visited.add(e.name);
	        	   
	        	}
	        }
	        
	       }
	       while(!visited.empty){
	       	 result.append('''DROP TABLE IF EXISTS `#__«extensionName.toLowerCase»_«(visited.removeLast).toLowerCase»`;''');
	         result.append("\n\r")
	       }
         return result.toString
    }
   
	
}