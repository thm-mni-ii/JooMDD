/**
 */
package de.thm.icampus.ejsl.generator.^extension
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.ejsl.eJSL.Module
import java.util.Calendar
import org.eclipse.emf.common.util.EList
import de.thm.icampus.ejsl.eJSL.ExtensionPackage
import org.eclipse.emf.ecore.EStructuralFeature
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.IndexPage
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.eJSL.DetailsPage
import de.thm.icampus.ejsl.eJSL.Section
import de.thm.icampus.ejsl.eJSL.Page
import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.generator.pages.PageGeneratorClient
import de.thm.icampus.ejsl.generator.util.Slug
import de.thm.icampus.ejsl.generator.util.KVPairGeneratorClient
import de.thm.icampus.ejsl.generator.util.ProtectedRegion
import de.thm.icampus.ejsl.eJSL.Link
import de.thm.icampus.ejsl.generator.pages.LinkGeneratorClient
import de.thm.icampus.ejsl.eJSL.KeyValuePair

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
	
	private IndexPage dpage
	

	Module module

	new(Module module, IFileSystemAccess fsa) {
		this.fsa = fsa
		this.name = 'mod_' + Slug.slugify(module.name)
		this.module = module
		
		this.ComponentInformation(module)
		this.module.formatName
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
		generateJoomlaDirectory("")
		generateFile("index" + ".html", this.module.IndexContent)
		generateFile(name + ".xml", this.module.xmlContent)
		generateFile(name + ".php", this.module.phpContent)
		generateFile("helper.php", helperPHP(module, module.pageRef.page as DynamicPage	))
		
		
		generateJoomlaDirectory("tmpl")
		generateFile("tmpl/default.php", defaultTemplate(module, module.pageRef.page as DynamicPage))
		generateFile("tmpl/index.html", this.module.IndexContent)
		
		for (lang : module.languages) {
			val ldir = lang.name
			generateFile("language/" + ldir + "/" + ldir + "." + name + ".ini", languageFileGen(lang.keyvaluepairs))
			generateFile("language/" + ldir + "/" + ldir + "." + name + ".sys.ini", languageFileGen(lang.keyvaluepairs))
		}

			
		 

		return ''
	}
	
	def languageFileGen(EList<KeyValuePair> list) '''
	«FOR KeyValuePair key: list»
	MOD_«module.name.toUpperCase»_«key.name.toUpperCase» = "«key.value»"
	«ENDFOR»
	'''
	
	
 

	
	def CharSequence xmlContent(Module module) {
		'''
		<?xml version="1.0" encoding="utf-8"?>
		<extension type="module" version="3.3" client="site" method="upgrade">
			<name>«module.name»</name>
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
			«module.manifest.authors.generate»
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
				<language tag="«lang.name»">«lang.name».«name».ini</language>
				«ENDFOR»
			</languages>
			<!-- Optional parameters -->
			<config>
			<fields name="params">
			   <fieldset name="basic">
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
			   </fieldset>
			</fields>
			</config>
		</extension>
		'''
	}

	def CharSequence phpContent(Module modul) {
		'''
		«IF modul.pageRef.pagescr != null»
			«var c = modul.pageRef.pagescr»
		<?php
		«Slug.generateFileDoc(module, true)»
		
			// Define used Jimports here

			// No direct access to this file
				defined('_JEXEC') or die;
		
				// Include the «module.name» functions only once
				require_once __DIR__ . '/helper.php';
				require_once JPATH_ADMINISTRATOR . '/components/com_«c.name.toLowerCase»/helpers/«c.name.toLowerCase».php';

				
			«ELSE»
		<?php
		«Slug.generateFileDoc(module, true)»
		
			// Define used Jimports here
		
			// No direct access to this file
				defined('_JEXEC') or die;
		
				// Include the «module.name» functions only once
				require_once __DIR__ . '/helper.php';
			«ENDIF»
			// Models, Functions should be implementated here
		    // «modul.name.substring(0,1).toUpperCase + modul.name.substring(1).toLowerCase»Helper::updateReset();
			$items = &«modul.name.substring(0,1).toUpperCase + modul.name.substring(1).toLowerCase»Helper::getList($params);
			$moduleclass_sfx = htmlspecialchars($params->get('moduleclass_sfx'));
			require JModuleHelper::getLayoutPath('«name»', $params->get('layout', 'default'));
			'''
	}
	
	 def CharSequence defaultTemplate(Module module, DynamicPage mpage) {
		'''
		<?php
		«Slug.generateFileDoc(module, true)»
			// No direct access to this file
			defined('_JEXEC') or die;
			/**
			 *if Component-Helper to be used
			 *
			 *require_once JPATH_ROOT . '/components/com_<nameOfComponent>/helpers/<nameOfComponentHelper.php';
			 *$baseurl = JUri::base();
			 */
			?>
			<ul class="«module.name»<?php echo $moduleclass_sfx; ?>">
			<?php// if ($headerText) : ?>
				<?php// echo $headerText; ?>
			<?php// endif; ?>
			
			<?php foreach ($items as $item) : ?>
			<div class="«module.pageRef.page.name»item">
			<?php if (empty($item)) : ?>
				«IF mpage.entities.isEmpty»
					<?php// itemlist is empty ;?>
					<!DOCTYPE html><titel></titel>
				«ELSE»
					<?php echo "itemlist is empty" ?>
					<!DOCTYPE html><titel></titel>
				<?php else : ?>					
					«FOR Attribute : mpage.tablecolumns»
					<?php $«Attribute.name» = $item->«Attribute.name.toLowerCase»;?>
					<?php echo «checkLinkOfAttributes(Attribute, module.pageRef.page.links)»; ?>
					«ENDFOR»	
				«ENDIF»
				<?php endif; ?>
		</div>
		<?php endforeach; ?>
		'''
	}
	
	def checkLinkOfAttributes(Attribute attribute, EList<Link> listLink) {
		var String result = attribute.name.toString
		if(module.pageRef.sect == null || module.pageRef.pagescr == null)
		return "$" + result;
		
		for(Link lk: listLink){
			if(lk.linkedAttribute.name.equalsIgnoreCase(attribute.name)){
			var LinkGeneratorClient lkClient = new LinkGeneratorClient(lk, Slug.getSectioName(module.pageRef.sect),  Slug.nameExtensionBind('com', module.pageRef.pagescr.name.toLowerCase),"$item->") 
				
			   return '''JHtml::_('link',«lkClient.generateLink» . "&id=" . $item->id, $item->«attribute.name.toLowerCase»)'''
			   
			   }
		}
		return "$" + result;
	}
	
	
	def CharSequence helperPHP(Module modul, DynamicPage mpage) {
		'''
		<?php
		«Slug.generateFileDoc(module, true)»
		
		// No direct access to this file
		defined('_JEXEC') or die;
		/**
		 *Helper for «module.name»
		 *
		 * @category
		 * @package
		 * @since
		 *
		 */
		class «modul.name.substring(0,1).toUpperCase + modul.name.substring(1).toLowerCase»Helper
		{
			
		}
		'''
	}
	
		def ComponentInformation (Module modul) {
			if( modul.pageRef.pagescr != null){
			var String section =  modul.pageRef.sect.getName()
            var Component c =  modul.pageRef.pagescr
    						if(section.equalsIgnoreCase('backend')){
			    				modelPath = "'/administrator/components/com_" + c.name.toLowerCase + "/models'"    		   					
    		   				} else{
			    				modelPath = "'/components_" + c.name.toLowerCase + "/models'"    		   					
    		   				}
			    			modelOfComponent = ("\"" + c.name.toFirstUpper + "\"")
			    			modelOfComponent2 = ("\"" + c.name.toFirstUpper + "Model\"")
			    		
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
			    «IF (module.pageRef.pagescr != null )»
			    $model = JModelLegacy::getInstance('«module.pageRef.page.name»', «modelOfComponent2», array('ignore_request' => true));
			    
				«ELSE»
			$model = JModelLegacy::getInstance('<type>', <modelOfComponent>, array('ignore_request' => true));
		
				«ENDIF»
			$model->setState('filter.state', $params->state);
			$model->setState('filter.search', $params->search);
			$model->setState('list.ordering', $params->ordering);
			$model->setState('list.direction', $params->direction);
			$model->setState('list.start', $params->start);
			$model_>setState('list.limit', $params->limit);
			
			$items = $model->getItems();

			 return $items;
			}	
	'''
	
		
	override getProtectedRegions() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override setProtectedRegions(EList<ProtectedRegion> myprotectedRegions) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getSlug() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override setSlug(Slug slug) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getKvPairClient() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override setKvPairClient(KVPairGeneratorClient e) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

} // ModuleGenerator
