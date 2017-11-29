package de.thm.icampus.joomdd.ejsl.generator.ps

import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedComponentImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl.ExtendedPageImpl
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.PageGeneratorHandler

class PageGenerator extends AbstractGenerator {

    IFileSystemAccess2 fsa
    EList<ExtendedPage> pageList = new BasicEList<ExtendedPage>
    String path
    ExtendedComponent comp
    String section = "site"
    boolean extUpdate

    new(EList<Page> pages, IFileSystemAccess2 access, String path, String domainName) {
        fsa = access
        pageList.addAll(pages.map[t|new ExtendedPageImpl(t)])
        this.path = path + domainName + "/"
        comp = null
    }

    new(ExtendedComponent component, 
        EList<ExtendedPage> pages, 
        IFileSystemAccess2 access, 
        String path, 
        String section,
        boolean extensionsUpdate
    ) {
        fsa = access
        pageList.addAll(pages)
        this.path = path
        comp = component
        this.section = section
        extUpdate = extensionsUpdate
    }

    override dogenerate() {

        if (!extUpdate && comp !== null) {
            for (ExtendedPage pg : pageList) {
                var PageGeneratorHandler client
                client = new PageGeneratorHandler(pg, comp, path, section, fsa)
                client.generateExtension
            }
        } else {
            if (comp === null) {
                var Component tempComp = EJSLFactory.eINSTANCE.createComponent
                tempComp.name = "ExtensionsName"
                var ExtendedComponent compTemp = new ExtendedComponentImpl(tempComp)
                upDatePageGenerator(compTemp)
            } else {
                upDatePageGenerator(comp)
            }
        }
    }

    private def void upDatePageGenerator(ExtendedComponent comp) {
        var boolean isBAckend = true
        if (section.equalsIgnoreCase("site")) {
            isBAckend = false
        }
        
        for (ExtendedPage pg : pageList) {
            var PageGeneratorHandler client
            client = new PageGeneratorHandler(pg, comp, path + section + "/", section, fsa)
            client.generatePages
            if (pg.extendedDynamicPageInstance !== null) {
                var String pathent = path + section + "/" + pg.name + "/"
                var ExtendedDynamicPage dynPage = pg.extendedDynamicPageInstance
                var EntityGenerator ent
                ent = new EntityGenerator(dynPage, comp, pathent, fsa, isBAckend)
                ent.dogenerate
            }
        }
    }
}
