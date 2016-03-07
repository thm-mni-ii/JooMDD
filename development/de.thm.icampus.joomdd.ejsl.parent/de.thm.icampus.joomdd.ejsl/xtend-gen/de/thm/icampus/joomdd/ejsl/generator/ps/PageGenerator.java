package de.thm.icampus.joomdd.ejsl.generator.ps;

import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.generator.ps.AbstracteGenerator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public class PageGenerator extends AbstracteGenerator {
  private EList<Page> pagelist;
  
  public PageGenerator(final EList<Page> list) {
    this.pagelist = list;
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
