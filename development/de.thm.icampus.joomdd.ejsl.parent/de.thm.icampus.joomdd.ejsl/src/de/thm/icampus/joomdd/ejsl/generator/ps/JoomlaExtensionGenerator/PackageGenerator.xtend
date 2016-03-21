/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.ExtensionPackage
import de.thm.icampus.joomdd.ejsl.eJSL.Library
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.eJSL.Plugin
import de.thm.icampus.joomdd.ejsl.eJSL.Template
import java.util.Calendar
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.ProtectedRegion
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.KVPairGeneratorClient
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug

public class PackageGenerator extends AbstractExtensionGenerator {
	
	private ExtensionGeneratorClient extClient
	@Property ExtensionPackage pkg
	
	new(ExtensionPackage pkg, IFileSystemAccess access, String path) {
		this.pkg = pkg
		this.fsa = access
		this.name = "pkg_" + Slug.slugify(pkg.name)
		this.path = path
	}
	
	override generate() {
		generateJoomlaDirectory("")
		
		// Manifest
		generateFile(this.name + ".xml", pkg.xmlContent)
		
		generateJoomlaDirectory("packages")
		
        // Generate extensions
        for (ext : this.pkg.extensions) {
        	this.extClient = new ExtensionGeneratorClient(fsa, ext)
        	this.extClient.setPath(this.name + "/packages/")
			this.extClient.generateExtension
        }
        return ''
	}
	
	def CharSequence xmlContent(ExtensionPackage pkg) '''
		<?xml version="1.0" encoding="utf-8"?>
			<extension type="package" version="3.4.0" method="upgrade">
			    <name>«pkg.name»</name>
			    «pkg.manifest.authors.generate»            
			    «IF (pkg.manifest.creationdate != null)»
			    	<creationDate>«pkg.manifest.creationdate»</creationDate>
			    «ELSE»
			    	<creationDate>«Calendar::instance.get(Calendar::YEAR)»</creationDate>
			    «ENDIF»
			    «IF (pkg.manifest.copyright != null)»
			    	<copyright>«pkg.manifest.copyright»</copyright>
			    «ENDIF»
			    «IF (pkg.manifest.license != null)»
			    	<license>GPL 2.0</license>
			    «ENDIF»
			    «IF (pkg.manifest.version != null)»
			    	<version>«pkg.manifest.version»</version>
			    «ENDIF»
			    «IF (pkg.manifest.description != null)»
			    	<description>«pkg.manifest.description»</description>
			    «ENDIF»
			    
				<files folder="packages">
					«FOR com : pkg.extensions.filter(typeof(Component))»
						<file type="component" id="«pkg.name»">«com.name».zip</file>
	                «ENDFOR»
	                «FOR lib : pkg.extensions.filter(typeof(Library))»
	                	<file type="library" id="«pkg.name»">«lib.name».zip</file>
	                «ENDFOR»
	                «FOR mod : pkg.extensions.filter(typeof(Module))»
	                	<file type="module" id="«pkg.name»" client="site">«mod.name».zip</file>
	                «ENDFOR»
	                «FOR tpl : pkg.extensions.filter(typeof(Template))»
						<file type="template" id="«pkg.name»">«tpl.name».zip</file>
	                «ENDFOR»
	                «FOR plg : pkg.extensions.filter(typeof(Plugin))»
						<file type="plugin" id="«pkg.name»" group="«plg.type»">«plg.name».zip</file>
	                «ENDFOR»
				</files>
			</extension>
	'''
	


} // PackageGenerator
