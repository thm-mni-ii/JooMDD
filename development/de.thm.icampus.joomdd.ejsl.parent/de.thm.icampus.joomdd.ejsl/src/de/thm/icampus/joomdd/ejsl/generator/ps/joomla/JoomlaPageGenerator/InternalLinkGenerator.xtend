package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.InternalLink
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug

/**
 * This class contains the templates to generate the necessary code for internal links.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
public class InternalLinkGenerator extends AbstractLinkGenerator {
	
	InternalLink link
	
	new(InternalLink link) {
		this.link = link
	}
	
	override generateLink(String sect, String compname) '''
	    «if (sect.isEmpty) '' else sect + "/"»"index.php?option=«Slug.nameExtensionBind("com",compname).toLowerCase»&view=«link.target.name.toLowerCase»"
	'''
}
