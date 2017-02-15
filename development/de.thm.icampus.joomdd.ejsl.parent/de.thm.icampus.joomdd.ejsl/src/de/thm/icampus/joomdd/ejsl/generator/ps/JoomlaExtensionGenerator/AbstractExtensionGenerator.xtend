/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.Author
import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import org.eclipse.emf.common.util.EList
import org.eclipse.xtend.lib.Property
import org.eclipse.xtext.generator.IFileSystemAccess2

abstract public class AbstractExtensionGenerator  {

	@Property IFileSystemAccess2 fsa
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
		return _path
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
		fsa.generateFile(fileName, content)
	}

	def CharSequence generateScript(Extension ext, String extName ){
		return new ExtenionScriptGenerator(ext, extName).generate()
	}
	

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
	
	
	
	

} // AbstractExtensionGenerator
