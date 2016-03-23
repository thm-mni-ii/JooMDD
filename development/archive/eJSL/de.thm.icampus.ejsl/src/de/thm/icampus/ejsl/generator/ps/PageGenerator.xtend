package de.thm.icampus.ejsl.generator.ps

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.eJSL.Page

class PageGenerator extends AbstracteGenerator {
		
	EList<Page> pagelist 
	new(EList<Page> list) {
		pagelist = list
	}
	
	override dogenerate() {
			throw new UnsupportedOperationException("TODO: auto-generated method stub")
		}
		
		override dogenerate(String path, IFileSystemAccess fileGen) {
			throw new UnsupportedOperationException("TODO: auto-generated method stub")
		}
		
		
	
}