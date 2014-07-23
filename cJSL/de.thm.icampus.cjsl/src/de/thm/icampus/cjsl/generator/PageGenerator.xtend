package de.thm.icampus.cjsl.generator

import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.cjsl.cjsl.Application
import de.thm.icampus.cjsl.cjsl.cJSL_Page

class PageGenerator extends ApplicationLibrary {
	IFileSystemAccess acc
	 Application app
	cJSL_Page pages 
	new(IFileSystemAccess newacc, Application newapp) {
		app = newapp
		acc = newacc
		pages = app.cjsl_page
	}
	
}