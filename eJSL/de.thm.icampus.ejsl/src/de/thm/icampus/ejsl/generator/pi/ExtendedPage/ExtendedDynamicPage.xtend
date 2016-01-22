package de.thm.icampus.ejsl.generator.pi.ExtendedPage

import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameter

interface ExtendedDynamicPage extends DynamicPage {
	
	def EList<ExtendedAttribute> getExtendedTableColumnList()
	def EList<ExtendedAttribute> getExtendFiltersList()
	def EList<ExtendedDetailPageField> getExtendedEditedFieldsList()
	def EList<ExtendedEntity> getExtendedEntityList()
	def EList<ExtendedParameterGroup> getExtendedParametersGroupsListe()
	def EList<ExtendedParameter> getExtendedGlobalParametersListe()
	def EList<ExtendedParameter> getExtendedLocalParametersListe()
	
	
	def Boolean isDetailsPage()
	def DynamicPage getInstance()
	
	
}