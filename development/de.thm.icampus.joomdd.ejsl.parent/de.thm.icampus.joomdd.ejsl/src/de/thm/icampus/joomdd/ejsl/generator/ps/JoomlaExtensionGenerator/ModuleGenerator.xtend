/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage
import de.thm.icampus.joomdd.ejsl.eJSL.Link
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.LinkGeneratorClient
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.PageGeneratorClient
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.LanguageGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import java.util.Calendar
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Generator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eJSLGenerator.ModuleGenerator#getPageClient <em>Page Client</em>}</li>
 * </ul>
 * </p>
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getModuleGenerator()
 * @model
 * @generated
 */
public class ModuleGenerator extends AbstractExtensionGenerator {  
			 
	PageGeneratorClient pageClient
	var modelOfComponent = '\'<modelOfComponent>\''
	var modelPath = '\'/components/com_<nameOfComponent>/models\''
	var String modelOfComponent2 = null;
		

	ExtendedModule extMod
	ExtendedDynamicPage dynpage
	String com

	new(ExtendedModule module, IFileSystemAccess2 fsa, String path) {
		this.fsa = fsa
		this.name = 'mod_' + Slug.slugify(module.name)
		this.extMod = module
		dynpage = module.extendedPageReference.extendedPage.extendedDynamicPageInstance
		com = module.extendedComponentName
		this.ComponentInformation(module)
		this.extMod.formatName
		this.path = path
	}
	
	def void formatName(Module module){
		module.name = Slug.slugify(module.name)
	}

	public def PageGeneratorClient getPageClient() {
		return this.pageClient
	}

	public def void setPageClient(PageGeneratorClient value) {
		this.pageClient = value;
	}

