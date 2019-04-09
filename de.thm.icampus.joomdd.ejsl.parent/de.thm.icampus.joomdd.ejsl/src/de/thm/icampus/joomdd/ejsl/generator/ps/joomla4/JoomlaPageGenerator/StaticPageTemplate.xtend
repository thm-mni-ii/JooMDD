package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedStaticPage
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.Slug

/**
 * This class contains the templates to generate the necessary code for static views.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 * 
 */
class StaticPageTemplate extends AbstractPageGenerator {
	
	ExtendedStaticPage staticpage
	ExtendedComponent comp
	String sect
	String namespaceSection
	
	new (ExtendedStaticPage page, ExtendedComponent component, String section, String path,IFileSystemAccess2 access ){
		staticpage = page
		comp = component
		this.sect = section
		this.path = path + "/" +path + staticpage.name
		this.fsa = access
		this.namespaceSection = if (this.sect.equals("admin")) "Administrator" else "Site"
	}
	
	def void generate() {
		generateFile(path+ "/view.php", generateView())
		generateFile(path+ "/tmpl/default.php", staticpage.HTMLBody)
		if(!sect.equalsIgnoreCase("admin")) {
			generateFile(path+ "/tmpl/default.xml", xmlSiteTemplateContent(staticpage.name, staticpage, comp))
		}
	}
	
	
	def CharSequence generateView() '''
	    «generateFileDoc(staticpage.instance, comp)»
	    
	    «Slug.generateNamespace(comp.name, this.namespaceSection, "View\\" + staticpage.name.toFirstUpper)»

	    «Slug.generateRestrictedAccess()»
	    
	    «Slug.generateUses(newArrayList("ViewLegacy", "Text"))»
	    
	    /**
	     * HTML View class for the «comp.name.toFirstUpper» Component
	     *
	     */
	    class HtmlView extends BaseHtmlView
	    {
	        /**
	         * Display the «staticpage.name.toFirstUpper» view
	         *
	         * @param   string  $tpl  The name of the template file to parse; automatically searches through the template paths.
	         *
	         * @return  void
	         */
	        public function display($tpl = null)
	        {
	            // Display the view
	            parent::display($tpl);
	        }
	    }
	'''
}
