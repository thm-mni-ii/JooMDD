package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Author;
import de.thm.icampus.joomdd.ejsl.eJSL.Extension;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend.lib.Property;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public abstract class AbstractExtensionGenerator {
  @Property
  private IFileSystemAccess _fsa;
  
  @Property
  private String _name;
  
  @Property
  private String _path = "";
  
  public Extension ext;
  
  /**
   * as the name gets prefixed with the type of the Extension,
   * this Field should hold the unprefixed name, needed for correct naming
   * of .php files in view and admin
   */
  @Property
  private String _noPrefixName;
  
  /**
   * Setter for path
   * Property path will contain a trailing slash
   */
  public void setPath(final String path) {
    this._path = path;
    boolean _and = false;
    boolean _isEmpty = path.isEmpty();
    boolean _not = (!_isEmpty);
    if (!_not) {
      _and = false;
    } else {
      boolean _or = false;
      boolean _endsWith = path.endsWith("/");
      if (_endsWith) {
        _or = true;
      } else {
        boolean _endsWith_1 = path.endsWith("\\");
        _or = _endsWith_1;
      }
      boolean _not_1 = (!_or);
      _and = _not_1;
    }
    if (_and) {
      String _concat = this._path.concat("/");
      this._path = _concat;
    }
  }
  
  /**
   * Get name extended path
   */
  public String getPath() {
    String _name = this.getName();
    String _plus = (_name + "/");
    return this._path.concat(_plus);
  }
  
  /**
   * Get either name extended or raw path
   * 
   * @param raw
   * @return raw path if raw = true, name extended path otherwise
   */
  public String getPath(final boolean raw) {
    if (raw) {
      return this._path;
    }
    return this.getPath();
  }
  
  /**
   * Setter for entity name.
   * 
   * @param name Name of entity
   */
  public void setName(final String name) {
    this._name = name;
  }
  
  /**
   * Getter for entity name.
   */
  public String getName() {
    return this._name;
  }
  
  /**
   * Generate directory containing default joomla index.html.
   * Directory name will be prefixed by path
   * 
   * @param dirName  using '/' as directory separator
   */
  protected void generateJoomlaDirectory(final String dirName) {
    String p = dirName;
    while (p.endsWith("/")) {
      int _length = p.length();
      int _minus = (_length - 1);
      String _substring = p.substring(0, _minus);
      p = _substring;
    }
    CharSequence _indexHtml = AbstractExtensionGenerator.indexHtml();
    this.generateFile((p + "/index.html"), _indexHtml);
  }
  
  public static CharSequence indexHtml() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<!DOCTYPE html><title></title>");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Generate File using fsa.
   * File name will be prefixed by path
   * 
   * @param fileName  using '/' as file separator
   * @param content   the to-be-written contents
   */
  protected void generateFile(final String fileName, final CharSequence content) {
    IFileSystemAccess _fsa = this.getFsa();
    String _path = this.getPath();
    String _plus = (_path + fileName);
    _fsa.generateFile(_plus, content);
  }
  
  /**
   * Generate content for entity. Every generated file will be
   * placed in the directory defined by property path
   */
  public CharSequence generate(final EList<Author> authors) {
    StringConcatenation _builder = new StringConcatenation();
    {
      int _size = authors.size();
      boolean _equals = (_size == 0);
      if (_equals) {
        _builder.append("<author>Auto Generated Author</author>");
        _builder.newLine();
        _builder.append("<authorEmail>info@generated.com</authorEmail>");
        _builder.newLine();
        _builder.append("<authorUrl>www.generated.com</authorUrl>");
        _builder.newLine();
      } else {
        {
          for(final Author author : authors) {
            _builder.append("<author>");
            String _name = author.getName();
            _builder.append(_name, "");
            _builder.append("</author>");
            _builder.newLineIfNotEmpty();
            {
              String _authoremail = author.getAuthoremail();
              boolean _notEquals = (!Objects.equal(_authoremail, null));
              if (_notEquals) {
                _builder.append("<authorEmail>");
                String _authoremail_1 = author.getAuthoremail();
                _builder.append(_authoremail_1, "");
                _builder.append("</authorEmail>");
                _builder.newLineIfNotEmpty();
              }
            }
            {
              String _authorurl = author.getAuthorurl();
              boolean _notEquals_1 = (!Objects.equal(_authorurl, null));
              if (_notEquals_1) {
                _builder.append("<authorUrl>");
                String _authorurl_1 = author.getAuthorurl();
                _builder.append(_authorurl_1, "");
                _builder.append("</authorUrl>");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public abstract CharSequence generate();
  
  /**
   * Generate content for entity.
   * 
   * @see EntityGenerator.generate()
   * @param path Will be set using
   */
  public void generate(final String path) {
    String old_path = this.getPath();
    this.setPath(path);
    this.generate();
    this.setPath(old_path);
  }
  
  public CharSequence generateParameter(final EList<ExtendedParameter> listParams) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final ExtendedParameter param : listParams) {
        _builder.append("<field");
        _builder.newLine();
        _builder.append("name=\"");
        String _name = param.getName();
        _builder.append(_name, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("type=\"");
        String _generatorType = param.generatorType();
        String _typeName = Slug.getTypeName(_generatorType);
        _builder.append(_typeName, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("label=\"");
        String _label = param.getLabel();
        _builder.append(_label, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("description=\"");
        String _descripton = param.getDescripton();
        _builder.append(_descripton, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("/>");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  @Pure
  public IFileSystemAccess getFsa() {
    return this._fsa;
  }
  
  public void setFsa(final IFileSystemAccess fsa) {
    this._fsa = fsa;
  }
  
  @Pure
  public String getNoPrefixName() {
    return this._noPrefixName;
  }
  
  public void setNoPrefixName(final String noPrefixName) {
    this._noPrefixName = noPrefixName;
  }
}
