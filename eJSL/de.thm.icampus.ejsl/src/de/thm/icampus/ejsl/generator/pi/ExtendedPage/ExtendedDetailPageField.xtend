package de.thm.icampus.ejsl.generator.pi.ExtendedPage

import de.thm.icampus.ejsl.eJSL.DetailPageField
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.ejsl.eJSL.KeyValuePair
import org.eclipse.emf.common.util.EList

interface ExtendedDetailPageField extends DetailPageField {
	
	def String getType()
	def ExtendedAttribute getExtendedAttribute()
	def EList<KeyValuePair> getExtrasKeyValue()
	
}