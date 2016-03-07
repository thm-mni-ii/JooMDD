package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.Author;
import de.thm.icampus.joomdd.ejsl.eJSL.BackendSection;
import de.thm.icampus.joomdd.ejsl.eJSL.Component;
import de.thm.icampus.joomdd.ejsl.eJSL.ContextLink;
import de.thm.icampus.joomdd.ejsl.eJSL.Datatype;
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference;
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage;
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.ExternalLink;
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage;
import de.thm.icampus.joomdd.ejsl.eJSL.InternalLink;
import de.thm.icampus.joomdd.ejsl.eJSL.Link;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.MethodParameter;
import de.thm.icampus.joomdd.ejsl.eJSL.Module;
import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.eJSL.Reference;
import de.thm.icampus.joomdd.ejsl.eJSL.Section;
import de.thm.icampus.joomdd.ejsl.eJSL.SectionKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypeKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes;
import de.thm.icampus.joomdd.ejsl.eJSL.Type;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.LinkGeneratorClient;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Slug</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see eJSLGenerator.GeneratorTemplatePackage#getSlug()
 * @model
 * @generated
 */
@SuppressWarnings("all")
public class Slug {
  /**
   * Slugify an String and replace all german special characters
   * e.g.: This is a String => this_is_a_string
   */
  public static Calendar cal = new GregorianCalendar();
  
  public static String slugify(final String str) {
    String _xblockexpression = null;
    {
      String res = str;
      String _replaceAll = res.replaceAll("[^\\pL\\d]+", "_");
      res = _replaceAll;
      String _replaceAll_1 = res.replaceAll("ä", "ae");
      res = _replaceAll_1;
      String _replaceAll_2 = res.replaceAll("ö", "ou");
      res = _replaceAll_2;
      String _replaceAll_3 = res.replaceAll("ü", "ue");
      res = _replaceAll_3;
      String _replaceAll_4 = res.replaceAll("ß", "ss");
      res = _replaceAll_4;
      String _replaceAll_5 = res.replaceAll("[^-\\w]+", "");
      res = _replaceAll_5;
      String _lowerCase = res.toLowerCase();
      res = _lowerCase;
      char _charAt = "_".charAt(0);
      _xblockexpression = Slug.trim(res, _charAt);
    }
    return _xblockexpression;
  }
  
  public static String trim(final String str, final char c) {
    String _xblockexpression = null;
    {
      int a = 0;
      int z = str.length();
      while (((a < z) && (str.charAt(a) == c))) {
        a = (a + 1);
      }
      do {
        z = (z - 1);
      } while(((z > a) && (str.charAt(z) == c)));
      _xblockexpression = str.substring(a, (z + 1));
    }
    return _xblockexpression;
  }
  
  /**
   * Takes a string as 'com_social_network' and rebuilds it clean as into 'SocialNetwork.
   * Needed for class names.
   */
  public static String cleanExtensionName(final String name) {
    final String[] split = name.split("_");
    StringBuffer res = new StringBuffer();
    int i = 0;
    while (((i = (i + 1)) < ((List<String>)Conversions.doWrapArray(split)).size())) {
      String _get = split[i];
      String _lowerCase = _get.toLowerCase();
      String _firstUpper = StringExtensions.toFirstUpper(_lowerCase);
      res.append(_firstUpper);
    }
    return res.toString();
  }
  
