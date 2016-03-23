package de.thm.icampus.ejsl.generator.ps

import org.eclipse.xtext.generator.IFileSystemAccess

abstract class  AbstracteGenerator {
	
	def CharSequence dogenerate()
	def void dogenerate(String path, IFileSystemAccess fileGen)
}