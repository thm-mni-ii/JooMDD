package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.generator.ContentGenerator
import de.thm.icampus.cjsl.cjsl.Application
import org.eclipse.emf.common.util.EList
import de.thm.icampus.cjsl.cjsl.Weblink
import org.eclipse.emf.ecore.EObject
import de.thm.icampus.cjsl.cjsl.cJSL_Content


class WebLinkGenerator extends ContentGenerator {
	
	Application app
	EList<Weblink> allweblinks
	cJSL_Content contentContainer
	EList<? extends EObject> allcat
	int weblinkStartid
	int assetAktuellID
	int catAktuellID
	int userStartid
	int viewLevelid

	new(
		Application newapp,
		int weblinkStartindex,
		int assetStartindex,
		int categorieStartindex,
		int userStartindex,
		int viewLevelMaxindex
	) {

		app = newapp
		contentContainer = app.cjsl_content
		allweblinks = contentContainer.weblink
		weblinkStartid = weblinkStartindex
		assetAktuellID = assetStartindex
		catAktuellID = categorieStartindex
		userStartid = userStartindex
		viewLevelid = viewLevelMaxindex
		allcat.addAll(contentContainer.weblinkCategory)
		

	}
	
	override generate() '''
	
	'''
	
}