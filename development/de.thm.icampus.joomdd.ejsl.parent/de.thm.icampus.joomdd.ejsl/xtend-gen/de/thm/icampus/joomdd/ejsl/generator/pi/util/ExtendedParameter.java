package de.thm.icampus.joomdd.ejsl.generator.pi.util;

import de.thm.icampus.joomdd.ejsl.eJSL.Parameter;

@SuppressWarnings("all")
public interface ExtendedParameter extends Parameter {
  public abstract String generatorType();
  
  public abstract Parameter getInstance();
}
