package de.thm.icampus.ejsl.generator.ps.JoomlaEntityGenerator

import de.thm.icampus.ejsl.generator.ps.EntityGenerator
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import java.util.HashSet
import de.thm.icampus.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.ejsl.eJSL.Reference
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.EJSLFactory
import de.thm.icampus.ejsl.eJSL.StandardTypes
import de.thm.icampus.ejsl.eJSL.SimpleHTMLTypeKinds
import de.thm.icampus.ejsl.eJSL.StandardTypeKinds
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.impl.ExtendedAttributeImpl
import de.thm.icampus.ejsl.generator.pi.util.PlattformIUtil
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import java.util.LinkedList
import java.util.List
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedReference

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
        var int count = 0
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
			if(!visited.contains(r.extendedEntity.name)){
			return false
			
			}
		}
		return true
	}
	
	
    
    def CharSequence generateSQLTable(ExtendedEntity table, boolean isupdate, String componentName)'''
    «IF !isupdate»
    DROP TABLE IF EXISTS `#__«componentName.toLowerCase»_«table.name.toLowerCase»`;
    «ENDIF»

   CREATE TABLE «IF isupdate» IF NOT EXISTS «ENDIF»`#__«componentName.toLowerCase»_«table.name.toLowerCase»` (
    
	«FOR a:table.allattribute»
		`«a.name.toLowerCase»` «a.generatorType.toLowerCase»,
	«ENDFOR»
«FOR ref:table.references»
CONSTRAINT  FOREIGN KEY(«Slug.transformAttributeListInString(ref.attribute,  ', ')») REFERENCES «Slug.slugify(ref.entity.name.toLowerCase)» («Slug.transformAttributeListInString(ref.attributerefereced, ', ')»)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
«ENDFOR»
PRIMARY KEY (`id`)
«FOR a:table.extendedAttributeList»
«IF a.isunique»
,  UNIQUE KEY («a.name»«if(a.withattribute != null)''',«a.withattribute.name»'''»)
«ENDIF» 
«ENDFOR»
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
'''
	
   public def CharSequence sqlAdminSqlUninstallContent(String extensionName) '''
        «/*val entities=component.eAllContents.toIterable.filter(typeof(Entity))*/»
        SET foreign_key_checks = 0;
        
        «FOR e:entities»
        	DROP TABLE IF EXISTS `#__«extensionName.toLowerCase»_«e.name.toLowerCase»`;
        «ENDFOR»
    '''
    
   
	
}