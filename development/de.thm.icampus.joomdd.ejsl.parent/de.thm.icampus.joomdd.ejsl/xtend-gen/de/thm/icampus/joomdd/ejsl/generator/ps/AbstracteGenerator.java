package de.thm.icampus.joomdd.ejsl.generator.ps;

import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public abstract class AbstracteGenerator {
  public abstract CharSequence dogenerate();
  
  public abstract void dogenerate(final String path, final IFileSystemAccess fileGen);
}
