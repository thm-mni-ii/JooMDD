/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Library
import de.thm.icampus.joomdd.ejsl.eJSL.Package
import java.util.Calendar
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.joomdd.ejsl.eJSL.Type
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.ProtectedRegion
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.KVPairGeneratorClient
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedLibrary
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import org.eclipse.xtext.generator.IFileSystemAccess2

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library Generator</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getLibraryGenerator()
 * @model
 * @generated
 */
public class LibraryGenerator extends AbstractExtensionGenerator {
	@Property ExtendedLibrary library
	private String subpackageName;

	new(ExtendedLibrary library, IFileSystemAccess2 access, String path) {
		this.library = library
		this.name = "lib_" + Slug.slugify(library.name)
		
		// Variable for subpackage tag in manifest and for installation
		this.subpackageName = library.name
		this.fsa = access
		library.formatName
		this.path = path
	}
	
	def void formatName(ExtendedLibrary library){
		library.name = Slug.slugify(library.name)
	}

	override generate() {
		generateJoomlaDirectory("")
		
		generateFile(this.name + ".xml", library.xmlContent(this.subpackageName.toFirstLower))

		if (library.languages.size > 0) {
			generateJoomlaDirectory("language")

			for (lang : library.languages) {
				generateJoomlaDirectory("/language/" + lang.name)
				generateFile("/language/" + lang.name + "/" + lang.name + "." + name + ".ini",
					library.iniLanguage(lang.name))
				generateFile("/language/" + lang.name + "/" + lang.name + "." + name + ".sys.ini",
					library.iniLanguage(lang.name))
			}
		}
		
		// Generate folders with classes within
		for (packageItem : library.packages) {
			generateJoomlaDirectory(packageItem.name)
			for (className : packageItem.classes) {
				generateFile("/" + packageItem.name + "/" + className.name + ".php", phpFile(packageItem, className.name))
			}
		}

		// Generate classes in main folder
		for (className : library.classes) {
			generateFile(className.name + ".php", library.phpFile(className.name))
		}
		
		// Generate entities
		for (entity : library.entities) {
			generateFile(entity.name + ".php", library.entityFile(entity.name))
		}

		return ""
	}
	
	def CharSequence entityFile(ExtendedLibrary library, String entityName) '''
		«FOR entity : library.extendedEntities»
			<?php
				/**
				 * @package		Joomla.Libraries
				 * @subpackage	«this.subpackageName»
				 *
				 * @copyright
				 * @license		
				 */
				
				defined('_JEXEC') or die();
				
				/**
				 * «this.subpackageName»«entityName» Class.
				 * 
				 * @package		Joomla.Libraries
				 * @subpackage	«this.subpackageName»
				 * @since		3.3
				 */
				class «this.subpackageName»«entityName»
				{
					«FOR attribute : entity.extendedAttributeList»
						/**
						 * @var		«attribute.generatorType»	Variable description
						 * @since	3.3
						 */
						private $«attribute.name»;
						
					«ENDFOR»
					
					/**
					 * Returns properties of class
					 *
					 * @param	$property	Name of searched property
					 * 
					 * @return	mixed	Property of this class
					 */
					public function __get($property)
					{
						if (property_exists($this, $property))
						{
							return $this->$property;
						}
					}

					/**
					 * Sets value for property
					 *
					 * @param	string	$property	Name of property
					 * @param	mixed	$value		New value for property
					 *
					 * @return	void
					 */
					public function __set($property, $value)
					{
						if (property_exists($this, $property))
						{
							$this->$property = $value;
						}
					}

					/**
					 * Method to get a single record.
					 *
					 * @param   integer  $pk  The id of the primary key.
					 *
					 * @return  mixed  Object on success, false on failure.
					 *
					 * @since   1.6
					 */
					public function getItem($pk = null)
					{
						// TODO: auto-generated method stub
					}
					
					/**
					 * Method to save the form data.
					 *
					 * @param   array  $data  The form data.
					 *
					 * @return  boolean  True on success.
					 *
					 * @since	3.1
					 */
					public function save($data)
					{
						// TODO: auto-generated method stub
					}
				}
			?>
		«ENDFOR»
	'''

	/**
	 * Method, that generates php files for classes
	 */
	def CharSequence phpFile(Library library, String contentName) '''
		<?php
			/**
			 * @package		Joomla.Libraries
			 * @subpackage	«this.subpackageName»
			 *
			 * @copyright
			 * @license		
			 */
			
			defined('_JEXEC') or die();
			
			«FOR classObj : library.classes»
				«IF classObj.name == contentName»
					/**
					* «this.subpackageName»«classObj.name» Class.
					* 
					* @package		Joomla.Libraries
					* @subpackage	«this.subpackageName»
					* @since		3.3
					*/
					class «this.subpackageName»«classObj.name»
					{
						«FOR method : classObj.methods»
							/**
							 * Method description
							 *
							 «FOR param : method.methodparameters»
							 	* @param	«switch(param.type.typeName) {
							 				case "string":	{ "string" }
							 				case "bool":	{ "boolean" }
							 				case "int":		{ "integer" }
							 				case "float":	{ "float" }
							 				case "array":	{ "array" }
							 				default:		{ "mixed" }
							 			}»	$«param.name»	Parameter description
							 «ENDFOR»
							 *
							 * @return	«switch(method.type.typeName) {
							 				case "string":	{ "string	Description" }
							 				case "bool":	{ "boolean	Description" }
							 				case "int":		{ "integer	Description" }
							 				case "float":	{ "float	Description" }
							 				case "array":	{ "array	Description" }
							 				default:		{ "mixed	Description" }
							 			}»
							 */
							public function «method.name»(«FOR param : method.methodparameters SEPARATOR ", "»$«param.name»«ENDFOR»)
							{
								// TODO: auto-generated method stub
								«switch(method.type.typeName) {
									case "string":	{ "$" + method.returnvalue + " = '';"  }
									case "bool":	{ "$" + method.returnvalue + " = false;" }
									case "int":		{ "$" + method.returnvalue + " = 0;" }
									case "float":	{ "$" + method.returnvalue + " = 0.0;" }
									case "array":	{ "$" + method.returnvalue + " = array();" }
									default:		{ "$" + method.returnvalue + " = null;" }
								}»
								return $«method.returnvalue»;
							}
							
						«ENDFOR»
					}
				«ENDIF»
			«ENDFOR»
		?>
	'''
	
