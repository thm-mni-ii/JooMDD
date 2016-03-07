/**
 */
package de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.ejsl.eJSL.Page

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Static Page</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getStaticPage()
 * @model
 * @generated
 */
public class StaticPageTemplate extends AbstractPageGenerator {
	
	Page mypage
	
	new (Page page){
		mypage = page
	}
	
	def  CharSequence generateStaticPage() '''
		INSERT INTO `#__content`
		(title, alias, introtext, state, created, created_by, attribs, metadata, version)
		VALUES
		(`«mypage.name»`, `«mypage.name.toLowerCase»`
		, `«mypage.hashCode»`
		, 1
		, GETDATE()
		, 1
		, `{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","alternative_readmore":"","article_layout":""}`
		, `{"robots":"","author":"","rights":"","xreference":""}`
		, 1
		)
	'''
	
	
	
} // StaticPage
