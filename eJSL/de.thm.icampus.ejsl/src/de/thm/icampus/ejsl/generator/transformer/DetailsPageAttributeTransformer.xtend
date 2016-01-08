package de.thm.icampus.ejsl.generator.transformer

import de.thm.icampus.ejsl.eJSL.Type
import de.thm.icampus.ejsl.eJSL.DetailPageField
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.SimpleHTMLTypes
import de.thm.icampus.ejsl.eJSL.ComplexHTMLTypes

class DetailsPageAttributeTransformer extends AttributeTransformer {
	
	
	DetailPageField  instance
	EntityAttributeTransformer attribute
	String typeHTml
	
	
	new(DetailPageField  field){
		instance = field
		attribute = transformAttribute(instance.attribute)
		typeHTml = parsetypeHTmlType()
		
	}
	
	def String parsetypeHTmlType() {
		
		if(instance.htmltype != null){
			switch instance.htmltype{
				SimpleHTMLTypes:{
					var SimpleHTMLTypes type = instance.htmltype as SimpleHTMLTypes
					return type.htmltype.getName
				}
				ComplexHTMLTypes:{
					var ComplexHTMLTypes  type = instance.htmltype as ComplexHTMLTypes
					return type.htmltype.getName
				}
			}
		}
	}
	
	def DetailPageField getInstance(){
		return this.instance
	}
	
	def EntityAttributeTransformer getAttribute (){
		 return attribute
	}
	def String getTypeHtml(){
		return typeHTml
	}
	
}