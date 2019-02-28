package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaPageGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.ContextLink
import de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug

/**
 * This class contains the code templates for context links..
 * 
 * @author Dennis Priefer, Dieudonne Timma Meyatchie
 */
public class ContextLinkGenerator extends AbstractLinkGenerator {
	ContextLink lk
	String valueF
	
	new(ContextLink link, String valueFeatures) {
		lk = link
		valueF= valueFeatures
	}
	
	override generateLink(String sect, String compname) '''
	«if (sect.isEmpty) '' else sect +"/"»'index.php?option=«Slug.nameExtensionBind("com",compname).toLowerCase»&view=«lk.target.name.toLowerCase»' «genLinkOption(lk.linkparameters)»'''
	
	def CharSequence genLinkOption(EList<LinkParameter> params)'''
	    «FOR LinkParameter lp : params»
	        . '&«lp.name»=«IF lp.attvalue !== null»' . «valueF + lp.attvalue.name.toLowerCase»«ELSE»«lp.value»'«ENDIF»
	    «ENDFOR»
	'''
	
} // ContextLink
