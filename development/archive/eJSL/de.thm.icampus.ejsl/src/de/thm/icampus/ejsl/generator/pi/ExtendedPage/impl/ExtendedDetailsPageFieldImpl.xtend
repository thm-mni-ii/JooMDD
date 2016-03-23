package de.thm.icampus.ejsl.generator.pi.ExtendedPage.impl

import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import de.thm.icampus.ejsl.eJSL.impl.DetailPageFieldImpl
import de.thm.icampus.ejsl.eJSL.DetailPageField
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.ejsl.generator.pi.util.PlattformIUtil
import de.thm.icampus.ejsl.eJSL.SimpleHTMLTypes
import de.thm.icampus.ejsl.eJSL.ComplexHTMLTypes

class ExtendedDetailsPageFieldImpl extends DetailPageFieldImpl implements ExtendedDetailPageField {
	
	String type
	ExtendedAttribute extendedAttribute
	DetailPageField instance
	new(DetailPageField field) {
		instance = field
		this.attribute = field.attribute
		this.htmltype = field.htmltype
		extendedAttribute = PlattformIUtil.transformAttribute(this.attribute)
		type = parseType()
	}
	
	def String parseType() {
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
	
	override getType() {
		return type
	}
	
	
	override getExtendedAttribute() {
		return extendedAttribute
	}
	
	override getExtrasKeyValue() {
		if(this.htmltype instanceof ComplexHTMLTypes){
			var ComplexHTMLTypes cpHtml  = this.htmltype as ComplexHTMLTypes
			return cpHtml.keyvaluepairs
		}
	}
	
	
}