package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity;

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;

@SuppressWarnings("all")
public interface ExtendedAttribute extends Attribute {
  public abstract String generatorType();
  
  public abstract Entity getEntity();
  
  public abstract Attribute getInstance();
  
  public abstract String htmlType();
}