	/**
	 * Method, that generates php files for classes in Package
	 */
	def CharSequence phpFile(Package packageObj, String contentName) '''
		<?php
			/**
			 * @package		Joomla.Libraries
			 * @subpackage	«this.subpackageName»
			 *
			 * @copyright
			 * @license		
			 */
			
			defined('_JEXEC') or die();
			
			«FOR classObj : packageObj.classes»
				«IF classObj.name == contentName»
					/**
					* «this.subpackageName»«classObj.name» Class.
					* 
					* @package		Joomla.Libraries
					* @subpackage	«this.subpackageName»
					* @since		3.3
					*/
					class «this.subpackageName»«classObj.name»
					{
						«FOR method : classObj.methods»
							/**
							 * Method description
							 *
							 «FOR param : method.methodparameters»
							 	* @param	«switch(param.type.typeName) {
							 				case "string":	{ "string" }
							 				case "bool":	{ "boolean" }
							 				case "int":		{ "integer" }
							 				case "float":	{ "float" }
							 				case "array":	{ "array" }
							 				default:		{ "mixed" }
							 			}»	$«param.name»	Parameter description
							 «ENDFOR»
							 *
							 * @return	«switch(method.type.typeName) {
							 				case "string":	{ "string	Description" }
							 				case "bool":	{ "boolean	Description" }
							 				case "int":		{ "integer	Description" }
							 				case "float":	{ "float	Description" }
							 				case "array":	{ "array	Description" }
							 				default:		{ "mixed	Description" }
							 			}»
							 */
							public function «method.name»(«FOR param : method.methodparameters SEPARATOR ", "»$«param.name»«ENDFOR»)
							{
								// TODO: auto-generated method stub
								«switch(method.type.typeName) {
									case "string":	{ "$" + method.returnvalue + " = '';"  }
									case "bool":	{ "$" + method.returnvalue + " = false;" }
									case "int":		{ "$" + method.returnvalue + " = 0;" }
									case "float":	{ "$" + method.returnvalue + " = 0.0;" }
									case "array":	{ "$" + method.returnvalue + " = array();" }
									default:		{ "$" + method.returnvalue + " = null;" }
								}»
								return $«method.returnvalue»;
							}
							
						«ENDFOR»
					}
				«ENDIF»
			«ENDFOR»
		?>
	'''
	
	def CharSequence getTypeName(ExtendedAttribute type){
		return Slug.getTypeName(type.htmlType)
	}
	def CharSequence getTypeName(Type type){
		return Slug.getTypeName(type)
	}

	def CharSequence iniLanguage(Library library, String languageFileName) '''
		«FOR pageLang : library.languages»
			«IF pageLang.name == languageFileName»
				«FOR langvalue : pageLang.keyvaluepairs»
					«langvalue.name»="«langvalue.value»"
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	'''
	
	def CharSequence xmlContent(Library library, String libName) '''
		<?xml version="1.0" encoding="utf-8"?>
		<extension type="library" version="3.3.0" method="upgrade">
		    <name>«library.name»</name>
		    <libraryname>«libName»</libraryname>
		    «library.manifest.authors.generate»            
		    «IF (library.manifest.creationdate != null)»
		    	<creationDate>«library.manifest.creationdate»</creationDate>
		    «ELSE»
		    	<creationDate>«Calendar::instance.get(Calendar::YEAR)»</creationDate>
		    «ENDIF»
		    «IF (library.manifest.copyright != null)»
		    	<copyright>«library.manifest.copyright»</copyright>
		    «ENDIF»
		    «IF (library.manifest.license != null)»
		    	<license>GPL 2.0</license>
		    «ENDIF»
		    «IF (library.manifest.version != null)»
		    	<version>«library.manifest.version»</version>
		    «ENDIF»
		    «IF (library.manifest.description != null)»
		    	<description>«library.manifest.description»</description>
		    «ENDIF»
		    
		    <files>
				<filename>index.html</filename>
		    	«FOR packageObj : library.packages»
		    		<folder>«packageObj.name»</folder>
		    	«ENDFOR»
		        «FOR classObj : library.classes»
		        	<filename>«classObj.name».php</filename>
		        «ENDFOR»
		        «FOR entity : library.entities»
		        	<filename>«entity.name».php</filename>
		        «ENDFOR»
		    </files>
		    
		    <languages>
		    	«FOR lang : library.languages»
		    		<language tag="«lang.name»">language/«lang.name»/«lang.name».«this.name».ini</language>
		    	«ENDFOR»
		    </languages>
		    
		</extension>
	'''

	

} // LibraryGenerator
