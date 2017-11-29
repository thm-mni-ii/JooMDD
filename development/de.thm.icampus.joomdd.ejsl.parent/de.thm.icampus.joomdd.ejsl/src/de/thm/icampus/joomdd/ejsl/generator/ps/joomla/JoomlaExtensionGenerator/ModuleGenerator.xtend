package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaExtensionGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage
import de.thm.icampus.joomdd.ejsl.eJSL.Link
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaExtensionGenerator.AbstractExtensionGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaPageGenerator.PageGeneratorClient
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.LanguageGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug
import java.util.Calendar
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2

/**
 * This class contains the templates to generate the necessary folders and files for a Joomla module.
 *
 * @author Dieudonne Timma, Dennis Priefer
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
	
	def void formatName(Module module) {
		module.name = Slug.slugify(module.name)
	}

	public def PageGeneratorClient getPageClient() {
		return this.pageClient
	}

	public def void setPageClient(PageGeneratorClient value) {
		this.pageClient = value;
	}

	override generate() {
		generateFile(path + name + ".xml", this.extMod.xmlContent)
		generateFile(path + name + ".php", this.extMod.phpContent)
		generateFile(path + "helper.php", helperPHP(extMod, extMod.pageRef.page as DynamicPage))
		generateFile(path + "tmpl/default.php", defaultTemplate())
		generateEmptyDirectory("language")
		var LanguageGenerator lang = new LanguageGenerator(fsa)
		lang.genModuletLanguage(extMod, path)
       
		return ''
	}
		
	def CharSequence xmlContent(ExtendedModule module) {
		'''
		<?xml version="1.0" encoding="utf-8"?>
		<extension type="module" version="3.8" client="site" method="upgrade">
			<name>«module.name.toFirstUpper»</name>
			<creationDate>
			    «if (module.manifest.creationdate !== null) {
			        module.manifest.creationdate
			    } else {
				    Calendar::instance.get(Calendar.YEAR)
			    }»
			</creationDate>
			<copyright>
			    «if (module.manifest.copyright !== null) {
			        module.manifest.copyright
			    } else {
				    "Generated with JooMDD by Institute for Information Sciences, THM, Germany"
			    }»
			</copyright>
			<license>
			    «if (module.manifest.license !== null) {
			        module.manifest.license
			    } else {
			        "GNU General Public License version 2 or later"
			    }»
			</license>
			«Slug.generateAuthors(module.manifest.authors)»
			<version>
			    «if (module.manifest.version !== null) {
			        module.manifest.version
			    } else {
				    "1.0.0"
			    }»
			</version>
			<description>
			    «if (module.manifest.description !== null) {
			        module.manifest.description
			    } else {
			        "Place Description here"
			    }»
			</description>
			<!-- Listing of all files that should be installed for the module -->
			<files>
			    <filename module="«name»">«name».php</filename>
				<filename>helper.php</filename>
				<folder>
					<filename>tmpl/default.php</filename>
				</folder>
				<folder>language</folder>
			</files>
			<dependency>

			</dependency>
			<!-- All language files shipped with the module -->
			<languages>
			    «FOR lang : module.languages»
			    «IF lang.sys == false»
			    <language tag="«lang.name»">language/«lang.name»/«lang.name».«name».ini</language>
			    «ELSE»
			    <language tag="«lang.name»">language/«lang.name»/«lang.name».«name».sys.ini</language>
			    «ENDIF»
			    «ENDFOR»
			</languages>
			<!-- Optional parameters -->
			<config>
			    <fields name="params">
			        <fieldset name="basic">
			            «IF dynpage !== null»
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
			   	    «IF com !== null && dynpage !==null»
			   	    <fieldset name="filter">
			   	        <field
			   	            name="created_by"
			   	            addfieldpath="administrator/components/«Slug.nameExtensionBind("com",com).toLowerCase»/models/fields"
			   	            type="componentuser"
			   	            label="«Slug.nameExtensionBind("mod", module.name).toUpperCase»_FILTER_CREATED_BY"
			   	            description="«Slug.nameExtensionBind("mod", module.name).toUpperCase»_FILTER_CREATED_BY"
			   	            entity = "«dynpage.extendedEntityList.get(0).name.toLowerCase»">
			   	            <option value="">JOPTION_SELECT_CREATED_BY</option>
			   	        </field>
			   	        «FOR ExtendedAttribute attr : dynpage.extendFiltersList»
			   	        «IF !attr.name.equalsIgnoreCase("params")»
			   	        <field
			   	            addfieldpath="administrator/components/«Slug.nameExtensionBind("com",com).toLowerCase»/models/fields"
			   	            name="«attr.name»"
			   	            type="«dynpage.extendedEntityList.get(0).name.toLowerCase»"
			   	            label="«Slug.nameExtensionBind("mod", module.name).toUpperCase»_FILTER_«attr.name.toUpperCase»"
			   	            description="«Slug.nameExtensionBind("mod", module.name).toUpperCase»_FILTER_«attr.name.toUpperCase»"
			   	            valueColumn="«attr.entity.name.toLowerCase».«attr.name.toLowerCase»"
			   	            textColumn="«attr.entity.name.toLowerCase».«attr.name.toLowerCase»">
			   	            <option value="">JOPTION_SELECT_«attr.name.toUpperCase»</option>
			   	        </field>
			   	        «ENDIF»
			   	        «ENDFOR»
			   	    </fieldset>
			   	    «ENDIF»
			    </fields>
			</config>
		</extension>
		'''
	}

    // ToDo: Check if parameter is necessary. The module is already given as member of the class....
	def CharSequence phpContent(ExtendedModule module) {
	'''
		<?php
		«Slug.generateFileDoc(extMod, true)»
		// Define used Jimports here
		
		// No direct access to this file
		defined('_JEXEC') or die;
		
		// Include the «extMod.name» functions only once
		require_once __DIR__ . '/helper.php';
		«IF module.pageRef.pagescr !== null»
		require_once JPATH_ADMINISTRATOR . '/components/com_«module.extendedComponentName.toLowerCase»/helpers/«module.extendedComponentName.toLowerCase».php';
		«ENDIF»
		// Models, Functions should be implementated here
		// «module.name.substring(0,1).toUpperCase + module.name.substring(1).toLowerCase»Helper::updateReset();
		$items = «module.name.toFirstUpper»Helper::getList($params);
		$model = «module.name.toFirstUpper»Helper::getModel();
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
		 * if component helper shall be used
		 *
		 * require_once JPATH_ROOT . '/components/com_<nameOfComponent>/helpers/<nameOfComponentHelper.php>';
		 * $baseurl = JUri::base();
		 */
		?>
		
		<ul class="«extMod.name»<?php echo $moduleclass_sfx; ?>">
		
		    <?php foreach ($items as $item) : ?>
		    <li><div class="«extMod.pageRef.page.name»item">
			<?php if (empty($item)) : ?>
			    <?php // itemlist is empty ;?>
			    <!DOCTYPE html><title></title>
			«IF !(dynpage.entities.isEmpty)»
			<?php else : ?>
			    «FOR ExtendedAttribute attr : dynpage.extendedTableColumnList»
			    «IF !attr.name.equalsIgnoreCase("params")»
			    <?php $«attr.name» = $item->«attr.name.toLowerCase»;?>
			    <?php echo «checkLinkOfAttributes(attr, extMod.pageRef.page.links)»; ?>
			    «ENDIF»
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
		if(extMod.pageRef.sect === null || extMod.pageRef.pagescr === null)
		return "$" + result;
		
		for(Link lk: listLink) {
            if(lk !== null && lk.linkedAttribute.name.equalsIgnoreCase(attribute.name)) {				
                return '''JHtml::_('link',«Slug.linkOfAttribut(attribute, extMod.extendedPageReference.extendedPage.extendedDynamicPageInstance,  extMod.extendedComponentName.toLowerCase, "$item->")», $item->«attribute.name.toLowerCase»)'''
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
		 * Helper for «extMod.name»
		 *
		 * @category
		 * @package
		 * @since
		 *
		 */
		class «modul.name.substring(0,1).toUpperCase + modul.name.substring(1).toLowerCase»Helper
		{
		    «genGetList»
			«genGetModel()»
		}
		'''
	}
	
	def genGetModel()'''
	/**
	 * @param
	 *
	 * @return
	 *
	 * @since
	 **/
	public static function getModel()
	{
	    /**
		 * placeholder "<>" are to be replaced
		 */
		JModelLegacy::addIncludePath(JPATH_ROOT . «modelPath», «modelOfComponent»);
		// $app = JFactory::getApplictation();
		«IF (extMod.pageRef.pagescr !== null )»
		$model = JModelLegacy::getInstance('«extMod.pageRef.page.name»', «modelOfComponent2», array('ignore_request' => true));
		«ELSE»
		$model = JModelLegacy::getInstance('<type>', <modelOfComponent>, array('ignore_request' => true));
		«ENDIF»
		return $model;
	}
	
	'''
	
    def ComponentInformation (ExtendedModule modul) {
        if( modul.pageRef.pagescr !== null) {
            var String section =  modul.extendedPageReference.extendedPage.name
            var String compName =  modul.extendedComponentName
            if(section.equalsIgnoreCase('backend')) {
                modelPath = "'/administrator/components/com_" + compName.toLowerCase + "/models'"    		   					
            } else {
                modelPath = "'/components/com_" + compName.toLowerCase + "/models'"    		   					
            }
            modelOfComponent = ("\"" + compName.toFirstUpper + "\"")
            modelOfComponent2 = ("\"" +compName.toFirstUpper + "Model\"")
        }		
    }	
	
	public def genGetList()'''
	/**
	 * @param
	 *
	 * @return
	 *
	 * @since
	 **/
	public static function getList($params_module = null)
	{
	    $model = «extMod.name.toFirstUpper»Helper::getModel();
		$state = $params_module->get('state');
		if(!empty($state))
		{
		    $model->setState('filter.state', $state);
		}

		$search = $params_module->get('search');
		if(!empty($search))
		{
		    $model->setState('filter.search', $search);
		}

		$created_by = $params_module->get('created_by');
		if(!empty($created_by))
		{
		    $model->setState('filter.search',$created_by);
		}

		$ordering = $params_module->get('ordering');
		if(!empty($ordering))
		{
		    $model->setState('list.ordering',$ordering);
		}

		$direction = $params_module->get('direction');
		if(!empty($direction))
		{
		    $model->setState('list.direction', $direction);
		}
		
		$start = $params_module->get('start');
		if(!empty($start))
		{
		    $model->setState('list.start', $start);
		}

		$limit = $params_module->get('limit');
		if(!empty($limit))
		{
		    $model->setState('list.limit', $limit);
		}

		«IF dynpage !== null»
		«FOR ExtendedAttribute attr: dynpage.extendFiltersList»
		«IF !attr.name.equalsIgnoreCase("params")»
		$«attr.name.toLowerCase» = $params_module->get('«attr.name.toLowerCase»');
		if(!empty($«attr.name.toLowerCase» ))
		{
		    $model->setState('filter.«attr.name.toLowerCase»', $«attr.name.toLowerCase» );
		}
		«ENDIF»
		«ENDFOR»
		«ENDIF»
		$items = $model->getItems();

		return $items;
	}	
	'''
} 