/**
 */
package de.thm.icampus.ejsl.generator;

import org.eclipse.emf.ecore.EObject;
import de.thm.icampus.ejsl.eJSL.Type
import de.thm.icampus.ejsl.eJSL.DatatypeReference
import de.thm.icampus.ejsl.eJSL.SimpleDatatypes

abstract public class AbstractPageGenerator {
	
	public def LinkGeneratorClient getLinkClient();

	 def void setLinkClient(LinkGeneratorClient value);
	 
	 public def CharSequence generatePage();
	 
	 def String getTypeName(Type typ){
		var String result = "";
		switch typ{
			DatatypeReference :{
				var DatatypeReference temptyp = typ as DatatypeReference
				result = temptyp.type.name
			}
			SimpleDatatypes:{
				var SimpleDatatypes temptyp = typ as SimpleDatatypes
				result = temptyp.type.toString
			}
		}
		return result
	}

} // AbstractPageGenerator
