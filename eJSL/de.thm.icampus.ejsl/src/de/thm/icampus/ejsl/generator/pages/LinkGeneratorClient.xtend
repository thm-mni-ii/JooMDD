/**
 */
package de.thm.icampus.ejsl.generator.pages

import org.eclipse.emf.ecore.EObject;
import de.thm.icampus.ejsl.eJSL.Link
import de.thm.icampus.ejsl.eJSL.ExternalLink
import de.thm.icampus.ejsl.eJSL.InternalLink
import de.thm.icampus.ejsl.eJSL.ContextLink

public class LinkGeneratorClient  {
	
	Link link
	String sect = ''
	String compname
	new( Link link, String section, String componentname){
		this.link = link
		if(sect.equalsIgnoreCase('BackendSection'))
		sect = section
		compname = componentname
		
	}
	
	new( Link link) {
		this.link = link
	}
	
	
	
	public def CharSequence generateLink(){
		switch link {
			ExternalLink :{
				var ExternalLinkGen extern = new ExternalLinkGen(link)
				return extern.generateLink(sect,compname)
			}
			
			InternalLink:{
			if(link instanceof ContextLink) {
				var ContextLinkGen intern = new ContextLinkGen(link)
				return intern.generateLink(sect,compname)
			}
			else{
				var InternalLinkGen intern = new InternalLinkGen(link)
				return intern.generateLink(sect,compname)
				}
			}
			
			
		}
		return "//it is empty to do!"
	}
	

} // LinkGeneratorClient
