package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension;

import de.thm.icampus.joomdd.ejsl.eJSL.Library;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public interface ExtendedLibrary extends Library {
  public abstract EList<ExtendedEntity> getExtendedEntities();
}
