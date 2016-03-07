package de.thm.icampus.joomdd.ejsl.generator.ps;

import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.generator.ps.AbstracteGenerator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public class EntityGenerator extends AbstracteGenerator {
  private EList<Entity> entities;
  
  public EntityGenerator(final EList<Entity> entitiesList) {
    this.entities = entitiesList;
  }
  
  @Override
  public CharSequence dogenerate() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  public void dogenerate(final String path, final IFileSystemAccess fileGen) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
}
