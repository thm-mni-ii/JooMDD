package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension;

import de.thm.icampus.joomdd.ejsl.eJSL.Module;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference;

@SuppressWarnings("all")
public interface ExtendedModule extends Module {
  public abstract ExtendedPageReference getExtendedPageReference();
  
  public abstract ExtendedComponent getExtendedComponent();
}
