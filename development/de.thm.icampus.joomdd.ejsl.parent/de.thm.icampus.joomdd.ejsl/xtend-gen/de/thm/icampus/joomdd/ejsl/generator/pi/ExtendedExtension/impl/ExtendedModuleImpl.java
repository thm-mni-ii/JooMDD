package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.Component;
import de.thm.icampus.joomdd.ejsl.eJSL.Language;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.Module;
import de.thm.icampus.joomdd.ejsl.eJSL.PageReference;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.ModuleImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedComponentImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedPageReferenceImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public class ExtendedModuleImpl extends ModuleImpl implements ExtendedModule {
  private ExtendedPageReference extendedReference;
  
  private ExtendedComponent com;
  
  public ExtendedModuleImpl(final Module mod) {
    String _name = mod.getName();
    String _slugify = PlattformIUtil.slugify(_name);
    this.name = _slugify;
    Manifestation _manifest = mod.getManifest();
    this.manifest = _manifest;
    EList<Language> _languages = mod.getLanguages();
    this.languages = _languages;
    PageReference _pageRef = mod.getPageRef();
    this.pageRef = _pageRef;
    ExtendedPageReferenceImpl _extendedPageReferenceImpl = new ExtendedPageReferenceImpl(this.pageRef);
    this.extendedReference = _extendedPageReferenceImpl;
    PageReference _pageRef_1 = mod.getPageRef();
    Component _pagescr = _pageRef_1.getPagescr();
    ExtendedComponentImpl _extendedComponentImpl = new ExtendedComponentImpl(_pagescr);
    this.com = _extendedComponentImpl;
  }
  
  @Override
  public ExtendedPageReference getExtendedPageReference() {
    return this.extendedReference;
  }
  
  @Override
  public ExtendedComponent getExtendedComponent() {
    return this.com;
  }
}
