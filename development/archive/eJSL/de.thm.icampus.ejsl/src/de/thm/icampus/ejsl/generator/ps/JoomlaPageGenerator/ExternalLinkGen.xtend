/**
 */
package de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.ejsl.eJSL.ExternalLink

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Link</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getExternalLink()
 * @model
 * @generated
 */
public class ExternalLinkGen extends AbstractLinkGenerator {
	
	ExternalLink link
    
	new(ExternalLink link) {
		this.link = link
	}
	
	override generateLink(String sect, String compname) {
		return link.target.toString
	}
	
} // ExternalLink