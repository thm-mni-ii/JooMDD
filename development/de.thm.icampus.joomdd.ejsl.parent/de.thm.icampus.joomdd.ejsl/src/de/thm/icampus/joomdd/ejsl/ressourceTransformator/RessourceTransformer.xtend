package de.thm.icampus.joomdd.ejsl.ressourceTransformator

import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel
import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension
import de.thm.icampus.joomdd.ejsl.eJSL.Feature
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.eJSL.DetailPageField
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypes
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypeKinds
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes
import de.thm.icampus.joomdd.ejsl.eJSL.Page

class RessourceTransformer {
	EJSLModel modelInstance
	CMSExtension cmsextension
	Feature featurs
	new (EJSLModel model){
		modelInstance = model
		
		cmsextension = model.ejslPart as CMSExtension
		featurs = cmsextension.feature
		
	}
	def dotransformation(){
		transformEmptyHtmlAttribute();
		var JoomlaTranformator jt = new JoomlaTranformator(modelInstance)
		jt.completeCMSExtension
		
	}
	def transformEmptyHtmlAttribute(){
		
		for(Page page: featurs.pages.filter[t | t instanceof DetailsPage]){
			var DetailsPage dp = page as DetailsPage
			completeTableColumnAndEditedFields(dp)
			for(DetailPageField field: dp.editfields){
				if(field.htmltype == null){
				   var int index = dp.editfields.indexOf(field)
				   dp.editfields.remove(field)
				     dp.editfields.add(index,parseAttributeType(field.attribute))
				    
				   println(field.attribute+ "  "+field.htmltype + " -> " + parseAttributeType(field.attribute))
				}
			}
		}
	}
	
	private def completeTableColumnAndEditedFields(DetailsPage page) {
		if(page.editfields.empty && !page.tablecolumns.empty){
			for(Attribute attr: page.tablecolumns){
				page.editfields.add(parseAttributeType(attr))
			}
		}
		if(!page.editfields.empty && page.tablecolumns.empty){
			
			for( DetailPageField editedAttr: page.editfields){
				page.tablecolumns.add(editedAttr.attribute)
			}
		}
		if(page.editfields.empty && page.tablecolumns.empty){
			for(Attribute attr: page.entities.get(0).attributes){
				page.tablecolumns.add(attr)
				page.editfields.add(parseAttributeType(attr))
			}
		}
	}
	

	
	def DetailPageField parseAttributeType(Attribute attribute) {
		var DetailPageField editField = EJSLFactory.eINSTANCE.createDetailPageField
		switch attribute.type{
			DatatypeReference :{
				var SimpleHTMLTypes result = EJSLFactory.eINSTANCE.createSimpleHTMLTypes
				result.htmltype = SimpleHTMLTypeKinds.get("Text_Field")
				editField.attribute = attribute
				editField.htmltype = result
				return editField
			}
			StandardTypes:{
				var StandardTypes temptyp = attribute.type as StandardTypes
				var SimpleHTMLTypes result = EJSLFactory.eINSTANCE.createSimpleHTMLTypes
				result.htmltype = SimpleHTMLTypeKinds.get(parsingType(temptyp))
				editField.attribute = attribute
				editField.htmltype = result
				return editField
			}
		}
	}
	
	def String parsingType(StandardTypes eJSlType) {
		
		    var String value = "";
		switch (eJSlType.type.getName()){
			case "Integer" :{
				value = "Integer"
			}
			case "Boolean" :{
			value = "Yes_No_Buttons"
				
			}
			case "Textarea" :{
				value = "Textarea"
			}
			case "Textfield" :{
				value = "Text_Field"
			} 
			case "Time":{
				value = "Datepicker"
			}
			case "Date":{
				value = "Datepicker"
			}
			case "Datetime" :{
				value = "Datepicker"
				}
			case "Link" :{
				value = "Text_Field"
			}
			case "Image":{
				value = "Imagepicker"
			}
			case "File" :{
				value = "Filepicker"
			}
			case "Label":{
				value = "Text_Field_NE"
			}
			
		}

		return value
	}
	
}