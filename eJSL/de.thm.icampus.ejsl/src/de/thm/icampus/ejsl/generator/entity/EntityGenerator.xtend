package de.thm.icampus.ejsl.generator.entity

import de.thm.icampus.ejsl.generator.util.Slug
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.Reference
import org.eclipse.emf.common.util.EList
import java.util.HashSet
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.Section
import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.IndexPage
import de.thm.icampus.ejsl.eJSL.DetailsPage
import de.thm.icampus.ejsl.eJSL.PageReference

class EntityGenerator {
	
	Component comp
	new(Component component){
		comp = component
	}
		// Alle untergeordneten (über Referenzen) Entities finden
	def Iterable<Entity> getEntities(Component component) {
		var entities = new HashSet<Entity>();
		
		// Section -> Page -> Entities
		for(Section s :component.sections) {
			for(PageReference p : s.pageRef) {
				if(p.page instanceof IndexPage) {
					entities.addAll((p.page as IndexPage).entities)
				} else if(p instanceof DetailsPage) {
					entities.addAll((p.page as DetailsPage).entities)
				}
			}
		}

		return entities;
	}
	
	 def CharSequence sqlAdminSqlInstallContent(Component component, boolean isupdate) {
        val Iterable<Entity> entities=getEntities(component)
        var HashSet<Entity> visited = new HashSet<Entity>();
        var StringBuffer result = new StringBuffer;
        while(visited.size != entities.size){
	        for (Entity e:entities){
	        	if(e.references.empty && !visited.contains(e)){
	        		result.append(generateSQLTable(e, isupdate));
	        		visited.addAll(e);
	        	}
	        	if(!visited.contains(e) && !e.references.empty && isAllreferenVisited(e.references, visited) ){
	        
	        	   result.append(generateSQLTable(e, isupdate))
	        	   visited.addAll(e);
	        	}
	        }
	       }
         return result.toString
     
   }
	
	def boolean isAllreferenVisited(EList<Reference> list, HashSet<Entity> entities) {
		
		for(Reference r: list){
			if(!entities.contains(r.entity))
			return false
		}
		return true
	}
    
    def CharSequence generateSQLTable(Entity table, boolean isupdate)'''
    «IF !isupdate»
    DROP TABLE IF EXISTS `#__«comp.name.toLowerCase»_«table.name.toLowerCase»`;
    «ENDIF»

   CREATE TABLE «IF isupdate» IF NOT EXISTS «ENDIF»`#__«comp.name.toLowerCase»_«table.name.toLowerCase»` (
    `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `asset_id` INT(10) UNSIGNED NOT NULL DEFAULT '0',    
    `ordering` INT(11)  NOT NULL ,
    `state` TINYINT(1)  NOT NULL ,
    `checked_out` INT(11)  NOT NULL,
    `checked_out_time` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	`created_by` INT(11)  NOT NULL ,
	«FOR a:table.attributes»
		`«a.name.toLowerCase»` «Slug.getTypeName(a.dbtype)»,
	«ENDFOR»
	`published` int(11) NOT NULL,
	`params` TEXT,
«FOR r:table.references»
	FOREIGN KEY (`«r.attribute.name.toLowerCase»`) REFERENCES `#__«comp.name.toLowerCase»_«r.entity.name.toLowerCase»` (`«r.attributerefereced.name.toLowerCase»`)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
«ENDFOR»
PRIMARY KEY (`id`)
«FOR a:table.attributes»
«IF a.isprimary»
,  UNIQUE KEY («a.name»)
«ENDIF»
«ENDFOR»
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
'''
	
	def Attribute getPrimaryKeyOfTable(Entity entity) {
		
		for(Attribute e : entity.attributes){
			if(e.isprimary)
			return e
		}
	}
    
   def CharSequence sqlAdminSqlUninstallContent(Component component) '''
        «/*val entities=component.eAllContents.toIterable.filter(typeof(Entity))*/»
        «val entities=getEntities(component)»
        «FOR e:entities»
        	DROP TABLE IF EXISTS `#__«comp.name.toLowerCase»_«e.name.toLowerCase»`;
        «ENDFOR»
    '''
}