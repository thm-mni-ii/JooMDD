package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Author;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair;
import de.thm.icampus.joomdd.ejsl.eJSL.Language;
import de.thm.icampus.joomdd.ejsl.eJSL.Library;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.Method;
import de.thm.icampus.joomdd.ejsl.eJSL.MethodParameter;
import de.thm.icampus.joomdd.ejsl.eJSL.Type;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedLibrary;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.AbstractExtensionGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug;
import java.util.Calendar;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend.lib.Property;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library Generator</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see eJSLGenerator.GeneratorTemplatePackage#getLibraryGenerator()
 * @model
 * @generated
 */
@SuppressWarnings("all")
public class LibraryGenerator extends AbstractExtensionGenerator {
  @Property
  private ExtendedLibrary _library;
  
  private String subpackageName;
  
  public LibraryGenerator(final ExtendedLibrary library, final IFileSystemAccess access) {
    this.setLibrary(library);
    String _name = library.getName();
    String _slugify = Slug.slugify(_name);
    String _plus = ("lib_" + _slugify);
    this.setName(_plus);
    String _name_1 = library.getName();
    this.subpackageName = _name_1;
    this.setFsa(access);
    this.formatName(library);
  }
  
  public void formatName(final ExtendedLibrary library) {
    String _name = library.getName();
    String _slugify = Slug.slugify(_name);
    library.setName(_slugify);
  }
  
