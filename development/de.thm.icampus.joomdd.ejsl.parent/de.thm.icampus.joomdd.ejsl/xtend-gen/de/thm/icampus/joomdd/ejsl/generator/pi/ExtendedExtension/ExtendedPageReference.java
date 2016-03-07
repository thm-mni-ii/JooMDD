package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension;

import de.thm.icampus.joomdd.ejsl.eJSL.PageReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage;

@SuppressWarnings("all")
public interface ExtendedPageReference extends PageReference {
  public abstract ExtendedPage getExtendedPage();
}
