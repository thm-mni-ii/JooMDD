package de.thm.icampus.ejsl.generator.transformer

import de.thm.icampus.ejsl.eJSL.Type
import de.thm.icampus.ejsl.eJSL.DatatypeReference
import de.thm.icampus.ejsl.eJSL.StandardTypes
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.Reference
import de.thm.icampus.ejsl.eJSL.Entity

class EntityAttributeTransformer extends AttributeTransformer {
	private Attribute instance
	private String typSQLeName
	private String typHtmleName
	private Reference refInstance
	
	new(Attribute attribute, Reference ref){
		instance = attribute
		refInstance = ref
		typSQLeName = TransformerUtil.slugify(parseTypeSQlName(instance.type))
		typHtmleName = TransformerUtil.slugify(parseTypeHtmlName(instance.type))
		instance.name = TransformerUtil.slugify(instance.name)
	}
	
	def Attribute getInstance(){
		return this.instance
	}
	def Entity getEntity(){
		return instance.eContainer as Entity
	}
	def Reference getRefInstance(){
		return refInstance
	}
	def String getTypeSQLName(){
		return typSQLeName
	}
	def String getTypeHtmlName(){
		return typHtmleName
	}
	
	
}