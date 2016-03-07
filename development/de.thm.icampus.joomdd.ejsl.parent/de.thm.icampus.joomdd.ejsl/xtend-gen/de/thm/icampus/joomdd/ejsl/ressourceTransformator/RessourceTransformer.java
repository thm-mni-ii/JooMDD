package de.thm.icampus.joomdd.ejsl.ressourceTransformator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension;
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference;
import de.thm.icampus.joomdd.ejsl.eJSL.DetailPageField;
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage;
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory;
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel;
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPart;
import de.thm.icampus.joomdd.ejsl.eJSL.Feature;
import de.thm.icampus.joomdd.ejsl.eJSL.HTMLTypes;
import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypeKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypes;
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypeKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes;
import de.thm.icampus.joomdd.ejsl.ressourceTransformator.JoomlaTranformator;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public class RessourceTransformer {
  private EJSLModel modelInstance;
  
  private CMSExtension cmsextension;
  
  private Feature featurs;
  
  public RessourceTransformer(final EJSLModel model) {
    this.modelInstance = model;
    EJSLPart _ejslPart = model.getEjslPart();
    this.cmsextension = ((CMSExtension) _ejslPart);
    Feature _feature = this.cmsextension.getFeature();
    this.featurs = _feature;
  }
  
  public void dotransformation() {
    this.transformEmptyHtmlAttribute();
    JoomlaTranformator jt = new JoomlaTranformator(this.modelInstance);
    jt.completeCMSExtension();
  }
  
  public void transformEmptyHtmlAttribute() {
    EList<Page> _pages = this.featurs.getPages();
    Iterable<DetailsPage> _filter = Iterables.<DetailsPage>filter(_pages, DetailsPage.class);
    for (final DetailsPage dp : _filter) {
      EList<DetailPageField> _editfields = dp.getEditfields();
      for (final DetailPageField field : _editfields) {
        HTMLTypes _htmltype = field.getHtmltype();
        boolean _equals = Objects.equal(_htmltype, null);
        if (_equals) {
          Attribute _attribute = field.getAttribute();
          SimpleHTMLTypes _parseAttributeType = this.parseAttributeType(_attribute);
          field.setHtmltype(_parseAttributeType);
        }
      }
    }
  }
  
  public SimpleHTMLTypes parseAttributeType(final Attribute attribute) {
    boolean _matched = false;
    if (!_matched) {
      if (attribute instanceof DatatypeReference) {
        _matched=true;
        SimpleHTMLTypes result = EJSLFactory.eINSTANCE.createSimpleHTMLTypes();
        SimpleHTMLTypeKinds _get = SimpleHTMLTypeKinds.get("Text_Field");
        result.setHtmltype(_get);
        return result;
      }
    }
    if (!_matched) {
      if (attribute instanceof StandardTypes) {
        _matched=true;
        StandardTypes temptyp = ((StandardTypes) attribute);
        SimpleHTMLTypes result = EJSLFactory.eINSTANCE.createSimpleHTMLTypes();
        String _parsingType = this.parsingType(temptyp);
        SimpleHTMLTypeKinds _get = SimpleHTMLTypeKinds.get(_parsingType);
        result.setHtmltype(_get);
        return result;
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
        value = "Integer";
        break;
      case "Boolean":
        value = "Yes_No_Buttons";
        break;
      case "Textarea":
        value = "Textarea";
        break;
      case "Textfield":
        value = "Text_Field";
        break;
      case "Time":
        value = "Datepicker";
        break;
      case "Date":
        value = "Datepicker";
        break;
      case "Datetime":
        value = "Datepicker";
        break;
      case "Link":
        value = "Text_Field";
        break;
      case "Image":
        value = "Imagepicker";
        break;
      case "File":
        value = "Filepicker";
        break;
      case "Label":
        value = "Text_Field_NE";
        break;
    }
    return value;
  }
}
