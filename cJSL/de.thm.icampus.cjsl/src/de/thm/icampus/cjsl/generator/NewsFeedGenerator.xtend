package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.generator.ContentGenerator
import de.thm.icampus.cjsl.cjsl.Application
import org.eclipse.emf.common.util.EList
import de.thm.icampus.cjsl.cjsl.Newsfeed
import org.eclipse.emf.ecore.EObject
import de.thm.icampus.cjsl.cjsl.cJSL_Content


class NewsFeedGenerator extends ContentGenerator {
	
	Application app
	EList<Newsfeed> allnewsfeeds
	cJSL_Content contentContainer
	EList<? extends EObject> allcat
	int newsfeedStartid
	int assetAktuellID
	int catAktuellID
	int userStartid
	int viewLevelid

	new(
		Application newapp,
		int newsfeedStartindex,
		int assetStartindex,
		int categorieStartindex,
		int userStartindex,
		int viewLevelMaxindex
	) {

		app = newapp
		contentContainer = app.cjsl_content
		allnewsfeeds = contentContainer.newsfeed
		newsfeedStartid = newsfeedStartindex
		assetAktuellID = assetStartindex
		catAktuellID = categorieStartindex
		userStartid = userStartindex
		viewLevelid = viewLevelMaxindex
		allcat.addAll(contentContainer.newsfeedCategory)
		

	}
	
	override generate() '''
	
	'''
		
	
}