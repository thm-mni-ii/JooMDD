package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Component;
import de.thm.icampus.joomdd.ejsl.eJSL.Extension;
import de.thm.icampus.joomdd.ejsl.eJSL.ExtensionPackage;
import de.thm.icampus.joomdd.ejsl.eJSL.Library;
import de.thm.icampus.joomdd.ejsl.eJSL.Module;
import de.thm.icampus.joomdd.ejsl.eJSL.Plugin;
import de.thm.icampus.joomdd.ejsl.eJSL.Template;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedLibrary;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPlugin;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedComponentImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedLibraryImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedModuleImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedPluginImpl;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.AbstractExtensionGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.ComponentGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.LibraryGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.ModuleGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.PackageGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.PluginGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.TemplateGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public class ExtensionGeneratorClient {
  private AbstractExtensionGenerator extensionsgenerator;
  
  private IFileSystemAccess fsa;
  
  private Extension ext;
  
  private String path;
  
  public ExtensionGeneratorClient(final IFileSystemAccess access, final Extension extens) {
    this.fsa = access;
    this.ext = extens;
  }
  
  public AbstractExtensionGenerator getExtensiongenerator() {
    return this.extensionsgenerator;
  }
  
  public void setExtensiongenerator(final AbstractExtensionGenerator value) {
    this.extensionsgenerator = value;
  }
  
  public void setPath(final String path) {
    this.path = path;
  }
  
  public CharSequence generateExtension() {
    final Extension ext = this.ext;
    boolean _matched = false;
    if (!_matched) {
      if (ext instanceof ExtensionPackage) {
        _matched=true;
        ExtensionPackage tempext = ((ExtensionPackage) this.ext);
        PackageGenerator _packageGenerator = new PackageGenerator(tempext, this.fsa);
        this.extensionsgenerator = _packageGenerator;
      }
    }
    if (!_matched) {
      if (ext instanceof Component) {
        _matched=true;
        ExtendedComponent tempext = new ExtendedComponentImpl(((Component) this.ext));
        ComponentGenerator _componentGenerator = new ComponentGenerator(tempext, this.fsa);
        this.extensionsgenerator = _componentGenerator;
      }
    }
    if (!_matched) {
      if (ext instanceof Module) {
        _matched=true;
        ExtendedModule tempext = new ExtendedModuleImpl(((Module) this.ext));
        ModuleGenerator _moduleGenerator = new ModuleGenerator(tempext, this.fsa);
        this.extensionsgenerator = _moduleGenerator;
      }
    }
    if (!_matched) {
      if (ext instanceof Plugin) {
        _matched=true;
        ExtendedPlugin tempext = new ExtendedPluginImpl(((Plugin) this.ext));
        PluginGenerator _pluginGenerator = new PluginGenerator(tempext, this.fsa);
        this.extensionsgenerator = _pluginGenerator;
      }
    }
    if (!_matched) {
      if (ext instanceof Library) {
        _matched=true;
        ExtendedLibrary tempext = new ExtendedLibraryImpl(((Library) this.ext));
        LibraryGenerator _libraryGenerator = new LibraryGenerator(tempext, this.fsa);
        this.extensionsgenerator = _libraryGenerator;
      }
    }
    if (!_matched) {
      if (ext instanceof Template) {
        _matched=true;
        Template tempext = ((Template) this.ext);
        TemplateGenerator _templateGenerator = new TemplateGenerator(tempext, this.fsa);
        this.extensionsgenerator = _templateGenerator;
      }
    }
    if (!_matched) {
      System.out.println("ExtensionGeneratorClient default");
    }
    boolean _notEquals = (!Objects.equal(this.path, null));
    if (_notEquals) {
      this.extensionsgenerator.setPath(this.path);
    }
    return this.extensionsgenerator.generate();
  }
  
  public Extension setExtension(final Extension extensions) {
    return this.ext = extensions;
  }
}
