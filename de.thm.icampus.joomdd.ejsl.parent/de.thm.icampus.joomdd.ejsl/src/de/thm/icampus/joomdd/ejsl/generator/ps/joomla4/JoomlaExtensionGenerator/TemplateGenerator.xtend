package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Template
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.AbstractExtensionGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.Slug
import java.util.Calendar
import org.eclipse.xtext.generator.IFileSystemAccess2

/**
 * This class contains the templates to generate the necessary folders and files for a Joomla Template.
 *
 * @author Dieudonne Timma, Dennis Priefer
 */
public class TemplateGenerator extends AbstractExtensionGenerator {
	
	Template template
	
	new(Template template, IFileSystemAccess2 fsa, String path) {
		this.fsa = fsa;
		this.name = "tpl_" + Slug.slugify(template.name)
		this.template = template
		this.path = path
	}

	override generate() {
		generateFile("index.php", template.phpIndex)
		generateFile("component.php", template.phpComponent)
		generateFile("templateDetails.xml", template.xmlTemplateDetails)
		generateFile("css/" + "general.css", template.cssGeneral)
		generateFile("css/" + "offline.css", template.cssGeneral)
		generateFile("css/" + "error.css", template.cssGeneral)
		generateFile("css/" + name + ".css", template.cssTemplate)
		generateFile("css/" + name + "_rtl.css", template.cssTemplate)
		generateEmptyDirectory("fonts")
		generateFile("html/" + "modules.php", template.phpModule)
		generateEmptyDirectory("javascript")
		generateFile("/images/dummyImage", template.DummyImage)
		
		generateEmptyDirectory("language")
		for (lang : template.languages) {
			generateFile("/language/" + lang.name + "/" + lang.name + "." + name + ".ini",
				template.iniLanguage(lang.name))
			generateFile("/language/" + lang.name + "/" + lang.name + "." + name + ".sys.ini",
				template.iniLanguage(lang.name))
		}

		return ''
	}

	def static TemplateGenerator getGenerator(Template template, IFileSystemAccess2 fsa, String path) {
		return new TemplateGenerator(template, fsa, path)
	}

	def CharSequence xmlTemplateDetails(Template template) {
	'''
		<?xml version="1.0" encoding="utf-8"?>
		<extension version="3.8" type="template" client="site">
		    <name>«template.name»</name>
		    «Slug.generateAuthors(template.manifest.authors)»
		    <creationDate>
		        «if (template.manifest.creationdate !== null) {
		            template.manifest.creationdate
		        } else {
		            Calendar::instance.get(Calendar::YEAR)
		        }»
		    </creationDate>
		    <copyright>
		        «if (template.manifest.copyright !== null) {
		            template.manifest.copyright
		        } else {
		            "All rights reserved by Author."
		        }»
		    </copyright>
		    <license>
		        «if (template.manifest.license !== null) {
		            template.manifest.license
		        } else {
		            "GPL 2.0"
		        }»
		    </license>
		    <version>
		        «if (template.manifest.version !== null) {
		            template.manifest.version
		        } else {
		            "1.0.0"
		        }»
		    </version>
		    <description>
		        «if (template.manifest.description !== null) {
		            template.manifest.description
		        } else {
		            "Place Description here"
		        }»
		    </description>
		    <!-- Listing of all files that should be installed for the module -->
		    <files>
		        <folder>css</folder>
		        <folder>fonts</folder>
		        <folder>html</folder>
		        <folder>images</folder>
		        <folder>javascript</folder>
		        <folder>language</folder>
		        <filename>index.php</filename>
		        <filename>component.php</filename>
		    </files>
		    <positions>
		        «FOR pos : template.positions»
		        <position>«pos.name»</position>
		        «ENDFOR»
		    </positions>
		    <!-- All language files shipped with the modul -->
		    <languages>
		        «FOR lang : template.languages»
		        <language tag="«lang.name»">«lang.name».«name».ini</language>
		        <language tag="«lang.name»">«lang.name».«name».sys.ini</language>
		        «ENDFOR»
		    </languages>
		    <!-- Optional parameters -->
		    <config>
		    </config>
		</extension>
	'''
	}

	def positions(Template template) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	def CharSequence phpIndex(Template template) {
	'''
		<?php
		defined('_JEXEC') or die;
		include dirname(__FILE__).DIRECTORY_SEPARATOR.'component.php';
		?>
	'''
	}

	def CharSequence phpComponent(Template template) {
	'''
		<?php
		defined('_JEXEC') or die;
		?>
		<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<?php echo $this->language; ?>" lang="<?php echo $this->language; ?>" dir="<?php echo $this->direction; ?>">
		<head>
		    <jdoc:include type="head" />
		    <link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/«name»/css/general.css" type="text/css" />
		    <link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/<?php echo $this->template; ?>/css/«name».css" type="text/css" />
		    <?php if ($this->direction == 'rtl') : ?>
		    <link rel="stylesheet" href="<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/css/«name»_rtl.css" type="text/css" />
		    <?php endif; ?>
		</head>
		<body class="contentpane">
		    «FOR pos : template.positions»
		    <div id="«pos.name»">
		        «FOR posType : pos.positionparameters»
		        <div id="«posType.divid»"><jdoc:include type="«posType.type»" name="«posType.name»" style="xhtml" /></div>
		        <!--#«posType.divid»-->
		        «ENDFOR»
		    </div><!--#«pos.name»-->
		    «ENDFOR»
		</body>
		</html>
	'''
	}

	def CharSequence phpModule(Template template) {
	'''
		<?php
		// no direct access
		defined('_JEXEC') or die;
		?>
	'''
	}

	def CharSequence cssGeneral(Template template) {
	'''
		@charset "utf-8";
		* { 
		    margin: 0px; 
		    padding: 0px; 
		    border: 0px;
		}
		html { 
		    height: 100.2%;
		}
		body { }
	'''
	}

	def CharSequence cssTemplate(Template template) {
	'''
		«FOR css : template.cssblocks»
			«css.selector» {
			«FOR cssValue : css.keyvaluepairs»
				«cssValue.name»: «cssValue.value»;
			«ENDFOR»
			}
		«ENDFOR»
		«FOR pos : template.positions»
			div#«pos.name» {
			«FOR posCss : pos.positionparameters»
				«FOR posCssValues : posCss.keyvaluepairs»
					«posCssValues.name»: «posCssValues.value»;
				«ENDFOR»
			«ENDFOR»
			}
		«ENDFOR»
	'''
	}


	def CharSequence iniLanguage(Template template, String languageFileName) {
	'''
		«FOR pageLang : template.languages»
			«IF pageLang.name == languageFileName»
				«FOR langvalue : pageLang.keyvaluepairs»
					«langvalue.name»="«langvalue.value»"
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	'''
	}

	def CharSequence DummyImage(Template template) {
	'''
		DummyImageFile
	'''
	}
	
	

}