  @Override
  public CharSequence generate() {
    this.generateJoomlaDirectory("");
    String _name = this.getName();
    String _plus = (_name + ".xml");
    ExtendedLibrary _library = this.getLibrary();
    String _firstLower = StringExtensions.toFirstLower(this.subpackageName);
    CharSequence _xmlContent = this.xmlContent(_library, _firstLower);
    this.generateFile(_plus, _xmlContent);
    ExtendedLibrary _library_1 = this.getLibrary();
    EList<Language> _languages = _library_1.getLanguages();
    int _size = _languages.size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      this.generateJoomlaDirectory("language");
      ExtendedLibrary _library_2 = this.getLibrary();
      EList<Language> _languages_1 = _library_2.getLanguages();
      for (final Language lang : _languages_1) {
        {
          String _name_1 = lang.getName();
          String _plus_1 = ("/language/" + _name_1);
          this.generateJoomlaDirectory(_plus_1);
          String _name_2 = lang.getName();
          String _plus_2 = ("/language/" + _name_2);
          String _plus_3 = (_plus_2 + "/");
          String _name_3 = lang.getName();
          String _plus_4 = (_plus_3 + _name_3);
          String _plus_5 = (_plus_4 + ".");
          String _name_4 = this.getName();
          String _plus_6 = (_plus_5 + _name_4);
          String _plus_7 = (_plus_6 + ".ini");
          ExtendedLibrary _library_3 = this.getLibrary();
          String _name_5 = lang.getName();
          CharSequence _iniLanguage = this.iniLanguage(_library_3, _name_5);
          this.generateFile(_plus_7, _iniLanguage);
          String _name_6 = lang.getName();
          String _plus_8 = ("/language/" + _name_6);
          String _plus_9 = (_plus_8 + "/");
          String _name_7 = lang.getName();
          String _plus_10 = (_plus_9 + _name_7);
          String _plus_11 = (_plus_10 + ".");
          String _name_8 = this.getName();
          String _plus_12 = (_plus_11 + _name_8);
          String _plus_13 = (_plus_12 + ".sys.ini");
          ExtendedLibrary _library_4 = this.getLibrary();
          String _name_9 = lang.getName();
          CharSequence _iniLanguage_1 = this.iniLanguage(_library_4, _name_9);
          this.generateFile(_plus_13, _iniLanguage_1);
        }
      }
    }
    ExtendedLibrary _library_3 = this.getLibrary();
    EList<de.thm.icampus.joomdd.ejsl.eJSL.Package> _packages = _library_3.getPackages();
    for (final de.thm.icampus.joomdd.ejsl.eJSL.Package packageItem : _packages) {
      {
        String _name_1 = packageItem.getName();
        this.generateJoomlaDirectory(_name_1);
        EList<de.thm.icampus.joomdd.ejsl.eJSL.Class> _classes = packageItem.getClasses();
        for (final de.thm.icampus.joomdd.ejsl.eJSL.Class className : _classes) {
          String _name_2 = packageItem.getName();
          String _plus_1 = ("/" + _name_2);
          String _plus_2 = (_plus_1 + "/");
          String _name_3 = className.getName();
          String _plus_3 = (_plus_2 + _name_3);
          String _plus_4 = (_plus_3 + ".php");
          String _name_4 = className.getName();
          CharSequence _phpFile = this.phpFile(packageItem, _name_4);
          this.generateFile(_plus_4, _phpFile);
        }
      }
    }
    ExtendedLibrary _library_4 = this.getLibrary();
    EList<de.thm.icampus.joomdd.ejsl.eJSL.Class> _classes = _library_4.getClasses();
    for (final de.thm.icampus.joomdd.ejsl.eJSL.Class className : _classes) {
      String _name_1 = className.getName();
      String _plus_1 = (_name_1 + ".php");
      ExtendedLibrary _library_5 = this.getLibrary();
      String _name_2 = className.getName();
      CharSequence _phpFile = this.phpFile(_library_5, _name_2);
      this.generateFile(_plus_1, _phpFile);
    }
    ExtendedLibrary _library_6 = this.getLibrary();
    EList<Entity> _entities = _library_6.getEntities();
    for (final Entity entity : _entities) {
      String _name_3 = entity.getName();
      String _plus_2 = (_name_3 + ".php");
      ExtendedLibrary _library_7 = this.getLibrary();
      String _name_4 = entity.getName();
      CharSequence _entityFile = this.entityFile(_library_7, _name_4);
      this.generateFile(_plus_2, _entityFile);
    }
    return "";
  }
  
  public CharSequence entityFile(final ExtendedLibrary library, final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ExtendedEntity> _extendedEntities = library.getExtendedEntities();
      for(final ExtendedEntity entity : _extendedEntities) {
        _builder.append("<?php");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("\t ");
        _builder.append("* @package\t\tJoomla.Libraries");
        _builder.newLine();
        _builder.append("\t ");
        _builder.append("* @subpackage\t");
        _builder.append(this.subpackageName, "\t ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("\t ");
        _builder.append("* @copyright");
        _builder.newLine();
        _builder.append("\t ");
        _builder.append("* @license\t\t");
        _builder.newLine();
        _builder.append("\t ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("defined(\'_JEXEC\') or die();");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("\t ");
        _builder.append("* ");
        _builder.append(this.subpackageName, "\t ");
        _builder.append(entityName, "\t ");
        _builder.append(" Class.");
        _builder.newLineIfNotEmpty();
        _builder.append("\t ");
        _builder.append("* ");
        _builder.newLine();
        _builder.append("\t ");
        _builder.append("* @package\t\tJoomla.Libraries");
        _builder.newLine();
        _builder.append("\t ");
        _builder.append("* @subpackage\t");
        _builder.append(this.subpackageName, "\t ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t ");
        _builder.append("* @since\t\t3.3");
        _builder.newLine();
        _builder.append("\t ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("class ");
        _builder.append(this.subpackageName, "\t");
        _builder.append(entityName, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("{");
        _builder.newLine();
        {
          EList<ExtendedAttribute> _extendedAttributeList = entity.getExtendedAttributeList();
          for(final ExtendedAttribute attribute : _extendedAttributeList) {
            _builder.append("\t\t");
            _builder.append("/**");
            _builder.newLine();
            _builder.append("\t\t");
            _builder.append(" ");
            _builder.append("* @var\t\t");
            String _generatorType = attribute.generatorType();
            _builder.append(_generatorType, "\t\t ");
            _builder.append("\tVariable description");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append(" ");
            _builder.append("* @since\t3.3");
            _builder.newLine();
            _builder.append("\t\t");
            _builder.append(" ");
            _builder.append("*/");
            _builder.newLine();
            _builder.append("\t\t");
            _builder.append("private $");
            String _name = attribute.getName();
            _builder.append(_name, "\t\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.newLine();
          }
        }
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* Returns properties of class");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* @param\t$property\tName of searched property");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* ");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* @return\tmixed\tProperty of this class");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("public function __get($property)");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("if (property_exists($this, $property))");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("return $this->$property;");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* Sets value for property");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* @param\tstring\t$property\tName of property");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* @param\tmixed\t$value\t\tNew value for property");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* @return\tvoid");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("public function __set($property, $value)");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("if (property_exists($this, $property))");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("$this->$property = $value;");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* Method to get a single record.");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* @param   integer  $pk  The id of the primary key.");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* @return  mixed  Object on success, false on failure.");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* @since   1.6");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("public function getItem($pk = null)");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("// TODO: auto-generated method stub");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* Method to save the form data.");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* @param   array  $data  The form data.");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* @return  boolean  True on success.");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("* @since\t3.1");
        _builder.newLine();
        _builder.append("\t\t ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("public function save($data)");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("// TODO: auto-generated method stub");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("?>");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  /**
   * Method, that generates php files for classes
   */
  public CharSequence phpFile(final Library library, final String contentName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?php");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @package\t\tJoomla.Libraries");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @subpackage\t");
    _builder.append(this.subpackageName, "\t ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @copyright");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @license\t\t");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("defined(\'_JEXEC\') or die();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<de.thm.icampus.joomdd.ejsl.eJSL.Class> _classes = library.getClasses();
      for(final de.thm.icampus.joomdd.ejsl.eJSL.Class classObj : _classes) {
        {
          String _name = classObj.getName();
          boolean _equals = Objects.equal(_name, contentName);
          if (_equals) {
            _builder.append("\t");
            _builder.append("/**");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("* ");
            _builder.append(this.subpackageName, "\t");
            String _name_1 = classObj.getName();
            _builder.append(_name_1, "\t");
            _builder.append(" Class.");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("* ");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("* @package\t\tJoomla.Libraries");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("* @subpackage\t");
            _builder.append(this.subpackageName, "\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("* @since\t\t3.3");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("*/");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("class ");
            _builder.append(this.subpackageName, "\t");
            String _name_2 = classObj.getName();
            _builder.append(_name_2, "\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("{");
            _builder.newLine();
            {
              EList<Method> _methods = classObj.getMethods();
              for(final Method method : _methods) {
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("/**");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append(" ");
                _builder.append("* Method description");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append(" ");
                _builder.append("*");
                _builder.newLine();
                {
                  EList<MethodParameter> _methodparameters = method.getMethodparameters();
                  for(final MethodParameter param : _methodparameters) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append(" ");
                    _builder.append("* @param\t");
                    String _switchResult = null;
                    Type _type = param.getType();
                    CharSequence _typeName = this.getTypeName(_type);
                    boolean _matched = false;
                    if (!_matched) {
                      if (Objects.equal(_typeName, "string")) {
                        _matched=true;
                        _switchResult = "string";
                      }
                    }
                    if (!_matched) {
                      if (Objects.equal(_typeName, "bool")) {
                        _matched=true;
                        _switchResult = "boolean";
                      }
                    }
                    if (!_matched) {
                      if (Objects.equal(_typeName, "int")) {
                        _matched=true;
                        _switchResult = "integer";
                      }
                    }
                    if (!_matched) {
                      if (Objects.equal(_typeName, "float")) {
                        _matched=true;
                        _switchResult = "float";
                      }
                    }
                    if (!_matched) {
                      if (Objects.equal(_typeName, "array")) {
                        _matched=true;
                        _switchResult = "array";
                      }
                    }
                    if (!_matched) {
                      _switchResult = "mixed";
                    }
                    _builder.append(_switchResult, "\t\t ");
                    _builder.append("\t$");
                    String _name_3 = param.getName();
                    _builder.append(_name_3, "\t\t ");
                    _builder.append("\tParameter description");
                    _builder.newLineIfNotEmpty();
                  }
                }
                _builder.append("\t");
                _builder.append("\t");
                _builder.append(" ");
                _builder.append("*");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append(" ");
                _builder.append("* @return\t");
                String _switchResult_1 = null;
                Type _type_1 = method.getType();
                CharSequence _typeName_1 = this.getTypeName(_type_1);
                boolean _matched_1 = false;
                if (!_matched_1) {
                  if (Objects.equal(_typeName_1, "string")) {
                    _matched_1=true;
                    _switchResult_1 = "string\tDescription";
                  }
                }
                if (!_matched_1) {
                  if (Objects.equal(_typeName_1, "bool")) {
                    _matched_1=true;
                    _switchResult_1 = "boolean\tDescription";
                  }
                }
                if (!_matched_1) {
                  if (Objects.equal(_typeName_1, "int")) {
                    _matched_1=true;
                    _switchResult_1 = "integer\tDescription";
                  }
                }
                if (!_matched_1) {
                  if (Objects.equal(_typeName_1, "float")) {
                    _matched_1=true;
                    _switchResult_1 = "float\tDescription";
                  }
                }
                if (!_matched_1) {
                  if (Objects.equal(_typeName_1, "array")) {
                    _matched_1=true;
                    _switchResult_1 = "array\tDescription";
                  }
                }
                if (!_matched_1) {
                  _switchResult_1 = "mixed\tDescription";
                }
                _builder.append(_switchResult_1, "\t\t ");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append(" ");
                _builder.append("*/");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("public function ");
                String _name_4 = method.getName();
                _builder.append(_name_4, "\t\t");
                _builder.append("(");
                {
                  EList<MethodParameter> _methodparameters_1 = method.getMethodparameters();
                  boolean _hasElements = false;
                  for(final MethodParameter param_1 : _methodparameters_1) {
                    if (!_hasElements) {
                      _hasElements = true;
                    } else {
                      _builder.appendImmediate(", ", "\t\t");
                    }
                    _builder.append("$");
                    String _name_5 = param_1.getName();
                    _builder.append(_name_5, "\t\t");
                  }
                }
                _builder.append(")");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("{");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("// TODO: auto-generated method stub");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                String _switchResult_2 = null;
                Type _type_2 = method.getType();
                CharSequence _typeName_2 = this.getTypeName(_type_2);
                boolean _matched_2 = false;
                if (!_matched_2) {
                  if (Objects.equal(_typeName_2, "string")) {
                    _matched_2=true;
                    String _returnvalue = method.getReturnvalue();
                    String _plus = ("$" + _returnvalue);
                    _switchResult_2 = (_plus + " = \'\';");
                  }
                }
                if (!_matched_2) {
                  if (Objects.equal(_typeName_2, "bool")) {
                    _matched_2=true;
                    String _returnvalue_1 = method.getReturnvalue();
                    String _plus_1 = ("$" + _returnvalue_1);
                    _switchResult_2 = (_plus_1 + " = false;");
                  }
                }
                if (!_matched_2) {
                  if (Objects.equal(_typeName_2, "int")) {
                    _matched_2=true;
                    String _returnvalue_2 = method.getReturnvalue();
                    String _plus_2 = ("$" + _returnvalue_2);
                    _switchResult_2 = (_plus_2 + " = 0;");
                  }
                }
                if (!_matched_2) {
                  if (Objects.equal(_typeName_2, "float")) {
                    _matched_2=true;
                    String _returnvalue_3 = method.getReturnvalue();
                    String _plus_3 = ("$" + _returnvalue_3);
                    _switchResult_2 = (_plus_3 + " = 0.0;");
                  }
                }
                if (!_matched_2) {
                  if (Objects.equal(_typeName_2, "array")) {
                    _matched_2=true;
                    String _returnvalue_4 = method.getReturnvalue();
                    String _plus_4 = ("$" + _returnvalue_4);
                    _switchResult_2 = (_plus_4 + " = array();");
                  }
                }
                if (!_matched_2) {
                  String _returnvalue_5 = method.getReturnvalue();
                  String _plus_5 = ("$" + _returnvalue_5);
                  _switchResult_2 = (_plus_5 + " = null;");
                }
                _builder.append(_switchResult_2, "\t\t\t");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("return $");
                String _returnvalue_6 = method.getReturnvalue();
                _builder.append(_returnvalue_6, "\t\t\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("}");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.newLine();
              }
            }
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("?>");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Method, that generates php files for classes in Package
   */
  public CharSequence phpFile(final de.thm.icampus.joomdd.ejsl.eJSL.Package packageObj, final String contentName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?php");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @package\t\tJoomla.Libraries");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @subpackage\t");
    _builder.append(this.subpackageName, "\t ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @copyright");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @license\t\t");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("defined(\'_JEXEC\') or die();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<de.thm.icampus.joomdd.ejsl.eJSL.Class> _classes = packageObj.getClasses();
      for(final de.thm.icampus.joomdd.ejsl.eJSL.Class classObj : _classes) {
        {
          String _name = classObj.getName();
          boolean _equals = Objects.equal(_name, contentName);
          if (_equals) {
            _builder.append("\t");
            _builder.append("/**");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("* ");
            _builder.append(this.subpackageName, "\t");
            String _name_1 = classObj.getName();
            _builder.append(_name_1, "\t");
            _builder.append(" Class.");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("* ");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("* @package\t\tJoomla.Libraries");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("* @subpackage\t");
            _builder.append(this.subpackageName, "\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("* @since\t\t3.3");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("*/");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("class ");
            _builder.append(this.subpackageName, "\t");
            String _name_2 = classObj.getName();
            _builder.append(_name_2, "\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("{");
            _builder.newLine();
            {
              EList<Method> _methods = classObj.getMethods();
              for(final Method method : _methods) {
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("/**");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append(" ");
                _builder.append("* Method description");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append(" ");
                _builder.append("*");
                _builder.newLine();
                {
                  EList<MethodParameter> _methodparameters = method.getMethodparameters();
                  for(final MethodParameter param : _methodparameters) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append(" ");
                    _builder.append("* @param\t");
                    String _switchResult = null;
                    Type _type = param.getType();
                    CharSequence _typeName = this.getTypeName(_type);
                    boolean _matched = false;
                    if (!_matched) {
                      if (Objects.equal(_typeName, "string")) {
                        _matched=true;
                        _switchResult = "string";
                      }
                    }
                    if (!_matched) {
                      if (Objects.equal(_typeName, "bool")) {
                        _matched=true;
                        _switchResult = "boolean";
                      }
                    }
                    if (!_matched) {
                      if (Objects.equal(_typeName, "int")) {
                        _matched=true;
                        _switchResult = "integer";
                      }
                    }
                    if (!_matched) {
                      if (Objects.equal(_typeName, "float")) {
                        _matched=true;
                        _switchResult = "float";
                      }
                    }
                    if (!_matched) {
                      if (Objects.equal(_typeName, "array")) {
                        _matched=true;
                        _switchResult = "array";
                      }
                    }
                    if (!_matched) {
                      _switchResult = "mixed";
                    }
                    _builder.append(_switchResult, "\t\t ");
                    _builder.append("\t$");
                    String _name_3 = param.getName();
                    _builder.append(_name_3, "\t\t ");
                    _builder.append("\tParameter description");
                    _builder.newLineIfNotEmpty();
                  }
                }
                _builder.append("\t");
                _builder.append("\t");
                _builder.append(" ");
                _builder.append("*");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append(" ");
                _builder.append("* @return\t");
                String _switchResult_1 = null;
                Type _type_1 = method.getType();
                CharSequence _typeName_1 = this.getTypeName(_type_1);
                boolean _matched_1 = false;
                if (!_matched_1) {
                  if (Objects.equal(_typeName_1, "string")) {
                    _matched_1=true;
                    _switchResult_1 = "string\tDescription";
                  }
                }
                if (!_matched_1) {
                  if (Objects.equal(_typeName_1, "bool")) {
                    _matched_1=true;
                    _switchResult_1 = "boolean\tDescription";
                  }
                }
                if (!_matched_1) {
                  if (Objects.equal(_typeName_1, "int")) {
                    _matched_1=true;
                    _switchResult_1 = "integer\tDescription";
                  }
                }
                if (!_matched_1) {
                  if (Objects.equal(_typeName_1, "float")) {
                    _matched_1=true;
                    _switchResult_1 = "float\tDescription";
                  }
                }
                if (!_matched_1) {
                  if (Objects.equal(_typeName_1, "array")) {
                    _matched_1=true;
                    _switchResult_1 = "array\tDescription";
                  }
                }
                if (!_matched_1) {
                  _switchResult_1 = "mixed\tDescription";
                }
                _builder.append(_switchResult_1, "\t\t ");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append(" ");
                _builder.append("*/");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("public function ");
                String _name_4 = method.getName();
                _builder.append(_name_4, "\t\t");
                _builder.append("(");
                {
                  EList<MethodParameter> _methodparameters_1 = method.getMethodparameters();
                  boolean _hasElements = false;
                  for(final MethodParameter param_1 : _methodparameters_1) {
                    if (!_hasElements) {
                      _hasElements = true;
                    } else {
                      _builder.appendImmediate(", ", "\t\t");
                    }
                    _builder.append("$");
                    String _name_5 = param_1.getName();
                    _builder.append(_name_5, "\t\t");
                  }
                }
                _builder.append(")");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("{");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("// TODO: auto-generated method stub");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                String _switchResult_2 = null;
                Type _type_2 = method.getType();
                CharSequence _typeName_2 = this.getTypeName(_type_2);
                boolean _matched_2 = false;
                if (!_matched_2) {
                  if (Objects.equal(_typeName_2, "string")) {
                    _matched_2=true;
                    String _returnvalue = method.getReturnvalue();
                    String _plus = ("$" + _returnvalue);
                    _switchResult_2 = (_plus + " = \'\';");
                  }
                }
                if (!_matched_2) {
                  if (Objects.equal(_typeName_2, "bool")) {
                    _matched_2=true;
                    String _returnvalue_1 = method.getReturnvalue();
                    String _plus_1 = ("$" + _returnvalue_1);
                    _switchResult_2 = (_plus_1 + " = false;");
                  }
                }
                if (!_matched_2) {
                  if (Objects.equal(_typeName_2, "int")) {
                    _matched_2=true;
                    String _returnvalue_2 = method.getReturnvalue();
                    String _plus_2 = ("$" + _returnvalue_2);
                    _switchResult_2 = (_plus_2 + " = 0;");
                  }
                }
                if (!_matched_2) {
                  if (Objects.equal(_typeName_2, "float")) {
                    _matched_2=true;
                    String _returnvalue_3 = method.getReturnvalue();
                    String _plus_3 = ("$" + _returnvalue_3);
                    _switchResult_2 = (_plus_3 + " = 0.0;");
                  }
                }
                if (!_matched_2) {
                  if (Objects.equal(_typeName_2, "array")) {
                    _matched_2=true;
                    String _returnvalue_4 = method.getReturnvalue();
                    String _plus_4 = ("$" + _returnvalue_4);
                    _switchResult_2 = (_plus_4 + " = array();");
                  }
                }
                if (!_matched_2) {
                  String _returnvalue_5 = method.getReturnvalue();
                  String _plus_5 = ("$" + _returnvalue_5);
                  _switchResult_2 = (_plus_5 + " = null;");
                }
                _builder.append(_switchResult_2, "\t\t\t");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("return $");
                String _returnvalue_6 = method.getReturnvalue();
                _builder.append(_returnvalue_6, "\t\t\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("}");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.newLine();
              }
            }
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("?>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence getTypeName(final ExtendedAttribute type) {
    String _htmlType = type.htmlType();
    return Slug.getTypeName(_htmlType);
  }
  
  public CharSequence getTypeName(final Type type) {
    return Slug.getTypeName(type);
  }
  
  public CharSequence iniLanguage(final Library library, final String languageFileName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Language> _languages = library.getLanguages();
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
  
  public CharSequence xmlContent(final Library library, final String libName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    _builder.newLine();
    _builder.append("<extension type=\"library\" version=\"3.3.0\" method=\"upgrade\">");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<name>");
    String _name = library.getName();
    _builder.append(_name, "    ");
    _builder.append("</name>");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("<libraryname>");
    _builder.append(libName, "    ");
    _builder.append("</libraryname>");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    Manifestation _manifest = library.getManifest();
    EList<Author> _authors = _manifest.getAuthors();
    CharSequence _generate = this.generate(_authors);
    _builder.append(_generate, "    ");
    _builder.append("            ");
    _builder.newLineIfNotEmpty();
    {
      Manifestation _manifest_1 = library.getManifest();
      String _creationdate = _manifest_1.getCreationdate();
      boolean _notEquals = (!Objects.equal(_creationdate, null));
      if (_notEquals) {
        _builder.append("    ");
        _builder.append("<creationDate>");
        Manifestation _manifest_2 = library.getManifest();
        String _creationdate_1 = _manifest_2.getCreationdate();
        _builder.append(_creationdate_1, "    ");
        _builder.append("</creationDate>");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("    ");
        _builder.append("<creationDate>");
        Calendar _instance = Calendar.getInstance();
        int _get = _instance.get(Calendar.YEAR);
        _builder.append(_get, "    ");
        _builder.append("</creationDate>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      Manifestation _manifest_3 = library.getManifest();
      String _copyright = _manifest_3.getCopyright();
      boolean _notEquals_1 = (!Objects.equal(_copyright, null));
      if (_notEquals_1) {
        _builder.append("    ");
        _builder.append("<copyright>");
        Manifestation _manifest_4 = library.getManifest();
        String _copyright_1 = _manifest_4.getCopyright();
        _builder.append(_copyright_1, "    ");
        _builder.append("</copyright>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      Manifestation _manifest_5 = library.getManifest();
      String _license = _manifest_5.getLicense();
      boolean _notEquals_2 = (!Objects.equal(_license, null));
      if (_notEquals_2) {
        _builder.append("    ");
        _builder.append("<license>GPL 2.0</license>");
        _builder.newLine();
      }
    }
    {
      Manifestation _manifest_6 = library.getManifest();
      String _version = _manifest_6.getVersion();
      boolean _notEquals_3 = (!Objects.equal(_version, null));
      if (_notEquals_3) {
        _builder.append("    ");
        _builder.append("<version>");
        Manifestation _manifest_7 = library.getManifest();
        String _version_1 = _manifest_7.getVersion();
        _builder.append(_version_1, "    ");
        _builder.append("</version>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      Manifestation _manifest_8 = library.getManifest();
      String _description = _manifest_8.getDescription();
      boolean _notEquals_4 = (!Objects.equal(_description, null));
      if (_notEquals_4) {
        _builder.append("    ");
        _builder.append("<description>");
        Manifestation _manifest_9 = library.getManifest();
        String _description_1 = _manifest_9.getDescription();
        _builder.append(_description_1, "    ");
        _builder.append("</description>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<files>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<filename>index.html</filename>");
    _builder.newLine();
    {
      EList<de.thm.icampus.joomdd.ejsl.eJSL.Package> _packages = library.getPackages();
      for(final de.thm.icampus.joomdd.ejsl.eJSL.Package packageObj : _packages) {
        _builder.append("    \t");
        _builder.append("<folder>");
        String _name_1 = packageObj.getName();
        _builder.append(_name_1, "    \t");
        _builder.append("</folder>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<de.thm.icampus.joomdd.ejsl.eJSL.Class> _classes = library.getClasses();
      for(final de.thm.icampus.joomdd.ejsl.eJSL.Class classObj : _classes) {
        _builder.append("        ");
        _builder.append("<filename>");
        String _name_2 = classObj.getName();
        _builder.append(_name_2, "        ");
        _builder.append(".php</filename>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Entity> _entities = library.getEntities();
      for(final Entity entity : _entities) {
        _builder.append("        ");
        _builder.append("<filename>");
        String _name_3 = entity.getName();
        _builder.append(_name_3, "        ");
        _builder.append(".php</filename>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("    ");
    _builder.append("</files>");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<languages>");
    _builder.newLine();
    {
      EList<Language> _languages = library.getLanguages();
      for(final Language lang : _languages) {
        _builder.append("    \t");
        _builder.append("<language tag=\"");
        String _name_4 = lang.getName();
        _builder.append(_name_4, "    \t");
        _builder.append("\">language/");
        String _name_5 = lang.getName();
        _builder.append(_name_5, "    \t");
        _builder.append("/");
        String _name_6 = lang.getName();
        _builder.append(_name_6, "    \t");
        _builder.append(".");
        String _name_7 = this.getName();
        _builder.append(_name_7, "    \t");
        _builder.append(".ini</language>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("    ");
    _builder.append("</languages>");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("</extension>");
    _builder.newLine();
    return _builder;
  }
  
  @Pure
  public ExtendedLibrary getLibrary() {
    return this._library;
  }
  
  public void setLibrary(final ExtendedLibrary library) {
    this._library = library;
  }
}
