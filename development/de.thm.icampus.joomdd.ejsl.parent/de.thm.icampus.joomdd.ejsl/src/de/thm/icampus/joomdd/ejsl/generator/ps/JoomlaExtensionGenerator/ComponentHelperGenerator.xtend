package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference

/**
 * This Class contains the Template for the Helper class of a component
 * @author Dieudonne Timma<dieudonnetimma@yahoo.fr>
 * @version 1.0
 */
class ComponentHelperGenerator extends AbstractExtensionGenerator{
//	
	
	ExtendedComponent extendeComp
	new(ExtendedComponent component) {
	
		extendeComp = component
	}
 
    override generate() '''
    <?php
         «Slug.generateFileDoc(extendeComp,true)»
   
      /**
         * «extendeComp.name.toUpperCase»  helper.
         */
        class «extendeComp.name.toFirstUpper»Helper {
         
         «genAddMenu»
         «genGetAction»
         «genUploadFile»

   	  }
    '''
    /**
     * Generate the AddSubmenu Methode for the Backenend Menu
     * 
     */
    private def CharSequence genAddMenu()'''
    /**
     * Configure the Linkbar.
     */
        public static function addSubmenu($vName = '') {
    	«FOR ExtendedPageReference pg : extendeComp.backEndExtendedPagerefence.filter[t| t.extendedPage.extendedDynamicPageInstance != null && !t.extendedPage.extendedDynamicPageInstance.isDetailsPage]»
    	
    		JHtmlSidebar::addEntry(
    		
    		JText::_('«Slug.nameExtensionBind("com",extendeComp.name).toUpperCase»_TITLE_«pg.extendedPage.name.toUpperCase»'),
    		'index.php?option=«Slug.nameExtensionBind("com",extendeComp.name).toLowerCase»&view=«pg.extendedPage.name.toLowerCase»',
    		$vName == '«pg.extendedPage.name.toLowerCase»'
    		);
    	     		
    	  
        «ENDFOR»
    
         }
    '''
    /**
     * Generate the Template to list the actions that can be performed.
     */
 	private def genGetAction()'''
	 /**
	          * Gets a list of the actions that can be performed.
	          *
	          * @return	JObject
	          * @since	1.6
	          */
	         public static function getActions() {
	             $user = JFactory::getUser();
	             $result = new JObject;
	     
	             $assetName = '«Slug::nameExtensionBind('com', extendeComp.name)»';
	     
	             $actions = array(
	                 'core.admin', 'core.manage', 'core.create', 'core.edit', 'core.edit.own', 'core.edit.state', 'core.delete'
	             );
	     
	             foreach ($actions as $action) {
	                 $result->set($action, $user->authorise($action, $assetName));
	             }
	     
	             return $result;
	         }
	     
	 '''
	 /**
	  *	Content the Template to save a File in Server
	  */
    private def genUploadFile()'''
    	/**
    	 * Save a file in Server
    	 * @param  $file    Array   content the informtion of a File to upload
    	 * @param  $target  String  content the path of Directory
    	 * @return	boolean or String
    	 */
    	public static function uploadFiles($file, $target) {
    		$file['name'] = JFile::makeSafe($file['name']);
    		$file['name'] = str_replace(' ', '_', $file['name']);
    		$file['filepath'] = JPath::clean(implode(DIRECTORY_SEPARATOR, array(JPATH_ROOT, $target, $file['name'])));
    		if (JFile::exists($file['filepath'])) {
    			// A file with this name already exists
    			// die( JText::_('COM_EXAMPLESHOP_ERROR_FILE_EXISTS'));
    			$index =1;
    			$file["name"] = $index."_".$file["name"];
    			$file['filepath'] = JPath::clean(implode(DIRECTORY_SEPARATOR, array(JPATH_ROOT, $target, $file['name'])));
    			while(JFile::exists($file['filepath'])){
    				$index = $index +1;
    				$file["name"] = $index."_".$file["name"];
    				$file['filepath'] = JPath::clean(implode(DIRECTORY_SEPARATOR, array(JPATH_ROOT, $target, $file['name'])));
    
    			}
    
    		}
    	
    		$object_file = new JObject($file);
    
    		if (!JFile::upload($object_file->tmp_name, $object_file->filepath))
    		{
    			return false;
    		}
    		return $file['name'];
    
    	}
    '''
}