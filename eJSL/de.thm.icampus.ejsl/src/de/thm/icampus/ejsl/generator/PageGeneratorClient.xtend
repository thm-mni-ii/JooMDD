/**
    */
package de.thm.icampus.ejsl.generator

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
		INSERT INTO `#__content`
		(title, alias, introtext, state, created, created_by, attribs, metadata, version)
		VALUES
		(`«sp.name»`, `«sp.name.toLowerCase»`
		, `«sp.hashCode»`
		, 1
		, GETDATE()
		, 1
		, `{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","alternative_readmore":"","article_layout":""}`
		, `{"robots":"","author":"","rights":"","xreference":""}`
		, 1
		)
	'''

	def static void generateView(Page page, Component component, String sec, String path,IFileSystemAccess fsa) {
		
		switch page{
			DetailsPage : {
				var DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path, fsa)
				dp.generateView()
			}
			IndexPage : {
				
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
				
			}
			StaticPage :{
				
			}
		}
		
	}

	
	
	
	
} // PageGeneratorClient

