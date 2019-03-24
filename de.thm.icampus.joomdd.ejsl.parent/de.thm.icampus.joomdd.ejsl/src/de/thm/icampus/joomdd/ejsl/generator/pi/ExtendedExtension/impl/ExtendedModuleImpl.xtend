package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.ModuleImpl
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.eJSL.ComponentReference
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil
import org.eclipse.xtend.lib.annotations.Accessors

class ExtendedModuleImpl extends ModuleImpl implements ExtendedModule {

    ExtendedPageReference extendedReference
    String comName
    String extensionName
    
    @Accessors(PUBLIC_GETTER)
    ExtendedComponent component

    new(Module mod) {
        this.name = PlattformUtil.slugify(mod.name)
        this.manifest = mod.manifest
        this.languages = mod.languages
        this.pageRef = mod.pageRef
        this.pageRef.pagescr.ref
        extendedReference = new ExtendedPageReferenceImpl(pageRef)
        init(mod.pageRef.pagescr)
        extensionName = "mod_" + this.name.toLowerCase
    }

    def init(ComponentReference reference) {
        if (reference == null)
        {
            this.comName = null
            this.component = null
        }
        else if (reference.ref != null) {
            this.comName = PlattformUtil.slugify(reference.ref.name)
            this.component = new ExtendedComponentImpl(reference.ref)
        }
        else (reference.core != null) {
            this.comName = PlattformUtil.slugify(reference.core.getName())
        }
    }

    override getExtendedPageReference() {
        return extendedReference
    }

    override getExtendedComponentName() {
        return comName
    }

    override extensionName() {
        return extensionName
    }
}
