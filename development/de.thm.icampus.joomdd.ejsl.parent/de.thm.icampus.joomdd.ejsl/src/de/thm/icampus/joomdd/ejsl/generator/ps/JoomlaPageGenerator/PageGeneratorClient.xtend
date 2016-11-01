/**
    */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedStaticPage
import org.eclipse.xtext.generator.IFileSystemAccess
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
		
		} else if (extPage.staticPageInstance != null && !extPage.staticPageInstance.preserve ) {
			generateStaticPage(extPage.staticPageInstance)
		}
	}
	
	
	
} // PageGeneratorClient

