package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.Datatype;
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypeKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes;
import de.thm.icampus.joomdd.ejsl.eJSL.Type;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.AttributeImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ExtendedAttributeImpl extends AttributeImpl implements ExtendedAttribute {
  private Entity entity;
  
  private String genType;
  
  private Attribute instance;
  
  private String htmlType;
  
  public ExtendedAttributeImpl(final Attribute attr) {
    Type _type = attr.getType();
    this.type = _type;
    String _name = attr.getName();
    String _slugify = PlattformIUtil.slugify(_name);
    String _lowerCase = _slugify.toLowerCase();
    this.name = _lowerCase;
    boolean _isIsunique = attr.isIsunique();
    this.isunique = _isIsunique;
    Attribute _withattribute = attr.getWithattribute();
    this.withattribute = _withattribute;
    EObject _eContainer = attr.eContainer();
    this.entity = ((Entity) _eContainer);
    String _generatorType = this.generatorType();
    this.genType = _generatorType;
    String _generatorTypeHtmlType = this.generatorTypeHtmlType();
    this.htmlType = _generatorTypeHtmlType;
    this.instance = attr;
  }
  
  public String generatorTypeHtmlType() {
    final Type type = this.type;
    boolean _matched = false;
    if (!_matched) {
      if (type instanceof DatatypeReference) {
        _matched=true;
        DatatypeReference temptyp = ((DatatypeReference) this.type);
        Datatype _type = temptyp.getType();
        return _type.getName();
      }
    }
    if (!_matched) {
      if (type instanceof StandardTypes) {
        _matched=true;
        StandardTypes temptyp = ((StandardTypes) this.type);
        StandardTypeKinds _type = temptyp.getType();
        return _type.getName();
      }
    }
    return null;
  }
  
  @Override
  public String generatorType() {
    final Type type = this.type;
    boolean _matched = false;
    if (!_matched) {
      if (type instanceof DatatypeReference) {
        _matched=true;
        DatatypeReference temptyp = ((DatatypeReference) this.type);
        Datatype _type = temptyp.getType();
        return _type.getName();
      }
    }
    if (!_matched) {
      if (type instanceof StandardTypes) {
        _matched=true;
        StandardTypes temptyp = ((StandardTypes) this.type);
        return this.parsingType(temptyp);
      }
    }
    return null;
  }
  
  public String parsingType(final StandardTypes eJSlType) {
    String value = "";
    StandardTypeKinds _type = eJSlType.getType();
    String _name = _type.getName();
    switch (_name) {
      case "Integer":
        value = "int(11) ";
        break;
      case "Boolean":
        value = "tinyint(1) ";
        String _default = eJSlType.getDefault();
        boolean _equals = Objects.equal(_default, null);
        if (_equals) {
          eJSlType.setDefault("0");
        }
        break;
      case "Textarea":
        value = "text ";
        break;
      case "Textfield":
        value = "varchar(255) ";
        break;
      case "Time":
        value = "time ";
        break;
      case "Date":
        value = "date ";
        break;
      case "Datetime":
        value = "datetime ";
        String _default_1 = eJSlType.getDefault();
        boolean _equals_1 = Objects.equal(_default_1, null);
        if (_equals_1) {
          eJSlType.setDefault("0000-00-00 00:00:00");
        }
        break;
      case "Link":
        value = "text ";
        break;
      case "Image":
        value = "text ";
        break;
      case "File":
        value = "text ";
        break;
      case "Label":
        value = "varchar(255) ";
        break;
      default:
        value = "int(11)";
        break;
    }
    String result = value;
    boolean _isNotnull = eJSlType.isNotnull();
    if (_isNotnull) {
      result = (result + "NOT NULL ");
    }
    String _default_2 = eJSlType.getDefault();
    boolean _notEquals = (!Objects.equal(_default_2, null));
    if (_notEquals) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("\"");
      String _default_3 = eJSlType.getDefault();
      String _string = _default_3.toString();
      _builder.append(_string, "");
      _builder.append("\"");
      String _plus = ((result + "DEFAULT ") + _builder);
      result = _plus;
    }
    boolean _isAutoincrement = eJSlType.isAutoincrement();
    if (_isAutoincrement) {
      result = (result + " AUTO_INCREMENT ");
    }
    return result;
  }
  
  @Override
  public Entity getEntity() {
    return this.entity;
  }
  
  @Override
  public Attribute getInstance() {
    return this.instance;
  }
  
  @Override
  public String htmlType() {
    return this.htmlType;
  }
}
