/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.BackendSection
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.ContextLink
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.ExternalLink
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.eJSL.InternalLink
import de.thm.icampus.joomdd.ejsl.eJSL.Link
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.eJSL.PageReference
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.eJSL.Section
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.LinkGeneratorClient
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.HashSet
import java.util.LinkedList
import java.util.List
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.eJSL.SectionKinds
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.eJSL.MethodParameter
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes
import de.thm.icampus.joomdd.ejsl.eJSL.Type
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedAttributeImpl

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
	def static String getTypeName(String type){
		var String result =""
		switch type{
			case "Integer":{
				result='''type="number" min="0"  '''
			} 
			case "Yes_No_Buttons":{
				result='''Yes_No_Buttons'''
			}
			 case "Textarea":{
			 	result='''type="textarea" rows="10" cols="5" '''
			 }
			 case "Text_Field":{
			 	result='''type="text" '''
			 	}
			 case "Datepicker":{
			 	result='''type="calendar" '''
			 } 
			 case "Imagepicker":{
			 	result='''Imagepicker '''
			 } 
			 case "Filepicker":{
			 	result='''Filepicker'''
			 } 
			 case "Text_Field_NE":{
			 	result='''type="text" '''
			 } 
			 case "Editor":{
			 	result='''type="editor" '''
			 }
			 case "Select":{
			 	result='''select'''
			 }
			 case "Multiselect":{
			 	result='''multiselect'''
			 }
			 case "Checkbox":{
			 	result='''checkbox'''
			 }
			 case "Radiobutton":{
			 	result='''radiobutton'''
			 }
			 case "hidden":{
			 	result='''hidden '''
			 }
		}
		return result
	}
	def static String getTypeName(String type, ExtendedAttribute attr){
		var String myType = Slug.getTypeName(type)
		
		return myType
	}
	def static String getTypeName(Type typ){
		var String result = "";
		switch typ{
			DatatypeReference :{
				var DatatypeReference temptyp = typ as DatatypeReference
				result = temptyp.type.name
			}
			StandardTypes:{
				var StandardTypes temptyp = typ as StandardTypes
				result = getTypeName(temptyp.type.getName())
			}
		}
		return result
	}
	def static String getTypeName(ExtendedParameter typ){
			
		return getTypeName(typ.generatorType)
	}
	/*
	 * Takes the name of an Extension and Prefix like (com ,name)and return com_name  .
	 * Needed for class names.
	 */
	def static String nameExtensionBind(String prefix, String name) {
		
		
		
		return prefix + "_" + slugify(name)
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
	
	def static CharSequence generateEntytiesInputAttribute(EList<ExtendedDetailPageField> fields, ExtendedEntity entity) {
		var StringBuffer buff = new StringBuffer()
		var notShow = newArrayList("state","created_by","asset_id","ordering","checked_out_time","checked_out", "published", "params")
		notShow.add(entity.primaryKey.name)
		
		
		
		for(ExtendedDetailPageField fielditem: fields){
			
			if(!notShow.contains(fielditem.extendedAttribute.name)){
				buff.append(inputFeldTemplate(fielditem.extendedAttribute))
				notShow.add(fielditem.extendedAttribute.name)
			}
			
		}
		for(ExtendedAttribute attr: entity.extendedAttributeList){
			if(!notShow.contains(attr.name)){
				buff.append(inputHiddenFeldTemplate(attr))
			}
		}
		
		
		
		return buff.toString
	}
	
	
	
	def static CharSequence inputHiddenFeldTemplate(ExtendedAttribute attr) '''
	<div class="controls"><?php echo $this->form->getInput('«attr.name.toLowerCase»'); ?></div>
	'''
	
	
	
	def static CharSequence inputFeldTemplate(ExtendedAttribute attr) '''
	<div class="control-group">
		<div class="control-label"><?php echo $this->form->getLabel('«attr.name.toLowerCase»'); ?></div>
		<div class="controls"><?php echo $this->form->getInput('«attr.name.toLowerCase»'); ?></div>
	</div>
	
	'''

	def static CharSequence generateFileDoc( Component component, boolean denied)'''
	
		/**
		* @version v0.0.1
		* @category Joomla component
		* @name «component.name»View
		«IF component.manifest != null»
		«FOR author : component.manifest.authors»
			* @author «author.name», <«author.authoremail»>
		«ENDFOR»
		* @copyright «component.manifest.copyright»
		* @license «component.manifest.license»
		«ENDIF»
		*/
		«IF denied»
		defined('_JEXEC') or die('Restricted access');
		«ENDIF»
		
	'''
	
	def static String generateKeysName(Component com, String name){
		if(name!=null)
		return "COM_" + com.name.toUpperCase + "_FIELD_" + Slug.slugify(name).toUpperCase
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
	def static String getSectioName(SectionKinds  reference) {
		if(reference.getName.equalsIgnoreCase("backend"))
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
	
	«switch lk{
		ExternalLink :{
			'''«(new LinkGeneratorClient(lk, '', compname, valuefeatures )).generateLink»'''
			}
		 InternalLink:{
		 if((lk as InternalLink).target instanceof DetailsPage){
		   if((page.instance as DynamicPage).entities.get(0).name.equals((lk.target as DynamicPage).entities.get(0).name)){
		   	if(!(lk instanceof ContextLink)){
		    '''«(new LinkGeneratorClient(lk, '', compname, valuefeatures )).generateLink» . '&«page.extendedEntityList.get(0).primaryKey.name»='.(int) $item->«page.extendedEntityList.get(0).primaryKey.name» '''
		    
		    }else{
		    	
		         if((lk as ContextLink).linkparameters.filter[t | t.id].size == 0){
		    	'''«(new LinkGeneratorClient(lk, '', compname, valuefeatures )).generateLink»  . '&«page.extendedEntityList.get(0).primaryKey.name»='.(int) $item->«page.extendedEntityList.get(0).primaryKey.name» '''
		    	}else{
		    		'''«(new LinkGeneratorClient(lk, '', compname, valuefeatures )).generateLink»  . '&«page.extendedEntityList.get(0).primaryKey.name»='.(int) $item->«page.extendedEntityList.get(0).primaryKey.name» '''
		    
		   			}		   	   
		    }}else{
		    	
		    		var ExtendedAttribute idRef = Slug.getAttributeForForeignID(attribute, page)
		    		var Entity entityRef = Slug.getEntityForForeignID(attribute, page)
		    		
		    
				   if(idRef != null){
				   '''«(new LinkGeneratorClient(lk, '', compname, valuefeatures )).generateLink» . '&«Slug.getPrimaryKeys(entityRef).name»='.(int) $item->«idRef.name»'''
				   	
				   }
				   else	
				 	'''«(new LinkGeneratorClient(lk, '', compname, valuefeatures )).generateLink» . '&«Slug.getPrimaryKeys(entityRef).name»='.(int) $this->getModel()->getIdOfReferenceItem("«(lk as InternalLink).name.toLowerCase»",$item)'''
		 	 
		 	}}else{
		 		'''«(new LinkGeneratorClient(lk, '', compname, valuefeatures )).generateLink» . '&filter.search='. $item->«attribute.name.toLowerCase»'''
		 		}
		 	
		}}»
	«ENDIF»
	«ENDIF»
	«ENDFOR»
	'''
	
	def static Entity getEntityForForeignID(ExtendedAttribute attr, ExtendedDynamicPage dynPage) {
		for(ExtendedReference ref: dynPage.extendedEntityList.get(0).extendedReference){
			if(ref.extendedAttribute.get(0).name.equalsIgnoreCase(attr.name)){
				
					return ref.extendedToEntity
			}
	}
	}
	def static ExtendedAttribute getAttributeForForeignID(ExtendedAttribute attr, ExtendedDynamicPage dynPage){
		for(ExtendedReference ref: dynPage.extendedEntityList.get(0).extendedReference){
			if(ref.extendedAttribute.get(0).name.equalsIgnoreCase(attr.name)){
				for(ExtendedAttribute refAttr: ref.extendedAttributeReferenced){
					if(refAttr.name.equalsIgnoreCase("id"))
					return ref.extendedAttribute.get(ref.extendedAttributeReferenced.indexOf(refAttr))
				}
			}
		}
		return null
	}
	def static Boolean isLinkedAttributeReference(Attribute attribute, DynamicPage page) {
		
		for(Entity e: page.entities){
			for(Reference ref: e.references){
				if(ref.attribute.get(0).equals(attribute))
				return true
			}
		}
		return false
	}
	
	def static Reference searchLinkedAttributeReference(Attribute attribute, DynamicPage page) {
		for(Entity e: page.entities){
			for(Reference ref: e.references){
				if(ref.attribute.get(0).name.equalsIgnoreCase(attribute.name))
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
 				'''"«linkItem.name.toLowerCase»" => array("db"=> "#__«com.name.toLowerCase»_«ref.entity.name.toLowerCase»","refattr" => array(«Slug.generateAttributeAndRefernce(ref)»
 				), "foreignPk" => "«Slug.getPrimaryKeys(ref.entity).name.toLowerCase»"),'''	
 			}				
 		}	
 	}»«ENDFOR»null);
 '''
	
	def static CharSequence generateAttributeAndRefernce(Reference reference) {
		var StringBuffer result = new StringBuffer
		
		for(Attribute attr: reference.attribute){
			var int index = reference.attribute.indexOf(attr)
			var Attribute referenced = reference.attributerefereced.get(index)
			if(attr != reference.attribute.last)
			result.append('''"«attr.name.toLowerCase»"=>"«referenced.name.toLowerCase»",''')
			else{
				result.append('''"«attr.name.toLowerCase»"=>"«referenced.name.toLowerCase»"''')
			}
			
		}
		
		return result.toString
	}
	
	static def CharSequence transformAttributeListInString(EList<Attribute>attributes, String separeSign){
		var StringBuffer result = new StringBuffer()
		for(attr: attributes){
			if(attr != attributes.last){
			result.append(attr.name.toLowerCase + separeSign)
			}else{
				result.append(Slug.slugify(attr.name))
			}
		}
		return result.toString

	}
	
	static def CharSequence transformAttributeListInString(String postWord, EList<Attribute>attributes, String separeSign){
		var StringBuffer result = new StringBuffer()
		for(attr: attributes){
			if(attr != attributes.last){
			result.append(postWord + Slug.slugify(attr.name).toLowerCase + separeSign)
			}else{
				result.append(postWord+ Slug.slugify(attr.name))
			}
		}
		return result.toString

	}
	static def CharSequence transformAttributeListInString(String postWord, EList<Attribute>attributes, String separeSign,String afterWord){
		var StringBuffer result = new StringBuffer()
		for(attr: attributes){
			if(attr != attributes.last){
			result.append(postWord + Slug.slugify(attr.name).toLowerCase + afterWord + separeSign)
			}else{
				result.append(postWord+ Slug.slugify(attr.name)+afterWord)
			}
		}
		return result.toString

	}
	static def CharSequence transformAttributeListInString(String quotationMark, String postWord, EList<Attribute>attributes, String separeSign){
		var StringBuffer result = new StringBuffer()
		for(attr: attributes){
			if(attr != attributes.last){
			result.append(quotationMark+postWord + Slug.slugify(attr.name).toLowerCase+quotationMark + separeSign)
			}else{
				result.append(quotationMark+postWord+ Slug.slugify(attr.name) +quotationMark)
			}
		}
		return result.toString

	}
	
	def static CharSequence getParamterType(MethodParameter parameter) {
		return ""
	}

		
		static def CharSequence writeParameter(
		ExtendedParameter param) '''
		<field
		name="«param.name»"
		type="«Slug.getTypeName(param)»"
		default="«param.defaultvalue»"
		label="«param.label»"
		description="«param.descripton»"
		>
	'''
 static def ExtendedDetailPageField getEditedFieldsForattribute(ExtendedDynamicPage dynPage, ExtendedAttribute attr){
		for(ExtendedDetailPageField field:dynPage.extendedEditedFieldsList ){
   				if(field.extendedAttribute.name.equalsIgnoreCase(attr.name)){
   				     return field
   				}
   			}
   			return null
	}
//get all other referenced in the referenced Entity	
	def static EList<Attribute> getOtherAttribute(ExtendedReference reference) {
	var Entity toEntity = reference.extendedToEntity
	var Reference ref = (toEntity.references.filter[t | !t.entity.name.equalsIgnoreCase( reference.extendedFromEntity.name)]).get(0)
	
	return ref.attribute
	
	}
	
	def static Entity getOtherEntityToMapping(ExtendedReference reference) {
		var Entity toEntity = reference.extendedToEntity
	var Reference ref = (toEntity.references.filter[t | !t.entity.name.equalsIgnoreCase(reference.extendedFromEntity.name)]).get(0)
	
	return ref.entity
	}
	
	def static CharSequence generateEntytiesBackendInputRefrence(ExtendedReference reference, ExtendedComponent com) '''
	<?php if (JFactory::getUser()->authorise('core.admin','«com.name.toLowerCase»')) : ?>
				<?php echo JHtml::_('bootstrap.addTab', 'myTab', '«Slug.getOtherEntityToMapping(reference).name.toLowerCase»', JText::_('«Slug.nameExtensionBind("com",com.name).toUpperCase»_«Slug.getOtherEntityToMapping(reference).name.toUpperCase»', true)); ?>
	 <div class="control-group">
		<div class="control-label"><?php echo $this->form->getLabel('«reference.entity.name.toLowerCase»_id'); ?></div>
		<div class="controls"><?php echo $this->form->getInput('«reference.entity.name.toLowerCase»_id'); ?></div>
	</div>
	«FOR attribute: Slug.getOtherAttribute(reference)»
	<div class="control-group">
		<div class="controls"><?php echo $this->form->getInput('«attribute.name.toLowerCase»'); ?></div>
	</div>
	«ENDFOR»
	<?php echo JHtml::_('bootstrap.endTab'); ?>
			<?php endif; ?>
	'''
	def static CharSequence generateEntytiesSiteInputRefrence(ExtendedReference reference,ExtendedComponent com) '''
		 <?php if (JFactory::getUser()->authorise('core.admin','«com.name.toLowerCase»')) : ?>
		 				<?php echo JHtml::_('bootstrap.addTab', 'myTab', '«Slug.getOtherEntityToMapping(reference).name.toLowerCase»', JText::_('«Slug.nameExtensionBind("com",com.name).toUpperCase»_«Slug.getOtherEntityToMapping(reference).name.toUpperCase»', true)); ?>
		 <div class="control-group">
		<div class="control-label"><?php echo $this->form->getLabel('«reference.entity.name.toLowerCase»_id'); ?></div>
		<div class="controls"><?php echo $this->form->getInput('«reference.entity.name.toLowerCase»_id'); ?></div>
	</div>
	«FOR attribute: Slug.getOtherAttribute(reference)»
	<div class="control-group">
		<div class="controls"><?php echo $this->form->getInput('«attribute.name.toLowerCase»'); ?></div>
	</div>
	«ENDFOR»
	<?php echo JHtml::_('bootstrap.endTab'); ?>
				<?php endif; ?>
	'''
	
	def static Attribute getPrimaryKeys(Entity entity) {
		for(Attribute attr: entity.attributes){
			if(attr.isIsprimary)
			return attr
		}
		
	}
	
	
} // Slug
