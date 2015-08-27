/**
 */
package de.thm.icampus.ejsl.generator.pages;

import de.thm.icampus.ejsl.eJSL.ContextLink
import de.thm.icampus.ejsl.eJSL.Link
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.LinkParameter
import org.eclipse.emf.common.util.EList

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context Link</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getContextLink()
 * @model
 * @generated
 */
public class ContextLinkGen extends AbstractLinkGenerator {
	
	
	ContextLink lk
	
	new(ContextLink link) {
		lk = link
	}
	
	override generateLink(String sect, String compname) '''
		 «if (sect.isEmpty) '' else sect +"/"»index.php?option=«compname»&view=«lk.target.name.toLowerCase»«genLinkOption(lk.linkparameters)»'''
	
	def CharSequence genLinkOption(EList<LinkParameter> params)'''
	«FOR LinkParameter lp : params»
	&«lp.name»=«lp.attvalue»«lp.attvalue»
	«ENDFOR»
	'''
	
	
} // ContextLink
