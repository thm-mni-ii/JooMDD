package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension;

import de.thm.icampus.joomdd.ejsl.eJSL.Plugin;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public interface ExtendedPlugin extends Plugin {
  public abstract EList<ExtendedEntity> getExtendedEntities();
  
  public abstract EList<ExtendedParameter> getExtendedParameter();
}
