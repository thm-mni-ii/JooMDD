package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaPageGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.ContextLink
import de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.Slug

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
	
	override generateLink(String sect, String compname) 
	'''«if (sect.isEmpty) '' else sect +"/"»'index.php?option=«Slug.nameExtensionBind("com",compname)»&view=«lk.target.name»' «genLinkOption(lk.linkparameters)»'''
	
	def CharSequence genLinkOption(EList<LinkParameter> params)
	'''«params.map[ lp | '''. '&«lp.name»=«IF lp.attvalue !== null»' . «valueF + lp.attvalue.name»«ELSE»«lp.value»'«ENDIF»''' ].join»'''
}
