package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator;

import org.eclipse.xtend.lib.Property;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public abstract class AbstractPageGenerator {
  @Property
  private IFileSystemAccess _fsa;
  
  @Property
  private String _name;
  
  @Property
  private String _path = "";
  
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
    CharSequence _indexHtml = AbstractPageGenerator.indexHtml();
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
    _fsa.generateFile(fileName, content);
  }
  
  @Pure
  public IFileSystemAccess getFsa() {
    return this._fsa;
  }
  
  public void setFsa(final IFileSystemAccess fsa) {
    this._fsa = fsa;
  }
  
  @Pure
  public String getName() {
    return this._name;
  }
  
  public void setName(final String name) {
    this._name = name;
  }
  
  @Pure
  public String getPath() {
    return this._path;
  }
  
  public void setPath(final String path) {
    this._path = path;
  }
}
