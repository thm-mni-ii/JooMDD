package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Author;
import de.thm.icampus.joomdd.ejsl.eJSL.CssBlock;
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair;
import de.thm.icampus.joomdd.ejsl.eJSL.Language;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.Position;
import de.thm.icampus.joomdd.ejsl.eJSL.PositionParameter;
import de.thm.icampus.joomdd.ejsl.eJSL.Template;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.AbstractExtensionGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug;
import java.util.Calendar;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template Generator</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see eJSLGenerator.GeneratorTemplatePackage#getTemplateGenerator()
 * @model
 * @generated
 */
@SuppressWarnings("all")
public class TemplateGenerator extends AbstractExtensionGenerator {
  private Template template;
  
  public TemplateGenerator(final Template template, final IFileSystemAccess fsa) {
    this.setFsa(fsa);
    String _name = template.getName();
    String _slugify = Slug.slugify(_name);
    String _plus = ("tpl_" + _slugify);
    this.setName(_plus);
    this.template = template;
  }
  
  @Override
  public CharSequence generate() {
    this.generateJoomlaDirectory("");
    CharSequence _phpIndex = this.phpIndex(this.template);
    this.generateFile("index.php", _phpIndex);
    CharSequence _phpComponent = this.phpComponent(this.template);
    this.generateFile("component.php", _phpComponent);
    CharSequence _xmlTemplateDetails = this.xmlTemplateDetails(this.template);
    this.generateFile("templateDetails.xml", _xmlTemplateDetails);
    this.generateJoomlaDirectory("css");
    CharSequence _cssGeneral = this.cssGeneral(this.template);
    this.generateFile(("css/" + "general.css"), _cssGeneral);
    CharSequence _cssGeneral_1 = this.cssGeneral(this.template);
    this.generateFile(("css/" + "offline.css"), _cssGeneral_1);
    CharSequence _cssGeneral_2 = this.cssGeneral(this.template);
    this.generateFile(("css/" + "error.css"), _cssGeneral_2);
    String _name = this.getName();
    String _plus = ("css/" + _name);
    String _plus_1 = (_plus + ".css");
    CharSequence _cssTemplate = this.cssTemplate(this.template);
    this.generateFile(_plus_1, _cssTemplate);
    String _name_1 = this.getName();
    String _plus_2 = ("css/" + _name_1);
    String _plus_3 = (_plus_2 + "_rtl.css");
    CharSequence _cssTemplate_1 = this.cssTemplate(this.template);
    this.generateFile(_plus_3, _cssTemplate_1);
    this.generateJoomlaDirectory("fonts");
    this.generateJoomlaDirectory("html");
    CharSequence _phpModule = this.phpModule(this.template);
    this.generateFile(("html/" + "modules.php"), _phpModule);
    this.generateJoomlaDirectory("javascript");
    this.generateJoomlaDirectory("images");
    CharSequence _DummyImage = this.DummyImage(this.template);
    this.generateFile("/images/dummyImage", _DummyImage);
    this.generateJoomlaDirectory("language");
    EList<Language> _languages = this.template.getLanguages();
    for (final Language lang : _languages) {
      {
        String _name_2 = lang.getName();
        String _plus_4 = ("/language/" + _name_2);
        this.generateJoomlaDirectory(_plus_4);
        String _name_3 = lang.getName();
        String _plus_5 = ("/language/" + _name_3);
        String _plus_6 = (_plus_5 + "/");
        String _name_4 = lang.getName();
        String _plus_7 = (_plus_6 + _name_4);
        String _plus_8 = (_plus_7 + ".");
        String _name_5 = this.getName();
        String _plus_9 = (_plus_8 + _name_5);
        String _plus_10 = (_plus_9 + ".ini");
        String _name_6 = lang.getName();
        CharSequence _iniLanguage = this.iniLanguage(this.template, _name_6);
        this.generateFile(_plus_10, _iniLanguage);
        String _name_7 = lang.getName();
        String _plus_11 = ("/language/" + _name_7);
        String _plus_12 = (_plus_11 + "/");
        String _name_8 = lang.getName();
        String _plus_13 = (_plus_12 + _name_8);
        String _plus_14 = (_plus_13 + ".");
        String _name_9 = this.getName();
        String _plus_15 = (_plus_14 + _name_9);
        String _plus_16 = (_plus_15 + ".sys.ini");
        String _name_10 = lang.getName();
        CharSequence _iniLanguage_1 = this.iniLanguage(this.template, _name_10);
        this.generateFile(_plus_16, _iniLanguage_1);
      }
    }
    return "";
  }
  
