package generator

import com.google.common.base.Joiner
import com.google.common.io.Files
import com.google.inject.Inject
import de.thm.icampus.joomdd.ejsl.eJSL.BackendSection
import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.ContextLink
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel
import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.eJSL.FrontendSection
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.eJSL.InternalLink
import de.thm.icampus.joomdd.ejsl.eJSL.Link
import java.io.File
import java.util.Scanner
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import de.thm.icampus.joomdd.ejsl.tests.EJSLInjectorProvider
import Util.TemplateLoader

@RunWith(XtextRunner)
@InjectWith(EJSLInjectorProvider)

class EJSLGeneratorTest {
	@Inject
	ParseHelper<EJSLModel> parseHelper;
	
	public val models = TemplateLoader.getTemplateFiles()
	
	
	
}
