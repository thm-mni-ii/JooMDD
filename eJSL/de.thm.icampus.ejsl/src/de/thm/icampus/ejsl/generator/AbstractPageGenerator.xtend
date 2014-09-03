/**
 */
package de.thm.icampus.ejsl.generator;

import org.eclipse.emf.ecore.EObject;

abstract public class AbstractPageGenerator {
	
	public def LinkGeneratorClient getLinkClient();

	 def void setLinkClient(LinkGeneratorClient value);
	 
	 public def CharSequence generatePage();

} // AbstractPageGenerator
