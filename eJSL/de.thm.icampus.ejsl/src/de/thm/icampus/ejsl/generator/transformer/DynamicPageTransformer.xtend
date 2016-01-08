package de.thm.icampus.ejsl.generator.transformer

import de.thm.icampus.ejsl.eJSL.DynamicPage
import java.util.LinkedList
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.DetailsPage

class DynamicPageTransformer {
	
	DynamicPage instance
	LinkedList<EntityAttributeTransformer> table_column
	LinkedList<EntityAttributeTransformer> filters
	LinkedList<DetailsPageAttributeTransformer> edit_attribute
	
	new(DynamicPage page){
		instance = page
		instance.name = TransformerUtil.slugify(instance.name)
		initAttributListe()
	}
	
	def initAttributListe() {
		table_column = new LinkedList<EntityAttributeTransformer>()
	    filters = new LinkedList<EntityAttributeTransformer>()
		edit_attribute = new LinkedList<DetailsPageAttributeTransformer>()
		for(Attribute e: instance.tablecolumns )
		    table_column.add(AttributeTransformer.transformAttribute(e))
		 for(Attribute e: instance.filters )
		    filters.add(AttributeTransformer.transformAttribute(e))
		 switch instance{
		 	DetailsPage :{
		 		for(Attribute e: instance.filters )
		    	filters.add(AttributeTransformer.transformAttribute(e))

		 	}
		 } 
	}
	
	
	
}