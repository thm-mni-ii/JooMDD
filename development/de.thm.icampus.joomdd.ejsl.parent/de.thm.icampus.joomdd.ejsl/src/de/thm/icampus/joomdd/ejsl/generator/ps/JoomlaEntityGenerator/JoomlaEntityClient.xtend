package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator

import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.AbstractExtensionGenerator
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference

class JoomlaEntityClient extends AbstractExtensionGenerator {
	
	
	new( IFileSystemAccess fsa){
		this.fsa = fsa
	}
	
	public def generateJoomlaComponenteElements(ExtendedComponent comp, String path, boolean isBackendSection){
		
		generateFields( comp,  path)
		if(isBackendSection){
		generateTable( comp,  path)
		generateSQL( comp,  path)
		
		}
		
	}
	private def generateSQL(ExtendedComponent comp, String path){
		var  JoomlaEntityGenerator entgen = new JoomlaEntityGenerator(comp.allExtendedEntity,  comp.name, false)
		
		generateFile(path + "sql/install.mysql.utf8.sql", entgen.dogenerate)
		generateFile(path +"sql/uninstall.mysql.utf8.sql", entgen.sqlAdminSqlUninstallContent(comp.name))
		entgen.update = true
		generateFile(path + '''sql/updates/mysql/«comp.manifest.version».mysql.utf8.sql''',entgen.generateUpdateScript(comp.name))
	}
	
	private def generateFields(ExtendedComponent comp, String path){
		for (ExtendedEntity ent : comp.allExtendedEntity.filter[t | t!=null]) {
			var FieldsGenerator fieldEntity = new FieldsGenerator(comp, ent)
			generateFile( path + "models/fields/" + ent.name.toLowerCase + ".php",fieldEntity.genFieldsForEntity)
			for (ExtendedReference ref : ent.extendedReference.filter[t | t.upper.equalsIgnoreCase("1")]) {
				var FieldsGenerator fieldReference = new FieldsGenerator(ref, comp,ent)
				generateFile(
					path + "models/fields/" + fieldReference.getnameField.toLowerCase  +
						".php", fieldReference.genReferenceField) 
			}
		}
		generateFile(path + "models/fields/" + comp.name.toLowerCase+"user.php", FieldsGenerator.genFieldsForUserView(comp) )
	
		
	}
	private def generateTable(ExtendedComponent comp, String path){
		for (ExtendedEntity ent : comp.allExtendedEntity.filter[t | t!=null]) {
			var TableGeneratorTemplate table = new TableGeneratorTemplate(comp, ent)
			generateFile(path + "tables/" + ent.name.toLowerCase + ".php", table.genClassTable)

		}
	}
	
	override generate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}