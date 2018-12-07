package de.thm.icampus.joomdd.ejsl.generator.pi.util.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.ParameterImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.eJSL.Parameter
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypes
import de.thm.icampus.joomdd.ejsl.eJSL.ComplexHTMLTypes
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypeKinds
import de.thm.icampus.joomdd.ejsl.eJSL.ComplexHTMLTypeKinds
import de.thm.icampus.joomdd.ejsl.eJSL.Datatype

class ExtendedParameterImpl extends ParameterImpl implements ExtendedParameter {
	
	Parameter instance 
	String type
	new(Parameter para){
		this.name = PlattformUtil.slugify(para.name)
		this.defaultvalue = para.defaultvalue
		this.label = para.label
		this.descripton = para.descripton
		this.dtype = para.dtype
		this.attributes = para.attributes
		this.values = para.values
		type = parseType()
		instance = para
	}
	
	def parseType() {
		switch this.dtype{
			SimpleHTMLTypes:{
					var SimpleHTMLTypeKinds type = this.dtype.htmltype as SimpleHTMLTypeKinds
					return type.getName()
				}
				ComplexHTMLTypes:{
					var ComplexHTMLTypeKinds  type = this.dtype.htmltype as ComplexHTMLTypeKinds
					return type.getName()
				}
				DatatypeReference:{
					var Datatype type = this.dtype.type as Datatype
					return type.type
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