	override generate() {
		generateJoomlaDirectory(path+"")
		println("module path " + path)
		generateFile(path + "index" + ".html", this.extMod.IndexContent)
		generateFile(path + name + ".xml", this.extMod.xmlContent)
		generateFile(path+name + ".php", this.extMod.phpContent)
		generateFile(path+"helper.php", helperPHP(extMod, extMod.pageRef.page as DynamicPage	))
		
		
		generateJoomlaDirectory(path+"tmpl")
		generateFile(path+ "tmpl/default.php", defaultTemplate())
		generateFile(path+"tmpl/index.html", this.extMod.IndexContent)
		
		var LanguageGenerator lang = new LanguageGenerator(fsa)
		lang.genModuletLanguage(extMod, path)
         
		return ''
	}
	
	
	
	
 

	
	def CharSequence xmlContent(ExtendedModule module) {
		'''
		<?xml version="1.0" encoding="utf-8"?>
		<extension type="module" version="3.3" client="site" method="upgrade">
			<name>«module.name.toFirstUpper»</name>
			<creationDate>«if (module.manifest.creationdate != null) {
				module.manifest.creationdate
			} else {
				Calendar::instance.get(Calendar.YEAR)
			}»</creationDate>
			<copyright>«if (module.manifest.copyright != null) {
				module.manifest.copyright
			} else {
				"All rights reserved by Author."
			}»</copyright>
			<license>«if (module.manifest.license != null) {
				module.manifest.license
			} else {
				"GNU General Public License version 2 or later"
			}»</license>
			«Slug.generateAuthors(module.manifest.authors)»
			<version>«if (module.manifest.version != null) {
				module.manifest.version
			} else {
				"1.0.0"
			}»</version>
			<description>«if (module.manifest.description != null) {
			module.manifest.description
		} else {
			"Place Description here"
		}»</description>
			<!-- Listing of all files that should be installed for the module -->
			<files>
				<filename module="«name»">«name».php</filename>
				<filename>index.html</filename>
				<filename>helper.php</filename>
				<folder>
					<filename>tmpl/index.html</filename>
					<filename>tmpl/default.php</filename>
				</folder>
				<folder>language</folder>
			</files>
			<dependency>
				
			</dependency>
			<!-- All language files shipped with the modul -->
			<languages>
				«FOR lang : module.languages»
				<language tag="«lang.name»">language/«lang.name»/«lang.name».«name».ini</language>
				<language tag="«lang.name»">language/«lang.name»/«lang.name».«name».sys.ini</language>
				«ENDFOR»
			</languages>
			<!-- Optional parameters -->
			<config>
			<fields name="params">
			   <fieldset name="basic">
			   «IF dynpage != null»
			   <field name="ordering" type="list"
		   	        label="«Slug.nameExtensionBind("mod",module.name).toUpperCase»_ORDERING"
		   	        description="«Slug.nameExtensionBind("mod",module.name).toUpperCase»_JFIELD_ORDERING_DESC"
		   	        class="inputbox"
		   	        default="id">
		   	      <option value="id">ID</option>  
		   	   «FOR ExtendedAttribute attr: dynpage.extendFiltersList»
			     <option value="«attr.name.toLowerCase»">«Slug.nameExtensionBind("mod",module.name).toUpperCase»_FORM_LBL_«attr.name.toUpperCase»</option>
    	        «ENDFOR»
			    </field>
			    «ENDIF»
			    <field name="direction" type="list"
		   	        label="«Slug.nameExtensionBind("mod",module.name).toUpperCase»_DIRECTION"
		   	        description="«Slug.nameExtensionBind("mod",module.name).toUpperCase»_JFIELD_DIRECTION_DESC"
		   	        class="inputbox"
		   	        size="1"
		   	        default="ASC">
		   	        <option value="ASC">«Slug.nameExtensionBind("mod",module.name).toUpperCase»_ASC</option>
		   	        <option value="DESC">«Slug.nameExtensionBind("mod",module.name).toUpperCase»_DESC</option>
		   	     </field>
			    		   	        
			   <field
					name="start"
					type="int"
					default="0"
					label="MOD_«module.name.toUpperCase»_START_LABEL"
					description="MOD_«module.name.toUpperCase»_START_DESC" />
				 <field
					name="limit"
					type="int"
					default="10"
					label="MOD_«module.name.toUpperCase»_LIMIT_LABEL"
					description="MOD_«module.name.toUpperCase»_LIMIT_DESC" />
				 <field
					name="search"
					type="text"
					label="MOD_«module.name.toUpperCase»_SEARCH_LABEL"
					description="MOD_«module.name.toUpperCase»_SEARCH_DESC" />
			   
			    <field name="state" type="list"
			   	        label="MOD_«module.name.toUpperCase»_JSTATUS"
			   	        description="MOD_«module.name.toUpperCase»_JFIELD_PUBLISHED_DESC"
			   	        class="inputbox"
			   	        size="1"
			   	        default="1">
			   	        <option value="1">JPUBLISHED</option>
			   	        <option value="0">JUNPUBLISHED</option>
			   	        <option value="2">JARCHIVED</option>
			   	        <option value="-2">JTRASHED</option>
			   	    </field>
			</fieldset>
			«IF com != null && dynpage !=null»
			<fieldset name="filter">
			 <field
              name="created_by"
               addfieldpath="administrator/components/«Slug.nameExtensionBind("com",com).toLowerCase»/models/fields"
              type="componentuser"
              label="«Slug.nameExtensionBind("mod", module.name).toUpperCase»_FILTER_CREATED_BY"
              description="«Slug.nameExtensionBind("mod", module.name).toUpperCase»_FILTER_CREATED_BY"
               entity = "«dynpage.extendedEntityList.get(0).name.toLowerCase»"
              >
              <option value="">JOPTION_SELECT_CREATED_BY</option>
              </field>
            «FOR ExtendedAttribute attr : dynpage.extendFiltersList»
             <field
                addfieldpath="administrator/components/«Slug.nameExtensionBind("com",com).toLowerCase»/models/fields"
                  name="«attr.name»"
                  type="«dynpage.extendedEntityList.get(0).name.toLowerCase»"
                  label="«Slug.nameExtensionBind("mod", module.name).toUpperCase»_FILTER_«attr.name.toUpperCase»"
                  description="«Slug.nameExtensionBind("mod", module.name).toUpperCase»_FILTER_«attr.name.toUpperCase»"
                   valueColumn="«attr.entity.name.toLowerCase».«attr.name.toLowerCase»"
                   textColumn="«attr.entity.name.toLowerCase».«attr.name.toLowerCase»"
                  >
              <option value="">JOPTION_SELECT_«attr.name.toUpperCase»</option>
          </field>
         «ENDFOR»
			</fieldset>
		«ENDIF»
			</fields>
			</config>
		</extension>
		'''
	}

