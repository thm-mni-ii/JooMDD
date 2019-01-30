package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.AttributeImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil

class ExtendedAttributeImpl extends AttributeImpl implements ExtendedAttribute {

	Entity entity
	String genType
	Attribute instance
	String htmlType
	boolean isReferenced = false

	new(Attribute attr) {
		attr.name = PlattformUtil.slugify(attr.name).toLowerCase
		this.type = attr.type
		this.name = PlattformUtil.slugify(attr.name).toLowerCase
		this.isunique = attr.isIsunique
		this.withattribute = attr.withattribute
		entity = attr.eContainer as Entity
		genType = generatorType()
		htmlType = generatorTypeHtmlType()
		this.preserve = attr.preserve
		this.isprimary = attr.isprimary
		instance = attr
		initAttributeProperties

	}
	
	new(Attribute attr, Entity ent) {
		instance = attr
		this.type = attr.type
		this.name = PlattformUtil.slugify(attr.name).toLowerCase
		this.isunique = attr.isIsunique
		this.withattribute = attr.withattribute
		entity = searchEntity(ent)
		genType = generatorType()
		htmlType = generatorTypeHtmlType()
		this.preserve = attr.preserve
		this.isprimary = attr.isprimary
		initAttributeProperties
	}
	
	def Entity searchEntity(Entity entity) {
		var Entity ent = instance.eContainer as Entity
		
		if(ent == null){
			var Reference ref = (entity.references.filter[t | t.entity.attributes.contains(instance)]).get(0)
			return ref.entity
		}
		
		if(ent.name != entity.name)
		return ent
		
		return entity
	}
	
	def initAttributeProperties() {
		
			for(Reference ref: entity.references.filter[t | !t.upper.equalsIgnoreCase("1")]){
				for(Attribute refAttr: ref.attribute)
				if(this.name.equalsIgnoreCase(refAttr.name))
				    this.isReferenced = true
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
			case "Label": {
				value = "varchar(255) "
			}
			default:{
				value = "int(11)"
			}
		}
		var String result = value
		if (eJSlType.notnull || isprimary)
			result = result + "NOT NULL "
		if (eJSlType.^default!= null)
			result = result + "DEFAULT " + '''"«eJSlType.^default.toString()»"'''
		if (eJSlType.autoincrement || isprimary)
			result = result + " AUTO_INCREMENT "

		return result
	}

	

	override getEntity() {
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

}
