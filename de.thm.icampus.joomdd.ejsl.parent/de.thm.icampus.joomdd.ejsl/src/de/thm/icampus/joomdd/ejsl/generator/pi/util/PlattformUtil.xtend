package de.thm.icampus.joomdd.ejsl.generator.pi.util

import de.thm.icampus.joomdd.ejsl.eJSL.Type
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedAttributeImpl
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import java.util.LinkedList

class PlattformUtil {
	
	
	
	def static String slugify(String str) {
		var res = str
		res = res.replaceAll("[^\\pL\\d]+", "_")
		res = res.replaceAll("ä", "ae")
		res = res.replaceAll("ö", "ou")
		res = res.replaceAll("ü", "ue")
		res = res.replaceAll("ß", "ss")
		res = res.replaceAll("[^-\\w]+", '')
		res = res.replaceAll("_1","")
		res = res.replaceAll("_unexc_","")
		
		trim(res, "_".charAt(0))
	}
	
	def static String trim(String str, char c) {
		var a = 0
		var z = str.length
		
		while (a < z && str.charAt(a) == c) {
			a = a + 1
		}
		
		do {
			z = z - 1
		} while (z > a && str.charAt(z) == c); 
		
		str.substring(a, z+1)
	}
	
	static def EList<ExtendedEntity> getAllReferenceOfEntity(ExtendedEntity entity){
        val LinkedList<Entity> visited = new LinkedList<Entity> ();
		visited.add(entity.instance)
		var LinkedList<Entity> tosearch = new LinkedList<Entity> ();
		
		for(Reference ref: entity.references){
            if(!visited.contains(ref.entity) && !tosearch.contains(ref.entity)){
			    tosearch.add(ref.entity)
			}
		}
		
		while(tosearch.size != 0){
			var LinkedList<Entity> childs = new LinkedList<Entity>();
			var LinkedList<Entity> toremove = new LinkedList<Entity>();
			
			for(Entity ent : tosearch){
				if(!visited.contains(ent)){//22
					for(Reference ref: ent.references){
						if(!visited.contains(ref.entity) ){
						    if(!tosearch.contains(ref.entity)){
						        childs.add(ref.entity) 
						    }
						}
					}
					
					visited.add(ent);
					toremove.add(ent)
				}				
				else{
					toremove.add(ent)
				}
			}
			
			tosearch.removeAll(toremove)
			tosearch.addAll(childs.filter[t | !visited.contains(t)])
		}
		
		visited.removeFirst
		var EList<ExtendedEntity> allEntityFromReference= new BasicEList<ExtendedEntity>()
		for(Entity ent : visited){
		    allEntityFromReference.add(new ExtendedEntityImpl(ent))
		}
		
		return allEntityFromReference
	}
}