	def CharSequence phpContent(ExtendedModule modul) {
		'''
		«IF modul.pageRef.pagescr != null»
			«var c = modul.extendedComponentName»
		<?php
		«Slug.generateFileDoc(extMod, true)»
		
			// Define used Jimports here

			// No direct access to this file
				defined('_JEXEC') or die;
		
				// Include the «extMod.name» functions only once
				require_once __DIR__ . '/helper.php';
				require_once JPATH_ADMINISTRATOR . '/components/com_«c.toLowerCase»/helpers/«c.toLowerCase».php';

				
			«ELSE»
		<?php
		«Slug.generateFileDoc(extMod, true)»
		
			// Define used Jimports here
		
			// No direct access to this file
				defined('_JEXEC') or die;
		
				// Include the «extMod.name» functions only once
				require_once __DIR__ . '/helper.php';
			«ENDIF»
			// Models, Functions should be implementated here
		    // «modul.name.substring(0,1).toUpperCase + modul.name.substring(1).toLowerCase»Helper::updateReset();
			$items = &«modul.name.substring(0,1).toUpperCase + modul.name.substring(1).toLowerCase»Helper::getList($params);
			$moduleclass_sfx = htmlspecialchars($params->get('moduleclass_sfx'));
			require JModuleHelper::getLayoutPath('«name»', $params->get('layout', 'default'));
			'''
	}
	
	 def CharSequence defaultTemplate() {
		'''
		<?php
		«Slug.generateFileDoc(extMod, true)»
			// No direct access to this file
			defined('_JEXEC') or die;
			/**
			 *if Component-Helper to be used
			 *
			 *require_once JPATH_ROOT . '/components/com_<nameOfComponent>/helpers/<nameOfComponentHelper.php>';
			 *$baseurl = JUri::base();
			 */
			?>
			<ul class="«extMod.name»<?php echo $moduleclass_sfx; ?>">
		
			<?php foreach ($items as $item) : ?>
			<li><div class="«extMod.pageRef.page.name»item">
			<?php if (empty($item)) : ?>
				«IF dynpage.entities.isEmpty»
					<?php// itemlist is empty ;?>
					<!DOCTYPE html><titel></titel>
				«ELSE»
					<?php echo "itemlist is empty" ?>
					<!DOCTYPE html><titel></titel>
				<?php else : ?>
										
					«FOR ExtendedAttribute attr : dynpage.extendedTableColumnList»
					<?php $«attr.name» = $item->«attr.name.toLowerCase»;?>
					<?php echo «checkLinkOfAttributes(attr, extMod.pageRef.page.links)»; ?>
					«ENDFOR»
						
				«ENDIF»
				<?php endif; ?>
		</div></li>
		
		<?php endforeach; ?>
		</ul>
		'''
	}
	
