package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import java.util.LinkedList
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.eJSL.Reference

class JoomlaEntityGenerator {
	EList<ExtendedEntity> entities
	String extendsionN
	boolean update
	new(EList<ExtendedEntity> entityList, String extendsionName, boolean updateScript){
		entities = orderEntity(entityList)
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
		if(update){
		fileGen.generateFile(path + "/update.sql",generateUpdateScript(extendsionN))
		fileGen.generateFile(path + "/install.sql",sqlAdminSqlInstallContent(extendsionN,update))
		}
		
		else{
		fileGen.generateFile(path + "/install.sql",sqlAdminSqlInstallContent(extendsionN,update))
		}
		
		fileGen.generateFile(path+ "/uninstall.sql",sqlAdminSqlUninstallContent(extendsionN))
	}
	
	 public def CharSequence sqlAdminSqlInstallContent(String extensionName, boolean isupdate) {
        
        var StringBuffer result = new StringBuffer;
           for(ExtendedEntity e : entities)
	        result.append(generateSQLTable(e, isupdate, extensionName));
	        
	        
         return result.toString
     
   }
	
	def boolean isAllreferenVisited(EList<ExtendedReference> list, List<String> visited, EList<ExtendedEntity> entityLsit) {
		
		for(ExtendedReference r: list.filter[t | t != null && t.upper.equalsIgnoreCase("1")]){
			if(!visited.contains(r.extendedToEntity.name)){
			  if((entityLsit.filter[t | t.name.equalsIgnoreCase(r.extendedToEntity.name)]).size > 0)
			return false
			
			}
		}
		return true
	}
	
	
    
    def CharSequence generateSQLTable(ExtendedEntity table, boolean isupdate, String componentName)'''
    «IF isupdate»
    DROP TABLE IF EXISTS `«Slug.databaseName(componentName, table.name)»`;
    «ENDIF»

   CREATE TABLE  IF NOT EXISTS `«Slug.databaseName( componentName, table.name.toLowerCase)»` (
	«FOR a:table.allattribute»
		`«a.name.toLowerCase»` «a.generatorType.toLowerCase»,
	«ENDFOR»
	
	«FOR ExtendedAttribute a:table.extendedAttributeList»
	«IF a.isunique»
	UNIQUE KEY («a.name»«if(a.withattribute != null)''',«a.withattribute.name»'''»),
	«ENDIF» 
	«ENDFOR»
	«FOR ref:table.references»
	INDEX(«Slug.transformAttributeListInString(ref.attribute,  ', ')»),
	«ENDFOR»
	«FOR ref:table.references.filter[t | t.upper.equals("1")]»
	CONSTRAINT `«componentName.toLowerCase»_«table.name.toLowerCase»_ibfk_«table.references.indexOf(ref)»` FOREIGN KEY(«Slug.transformAttributeListInString(ref.attribute,  ',')») REFERENCES `«Slug.databaseName(componentName, Slug.slugify(ref.entity.name.toLowerCase))»` («Slug.transformAttributeListInString(ref.attributerefereced, ', ')»)
	    ON UPDATE CASCADE
	    ON DELETE CASCADE,
	«ENDFOR»
    PRIMARY KEY (`«table.primaryKey.name»`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
'''
	
