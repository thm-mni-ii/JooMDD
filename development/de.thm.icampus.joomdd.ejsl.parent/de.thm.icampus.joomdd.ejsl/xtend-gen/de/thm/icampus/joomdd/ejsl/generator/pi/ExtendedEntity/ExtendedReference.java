package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity;

import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.Reference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public interface ExtendedReference extends Reference {
  public abstract EList<ExtendedAttribute> getExtendedAttribute();
  
  public abstract EList<ExtendedAttribute> getExtendedAttributeReferenced();
  
  public abstract Entity getExtendedToEntity();
  
  public abstract Entity getExtendedFromEntity();
}