  public static String getTypeName(final String type) {
    String result = "";
    switch (type) {
      case "Integer":
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("type=\"number\" min=\"0\"  ");
        result = _builder.toString();
        break;
      case "Yes_No_Buttons":
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(" ");
        _builder_1.append("type=\"number\" min=\"0\" max=\"1\" ");
        result = _builder_1.toString();
        break;
      case "Textarea":
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("type=\"textarea\" rows=\"10\" cols=\"5\" ");
        result = _builder_2.toString();
        break;
      case "Text_Field":
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append("type=\"text\" ");
        result = _builder_3.toString();
        break;
      case "Datepicker":
        StringConcatenation _builder_4 = new StringConcatenation();
        _builder_4.append("type=\"datetime\" ");
        result = _builder_4.toString();
        break;
      case "Imagepicker":
        StringConcatenation _builder_5 = new StringConcatenation();
        _builder_5.append("type=\"imagelist\" ");
        result = _builder_5.toString();
        break;
      case "Filepicker":
        StringConcatenation _builder_6 = new StringConcatenation();
        _builder_6.append("type=\"filelist\" directory=\"administrator\" ");
        result = _builder_6.toString();
        break;
      case "Text_Field_NE":
        StringConcatenation _builder_7 = new StringConcatenation();
        _builder_7.append("type=\"text\" ");
        result = _builder_7.toString();
        break;
      case "Editor":
        StringConcatenation _builder_8 = new StringConcatenation();
        _builder_8.append("type=\"editor\" ");
        result = _builder_8.toString();
        break;
      case "Multiselect":
        StringConcatenation _builder_9 = new StringConcatenation();
        _builder_9.append("type=\"text\" ");
        result = _builder_9.toString();
        break;
      case "Checkbox":
        StringConcatenation _builder_10 = new StringConcatenation();
        _builder_10.append("type=\"text\" ");
        result = _builder_10.toString();
        break;
      case "Radiobutton":
        StringConcatenation _builder_11 = new StringConcatenation();
        _builder_11.append("type=\"text\" ");
        result = _builder_11.toString();
        break;
      case "hidden":
        StringConcatenation _builder_12 = new StringConcatenation();
        _builder_12.append("type=\"hidden\" ");
        result = _builder_12.toString();
        break;
    }
    return result;
  }
  
  public static String getTypeName(final String type, final ExtendedAttribute attr) {
    String myType = Slug.getTypeName(type);
    return myType;
  }
  
  public static String getTypeName(final Type typ) {
    String result = "";
    boolean _matched = false;
    if (!_matched) {
      if (typ instanceof DatatypeReference) {
        _matched=true;
        DatatypeReference temptyp = ((DatatypeReference) typ);
        Datatype _type = temptyp.getType();
        String _name = _type.getName();
        result = _name;
      }
    }
    if (!_matched) {
      if (typ instanceof StandardTypes) {
        _matched=true;
        StandardTypes temptyp = ((StandardTypes) typ);
        StandardTypeKinds _type = temptyp.getType();
        String _name = _type.getName();
        String _typeName = Slug.getTypeName(_name);
        result = _typeName;
      }
    }
    return result;
  }
  
  public static String getTypeName(final ExtendedParameter typ) {
    String _generatorType = typ.generatorType();
    return Slug.getTypeName(_generatorType);
  }
  
  /**
   * Takes the name of an Extension and Prefix like (com ,name)and return com_name  .
   * Needed for class names.
   */
  public static String nameExtensionBind(final String prefix, final String name) {
    return ((prefix + "_") + name);
  }
  
  public static BackendSection getBackendSectionViews(final Component com) {
    EList<Section> _sections = com.getSections();
    for (final Section sec : _sections) {
      boolean _matched = false;
      if (!_matched) {
        if (sec instanceof BackendSection) {
          _matched=true;
          return ((BackendSection)sec);
        }
      }
    }
    return null;
  }
  
  public static CharSequence generateEntytiesInputAttribute(final EList<ExtendedDetailPageField> fields, final ExtendedEntity entity) {
    StringBuffer buff = new StringBuffer();
    ArrayList<String> notShow = CollectionLiterals.<String>newArrayList("id", "state", "created_by", "asset_id", "ordering", "checked_out_time", "checked_out", "published", "params");
    for (final ExtendedDetailPageField fielditem : fields) {
      ExtendedAttribute _extendedAttribute = fielditem.getExtendedAttribute();
      String _name = _extendedAttribute.getName();
      boolean _contains = notShow.contains(_name);
      boolean _not = (!_contains);
      if (_not) {
        ExtendedAttribute _extendedAttribute_1 = fielditem.getExtendedAttribute();
        CharSequence _inputFeldTemplate = Slug.inputFeldTemplate(_extendedAttribute_1);
        buff.append(_inputFeldTemplate);
        ExtendedAttribute _extendedAttribute_2 = fielditem.getExtendedAttribute();
        String _name_1 = _extendedAttribute_2.getName();
        notShow.add(_name_1);
      }
    }
    EList<ExtendedAttribute> _extendedAttributeList = entity.getExtendedAttributeList();
    for (final ExtendedAttribute attr : _extendedAttributeList) {
      String _name_2 = attr.getName();
      boolean _contains_1 = notShow.contains(_name_2);
      boolean _not_1 = (!_contains_1);
      if (_not_1) {
        CharSequence _inputHiddenFeldTemplate = Slug.inputHiddenFeldTemplate(attr);
        buff.append(_inputHiddenFeldTemplate);
      }
    }
    return buff.toString();
  }
  
