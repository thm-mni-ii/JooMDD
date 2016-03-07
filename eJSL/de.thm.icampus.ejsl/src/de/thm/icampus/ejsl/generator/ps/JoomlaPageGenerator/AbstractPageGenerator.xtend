/**
 */
package de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator;

import org.eclipse.emf.ecore.EObject;
import de.thm.icampus.ejsl.eJSL.Type
import de.thm.icampus.ejsl.eJSL.DatatypeReference
import org.eclipse.xtext.generator.IFileSystemAccess

abstract public class AbstractPageGenerator {
	@Property IFileSystemAccess fsa
	@Property String name
	@Property String path = ""
	
	public def  LinkGeneratorClient getLinkClient();

	 def void setLinkClient( LinkGeneratorClient value);
	 
	 public def CharSequence generatePage();
	 
	 
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

} // AbstractPageGenerator
