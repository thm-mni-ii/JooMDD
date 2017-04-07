package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage

import de.thm.icampus.joomdd.ejsl.eJSL.DetailPageField
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair
import org.eclipse.emf.common.util.EList

interface ExtendedDetailPageField extends DetailPageField {
	
	def String getType()
	def ExtendedAttribute getExtendedAttribute()
	def DetailPageField getinstance()
	
}