package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.eJSL.Component

class ExtenionScriptGenerator {
	
	Extension ex
	String extName
	new(Extension ext, String name) {
		ex = ext
		extName = name
	}
	
	public def CharSequence generate()'''
	 <?php
	  «Slug.generateFileDoc(ex,true)»
	   
	 /**
	 * «extName»  script.
	 */
	 class «extName.toFirstUpper»InstallerScript
	 {
	   «genInstall()»
	   «genUnsinstall()»
	   «genUpsate()»
	   «genpreflight()»
	   «genPostflight()»
	 }
	'''
	
	def CharSequence genPostflight() '''
	/**
		 * method to run after an install/update/uninstall method
		 *
		 * @return void
		 */
		/**function postflight($type, $parent) 
		{
			// $parent is the class calling this method
			// $type is the type of change (install, update or discover_install)
			echo '<p>' . JText::_('«extName.toUpperCase»POSTFLIGHT_' . $type . '_TEXT') . '</p>';
		}*/ 
	'''
	
	def CharSequence genpreflight() '''
	/**
		 * method to run before an install/update/uninstall method
		 *
		 * @return void
		 */
		/**function preflight($type, $parent) 
		{
			// $parent is the class calling this method
			// $type is the type of change (install, update or discover_install)
			echo '<p>' . JText::_('«extName.toUpperCase»_PREFLIGHT_' . $type . '_TEXT') . '</p>';
		}*/
	'''
	
	def CharSequence genUpsate() '''
	/**
	 * method to update the component
	 *
	 * @return void
	 */
	function update($parent) 
	{
		
		echo '<p>' . JText::sprintf('«extName.toUpperCase»_UPDATE_TEXT',  $parent->get('manifest')->version) . '</p>';
	}
	'''
	def CharSequence genUnsinstall() '''
	/**
	 * method to uninstall the component
	 *
	 * @return void
	 */
	function uninstall($parent) 
	{
		echo '<p>' .JText::_('«extName.toUpperCase»_UNINSTALL_TEXT') . '</p>';
	}
	 
	'''
	
	def CharSequence genInstall() '''
	
	/**
		 * method to install the component
		 *
		 * @return void
		 */
		function install($parent) 
		{
			«IF ex instanceof Component»
			$parent->getParent()->setRedirectURL('index.php?option=«extName»');
			«ELSE»
			echo '<p>' .JText::_('«extName.toUpperCase»_INSTALL_TEXT') . '</p>';
			«ENDIF»
		}
	
	
	
	'''
	
}