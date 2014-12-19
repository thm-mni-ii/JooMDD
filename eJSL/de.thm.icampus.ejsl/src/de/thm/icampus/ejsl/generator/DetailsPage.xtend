package de.thm.icampus.ejsl.generator

import de.thm.icampus.ejsl.eJSL.DetailsPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.Section

class DetailsPageTemplate extends DynamicPageTemplate {
	
	private DetailsPage dpage
	private Component  com
	private Section sec
	
	new(DetailsPage dp, Component cp, Section section){
		
		dpage = dp
		com = cp
		sec = section
	}
	
}