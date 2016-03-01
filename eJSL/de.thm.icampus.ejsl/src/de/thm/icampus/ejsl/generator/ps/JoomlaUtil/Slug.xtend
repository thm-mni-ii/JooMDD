/**
 */
package de.thm.icampus.ejsl.generator.ps.JoomlaUtil

import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.ContextLink
import de.thm.icampus.ejsl.eJSL.DatatypeReference
import de.thm.icampus.ejsl.eJSL.DetailsPage
import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.ExternalLink
import de.thm.icampus.ejsl.eJSL.IndexPage
import de.thm.icampus.ejsl.eJSL.InternalLink
import de.thm.icampus.ejsl.eJSL.Link
import de.thm.icampus.ejsl.eJSL.Module
import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.PageReference
import de.thm.icampus.ejsl.eJSL.Reference
import de.thm.icampus.ejsl.eJSL.Section
import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator.LinkGeneratorClient
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.HashSet
import java.util.LinkedList
import java.util.List
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute

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
	 public static Calendar cal = new GregorianCalendar();
	
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
	def static String getTypeName(ExtendedAttribute typ){
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
	<input type="hidden" name="jform[«Slug.slugify(attr.name).toLowerCase»]" value="<?php echo $this->item->«Slug.slugify(attr.name).toLowerCase»; ?>" />
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
			for(PageReference dyn: sec.pageRef){
				switch dyn.page{
					DetailsPage :{
						var DetailsPage pg = dyn.page as DetailsPage
					result.add(pg);
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
		return "COM_" + com.name.toUpperCase + "_" + Slug.slugify(name).toUpperCase
	}
	
	def static String generateKeysNamePage(Component com, Page page ,String name){
		return "COM_" + com.name.toUpperCase + "_FIELD_" + Slug.slugify(page.name).toUpperCase + "_" + Slug.slugify(name).toUpperCase
	}
	
	def static DetailsPage getPageForDetails(ExtendedDynamicPage inpage, ExtendedComponent com) {
		
		for(Link lk: inpage.links){
			switch lk {
		   ContextLink:{
				var InternalLink lkin = lk as InternalLink
				if(lkin.target instanceof DetailsPage)
				  return lkin.target as DetailsPage
			}
			 InternalLink :{
				var InternalLink lkin = lk as InternalLink
				if(lkin.target instanceof DetailsPage)
				  return lkin.target as DetailsPage
			}
			}
		}
		return null
	}
	
	def static IndexPage  getPageForAll(ExtendedDynamicPage inpage, ExtendedComponent com) {
		for(Link lk: inpage.links){
			switch lk{
				 ContextLink:{
					var InternalLink lkin = lk as InternalLink
					if(lkin.target instanceof IndexPage)
					  return lkin.target as IndexPage
				}
				 InternalLink:{
					var InternalLink lkin = lk as InternalLink
					if(lkin.target instanceof IndexPage)
					  return lkin.target as IndexPage
				}
			}
		}
		return null
	}
	def static CharSequence generateFileDoc( Module module, boolean denied)
	'''
	
	/**
	 * @version     CVS: 1.0
	 * @category    Joomla module
	 * @package     Packagename
	 * @subpackage  Subpackagename
	 * @name        «module.name»
	 * @description 
	 «FOR author :module.manifest.authors»
	 * @author      «author.name», <«author.authoremail»>
	 «ENDFOR»
	 * @copyright   «cal.get(Calendar.YEAR)»  «module.manifest.copyright»
	 * @license     «module.manifest.license»
	 * @link        www.link.com
	 */
	'''
	
	def static String getSectioName(Section  reference) {
		if(reference instanceof BackendSection)
		return 'admin'
		
		return ''
		
	}
	
	def static CharSequence databaseName(String componentName, String entityName) {
		return "#__" + componentName.toLowerCase + "_" + entityName.toLowerCase
	}
	
	def static Boolean isAttributeLinked(ExtendedAttribute attr, DynamicPage page) {
		for(Link ref: page.links){
			if(ref.linkedAttribute != null){
			if(ref.linkedAttribute.name.equalsIgnoreCase(attr.name))
			return true
			
			}
		}
		return false
	}
	
	def static CharSequence linkOfAttribut(ExtendedAttribute attribute, ExtendedDynamicPage  page, String compname, String valuefeatures) '''
	«FOR Link lk: page.links»
	«IF lk.linkedAttribute != null»
	«IF lk.linkedAttribute.name.equalsIgnoreCase(attribute.name)»
	«ENDIF»
	«switch lk{
		ExternalLink :{
			'''«(new LinkGeneratorClient(lk, '', compname, valuefeatures )).generateLink»'''
			}
		 InternalLink:{
		 if((lk as InternalLink).target instanceof DetailsPage){
		   if((page as DynamicPage).entities.get(0).name.equals((lk.target as DynamicPage).entities.get(0).name)){
		   	
		    '''«(new LinkGeneratorClient(lk, '', compname, valuefeatures )).generateLink» . '&id='.(int) $item->id'''
		    
		   	}		   	   
		    else{
		 	'''«(new LinkGeneratorClient(lk, '', compname, valuefeatures )).generateLink» . '&id='.(int) $this->getModel()->getIdOfReferenceItem("«(lk as InternalLink).name.toLowerCase»",$item->«attribute.name.toLowerCase»)'''
		 	  }
		 	}else{
		 		'''«(new LinkGeneratorClient(lk, '', compname, valuefeatures )).generateLink» . '&filter.search='. $item->«attribute.name.toLowerCase»'''
		 		}
		 	}
		}»
	
	«ENDIF»
	«ENDFOR»
	'''
	
	def static Boolean isLinkedAttributeReference(Attribute attribute, DynamicPage page) {
		
		for(Entity e: page.entities){
			for(Reference ref: e.references){
				if(ref.attribute.equals(attribute))
				return true
			}
		}
		return false
	}
	
	def static Reference searchLinkedAttributeReference(Attribute attribute, DynamicPage page) {
		for(Entity e: page.entities){
			for(Reference ref: e.references){
				if(ref.attribute.equals(attribute))
				return ref
			}
		}
		return null
	}
	 static def CharSequence genLinkedInfo(DynamicPage page, Component com)'''
 	private  $entitiesRef = array(
 	«FOR Link linkItem: page.links»
 	«switch linkItem {
 		InternalLink :{
 			if(isLinkedAttributeReference(linkItem.linkedAttribute, page)){
 				var Reference ref = Slug.searchLinkedAttributeReference(linkItem.linkedAttribute, page);
 				'''"«linkItem.name.toLowerCase»" => array("db"=> "#__«com.name.toLowerCase»_«ref.entity.name.toLowerCase»","refattr" => array(«Slug.transformAttributeListInString('''"''',"",ref.attributerefereced,",")»)"),'''	
 			}				
 		}	
 	}»«ENDFOR»null);
 '''
	static def CharSequence transformAttributeListInString(EList<Attribute>attributes, String separeSign){
		var StringBuffer result = new StringBuffer()
		for(attr: attributes){
			if(attr != attributes.last){
			result.append(attr.name.toLowerCase + separeSign)
			}else{
				result.append(attr.name)
			}
		}
		return result.toString

	}
	
	static def CharSequence transformAttributeListInString(String postWord, EList<Attribute>attributes, String separeSign){
		var StringBuffer result = new StringBuffer()
		for(attr: attributes){
			if(attr != attributes.last){
			result.append(postWord + attr.name.toLowerCase + separeSign)
			}else{
				result.append(postWord+attr.name)
			}
		}
		return result.toString

	}
	static def CharSequence transformAttributeListInString(String quotationMark, String postWord, EList<Attribute>attributes, String separeSign){
		var StringBuffer result = new StringBuffer()
		for(attr: attributes){
			if(attr != attributes.last){
			result.append(quotationMark+postWord + attr.name.toLowerCase+quotationMark + separeSign)
			}else{
				result.append(quotationMark+postWord+attr.name+quotationMark)
			}
		}
		return result.toString

	}
 
	
} // Slug
