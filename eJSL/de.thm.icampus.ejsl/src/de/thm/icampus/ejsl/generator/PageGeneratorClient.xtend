/**
 */
package de.thm.icampus.ejsl.generator

import org.eclipse.emf.ecore.EObject;


public class PageGeneratorClient {
	
	AbstractPageGenerator page
	
	public def AbstractPageGenerator getPage(){
		return this.page
	}


	public def void setPage(AbstractPageGenerator value){
		this.page=value
	}
	
	public def CharSequence generatePage(){
		return this.page.generatePage
	}

} // PageGeneratorClient
