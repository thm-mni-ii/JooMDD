package de.thm.icampus.ejsl.generator.pi.ExtendedPage

import de.thm.icampus.ejsl.eJSL.DetailPageField
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute

interface ExtendedDetailPageField extends DetailPageField {
	
	def String getType()
	def ExtendedAttribute getExtendedAttribute()
	
}