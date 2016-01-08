package de.thm.icampus.ejsl.generator.transformer

import de.thm.icampus.ejsl.eJSL.Type
import de.thm.icampus.ejsl.eJSL.DatatypeReference
import de.thm.icampus.ejsl.eJSL.StandardTypes
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.Reference

abstract class  AttributeTransformer  {
	
	def parseTypeSQlName(Type eJSlType){
		switch eJSlType{
			DatatypeReference :{
				var DatatypeReference temptyp = eJSlType as DatatypeReference
				return temptyp.type.name
			}
			StandardTypes:{
				var StandardTypes temptyp = eJSlType as StandardTypes
				return parsingToSQLType(temptyp)
			}
		}
		
	}
	def parseTypeHtmlName(Type eJSlType){
		switch eJSlType{
			DatatypeReference :{
				var DatatypeReference temptyp = eJSlType as DatatypeReference
				return temptyp.type.name
			}
			StandardTypes:{
				var StandardTypes temptyp = eJSlType as StandardTypes
				return parsingToHtmlType(temptyp)
			}
		}
		
	}
	
	
	def String parsingToSQLType(StandardTypes eJSlType) {
		
		    var String value = "";
		switch (eJSlType.type.getName()){
			case "Integer" :{
				value = "int(11) "
			}
			case "Boolean" :{
				value = "tinyint(1) "
			}
			case "Textarea" :{
				value = "text "
			}
			case "Textfield" :{
				value = "varchar(255) "
			} 
			case "Time":{
				value = "time "
			}
			case "Date":{
				value = "date "
			}
			case "Datetime" :{
				value = "datetime "
			}
			case "Link" :{
				value = "text "
			}
			case "Image":{
				value = "text "
			}
			case "File" :{
				value = "text "
			}
			case "Label":{
				value = "varchar(255) "
			}
			
		}
		var String result = value
		if(eJSlType.notnull)
		    result = result +  "NOT NULL "
		if(!eJSlType.^default.toString.empty)
		  result = result + "DEFAULT " + eJSlType.^default.toString
		if(eJSlType.autoincrement)
		   result = result + "AUTO_INCREMENT "
		   
		return result
	}
	def String parsingToHtmlType(StandardTypes eJSlType) {
		
		    var String value = "";
		switch (eJSlType.type.getName()){
			case "Integer" :{
				value = "text"
			}
			case "Boolean" :{
				value = "text"
			}
			case "Textarea" :{
				value = "Textarea"
			}
			case "Textfield" :{
				value = "text"
			} 
			case "Time":{
				value = "datetime"
			}
			case "Date":{
				value = "date "
			}
			case "Datetime" :{
				value = "datetime "
			}
			case "Link" :{
				value = "text"
			}
			case "Image":{
				value = "image"
			}
			case "File" :{
				value = "file"
			}
			case "Label":{
				value = "label"
			}
			
		}
		
		return value
	}
	
	static def EntityAttributeTransformer transformAttribute(Attribute ejslAttribute){
		var Entity entity = ejslAttribute.eContainer as Entity
		var Reference ref = null
		for(Reference e: entity.references){
			if(e.attribute.name == ejslAttribute.name )
				ref = e
		}
		
		return new EntityAttributeTransformer (ejslAttribute, ref)
	}
}