package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4

import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaExtensionGenerator.AbstractExtensionGenerator
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaEntityGenerator.JoomlaEntityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaEntityGenerator.FieldsGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaEntityGenerator.FieldsCardinalityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaEntityGenerator.FieldsFileloaderGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaEntityGenerator.TableGeneratorTemplate
import org.eclipse.emf.common.util.EList

/**
 * This class represents the interface between the JooMDD generator and the Joomla-specific entity generator templates. 
 * 
 * @author Dennis Priefer, Dieudonne Timma Meyatchie
 */
class EntityGeneratorHandler extends AbstractExtensionGenerator {
	
	new( IFileSystemAccess2 fsa){
		this.fsa = fsa
	}
	
	def generateJoomlaComponenteElements(EList<ExtendedEntity> extendedEntityList, ExtendedComponent comp, String path, boolean isBackendSection){
		 
		generateFields(extendedEntityList, comp,  path, isBackendSection)
		if(isBackendSection) {
		    generateTable(extendedEntityList, comp,  path)
		    generateSQL(extendedEntityList, comp,  path)
		}
		
	}
	private def generateSQL(EList<ExtendedEntity> extendedEntityList, ExtendedComponent comp, String path) {
		var  JoomlaEntityGenerator entgen = new JoomlaEntityGenerator(extendedEntityList, comp.name, false)
		
		generateFile(path + "sql/install.mysql.utf8.sql", entgen.dogenerate)
		generateFile(path + "sql/uninstall.mysql.utf8.sql", entgen.sqlAdminSqlUninstallContent(comp.name))
		entgen.update = true
		// generateFile(path + '''sql/updates/mysql/«comp.manifest.version».mysql.utf8.sql''', entgen.generateUpdateScript(comp.name))
		generateFile(path + '''sql/updates/mysql/«comp.manifest.version».mysql.utf8.sql''', entgen.generateUpdateScript(comp.name))
	}
	
	private def generateFields(EList<ExtendedEntity> extendedEntityList, ExtendedComponent comp, String path, boolean isBackendSection){
	    var String section = if (isBackendSection === true) "Administrator" else "Site"
	    
		for (ExtendedEntity ent : extendedEntityList.filter[t | t !== null]) {
			var FieldsGenerator fieldEntity = new FieldsGenerator(comp, ent, section)
            fieldEntity.dogenerate(path + "Field/" , fsa)
            
			for(ExtendedReference ref: ent.allExtendedReferences){
				switch ref.upper{
				    case "1":{
					    var FieldsGenerator fields = new FieldsGenerator(ref, comp, ent, section)
						fields.dogenerate(path + "Field/" , fsa)
					}
					case "*" , case "-1":{
					    var FieldsCardinalityGenerator fields = new FieldsCardinalityGenerator(ref, comp, ent, section)
						fields.dogenerate(path + "Field/" , fsa)
					}
				}
			}
		}
		
		if(comp.hasFileToload) {
		    var fileloader = new FieldsFileloaderGenerator(comp, section)
		    generateFile(path + "Field/" + "FileloaderField.php",  fileloader.generateFileloader)
		    generateFile(path + "Field/" + "ImageloaderField.php", fileloader.generateImageloader )
		}
		
	}
	
	private def generateTable(EList<ExtendedEntity> extendedEntityList, ExtendedComponent comp, String path){
		for (ExtendedEntity ent : extendedEntityList.filter[t | t !== null]) {
			var TableGeneratorTemplate table = new TableGeneratorTemplate(comp, ent)
			generateFile(path + "Table/"+ent.name.toLowerCase.toFirstUpper+"Table.php", table.genClassTable)
		}
	}
	
	override generate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}