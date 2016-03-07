package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.Component;
import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.eJSL.PageReference;
import de.thm.icampus.joomdd.ejsl.eJSL.SectionKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.PageReferenceImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl.ExtendedPageImpl;

@SuppressWarnings("all")
public class ExtendedPageReferenceImpl extends PageReferenceImpl implements ExtendedPageReference {
  private ExtendedPage extendedPage;
  
  private PageReference instance;
  
  public ExtendedPageReferenceImpl(final PageReference pageRef) {
    this.instance = pageRef;
    Page _page = pageRef.getPage();
    this.page = _page;
    Component _pagescr = pageRef.getPagescr();
    this.pagescr = _pagescr;
    SectionKinds _sect = pageRef.getSect();
    this.sect = _sect;
    ExtendedPageImpl _extendedPageImpl = new ExtendedPageImpl(this.page);
    this.extendedPage = _extendedPageImpl;
  }
  
  @Override
  public ExtendedPage getExtendedPage() {
    return this.extendedPage;
  }
}
