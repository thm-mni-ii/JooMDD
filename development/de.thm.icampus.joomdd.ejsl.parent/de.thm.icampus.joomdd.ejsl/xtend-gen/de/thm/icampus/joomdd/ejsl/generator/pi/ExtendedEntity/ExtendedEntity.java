package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity;

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public interface ExtendedEntity extends Entity {
  public abstract EList<ExtendedAttribute> getExtendedAttributeList();
  
  public abstract EList<ExtendedAttribute> getExtendedParentAttributeList();
  
  public abstract Entity getInstance();
  
  public abstract EList<ExtendedAttribute> getAllattribute();
  
  public abstract EList<ExtendedReference> getExtendedReference();
  
  public abstract boolean haveIdAttribute();
  
  public abstract void putNewAttributeInEntity(final Attribute e);
  
  public abstract ExtendedAttribute searchIdAttribute();
  
  public abstract EList<ExtendedReference> getallReferenceToEntity();
  
  public abstract ExtendedAttribute getAttributeByName(final String name);
}
