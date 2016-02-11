package de.thm.icampus.ejsl.generator.ps

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.eJSL.Entity

class EntityGenerator extends AbstracteGenerator {
	
	EList<Entity> entities
	
	new(EList<Entity> entitiesList) {
		entities = entitiesList
	}
	
	override dogenerate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override dogenerate(String path, IFileSystemAccess fileGen) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	
	
}