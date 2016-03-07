package de.thm.icampus.joomdd.ejsl.generator.pi.util.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.Datatype;
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference;
import de.thm.icampus.joomdd.ejsl.eJSL.Parameter;
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypeKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes;
import de.thm.icampus.joomdd.ejsl.eJSL.Type;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.ParameterImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil;

@SuppressWarnings("all")
public class ExtendedParameterImpl extends ParameterImpl implements ExtendedParameter {
  private Parameter instance;
  
  private String type;
  
  public ExtendedParameterImpl(final Parameter para) {
    String _name = para.getName();
    String _slugify = PlattformIUtil.slugify(_name);
    this.name = _slugify;
    String _defaultvalue = para.getDefaultvalue();
    this.defaultvalue = _defaultvalue;
    String _label = para.getLabel();
    this.label = _label;
    String _descripton = para.getDescripton();
    this.descripton = _descripton;
    Type _dtype = para.getDtype();
    this.dtype = _dtype;
    String _parseType = this.parseType();
    this.type = _parseType;
    this.instance = para;
  }
  
  public String parseType() {
    final Type _switchValue = this.dtype;
    boolean _matched = false;
    if (!_matched) {
      if (_switchValue instanceof DatatypeReference) {
        _matched=true;
        DatatypeReference temptyp = ((DatatypeReference) this.dtype);
        Datatype _type = temptyp.getType();
        return _type.getName();
      }
    }
    if (!_matched) {
      if (_switchValue instanceof StandardTypes) {
        _matched=true;
        StandardTypes temptyp = ((StandardTypes) this.dtype);
        return this.parsingType(temptyp);
      }
    }
    return null;
  }
  
  public String parsingType(final StandardTypes types) {
    String _xblockexpression = null;
    {
      String value = "";
      String _switchResult = null;
      StandardTypeKinds _type = types.getType();
      String _name = _type.getName();
      switch (_name) {
        case "Integer":
          _switchResult = value = "Integer";
          break;
        case "Boolean":
          _switchResult = value = "Yes_No_Buttons";
          break;
        case "Textarea":
          _switchResult = value = "Textarea";
          break;
        case "Textfield":
          _switchResult = value = "Text_Field";
          break;
        case "Time":
          _switchResult = value = "Datepicker";
          break;
        case "Date":
          _switchResult = value = "Datepicker";
          break;
        case "Datetime":
          _switchResult = value = "Datepicker";
          break;
        case "Link":
          _switchResult = value = "Text_Field";
          break;
        case "Image":
          _switchResult = value = "Imagepicker";
          break;
        case "File":
          _switchResult = value = "Filepicker";
          break;
        case "Label":
          _switchResult = value = "Text_Field_NE";
          break;
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
  
  @Override
  public String generatorType() {
    return this.type;
  }
  
  @Override
  public Parameter getInstance() {
    return this.instance;
  }
}
