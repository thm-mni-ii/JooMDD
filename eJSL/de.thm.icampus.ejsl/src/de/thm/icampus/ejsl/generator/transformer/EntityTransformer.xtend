package de.thm.icampus.ejsl.generator.transformer

import de.thm.icampus.ejsl.eJSL.Entity
import java.util.LinkedList
import de.thm.icampus.ejsl.eJSL.Attribute

class EntityTransformer {
	Entity instance
	LinkedList<EntityAttributeTransformer> attributes 
	
	new (Entity entity){
		instance = entity
		instance.name = TransformerUtil.slugify(instance.name)
		initAttributeList()
		
	}
	
	def initAttributeList(){
		attributes = new LinkedList <EntityAttributeTransformer> ()
		for( Attribute a: instance.attributes){
			attributes.add(AttributeTransformer.transformAttribute(a))
		}
	}
	
	
}