
package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaPageGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.BackendSection
import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedStaticPage
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaUtil.Slug
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Abstract class for Joomla page generator classes.
 * 
 * @author Dennis Priefer, Dieudonne Timma Meyatchie
 */
abstract public class AbstractPageGenerator {
	@Accessors IFileSystemAccess2 fsa
	@Accessors String name
	@Accessors String path = ""
	 
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

   def CharSequence generateFileDoc(Page page, ExtendedComponent component)'''
       <?php
       /**
        «IF component.manifest !== null»
        * @version «component.manifest.version»
        «ENDIF»
        * @category Joomla component
        * @subpackage com_«Slug.slugify(component.name)».«if(page.eContainer instanceof BackendSection) "admin" else "site"»
        * @name «component.name»View
        «IF component.manifest !== null»
        «FOR author : component.manifest.authors»
        * @author «author.name», <«author.authoremail»>
        «ENDFOR»
        * @copyright «component.manifest.copyright»
        * @license «component.manifest.license»
        */
        «ENDIF»
    '''
    
    def CharSequence xmlSiteTemplateContent(String pagename, ExtendedStaticPage page, ExtendedComponent component) '''
		<?xml version="1.0" encoding="utf-8"?>
		<metadata>
		    <layout title="«Slug.nameExtensionBind("com", component.name).toUpperCase»_VIEW_«pagename.toUpperCase»_TITLE" option="View">
		        <message><![CDATA[«Slug.nameExtensionBind("com", component.name).toUpperCase»_VIEW_«pagename.toUpperCase»_DESC]]></message>
		    </layout>
		    <fields
		        name="request"
		        addfieldpath="administrator/components/«Slug.nameExtensionBind("com", component.name).toLowerCase»/Field"
		    >
		
		        «generateParameter(page.extendedGlobalParamater, component)»
		        «generateParameter(page.extendedLocalParameter, component)»
		        «FOR ExtendedParameterGroup e : page.extendedParameterGroup »
		        <fieldset name="«e.name.toLowerCase»" label="«Slug.nameExtensionBind("com",component.name).toUpperCase»_FIELDSET_«page.name.toUpperCase»_«e.name.toUpperCase»">
		            «generateParameter(e.extendedParameterList, component)»
		            «generateParameter(e.extendedParameterList,component)»
		        </fieldset>
		        «ENDFOR»
		    </fields>
		</metadata>
	'''
	def  CharSequence generateTemplate(ExtendedDynamicPage page, ExtendedComponent component) '''
	'''
	
	def CharSequence generateParameter(EList<ExtendedParameter>listParams, ExtendedComponent component)'''
	    «FOR param : listParams»
		    «Slug.writeParameter(param,component)»
		«ENDFOR»
	'''
		
}
