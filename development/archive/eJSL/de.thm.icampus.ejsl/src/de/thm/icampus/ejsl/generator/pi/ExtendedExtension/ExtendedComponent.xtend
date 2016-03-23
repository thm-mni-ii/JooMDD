package de.thm.icampus.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.ejsl.eJSL.Component
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedPage

interface ExtendedComponent extends Component {
	
	def EList<ExtendedPageReference> getFrontEndExtendedPagerefence()
	def EList<ExtendedPageReference> getBackEndExtendedPagerefence()
	def EList<ExtendedEntity> getAllExtendedEntity()
	def EList<ExtendedParameterGroup>getExtendedParameterGroupList()
	def EList<ExtendedPage>getAllExtendedPage()
	def Component getInstance()
	
	
}
