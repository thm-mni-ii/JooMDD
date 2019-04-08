package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension

import de.thm.icampus.joomdd.ejsl.eJSL.Component
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import java.util.ArrayList
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.StaticLanguageValue
import de.thm.icampus.joomdd.ejsl.eJSL.Section

interface ExtendedComponent extends Component {
	
	def EList<ExtendedPageReference> getFrontEndExtendedPagerefence()
	def EList<ExtendedPageReference> getBackEndExtendedPagerefence()
	def EList<ExtendedEntity> getAllExtendedEntity()
	def EList<ExtendedParameterGroup>getExtendedParameterGroupList()
	def EList<ExtendedPage>getAllExtendedPage()
	def Component getInstance()
	def String extensionName()
	def boolean hasFileToload()
	
	/**
	 * Get the backend section of this component.
	 * 
	 * @return Section
	 */
	def Section getBackendSection()
	
	/**
     * Get the frontend section of this component.
     * 
     * @return Section
     */
	def Section getFrontendSection()
	
	def String getComponentHelperClassName()
}
