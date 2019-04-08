package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0

import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedComponentImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaEntityGenerator.FieldsCardinalityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaEntityGenerator.FieldsGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaEntityGenerator.JoomlaEntityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.JoomlaEntityGenerator.TableGeneratorTemplate
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4alpha0.EntityGeneratorHandler
import de.thm.icampus.joomdd.ejsl.generator.ps.AbstractGenerator

class EntityGenerator extends AbstractGenerator {

    EList<ExtendedEntity> entities
    ExtendedComponent extensions
    ExtendedDynamicPage page
    String path
    IFileSystemAccess2 fsa
    boolean isBackendSection

    new(EList<Entity> entitiesList, String path, IFileSystemAccess2 fsa, String domainName) {
        this.entities = new BasicEList<ExtendedEntity>
        entities.addAll(entitiesList.map[t|new ExtendedEntityImpl(t)])
        this.path = path + domainName + "/"
        this.fsa = fsa
    }

    new(ExtendedComponent extensions, 
        String path, 
        IFileSystemAccess2 fsa, 
        boolean isBackenSection
    ) {
        this.entities = new BasicEList<ExtendedEntity>
        this.entities = extensions.allExtendedEntity
        this.extensions = extensions
        this.path = path
        this.fsa = fsa
        this.isBackendSection = isBackenSection
    }

    new(ExtendedDynamicPage page, 
        ExtendedComponent extensions, 
        String path, 
        IFileSystemAccess2 fsa,
        boolean isBackend
    ) {
        this.entities = new BasicEList<ExtendedEntity>
        this.entities = page.extendedEntityList
        this.page = page
        this.path = path
        this.fsa = fsa
        this.extensions = extensions
        this.isBackendSection = isBackend

    }

    override dogenerate() {
        if (extensions !== null && page === null) {
            var EntityGeneratorHandler client = new EntityGeneratorHandler(fsa)
            client.generateJoomlaComponenteElements(extensions, path, isBackendSection)
        } else if (page !== null) {
            var EList<ExtendedEntity> pageEntities = PlattformUtil.getAllReferenceOfEntity(
                page.extendedEntityList.get(0)
            )
            pageEntities.add(page.extendedEntityList.get(0))
            
            var JoomlaEntityGenerator joomExt
            joomExt = new JoomlaEntityGenerator(pageEntities, extensions.name, true)
            joomExt.dogenerate(path + "sql", fsa)
           
            for (ExtendedEntity ent : pageEntities) {
                var TableGeneratorTemplate table = new TableGeneratorTemplate(extensions, ent)
                table.dogenerate(path + "Table", fsa)
            }
            
            for (ExtendedEntity ent : pageEntities) {
                var FieldsGenerator fieldsEntity = new FieldsGenerator(extensions, ent)
                fieldsEntity.dogenerate(path + "Field", fsa)
                for (ExtendedReference ref : ent.allExtendedReferences) {
                    switch ref.upper {
                        case "1": {
                            var FieldsGenerator fields = new FieldsGenerator(ref, extensions, ent)
                            fields.dogenerate(path + "Field/", fsa)
                        }
                        case "*",
                        case "-1": {
                            var FieldsCardinalityGenerator fields
                            fields = new FieldsCardinalityGenerator(ref, extensions, ent)
                            fields.dogenerate(path + "Field/", fsa)
                        }
                    }
                }
            }
        } else {
            var Component comp = EJSLFactory.eINSTANCE.createComponent
            comp.name = "ExtensionsName"
            var ExtendedComponent extComp = new ExtendedComponentImpl(comp)

            var JoomlaEntityGenerator joomExt
            joomExt = new JoomlaEntityGenerator(entities, "<Extensions_name>", false)
            joomExt.dogenerate(path + "sql", fsa)
            
            for (ExtendedEntity ent: entities) {
                var TableGeneratorTemplate table = new TableGeneratorTemplate(extComp, ent)
                table.dogenerate(path + "Table", fsa)
            }
            
            for (ExtendedEntity ent: entities) {
                var FieldsGenerator fieldsEntity = new FieldsGenerator(extComp, ent)
                fieldsEntity.dogenerate(path + "Field/", fsa)
                for (ExtendedReference ref : ent.allExtendedReferences) {
                    switch ref.upper {
                        case "1": {
                            var FieldsGenerator fields = new FieldsGenerator(ref, extComp, ent)
                            fields.dogenerate(path + "Field/", fsa)
                        }
                        case "*",
                        case "-1": {
                            var FieldsCardinalityGenerator fields
                            fields = new FieldsCardinalityGenerator(ref, extComp, ent)
                            fields.dogenerate(path + "Field/", fsa)
                        }
                    }
                }
            }
        }
    }
    
    def generateFile(String string, CharSequence sequence) {}
}
