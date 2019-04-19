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
	String currentAttributeName
	
	new(ContextLink link, String valueFeatures, String currentAttributeName) {
		lk = link
		valueF= valueFeatures
		this.currentAttributeName = currentAttributeName
	}
	
	override generateLink(String sect, String compname) 
	'''«if (sect.isEmpty) '' else sect +"/"»'index.php?option=«Slug.nameExtensionBind("com",compname)»&view=«lk.target.name»' «genLinkOption(lk.linkparameters)»'''
	
	def CharSequence genLinkOption(EList<LinkParameter> params)
	{
    	return '''
    	   «params.map[ lp | 
    	       var parameterValue = ''''''
    	       
    	       if (lp.attvalue !== null) {
    	           var lpAttValueName = lp.attvalue.name
    	           if (lpAttValueName.equals(this.currentAttributeName)) {
    	               // Assume we are in a multi value context.
    	               // The variable name "$value" must correspond to the variable in the foreach statement.
    	               parameterValue = '''$value'''
    	           } else {
    	               parameterValue = valueF + lp.attvalue.name
    	           }
    	           parameterValue = '''' . «parameterValue»'''
    	       } else {
    	           parameterValue = lp.value
    	       }
        	   
        	   return '''. '&«lp.name»=«parameterValue»'''
    	   ].join()»
    	   '''
    }
}
