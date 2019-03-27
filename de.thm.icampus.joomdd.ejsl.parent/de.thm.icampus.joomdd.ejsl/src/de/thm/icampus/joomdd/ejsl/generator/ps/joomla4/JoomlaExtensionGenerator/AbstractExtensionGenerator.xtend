package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtend.lib.annotations.Accessors
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.ExtentionScriptGenerator

/**
 * Abstract class for Joomla extension generator classes.
 * 
 * @author Dennis Priefer, Dieudonne Timma Meyatchie
 */
abstract public class AbstractExtensionGenerator  {

	@Accessors IFileSystemAccess2 fsa
	@Accessors String name 
	@Accessors String path = ""
	public Extension ext

	/**
     * as the name gets prefixed with the type of the Extension, 
     * this Field should hold the unprefixed name, needed for correct naming 
     * of .php files in view and admin
     */
	@Accessors String noPrefixName

	/**
     * Setter for path
     * Property path will contain a trailing slash
     */
	def void setPath(String path) {
		this.path = path
		if (!path.empty && !(path.endsWith("/") || path.endsWith("\\"))) {
			this.path = this.path.concat("/")
		}
	}
 
	/**
     * Get name extended path
     */
	def String getPath() {
		return this.path
	}

	/**
     * Get either name extended or raw path
     * 
     * @param raw
     * @return raw path if raw = true, name extended path otherwise 
     */
	def String getPath(boolean raw) {
		if (raw)
			return this.path
		return path
	}

	/**
     * Setter for entity name.
     * 
     * @param name Name of entity
     */
	def void setName(String name) {
		this.name = name
	}

	/**
     * Getter for entity name.
     */
	def String getName() {
		return this.name
	}

	/**
     * Generate directory containing default joomla index.html.
     * Directory name will be prefixed by path
     * 
     * @param dirName  using '/' as directory separator
     */
	def protected generateEmptyDirectory(String dirName) {
		var p = dirName
		while (p.endsWith("/")) {
			p = p.substring(0, p.length - 1);
		}
		generateFile(p.toLowerCase + "/index.html", indexHtml)
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
		fsa.generateFile(fileName.toLowerCase, content)
	}

	def CharSequence generateScript(ExtendedComponent ext, String extName ){
		return new ExtentionScriptGenerator(ext, extName).generate()
	}
	def CharSequence generateScript(ExtendedModule ext, String extName ){
		return new ExtentionScriptGenerator(ext, extName).generate()
	}
	def CharSequence generateScript(Extension ext, String extName ){
		return new ExtentionScriptGenerator(ext, extName).generate()
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

}
