/**
 */
package de.thm.icampus.ejsl.generator.pages

import de.thm.icampus.ejsl.eJSL.InternalLink
import de.thm.icampus.ejsl.generator.util.Slug

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
	
	override generateLink(String sect, String compname) '''
		 «if (sect.isEmpty) '' else sect + "/"»"index.php?option=«compname.toLowerCase»&view=«link.target.name.toLowerCase»"'''
	
} // InternalLink
