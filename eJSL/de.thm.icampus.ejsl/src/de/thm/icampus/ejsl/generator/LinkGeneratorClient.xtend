/**
 */
package de.thm.icampus.ejsl.generator

import org.eclipse.emf.ecore.EObject;


public class LinkGeneratorClient  {
	
	AbstractLinkGenerator link
	
	public def AbstractLinkGenerator getLink(){
		return this.link
	}

	
	public def void setLink(AbstractLinkGenerator value){
		this.link = value;
	}
	
	public def CharSequence generateLink(){
		return this.link.generateLink
	}

} // LinkGeneratorClient
