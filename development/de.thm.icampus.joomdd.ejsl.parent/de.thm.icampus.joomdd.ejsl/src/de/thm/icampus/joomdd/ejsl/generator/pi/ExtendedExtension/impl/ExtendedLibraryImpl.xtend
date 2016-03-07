package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.LibraryImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedLibrary
import de.thm.icampus.joomdd.ejsl.eJSL.Library
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl
import org.eclipse.emf.common.util.BasicEList

class ExtendedLibraryImpl extends LibraryImpl implements ExtendedLibrary {
	
	EList<ExtendedEntity> extendedEntities
	new(Library lib){
		this.entities = lib.entities
		this.name = PlattformIUtil.slugify(lib.name)
		this.classes = lib.classes
		this.manifest = lib.manifest
		this.packages = lib.packages
		this.languages = lib.languages
		init()
	}
	def void init(){
		extendedEntities = new BasicEList<ExtendedEntity>
		extendedEntities.addAll(entities.map[t | new ExtendedEntityImpl(t)])
		
	}
	override getExtendedEntities() {
		return extendedEntities
	}
	
}