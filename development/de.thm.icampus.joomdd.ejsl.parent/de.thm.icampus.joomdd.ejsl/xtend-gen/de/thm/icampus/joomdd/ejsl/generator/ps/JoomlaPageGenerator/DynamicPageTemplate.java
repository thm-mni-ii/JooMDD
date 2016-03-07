package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.Author;
import de.thm.icampus.joomdd.ejsl.eJSL.BackendSection;
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.Type;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.AbstractPageGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Page</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see eJSLGenerator.GeneratorTemplatePackage#getDynamicPage()
 * @model
 * @generated
 */
@SuppressWarnings("all")
public abstract class DynamicPageTemplate extends AbstractPageGenerator {
  public CharSequence generateFileDoc(final DynamicPage page, final ExtendedComponent component, final boolean denied) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?php");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("* @version v0.0.1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("* @category Joomla component");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("* @subpackage com_");
    String _name = component.getName();
    String _slugify = Slug.slugify(_name);
    _builder.append(_slugify, "\t");
    _builder.append(".");
    String _xifexpression = null;
    EObject _eContainer = page.eContainer();
    if ((_eContainer instanceof BackendSection)) {
      _xifexpression = "admin";
    } else {
      _xifexpression = "site";
    }
    _builder.append(_xifexpression, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("* @name ");
    String _name_1 = component.getName();
    _builder.append(_name_1, "\t");
    _builder.append("View");
    _builder.newLineIfNotEmpty();
    {
      Manifestation _manifest = component.getManifest();
      EList<Author> _authors = _manifest.getAuthors();
      for(final Author author : _authors) {
        _builder.append("\t");
        _builder.append("* @author ");
        String _name_2 = author.getName();
        _builder.append(_name_2, "\t");
        _builder.append(", <");
        String _authoremail = author.getAuthoremail();
        _builder.append(_authoremail, "\t");
        _builder.append(">");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("* @copyright ");
    Manifestation _manifest_1 = component.getManifest();
    String _copyright = _manifest_1.getCopyright();
    _builder.append(_copyright, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("* @license ");
    Manifestation _manifest_2 = component.getManifest();
    String _license = _manifest_2.getLicense();
    _builder.append(_license, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("*/");
    _builder.newLine();
    {
      if (denied) {
        _builder.append("\t");
        _builder.append("defined(\'_JEXEC\') or die(\'Restricted access\');");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence xmlSiteTemplateContent(final String pagename, final ExtendedDynamicPage page, final ExtendedComponent component) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    _builder.newLine();
    _builder.append("<metadata>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<layout title=\"");
    String _name = component.getName();
    String _nameExtensionBind = Slug.nameExtensionBind("com", _name);
    String _upperCase = _nameExtensionBind.toUpperCase();
    _builder.append(_upperCase, "    ");
    _builder.append("_VIEW_");
    String _upperCase_1 = pagename.toUpperCase();
    _builder.append(_upperCase_1, "    ");
    _builder.append("_TITLE\" option=\"View\">");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("<message><![CDATA[");
    String _name_1 = component.getName();
    String _nameExtensionBind_1 = Slug.nameExtensionBind("com", _name_1);
    String _upperCase_2 = _nameExtensionBind_1.toUpperCase();
    _builder.append(_upperCase_2, "        ");
    _builder.append("_VIEW_");
    String _upperCase_3 = pagename.toUpperCase();
    _builder.append(_upperCase_3, "        ");
    _builder.append("_DESC]]></message>");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("</layout>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<fields");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("name=\"request\"");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("addfieldpath=\"administrator/components/");
    String _name_2 = component.getName();
    String _nameExtensionBind_2 = Slug.nameExtensionBind("com", _name_2);
    String _lowerCase = _nameExtensionBind_2.toLowerCase();
    _builder.append(_lowerCase, "        ");
    _builder.append("/models/fields\"");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append(">");
    _builder.newLine();
    _builder.append("   ");
    _builder.newLine();
    {
      DynamicPage _instance = page.getInstance();
      if ((_instance instanceof IndexPage)) {
        _builder.append("    ");
        _builder.append("<fieldset name=\"request\">");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("   ");
        _builder.append("<field name=\"template_layout\" type=\"list\"");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("   ");
        _builder.append("label=\"");
        String _name_3 = component.getName();
        String _nameExtensionBind_3 = Slug.nameExtensionBind("com", _name_3);
        String _upperCase_4 = _nameExtensionBind_3.toUpperCase();
        _builder.append(_upperCase_4, "       ");
        _builder.append("_TEMPLATE_LAYOUT\"");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t        ");
        _builder.append("description=\"");
        String _name_4 = component.getName();
        String _nameExtensionBind_4 = Slug.nameExtensionBind("com", _name_4);
        String _upperCase_5 = _nameExtensionBind_4.toUpperCase();
        _builder.append(_upperCase_5, "\t\t        ");
        _builder.append("_TEMPLATE_LAYOUT_DESC\"");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t        ");
        _builder.append("class=\"inputbox\"");
        _builder.newLine();
        _builder.append("\t\t        ");
        _builder.append("size=\"1\"");
        _builder.newLine();
        _builder.append("\t\t        ");
        _builder.append("default=\"1\">");
        _builder.newLine();
        _builder.append("\t\t        ");
        _builder.append("<option value=\"2\">JTEMPLATE_LAYOUT_TABLE</option>");
        _builder.newLine();
        _builder.append("\t\t        ");
        _builder.append("<option value=\"1\">JTEMPLATE_LAYOUT_LIST</option>");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("</field> ");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("</fieldset>");
        _builder.newLine();
      }
    }
    _builder.append("    \t");
    EList<ExtendedParameter> _extendedGlobalParametersListe = page.getExtendedGlobalParametersListe();
    CharSequence _generateParameter = this.generateParameter(_extendedGlobalParametersListe, component);
    _builder.append(_generateParameter, "    \t");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    EList<ExtendedParameter> _extendedLocalParametersListe = page.getExtendedLocalParametersListe();
    CharSequence _generateParameter_1 = this.generateParameter(_extendedLocalParametersListe, component);
    _builder.append(_generateParameter_1, "        ");
    _builder.newLineIfNotEmpty();
    {
      EList<ExtendedParameterGroup> _extendedParametersGroupsListe = page.getExtendedParametersGroupsListe();
      for(final ExtendedParameterGroup e : _extendedParametersGroupsListe) {
        _builder.append("        ");
        _builder.append("<fieldset name=\"");
        String _name_5 = e.getName();
        String _lowerCase_1 = _name_5.toLowerCase();
        _builder.append(_lowerCase_1, "        ");
        _builder.append("\"  label=\"");
        String _name_6 = component.getName();
        String _nameExtensionBind_5 = Slug.nameExtensionBind("com", _name_6);
        String _upperCase_6 = _nameExtensionBind_5.toUpperCase();
        _builder.append(_upperCase_6, "        ");
        _builder.append("_FIELDSET_");
        String _name_7 = page.getName();
        String _upperCase_7 = _name_7.toUpperCase();
        _builder.append(_upperCase_7, "        ");
        _builder.append("_");
        String _name_8 = e.getName();
        String _upperCase_8 = _name_8.toUpperCase();
        _builder.append(_upperCase_8, "        ");
        _builder.append("\" ");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        EList<ExtendedParameter> _extendedParameterList = e.getExtendedParameterList();
        CharSequence _generateParameter_2 = this.generateParameter(_extendedParameterList, component);
        _builder.append(_generateParameter_2, "        ");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        EList<ExtendedParameter> _extendedParameterList_1 = e.getExtendedParameterList();
        CharSequence _generateParameter_3 = this.generateParameter(_extendedParameterList_1, component);
        _builder.append(_generateParameter_3, "        ");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("</fieldset>");
        _builder.newLine();
      }
    }
    _builder.append("       ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("</fields>");
    _builder.newLine();
    _builder.append("</metadata>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateTemplate(final ExtendedDynamicPage page, final ExtendedComponent component) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  public CharSequence generateParameter(final EList<ExtendedParameter> listParams, final ExtendedComponent component) {
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
        Type _dtype = param.getDtype();
        String _typeName = Slug.getTypeName(_dtype);
        _builder.append(_typeName, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("label=\"");
        String _label = param.getLabel();
        String _generateKeysName = Slug.generateKeysName(component, _label);
        _builder.append(_generateKeysName, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("description=\"");
        String _descripton = param.getDescripton();
        String _generateKeysName_1 = Slug.generateKeysName(component, _descripton);
        _builder.append(_generateKeysName_1, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("/>");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence xmlAdminFields(final ExtendedDynamicPage page, final ExtendedComponent component, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    _builder.newLine();
    _builder.append("<form>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<fieldset>");
    _builder.newLine();
    _builder.append("\t  ");
    _builder.append("<field name=\"id\" type=\"text\" default=\"0\" label=\"");
    String _name = component.getName();
    String _nameExtensionBind = Slug.nameExtensionBind("com", _name);
    String _upperCase = _nameExtensionBind.toUpperCase();
    _builder.append(_upperCase, "\t  ");
    _builder.append("_FORM_LBL_NONE_ID\"");
    _builder.newLineIfNotEmpty();
    _builder.append("readonly=\"true\" class=\"readonly\"");
    _builder.newLine();
    _builder.append("description=\"JGLOBAL_FIELD_ID_DESC\" /> ");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<field name=\"created_by\" type=\"hidden\" default=\"\" ");
    _builder.newLine();
    _builder.append("label=\"");
    String _name_1 = component.getName();
    String _nameExtensionBind_1 = Slug.nameExtensionBind("com", _name_1);
    String _upperCase_1 = _nameExtensionBind_1.toUpperCase();
    _builder.append(_upperCase_1, "");
    _builder.append("_FORM_LBL_NONE_CREATED_BY\"");
    _builder.newLineIfNotEmpty();
    _builder.append("description=\"");
    String _name_2 = component.getName();
    String _nameExtensionBind_2 = Slug.nameExtensionBind("com", _name_2);
    String _upperCase_2 = _nameExtensionBind_2.toUpperCase();
    _builder.append(_upperCase_2, "");
    _builder.append("_FORM_LBL_NONE_CREATED_BY\"  /> ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<ExtendedEntity> _extendedEntityList = page.getExtendedEntityList();
      for(final ExtendedEntity e : _extendedEntityList) {
        {
          EList<ExtendedAttribute> _extendedAttributeList = e.getExtendedAttributeList();
          for(final ExtendedAttribute attr : _extendedAttributeList) {
            _builder.append("\t");
            _builder.append("<field name=\"");
            String _name_3 = attr.getName();
            String _lowerCase = _name_3.toLowerCase();
            _builder.append(_lowerCase, "\t");
            _builder.append("\" id=\"");
            String _name_4 = attr.getName();
            String _lowerCase_1 = _name_4.toLowerCase();
            _builder.append(_lowerCase_1, "\t");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t\t");
            CharSequence _htmlTypeOfAttribute = this.getHtmlTypeOfAttribute(page, attr, e, component);
            _builder.append(_htmlTypeOfAttribute, "\t\t\t\t\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("label=\"");
            String _name_5 = component.getName();
            String _nameExtensionBind_3 = Slug.nameExtensionBind("com", _name_5);
            String _upperCase_3 = _nameExtensionBind_3.toUpperCase();
            _builder.append(_upperCase_3, "\t");
            _builder.append("_FORM_LBL_");
            String _name_6 = e.getName();
            String _upperCase_4 = _name_6.toUpperCase();
            _builder.append(_upperCase_4, "\t");
            _builder.append("_");
            String _name_7 = attr.getName();
            String _upperCase_5 = _name_7.toUpperCase();
            _builder.append(_upperCase_5, "\t");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("/> ");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("\t   ");
    _builder.append("<field name=\"state\" type=\"list\"");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("label=\"");
    String _name_8 = component.getName();
    String _nameExtensionBind_4 = Slug.nameExtensionBind("com", _name_8);
    String _upperCase_6 = _nameExtensionBind_4.toUpperCase();
    _builder.append(_upperCase_6, "\t        ");
    _builder.append("_JSTATUS\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t        ");
    _builder.append("description=\"");
    String _name_9 = component.getName();
    String _nameExtensionBind_5 = Slug.nameExtensionBind("com", _name_9);
    String _upperCase_7 = _nameExtensionBind_5.toUpperCase();
    _builder.append(_upperCase_7, "\t        ");
    _builder.append("_JFIELD_PUBLISHED_DESC\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t        ");
    _builder.append("class=\"inputbox\"");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("size=\"1\"");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("default=\"1\">");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("<option value=\"1\">JPUBLISHED</option>");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("<option value=\"0\">JUNPUBLISHED</option>");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("<option value=\"2\">JARCHIVED</option>");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("<option value=\"-2\">JTRASHED</option>");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("</field> ");
    _builder.newLine();
    _builder.append("\t\t\t         ");
    _builder.append("<field name=\"published\" type=\"hidden\" filter=\"unset\" />");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<field name=\"checked_out\" type=\"hidden\" filter=\"unset\" />");
    _builder.newLine();
    _builder.append("\t\t\t        ");
    _builder.append("<field name=\"checked_out_time\" type=\"hidden\" filter=\"unset\" /> ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</fieldset> ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("<fieldset name=\"accesscontrol\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<field name=\"asset_id\" type=\"hidden\" filter=\"unset\" />");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<field name=\"rules\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("type=\"rules\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("label=\"JFIELD_RULES_LABEL\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("translate_label=\"false\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("filter=\"rules\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("validate=\"rules\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("class=\"inputbox\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("component=\"");
    String _name_10 = component.getName();
    String _nameExtensionBind_6 = Slug.nameExtensionBind("com", _name_10);
    String _lowerCase_2 = _nameExtensionBind_6.toLowerCase();
    _builder.append(_lowerCase_2, "\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("section=\"");
    String _name_11 = page.getName();
    String _lowerCase_3 = _name_11.toLowerCase();
    _builder.append(_lowerCase_3, "\t");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("/>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("</fieldset>");
    _builder.newLine();
    _builder.append("</form>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence getHtmlTypeOfAttribute(final ExtendedDynamicPage dynP, final ExtendedAttribute attr, final ExtendedEntity en, final ExtendedComponent com) {
    StringBuffer buff = new StringBuffer();
    EList<ExtendedReference> _extendedReference = en.getExtendedReference();
    for (final ExtendedReference ref : _extendedReference) {
      EList<ExtendedAttribute> _extendedAttribute = ref.getExtendedAttribute();
      ExtendedAttribute _get = _extendedAttribute.get(0);
      String _name = _get.getName();
      String _name_1 = attr.getName();
      boolean _equalsIgnoreCase = _name.equalsIgnoreCase(_name_1);
      if (_equalsIgnoreCase) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("type =\"");
        String _name_2 = en.getName();
        String _plus = (_name_2 + "to");
        Entity _entity = ref.getEntity();
        String _name_3 = _entity.getName();
        String _plus_1 = (_plus + _name_3);
        _builder.append(_plus_1, "");
        EList<ExtendedReference> _extendedReference_1 = en.getExtendedReference();
        int _indexOf = _extendedReference_1.indexOf(ref);
        _builder.append(_indexOf, "");
        _builder.append("\"");
        buff.append(_builder);
        return buff.toString();
      }
    }
    EList<ExtendedDetailPageField> _extendedEditedFieldsList = dynP.getExtendedEditedFieldsList();
    boolean _isEmpty = _extendedEditedFieldsList.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      EList<ExtendedDetailPageField> _extendedEditedFieldsList_1 = dynP.getExtendedEditedFieldsList();
      for (final ExtendedDetailPageField field : _extendedEditedFieldsList_1) {
        ExtendedAttribute _extendedAttribute_1 = field.getExtendedAttribute();
        String _name_4 = _extendedAttribute_1.getName();
        String _name_5 = attr.getName();
        boolean _equalsIgnoreCase_1 = _name_4.equalsIgnoreCase(_name_5);
        if (_equalsIgnoreCase_1) {
          String _type = field.getType();
          ExtendedAttribute _extendedAttribute_2 = field.getExtendedAttribute();
          return Slug.getTypeName(_type, _extendedAttribute_2);
        }
      }
    }
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("type =\"hidden\"");
    buff.append(_builder_1);
    return buff.toString();
  }
}
