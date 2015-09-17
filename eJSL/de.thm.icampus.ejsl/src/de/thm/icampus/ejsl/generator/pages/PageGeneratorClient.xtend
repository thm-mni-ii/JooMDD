/**
    */
package de.thm.icampus.ejsl.generator.pages

import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.DetailsPage
import de.thm.icampus.ejsl.eJSL.IndexPage
import de.thm.icampus.ejsl.eJSL.StaticPage
import de.thm.icampus.ejsl.eJSL.Section
import org.eclipse.xtext.generator.IFileSystemAccess

public class PageGeneratorClient {
	def static CharSequence generateStaticPage(Page sp) '''
	
	'''

	def static void generateView(Page page, Component component, String sec, String path,IFileSystemAccess fsa) {
		switch page{
			DetailsPage : {
				var DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path, fsa)
				dp.generateView()
			}
			IndexPage : {
				var IndexPageTemplate dp = new IndexPageTemplate(page, component, sec, path, fsa)
				dp.generateView()
			}
			StaticPage :{
				
			}
		}
		
		
	}

	def static void generateController(Page page, Component component, String sec, String path,IFileSystemAccess fsa) {
		switch page{
			DetailsPage : {
				var DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path,fsa)
				dp.generateController()
			}
			IndexPage : {
				var IndexPageTemplate dp = new IndexPageTemplate(page, component, sec, path, fsa)
				dp.generateController()
			}
			StaticPage :{
				
			}
		}
		
	}
	def static generateModel(Page page, Component component, String sec, String path,IFileSystemAccess fsa) {
		switch page{
			DetailsPage : {
				var DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path,fsa)
				dp.generateModel()
			}
			IndexPage : {
				var IndexPageTemplate dp = new IndexPageTemplate(page, component, sec, path, fsa)
				dp.generateModel()
			}
			StaticPage :{
				
			}
		}
		
	}

	
	
	
	
} // PageGeneratorClient

