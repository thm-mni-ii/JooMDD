package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.Author
import de.thm.icampus.joomdd.ejsl.eJSL.BackendSection
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.ContextLink
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.eJSL.Extension
import de.thm.icampus.joomdd.ejsl.eJSL.ExternalLink
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.eJSL.InternalLink
import de.thm.icampus.joomdd.ejsl.eJSL.Link
import de.thm.icampus.joomdd.ejsl.eJSL.MethodParameter
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.eJSL.Section
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes
import de.thm.icampus.joomdd.ejsl.eJSL.Type
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl.ExtendedDynamicPageImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import java.io.File
import java.util.Calendar
import java.util.GregorianCalendar
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.LinkGeneratorHandler
import java.util.ArrayList

/**
 * This class contains templates which are often used in different contexts.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 * 
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
	
	def static String getTypeName(String type) {
		var String result =""
		switch type {
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
			    result="imagepicker"
			} 
			case "Filepicker":{
			    result="filepicker"
			}
			case "Text_Field_NE":{
			    result='''type="text" '''
			} 
			case "Editor":{
			    result='''type="editor" '''
			}
			case "Select":{
			    result="select"
			}
			case "Multiselect":{
			    result="multiselect"
			}
			case "Checkbox":{
			    result="checkbox"
			}
			case "Radiobutton":{
			    result="radiobutton"
			}
			case "hidden":{
			    result="hidden"
			}
			default : {
			    result = '''type="«type»"'''
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
		for(Section sec: com.sections) {
			switch sec{
				 BackendSection:{
				 	return sec
				 }
			}
		}
		return null;
	}
	
	def static CharSequence generateEntytiesInputAttribute(EList<ExtendedDetailPageField> fields, ExtendedEntity entity) {
		var StringBuffer buff = new StringBuffer()
		var notShow = newArrayList("state","created_by","asset_id","ordering","checked_out_time","checked_out", "published", "params")
		notShow.add(entity.primaryKey.name)
		
		for (ExtendedDetailPageField fielditem: fields) {
			if (!notShow.contains(fielditem.extendedAttribute.name)) {
				buff.append(inputFeldTemplate(fielditem.extendedAttribute))
				notShow.add(fielditem.extendedAttribute.name)
			}	
		}
		
		for (ExtendedAttribute attr: entity.ownExtendedAttributes){
			if (!notShow.contains(attr.name)) {
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

	def static String generateKeysName(Component com, String name){
		if(name!==null)
		return "COM_" + com.name.toUpperCase + "_FIELD_" + Slug.slugify(name).toUpperCase
	}
	
	def static String generateKeysNamePage(Component com, Page page ,String name){
		return "COM_" + com.name.toUpperCase + "_FIELD_" + Slug.slugify(page.name).toUpperCase + "_" + Slug.slugify(name).toUpperCase
	}
	
	def static ExtendedDynamicPage getPageForDetails(ExtendedDynamicPage inpage, ExtendedComponent com) {
		if (inpage.links.empty) {
			for (ExtendedPageReference pg : com.backEndExtendedPagerefence) {
				if (pg.extendedPage.extendedDynamicPageInstance !== null && pg.extendedPage.extendedDynamicPageInstance.detailsPage) {
					var String first = pg.extendedPage.extendedDynamicPageInstance.extendedEntityList.get(0).name
					var String second = inpage.extendedEntityList.get(0).name
					if(first.equalsIgnoreCase(second))
					return pg.extendedPage.extendedDynamicPageInstance
				}
			}
		}
		for (Link lk: inpage.links) {
		    switch lk {
		        ContextLink: {
		            var InternalLink lkin = lk as InternalLink
		            if (lkin.target instanceof DetailsPage) {
		                return new ExtendedDynamicPageImpl(lkin.target as DetailsPage)
		            }
		        }
		        InternalLink : {
		            var InternalLink lkin = lk as InternalLink
		            if (lkin.target instanceof DetailsPage) {
		                return new ExtendedDynamicPageImpl(lkin.target as DetailsPage)
	                }
	            }
	        }
	    }
		return null
	}
	
	def static IndexPage getPageForAll(ExtendedDynamicPage inpage, ExtendedComponent com) {
	    if (inpage.links.empty) {
		    for (ExtendedPageReference pg : com.backEndExtendedPagerefence) {
				if (pg.extendedPage.extendedDynamicPageInstance!== null && !pg.extendedPage.extendedDynamicPageInstance.detailsPage) {
					var String first = pg.extendedPage.extendedDynamicPageInstance.extendedEntityList.get(0).name
					var String second = inpage.extendedEntityList.get(0).name
					if (first.equalsIgnoreCase(second)) {
					    return pg.extendedPage.extendedDynamicPageInstance.instance as IndexPage
					}
				}
			}
		}
		for (Link lk: inpage.links) {
			switch lk {
			    ContextLink: {
					var InternalLink lkin = lk as InternalLink
					if(lkin.target instanceof IndexPage)
					  return lkin.target as IndexPage
				}
				InternalLink: {
				    var InternalLink lkin = lk as InternalLink
					if (lkin.target instanceof IndexPage) {
					    return lkin.target as IndexPage
					}
				}
			}
		}
		return null
	}
	
	/**
     * Generate the author informations for the Manifest of a extension
     * 
     * @param EList<Author> authors List of authors
     * @return Charsequence 
     * 
     */
	def static CharSequence generateAuthors(EList<Author> authors) '''
		«IF authors.size() == 0»
			<author>Auto Generated Author</author>
			<authorEmail>info@generated.com</authorEmail>
			<authorUrl>www.generated.com</authorUrl>
		«ELSE»
			«FOR author : authors»
				<author>«author.name»</author>
				«IF author.authoremail !== null»
				<authorEmail>«author.authoremail»</authorEmail>
				«ENDIF»
				«IF author.authorurl !== null»
				<authorUrl>«author.authorurl»</authorUrl>
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	'''
	
	/**
     * Generate the author informations for the documentation of a file
     * 
     * @param EList<Author> authors List of authors
     * @return Charsequence 
     * 
     */
	def static CharSequence generateAuthorsDocumentation(EList<Author> authors) '''
		«IF authors.size() == 0»
		* @author Auto Generated Author
		* @authorEmail <info@generated.com>
		* @authorUrl www.generated.com
		«ELSE»
		«FOR author : authors»
		* @author «author.name» «IF author.authoremail !== null»  <«author.authoremail»>«ENDIF»«IF author.authorurl !== null» <«author.authorurl»>«ENDIF»
		«ENDFOR»
		«ENDIF»
	'''
	
	def static CharSequence generateFileDoc( Module module) '''
	    /**
	     * @version     «module.manifest.version»
	     * @category    Joomla module
	     * @package     Joomla.Site
	     * @subpackage  mod_«module.name»
	     * @name        «module.name»
	     * @description  «module.manifest.description»
	     «generateAuthorsDocumentation(module.manifest.authors)»
	     * @copyright   «cal.get(Calendar.YEAR)»  «module.manifest.copyright»
	     * @license     «module.manifest.license»
	     * @link        «module.manifest.link»
	     */
	'''
	
    def static CharSequence generateFileDoc( Component component)'''
	    /**
	     «IF component.manifest === null»
	     * @category    Joomla component
	     * @package     Joomla.Administrator
	     * @subpackage  com_«component.name»
	     * @name «component.name»«ELSE»
	     * @version «component.manifest.version»
	     * @category    Joomla component
	     * @package     Joomla.Administrator
	     * @subpackage  com_«component.name»
	     * @name «component.name»
	     «IF component.manifest !== null»
	     «generateAuthorsDocumentation(component.manifest.authors)»
	     * @copyright   «component.manifest.copyright»
	     * @license     «component.manifest.license»
	     «ENDIF»
	     «ENDIF»
	     */
	'''

    def static CharSequence generateFileDoc( Extension ext)'''
		/**
		 * @version «ext.manifest.version»
		 * @category Joomla component
		 * @package     Joomla.Administrator
		 * @subpackage  com_«ext.name»
		 * @name «ext.name»View
		 «IF ext.manifest !== null»
		 «generateAuthorsDocumentation(ext.manifest.authors)»
		 * @copyright «ext.manifest.copyright»
		 * @license «ext.manifest.license»
		 «ENDIF»
		 */
	'''
	
	def static CharSequence generateRestrictedAccess() '''
		defined('_JEXEC') or die('Restricted access');
	'''
	
	 def static CharSequence generateUses(ArrayList<String> classNames)'''
	 	«FOR String className: classNames»
	 		«switch(className) {
			    case 'Registry' : { "use Joomla\\Registry\\Registry;" }
				case 'RegistryFormat' : { "use Joomla\\Registry\\AbstractRegistryFormat;" }
				case 'RegistryFormatIni' : { "use Joomla\\Registry\\Format\\Ini;" }
				case 'RegistryFormatJson' : { "use Joomla\\Registry\\Format\\Json;" }
				case 'RegistryFormatPhp' : { "use Joomla\\Registry\\Format\\Php;" }
				case 'RegistryFormatXml' : { "use Joomla\\Registry\\Format\\Xml;" }
				case 'StringInflector' : { "use Joomla\\String\\Inflector;" }
				case 'StringNormalise' : { "use Joomla\\String\\Normalise;" }
				case 'Data' : { "use Joomla\\Data\\DataObject;" }
				case 'DataSet' : { "use Joomla\\Data\\DataSet;" }
				case 'DataDumpable' : { "use Joomla\\Data\\DumpableInterface;" }
				
				case 'ArrayHelper' : { "use Joomla\\Utilities\\ArrayHelper;" }
				
				case 'ApplicationAdministrator' : { "use Joomla\\CMS\\Application\\AdministratorApplication;" }
				case 'ApplicationHelper' : { "use Joomla\\CMS\\Application\\ApplicationHelper;" }
				case 'ApplicationBase' : { "use Joomla\\CMS\\Application\\BaseApplication;" }
				case 'ApplicationCli' : { "use Joomla\\CMS\\Application\\CliApplication;" }
				case 'ApplicationCms' : { "use Joomla\\CMS\\Application\\CMSApplication;" }
				case 'ApplicationDaemon' : { "use Joomla\\CMS\\Application\\DaemonApplication;" }
				case 'ApplicationSite' : { "use Joomla\\CMS\\Application\\SiteApplication;" }
				case 'ApplicationWeb' : { "use Joomla\\CMS\\Application\\WebApplication;" }
				case 'ApplicationWebClient' : { "use Joomla\\Application\\Web\\WebClient;" }
				case 'Daemon' : { "use Joomla\\CMS\\Application\\DaemonApplication;" }
				case 'Cli' : { "use Joomla\\CMS\\Application\\CliApplication;" }
				case 'Web' : { "use Joomla\\CMS\\Application\\WebApplication';" }
				case 'WebClient' : { "use Joomla\\Application\\Web\\WebClient';" }
				
				case 'ModelAdmin' : { "use Joomla\\CMS\\MVC\\Model\\AdminModel;" }
				case 'ModelForm' : { "use Joomla\\CMS\\MVC\\Model\\FormModel;" }
				case 'ModelItem' : { "use Joomla\\CMS\\MVC\\Model\\ItemModel;" }
				case 'ModelList' : { "use Joomla\\CMS\\MVC\\Model\\ListModel;" }
				case 'ModelLegacy' : { "use Joomla\\CMS\\MVC\\Model\\BaseDatabaseModel;" }
				case 'ViewCategories' : { "use Joomla\\CMS\\MVC\\View\\CategoriesView;" }
				case 'ViewCategory' : { "use Joomla\\CMS\\MVC\\View\\CategoryView;" }
				case 'ViewCategoryfeed' : { "use Joomla\\CMS\\MVC\\View\\CategoryFeedView;" }
				case 'ViewLegacy' : { "use Joomla\\CMS\\MVC\\View\\HtmlView;" }
				case 'ControllerAdmin' : { "use Joomla\\CMS\\MVC\\Controller\\AdminController;" }
				case 'ControllerLegacy' : { "use Joomla\\CMS\\MVC\\Controller\\BaseController;" }
				case 'ControllerForm' : { "use Joomla\\CMS\\MVC\\Controller\\FormController;" }
				case 'TableInterface' : { "use Joomla\\CMS\\Table\\TableInterface;" }
				case 'Table' : { "use Joomla\\CMS\\Table\\Table;" }
				case 'TableNested' : { "use Joomla\\CMS\\Table\\Nested;" }
				case 'TableAsset' : { "use Joomla\\CMS\\Table\\Asset;" }
				case 'TableExtension' : { "use Joomla\\CMS\\Table\\Extension;" }
				case 'TableLanguage' : { "use Joomla\\CMS\\Table\\Language;" }
				case 'TableUpdate' : { "use Joomla\\CMS\\Table\\Update;" }
				case 'TableUpdatesite' : { "use Joomla\\CMS\\Table\\UpdateSite;" }
				case 'TableUser' : { "use Joomla\\CMS\\Table\\User;" }
				case 'TableUsergroup' : { "use Joomla\\CMS\\Table\\Usergroup;" }
				case 'TableViewlevel' : { "use Joomla\\CMS\\Table\\ViewLevel;" }
				case 'TableContenthistory' : { "use Joomla\\CMS\\Table\\ContentHistory;" }
				case 'TableContenttype' : { "use Joomla\\CMS\\Table\\ContentType;" }
				case 'TableCorecontent' : { "use Joomla\\CMS\\Table\\CoreContent;" }
				case 'TableUcm' : { "use Joomla\\CMS\\Table\\Ucm;" }
				case 'TableCategory' : { "use Joomla\\CMS\\Table\\Category;" }
				case 'TableContent' : { "use Joomla\\CMS\\Table\\Content;" }
				case 'TableMenu' : { "use Joomla\\CMS\\Table\\Menu;" }
				case 'TableMenuType' : { "use Joomla\\CMS\\Table\\MenuType;" }
				case 'TableModule' : { "use Joomla\\CMS\\Table\\Module;" }
				case 'TableObserver' : { "use Joomla\\CMS\\Table\\Observer\\AbstractObserver;" }
				case 'TableObserverContenthistory' : { "use Joomla\\CMS\\Table\\Observer\\ContentHistory;" }
				case 'TableObserverTags' : { "use Joomla\\CMS\\Table\\Observer\\Tags;" }
				
				case 'Access' : { "use Joomla\\CMS\\Access\\Access;" }
				case 'AccessRule' : { "use Joomla\\CMS\\Access\\Rule;" }
				case 'AccessRules' : { "use Joomla\\CMS\\Access\\Rules;" }
				case 'AccessWrapperAccess' : { "use Joomla\\CMS\\Access\\Wrapper\\Access';" }
				case 'AccessExceptionNotallowed' : { "use Joomla\\CMS\\Access\\Exception\\NotAllowed;" }
				case 'Rule' : { "use Joomla\\CMS\\Access\\Rule;" }
				case 'Rules' : { "use Joomla\\CMS\\Access\\Rules;" }
				
				case 'Help' : { "use Joomla\\CMS\\Help\\Help;" }
				case 'Captcha' : { "use Joomla\\CMS\\Captcha\\Captcha;" }
				
				case 'LanguageAssociations' : { "use Joomla\\CMS\\Language\\Associations;" }
				case 'Language' : { "use Joomla\\CMS\\Language\\Language;" }
				case 'LanguageHelper' : { "use Joomla\\CMS\\Language\\LanguageHelper;" }
				case 'LanguageStemmer' : { "use Joomla\\CMS\\Language\\LanguageStemmer;" }
				case 'LanguageMultilang' : { "use Joomla\\CMS\\Language\\Multilanguage;" }
				case 'Text' : { "use Joomla\\CMS\\Language\\Text;" }
				case 'LanguageTransliterate' : { "use Joomla\\CMS\\Language\\Transliterate;" }
				case 'LanguageStemmerPorteren' : { "use Joomla\\CMS\\Language\\Stemmer\\Porteren;" }
				case 'LanguageWrapperText' : { "use Joomla\\CMS\\Language\\Wrapper\\JTextWrapper';" }
				case 'LanguageWrapperHelper' : { "use Joomla\\CMS\\Language\\Wrapper\\LanguageHelperWrapper';" }
				case 'LanguageWrapperTransliterate' : { "use Joomla\\CMS\\Language\\Wrapper\\TransliterateWrapper';" }
				
				case 'ComponentHelper' : { "use Joomla\\CMS\\Component\\ComponentHelper;" }
				case 'ComponentRecord' : { "use Joomla\\CMS\\Component\\ComponentRecord;" }
				case 'ComponentExceptionMissing' : { "use Joomla\\CMS\\Component\\Exception\\MissingComponentException;" }
				case 'ComponentRouterBase' : { "use Joomla\\CMS\\Component\\Router\\RouterBase;" }
				case 'ComponentRouterInterface' : { "use Joomla\\CMS\\Component\\Router\\RouterInterface;" }
				case 'ComponentRouterLegacy' : { "use Joomla\\CMS\\Component\\Router\\RouterLegacy;" }
				case 'ComponentRouterView' : { "use Joomla\\CMS\\Component\\Router\\RouterView;" }
				case 'ComponentRouterViewconfiguration' : { "use Joomla\\CMS\\Component\\Router\\RouterViewConfiguration;" }
				case 'ComponentRouterRulesMenu' : { "use Joomla\\CMS\\Component\\Router\\Rules\\MenuRules;" }
				case 'ComponentRouterRulesNomenu' : { "use Joomla\\CMS\\Component\\Router\\Rules\\NomenuRules;" }
				case 'ComponentRouterRulesInterface' : { "use Joomla\\CMS\\Component\\Router\\Rules\\RulesInterface;" }
				case 'ComponentRouterRulesStandard' : { "use Joomla\\CMS\\Component\\Router\\Rules\\StandardRules;" }
				
				case 'Editor' : { "use Joomla\\CMS\\Editor\\Editor;" }
				
				case 'ErrorPage' : { "use Joomla\\CMS\\Exception\\ExceptionHandler;" }
				
				case 'AuthenticationHelper' : { "use Joomla\\CMS\\Helper\\AuthenticationHelper;" }
				case 'Helper' : { "use Joomla\\CMS\\Helper\\CMSHelper;" }
				case 'HelperContent' : { "use Joomla\\CMS\\Helper\\ContentHelper;" }
				case 'HelperContenthistory' : { "use Joomla\\CMS\\Helper\\ContentHistoryHelper;" }
				case 'LibraryHelper' : { "use Joomla\\CMS\\Helper\\LibraryHelper;" }
				case 'HelperMedia' : { "use Joomla\\CMS\\Helper\\MediaHelper;" }
				case 'ModuleHelper' : { "use Joomla\\CMS\\Helper\\ModuleHelper;" }
				case 'HelperRoute' : { "use Joomla\\CMS\\Helper\\RouteHelper;" }
				case 'SearchHelper' : { "use Joomla\\CMS\\Helper\\SearchHelper;" }
				case 'HelperTags' : { "use Joomla\\CMS\\Helper\\TagsHelper;" }
				case 'HelperUsergroups' : { "use Joomla\\CMS\\Helper\\UserGroupsHelper;" }
				
				case 'LayoutBase' : { "use Joomla\\CMS\\Layout\\BaseLayout;" }
				case 'LayoutFile' : { "use Joomla\\CMS\\Layout\\FileLayout;" }
				case 'LayoutHelper' : { "use Joomla\\CMS\\Layout\\LayoutHelper;" }
				case 'Layout' : { "use Joomla\\CMS\\Layout\\LayoutInterface;" }
				
				case 'ResponseJson' : { "use Joomla\\CMS\\Response\\JsonResponse;" }
				
				case 'Plugin' : { "use Joomla\\CMS\\Plugin\\CMSPlugin;" }
				case 'PluginHelper' : { "use Joomla\\CMS\\Plugin\\PluginHelper;" }
				
				case 'Menu' : { "use Joomla\\CMS\\Menu\\AbstractMenu;" }
				case 'MenuAdministrator' : { "use Joomla\\CMS\\Menu\\AdministratorMenu;" }
				case 'MenuItem' : { "use Joomla\\CMS\\Menu\\MenuItem;" }
				case 'MenuSite' : { "use Joomla\\CMS\\Menu\\SiteMenu;" }
				
				case 'Pagination' : { "use Joomla\\CMS\\Pagination\\Pagination;" }
				case 'PaginationObject' : { "use Joomla\\CMS\\Pagination\\PaginationObject;" }
				
				case 'Pathway' : { "use Joomla\\CMS\\Pathway\\Pathway;" }
				case 'PathwaySite' : { "use Joomla\\CMS\\Pathway\\SitePathway;" }
				
				case 'SchemaChangeitem' : { "use Joomla\\CMS\\Schema\\ChangeItem;" }
				case 'SchemaChangeset' : { "use Joomla\\CMS\\Schema\\ChangeSet;" }
				case 'SchemaChangeitemMysql' : { "use Joomla\\CMS\\Schema\\ChangeItem\\MysqlChangeItem;" }
				case 'SchemaChangeitemPostgresql' : { "use Joomla\\CMS\\Schema\\ChangeItem\\PostgresqlChangeItem;" }
				case 'SchemaChangeitemSqlsrv' : { "use Joomla\\CMS\\Schema\\ChangeItem\\SqlsrvChangeItem;" }
				
				case 'Ucm' : { "use Joomla\\CMS\\UCM\\UCM;" }
				case 'UcmBase' : { "use Joomla\\CMS\\UCM\\UCMBase;" }
				case 'UcmContent' : { "use Joomla\\CMS\\UCM\\UCMContent;" }
				case 'UcmType' : { "use Joomla\\CMS\\UCM\\UCMType;" }
				
				case 'Toolbar' : { "use Joomla\\CMS\\Toolbar\\Toolbar;" }
				case 'ToolbarButton' : { "use Joomla\\CMS\\Toolbar\\ToolbarButton;" }
				case 'ToolbarButtonConfirm' : { "use Joomla\\CMS\\Toolbar\\Button\\ConfirmButton;" }
				case 'ToolbarButtonCustom' : { "use Joomla\\CMS\\Toolbar\\Button\\CustomButton;" }
				case 'ToolbarButtonHelp' : { "use Joomla\\CMS\\Toolbar\\Button\\HelpButton;" }
				case 'ToolbarButtonLink' : { "use Joomla\\CMS\\Toolbar\\Button\\LinkButton;" }
				case 'ToolbarButtonPopup' : { "use Joomla\\CMS\\Toolbar\\Button\\PopupButton;" }
				case 'ToolbarButtonSeparator' : { "use Joomla\\CMS\\Toolbar\\Button\\SeparatorButton;" }
				case 'ToolbarButtonSlider' : { "use Joomla\\CMS\\Toolbar\\Button\\SliderButton;" }
				case 'ToolbarButtonStandard' : { "use Joomla\\CMS\\Toolbar\\Button\\StandardButton;" }
				case 'Button' : { "use Joomla\\CMS\\Toolbar\\ToolbarButton;" }
				
				case 'Version' : { "use Joomla\\CMS\\Version;" }
				
				case 'Authentication' : { "use Joomla\\CMS\\Authentication\\Authentication;" }
				case 'AuthenticationResponse' : { "use Joomla\\CMS\\Authentication\\AuthenticationResponse;" }
				
				case 'Browser' : { "use Joomla\\CMS\\Environment\\Browser;" }
				
				case 'AssociationExtensionInterface' : { "use Joomla\\CMS\\Association\\AssociationExtensionInterface;" }
				case 'AssociationExtensionHelper' : { "use Joomla\\CMS\\Association\\AssociationExtensionHelper;" }
				
				case 'Document' : { "use Joomla\\CMS\\Document\\Document;" }
				case 'DocumentError' : { "use Joomla\\CMS\\Document\\ErrorDocument;" }
				case 'DocumentFeed' : { "use Joomla\\CMS\\Document\\FeedDocument;" }
				case 'DocumentHtml' : { "use Joomla\\CMS\\Document\\HtmlDocument;" }
				case 'DocumentImage' : { "use Joomla\\CMS\\Document\\ImageDocument;" }
				case 'DocumentJson' : { "use Joomla\\CMS\\Document\\JsonDocument;" }
				case 'DocumentOpensearch' : { "use Joomla\\CMS\\Document\\OpensearchDocument;" }
				case 'DocumentRaw' : { "use Joomla\\CMS\\Document\\RawDocument;" }
				case 'DocumentRenderer' : { "use Joomla\\CMS\\Document\\DocumentRenderer;" }
				case 'DocumentXml' : { "use Joomla\\CMS\\Document\\XmlDocument;" }
				case 'DocumentRendererFeedAtom' : { "use Joomla\\CMS\\Document\\Renderer\\Feed\\AtomRenderer;" }
				case 'DocumentRendererFeedRss' : { "use Joomla\\CMS\\Document\\Renderer\\Feed\\RssRenderer;" }
				case 'DocumentRendererHtmlComponent' : { "use Joomla\\CMS\\Document\\Renderer\\Html\\ComponentRenderer;" }
				case 'DocumentRendererHtmlHead' : { "use Joomla\\CMS\\Document\\Renderer\\Html\\HeadRenderer;" }
				case 'DocumentRendererHtmlMessage' : { "use Joomla\\CMS\\Document\\Renderer\\Html\\MessageRenderer;" }
				case 'DocumentRendererHtmlModule' : { "use Joomla\\CMS\\Document\\Renderer\\Html\\ModuleRenderer;" }
				case 'DocumentRendererHtmlModules' : { "use Joomla\\CMS\\Document\\Renderer\\Html\\ModulesRenderer;" }
				case 'DocumentRendererAtom' : { "use Joomla\\CMS\\Document\\Renderer\\Feed\\AtomRenderer';" }
				case 'DocumentRendererRSS' : { "use Joomla\\CMS\\Document\\Renderer\\Feed\\RssRenderer';" }
				case 'DocumentRendererComponent' : { "use Joomla\\CMS\\Document\\Renderer\\Html\\ComponentRenderer';" }
				case 'DocumentRendererHead' : { "use Joomla\\CMS\\Document\\Renderer\\Html\\HeadRenderer';" }
				case 'DocumentRendererMessage' : { "use Joomla\\CMS\\Document\\Renderer\\Html\\MessageRenderer';" }
				case 'DocumentRendererModule' : { "use Joomla\\CMS\\Document\\Renderer\\Html\\ModuleRenderer';" }
				case 'DocumentRendererModules' : { "use Joomla\\CMS\\Document\\Renderer\\Html\\ModulesRenderer';" }
				case 'FeedEnclosure' : { "use Joomla\\CMS\\Document\\Feed\\FeedEnclosure;" }
				case 'FeedImage' : { "use Joomla\\CMS\\Document\\Feed\\FeedImage;" }
				case 'FeedItem' : { "use Joomla\\CMS\\Document\\Feed\\FeedItem;" }
				case 'OpenSearchImage' : { "use Joomla\\CMS\\Document\\Opensearch\\OpensearchImage;" }
				case 'OpenSearchUrl' : { "use Joomla\\CMS\\Document\\Opensearch\\OpensearchUrl;" }
				
				case 'FilterInput' : { "use Joomla\\CMS\\Filter\\InputFilter;" }
				case 'FilterOutput' : { "use Joomla\\CMS\\Filter\\OutputFilter;" }
				case 'FilterWrapperOutput' : { "use Joomla\\CMS\\Filter\\Wrapper\\OutputFilterWrapper';" }
				
				case 'Http' : { "use Joomla\\CMS\\Http\\Http;" }
				case 'HttpFactory' : { "use Joomla\\CMS\\Http\\HttpFactory;" }
				case 'HttpResponse' : { "use Joomla\\CMS\\Http\\Response;" }
				case 'HttpTransport' : { "use Joomla\\CMS\\Http\\TransportInterface;" }
				case 'HttpTransportCurl' : { "use Joomla\\CMS\\Http\\Transport\\CurlTransport;" }
				case 'HttpTransportSocket' : { "use Joomla\\CMS\\Http\\Transport\\SocketTransport;" }
				case 'HttpTransportStream' : { "use Joomla\\CMS\\Http\\Transport\\StreamTransport;" }
				case 'HttpWrapperFactory' : { "use Joomla\\CMS\\Http\\Wrapper\\FactoryWrapper';" }
				
				case 'Installer' : { "use Joomla\\CMS\\Installer\\Installer;" }
				case 'InstallerAdapter' : { "use Joomla\\CMS\\Installer\\InstallerAdapter;" }
				case 'InstallerExtension' : { "use Joomla\\CMS\\Installer\\InstallerExtension;" }
				case 'Extension' : { "use Joomla\\CMS\\Installer\\InstallerExtension;" }
				case 'InstallerHelper' : { "use Joomla\\CMS\\Installer\\InstallerHelper;" }
				case 'InstallerScript' : { "use Joomla\\CMS\\Installer\\InstallerScript;" }
				case 'InstallerManifest' : { "use Joomla\\CMS\\Installer\\Manifest;" }
				case 'InstallerAdapterComponent' : { "use Joomla\\CMS\\Installer\\Adapter\\ComponentAdapter;" }
				case 'InstallerComponent' : { "use Joomla\\CMS\\Installer\\Adapter\\ComponentAdapter;" }
				case 'InstallerAdapterFile' : { "use Joomla\\CMS\\Installer\\Adapter\\FileAdapter;" }
				case 'InstallerFile' : { "use Joomla\\CMS\\Installer\\Adapter\\FileAdapter;" }
				case 'InstallerAdapterLanguage' : { "use Joomla\\CMS\\Installer\\Adapter\\LanguageAdapter;" }
				case 'InstallerLanguage' : { "use Joomla\\CMS\\Installer\\Adapter\\LanguageAdapter;" }
				case 'InstallerAdapterLibrary' : { "use Joomla\\CMS\\Installer\\Adapter\\LibraryAdapter;" }
				case 'InstallerLibrary' : { "use Joomla\\CMS\\Installer\\Adapter\\LibraryAdapter;" }
				case 'InstallerAdapterModule' : { "use Joomla\\CMS\\Installer\\Adapter\\ModuleAdapter;" }
				case 'InstallerModule' : { "use Joomla\\CMS\\Installer\\Adapter\\ModuleAdapter;" }
				case 'InstallerAdapterPackage' : { "use Joomla\\CMS\\Installer\\Adapter\\PackageAdapter;" }
				case 'InstallerPackage' : { "use Joomla\\CMS\\Installer\\Adapter\\PackageAdapter;" }
				case 'InstallerAdapterPlugin' : { "use Joomla\\CMS\\Installer\\Adapter\\PluginAdapter;" }
				case 'InstallerPlugin' : { "use Joomla\\CMS\\Installer\\Adapter\\PluginAdapter;" }
				case 'InstallerAdapterTemplate' : { "use Joomla\\CMS\\Installer\\Adapter\\TemplateAdapter;" }
				case 'InstallerTemplate' : { "use Joomla\\CMS\\Installer\\Adapter\\TemplateAdapter;" }
				case 'InstallerManifestLibrary' : { "use Joomla\\CMS\\Installer\\Manifest\\LibraryManifest;" }
				case 'InstallerManifestPackage' : { "use Joomla\\CMS\\Installer\\Manifest\\PackageManifest;" }
				
				case 'RouterAdministrator' : { "use Joomla\\CMS\\Router\\AdministratorRouter;" }
				case 'Route' : { "use Joomla\\CMS\\Router\\Route;" }
				case 'Router' : { "use Joomla\\CMS\\Router\\Router;" }
				case 'RouterSite' : { "use Joomla\\CMS\\Router\\SiteRouter;" }
				
				case 'Categories' : { "use Joomla\\CMS\\Categories\\Categories;" }
				case 'CategoryNode' : { "use Joomla\\CMS\\Categories\\CategoryNode;" }
				
				case 'Date' : { "use Joomla\\CMS\\Date\\Date;" }
				
				case 'Log' : { "use Joomla\\CMS\\Log\\Log;" }
				case 'LogEntry' : { "use Joomla\\CMS\\Log\\LogEntry;" }
				case 'LogLogger' : { "use Joomla\\CMS\\Log\\Logger;" }
				case 'Logger' : { "use Joomla\\CMS\\Log\\Logger;" }
				case 'LogLoggerCallback' : { "use Joomla\\CMS\\Log\\Logger\\CallbackLogger;" }
				case 'LogLoggerDatabase' : { "use Joomla\\CMS\\Log\\Logger\\DatabaseLogger;" }
				case 'LogLoggerEcho' : { "use Joomla\\CMS\\Log\\Logger\\EchoLogger;" }
				case 'LogLoggerFormattedtext' : { "use Joomla\\CMS\\Log\\Logger\\FormattedtextLogger;" }
				case 'LogLoggerMessagequeue' : { "use Joomla\\CMS\\Log\\Logger\\MessagequeueLogger;" }
				case 'LogLoggerSyslog' : { "use Joomla\\CMS\\Log\\Logger\\SyslogLogger;" }
				case 'LogLoggerW3c' : { "use Joomla\\CMS\\Log\\Logger\\W3cLogger;" }
				
				case 'Profiler' : { "use Joomla\\CMS\\Profiler\\Profiler;" }
				
				case 'Uri' : { "use Joomla\\CMS\\Uri\\Uri;" }
				
				case 'Cache' : { "use Joomla\\CMS\\Cache\\Cache;" }
				case 'CacheController' : { "use Joomla\\CMS\\Cache\\CacheController;" }
				case 'CacheStorage' : { "use Joomla\\CMS\\Cache\\CacheStorage;" }
				case 'CacheControllerCallback' : { "use Joomla\\CMS\\Cache\\Controller\\CallbackController;" }
				case 'CacheControllerOutput' : { "use Joomla\\CMS\\Cache\\Controller\\OutputController;" }
				case 'CacheControllerPage' : { "use Joomla\\CMS\\Cache\\Controller\\PageController;" }
				case 'CacheControllerView' : { "use Joomla\\CMS\\Cache\\Controller\\ViewController;" }
				case 'CacheStorageApc' : { "use Joomla\\CMS\\Cache\\Storage\\ApcStorage;" }
				case 'CacheStorageApcu' : { "use Joomla\\CMS\\Cache\\Storage\\ApcuStorage;" }
				case 'CacheStorageHelper' : { "use Joomla\\CMS\\Cache\\Storage\\CacheStorageHelper;" }
				case 'CacheStorageCachelite' : { "use Joomla\\CMS\\Cache\\Storage\\CacheliteStorage;" }
				case 'CacheStorageFile' : { "use Joomla\\CMS\\Cache\\Storage\\FileStorage;" }
				case 'CacheStorageMemcached' : { "use Joomla\\CMS\\Cache\\Storage\\MemcachedStorage;" }
				case 'CacheStorageMemcache' : { "use Joomla\\CMS\\Cache\\Storage\\MemcacheStorage;" }
				case 'CacheStorageRedis' : { "use Joomla\\CMS\\Cache\\Storage\\RedisStorage;" }
				case 'CacheStorageWincache' : { "use Joomla\\CMS\\Cache\\Storage\\WincacheStorage;" }
				case 'CacheStorageXcache' : { "use Joomla\\CMS\\Cache\\Storage\\XcacheStorage;" }
				case 'CacheException' : { "use Joomla\\CMS\\Cache\\Exception\\CacheExceptionInterface;" }
				case 'CacheExceptionConnecting' : { "use Joomla\\CMS\\Cache\\Exception\\CacheConnectingException;" }
				case 'CacheExceptionUnsupported' : { "use Joomla\\CMS\\Cache\\Exception\\UnsupportedCacheException;" }
				
				case 'Session' : { "use Joomla\\CMS\\Session\\Session;" }
				case 'SessionExceptionUnsupported' : { "use Joomla\\CMS\\Session\\Exception\\UnsupportedStorageException;" }
				
				case 'User' : { "use Joomla\\CMS\\User\\User;" }
				case 'UserHelper' : { "use Joomla\\CMS\\User\\UserHelper;" }
				case 'UserWrapperHelper' : { "use Joomla\\CMS\\User\\UserWrapper';" }
				
				case 'Form' : { "use Joomla\\CMS\\Form\\Form;" }
				case 'FormField' : { "use Joomla\\CMS\\Form\\FormField;" }
				case 'FormHelper' : { "use Joomla\\CMS\\Form\\FormHelper;" }
				case 'FormRule' : { "use Joomla\\CMS\\Form\\FormRule;" }
				case 'FormWrapper' : { "use Joomla\\CMS\\Form\\FormWrapper';" }
				case 'FormFieldAuthor' : { "use Joomla\\CMS\\Form\\Field\\AuthorField;" }
				case 'FormFieldCaptcha' : { "use Joomla\\CMS\\Form\\Field\\CaptchaField;" }
				case 'FormFieldChromeStyle' : { "use Joomla\\CMS\\Form\\Field\\ChromestyleField;" }
				case 'FormFieldContenthistory' : { "use Joomla\\CMS\\Form\\Field\\ContenthistoryField;" }
				case 'FormFieldContentlanguage' : { "use Joomla\\CMS\\Form\\Field\\ContentlanguageField;" }
				case 'FormFieldContenttype' : { "use Joomla\\CMS\\Form\\Field\\ContenttypeField;" }
				case 'FormFieldEditor' : { "use Joomla\\CMS\\Form\\Field\\EditorField;" }
				case 'FormFieldFrontend_Language' : { "use Joomla\\CMS\\Form\\Field\\FrontendlanguageField;" }
				case 'FormFieldHeadertag' : { "use Joomla\\CMS\\Form\\Field\\HeadertagField;" }
				case 'FormFieldHelpsite' : { "use Joomla\\CMS\\Form\\Field\\HelpsiteField;" }
				case 'FormFieldLastvisitDateRange' : { "use Joomla\\CMS\\Form\\Field\\LastvisitdaterangeField;" }
				case 'FormFieldLimitbox' : { "use Joomla\\CMS\\Form\\Field\\LimitboxField;" }
				case 'FormFieldMedia' : { "use Joomla\\CMS\\Form\\Field\\MediaField;" }
				case 'FormFieldMenu' : { "use Joomla\\CMS\\Form\\Field\\MenuField;" }
				case 'FormFieldMenuitem' : { "use Joomla\\CMS\\Form\\Field\\MenuitemField;" }
				case 'FormFieldModuleOrder' : { "use Joomla\\CMS\\Form\\Field\\ModuleorderField;" }
				case 'FormFieldModulePosition' : { "use Joomla\\CMS\\Form\\Field\\ModulepositionField;" }
				case 'FormFieldModuletag' : { "use Joomla\\CMS\\Form\\Field\\ModuletagField;" }
				case 'FormFieldOrdering' : { "use Joomla\\CMS\\Form\\Field\\OrderingField;" }
				case 'FormFieldPlugin_Status' : { "use Joomla\\CMS\\Form\\Field\\PluginstatusField;" }
				case 'FormFieldRedirect_Status' : { "use Joomla\\CMS\\Form\\Field\\RedirectStatusField;" }
				case 'FormFieldRegistrationDateRange' : { "use Joomla\\CMS\\Form\\Field\\RegistrationdaterangeField;" }
				case 'FormFieldStatus' : { "use Joomla\\CMS\\Form\\Field\\StatusField;" }
				case 'FormFieldTag' : { "use Joomla\\CMS\\Form\\Field\\TagField;" }
				case 'FormFieldTemplatestyle' : { "use Joomla\\CMS\\Form\\Field\\TemplatestyleField;" }
				case 'FormFieldUserActive' : { "use Joomla\\CMS\\Form\\Field\\UseractiveField;" }
				case 'FormFieldUserGroupList' : { "use Joomla\\CMS\\Form\\Field\\UsergrouplistField;" }
				case 'FormFieldUserState' : { "use Joomla\\CMS\\Form\\Field\\UserstateField;" }
				case 'FormFieldUser' : { "use Joomla\\CMS\\Form\\Field\\UserField;" }
				case 'FormRuleBoolean' : { "use Joomla\\CMS\\Form\\Rule\\BooleanRule;" }
				case 'FormRuleCalendar' : { "use Joomla\\CMS\\Form\\Rule\\CalendarRule;" }
				case 'FormRuleCaptcha' : { "use Joomla\\CMS\\Form\\Rule\\CaptchaRule;" }
				case 'FormRuleColor' : { "use Joomla\\CMS\\Form\\Rule\\ColorRule;" }
				case 'FormRuleEmail' : { "use Joomla\\CMS\\Form\\Rule\\EmailRule;" }
				case 'FormRuleEquals' : { "use Joomla\\CMS\\Form\\Rule\\EqualsRule;" }
				case 'FormRuleNotequals' : { "use Joomla\\CMS\\Form\\Rule\\NotequalsRule;" }
				case 'FormRuleNumber' : { "use Joomla\\CMS\\Form\\Rule\\NumberRule;" }
				case 'FormRuleOptions' : { "use Joomla\\CMS\\Form\\Rule\\OptionsRule;" }
				case 'FormRulePassword' : { "use Joomla\\CMS\\Form\\Rule\\PasswordRule;" }
				case 'FormRuleRules' : { "use Joomla\\CMS\\Form\\Rule\\RulesRule;" }
				case 'FormRuleTel' : { "use Joomla\\CMS\\Form\\Rule\\TelRule;" }
				case 'FormRuleUrl' : { "use Joomla\\CMS\\Form\\Rule\\UrlRule;" }
				case 'FormRuleUsername' : { "use Joomla\\CMS\\Form\\Rule\\UsernameRule;" }
				
				case 'Microdata' : { "use Joomla\\CMS\\Microdata\\Microdata;" }
				
				case 'Factory' : { "use Joomla\\CMS\\Factory;" }
				
				case 'Mail' : { "use Joomla\\CMS\\Mail\\Mail;" }
				case 'MailHelper' : { "use Joomla\\CMS\\Mail\\MailHelper;" }
				case 'MailWrapperHelper' : { "use Joomla\\CMS\\Mail\\MailWrapper;" }
				
				case 'ClientHelper' : { "use Joomla\\CMS\\Client\\ClientHelper;" }
				case 'ClientWrapperHelper' : { "use Joomla\\CMS\\Client\\ClientWrapper;" }
				case 'ClientFtp' : { "use Joomla\\CMS\\Client\\FtpClient;" }
				case 'FTP' : { "use Joomla\\CMS\\Client\\FtpClient';" }
				case 'ClientLdap' : { "use Joomla\\Ldap\\LdapClient;" }
				case 'LDAP' : { "use Joomla\\Ldap\\LdapClient';" }
				
				case 'Update' : { "use Joomla\\CMS\\Updater\\Update;" }
				case 'UpdateAdapter' : { "use Joomla\\CMS\\Updater\\UpdateAdapter;" }
				case 'Updater' : { "use Joomla\\CMS\\Updater\\Updater;" }
				case 'UpdaterCollection' : { "use Joomla\\CMS\\Updater\\Adapter\\CollectionAdapter;" }
				case 'UpdaterExtension' : { "use Joomla\\CMS\\Updater\\Adapter\\ExtensionAdapter;" }
				
				case 'Crypt' : { "use Joomla\\CMS\\Crypt\\Crypt;" }
				case 'CryptCipher' : { "use Joomla\\CMS\\Crypt\\CipherInterface;" }
				case 'CryptKey' : { "use Joomla\\CMS\\Crypt\\Key;" }
				case 'CryptPassword' : { "use Joomla\\CMS\\Crypt\\CryptPassword';" }
				case 'CryptCipherBlowfish' : { "use Joomla\\CMS\\Crypt\\Cipher\\BlowfishCipher';" }
				case 'CryptCipherCrypto' : { "use Joomla\\CMS\\Crypt\\Cipher\\CryptoCipher;" }
				case 'CryptCipherMcrypt' : { "use Joomla\\CMS\\Crypt\\Cipher\\McryptCipher';" }
				case 'CryptCipherRijndael256' : { "use Joomla\\CMS\\Crypt\\Cipher\\Rijndael256Cipher';" }
				case 'CryptCipherSimple' : { "use Joomla\\CMS\\Crypt\\Cipher\\SimpleCipher';" }
				case 'CryptCipher3Des' : { "use Joomla\\CMS\\Crypt\\Cipher\\TripleDesCipher';" }
				case 'CryptPasswordSimple' : { "use Joomla\\CMS\\Crypt\\Password\\SimpleCryptPassword';" }
				
				case 'StringPunycode' : { "use Joomla\\CMS\\String\\PunycodeHelper;" }
				
				case 'Buffer' : { "use Joomla\\CMS\\Utility\\BufferStreamHandler;" }
				case 'Utility' : { "use Joomla\\CMS\\Utility\\Utility;" }
				
				case 'InputCli' : { "use Joomla\\CMS\\Input\\Cli;" }
				case 'InputCookie' : { "use Joomla\\CMS\\Input\\Cookie;" }
				case 'InputFiles' : { "use Joomla\\CMS\\Input\\Files;" }
				case 'Input' : { "use Joomla\\CMS\\Input\\Input;" }
				case 'InputJSON' : { "use Joomla\\CMS\\Input\\Json;" }
				
				case 'Feed' : { "use Joomla\\CMS\\Feed\\Feed;" }
				case 'FeedEntry' : { "use Joomla\\CMS\\Feed\\FeedEntry;" }
				case 'FeedFactory' : { "use Joomla\\CMS\\Feed\\FeedFactory;" }
				case 'FeedLink' : { "use Joomla\\CMS\\Feed\\FeedLink;" }
				case 'FeedParser' : { "use Joomla\\CMS\\Feed\\FeedParser;" }
				case 'FeedPerson' : { "use Joomla\\CMS\\Feed\\FeedPerson;" }
				case 'FeedParserAtom' : { "use Joomla\\CMS\\Feed\\Parser\\AtomParser;" }
				case 'FeedParserNamespace' : { "use Joomla\\CMS\\Feed\\Parser\\NamespaceParserInterface;" }
				case 'FeedParserRss' : { "use Joomla\\CMS\\Feed\\Parser\\RssParser;" }
				case 'FeedParserRssItunes' : { "use Joomla\\CMS\\Feed\\Parser\\Rss\\ItunesRssParser;" }
				case 'FeedParserRssMedia' : { "use Joomla\\CMS\\Feed\\Parser\\Rss\\MediaRssParser;" }
				
				case 'Image' : { "use Joomla\\CMS\\Image\\Image;" }
				case 'ImageFilter' : { "use Joomla\\CMS\\Image\\ImageFilter;" }
				case 'ImageFilterBackgroundfill' : { "use Joomla\\Image\\Filter\\Backgroundfill;" }
				case 'ImageFilterBrightness' : { "use Joomla\\Image\\Filter\\Brightness;" }
				case 'ImageFilterContrast' : { "use Joomla\\Image\\Filter\\Contrast;" }
				case 'ImageFilterEdgedetect' : { "use Joomla\\Image\\Filter\\Edgedetect;" }
				case 'ImageFilterEmboss' : { "use Joomla\\Image\\Filter\\Emboss;" }
				case 'ImageFilterNegate' : { "use Joomla\\Image\\Filter\\Negate;" }
				case 'ImageFilterSketchy' : { "use Joomla\\Image\\Filter\\Sketchy;" }
				case 'ImageFilterSmooth' : { "use Joomla\\Image\\Filter\\Smooth;" }
				
				case 'Object' : { "use Joomla\\CMS\\Object\\CMSObject;" }
				
				case 'ExtensionHelper' : { "use Joomla\\CMS\\Extension\\ExtensionHelper;" }
				
				case 'Html' : { "use Joomla\\CMS\\HTML\\HTMLHelper;" }
				
				default: { "use " + className + ";"}
			}»
		«ENDFOR»
	'''
	
	def static String getSectioName(Section reference) {
		if(reference instanceof BackendSection)
		return 'admin'
		return ''	
	}

	
	def static CharSequence databaseName(String componentName, String entityName) {
		return "#__" + componentName.toLowerCase + "_" + entityName.toLowerCase
	}
	
	def static Boolean isAttributeLinked(ExtendedAttribute attr, DynamicPage page) {
		for (Link ref: page.links) {
			if (ref.linkedAttribute !== null) {
			    if (ref.linkedAttribute.name.equalsIgnoreCase(attr.name)) {
			        return true
			    }
			}
		}
		return false
	}
	
	def static CharSequence linkOfAttribut(ExtendedAttribute attribute, ExtendedDynamicPage  page, String compname, String valuefeatures) '''
	    «FOR Link lk: page.links»
	    «IF lk.linkedAttribute !== null»
	    «IF lk.linkedAttribute.name.equalsIgnoreCase(attribute.name)»
	    «switch lk {
		    ExternalLink : {
			    '''«(new LinkGeneratorHandler(lk, '', compname, valuefeatures )).generateLink»'''
		    }
		    InternalLink: {
		        if ((lk as InternalLink).target instanceof DetailsPage) {
		            if ((page.instance as DynamicPage).entities.get(0).name.equals((lk.target as DynamicPage).entities.get(0).name)) {
		                if (!(lk instanceof ContextLink)) {
		                    '''«(new LinkGeneratorHandler(lk, '', compname, valuefeatures )).generateLink» . '&«page.extendedEntityList.get(0).primaryKey.name»='.(int) $item->«page.extendedEntityList.get(0).primaryKey.name» '''
		                } else {
		                    if ((lk as ContextLink).linkparameters.filter[t | t.id].size == 0) {
		                        '''«(new LinkGeneratorHandler(lk, '', compname, valuefeatures )).generateLink»  . '&«page.extendedEntityList.get(0).primaryKey.name»='.(int) $item->«page.extendedEntityList.get(0).primaryKey.name» '''
		                    } else {
		    		            '''«(new LinkGeneratorHandler(lk, '', compname, valuefeatures )).generateLink»  . '&«page.extendedEntityList.get(0).primaryKey.name»='.(int) $item->«page.extendedEntityList.get(0).primaryKey.name» '''
		   			        }
		   		        }
		   	        } else {
		   	            var ExtendedAttribute idRef = Slug.getAttributeForForeignID(attribute, page)
		    	        var Entity entityRef = Slug.getEntityForForeignID(attribute, page)
		    	
			            if (idRef !== null) {
				            '''«(new LinkGeneratorHandler(lk, '', compname, valuefeatures )).generateLink» . '&«Slug.getPrimaryKeys(entityRef).name»='.(int) $item->«idRef.name»'''	
				        } else {
				 	        '''«(new LinkGeneratorHandler(lk, '', compname, valuefeatures )).generateLink» . '&«Slug.getPrimaryKeys(entityRef).name»='.(int) $model->getIdOfReferenceItem("«(lk as InternalLink).name.toLowerCase»",$item)'''
		 	            }
		 	        }
                } else {
		            '''«(new LinkGeneratorHandler(lk, '', compname, valuefeatures )).generateLink» . '&filter.search='. $item->«attribute.name.toLowerCase»'''
		        }
            }
        }»
	    «ENDIF»
	    «ENDIF»
	    «ENDFOR»
	'''
	
	def static Entity getEntityForForeignID(ExtendedAttribute attr, ExtendedDynamicPage dynPage) {
		for (ExtendedReference ref: dynPage.extendedEntityList.get(0).allExtendedReferences) {
			if (ref.extendedAttributes.get(0).name.equalsIgnoreCase(attr.name)) {
				return ref.destinationEntity
			}
		}
	}
	
	def static ExtendedAttribute getAttributeForForeignID(ExtendedAttribute attr, ExtendedDynamicPage dynPage){
		for (ExtendedReference ref: dynPage.extendedEntityList.get(0).allExtendedReferences){
			if (ref.extendedAttributes.get(0).name.equalsIgnoreCase(attr.name)) {
				for (ExtendedAttribute refAttr: ref.referencedExtendedAttributes) {
					if (refAttr.name.equalsIgnoreCase("id")) {
						return ref.extendedAttributes.get(ref.referencedExtendedAttributes.indexOf(refAttr))
					}
				}
			}
		}
		return null
	}
	
	def static Boolean isLinkedAttributeReference(Attribute attribute, DynamicPage page) {
		for (Entity e: page.entities) {
			for (Reference ref: e.references) {
				if (ref.attribute.get(0).equals(attribute)) {
					return true
				}
			}
		}
		return false
	}
	
	def static Reference searchLinkedAttributeReference(Attribute attribute, DynamicPage page) {
		for (Entity e: page.entities) {
			for (Reference ref: e.references) {
				if (ref.attribute.get(0).name.equalsIgnoreCase(attribute.name)) {
				    return ref
				}
			}
		}
		return null
	}
	
	static def CharSequence genLinkedInfo(DynamicPage page, Component com)'''
 	    private  $entitiesRef = array(
 	    «FOR Link linkItem: page.links»
 	    «switch linkItem {
 		    InternalLink :{
 			    if (isLinkedAttributeReference(linkItem.linkedAttribute, page)) {
 				    var Reference ref = Slug.searchLinkedAttributeReference(linkItem.linkedAttribute, page);
 				    '''
 				    "«linkItem.name.toLowerCase»" => array("db"=> "#__«com.name.toLowerCase»_«ref.entity.name.toLowerCase»","refattr" => array(«Slug.generateAttributeAndRefernce(ref)»
 				    ), "foreignPk" => "«Slug.getPrimaryKeys(ref.entity).name.toLowerCase»"),'''	
 			    }				
 		    }	
 	    }»
 	    «ENDFOR»
 	    null);
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
	
	static def CharSequence transformAttributeListInString(EList<Attribute>attributes, String separeSign) {
		var StringBuffer result = new StringBuffer()
		for (attr: attributes) {
			if (attr != attributes.last) {
			result.append(attr.name.toLowerCase + separeSign)
			} else {
				result.append(Slug.slugify(attr.name))
			}
		}
		return result.toString
	}
	
	static def CharSequence transformAttributeListInString(String postWord, EList<Attribute>attributes, String separeSign){
		var StringBuffer result = new StringBuffer()
		for (attr: attributes) {
			if (attr != attributes.last) {
			    result.append(postWord + Slug.slugify(attr.name).toLowerCase + separeSign)
			} else {
				result.append(postWord+ Slug.slugify(attr.name))
			}
		}
		return result.toString

	}
	static def CharSequence transformAttributeListInString(String postWord, EList<Attribute>attributes, String separeSign,String afterWord){
		var StringBuffer result = new StringBuffer()
		for (attr: attributes) {
			if (attr != attributes.last) {
			    result.append(postWord + Slug.slugify(attr.name).toLowerCase + afterWord + separeSign)
			} else {
				result.append(postWord+ Slug.slugify(attr.name)+afterWord)
			}
		}
		return result.toString

	}
	static def CharSequence transformAttributeListInString(String quotationMark, String postWord, EList<Attribute>attributes, String separeSign){
		var StringBuffer result = new StringBuffer()
		for (attr: attributes) {
			if (attr != attributes.last) {
			    result.append(quotationMark+postWord + Slug.slugify(attr.name).toLowerCase+quotationMark + separeSign)
			} else {
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
		for (ExtendedDetailPageField field:dynPage.extendedEditedFieldsList ) {
   		    if (field.extendedAttribute.name.equalsIgnoreCase(attr.name)) {
   			    return field
   			}
   		}
   		return null
	}
	
    //get all other referenced in the referenced Entity	
	def static EList<Attribute> getOtherAttribute(ExtendedReference reference) {
	    var Entity toEntity = reference.destinationEntity
	    var Reference ref = (toEntity.references.filter[t | !t.entity.name.equalsIgnoreCase( reference.sourceEntity.name)]).get(0)
	    return ref.attribute
	}
	
	def static Entity getOtherEntityToMapping(ExtendedReference reference) {
	    var Entity toEntity = reference.destinationEntity
	    var Reference ref = (toEntity.references.filter[t | !t.entity.name.equalsIgnoreCase(reference.sourceEntity.name)]).get(0)
	    return ref.entity
	}
	
	def static CharSequence generateEntytiesBackendInputRefrence(ExtendedReference reference, ExtendedComponent com) '''
	    <?php if (Factory::getUser()->authorise('core.admin','«com.name.toLowerCase»')) : ?>
	    <?php echo HTMLHelper::_('bootstrap.addTab', 'myTab', '«Slug.getOtherEntityToMapping(reference).name.toLowerCase»', Text::_('«Slug.nameExtensionBind("com",com.name).toUpperCase»_«Slug.getOtherEntityToMapping(reference).name.toUpperCase»', true)); ?>
	    <div class="control-group">
	        <div class="control-label"><?php echo $this->form->getLabel('«reference.entity.name.toLowerCase»_id'); ?></div>
	        <div class="controls"><?php echo $this->form->getInput('«reference.entity.name.toLowerCase»_id'); ?></div>
	    </div>
	    «FOR attribute: Slug.getOtherAttribute(reference)»
	    <div class="control-group">
	        <div class="controls"><?php echo $this->form->getInput('«attribute.name.toLowerCase»'); ?></div>
	    </div>
	    «ENDFOR»
	    <?php echo HTMLHelper::_('bootstrap.endTab'); ?>
	    <?php endif; ?>
	'''
	
	def static CharSequence generateEntytiesSiteInputRefrence(ExtendedReference reference,ExtendedComponent com) '''
		<?php if (Factory::getUser()->authorise('core.admin','«com.name.toLowerCase»')) : ?>
		<?php echo HTMLHelper::_('bootstrap.addTab', 'myTab', '«Slug.getOtherEntityToMapping(reference).name.toLowerCase»', Text::_('«Slug.nameExtensionBind("com",com.name).toUpperCase»_«Slug.getOtherEntityToMapping(reference).name.toUpperCase»', true)); ?>
		<div class="control-group">
		    <div class="control-label"><?php echo $this->form->getLabel('«reference.entity.name.toLowerCase»_id'); ?></div>
		    <div class="controls"><?php echo $this->form->getInput('«reference.entity.name.toLowerCase»_id'); ?></div>
		</div>
		«FOR attribute: Slug.getOtherAttribute(reference)»
		<div class="control-group">
		    <div class="controls"><?php echo $this->form->getInput('«attribute.name.toLowerCase»'); ?></div>
		</div>
		«ENDFOR»
		<?php echo HTMLHelper::_('bootstrap.endTab'); ?>
		<?php endif; ?>
	'''
	
	def static Attribute getPrimaryKeys(Entity entity) {
		for(Attribute attr: entity.attributes) {
			if(attr.isIsprimary)
			return attr
		}
	}
	
	def static void deleteFolder(String folder) {
	    var File root = new File(folder)
	    for (File item : root.listFiles) {
	        if (item.directory) {
	            deleteFolder(item.path)
	        } else {
	            item.delete
	        }
	    }
	    root.delete
	}
}
