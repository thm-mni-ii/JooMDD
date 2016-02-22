package de.thm.icampus.ejsl.generator.pi.ExtendedEntity.impl

import de.thm.icampus.ejsl.eJSL.impl.AttributeImpl
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.Reference
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.DatatypeReference
import de.thm.icampus.ejsl.eJSL.StandardTypes
import de.thm.icampus.ejsl.eJSL.Type
import de.thm.icampus.ejsl.generator.pi.util.PlattformIUtil
import de.thm.icampus.ejsl.eJSL.Extension

class ExtendedAttributeImpl extends AttributeImpl implements ExtendedAttribute {

	Entity entity
	String genType
	Attribute instance

	new(Attribute attr) {

		this.type = attr.type
		this.name = PlattformIUtil.slugify(attr.name)
		this.isunique = attr.isIsunique
		this.withattribute = attr.withattribute
		entity = attr.eContainer as Entity
		genType = generatorType()
		instance = attr

	}

	override generatorType() {
		switch type {
			DatatypeReference: {
				var DatatypeReference temptyp = type as DatatypeReference
				return temptyp.type.name
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
			case "Textarea": {
				value = "text "
			}
			case "Textfield": {
				value = "varchar(255) "
			}
			case "Time": {
				value = "time "
			}
			case "Date": {
				value = "date "
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
		}
		var String result = value
		if (eJSlType.notnull)
			result = result + "NOT NULL "
		if (eJSlType.^default!= null)
			result = result + "DEFAULT " + '''"«eJSlType.^default.toString»"'''
		if (eJSlType.autoincrement)
			result = result + " AUTO_INCREMENT "

		return result
	}

	

	override getEntity() {
		return entity
	}

	override getInstance() {
		return instance
	}


}
