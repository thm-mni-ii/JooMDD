/**
 */
package de.thm.icampus.ejsl.generator.^extension
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.ejsl.eJSL.Module
import java.util.Calendar
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pages.PageGeneratorClient
import de.thm.icampus.ejsl.generator.util.Slug
import de.thm.icampus.ejsl.generator.util.ProtectedRegion
import de.thm.icampus.ejsl.generator.util.KVPairGeneratorClient

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Generator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eJSLGenerator.ModuleGenerator#getPageClient <em>Page Client</em>}</li>
 * </ul>
 * </p>
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getModuleGenerator()
 * @model
 * @generated
 */
public class ModuleGenerator extends AbstractExtensionGenerator {

	PageGeneratorClient pageClient

	Module module

	new(Module module, IFileSystemAccess fsa) {
		this.fsa = fsa
		this.name = 'mod_' + Slug.slugify(module.name)
		this.module = module
	}

	public def PageGeneratorClient getPageClient() {
		return this.pageClient
	}

	public def void setPageClient(PageGeneratorClient value) {
		this.pageClient = value;
	}

	override generate() {
		generateJoomlaDirectory("")
		
		generateFile(name + ".xml", this.module.xmlContent)
		generateFile(name + ".php", this.module.phpContent)
		generateFile("helper.php", this.module.helperPHP)
		
		for (lang : module.languages) {
			generateFile(lang.name + "." + name + ".ini", "")
		}
		
		generateJoomlaDirectory("tmpl")
		generateFile("tmpl/default.php", this.module.defaultTemplate)

		return ''
	}

	def CharSequence xmlContent(Module module) {
		'''
		<?xml version="1.0" encoding="utf-8"?>
		<extension type="module" version="3.3" client="site" method="install">
		<name>«module.name»</name>
		«module.manifest.authors.generate»
		<creationDate>«if (module.manifest.creationdate != null) {
				module.manifest.creationdate
			} else {
				Calendar::instance.get(Calendar::YEAR)
			}»</creationDate>
		<copyright>«if (module.manifest.copyright != null) {
				module.manifest.copyright
			} else {
				"All rights reserved by Author."
			}»</copyright>
		<license>«if (module.manifest.license != null) {
				module.manifest.license
			} else {
				"GPL 2.0"
			}»</license>
		<version>«if (module.manifest.version != null) {
				module.manifest.version
			} else {
				"1.0.0"
			}»</version>
		<description>«if (module.manifest.description != null) {
			module.manifest.description
		} else {
			"Place Description here"
		}»</description>
		<!-- Listing of all files that should be installed for the module -->
		<files>
		<filename module="«name»">«name».php</filename>
		<filename>index.html</filename>
		<filename>tmpl/index.html</filename>
		<filename>tmpl/default.php</filename>
		<!-- Helper -->
		  <filename>helper.php</filename>
		<!-- Additional Files -->
		</files>
		<!-- All language files shipped with the modul -->
		<languages>
		«FOR lang : module.languages»
		<language tag="«lang.name»">«lang.name».«name».ini</language>
		«ENDFOR»
		</languages>
		<!-- Optional parameters -->
		<config>
		</config>
		</extension>
		'''
	}

	def CharSequence phpContent(Module module) {
		'''
		<?php
		// don't allow other scripts to grab and execute this file
		defined('_JEXEC') or die('Direct Access to this location is not allowed');

		// require helper file
		require_once dirname(__FILE__).'helper.php';
		
		require(JModuleHelper::getLayoutPath(«name»));
		?>
		'''
	}
	
	 def CharSequence defaultTemplate(Module module) {
		'''
		<?php 
		// no direct access
		defined( '_JEXEC' ) or die( 'Restricted access' );
		echo «module.name »": Default Template"
		?>
		'''
	}
	
	def CharSequence helperPHP(Module modul) {
		'''
		<?php
		// includes helper functions.
		
		class mod«name»Helper
		{ 
		    public static function getHello( $params )
		    {
		        return 'Hello, World!';
		    }
		}
		?>
		'''
	}
	
	override getProtectedRegions() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override setProtectedRegions(EList<ProtectedRegion> myprotectedRegions) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getSlug() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override setSlug(Slug slug) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getKvPairClient() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override setKvPairClient(KVPairGeneratorClient e) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

} // ModuleGenerator
