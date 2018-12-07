package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.LibraryImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedLibrary
import de.thm.icampus.joomdd.ejsl.eJSL.Library
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil

class ExtendedLibraryImpl extends LibraryImpl implements ExtendedLibrary {
	
	EList<ExtendedEntity> extendedEntities
	String extensionName 
	new(Library lib){
		this.entities = lib.entities
		this.name = PlattformUtil.slugify(lib.name)
		this.classes = lib.classes
		this.manifest = lib.manifest
		this.packages = lib.packages
		this.languages = lib.languages
		 extensionName == "lib_" + this.name.toLowerCase
		init()
	}
	def void init(){
		extendedEntities = new BasicEList<ExtendedEntity>
		extendedEntities.addAll(entities.map[t | new ExtendedEntityImpl(t)])
		
	}
	override getExtendedEntities() {
		return extendedEntities
	}
	
	override extensionName() {
		return extensionName
	}
	
}