  public static CharSequence inputHiddenFeldTemplate(final ExtendedAttribute attr) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<div class=\"controls\"><?php echo $this->form->getInput(\'");
    String _name = attr.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("\'); ?></div>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public static CharSequence inputFeldTemplate(final ExtendedAttribute attr) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<div class=\"control-group\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<div class=\"control-label\"><?php echo $this->form->getLabel(\'");
    String _name = attr.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "\t");
    _builder.append("\'); ?></div>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<div class=\"controls\"><?php echo $this->form->getInput(\'");
    String _name_1 = attr.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    _builder.append(_lowerCase_1, "\t");
    _builder.append("\'); ?></div>");
    _builder.newLineIfNotEmpty();
    _builder.append("</div>");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence generateFileDoc(final Component component, final boolean denied) {
    StringConcatenation _builder = new StringConcatenation();
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
    _builder.append("* @name ");
    String _name = component.getName();
    _builder.append(_name, "\t");
    _builder.append("View");
    _builder.newLineIfNotEmpty();
    {
      Manifestation _manifest = component.getManifest();
      EList<Author> _authors = _manifest.getAuthors();
      for(final Author author : _authors) {
        _builder.append("\t");
        _builder.append("* @author ");
        String _name_1 = author.getName();
        _builder.append(_name_1, "\t");
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
  
  public static String generateKeysName(final Component com, final String name) {
    boolean _notEquals = (!Objects.equal(name, null));
    if (_notEquals) {
      String _name = com.getName();
      String _upperCase = _name.toUpperCase();
      String _plus = ("COM_" + _upperCase);
      String _plus_1 = (_plus + "_FIELD_");
      String _slugify = Slug.slugify(name);
      String _upperCase_1 = _slugify.toUpperCase();
      return (_plus_1 + _upperCase_1);
    }
    return null;
  }
  
  public static String generateKeysNamePage(final Component com, final Page page, final String name) {
    String _name = com.getName();
    String _upperCase = _name.toUpperCase();
    String _plus = ("COM_" + _upperCase);
    String _plus_1 = (_plus + "_FIELD_");
    String _name_1 = page.getName();
    String _slugify = Slug.slugify(_name_1);
    String _upperCase_1 = _slugify.toUpperCase();
    String _plus_2 = (_plus_1 + _upperCase_1);
    String _plus_3 = (_plus_2 + "_");
    String _slugify_1 = Slug.slugify(name);
    String _upperCase_2 = _slugify_1.toUpperCase();
    return (_plus_3 + _upperCase_2);
  }
  
  public static DetailsPage getPageForDetails(final ExtendedDynamicPage inpage, final ExtendedComponent com) {
    EList<Link> _links = inpage.getLinks();
    for (final Link lk : _links) {
      boolean _matched = false;
      if (!_matched) {
        if (lk instanceof ContextLink) {
          _matched=true;
          InternalLink lkin = ((InternalLink) lk);
          Page _target = lkin.getTarget();
          if ((_target instanceof DetailsPage)) {
            Page _target_1 = lkin.getTarget();
            return ((DetailsPage) _target_1);
          }
        }
      }
      if (!_matched) {
        if (lk instanceof InternalLink) {
          _matched=true;
          InternalLink lkin = ((InternalLink) lk);
          Page _target = lkin.getTarget();
          if ((_target instanceof DetailsPage)) {
            Page _target_1 = lkin.getTarget();
            return ((DetailsPage) _target_1);
          }
        }
      }
    }
    return null;
  }
  
  public static IndexPage getPageForAll(final ExtendedDynamicPage inpage, final ExtendedComponent com) {
    EList<Link> _links = inpage.getLinks();
    for (final Link lk : _links) {
      boolean _matched = false;
      if (!_matched) {
        if (lk instanceof ContextLink) {
          _matched=true;
          InternalLink lkin = ((InternalLink) lk);
          Page _target = lkin.getTarget();
          if ((_target instanceof IndexPage)) {
            Page _target_1 = lkin.getTarget();
            return ((IndexPage) _target_1);
          }
        }
      }
      if (!_matched) {
        if (lk instanceof InternalLink) {
          _matched=true;
          InternalLink lkin = ((InternalLink) lk);
          Page _target = lkin.getTarget();
          if ((_target instanceof IndexPage)) {
            Page _target_1 = lkin.getTarget();
            return ((IndexPage) _target_1);
          }
        }
      }
    }
    return null;
  }
  
  public static CharSequence generateFileDoc(final Module module, final boolean denied) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @version     CVS: 1.0");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @category    Joomla module");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @package     Packagename");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @subpackage  Subpackagename");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @name        ");
    String _name = module.getName();
    _builder.append(_name, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("* @description ");
    _builder.newLine();
    {
      Manifestation _manifest = module.getManifest();
      EList<Author> _authors = _manifest.getAuthors();
      for(final Author author : _authors) {
        _builder.append(" ");
        _builder.append("* @author      ");
        String _name_1 = author.getName();
        _builder.append(_name_1, " ");
        _builder.append(", <");
        String _authoremail = author.getAuthoremail();
        _builder.append(_authoremail, " ");
        _builder.append(">");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append(" ");
    _builder.append("* @copyright   ");
    int _get = Slug.cal.get(Calendar.YEAR);
    _builder.append(_get, " ");
    _builder.append("  ");
    Manifestation _manifest_1 = module.getManifest();
    String _copyright = _manifest_1.getCopyright();
    _builder.append(_copyright, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("* @license     ");
    Manifestation _manifest_2 = module.getManifest();
    String _license = _manifest_2.getLicense();
    _builder.append(_license, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("* @link        www.link.com");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    return _builder;
  }
  
  public static String getSectioName(final Section reference) {
    if ((reference instanceof BackendSection)) {
      return "admin";
    }
    return "";
  }
  
  public static String getSectioName(final SectionKinds reference) {
    String _name = reference.getName();
    boolean _equalsIgnoreCase = _name.equalsIgnoreCase("backend");
    if (_equalsIgnoreCase) {
      return "admin";
    }
    return "";
  }
  
  public static CharSequence databaseName(final String componentName, final String entityName) {
    String _lowerCase = componentName.toLowerCase();
    String _plus = ("#__" + _lowerCase);
    String _plus_1 = (_plus + "_");
    String _lowerCase_1 = entityName.toLowerCase();
    return (_plus_1 + _lowerCase_1);
  }
  
  public static Boolean isAttributeLinked(final ExtendedAttribute attr, final DynamicPage page) {
    EList<Link> _links = page.getLinks();
    for (final Link ref : _links) {
      Attribute _linkedAttribute = ref.getLinkedAttribute();
      boolean _notEquals = (!Objects.equal(_linkedAttribute, null));
      if (_notEquals) {
        Attribute _linkedAttribute_1 = ref.getLinkedAttribute();
        String _name = _linkedAttribute_1.getName();
        String _name_1 = attr.getName();
        boolean _equalsIgnoreCase = _name.equalsIgnoreCase(_name_1);
        if (_equalsIgnoreCase) {
          return Boolean.valueOf(true);
        }
      }
    }
    return Boolean.valueOf(false);
  }
  
  public static CharSequence linkOfAttribut(final ExtendedAttribute attribute, final ExtendedDynamicPage page, final String compname, final String valuefeatures) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Link> _links = page.getLinks();
      for(final Link lk : _links) {
        {
          Attribute _linkedAttribute = lk.getLinkedAttribute();
          boolean _notEquals = (!Objects.equal(_linkedAttribute, null));
          if (_notEquals) {
            {
              Attribute _linkedAttribute_1 = lk.getLinkedAttribute();
              String _name = _linkedAttribute_1.getName();
              String _name_1 = attribute.getName();
              boolean _equalsIgnoreCase = _name.equalsIgnoreCase(_name_1);
              if (_equalsIgnoreCase) {
                _builder.newLine();
                CharSequence _switchResult = null;
                boolean _matched = false;
                if (!_matched) {
                  if (lk instanceof ExternalLink) {
                    _matched=true;
                    StringConcatenation _builder_1 = new StringConcatenation();
                    LinkGeneratorClient _linkGeneratorClient = new LinkGeneratorClient(lk, "", compname, valuefeatures);
                    CharSequence _generateLink = _linkGeneratorClient.generateLink();
                    _builder_1.append(_generateLink, "");
                    _switchResult = _builder_1;
                  }
                }
                if (!_matched) {
                  if (lk instanceof InternalLink) {
                    _matched=true;
                    CharSequence _xifexpression = null;
                    Page _target = ((InternalLink) lk).getTarget();
                    if ((_target instanceof DetailsPage)) {
                      CharSequence _xifexpression_1 = null;
                      DynamicPage _instance = page.getInstance();
                      EList<Entity> _entities = ((DynamicPage) _instance).getEntities();
                      Entity _get = _entities.get(0);
                      String _name_2 = _get.getName();
                      Page _target_1 = ((InternalLink)lk).getTarget();
                      EList<Entity> _entities_1 = ((DynamicPage) _target_1).getEntities();
                      Entity _get_1 = _entities_1.get(0);
                      String _name_3 = _get_1.getName();
                      boolean _equals = _name_2.equals(_name_3);
                      if (_equals) {
                        CharSequence _xifexpression_2 = null;
                        if ((!(lk instanceof ContextLink))) {
                          StringConcatenation _builder_1 = new StringConcatenation();
                          LinkGeneratorClient _linkGeneratorClient = new LinkGeneratorClient(lk, "", compname, valuefeatures);
                          CharSequence _generateLink = _linkGeneratorClient.generateLink();
                          _builder_1.append(_generateLink, "");
                          _builder_1.append(" . \'&id=\'.(int) $item->id ");
                          _xifexpression_2 = _builder_1;
                        } else {
                          StringConcatenation _builder_2 = new StringConcatenation();
                          LinkGeneratorClient _linkGeneratorClient_1 = new LinkGeneratorClient(lk, "", compname, valuefeatures);
                          CharSequence _generateLink_1 = _linkGeneratorClient_1.generateLink();
                          _builder_2.append(_generateLink_1, "");
                          _xifexpression_2 = _builder_2;
                        }
                        _xifexpression_1 = _xifexpression_2;
                      } else {
                        CharSequence _xblockexpression = null;
                        {
                          ExtendedAttribute idRef = Slug.getAttributeForForeignID(attribute, page);
                          CharSequence _xifexpression_3 = null;
                          boolean _notEquals_1 = (!Objects.equal(idRef, null));
                          if (_notEquals_1) {
                            StringConcatenation _builder_3 = new StringConcatenation();
                            LinkGeneratorClient _linkGeneratorClient_2 = new LinkGeneratorClient(lk, "", compname, valuefeatures);
                            CharSequence _generateLink_2 = _linkGeneratorClient_2.generateLink();
                            _builder_3.append(_generateLink_2, "");
                            _builder_3.append(" . \'&id=\'.(int) $item->");
                            String _name_4 = idRef.getName();
                            _builder_3.append(_name_4, "");
                            _xifexpression_3 = _builder_3;
                          } else {
                            StringConcatenation _builder_4 = new StringConcatenation();
                            LinkGeneratorClient _linkGeneratorClient_3 = new LinkGeneratorClient(lk, "", compname, valuefeatures);
                            CharSequence _generateLink_3 = _linkGeneratorClient_3.generateLink();
                            _builder_4.append(_generateLink_3, "");
                            _builder_4.append(" . \'&id=\'.(int) $this->getModel()->getIdOfReferenceItem(\"");
                            String _name_5 = ((InternalLink) lk).getName();
                            String _lowerCase = _name_5.toLowerCase();
                            _builder_4.append(_lowerCase, "");
                            _builder_4.append("\",$item->");
                            String _name_6 = attribute.getName();
                            String _lowerCase_1 = _name_6.toLowerCase();
                            _builder_4.append(_lowerCase_1, "");
                            _builder_4.append(")");
                            _xifexpression_3 = _builder_4;
                          }
                          _xblockexpression = _xifexpression_3;
                        }
                        _xifexpression_1 = _xblockexpression;
                      }
                      _xifexpression = _xifexpression_1;
                    } else {
                      StringConcatenation _builder_3 = new StringConcatenation();
                      LinkGeneratorClient _linkGeneratorClient_2 = new LinkGeneratorClient(lk, "", compname, valuefeatures);
                      CharSequence _generateLink_2 = _linkGeneratorClient_2.generateLink();
                      _builder_3.append(_generateLink_2, "");
                      _builder_3.append(" . \'&filter.search=\'. $item->");
                      String _name_4 = attribute.getName();
                      String _lowerCase = _name_4.toLowerCase();
                      _builder_3.append(_lowerCase, "");
                      _xifexpression = _builder_3;
                    }
                    _switchResult = _xifexpression;
                  }
                }
                _builder.append(_switchResult, "");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public static ExtendedAttribute getAttributeForForeignID(final ExtendedAttribute attr, final ExtendedDynamicPage dynPage) {
    EList<ExtendedEntity> _extendedEntityList = dynPage.getExtendedEntityList();
    ExtendedEntity _get = _extendedEntityList.get(0);
    EList<ExtendedReference> _extendedReference = _get.getExtendedReference();
    for (final ExtendedReference ref : _extendedReference) {
      EList<ExtendedAttribute> _extendedAttribute = ref.getExtendedAttribute();
      ExtendedAttribute _get_1 = _extendedAttribute.get(0);
      String _name = _get_1.getName();
      String _name_1 = attr.getName();
      boolean _equalsIgnoreCase = _name.equalsIgnoreCase(_name_1);
      if (_equalsIgnoreCase) {
        EList<ExtendedAttribute> _extendedAttributeReferenced = ref.getExtendedAttributeReferenced();
        for (final ExtendedAttribute refAttr : _extendedAttributeReferenced) {
          String _name_2 = refAttr.getName();
          boolean _equalsIgnoreCase_1 = _name_2.equalsIgnoreCase("id");
          if (_equalsIgnoreCase_1) {
            EList<ExtendedAttribute> _extendedAttribute_1 = ref.getExtendedAttribute();
            EList<ExtendedAttribute> _extendedAttributeReferenced_1 = ref.getExtendedAttributeReferenced();
            int _indexOf = _extendedAttributeReferenced_1.indexOf(refAttr);
            return _extendedAttribute_1.get(_indexOf);
          }
        }
      }
    }
    return null;
  }
  
  public static Boolean isLinkedAttributeReference(final Attribute attribute, final DynamicPage page) {
    EList<Entity> _entities = page.getEntities();
    for (final Entity e : _entities) {
      EList<Reference> _references = e.getReferences();
      for (final Reference ref : _references) {
        EList<Attribute> _attribute = ref.getAttribute();
        Attribute _get = _attribute.get(0);
        boolean _equals = _get.equals(attribute);
        if (_equals) {
          return Boolean.valueOf(true);
        }
      }
    }
    return Boolean.valueOf(false);
  }
  
  public static Reference searchLinkedAttributeReference(final Attribute attribute, final DynamicPage page) {
    EList<Entity> _entities = page.getEntities();
    for (final Entity e : _entities) {
      EList<Reference> _references = e.getReferences();
      for (final Reference ref : _references) {
        EList<Attribute> _attribute = ref.getAttribute();
        Attribute _get = _attribute.get(0);
        String _name = _get.getName();
        String _name_1 = attribute.getName();
        boolean _equalsIgnoreCase = _name.equalsIgnoreCase(_name_1);
        if (_equalsIgnoreCase) {
          return ref;
        }
      }
    }
    return null;
  }
  
  public static CharSequence genLinkedInfo(final DynamicPage page, final Component com) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private  $entitiesRef = array(");
    _builder.newLine();
    {
      EList<Link> _links = page.getLinks();
      for(final Link linkItem : _links) {
        CharSequence _switchResult = null;
        boolean _matched = false;
        if (!_matched) {
          if (linkItem instanceof InternalLink) {
            _matched=true;
            CharSequence _xifexpression = null;
            Attribute _linkedAttribute = ((InternalLink)linkItem).getLinkedAttribute();
            Boolean _isLinkedAttributeReference = Slug.isLinkedAttributeReference(_linkedAttribute, page);
            if ((_isLinkedAttributeReference).booleanValue()) {
              CharSequence _xblockexpression = null;
              {
                Attribute _linkedAttribute_1 = ((InternalLink)linkItem).getLinkedAttribute();
                Reference ref = Slug.searchLinkedAttributeReference(_linkedAttribute_1, page);
                StringConcatenation _builder_1 = new StringConcatenation();
                _builder_1.append("\"");
                String _name = ((InternalLink)linkItem).getName();
                String _lowerCase = _name.toLowerCase();
                _builder_1.append(_lowerCase, "");
                _builder_1.append("\" => array(\"db\"=> \"#__");
                String _name_1 = com.getName();
                String _lowerCase_1 = _name_1.toLowerCase();
                _builder_1.append(_lowerCase_1, "");
                _builder_1.append("_");
                Entity _entity = ref.getEntity();
                String _name_2 = _entity.getName();
                String _lowerCase_2 = _name_2.toLowerCase();
                _builder_1.append(_lowerCase_2, "");
                _builder_1.append("\",\"refattr\" => array(");
                StringConcatenation _builder_2 = new StringConcatenation();
                _builder_2.append("\"");
                EList<Attribute> _attributerefereced = ref.getAttributerefereced();
                CharSequence _transformAttributeListInString = Slug.transformAttributeListInString(_builder_2.toString(), "", _attributerefereced, ",");
                _builder_1.append(_transformAttributeListInString, "");
                _builder_1.append(")),");
                _xblockexpression = _builder_1;
              }
              _xifexpression = _xblockexpression;
            }
            _switchResult = _xifexpression;
          }
        }
        _builder.append(_switchResult, "");
      }
    }
    _builder.append("null);");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public static CharSequence transformAttributeListInString(final EList<Attribute> attributes, final String separeSign) {
    StringBuffer result = new StringBuffer();
    for (final Attribute attr : attributes) {
      Attribute _last = IterableExtensions.<Attribute>last(attributes);
      boolean _notEquals = (!Objects.equal(attr, _last));
      if (_notEquals) {
        String _name = attr.getName();
        String _lowerCase = _name.toLowerCase();
        String _plus = (_lowerCase + separeSign);
        result.append(_plus);
      } else {
        String _name_1 = attr.getName();
        result.append(_name_1);
      }
    }
    return result.toString();
  }
  
  public static CharSequence transformAttributeListInString(final String postWord, final EList<Attribute> attributes, final String separeSign) {
    StringBuffer result = new StringBuffer();
    for (final Attribute attr : attributes) {
      Attribute _last = IterableExtensions.<Attribute>last(attributes);
      boolean _notEquals = (!Objects.equal(attr, _last));
      if (_notEquals) {
        String _name = attr.getName();
        String _lowerCase = _name.toLowerCase();
        String _plus = (postWord + _lowerCase);
        String _plus_1 = (_plus + separeSign);
        result.append(_plus_1);
      } else {
        String _name_1 = attr.getName();
        String _plus_2 = (postWord + _name_1);
        result.append(_plus_2);
      }
    }
    return result.toString();
  }
  
  public static CharSequence transformAttributeListInString(final String quotationMark, final String postWord, final EList<Attribute> attributes, final String separeSign) {
    StringBuffer result = new StringBuffer();
    for (final Attribute attr : attributes) {
      Attribute _last = IterableExtensions.<Attribute>last(attributes);
      boolean _notEquals = (!Objects.equal(attr, _last));
      if (_notEquals) {
        String _name = attr.getName();
        String _lowerCase = _name.toLowerCase();
        String _plus = ((quotationMark + postWord) + _lowerCase);
        String _plus_1 = (_plus + quotationMark);
        String _plus_2 = (_plus_1 + separeSign);
        result.append(_plus_2);
      } else {
        String _name_1 = attr.getName();
        String _plus_3 = ((quotationMark + postWord) + _name_1);
        String _plus_4 = (_plus_3 + quotationMark);
        result.append(_plus_4);
      }
    }
    return result.toString();
  }
  
  public static CharSequence getParamterType(final MethodParameter parameter) {
    return "";
  }
}
