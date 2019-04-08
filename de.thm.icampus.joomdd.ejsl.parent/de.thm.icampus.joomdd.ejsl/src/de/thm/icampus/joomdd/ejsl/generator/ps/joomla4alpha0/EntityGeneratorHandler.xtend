package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0

import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaExtensionGenerator.AbstractExtensionGenerator
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaEntityGenerator.JoomlaEntityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaEntityGenerator.FieldsGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaEntityGenerator.FieldsCardinalityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaEntityGenerator.FieldsFileloaderGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaEntityGenerator.TableGeneratorTemplate
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaUtil.Slug

/**
 * This class represents the interface between the JooMDD generator and the Joomla-specific entity generator templates. 
 * 
 * @author Dennis Priefer, Dieudonne Timma Meyatchie
 */
class EntityGeneratorHandler extends AbstractExtensionGenerator {
	
	new( IFileSystemAccess2 fsa){
		this.fsa = fsa
	}
	
	public def generateJoomlaComponenteElements(ExtendedComponent comp, String path, boolean isBackendSection){
		 
		generateFields( comp,  path, isBackendSection)
		if(isBackendSection) {
		    generateTable( comp,  path)
		    generateSQL( comp,  path)
		}
		
	}
	private def generateSQL(ExtendedComponent comp, String path) {
		var  JoomlaEntityGenerator entgen = new JoomlaEntityGenerator(comp.allExtendedEntity,  comp.name, false)
		
		generateFile(path + "sql/install.mysql.utf8.sql", entgen.dogenerate)
		generateFile(path + "sql/uninstall.mysql.utf8.sql", entgen.sqlAdminSqlUninstallContent(comp.name))
		entgen.update = true
		generateFile(path + '''sql/updates/mysql/«comp.manifest.version».mysql.utf8.sql''',entgen.generateUpdateScript(comp.name))
	}
	
	private def generateFields(ExtendedComponent comp, String path, boolean isBackendSection){
		for (ExtendedEntity ent : comp.allExtendedEntity.filter[t | t !== null]) {
			var FieldsGenerator fieldEntity = new FieldsGenerator(comp, ent)
			if (isBackendSection) {
				generateFile( path + "Field/" + Slug.capitalize(ent.name.toLowerCase) + "Field.php",fieldEntity.genAdministratorFieldsForEntity)
			}
			else {
				generateFile( path + "Field/" + Slug.capitalize(ent.name.toLowerCase) + "Field.php",fieldEntity.genSiteFieldsForEntity)
			}
			for(ExtendedReference ref: ent.allExtendedReferences){
				switch ref.upper{
				    case "1":{
					    var FieldsGenerator fields = new FieldsGenerator(ref,comp, ent)
						fields.dogenerate(path+ "Field/" , fsa)
					}
					case "*" , case "-1":{
					    var FieldsCardinalityGenerator fields = new FieldsCardinalityGenerator(ref,comp, ent)
						fields.dogenerate(path+ "Field/" , fsa)
					}
				}
			}
		}
		/*if (isBackendSection)
			generateFile(path + "Field/" + comp.name.toLowerCase.toFirstUpper+"userField.php", FieldsGenerator.genAdministratorFieldsForUserView(comp) )
		else
			generateFile(path + "Field/" + comp.name.toLowerCase.toFirstUpper+"userField.php", FieldsGenerator.genSiteFieldsForUserView(comp) )*/
		if(comp.hasFileToload) {
		    var fileloader = new FieldsFileloaderGenerator(comp)
		    generateFile(path + "Field/" + "FileloaderField.php",  fileloader.generateFileloader)
		    generateFile(path + "Field/" + "ImageloaderField.php", fileloader.generateImageloader )
		}
		
	}
	private def generateTable(ExtendedComponent comp, String path){
		for (ExtendedEntity ent : comp.allExtendedEntity.filter[t | t !== null]) {
			var TableGeneratorTemplate table = new TableGeneratorTemplate(comp, ent)
			generateFile(path + "Table/"+ent.name.toLowerCase.toFirstUpper+"Table.php", table.genClassTable)
		}
	}
	
	override generate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}