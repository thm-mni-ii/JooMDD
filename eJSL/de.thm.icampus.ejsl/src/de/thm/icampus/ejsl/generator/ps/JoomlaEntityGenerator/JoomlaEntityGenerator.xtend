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
	
//	def dogenerate(String path, IFileSystemAccess fileGen) {
//		fileGen.generateFile(path + "/install.sql",sqlAdminSqlInstallContent(extendsionN,update))
//		fileGen.generateFile(path+ "/uninstal.sql",sqlAdminSqlUninstallContent(extendsionN))
//	}
	
	 public def CharSequence sqlAdminSqlInstallContent(String extensionName, boolean isupdate) {
        
        var HashSet<ExtendedEntity> visited = new HashSet<ExtendedEntity>();
        var StringBuffer result = new StringBuffer;
        while(visited.size != entities.size){
	        for (ExtendedEntity e:entities){
	        	if(e.references.empty && !visited.contains(e)){
	        		result.append(generateSQLTable(e, isupdate, extensionName));
	        		visited.addAll(e);
	        	}
	        	else if(!visited.contains(e) && !e.references.empty && isAllreferenVisited(e.references, visited) ){
	        
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
    
	«FOR a:table.allattribute»
		`«a.name.toLowerCase»` «a.generatorType.toLowerCase»,
	«ENDFOR»
«FOR ref:table.references»
CONSTRAINT  FOREIGN KEY(«Slug.transformAttributeListInString(ref.attribute,  ', ')») REFERENCES «Slug.slugify(ref.entity.name.toLowerCase)» («Slug.transformAttributeListInString(ref.attributerefereced, ', ')»)
    ON UPDATE CASCADE
    ON DELETE CASCADE
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
    
    public def void completeEntity(){
    	
    	for(ExtendedEntity ent: entities){
    		completeAttributeOfEntity(ent)
    	}
    	for(ExtendedEntity ent: entities){
    		completeReferenceOfEntity(ent)
    	}
    	
    }
	
	def completeReferenceOfEntity(ExtendedEntity ent) {
		if(!ent.haveIdAttribute()){
			var Attribute id = EJSLFactory.eINSTANCE.createAttribute
			id.name = "id"
			var StandardTypes typeid = EJSLFactory.eINSTANCE.createStandardTypes
			typeid.type =  StandardTypeKinds.INTEGER
			typeid.notnull = true
			typeid.autoincrement = true
			id.type = typeid
			ent.putNewAttributeInEntity(id)
			
		}
		var Attribute asset_id = EJSLFactory.eINSTANCE.createAttribute
			asset_id.name = "asset_id"
			var StandardTypes type_asset_id = EJSLFactory.eINSTANCE.createStandardTypes
			type_asset_id.type =  StandardTypeKinds.INTEGER
			type_asset_id.notnull = true
			type_asset_id.^default = "0"
			asset_id.type = type_asset_id
			ent.putNewAttributeInEntity(asset_id)
						
		var Attribute state = EJSLFactory.eINSTANCE.createAttribute
			state.name = "state"
			var StandardTypes type_state = EJSLFactory.eINSTANCE.createStandardTypes
			type_state.type =  StandardTypeKinds.BOOLEAN
			type_state.notnull = true
			state.type = type_state
       ent.putNewAttributeInEntity(state)	
				
		var Attribute ordering = EJSLFactory.eINSTANCE.createAttribute
			ordering.name = "ordering"
			var StandardTypes type_ordering = EJSLFactory.eINSTANCE.createStandardTypes
			type_ordering.type =  StandardTypeKinds.INTEGER
			type_ordering.notnull = true
			ordering.type = type_ordering
		ent.putNewAttributeInEntity(ordering)
			
		var Attribute checked_out_time = EJSLFactory.eINSTANCE.createAttribute
			checked_out_time.name = "checked_out_time"
			var StandardTypes type_checked_out_time= EJSLFactory.eINSTANCE.createStandardTypes
			type_checked_out_time.type =  StandardTypeKinds.DATETIME
			type_checked_out_time.notnull = true
			type_checked_out_time.^default = "0000-00-00 00:00:00"
			checked_out_time.type = type_checked_out_time
		ent.putNewAttributeInEntity(checked_out_time)
			
		var Attribute checked_out = EJSLFactory.eINSTANCE.createAttribute
			checked_out.name = "checked_out"
			var StandardTypes type_checked_out = EJSLFactory.eINSTANCE.createStandardTypes
			type_checked_out.type =  StandardTypeKinds.INTEGER
			type_checked_out.notnull = true
			checked_out.type = type_checked_out
		ent.putNewAttributeInEntity(checked_out)
			
		var Attribute created_by = EJSLFactory.eINSTANCE.createAttribute
			created_by.name = "checked_out_time"
			var StandardTypes type_created_by = EJSLFactory.eINSTANCE.createStandardTypes
			type_created_by.type =  StandardTypeKinds.INTEGER
			type_created_by.notnull = true
			created_by.type = type_created_by
		ent.putNewAttributeInEntity(created_by)
		
		var Attribute published = EJSLFactory.eINSTANCE.createAttribute
			published.name = "published"
			var StandardTypes type_published = EJSLFactory.eINSTANCE.createStandardTypes
			type_published.type =  StandardTypeKinds.BOOLEAN
			published.type = type_published
		ent.putNewAttributeInEntity(published)
		
		var Attribute params = EJSLFactory.eINSTANCE.createAttribute
			params.name = "params"
			var StandardTypes type_params = EJSLFactory.eINSTANCE.createStandardTypes
			type_params.type =  StandardTypeKinds.TEXTAREA
			params.type = type_params
		ent.putNewAttributeInEntity(params)
		
		for(ExtendedAttribute attr: ent.allattribute){
			 if(attr.id){
			 	attr.withattribute = ent.searchIdAttribute
			 }
		}
	}
	
	def void  completeAttributeOfEntity(ExtendedEntity ent) {
		
		for(Reference ref : ent.references){
			if(ref.id){
				if(ent.searchIdAttribute != null){
					var Attribute id = EJSLFactory.eINSTANCE.createAttribute
					id.name = "id"
					var StandardTypes typeid = EJSLFactory.eINSTANCE.createStandardTypes
					typeid.type =  StandardTypeKinds.INTEGER
					typeid.notnull = true
					typeid.autoincrement = true
					id.type = typeid
					ent.putNewAttributeInEntity(id)
				}
				ref.attributerefereced.add(ent.searchIdAttribute)
			}
		}
	}
	
}