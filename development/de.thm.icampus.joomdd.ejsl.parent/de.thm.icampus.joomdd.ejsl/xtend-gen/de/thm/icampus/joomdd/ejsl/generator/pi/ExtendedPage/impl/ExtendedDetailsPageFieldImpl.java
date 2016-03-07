package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.ComplexHTMLTypeKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.ComplexHTMLTypes;
import de.thm.icampus.joomdd.ejsl.eJSL.DetailPageField;
import de.thm.icampus.joomdd.ejsl.eJSL.HTMLTypes;
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair;
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypeKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypes;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.DetailPageFieldImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public class ExtendedDetailsPageFieldImpl extends DetailPageFieldImpl implements ExtendedDetailPageField {
  private String type;
  
  private ExtendedAttribute extendedAttribute;
  
  private DetailPageField instance;
  
  public ExtendedDetailsPageFieldImpl(final DetailPageField field) {
    this.instance = field;
    Attribute _attribute = field.getAttribute();
    this.attribute = _attribute;
    HTMLTypes _htmltype = field.getHtmltype();
    this.htmltype = _htmltype;
    ExtendedAttribute _transformAttribute = PlattformIUtil.transformAttribute(this.attribute);
    this.extendedAttribute = _transformAttribute;
    String _parseType = this.parseType();
    this.type = _parseType;
  }
  
  public String parseType() {
    HTMLTypes _htmltype = this.instance.getHtmltype();
    boolean _matched = false;
    if (!_matched) {
      if (_htmltype instanceof SimpleHTMLTypes) {
        _matched=true;
        HTMLTypes _htmltype_1 = this.instance.getHtmltype();
        SimpleHTMLTypes type = ((SimpleHTMLTypes) _htmltype_1);
        SimpleHTMLTypeKinds _htmltype_2 = type.getHtmltype();
        return _htmltype_2.getName();
      }
    }
    if (!_matched) {
      if (_htmltype instanceof ComplexHTMLTypes) {
        _matched=true;
        HTMLTypes _htmltype_1 = this.instance.getHtmltype();
        ComplexHTMLTypes type = ((ComplexHTMLTypes) _htmltype_1);
        ComplexHTMLTypeKinds _htmltype_2 = type.getHtmltype();
        return _htmltype_2.getName();
      }
    }
    return null;
  }
  
  @Override
  public String getType() {
    return this.type;
  }
  
  @Override
  public ExtendedAttribute getExtendedAttribute() {
    return this.extendedAttribute;
  }
  
  @Override
  public EList<KeyValuePair> getExtrasKeyValue() {
    if ((this.htmltype instanceof ComplexHTMLTypes)) {
      ComplexHTMLTypes cpHtml = ((ComplexHTMLTypes) this.htmltype);
      return cpHtml.getKeyvaluepairs();
    }
    return null;
  }
}
