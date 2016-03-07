package de.thm.icampus.joomdd.ejsl.generator.ps;

import de.thm.icampus.joomdd.ejsl.eJSL.Extension;
import de.thm.icampus.joomdd.ejsl.generator.ps.AbstracteGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.ExtensionGeneratorClient;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public class ExtensionGenerator extends AbstracteGenerator {
  private EList<Extension> extensions;
  
  public ExtensionGenerator(final EList<Extension> list) {
    this.extensions = list;
  }
  
  @Override
  public CharSequence dogenerate() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Override
  public void dogenerate(final String path, final IFileSystemAccess fileGen) {
    for (final Extension ext : this.extensions) {
      {
        ExtensionGeneratorClient extClient = new ExtensionGeneratorClient(fileGen, ext);
        extClient.generateExtension();
      }
    }
  }
}
