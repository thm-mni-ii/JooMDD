/**
    */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedStaticPage
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator.FieldsGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2

public class PageGeneratorClient {
	
	ExtendedPage extPage
	ExtendedComponent com
	String pathExt
	IFileSystemAccess2 fsa
	String sectionExt
	
	
	new(ExtendedPage page, ExtendedComponent component, String path, String section,IFileSystemAccess2 access) {
		extPage = page
		com = component
		pathExt = path 
		this.fsa = access
		sectionExt = section
	}
	
	private def  void generateStaticPage(ExtendedStaticPage sp) {
		var StaticPageTemplate static = new StaticPageTemplate(sp,com,sectionExt,pathExt,fsa)
		static.generate
	}

	private def  void generateView(ExtendedDynamicPage page, ExtendedComponent component, String sec, String path,IFileSystemAccess2 fsa) {
		if(page.detailsPage) {
			
				var DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path, fsa)
				dp.generateView()
			}
			else{
				var IndexPageTemplate dp = new IndexPageTemplate(page, component, sec, path, fsa)
				dp.generateView()
			}
			
		}
		
		


	private def void generateController(ExtendedDynamicPage page, ExtendedComponent component, String sec, String path,IFileSystemAccess2 fsa) {
			if(page.detailsPage) {
				var DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path,fsa)
				dp.generateController()
			}
			else{
				var IndexPageTemplate dp = new IndexPageTemplate(page, component, sec, path, fsa)
				dp.generateController()
			}
			
		}
		
	private def  generateModel(ExtendedDynamicPage page, ExtendedComponent component, String sec, String path,IFileSystemAccess2 fsa) {
			if(page.detailsPage) {
				var DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path,fsa)
				dp.generateModel()
			}
			else {
				var IndexPageTemplate dp = new IndexPageTemplate(page, component, sec, path, fsa)
				dp.generateModel()
			}
			
		}
		
	


	def void generateExtension(){
		if (extPage.extendedDynamicPageInstance != null) {
			var String viewPath = pathExt + sectionExt+ "/views";
			var ExtendedDynamicPage dynPage = extPage.extendedDynamicPageInstance as ExtendedDynamicPage
			generateView(dynPage, com, sectionExt, viewPath, fsa)
			var String controllerpath = pathExt + sectionExt+ "/controllers"
			generateController(dynPage, com, sectionExt, controllerpath, fsa)
			var String modelpath = pathExt + sectionExt+ "/models"
			generateModel(dynPage, com, sectionExt, modelpath, fsa)
			if(extPage.extendedDynamicPageInstance.isDetailsPage){
				generateUnknownFields(modelpath)
			}
		
		} else if (extPage.staticPageInstance != null) {
			generateStaticPage(extPage.staticPageInstance)
		}
	}
	def void generatePages(){
		if (extPage.extendedDynamicPageInstance != null && !extPage.extendedDynamicPageInstance.preserve ) {
			var String viewPath = pathExt + extPage.name+ "/views";
			var ExtendedDynamicPage dynPage = extPage.extendedDynamicPageInstance as ExtendedDynamicPage
			generateView(dynPage, com, sectionExt, viewPath, fsa)
			var String controllerpath = pathExt +extPage.name  + "/controllers" 
			generateController(dynPage, com, sectionExt, controllerpath, fsa)
			var String modelpath = pathExt + extPage.name  + "/models"
			generateModel(dynPage, com, sectionExt, modelpath, fsa)
			if(extPage.extendedDynamicPageInstance.isDetailsPage){
				generateUnknownFields(modelpath)
			}
		
		} else if (extPage.staticPageInstance != null && !extPage.staticPageInstance.preserve ) {
			generateStaticPage(extPage.staticPageInstance)
		}
	}
	def void generateUnknownFields(String modelPath){
		var String fieldsPath = modelPath +"/fields"
		 val joomlafields = #["menuitem","accesslevel","cachehandler","calendar","captcha","category","checkbox","checkboxes","chromestyle","color","contentlanguage","contenttype","componentlayout","contentlanguage","databaseconnection","editor","editors","email","file","filelist","folderlist","groupedlist","headertag","helpsite","hidden","imagelist","integer","language","list","media","menu","menuitem","meter","modulelayout","moduleorder","moduleposition","moduletag","note","number","password","plugins","predefinedlist","radio","range","repeatable","rules","sessionhandler","spacer","sql","subform","tag","tel","templatestyle","text","textarea","timezone","url","user","useractive","usergroup","usergrouplist","userstate"] 
		
		for(ExtendedDetailPageField field: extPage.extendedDynamicPageInstance.extendedEditedFieldsList){
			if (field.getinstance.htmltype instanceof DatatypeReference){
				var DatatypeReference  type = field.htmltype as DatatypeReference
				if(!joomlafields.contains(type.type.type.toLowerCase) ) {
					var FieldsGenerator gen = new FieldsGenerator(field,com)
					fsa.generateFile(fieldsPath+ "/" + type.type.type.toLowerCase, gen.genEmptyField)
				}
			}
		}
	}
	
	
	
} // PageGeneratorClient

