/**
    */
package de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedPage
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.ejsl.eJSL.StaticPage

public class PageGeneratorClient {
	
	ExtendedPage extPage
	ExtendedComponent com
	String pathExt
	IFileSystemAccess fsa
	String sectionExt
	
	
	new(ExtendedPage page, ExtendedComponent component, String path, String section,IFileSystemAccess access) {
		extPage = page
		com = component
		pathExt = path
		fsa = access
		sectionExt = section
	}
	
	def  CharSequence generateStaticPage(StaticPage sp) '''
	
	'''

	def  void generateView(ExtendedDynamicPage page, ExtendedComponent component, String sec, String path,IFileSystemAccess fsa) {
		if(page.detailsPage) {
			
				var DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path, fsa)
				//dp.generateView()
			}
			else{
				var IndexPageTemplate dp = new IndexPageTemplate(page, component, sec, path, fsa)
				dp.generateView()
			}
			
		}
		
		


	 def void generateController(ExtendedDynamicPage page, ExtendedComponent component, String sec, String path,IFileSystemAccess fsa) {
			if(page.detailsPage) {
				var DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path,fsa)
				//dp.generateController()
			}
			else{
				var IndexPageTemplate dp = new IndexPageTemplate(page, component, sec, path, fsa)
				dp.generateController()
			}
			
		}
		
	def  generateModel(ExtendedDynamicPage page, ExtendedComponent component, String sec, String path,IFileSystemAccess fsa) {
			if(page.detailsPage) {
				var DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path,fsa)
				//dp.generateModel()
			}
			else {
				var IndexPageTemplate dp = new IndexPageTemplate(page, component, sec, path, fsa)
				dp.generateModel()
			}
			
		}
		
	


	def void generate(){
		if (extPage.extendedDynamicPageInstance != null) {
			var String viewPath = pathExt + "/views";
			var ExtendedDynamicPage dynPage = extPage.extendedDynamicPageInstance as ExtendedDynamicPage
			generateView(dynPage, com, sectionExt, viewPath, fsa)
			var String controllerpath = pathExt + "/controllers"
			generateController(dynPage, com, sectionExt, controllerpath, fsa)
			var String modelpath = pathExt + "/models"
			generateModel(dynPage, com, sectionExt, modelpath, fsa)
		} else if (extPage.staticPageInstance != null) {
			generateStaticPage(extPage.staticPageInstance)
		}
	}
	
	
	
} // PageGeneratorClient

