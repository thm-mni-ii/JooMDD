package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.thm.icampus.joomdd.ejsl.eJSL.Author;
import de.thm.icampus.joomdd.ejsl.eJSL.Component;
import de.thm.icampus.joomdd.ejsl.eJSL.Extension;
import de.thm.icampus.joomdd.ejsl.eJSL.ExtensionPackage;
import de.thm.icampus.joomdd.ejsl.eJSL.Library;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.Module;
import de.thm.icampus.joomdd.ejsl.eJSL.Plugin;
import de.thm.icampus.joomdd.ejsl.eJSL.PluginKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.Template;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.AbstractExtensionGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.ExtensionGeneratorClient;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug;
import java.util.Calendar;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend.lib.Property;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class PackageGenerator extends AbstractExtensionGenerator {
  private ExtensionGeneratorClient extClient;
  
  @Property
  private ExtensionPackage _pkg;
  
  public PackageGenerator(final ExtensionPackage pkg, final IFileSystemAccess access) {
    this.setPkg(pkg);
    this.setFsa(access);
    String _name = pkg.getName();
    String _slugify = Slug.slugify(_name);
    String _plus = ("pkg_" + _slugify);
    this.setName(_plus);
  }
  
  @Override
  public CharSequence generate() {
    this.generateJoomlaDirectory("");
    String _name = this.getName();
    String _plus = (_name + ".xml");
    ExtensionPackage _pkg = this.getPkg();
    CharSequence _xmlContent = this.xmlContent(_pkg);
    this.generateFile(_plus, _xmlContent);
    this.generateJoomlaDirectory("packages");
    ExtensionPackage _pkg_1 = this.getPkg();
    EList<Extension> _extensions = _pkg_1.getExtensions();
    for (final Extension ext : _extensions) {
      {
        this.extClient.setExtension(ext);
        String _name_1 = this.getName();
        String _plus_1 = (_name_1 + "/packages/");
        this.extClient.setPath(_plus_1);
        this.extClient.generateExtension();
      }
    }
    return "";
  }
  
  public CharSequence xmlContent(final ExtensionPackage pkg) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<extension type=\"package\" version=\"3.4.0\" method=\"upgrade\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("<name>");
    String _name = pkg.getName();
    _builder.append(_name, "\t    ");
    _builder.append("</name>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t    ");
    Manifestation _manifest = pkg.getManifest();
    EList<Author> _authors = _manifest.getAuthors();
    CharSequence _generate = this.generate(_authors);
    _builder.append(_generate, "\t    ");
    _builder.append("            ");
    _builder.newLineIfNotEmpty();
    {
      Manifestation _manifest_1 = pkg.getManifest();
      String _creationdate = _manifest_1.getCreationdate();
      boolean _notEquals = (!Objects.equal(_creationdate, null));
      if (_notEquals) {
        _builder.append("\t    ");
        _builder.append("<creationDate>");
        Manifestation _manifest_2 = pkg.getManifest();
        String _creationdate_1 = _manifest_2.getCreationdate();
        _builder.append(_creationdate_1, "\t    ");
        _builder.append("</creationDate>");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("\t    ");
        _builder.append("<creationDate>");
        Calendar _instance = Calendar.getInstance();
        int _get = _instance.get(Calendar.YEAR);
        _builder.append(_get, "\t    ");
        _builder.append("</creationDate>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      Manifestation _manifest_3 = pkg.getManifest();
      String _copyright = _manifest_3.getCopyright();
      boolean _notEquals_1 = (!Objects.equal(_copyright, null));
      if (_notEquals_1) {
        _builder.append("\t    ");
        _builder.append("<copyright>");
        Manifestation _manifest_4 = pkg.getManifest();
        String _copyright_1 = _manifest_4.getCopyright();
        _builder.append(_copyright_1, "\t    ");
        _builder.append("</copyright>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      Manifestation _manifest_5 = pkg.getManifest();
      String _license = _manifest_5.getLicense();
      boolean _notEquals_2 = (!Objects.equal(_license, null));
      if (_notEquals_2) {
        _builder.append("\t    ");
        _builder.append("<license>GPL 2.0</license>");
        _builder.newLine();
      }
    }
    {
      Manifestation _manifest_6 = pkg.getManifest();
      String _version = _manifest_6.getVersion();
      boolean _notEquals_3 = (!Objects.equal(_version, null));
      if (_notEquals_3) {
        _builder.append("\t    ");
        _builder.append("<version>");
        Manifestation _manifest_7 = pkg.getManifest();
        String _version_1 = _manifest_7.getVersion();
        _builder.append(_version_1, "\t    ");
        _builder.append("</version>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      Manifestation _manifest_8 = pkg.getManifest();
      String _description = _manifest_8.getDescription();
      boolean _notEquals_4 = (!Objects.equal(_description, null));
      if (_notEquals_4) {
        _builder.append("\t    ");
        _builder.append("<description>");
        Manifestation _manifest_9 = pkg.getManifest();
        String _description_1 = _manifest_9.getDescription();
        _builder.append(_description_1, "\t    ");
        _builder.append("</description>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t    ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<files folder=\"packages\">");
    _builder.newLine();
    {
      EList<Extension> _extensions = pkg.getExtensions();
      Iterable<Component> _filter = Iterables.<Component>filter(_extensions, Component.class);
      for(final Component com : _filter) {
        _builder.append("\t\t\t");
        _builder.append("<file type=\"component\" id=\"");
        String _name_1 = pkg.getName();
        _builder.append(_name_1, "\t\t\t");
        _builder.append("\">");
        String _name_2 = com.getName();
        _builder.append(_name_2, "\t\t\t");
        _builder.append(".zip</file>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Extension> _extensions_1 = pkg.getExtensions();
      Iterable<Library> _filter_1 = Iterables.<Library>filter(_extensions_1, Library.class);
      for(final Library lib : _filter_1) {
        _builder.append("\t                ");
        _builder.append("<file type=\"library\" id=\"");
        String _name_3 = pkg.getName();
        _builder.append(_name_3, "\t                ");
        _builder.append("\">");
        String _name_4 = lib.getName();
        _builder.append(_name_4, "\t                ");
        _builder.append(".zip</file>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Extension> _extensions_2 = pkg.getExtensions();
      Iterable<Module> _filter_2 = Iterables.<Module>filter(_extensions_2, Module.class);
      for(final Module mod : _filter_2) {
        _builder.append("\t                ");
        _builder.append("<file type=\"module\" id=\"");
        String _name_5 = pkg.getName();
        _builder.append(_name_5, "\t                ");
        _builder.append("\" client=\"site\">");
        String _name_6 = mod.getName();
        _builder.append(_name_6, "\t                ");
        _builder.append(".zip</file>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Extension> _extensions_3 = pkg.getExtensions();
      Iterable<Template> _filter_3 = Iterables.<Template>filter(_extensions_3, Template.class);
      for(final Template tpl : _filter_3) {
        _builder.append("<file type=\"template\" id=\"");
        String _name_7 = pkg.getName();
        _builder.append(_name_7, "");
        _builder.append("\">");
        String _name_8 = tpl.getName();
        _builder.append(_name_8, "");
        _builder.append(".zip</file>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Extension> _extensions_4 = pkg.getExtensions();
      Iterable<Plugin> _filter_4 = Iterables.<Plugin>filter(_extensions_4, Plugin.class);
      for(final Plugin plg : _filter_4) {
        _builder.append("<file type=\"plugin\" id=\"");
        String _name_9 = pkg.getName();
        _builder.append(_name_9, "");
        _builder.append("\" group=\"");
        PluginKinds _type = plg.getType();
        _builder.append(_type, "");
        _builder.append("\">");
        String _name_10 = plg.getName();
        _builder.append(_name_10, "");
        _builder.append(".zip</file>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("</files>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</extension>");
    _builder.newLine();
    return _builder;
  }
  
  @Pure
  public ExtensionPackage getPkg() {
    return this._pkg;
  }
  
  public void setPkg(final ExtensionPackage pkg) {
    this._pkg = pkg;
  }
}
