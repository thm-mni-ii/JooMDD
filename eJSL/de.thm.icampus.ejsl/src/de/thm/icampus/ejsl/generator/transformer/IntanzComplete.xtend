package de.thm.icampus.ejsl.generator.transformer

import de.thm.icampus.ejsl.eJSL.EJSLModel
import java.util.LinkedList
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.DynamicPage

class IntanzComplete {
	EJSLModel instance
	LinkedList<EntityTransformer> entities
	LinkedList<DynamicPageTransformer> dynamicPages
	
	
	new (EJSLModel modelInstance){
		instance = modelInstance
		initEntityAndPage()
	}
	
	def initEntityAndPage(){
		solveEntityExtend()
		for(Entity e : instance.entities)
		    entities.add(new EntityTransformer(e))
		
		for(DynamicPage d: instance.pages.filter(typeof(DynamicPage)))
		     dynamicPages.add(new DynamicPageTransformer(d))
	}
	
	
	def solveEntityExtend(){
		var  LinkedList<Entity> tovisited = new LinkedList<Entity>()
		tovisited.addAll(instance.entities);
		for(Entity e: tovisited){
			if(e.supertype != null){
			 if(e.supertype.supertype != null){
			 	tovisited.remove(e);
			 	tovisited.addLast(e);
			 }else{
			 	var Entity parent = e.supertype
			 	e.attributes.addAll(parent.attributes)
			 	e.supertype = null
			 }
			}
		}
		
	}
	
	
}