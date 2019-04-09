package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4

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
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaEntityGenerator.FieldsCardinalityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaEntityGenerator.FieldsGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaEntityGenerator.JoomlaEntityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaEntityGenerator.TableGeneratorTemplate
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.EntityGeneratorHandler
import de.thm.icampus.joomdd.ejsl.generator.ps.AbstractGenerator

class EntityGenerator extends AbstractGenerator {

    EList<ExtendedEntity> entities
    ExtendedComponent extendedComponent
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

    new(ExtendedComponent extendedComponent, 
        String path, 
        IFileSystemAccess2 fsa, 
        boolean isBackenSection
    ) {
        this.entities = new BasicEList<ExtendedEntity>
        this.entities = extendedComponent.allExtendedEntity
        this.extendedComponent = extendedComponent
        this.path = path
        this.fsa = fsa
        this.isBackendSection = isBackenSection
    }

    new(ExtendedDynamicPage page, 
        ExtendedComponent extendedComponent, 
        String path, 
        IFileSystemAccess2 fsa,
        boolean isBackend
    ) {
        this.entities = new BasicEList<ExtendedEntity>
        this.entities = page.extendedEntityList
        this.page = page
        this.path = path
        this.fsa = fsa
        this.extendedComponent = extendedComponent
        this.isBackendSection = isBackend

    }

    override dogenerate() {
        var EntityGeneratorHandler client = new EntityGeneratorHandler(fsa)
        
        if (extendedComponent !== null && page === null) {
            client.generateJoomlaComponenteElements(this.extendedComponent.allExtendedEntity, this.extendedComponent, this.path, this.isBackendSection)
        } else if (page !== null) {
            var EList<ExtendedEntity> pageEntities = PlattformUtil.getAllReferenceOfEntity(
                page.extendedEntityList.get(0)
            )
            pageEntities.add(page.extendedEntityList.get(0))
            
            client.generateJoomlaComponenteElements(pageEntities, this.extendedComponent, this.path, this.isBackendSection)
        } else {
            var Component comp = EJSLFactory.eINSTANCE.createComponent
            comp.name = "ExtensionsName"
            var ExtendedComponent extComp = new ExtendedComponentImpl(comp)
            
            client.generateJoomlaComponenteElements(this.entities, extComp, this.path, this.isBackendSection)
        }
    }
    
    def generateFile(String string, CharSequence sequence) {}
}
