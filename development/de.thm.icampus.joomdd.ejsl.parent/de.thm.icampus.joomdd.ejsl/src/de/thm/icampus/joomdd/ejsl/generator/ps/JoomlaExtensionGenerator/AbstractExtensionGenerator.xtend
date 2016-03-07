/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.Author
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.eJSL.Type
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.Parameter
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.ProtectedRegion
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.KVPairGeneratorClient
import de.thm.icampus.joomdd.ejsl.generator.ps.ExtensionGenerator
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter

abstract public class AbstractExtensionGenerator  {

	@Property IFileSystemAccess fsa
	@Property String name
	@Property String path = ""
	public Extension ext

	/**
     * as the name gets prefixed with the type of the Extension, 
     * this Field should hold the unprefixed name, needed for correct naming 
     * of .php files in view and admin
     */
	@Property String noPrefixName

	/**
     * Setter for path
     * Property path will contain a trailing slash
     */
	def void setPath(String path) {
		_path = path
		if (!path.empty && !(path.endsWith("/") || path.endsWith("\\"))) {
			_path = _path.concat("/")
		}
	}

	/**
     * Get name extended path
     */
	def String getPath() {
		return _path.concat(name + "/")
	}

	/**
     * Get either name extended or raw path
     * 
     * @param raw
     * @return raw path if raw = true, name extended path otherwise 
     */
	def String getPath(boolean raw) {
		if (raw)
			return _path
		return path
	}

	/**
     * Setter for entity name.
     * 
     * @param name Name of entity
     */
	def void setName(String name) {
		_name = name
	}

	/**
     * Getter for entity name.
     */
	def String getName() {
		return _name
	}

	/**
     * Generate directory containing default joomla index.html.
     * Directory name will be prefixed by path
     * 
     * @param dirName  using '/' as directory separator
     */
	def protected generateJoomlaDirectory(String dirName) {
		var p = dirName
		while (p.endsWith("/")) {
			p = p.substring(0, p.length - 1);
		}
		generateFile(p + "/index.html", indexHtml)
	}

	def static CharSequence indexHtml() '''
		<!DOCTYPE html><title></title>
	'''

	/**
     * Generate File using fsa.
     * File name will be prefixed by path
     * 
     * @param fileName  using '/' as file separator
     * @param content   the to-be-written contents
     */
	def protected void generateFile(String fileName, CharSequence content) {
		fsa.generateFile(path + fileName, content)
	}

	/* Abstract Methods */
	/**
     * Generate content for entity. Every generated file will be 
     * placed in the directory defined by property path
     */
	def CharSequence generate(EList<Author> authors) '''
		«IF authors.size() == 0»
			<author>Auto Generated Author</author>
			<authorEmail>info@generated.com</authorEmail>
			<authorUrl>www.generated.com</authorUrl>
		«ELSE»
			«FOR author : authors»
				<author>«author.name»</author>
				«IF author.authoremail != null»
					<authorEmail>«author.authoremail»</authorEmail>
				«ENDIF»
				«IF author.authorurl != null»
					<authorUrl>«author.authorurl»</authorUrl>
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	'''

	public def CharSequence generate();

	/**
     * Generate content for entity.
     * 
     * @see EntityGenerator.generate()
     * @param path Will be set using 
     */
	def void generate(String path) {
		var old_path = this.path
		this.path = path
		generate()
		this.path = old_path
	}
	
	
	
	def CharSequence generateParameter(EList<ExtendedParameter>listParams)'''
		«FOR param : listParams»
		 <field
		 name="«param.name»"
		 type="«Slug.getTypeName(param.generatorType)»"
		 label="«param.label »"
		 description="«param.descripton»"
		 />
		«ENDFOR»
		'''


} // AbstractExtensionGenerator