	def checkLinkOfAttributes(ExtendedAttribute attribute, EList<Link> listLink) {
		var String result = attribute.name.toString
		if(extMod.pageRef.sect == null || extMod.pageRef.pagescr == null)
		return "$" + result;
		
		for(Link lk: listLink){
			if(lk.linkedAttribute.name.equalsIgnoreCase(attribute.name)){
			var LinkGeneratorClient lkClient = new LinkGeneratorClient(lk, Slug.getSectioName(extMod.pageRef.sect),  extMod.extendedComponentName.toLowerCase,"$item->") 
				
			   return '''JHtml::_('link',«lkClient.generateLink», $item->«attribute.name.toLowerCase»)'''
			   
			   }
		}
		return "$" + result;
	}
	
	
	def CharSequence helperPHP(Module modul, DynamicPage mpage) {
		'''
		<?php
		«Slug.generateFileDoc(extMod, true)»
		
		// No direct access to this file
		defined('_JEXEC') or die;
		/**
		 *Helper for «extMod.name»
		 *
		 * @category
		 * @package
		 * @since
		 *
		 */
		class «modul.name.substring(0,1).toUpperCase + modul.name.substring(1).toLowerCase»Helper
		{
			«genGetList»
		}
		'''
	}
	
		def ComponentInformation (ExtendedModule modul) {
			if( modul.pageRef.pagescr != null){
			var String section =  modul.pageRef.sect.getName()
            var String compName =  modul.extendedComponentName
    						if(section.equalsIgnoreCase('backend')){
			    				modelPath = "'/administrator/components/com_" + compName.toLowerCase + "/models'"    		   					
    		   				} else{
			    				modelPath = "'/components/com_" + compName.toLowerCase + "/models'"    		   					
    		   				}
			    			modelOfComponent = ("\"" + compName.toFirstUpper + "\"")
			    			modelOfComponent2 = ("\"" +compName.toFirstUpper + "Model\"")
			    		
			    	}		
    		}	

	
		def CharSequence IndexContent(Module modul) {
		'''
	<html>
		<head>
		</head>
		<body>
		</body>
	</html>
		'''
	}
	
	public def genGetList()'''
	/**
		 * @param
		 *
		 * @return
		 *
		 * @since
		 **/
		public static function &getList($params = null)
		{
		
		/**
		 * placeholder "<>" are to be replaced
		*/
		JModelLegacy::addIncludePath(JPATH_ROOT . «modelPath», «modelOfComponent»);
		
		// $app = JFactory::getApplictation();
			    «IF (extMod.pageRef.pagescr != null )»
			    $model = JModelLegacy::getInstance('«extMod.pageRef.page.name»', «modelOfComponent2», array('ignore_request' => true));
			    
				«ELSE»
			$model = JModelLegacy::getInstance('<type>', <modelOfComponent>, array('ignore_request' => true));
		
				«ENDIF»
			$state = $params->get('state');
			if(!empty($state))
			$model->setState('filter.state', $state);
			
			$search = $params->get('search');
			if(!empty($search))
			$model->setState('filter.search', $search);
			
			$created_by = $params->get('created_by');
			 if(!empty($created_by))
			$model->setState('filter.search',$created_by);
			
			$ordering = $params->get('ordering');
			if(!empty($ordering))
			$model->setState('list.ordering',$ordering);
			
			$direction = $params->get('direction');
			 if(!empty($direction))
			$model->setState('list.direction', $direction);
			
			$start = $params->get('start');
			if(!empty($start))
			$model->setState('list.start', $start);
			
			$limit = $params->get('limit');
			if(!empty($limit))
			$model->setState('list.limit', $limit);
			«IF dynpage != null»
			«FOR ExtendedAttribute attr: dynpage.extendFiltersList»
			$«attr.name.toLowerCase» = $params->get('«attr.name.toLowerCase»');
			if(!empty($«attr.name.toLowerCase» ))
			$model->setState('filter.«attr.name.toLowerCase»', $«attr.name.toLowerCase» );
			«ENDFOR»
			«ENDIF»
			$items = $model->getItems();

			 return $items;
			}	
	'''
	
		
	

} // ModuleGenerator
