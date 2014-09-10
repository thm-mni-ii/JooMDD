package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.generator.ContentGenerator
import de.thm.icampus.cjsl.cjsl.Application
import de.thm.icampus.cjsl.cjsl.cJSL_Content
import org.eclipse.emf.common.util.EList
import de.thm.icampus.cjsl.cjsl.Contact
import de.thm.icampus.cjsl.cjsl.ContactCategory

class ContactGenerator extends ContentGenerator {
	
	Application app
	EList<Contact> allcontacts
	cJSL_Content contentContainer
	EList<ContactCategory> allcat
	int contactStartid
	int assetAktuellID
	int catAktuellID
	int userStartid
	int viewLevelid

	new(
		Application newapp,
		int contactStartindex,
		int assetStartindex,
		int categorieStartindex,
		int userStartindex,
		int viewLevelMaxindex
	) {

		app = newapp
		contentContainer = app.cjsl_content
		allcontacts = contentContainer.contact
		contactStartid = contactStartindex
		assetAktuellID = assetStartindex
		catAktuellID = categorieStartindex
		userStartid = userStartindex
		viewLevelid = viewLevelMaxindex
		allcat = contentContainer.contactCategory
		

	}
	
	override generate() '''
	
	'''
}