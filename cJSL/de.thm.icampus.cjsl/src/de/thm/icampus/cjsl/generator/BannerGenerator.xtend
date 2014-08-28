package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.generator.ApplicationLibrary
import de.thm.icampus.cjsl.cjsl.Application
import de.thm.icampus.cjsl.cjsl.Banner
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import de.thm.icampus.cjsl.cjsl.cJSL_Content


class BannerGenerator extends ContentGenerator {
	
	Application app
	EList<Banner> allbanners
	
	cJSL_Content contentContainer
	EList<EObject> allcat
	int bannerStartid
	int assetAktuellID
	int catAktuellID
	int userStartid
	int viewLevelid

	new(
		Application newapp,
		int bannerStartindex,
		int assetStartindex,
		int categorieStartindex,
		int userStartindex,
		int viewLevelMaxindex
	) {

		app = newapp
		contentContainer = app.cjsl_content
		allbanners = contentContainer.banner
		bannerStartid = bannerStartindex
		assetAktuellID = assetStartindex
		catAktuellID = categorieStartindex
		userStartid = userStartindex
		viewLevelid = viewLevelMaxindex
		allcat.addAll(contentContainer.bannerCategory)
		

	}
	
	override generate() '''
	
	'''
	
}