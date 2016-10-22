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
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator.FieldsCardinalityGenerator
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil

class EntityGenerator extends AbstracteGenerator {
	
	EList<ExtendedEntity> entities
	ExtendedComponent extensions
	ExtendedDynamicPage page
	String path
	IFileSystemAccess fsa
	boolean isBackendSection
	
	new(EList<Entity> entitiesList, String path ,IFileSystemAccess fsa, String domainName) {
		this.entities = new BasicEList<ExtendedEntity>
		entities.addAll(entitiesList.map[t | new ExtendedEntityImpl(t)])
		this.path = path + domainName +"/"
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
	new(ExtendedDynamicPage page, ExtendedComponent extensions,String path , IFileSystemAccess fsa,boolean isBackend){
		this.entities = new BasicEList<ExtendedEntity>
		this.entities =page.extendedEntityList
		this.page = page
		this.path = path
		this.fsa = fsa
		this.extensions = extensions
		this.isBackendSection = isBackend
		
	}
	
	override dogenerate() {
		if(extensions != null && page == null){
			var JoomlaEntityClient client = new JoomlaEntityClient(fsa)
			client.generateJoomlaComponenteElements(extensions, path, isBackendSection)
			//println( "Entity for component " + extensions.name + " path ist " + path)
		}
		else if(page != null){
				
			var EList<ExtendedEntity> pageEntities =PlattformUtil.getAllReferenceOfEntity(page.extendedEntityList.get(0))
			pageEntities.add(page.extendedEntityList.get(0))
			var JoomlaEntityGenerator joomExt = new JoomlaEntityGenerator(pageEntities,extensions.name,true)
			joomExt.dogenerate(path+"sql", fsa)
			//println( "Page for component " + extensions.name + " path ist " + path)
			for(ExtendedEntity ent: pageEntities){
				var TableGeneratorTemplate table = new TableGeneratorTemplate(extensions, ent)
				table.dogenerate(path+"tables", fsa)
			}
			for(ExtendedEntity ent: pageEntities){
				var FieldsGenerator fieldsEntity = new FieldsGenerator(extensions, ent)
				fieldsEntity.dogenerate(path + "models/fields" , fsa)
				for(ExtendedReference ref: ent.extendedReference){
				switch ref.upper{
						case "1":{
							var FieldsGenerator fields = new FieldsGenerator(ref,extensions, ent)
							fields.dogenerate(path+"fields/" , fsa)
						}
						case "*" , case "-1":{
							var FieldsCardinalityGenerator fields = new FieldsCardinalityGenerator(ref,extensions, ent)
							fields.dogenerate(path+"fields/" , fsa)
						}
					}
				}
			}
		}
		else{
			var Component comp = EJSLFactory.eINSTANCE.createComponent
			comp.name = "ExtensionsName"
			var ExtendedComponent extComp = new ExtendedComponentImpl(comp)
			
			var JoomlaEntityGenerator joomExt = new JoomlaEntityGenerator(entities,"<Extensions_name>",false)
			joomExt.dogenerate(path+"sql", fsa)
			for(ExtendedEntity ent: entities){
				var TableGeneratorTemplate table = new TableGeneratorTemplate(extComp, ent)
				table.dogenerate(path+"tables", fsa)
			}
			for(ExtendedEntity ent: entities){
				var FieldsGenerator fieldsEntity = new FieldsGenerator(extComp, ent)
				fieldsEntity.dogenerate(path+"fields/" , fsa)
				for(ExtendedReference ref: ent.extendedReference){
					switch ref.upper{
						case "1":{
							var FieldsGenerator fields = new FieldsGenerator(ref,extComp, ent)
							fields.dogenerate(path+"fields/" , fsa)
						}
						case "*" , case "-1":{
							var FieldsCardinalityGenerator fields = new FieldsCardinalityGenerator(ref,extComp, ent)
							fields.dogenerate(path+"fields/" , fsa)
						}
					}
				
				
				}
			}
		}
		
	}
	
	
	
	def generateFile(String string, CharSequence sequence) {
	}
	
	
	
	
}