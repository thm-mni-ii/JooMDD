package de.thm.icampus.joomdd.ejsl.generator.ps

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl.ExtendedPageImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.PageGeneratorClient
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedComponentImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import org.eclipse.emf.common.util.BasicEList

class PageGenerator extends AbstracteGenerator {

	IFileSystemAccess fsa
	EList<ExtendedPage> pageList = new BasicEList<ExtendedPage>
	String path
	ExtendedComponent comp
	String section = "site"

	new(EList<Page> pages, IFileSystemAccess access, String path) {
		fsa = access
		pageList.addAll(pages.map[t|new ExtendedPageImpl(t)])
		this.path = path
	}

	new(ExtendedComponent component, EList<ExtendedPage> pages, IFileSystemAccess access, String path, String section) {
		fsa = access
		pageList.addAll(pages)
		this.path = path
		comp = component
		this.section = section
	}

	override dogenerate() {

		if (comp != null) {
			for (ExtendedPage pg : pageList) {
				var PageGeneratorClient client = new PageGeneratorClient(pg, comp, path, section, fsa)
				client.generateExtension
			}
		} else {
			var Component tempComp = EJSLFactory.eINSTANCE.createComponent
			tempComp.name = "ExtensionsName"
			comp = new ExtendedComponentImpl(tempComp)
			for (ExtendedPage pg : pageList) {
				
				var PageGeneratorClient client = new PageGeneratorClient(pg, comp, path, "site", fsa)
				client.generatePages
				if (pg.extendedDynamicPageInstance != null) {
					var String pathent = path + pg.name +"/"
					var ExtendedDynamicPage dynPage = pg.extendedDynamicPageInstance
					var EList<ExtendedEntity> entities = dynPage.extendedEntityList.get(0).getallEntityFromReferences
					entities.add(dynPage.extendedEntityList.get(0))
					var EntityGenerator ent = new EntityGenerator(dynPage, pathent, fsa)
					ent.dogenerate

				}
			}

		}
	}

}
