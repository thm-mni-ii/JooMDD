package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.ExternalLink

/**
 * This class contains the templates to generate the necessary code for external links.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
public class ExternalLinkGenerator extends AbstractLinkGenerator {
	
	ExternalLink link
    
	new(ExternalLink link) {
	    this.link = link
	}
	
	override generateLink(String sect, String compname) {
	    return link.target.toString
	}
	
}
