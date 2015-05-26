/**
 */
package de.thm.icampus.ejsl.generator

import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.Component
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.eJSL.Section
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.Type
import de.thm.icampus.ejsl.eJSL.DatatypeReference
import de.thm.icampus.ejsl.eJSL.SimpleDatatypes
import de.thm.icampus.ejsl.eJSL.Reference
import java.util.HashSet
import java.util.LinkedList
import java.util.List
import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.DetailsPage
import de.thm.icampus.ejsl.eJSL.IndexPage

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Slug</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getSlug()
 * @model
 * @generated
 */
public class Slug  {
		/*
	 * Slugify an String and replace all german special characters
	 * e.g.: This is a String => this_is_a_string
	 */
	
	def static String slugify(String str) {
		var res = str
		res = res.replaceAll("[^\\pL\\d]+", "_")
		res = res.replaceAll("ä", "ae")
		res = res.replaceAll("ö", "ou")
		res = res.replaceAll("ü", "ue")
		res = res.replaceAll("ß", "ss")
		res = res.replaceAll("[^-\\w]+", '')
		res = res.toLowerCase()
		trim(res, "_".charAt(0))
	}
	
	def static String trim(String str, char c) {
		var a = 0
		var z = str.length
		
		while (a < z && str.charAt(a) == c) {
			a = a + 1
		}
		
		do {
			z = z - 1
		} while (z > a && str.charAt(z) == c); 
		
		str.substring(a, z+1)
	}
	
	/*
	 * Takes a string as 'com_social_network' and rebuilds it clean as into 'SocialNetwork.
	 * Needed for class names.
	 */
	def static String cleanExtensionName(String name) {
		val split = name.split("_")
		var res = new StringBuffer();
		var i = 0
		
		while ((i=i+1) < split.size) {
			res.append(split.get(i).toLowerCase.toFirstUpper)
		}
		
		return res.toString
	}
	def static String getTypeName(Type typ){
		var String result = "";
		switch typ{
			DatatypeReference :{
				var DatatypeReference temptyp = typ as DatatypeReference
				result = temptyp.type.name
			}
			SimpleDatatypes:{
				var SimpleDatatypes temptyp = typ as SimpleDatatypes
				result = temptyp.type.toString
			}
		}
		return result
	}
	/*
	 * Takes the name of an Extension and Prefix like (com ,name)and return com_name  .
	 * Needed for class names.
	 */
	def static String nameExtensionBind(String prefix, String name) {
		
		return prefix + "_" + name
	}
	
	def static BackendSection getBackendSectionViews(Component com)
	{
		for(Section sec: com.sections){
			
			switch sec{
				 BackendSection:{
				 	return sec
				 }
				
			}
		}
		return null ;
	}
	
	def static CharSequence generateEntytiesHiddenAttribute(Entity entity, List<Entity> toSchowEntities) {
		var StringBuffer buff = new StringBuffer()
		
		for(Attribute e : entity.attributes){
			
			if(getTypeName(e.htmltype).toLowerCase.compareTo("hidden") == 0){
				buff.append(inputHiddenFeldTemplate(e) + "\n");
			}
		}
		
		for(Attribute e : getAllAttributeOfreference(entity, toSchowEntities)){
			
			if(getTypeName(e.htmltype).toLowerCase.compareTo("hidden") == 0){
				buff.append(inputHiddenFeldTemplate(e) + "\n");
			}
		}
		
		return buff.toString
	}
	
	def static HashSet<Attribute> getAllAttributeOfreference(Entity ent, List<Entity> toSchowEntities) {
		var HashSet<Attribute> result  = new HashSet<Attribute>
		var LinkedList<Entity> tovisitEntity = new LinkedList <Entity>() 
		tovisitEntity.add(ent)
		for(Entity en: tovisitEntity){
			for(Reference re: en.references){
			if( toSchowEntities.contains(re.entity)){
				var Attribute refered = re.attributerefereced
				for(Attribute attr: re.entity.attributes){
					if(attr.name.equals(refered.name) && !result.contains(attr) && attr.name.equalsIgnoreCase("id")){
						
						result.add(attr)
					}
				}
				if(!tovisitEntity.contains(re.entity)){
					tovisitEntity.add(re.entity)
				}
			}
			
			}
		}
		return result
	}
	
	def static CharSequence inputHiddenFeldTemplate(Attribute attr) '''
	<input type="hidden" name="jform[«attr.name.toLowerCase»]" value="<?php echo $this->item->«attr.name.toLowerCase»; ?>" />
	'''
	
	def static CharSequence generateEntytiesInputAttribute(Entity entity, List<Entity> toSchowEntities) {
		var StringBuffer buff = new StringBuffer()
		
		for(Attribute e : entity.attributes){
			
			if(getTypeName(e.htmltype).toLowerCase.compareTo("hidden") != 0){
				buff.append(inputFeldTemplate(e) + "\n");
			}
		}
		
		for(Attribute e : getAllAttributeOfreference(entity, toSchowEntities)){
			
			if(getTypeName(e.htmltype).toLowerCase.compareTo("hidden") != 0){
				buff.append(inputFeldTemplate(e) + "\n");
			}
		}
		
		return buff.toString
	}
	
	def static CharSequence inputFeldTemplate(Attribute attr) '''
	<div class="control-group">
		<div class="control-label"><?php echo $this->form->getLabel('«attr.name.toLowerCase»'); ?></div>
		<div class="controls"><?php echo $this->form->getInput('«attr.name.toLowerCase»'); ?></div>
	</div>
	'''
	def static HashSet<DetailsPage>  getAllAttributeOfAComponente(Component c){
		var HashSet<DetailsPage> result = new HashSet<DetailsPage> ()
		for(Section sec : c.sections){
			for(Page dyn: sec.page){
				switch dyn{
					DetailsPage :{
					result.add(dyn);
					}
				}
			}
		}
		return result
	}
	def static CharSequence generateFileDoc( Component component, boolean denied)'''
	
		/**
		* @version v0.0.1
		* @category Joomla component
		* @name «component.name»View
		«FOR author : component.manifest.authors»
			* @author «author.name», <«author.authoremail»>
		«ENDFOR»
		* @copyright «component.manifest.copyright»
		* @license «component.manifest.license»
		*/
		«IF denied»
		defined('_JEXEC') or die('Restricted access');
		«ENDIF»
		
	'''
	
	def static String generateKeysName(Component com, String name){
		return "COM_" + com.name.toUpperCase + "_" + name.toUpperCase
	}
	
	def static String generateKeysNamePage(Component com, Page page ,String name){
		return "COM_" + com.name.toUpperCase + "_FIELD_" + page.name.toUpperCase + "_" + name.toUpperCase
	}
	
	def static DetailsPage getPageForDetails(IndexPage inpage, Component com) {
		for(Section tempSec: com.sections){
			if(tempSec.page.contains(inpage)){
				for(Page pg : tempSec.page ){
					switch(pg){
						DetailsPage :{
							if(pg.entities.contains(inpage.entities.get(0)))
							return pg;
						}
					}
				}
			}
		}
		return null
	}
	
	def static IndexPage  getPageForAll(DetailsPage inpage, Component com) {
		for(Section tempSec: com.sections){
			if(tempSec.page.contains(inpage)){
				for(Page pg : tempSec.page ){
					switch(pg){
						IndexPage :{
							if(pg.entities.contains(inpage.entities.get(0)))
							return pg;
						}
					}
				}
			}
		}
		return null
	}
	
} // Slug
