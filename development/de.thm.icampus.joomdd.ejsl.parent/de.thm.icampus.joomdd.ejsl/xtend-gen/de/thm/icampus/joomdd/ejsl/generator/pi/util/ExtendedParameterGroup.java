package de.thm.icampus.joomdd.ejsl.generator.pi.util;

import de.thm.icampus.joomdd.ejsl.eJSL.ParameterGroup;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public interface ExtendedParameterGroup extends ParameterGroup {
  public abstract EList<ExtendedParameter> getExtendedParameterList();
}
