package de.thm.icampus.joomdd.ejsl.generator.ps

import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator.JoomlaEntityClient
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedComponentImpl
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator.JoomlaEntityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator.TableGeneratorTemplate
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator.FieldsGenerator

class EntityGenerator extends AbstracteGenerator {
	
	EList<ExtendedEntity> entities
	ExtendedComponent extensions
	ExtendedDynamicPage page
	String path
	IFileSystemAccess fsa
	boolean isBackendSection
	
	new(EList<Entity> entitiesList, IFileSystemAccess fsa) {
		this.entities = new BasicEList<ExtendedEntity>
		entities.addAll(entitiesList.map[t | new ExtendedEntityImpl(t)])
		path ="Entity"
		this.fsa = fsa
	}
	new(ExtendedComponent extensions,String path,IFileSystemAccess fsa, boolean isBackenSection ){
		this.entities = new BasicEList<ExtendedEntity>
		this.entities = extensions.allExtendedEntity
		this.extensions = extensions
		this.path = path
		this.fsa = fsa
		this.isBackendSection = isBackenSection
	}
	new(ExtendedDynamicPage page, String path , IFileSystemAccess fsa){
		this.entities = new BasicEList<ExtendedEntity>
		this.entities =page.extendedEntityList
		this.page = page
		this.path = path
		this.fsa = fsa
	}
	
	override dogenerate() {
		if(extensions != null){
			var JoomlaEntityClient client = new JoomlaEntityClient(fsa)
			client.generateJoomlaComponenteElements(extensions, path, isBackendSection)
		}
		else if(page != null){
				var Component comp = EJSLFactory.eINSTANCE.createComponent
			comp.name = "ExtensionsName"
			var ExtendedComponent extComp = new ExtendedComponentImpl(comp)
			var EList<ExtendedEntity> pageEntities = page.extendedEntityList.get(0).getallEntityFromReferences
			var JoomlaEntityGenerator joomExt = new JoomlaEntityGenerator(pageEntities,"<Extensions_name>",false)
			joomExt.dogenerate(path+"sql", fsa)
			for(ExtendedEntity ent: pageEntities){
				var TableGeneratorTemplate table = new TableGeneratorTemplate(extComp, ent)
				table.dogenerate(path+"tables", fsa)
			}
			for(ExtendedEntity ent: pageEntities){
				for(ExtendedReference ref: ent.extendedReference){
				var FieldsGenerator fields = new FieldsGenerator(ref,extComp, ent)
				fields.dogenerate(path + "model/fields" , fsa)
				}
			}
		}
		else{
			var Component comp = EJSLFactory.eINSTANCE.createComponent
			comp.name = "ExtensionsName"
			var ExtendedComponent extComp = new ExtendedComponentImpl(comp)
			
			var JoomlaEntityGenerator joomExt = new JoomlaEntityGenerator(entities,"<Extensions_name>",false)
			joomExt.dogenerate("Entities/sql", fsa)
			for(ExtendedEntity ent: entities){
				var TableGeneratorTemplate table = new TableGeneratorTemplate(extComp, ent)
				table.dogenerate("Entities/tables", fsa)
			}
			for(ExtendedEntity ent: entities){
				for(ExtendedReference ref: ent.extendedReference){
				var FieldsGenerator fields = new FieldsGenerator(ref,extComp, ent)
				fields.dogenerate("Entities/fields/" , fsa)
				}
			}
		}
		
	}
	
	
	
	def generateFile(String string, CharSequence sequence) {
	}
	
	
	
	
}