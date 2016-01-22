package de.thm.icampus.ejsl.generator.pi.util.impl

import de.thm.icampus.ejsl.eJSL.impl.ParameterImpl
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.ejsl.eJSL.Parameter
import de.thm.icampus.ejsl.generator.pi.util.PlattformIUtil
import de.thm.icampus.ejsl.eJSL.DatatypeReference
import de.thm.icampus.ejsl.eJSL.StandardTypes

class ExtendedParameterImpl extends ParameterImpl implements ExtendedParameter {
	
	Parameter instance 
	String type
	new(Parameter para){
		this.name = PlattformIUtil.slugify(para.name)
		this.defaultvalue = para.defaultvalue
		this.label = para.label
		this.descripton = para.descripton
		this.dtype = para.dtype
		type = parseType()
		instance = para
	}
	
	def parseType() {
		switch this.dtype{
			DatatypeReference :{
				var DatatypeReference temptyp = this.dtype as DatatypeReference
				return temptyp.type.name
			}
			StandardTypes:{
				var StandardTypes temptyp = this.dtype as StandardTypes
				return parsingType(temptyp)
			}
		}
	}
	
	def String parsingType(StandardTypes types) {
		
		    var String value = "";
		switch (types.type.getName()){
			case "Integer" :{
				value = "Integer"
			}
			case "Boolean" :{
			value = "Yes_No_Buttons"
				
			}
			case "Textarea" :{
				value = "Textarea"
			}
			case "Textfield" :{
				value = "Text_Field"
			} 
			case "Time":{
				value = "Datepicker"
			}
			case "Date":{
				value = "Datepicker"
			}
			case "Datetime" :{
				value = "Datepicker"
				}
			case "Link" :{
				value = "Text_Field"
			}
			case "Image":{
				value = "Imagepicker"
			}
			case "File" :{
				value = "Filepicker"
			}
			case "Label":{
				value = "Text_Field_NE"
			}
			
		}
	}
	
	override generatorType() {
		return type
	}
	
	override getInstance() {
		return instance
	}
	
	
}