   public def CharSequence sqlAdminSqlUninstallContent(String extensionName) {
        
        var LinkedList<ExtendedEntity> visited = new LinkedList<ExtendedEntity>;
        visited.addAll(entities)
        var StringBuffer result = new StringBuffer;
        result.append("SET foreign_key_checks = 0;")
         result.append("\n\r")
        
	       while(!visited.empty){
	       	 result.append('''DROP TABLE IF EXISTS `«Slug.databaseName(extensionName.toLowerCase, (visited.removeLast.name))»`;''');
	         result.append("\n\r")
	       }
         return result.toString
    }
    private def EList<ExtendedEntity> orderEntity(EList<ExtendedEntity> entitiesList){
    	var EList<ExtendedEntity> result = new BasicEList<ExtendedEntity>()
    	 var LinkedList<String> visited = new LinkedList<String>();

    	 while(visited.size < entitiesList.size ){
	        for (ExtendedEntity e:entitiesList){
	        	if(e.extendedReference.empty && !visited.contains(e.name)){	        		
	        		visited.add(e.name);
	        		result.add(e)
	        	}
	         else if(!visited.contains(e.name) && !e.references.empty && isAllreferenVisited(e.extendedReference, visited, entitiesList) ){
	        	   visited.add(e.name);
	        	   result.add(e)
	        	   
	        	}
	        }
	        
	       }
	       return result
    }
    public def CharSequence generateUpdateScript(String extensionName)'''
    «FOR ExtendedEntity en: entities.filter[t | !t.preserve] »
       
    ALTER TABLE `«Slug.databaseName(extensionName.toLowerCase, en.name)»`  
    «FOR ExtendedAttribute attr: en.refactoryAttribute»
    «IF attr.name != en.refactoryAttribute.getMylastAttribute.name»
    ADD COLUMN `«attr.name»`  «attr.generatorType»
    «var ExtendedAttribute after = getAfterAttribute(attr,en)»
    «IF after != null»
     AFTER «after.name»,
     «ELSE»
     ,
     «ENDIF»
    «ELSE»
     ADD COLUMN `«attr.name»`  «attr.generatorType»
      «var ExtendedAttribute after = getAfterAttribute(attr,en)»
     «IF after != null»
     AFTER «after.name»
      «ENDIF»
    «ENDIF»
    «ENDFOR»
    ;
    
     ALTER TABLE `«Slug.databaseName(extensionName.toLowerCase, en.name)»`  
     «FOR ExtendedAttribute attr: en.refactoryAttribute.filter[t | t.isunique && !t.preserve]»
      «IF attr.name != (en.refactoryAttribute.filter[t | t.isunique && !t.preserve]).getMylastAttribute.name»
     ADD INDEX KEY (`«attr.name»` «if(attr.withattribute != null)''',`«attr.withattribute.name»`'''»),
      «ELSE»
       ADD INDEX KEY (`«attr.name»` «if(attr.withattribute != null)''',`«attr.withattribute.name»` '''»)
      «ENDIF»
     «ENDFOR»
     ;
     ALTER TABLE `«Slug.databaseName(extensionName.toLowerCase, en.name)»`  
      «FOR Reference ref: en.refactoryReference.filter[t | !t.preserve && t.upper.equalsIgnoreCase("1")]»
       «IF ref !=  (en.refactoryReference.filter[t | !t.preserve]).getMylastReference»
      ADD CONSTRAINT `«extensionName.toLowerCase»_«en.name.toLowerCase»_ibfk_«en.refactoryReference.indexOf(ref)»` FOREIGN KEY(«Slug.transformAttributeListInString(ref.attribute,  ',')») REFERENCES `«Slug.databaseName(extensionName, Slug.slugify(ref.entity.name.toLowerCase))»` («Slug.transformAttributeListInString(ref.attributerefereced, ', ')»)
      	   	    ON UPDATE CASCADE
      	   	    ON DELETE CASCADE,
	   «ELSE»
	   ADD CONSTRAINT `«extensionName.toLowerCase»_«en.name.toLowerCase»_ibfk_«en.refactoryReference.indexOf(ref)»` FOREIGN KEY(«Slug.transformAttributeListInString(ref.attribute,  ',')») REFERENCES `«Slug.databaseName(extensionName, Slug.slugify(ref.entity.name.toLowerCase))»` («Slug.transformAttributeListInString(ref.attributerefereced, ', ')»)
	   	    ON UPDATE CASCADE
	   	    ON DELETE CASCADE
	   «ENDIF»
	  «ENDFOR»
      ;
     
    «ENDFOR»
    '''
	
	def ExtendedAttribute getMylastAttribute(Iterable<ExtendedAttribute> list){
		
		return list.get(list.size-1)
		
	}
	def ExtendedReference getMylastReference(Iterable<ExtendedReference> list){
		return list.get(list.size-1)
		
	}
	
	def getAfterAttribute(ExtendedAttribute attribute, ExtendedEntity ent) {
		if(ent.extendedAttributeList.get(0). name.equalsIgnoreCase(attribute.name)){
			return null
		}
		for(ExtendedAttribute attr: ent.extendedAttributeList){
			if(attr.name.equalsIgnoreCase(attribute.name)){
				var int index = ent.extendedAttributeList.indexOf(attr)
				return ent.extendedAttributeList.get(index-1)
			}
		}
	}
   
	
}