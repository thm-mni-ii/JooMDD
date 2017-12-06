package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaEntityGenerator

import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaEntityGenerator.FieldsGenerator
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug

/**
 * This class contains the templates for field file loaders.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
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
		«Slug.generateFileDoc(com)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("Text", "ComponentHelper", "Uri", "FormField", "Factory"))»
		
		/**
		 * This class contain a input field to load a document or image.
		 * The parameter for configuration of the path, type, or format are
		 * in the manifest file.
		 */
		class JFormFieldFileloader extends FormField
		{
		    protected function getInput()
		    {
		        $html = array();
		        $params = ComponentHelper::getParams('«Slug.nameExtensionBind("com", com.name).toLowerCase»');
		        $path = $params->get($this->getAttribute('path'));
		        $format = $params->get($this->getAttribute('accept_format'));
		        $file='';
		        if(!empty($this->value))
		        {
		            $file=  Uri::root()  .$path . '/'. $this->value;
		        }
		
		        $document = Factory::getDocument();
		        $iconpath = Uri::root() . 'media/media/images/mime-icon-32/';
		        $document->addScript( Uri::root() . '/media/«Slug.nameExtensionBind("com", com.name).toLowerCase»/js/bootsnip.js');
		        $document->addStyleSheet( Uri::root() . '/media/«Slug.nameExtensionBind("com", com.name).toLowerCase»/css/bootsnip.css');
		        $document->addStyleSheet( Uri::root() . 'media/jui/css/bootstrap.min.css');
		        $html []="<div class='img-picker' fieldtype='file' name='$this->name' value='$this->value' accept='$format' file='$file' iconpath='$iconpath' showLabel='".Text::_("«Slug.nameExtensionBind("com", com.name).toUpperCase»_ADD")."' 
		        deleteLabel='".Text::_("«Slug.nameExtensionBind("com", com.name).toUpperCase»_DELETE")."'><div id='add'></div><div id='preview'></div></div>";
		        return implode($html);
		    }
		}
	'''
	/**
	 * Return the code for the Field imageloader.php, to load document in a page
	 */
	def CharSequence generateImageloader()'''
		<?php
		«Slug.generateFileDoc(com)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("Text", "ComponentHelper", "Uri", "FormField", "Factory"))»
		
		/**
		 * This class contain a input field to load a document or image.
		 * The parameter for configuration of the path, type, or format are
		 * in the manifest file.
		 */
		class JFormFieldImageloader extends FormField
		{
		    protected function getInput()
		    {
		        $html = array();
		        $params = ComponentHelper::getParams('«Slug.nameExtensionBind("com", com.name).toLowerCase»');
		        $path = $params->get($this->getAttribute('path'));
		       
		        $file='';
		        if(!empty($this->value))
		        {
		            $file=  Uri::root()  .$path . '/'. $this->value;
		        }
		
		        $document = Factory::getDocument();
		        $document->addScript( Uri::root() . '/media/«Slug.nameExtensionBind("com", com.name).toLowerCase»/js/bootsnip.js');
		       	$document->addStyleSheet( Uri::root() . '/media/«Slug.nameExtensionBind("com", com.name).toLowerCase»/css/bootsnip.css');
		       	$document->addStyleSheet( Uri::root() . 'media/jui/css/bootstrap.min.css');
		        $input = Factory::getApplication()->input;
		        $html []="<div class='img-picker' fieldtype='image' iconpath=' '  name='$this->name' value='$this->value' accept='image/*' file='$file' showLabel='".Text::_("«Slug.nameExtensionBind("com", com.name).toUpperCase»_ADD")."' 
		        deleteLabel='".Text::_("«Slug.nameExtensionBind("com", com.name).toUpperCase»_DELETE")."'><div id='add'></div><div id='preview'></div></div>";
		        return implode($html);
		    }
		}
	'''
	
}