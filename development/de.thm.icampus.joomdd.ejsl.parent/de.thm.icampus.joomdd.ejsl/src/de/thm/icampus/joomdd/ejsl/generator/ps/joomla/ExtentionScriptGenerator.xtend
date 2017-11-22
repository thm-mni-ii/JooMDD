package de.thm.icampus.joomdd.ejsl.generator.ps.joomla

import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedLibrary
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage

/**
 * This class generates the install script files for Joomla extensions.
 * 
 * @author Dennis Priefer, Dieudonne Timma Meyatchie
 */
class ExtentionScriptGenerator {
	
	Extension ex
	ExtendedComponent com
	String extName
	new(ExtendedComponent extensions, String name) {
		ex = extensions
		com = extensions
		extName = name
	}
	new(ExtendedModule mod, String extensionName){
		ex = mod
	}
	new(ExtendedLibrary lib, String extensionName){
		ex = lib
	}
	new(Extension pack, String extensionName){
		ex = pack
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
		    «genSetCompoenentParamater()»
		    «genUnsinstall()»
		    «genUpsate()»
		    «genpreflight()»
		    «genPostflight()»
		  
		}
	'''
	
	def CharSequence genSetCompoenentParamater() '''
		function setComponentParameter()
		{
		    // Load the current component params.
		    $params = JComponentHelper::getParams('«com.extensionName»');
		    // Set new value of the param(s)
		    $params->set('upload_maxsize', 10);
		    $params->set('accept_format', "bmp,csv,doc,gif,ico,jpg,jpeg,odg,odp,ods,odt,pdf,png,ppt,swf,txt,xcf,xls,BMP,CSV,DOC,GIF,ICO,JPG,JPEG,ODG,ODP,ODS,ODT,PDF,PNG,PPT,SWF,TXT,XCF,XLS");
		    «FOR ExtendedPage pag: com.allExtendedPage.filter[t | t.extendedDynamicPageInstance!==null && 
		        t.extendedDynamicPageInstance.isDetailsPage == true && t.extendedDynamicPageInstance.haveFiletoLoad]»
		    $params->set('«pag.extendedDynamicPageInstance.name.toLowerCase»_image_path', 'media/«com.extensionName.toLowerCase»/«pag.extendedDynamicPageInstance.name.toLowerCase»/images');
		    $params->set('«pag.extendedDynamicPageInstance.name.toLowerCase»_file_path', 'media/«com.extensionName.toLowerCase»/«pag.extendedDynamicPageInstance.name.toLowerCase»/files');
		    «ENDFOR»

		    // Save the parameters
		    $componentid = JComponentHelper::getComponent('«com.extensionName»')->id;
		    $table = JTable::getInstance('extension');
		    $table->load($componentid);
		    $table->bind(array('params' => $params->toString()));
		
		    // check for error
		    if (!$table->check())
		    {
		        return false;
		    }
		    // Save to database
		    if (!$table->store())
		    {
		        return false;
		    }
		    return true;
		}
	'''
	
	def CharSequence genPostflight() '''
		/**
		 * method to run after an install/update/uninstall method
		 *
		 * @return void
		 */
		
		/**
		function postflight($type, $parent)
		{
		    // $parent is the class calling this method
			// $type is the type of change (install, update or discover_install)
			echo '<p>' . JText::_('«extName.toUpperCase»POSTFLIGHT_' . $type . '_TEXT') . '</p>';
		}
		*/ 
	'''
	
	def CharSequence genpreflight() '''
		/**
		 * method to run before an install/update/uninstall method
		 *
		 * @return void
		 */
		
		/**
		function preflight($type, $parent)
		{
		    // $parent is the class calling this method
			// $type is the type of change (install, update or discover_install)
			echo '<p>' . JText::_('«extName.toUpperCase»_PREFLIGHT_' . $type . '_TEXT') . '</p>';
		}
		*/
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
		    if(!$this->setComponentParameter())
		    {
		        echo '<p>' .JText::_('«extName.toUpperCase»_INSTALL_NO_PARAMETER_INSTALLED') . '</p>';
		    }
		    $parent->getParent()->setRedirectURL('index.php?option=«extName»');
		    «ELSE»
		    echo '<p>' .JText::_('«extName.toUpperCase»_INSTALL_TEXT') . '</p>';
		    «ENDIF»
		}
	'''
}
