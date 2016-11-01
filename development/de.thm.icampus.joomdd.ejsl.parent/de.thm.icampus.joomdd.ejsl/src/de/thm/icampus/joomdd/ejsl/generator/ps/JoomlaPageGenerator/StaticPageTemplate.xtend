/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedStaticPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IFileSystemAccess2

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Static Page</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getStaticPage()
 * @model
 * @generated
 */
public class StaticPageTemplate extends AbstractPageGenerator {
	
	ExtendedStaticPage staticpage
	ExtendedComponent comp
	String sect

	
	new (ExtendedStaticPage page, ExtendedComponent component, String section, String path,IFileSystemAccess2 access ){
		staticpage = page
		comp = component
		sect = section
		this.path = path + "/" +path + staticpage.name
		this.fsa = access
	}
	
	def void generate(){
		generateJoomlaDirectory(path)
		generateJoomlaDirectory(path + "/tmpl")
		
		generateFile(path+ "/view.php", generateView())
		generateFile(path+ "/tmpl/default.php", staticpage.HTMLBody)
		if(!sect.equalsIgnoreCase("admin")){
			generateFile(path+ "/tmpl/default.xml", xmlSiteTemplateContent(staticpage.name, staticpage, comp) )
		}
		
	}
	
	
	def CharSequence generateView() '''
	«generateFileDoc(staticpage.instance, comp,true )»
	/**
	 * HTML View class for the «comp.name.toFirstUpper» Component
	 *
	 */
	class «comp.name.toFirstUpper»View«staticpage.name.toFirstUpper» extends JViewLegacy
	{
		/**
		 * Display the «staticpage.name.toFirstUpper» view
		 *
		 * @param   string  $tpl  The name of the template file to parse; automatically searches through the template paths.
		 *
		 * @return  void
		 */
		function display($tpl = null)
		{
	 
			// Display the view
			parent::display($tpl);
		}
	}
	'''
	
	
	
	
} // StaticPage
