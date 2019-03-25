package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.AttributeImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil
import org.eclipse.xtext.EcoreUtil2
import de.thm.icampus.joomdd.ejsl.generator.pi.util.MappingEntity

class ExtendedAttributeImpl extends AttributeImpl implements ExtendedAttribute {
    
	Entity entity
	String genType
	Attribute instance
	String htmlType
	boolean isReferenced = false
	boolean isTheBaseElement = false;

	new(Attribute attribute) {
	    setParentProperties(attribute)
        instance = attribute
	    
		var attrEContainer = attribute.eContainer as Entity
		entity = attrEContainer
		genType = generatorType()
		htmlType = generatorTypeHtmlType()
		initAttributeProperties
	}
	
    override setParentProperties(Attribute attribute) {
        this.name = PlattformUtil.slugify(attribute.name)
        this.type = attribute.type
        this.isunique = attribute.isIsunique
        this.withattribute = attribute.withattribute
        this.preserve = attribute.preserve
        this.isprimary = attribute.isprimary
    }
    
	def initAttributeProperties() {
		for(Attribute att: this.entity.attributes ){
			if(att.withattribute !== null && att.withattribute.name.compareTo(this.name)==0 )
			this.isTheBaseElement = true
		}
		
		for(Reference ref: entity.references.filter[t | !t.upper.equalsIgnoreCase("1")]){
			for(Attribute refAttr: ref.attribute)
			if(this.name.equalsIgnoreCase(refAttr.name)){
				this.isReferenced = true
			}    
		}
	}
	
	def String generatorTypeHtmlType() {
		switch type {
			DatatypeReference: {
				var DatatypeReference temptyp = type as DatatypeReference
				return "text"
			}
			StandardTypes: {
				var StandardTypes temptyp = type as StandardTypes
				return temptyp.type.getName
			}
		}
	}
	
	

	override generatorType() {
		switch type {
			DatatypeReference: {
				var DatatypeReference temptyp = type as DatatypeReference
				return temptyp.type.type
			}
			StandardTypes: {
				var StandardTypes temptyp = type as StandardTypes
				return parsingType(temptyp)
			}
		}

	}

	def String parsingType(StandardTypes eJSlType) {

		var String value = "";
		switch (eJSlType.type.getName()) {
			case "Integer": {
				value = "int(11) "
			}
			case "Boolean": {
				value = "tinyint(1) "
				if (eJSlType.^default == null)
					eJSlType.^default = "0"
			}
			case "Text": {
				value = "text "
			}
			case "Short_Text": {
				value = "varchar(255) "
			}
			case "Time": {
				value = "varchar(255) "
			}
			case "Date": {
				value = "varchar(255) "
			}
			case "Datetime": {
				value = "datetime "
				if (eJSlType.^default == null)
					eJSlType.^default = "0000-00-00 00:00:00"
			}
			case "Link": {
				value = "text "
			}
			case "Image": {
				value = "text "
			}
			case "File": {
				value = "text "
			}
			default:{
				value = "int(11)"
			}
		}
		var String result = value
		
		if (eJSlType.notnull || isprimary)
		{
		    result = result + "NOT NULL "
		}

		if (eJSlType.^default!= null) {
		    result = result + "DEFAULT " + '''"«eJSlType.^default.toString()»"'''
		}
			
		if (eJSlType.autoincrement) {
		    result = result + " AUTO_INCREMENT "
		}	

		return result
	}

	override Entity getEntity() {
		return entity
	}

	override getInstance() {
		return instance
	}
	
	override htmlType() {
		return htmlType
	}
	
	override isReferenced() {
		return isReferenced
	}
	
	override setIsReferenced(boolean value) {
		this.isReferenced = value
	}
	
	override isTheBaseElementOfUniquePair() {
		return isTheBaseElement
	}

    override isAutoIncrement() {
        if (this.type instanceof StandardTypes) {
            return type.autoincrement
        } else {
            return false
        }
    }
}