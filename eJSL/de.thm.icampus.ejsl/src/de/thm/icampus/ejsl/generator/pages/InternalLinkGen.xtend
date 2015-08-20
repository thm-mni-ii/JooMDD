/**
 */
package de.thm.icampus.ejsl.generator.pages

import de.thm.icampus.ejsl.eJSL.InternalLink

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Internal Link</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getInternalLink()
 * @model
 * @generated
 */
public class InternalLinkGen extends AbstractLinkGenerator {
	
	InternalLink link
	
	new(InternalLink link) {
		this.link = link
	}
	
	override generateLink(String sect, String compname) {
				
	}
	
} // InternalLink