  public static TemplateGenerator getGenerator(final Template template, final IFileSystemAccess fsa) {
    return new TemplateGenerator(template, fsa);
  }
  
  public CharSequence xmlTemplateDetails(final Template template) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    _builder.newLine();
    _builder.append("<extension version=\"3.3\" type=\"template\" client=\"site\">");
    _builder.newLine();
    _builder.append("<name>");
    String _name = template.getName();
    _builder.append(_name, "");
    _builder.append("</name>");
    _builder.newLineIfNotEmpty();
    Manifestation _manifest = template.getManifest();
    EList<Author> _authors = _manifest.getAuthors();
    CharSequence _generate = this.generate(_authors);
    _builder.append(_generate, "");
    _builder.newLineIfNotEmpty();
    _builder.append("<creationDate>");
    Object _xifexpression = null;
    Manifestation _manifest_1 = template.getManifest();
    String _creationdate = _manifest_1.getCreationdate();
    boolean _notEquals = (!Objects.equal(_creationdate, null));
    if (_notEquals) {
      Manifestation _manifest_2 = template.getManifest();
      _xifexpression = _manifest_2.getCreationdate();
    } else {
      Calendar _instance = Calendar.getInstance();
      _xifexpression = Integer.valueOf(_instance.get(Calendar.YEAR));
    }
    _builder.append(_xifexpression, "");
    _builder.append("</creationDate>");
    _builder.newLineIfNotEmpty();
    _builder.append("<copyright>");
    String _xifexpression_1 = null;
    Manifestation _manifest_3 = template.getManifest();
    String _copyright = _manifest_3.getCopyright();
    boolean _notEquals_1 = (!Objects.equal(_copyright, null));
    if (_notEquals_1) {
      Manifestation _manifest_4 = template.getManifest();
      _xifexpression_1 = _manifest_4.getCopyright();
    } else {
      _xifexpression_1 = "All rights reserved by Author.";
    }
    _builder.append(_xifexpression_1, "");
    _builder.append("</copyright>");
    _builder.newLineIfNotEmpty();
    _builder.append("<license>");
    String _xifexpression_2 = null;
    Manifestation _manifest_5 = template.getManifest();
    String _license = _manifest_5.getLicense();
    boolean _notEquals_2 = (!Objects.equal(_license, null));
    if (_notEquals_2) {
      Manifestation _manifest_6 = template.getManifest();
      _xifexpression_2 = _manifest_6.getLicense();
    } else {
      _xifexpression_2 = "GPL 2.0";
    }
    _builder.append(_xifexpression_2, "");
    _builder.append("</license>");
    _builder.newLineIfNotEmpty();
    _builder.append("<version>");
    String _xifexpression_3 = null;
    Manifestation _manifest_7 = template.getManifest();
    String _version = _manifest_7.getVersion();
    boolean _notEquals_3 = (!Objects.equal(_version, null));
    if (_notEquals_3) {
      Manifestation _manifest_8 = template.getManifest();
      _xifexpression_3 = _manifest_8.getVersion();
    } else {
      _xifexpression_3 = "1.0.0";
    }
    _builder.append(_xifexpression_3, "");
    _builder.append("</version>");
    _builder.newLineIfNotEmpty();
    _builder.append("<description>");
    String _xifexpression_4 = null;
    Manifestation _manifest_9 = template.getManifest();
    String _description = _manifest_9.getDescription();
    boolean _notEquals_4 = (!Objects.equal(_description, null));
    if (_notEquals_4) {
      Manifestation _manifest_10 = template.getManifest();
      _xifexpression_4 = _manifest_10.getDescription();
    } else {
      _xifexpression_4 = "Place Description here";
    }
    _builder.append(_xifexpression_4, "");
    _builder.append("</description>");
    _builder.newLineIfNotEmpty();
    _builder.append("<!-- Listing of all files that should be installed for the module -->");
    _builder.newLine();
    _builder.append("<files>");
    _builder.newLine();
    _builder.append("<folder>css</folder>");
    _builder.newLine();
    _builder.append("<folder>fonts</folder>");
    _builder.newLine();
    _builder.append("<folder>html</folder>");
    _builder.newLine();
    _builder.append("<folder>images</folder>");
    _builder.newLine();
    _builder.append("<folder>javascript</folder>");
    _builder.newLine();
    _builder.append("<folder>language</folder>");
    _builder.newLine();
    _builder.append("<filename>index.html</filename>");
    _builder.newLine();
    _builder.append("<filename>index.php</filename>");
    _builder.newLine();
    _builder.append("<filename>component.php</filename>");
    _builder.newLine();
    _builder.append("</files>");
    _builder.newLine();
    _builder.append("<positions>");
    _builder.newLine();
    {
      EList<Position> _positions = template.getPositions();
      for(final Position pos : _positions) {
        _builder.append("<position>");
        String _name_1 = pos.getName();
        _builder.append(_name_1, "");
        _builder.append("</position>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</positions>");
    _builder.newLine();
    _builder.append("<!-- All language files shipped with the modul -->");
    _builder.newLine();
    _builder.append("<languages>");
    _builder.newLine();
    {
      EList<Language> _languages = template.getLanguages();
      for(final Language lang : _languages) {
        _builder.append("<language tag=\"");
        String _name_2 = lang.getName();
        _builder.append(_name_2, "");
        _builder.append("\">");
        String _name_3 = lang.getName();
        _builder.append(_name_3, "");
        _builder.append(".");
        String _name_4 = this.getName();
        _builder.append(_name_4, "");
        _builder.append(".ini</language>");
        _builder.newLineIfNotEmpty();
        _builder.append("<language tag=\"");
        String _name_5 = lang.getName();
        _builder.append(_name_5, "");
        _builder.append("\">");
        String _name_6 = lang.getName();
        _builder.append(_name_6, "");
        _builder.append(".");
        String _name_7 = this.getName();
        _builder.append(_name_7, "");
        _builder.append(".sys.ini</language>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</languages>");
    _builder.newLine();
    _builder.append("<!-- Optional parameters -->");
    _builder.newLine();
    _builder.append("<config>");
    _builder.newLine();
    _builder.append("</config>");
    _builder.newLine();
    _builder.append("</extension>");
    _builder.newLine();
    return _builder;
  }
  
  public void positions(final Template template) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public CharSequence phpIndex(final Template template) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?php");
    _builder.newLine();
    _builder.append("defined(\'_JEXEC\') or die;");
    _builder.newLine();
    _builder.append("include dirname(__FILE__).DIRECTORY_SEPARATOR.\'component.php\';");
    _builder.newLine();
    _builder.append("?>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence phpComponent(final Template template) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?php");
    _builder.newLine();
    _builder.append("defined(\'_JEXEC\') or die;");
    _builder.newLine();
    _builder.append("?>");
    _builder.newLine();
    _builder.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
    _builder.newLine();
    _builder.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"<?php echo $this->language; ?>\" lang=\"<?php echo $this->language; ?>\" dir=\"<?php echo $this->direction; ?>\">");
    _builder.newLine();
    _builder.append("<head>");
    _builder.newLine();
    _builder.append("<jdoc:include type=\"head\" />");
    _builder.newLine();
    _builder.append("<link rel=\"stylesheet\" href=\"<?php echo $this->baseurl ?>/templates/");
    String _name = this.getName();
    _builder.append(_name, "");
    _builder.append("/css/general.css\" type=\"text/css\" />");
    _builder.newLineIfNotEmpty();
    _builder.append("<link rel=\"stylesheet\" href=\"<?php echo $this->baseurl ?>/templates/<?php echo $this->template; ?>/css/");
    String _name_1 = this.getName();
    _builder.append(_name_1, "");
    _builder.append(".css\" type=\"text/css\" />");
    _builder.newLineIfNotEmpty();
    _builder.append("<?php if ($this->direction == \'rtl\') : ?>");
    _builder.newLine();
    _builder.append("<link rel=\"stylesheet\" href=\"<?php echo $this->baseurl ?>/templates/<?php echo $this->template ?>/css/");
    String _name_2 = this.getName();
    _builder.append(_name_2, "");
    _builder.append("_rtl.css\" type=\"text/css\" />");
    _builder.newLineIfNotEmpty();
    _builder.append("<?php endif; ?>");
    _builder.newLine();
    _builder.append("</head>");
    _builder.newLine();
    _builder.append("<body class=\"contentpane\">");
    _builder.newLine();
    {
      EList<Position> _positions = template.getPositions();
      for(final Position pos : _positions) {
        _builder.append("<div id=\"");
        String _name_3 = pos.getName();
        _builder.append(_name_3, "");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        {
          EList<PositionParameter> _positionparameters = pos.getPositionparameters();
          for(final PositionParameter posType : _positionparameters) {
            _builder.append("<div id=\"");
            String _divid = posType.getDivid();
            _builder.append(_divid, "");
            _builder.append("\"><jdoc:include type=\"");
            String _type = posType.getType();
            _builder.append(_type, "");
            _builder.append("\" name=\"");
            String _name_4 = posType.getName();
            _builder.append(_name_4, "");
            _builder.append("\" style=\"xhtml\" /></div><!--#");
            String _divid_1 = posType.getDivid();
            _builder.append(_divid_1, "");
            _builder.append("-->");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("</div><!--#");
        String _name_5 = pos.getName();
        _builder.append(_name_5, "");
        _builder.append("-->");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</body>");
    _builder.newLine();
    _builder.append("</html>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence phpModule(final Template template) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?php");
    _builder.newLine();
    _builder.append("// no direct access");
    _builder.newLine();
    _builder.append("defined(\'_JEXEC\') or die;");
    _builder.newLine();
    _builder.append("?>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence cssGeneral(final Template template) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@charset \"utf-8\";");
    _builder.newLine();
    _builder.append("* { ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("margin: 0px; ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("padding: 0px; ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("border: 0px;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("html { ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("height: 100.2%;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("body { }");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence cssTemplate(final Template template) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<CssBlock> _cssblocks = template.getCssblocks();
      for(final CssBlock css : _cssblocks) {
        String _selector = css.getSelector();
        _builder.append(_selector, "");
        _builder.append(" {");
        _builder.newLineIfNotEmpty();
        {
          EList<KeyValuePair> _keyvaluepairs = css.getKeyvaluepairs();
          for(final KeyValuePair cssValue : _keyvaluepairs) {
            String _name = cssValue.getName();
            _builder.append(_name, "");
            _builder.append(": ");
            String _value = cssValue.getValue();
            _builder.append(_value, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    }
    {
      EList<Position> _positions = template.getPositions();
      for(final Position pos : _positions) {
        _builder.append("div#");
        String _name_1 = pos.getName();
        _builder.append(_name_1, "");
        _builder.append(" {");
        _builder.newLineIfNotEmpty();
        {
          EList<PositionParameter> _positionparameters = pos.getPositionparameters();
          for(final PositionParameter posCss : _positionparameters) {
            {
              EList<KeyValuePair> _keyvaluepairs_1 = posCss.getKeyvaluepairs();
              for(final KeyValuePair posCssValues : _keyvaluepairs_1) {
                String _name_2 = posCssValues.getName();
                _builder.append(_name_2, "");
                _builder.append(": ");
                String _value_1 = posCssValues.getValue();
                _builder.append(_value_1, "");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence iniLanguage(final Template template, final String languageFileName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Language> _languages = template.getLanguages();
      for(final Language pageLang : _languages) {
        {
          String _name = pageLang.getName();
          boolean _equals = Objects.equal(_name, languageFileName);
          if (_equals) {
            {
              EList<KeyValuePair> _keyvaluepairs = pageLang.getKeyvaluepairs();
              for(final KeyValuePair langvalue : _keyvaluepairs) {
                String _name_1 = langvalue.getName();
                _builder.append(_name_1, "");
                _builder.append("=\"");
                String _value = langvalue.getValue();
                _builder.append(_value, "");
                _builder.append("\"");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence DummyImage(final Template template) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("DummyImageFile");
    _builder.newLine();
    return _builder;
  }
}
