package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator

import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator.FieldsGenerator
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug

class FieldsFileloaderGenerator extends FieldsGenerator {
	
	new(ExtendedReference ref, ExtendedComponent component, ExtendedEntity from) {
		super(ref, component, from)
	}
	
	new(ExtendedComponent component) {
		super(component)
	}
	
	/**
	 * Return the code for the Field fileloader.php, to load document in a page
	 */
	 def CharSequence generateFileloader()'''
		<?php
		«Slug.generateFileDoc(com,true)»
		
		/**
		 * This class contain a input field to load a document or image.
		 * The parameter for configuration of the path, type, or format are
		 * in the manifest file.
		 */
		class JFormFieldFileloader extends JFormField
		{
		    protected function getInput()
		    {
		        $html = array();
		        $params = JComponentHelper::getParams('«Slug.nameExtensionBind("com", com.name).toLowerCase»');
		        $path = $params->get($this->getAttribute('path'));
		        $format = $params->get($this->getAttribute('accept_format'));
		        $file='';
		        if(!empty($this->value))
		        {
		            $file=  JURI::root()  .$path . '/'. $this->value;
		        }
		
		        $document = JFactory::getDocument();
		        $iconpath = JURI::root() . 'media/media/images/mime-icon-32/';
		        $document->addScript( JURI::root() . '/media/«Slug.nameExtensionBind("com", com.name).toLowerCase»/js/bootsnip.js');
		        $document->addStyleSheet( JURI::root() . '/media/«Slug.nameExtensionBind("com", com.name).toLowerCase»/css/bootsnip.css');
		        $document->addStyleSheet( JURI::root() . 'media/jui/css/bootstrap.min.css');
		        $html []="<div class='img-picker' fieldtype='file' name='$this->name' accept='$format' file='$file' iconpath='$iconpath' showLabel='".JText::_("«Slug.nameExtensionBind("com", com.name).toUpperCase»_ADD")."' 
		        deleteLabel='".JText::_("«Slug.nameExtensionBind("com", com.name).toUpperCase»_DELETE")."'><div id='add'></div><div id='preview'></div></div>";
		        return implode($html);
		    }
		}
	'''
	/**
	 * Return the code for the Field imageloader.php, to load document in a page
	 */
	def CharSequence generateImageloader()'''
		<?php
		«Slug.generateFileDoc(com,true)»
		
		/**
		 * This class contain a input field to load a document or image.
		 * The parameter for configuration of the path, type, or format are
		 * in the manifest file.
		 */
		class JFormFieldImageloader extends JFormField
		{
		    protected function getInput()
		    {
		        $html = array();
		        $params = JComponentHelper::getParams('«Slug.nameExtensionBind("com", com.name).toLowerCase»');
		        $path = $params->get($this->getAttribute('path'));
		       
		        $file='';
		        if(!empty($this->value))
		        {
		            $file=  JURI::root()  .$path . '/'. $this->value;
		        }
		
		        $document = JFactory::getDocument();
		        $document->addScript( JURI::root() . '/media/«Slug.nameExtensionBind("com", com.name).toLowerCase»/js/bootsnip.js');
		       	$document->addStyleSheet( JURI::root() . '/media/«Slug.nameExtensionBind("com", com.name).toLowerCase»/css/bootsnip.css');
		       	$document->addStyleSheet( JURI::root() . 'media/jui/css/bootstrap.min.css');
		        $input = JFactory::getApplication()->input;
		        $html []="<div class='img-picker' fieldtype='image' iconpath=' '  name='$this->name'  accept='image/*' file='$file' showLabel='".JText::_("«Slug.nameExtensionBind("com", com.name).toUpperCase»_ADD")."' 
		        deleteLabel='".JText::_("«Slug.nameExtensionBind("com", com.name).toUpperCase»_DELETE")."'><div id='add'></div><div id='preview'></div></div>";
		        return implode($html);
		    }
		}
	'''
	
}