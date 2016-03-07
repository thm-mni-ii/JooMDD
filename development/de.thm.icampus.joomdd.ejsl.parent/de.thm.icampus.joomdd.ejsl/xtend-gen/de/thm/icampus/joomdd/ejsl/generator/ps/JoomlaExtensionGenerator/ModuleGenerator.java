package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.Author;
import de.thm.icampus.joomdd.ejsl.eJSL.Component;
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.Language;
import de.thm.icampus.joomdd.ejsl.eJSL.Link;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.Module;
import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.eJSL.PageReference;
import de.thm.icampus.joomdd.ejsl.eJSL.SectionKinds;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.AbstractExtensionGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.LinkGeneratorClient;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.PageGeneratorClient;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.LanguageGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug;
import java.util.Calendar;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Generator</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eJSLGenerator.ModuleGenerator#getPageClient <em>Page Client</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eJSLGenerator.GeneratorTemplatePackage#getModuleGenerator()
 * @model
 * @generated
 */
@SuppressWarnings("all")
public class ModuleGenerator extends AbstractExtensionGenerator {
  private PageGeneratorClient pageClient;
  
  private String modelOfComponent = "\'<modelOfComponent>\'";
  
  private String modelPath = "\'/components/com_<nameOfComponent>/models\'";
  
  private String modelOfComponent2 = null;
  
  private ExtendedModule extMod;
  
  private ExtendedDynamicPage dynpage;
  
  private ExtendedComponent com;
  
  public ModuleGenerator(final ExtendedModule module, final IFileSystemAccess fsa) {
    this.setFsa(fsa);
    String _name = module.getName();
    String _slugify = Slug.slugify(_name);
    String _plus = ("mod_" + _slugify);
    this.setName(_plus);
    this.extMod = module;
    ExtendedPageReference _extendedPageReference = module.getExtendedPageReference();
    ExtendedPage _extendedPage = _extendedPageReference.getExtendedPage();
    ExtendedDynamicPage _extendedDynamicPageInstance = _extendedPage.getExtendedDynamicPageInstance();
    this.dynpage = _extendedDynamicPageInstance;
    ExtendedComponent _extendedComponent = module.getExtendedComponent();
    this.com = _extendedComponent;
    this.ComponentInformation(module);
    this.formatName(this.extMod);
  }
  
  public void formatName(final Module module) {
    String _name = module.getName();
    String _slugify = Slug.slugify(_name);
    module.setName(_slugify);
  }
  
  public PageGeneratorClient getPageClient() {
    return this.pageClient;
  }
  
  public void setPageClient(final PageGeneratorClient value) {
    this.pageClient = value;
  }
  
  @Override
  public CharSequence generate() {
    this.generateJoomlaDirectory("");
    CharSequence _IndexContent = this.IndexContent(this.extMod);
    this.generateFile(("index" + ".html"), _IndexContent);
    String _name = this.getName();
    String _plus = (_name + ".xml");
    CharSequence _xmlContent = this.xmlContent(this.extMod);
    this.generateFile(_plus, _xmlContent);
    String _name_1 = this.getName();
    String _plus_1 = (_name_1 + ".php");
    CharSequence _phpContent = this.phpContent(this.extMod);
    this.generateFile(_plus_1, _phpContent);
    PageReference _pageRef = this.extMod.getPageRef();
    Page _page = _pageRef.getPage();
    CharSequence _helperPHP = this.helperPHP(this.extMod, ((DynamicPage) _page));
    this.generateFile("helper.php", _helperPHP);
    this.generateJoomlaDirectory("tmpl");
    CharSequence _defaultTemplate = this.defaultTemplate();
    this.generateFile("tmpl/default.php", _defaultTemplate);
    CharSequence _IndexContent_1 = this.IndexContent(this.extMod);
    this.generateFile("tmpl/index.html", _IndexContent_1);
    IFileSystemAccess _fsa = this.getFsa();
    LanguageGenerator lang = new LanguageGenerator(_fsa);
    String _name_2 = this.extMod.getName();
    String _lowerCase = _name_2.toLowerCase();
    String _plus_2 = ("mod_" + _lowerCase);
    lang.genModuletLanguage(this.extMod, _plus_2);
    return "";
  }
  
  public CharSequence xmlContent(final ExtendedModule module) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    _builder.newLine();
    _builder.append("<extension type=\"module\" version=\"3.3\" client=\"site\" method=\"upgrade\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<name>");
    String _name = module.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "\t");
    _builder.append("</name>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<creationDate>");
    Object _xifexpression = null;
    Manifestation _manifest = module.getManifest();
    String _creationdate = _manifest.getCreationdate();
    boolean _notEquals = (!Objects.equal(_creationdate, null));
    if (_notEquals) {
      Manifestation _manifest_1 = module.getManifest();
      _xifexpression = _manifest_1.getCreationdate();
    } else {
      Calendar _instance = Calendar.getInstance();
      _xifexpression = Integer.valueOf(_instance.get(Calendar.YEAR));
    }
    _builder.append(_xifexpression, "\t");
    _builder.append("</creationDate>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<copyright>");
    String _xifexpression_1 = null;
    Manifestation _manifest_2 = module.getManifest();
    String _copyright = _manifest_2.getCopyright();
    boolean _notEquals_1 = (!Objects.equal(_copyright, null));
    if (_notEquals_1) {
      Manifestation _manifest_3 = module.getManifest();
      _xifexpression_1 = _manifest_3.getCopyright();
    } else {
      _xifexpression_1 = "All rights reserved by Author.";
    }
    _builder.append(_xifexpression_1, "\t");
    _builder.append("</copyright>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<license>");
    String _xifexpression_2 = null;
    Manifestation _manifest_4 = module.getManifest();
    String _license = _manifest_4.getLicense();
    boolean _notEquals_2 = (!Objects.equal(_license, null));
    if (_notEquals_2) {
      Manifestation _manifest_5 = module.getManifest();
      _xifexpression_2 = _manifest_5.getLicense();
    } else {
      _xifexpression_2 = "GNU General Public License version 2 or later";
    }
    _builder.append(_xifexpression_2, "\t");
    _builder.append("</license>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    Manifestation _manifest_6 = module.getManifest();
    EList<Author> _authors = _manifest_6.getAuthors();
    CharSequence _generate = this.generate(_authors);
    _builder.append(_generate, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<version>");
    String _xifexpression_3 = null;
    Manifestation _manifest_7 = module.getManifest();
    String _version = _manifest_7.getVersion();
    boolean _notEquals_3 = (!Objects.equal(_version, null));
    if (_notEquals_3) {
      Manifestation _manifest_8 = module.getManifest();
      _xifexpression_3 = _manifest_8.getVersion();
    } else {
      _xifexpression_3 = "1.0.0";
    }
    _builder.append(_xifexpression_3, "\t");
    _builder.append("</version>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<description>");
    String _xifexpression_4 = null;
    Manifestation _manifest_9 = module.getManifest();
    String _description = _manifest_9.getDescription();
    boolean _notEquals_4 = (!Objects.equal(_description, null));
    if (_notEquals_4) {
      Manifestation _manifest_10 = module.getManifest();
      _xifexpression_4 = _manifest_10.getDescription();
    } else {
      _xifexpression_4 = "Place Description here";
    }
    _builder.append(_xifexpression_4, "\t");
    _builder.append("</description>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<!-- Listing of all files that should be installed for the module -->");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<files>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<filename module=\"");
    String _name_1 = this.getName();
    _builder.append(_name_1, "\t\t");
    _builder.append("\">");
    String _name_2 = this.getName();
    _builder.append(_name_2, "\t\t");
    _builder.append(".php</filename>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("<filename>index.html</filename>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<filename>helper.php</filename>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<folder>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<filename>tmpl/index.html</filename>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<filename>tmpl/default.php</filename>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</folder>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<folder>language</folder>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</files>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<!-- All language files shipped with the modul -->");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<languages>");
    _builder.newLine();
    {
      EList<Language> _languages = module.getLanguages();
      for(final Language lang : _languages) {
        _builder.append("\t\t");
        _builder.append("<language tag=\"");
        String _name_3 = lang.getName();
        _builder.append(_name_3, "\t\t");
        _builder.append("\">language/");
        String _name_4 = lang.getName();
        _builder.append(_name_4, "\t\t");
        _builder.append("/");
        String _name_5 = lang.getName();
        _builder.append(_name_5, "\t\t");
        _builder.append(".");
        String _name_6 = this.getName();
        _builder.append(_name_6, "\t\t");
        _builder.append(".ini</language>");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("<language tag=\"");
        String _name_7 = lang.getName();
        _builder.append(_name_7, "\t\t");
        _builder.append("\">language/");
        String _name_8 = lang.getName();
        _builder.append(_name_8, "\t\t");
        _builder.append("/");
        String _name_9 = lang.getName();
        _builder.append(_name_9, "\t\t");
        _builder.append(".");
        String _name_10 = this.getName();
        _builder.append(_name_10, "\t\t");
        _builder.append(".sys.ini</language>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("</languages>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<!-- Optional parameters -->");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<config>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<fields name=\"params\">");
    _builder.newLine();
    _builder.append("\t   ");
    _builder.append("<fieldset name=\"basic\">");
    _builder.newLine();
    {
      boolean _notEquals_5 = (!Objects.equal(this.dynpage, null));
      if (_notEquals_5) {
        _builder.append("\t   ");
        _builder.append("<field name=\"ordering\" type=\"list\"");
        _builder.newLine();
        _builder.append("\t\t   \t        ");
        _builder.append("label=\"");
        String _name_11 = module.getName();
        String _nameExtensionBind = Slug.nameExtensionBind("mod", _name_11);
        String _upperCase = _nameExtensionBind.toUpperCase();
        _builder.append(_upperCase, "\t\t   \t        ");
        _builder.append("_ORDERING\"");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t   \t        ");
        _builder.append("description=\"");
        String _name_12 = module.getName();
        String _nameExtensionBind_1 = Slug.nameExtensionBind("mod", _name_12);
        String _upperCase_1 = _nameExtensionBind_1.toUpperCase();
        _builder.append(_upperCase_1, "\t\t   \t        ");
        _builder.append("_JFIELD_ORDERING_DESC\"");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t   \t        ");
        _builder.append("class=\"inputbox\"");
        _builder.newLine();
        _builder.append("\t\t   \t        ");
        _builder.append("default=\"id\">");
        _builder.newLine();
        _builder.append("\t\t   \t      ");
        _builder.append("<option value=\"id\">ID</option>  ");
        _builder.newLine();
        {
          EList<ExtendedAttribute> _extendFiltersList = this.dynpage.getExtendFiltersList();
          for(final ExtendedAttribute attr : _extendFiltersList) {
            _builder.append("<option value=\"");
            String _name_13 = attr.getName();
            String _lowerCase = _name_13.toLowerCase();
            _builder.append(_lowerCase, "");
            _builder.append("\">");
            String _name_14 = module.getName();
            String _nameExtensionBind_2 = Slug.nameExtensionBind("mod", _name_14);
            String _upperCase_2 = _nameExtensionBind_2.toUpperCase();
            _builder.append(_upperCase_2, "");
            _builder.append("_FORM_LBL_");
            String _name_15 = attr.getName();
            String _upperCase_3 = _name_15.toUpperCase();
            _builder.append(_upperCase_3, "");
            _builder.append("</option>");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t   ");
        _builder.append(" ");
        _builder.append("</field>");
        _builder.newLine();
      }
    }
    _builder.append("\t    ");
    _builder.append("<field name=\"direction\" type=\"list\"");
    _builder.newLine();
    _builder.append("   \t        ");
    _builder.append("label=\"");
    String _name_16 = module.getName();
    String _nameExtensionBind_3 = Slug.nameExtensionBind("mod", _name_16);
    String _upperCase_4 = _nameExtensionBind_3.toUpperCase();
    _builder.append(_upperCase_4, "   \t        ");
    _builder.append("_DIRECTION\"");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t        ");
    _builder.append("description=\"");
    String _name_17 = module.getName();
    String _nameExtensionBind_4 = Slug.nameExtensionBind("mod", _name_17);
    String _upperCase_5 = _nameExtensionBind_4.toUpperCase();
    _builder.append(_upperCase_5, "   \t        ");
    _builder.append("_JFIELD_DIRECTION_DESC\"");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t        ");
    _builder.append("class=\"inputbox\"");
    _builder.newLine();
    _builder.append("   \t        ");
    _builder.append("size=\"1\"");
    _builder.newLine();
    _builder.append("   \t        ");
    _builder.append("default=\"ASC\">");
    _builder.newLine();
    _builder.append("   \t        ");
    _builder.append("<option value=\"ASC\">");
    String _name_18 = module.getName();
    String _nameExtensionBind_5 = Slug.nameExtensionBind("mod", _name_18);
    String _upperCase_6 = _nameExtensionBind_5.toUpperCase();
    _builder.append(_upperCase_6, "   \t        ");
    _builder.append("_ASC</option>");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t        ");
    _builder.append("<option value=\"DESC\">");
    String _name_19 = module.getName();
    String _nameExtensionBind_6 = Slug.nameExtensionBind("mod", _name_19);
    String _upperCase_7 = _nameExtensionBind_6.toUpperCase();
    _builder.append(_upperCase_7, "   \t        ");
    _builder.append("_DESC</option>");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t     ");
    _builder.append("</field>");
    _builder.newLine();
    _builder.append("\t    \t\t   \t        ");
    _builder.newLine();
    _builder.append("\t   ");
    _builder.append("<field");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("name=\"start\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("type=\"int\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("default=\"0\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("label=\"MOD_");
    String _name_20 = module.getName();
    String _upperCase_8 = _name_20.toUpperCase();
    _builder.append(_upperCase_8, "\t\t\t");
    _builder.append("_START_LABEL\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("description=\"MOD_");
    String _name_21 = module.getName();
    String _upperCase_9 = _name_21.toUpperCase();
    _builder.append(_upperCase_9, "\t\t\t");
    _builder.append("_START_DESC\" />");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t ");
    _builder.append("<field");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("name=\"limit\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("type=\"int\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("default=\"10\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("label=\"MOD_");
    String _name_22 = module.getName();
    String _upperCase_10 = _name_22.toUpperCase();
    _builder.append(_upperCase_10, "\t\t\t");
    _builder.append("_LIMIT_LABEL\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("description=\"MOD_");
    String _name_23 = module.getName();
    String _upperCase_11 = _name_23.toUpperCase();
    _builder.append(_upperCase_11, "\t\t\t");
    _builder.append("_LIMIT_DESC\" />");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t ");
    _builder.append("<field");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("name=\"search\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("type=\"text\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("label=\"MOD_");
    String _name_24 = module.getName();
    String _upperCase_12 = _name_24.toUpperCase();
    _builder.append(_upperCase_12, "\t\t\t");
    _builder.append("_SEARCH_LABEL\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("description=\"MOD_");
    String _name_25 = module.getName();
    String _upperCase_13 = _name_25.toUpperCase();
    _builder.append(_upperCase_13, "\t\t\t");
    _builder.append("_SEARCH_DESC\" />");
    _builder.newLineIfNotEmpty();
    _builder.append("\t   ");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("<field name=\"state\" type=\"list\"");
    _builder.newLine();
    _builder.append("\t   \t        ");
    _builder.append("label=\"MOD_");
    String _name_26 = module.getName();
    String _upperCase_14 = _name_26.toUpperCase();
    _builder.append(_upperCase_14, "\t   \t        ");
    _builder.append("_JSTATUS\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t   \t        ");
    _builder.append("description=\"MOD_");
    String _name_27 = module.getName();
    String _upperCase_15 = _name_27.toUpperCase();
    _builder.append(_upperCase_15, "\t   \t        ");
    _builder.append("_JFIELD_PUBLISHED_DESC\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t   \t        ");
    _builder.append("class=\"inputbox\"");
    _builder.newLine();
    _builder.append("\t   \t        ");
    _builder.append("size=\"1\"");
    _builder.newLine();
    _builder.append("\t   \t        ");
    _builder.append("default=\"1\">");
    _builder.newLine();
    _builder.append("\t   \t        ");
    _builder.append("<option value=\"1\">JPUBLISHED</option>");
    _builder.newLine();
    _builder.append("\t   \t        ");
    _builder.append("<option value=\"0\">JUNPUBLISHED</option>");
    _builder.newLine();
    _builder.append("\t   \t        ");
    _builder.append("<option value=\"2\">JARCHIVED</option>");
    _builder.newLine();
    _builder.append("\t   \t        ");
    _builder.append("<option value=\"-2\">JTRASHED</option>");
    _builder.newLine();
    _builder.append("\t   \t    ");
    _builder.append("</field>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</fieldset>");
    _builder.newLine();
    {
      boolean _and = false;
      boolean _notEquals_6 = (!Objects.equal(this.com, null));
      if (!_notEquals_6) {
        _and = false;
      } else {
        boolean _notEquals_7 = (!Objects.equal(this.dynpage, null));
        _and = _notEquals_7;
      }
      if (_and) {
        _builder.append("\t");
        _builder.append("<fieldset name=\"filter\">");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(" ");
        _builder.append("<field");
        _builder.newLine();
        _builder.append("              ");
        _builder.append("name=\"created_by\"");
        _builder.newLine();
        _builder.append("               ");
        _builder.append("addfieldpath=\"administrator/components/");
        String _name_28 = this.com.getName();
        String _nameExtensionBind_7 = Slug.nameExtensionBind("com", _name_28);
        String _lowerCase_1 = _nameExtensionBind_7.toLowerCase();
        _builder.append(_lowerCase_1, "               ");
        _builder.append("/models/fields\"");
        _builder.newLineIfNotEmpty();
        _builder.append("              ");
        _builder.append("type=\"");
        String _name_29 = this.com.getName();
        String _lowerCase_2 = _name_29.toLowerCase();
        _builder.append(_lowerCase_2, "              ");
        _builder.append("user\"");
        _builder.newLineIfNotEmpty();
        _builder.append("              ");
        _builder.append("label=\"");
        String _name_30 = module.getName();
        String _nameExtensionBind_8 = Slug.nameExtensionBind("mod", _name_30);
        String _upperCase_16 = _nameExtensionBind_8.toUpperCase();
        _builder.append(_upperCase_16, "              ");
        _builder.append("_FILTER_CREATED_BY\"");
        _builder.newLineIfNotEmpty();
        _builder.append("              ");
        _builder.append("description=\"");
        String _name_31 = module.getName();
        String _nameExtensionBind_9 = Slug.nameExtensionBind("mod", _name_31);
        String _upperCase_17 = _nameExtensionBind_9.toUpperCase();
        _builder.append(_upperCase_17, "              ");
        _builder.append("_FILTER_CREATED_BY\"");
        _builder.newLineIfNotEmpty();
        _builder.append("               ");
        _builder.append("entity = \"");
        EList<ExtendedEntity> _extendedEntityList = this.dynpage.getExtendedEntityList();
        ExtendedEntity _get = _extendedEntityList.get(0);
        String _name_32 = _get.getName();
        String _lowerCase_3 = _name_32.toLowerCase();
        _builder.append(_lowerCase_3, "               ");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("              ");
        _builder.append(">");
        _builder.newLine();
        _builder.append("              ");
        _builder.append("<option value=\"\">JOPTION_SELECT_CREATED_BY</option>");
        _builder.newLine();
        _builder.append("              ");
        _builder.append("</field>");
        _builder.newLine();
        {
          EList<ExtendedAttribute> _extendFiltersList_1 = this.dynpage.getExtendFiltersList();
          for(final ExtendedAttribute attr_1 : _extendFiltersList_1) {
            _builder.append("            ");
            _builder.append("<field");
            _builder.newLine();
            _builder.append("            ");
            _builder.append("   ");
            _builder.append("addfieldpath=\"administrator/components/");
            String _name_33 = this.com.getName();
            String _nameExtensionBind_10 = Slug.nameExtensionBind("com", _name_33);
            String _lowerCase_4 = _nameExtensionBind_10.toLowerCase();
            _builder.append(_lowerCase_4, "               ");
            _builder.append("/models/fields\"");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("     ");
            _builder.append("name=\"");
            String _name_34 = attr_1.getName();
            _builder.append(_name_34, "                 ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("     ");
            _builder.append("type=\"");
            EList<ExtendedEntity> _extendedEntityList_1 = this.dynpage.getExtendedEntityList();
            ExtendedEntity _get_1 = _extendedEntityList_1.get(0);
            String _name_35 = _get_1.getName();
            String _lowerCase_5 = _name_35.toLowerCase();
            _builder.append(_lowerCase_5, "                 ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("     ");
            _builder.append("label=\"");
            String _name_36 = module.getName();
            String _nameExtensionBind_11 = Slug.nameExtensionBind("mod", _name_36);
            String _upperCase_18 = _nameExtensionBind_11.toUpperCase();
            _builder.append(_upperCase_18, "                 ");
            _builder.append("_FILTER_");
            String _name_37 = attr_1.getName();
            String _upperCase_19 = _name_37.toUpperCase();
            _builder.append(_upperCase_19, "                 ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("     ");
            _builder.append("description=\"");
            String _name_38 = module.getName();
            String _nameExtensionBind_12 = Slug.nameExtensionBind("mod", _name_38);
            String _upperCase_20 = _nameExtensionBind_12.toUpperCase();
            _builder.append(_upperCase_20, "                 ");
            _builder.append("_FILTER_");
            String _name_39 = attr_1.getName();
            String _upperCase_21 = _name_39.toUpperCase();
            _builder.append(_upperCase_21, "                 ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("      ");
            _builder.append("valueColumn=\"");
            String _name_40 = attr_1.getName();
            String _lowerCase_6 = _name_40.toLowerCase();
            _builder.append(_lowerCase_6, "                  ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("      ");
            _builder.append("textColumn=\"");
            String _name_41 = attr_1.getName();
            String _lowerCase_7 = _name_41.toLowerCase();
            _builder.append(_lowerCase_7, "                  ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("            ");
            _builder.append("     ");
            _builder.append(">");
            _builder.newLine();
            _builder.append("            ");
            _builder.append(" ");
            _builder.append("<option value=\"\">JOPTION_SELECT_");
            String _name_42 = attr_1.getName();
            String _upperCase_22 = _name_42.toUpperCase();
            _builder.append(_upperCase_22, "             ");
            _builder.append("</option>");
            _builder.newLineIfNotEmpty();
            _builder.append("          ");
            _builder.append("</field>");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.append("</fieldset>");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("</fields>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</config>");
    _builder.newLine();
    _builder.append("</extension>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence phpContent(final Module modul) {
    StringConcatenation _builder = new StringConcatenation();
    {
      PageReference _pageRef = modul.getPageRef();
      Component _pagescr = _pageRef.getPagescr();
      boolean _notEquals = (!Objects.equal(_pagescr, null));
      if (_notEquals) {
        _builder.append("\t");
        PageReference _pageRef_1 = modul.getPageRef();
        Component c = _pageRef_1.getPagescr();
        _builder.newLineIfNotEmpty();
        _builder.append("<?php");
        _builder.newLine();
        CharSequence _generateFileDoc = Slug.generateFileDoc(this.extMod, true);
        _builder.append(_generateFileDoc, "");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("\t");
        _builder.append("// Define used Jimports here");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t");
        _builder.append("// No direct access to this file");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("defined(\'_JEXEC\') or die;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("// Include the ");
        String _name = this.extMod.getName();
        _builder.append(_name, "\t\t");
        _builder.append(" functions only once");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("require_once __DIR__ . \'/helper.php\';");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("require_once JPATH_ADMINISTRATOR . \'/components/com_");
        String _name_1 = c.getName();
        String _lowerCase = _name_1.toLowerCase();
        _builder.append(_lowerCase, "\t\t");
        _builder.append("/helpers/");
        String _name_2 = c.getName();
        String _lowerCase_1 = _name_2.toLowerCase();
        _builder.append(_lowerCase_1, "\t\t");
        _builder.append(".php\';");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("\t\t");
        _builder.newLine();
      } else {
        _builder.append("<?php");
        _builder.newLine();
        CharSequence _generateFileDoc_1 = Slug.generateFileDoc(this.extMod, true);
        _builder.append(_generateFileDoc_1, "");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("\t");
        _builder.append("// Define used Jimports here");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t");
        _builder.append("// No direct access to this file");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("defined(\'_JEXEC\') or die;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("// Include the ");
        String _name_3 = this.extMod.getName();
        _builder.append(_name_3, "\t\t");
        _builder.append(" functions only once");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("require_once __DIR__ . \'/helper.php\';");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("// Models, Functions should be implementated here");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("// ");
    String _name_4 = modul.getName();
    String _substring = _name_4.substring(0, 1);
    String _upperCase = _substring.toUpperCase();
    String _name_5 = modul.getName();
    String _substring_1 = _name_5.substring(1);
    String _lowerCase_2 = _substring_1.toLowerCase();
    String _plus = (_upperCase + _lowerCase_2);
    _builder.append(_plus, "    ");
    _builder.append("Helper::updateReset();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("$items = &");
    String _name_6 = modul.getName();
    String _substring_2 = _name_6.substring(0, 1);
    String _upperCase_1 = _substring_2.toUpperCase();
    String _name_7 = modul.getName();
    String _substring_3 = _name_7.substring(1);
    String _lowerCase_3 = _substring_3.toLowerCase();
    String _plus_1 = (_upperCase_1 + _lowerCase_3);
    _builder.append(_plus_1, "\t");
    _builder.append("Helper::getList($params);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("$moduleclass_sfx = htmlspecialchars($params->get(\'moduleclass_sfx\'));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("require JModuleHelper::getLayoutPath(\'");
    String _name_8 = this.getName();
    _builder.append(_name_8, "\t");
    _builder.append("\', $params->get(\'layout\', \'default\'));");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence defaultTemplate() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?php");
    _builder.newLine();
    CharSequence _generateFileDoc = Slug.generateFileDoc(this.extMod, true);
    _builder.append(_generateFileDoc, "");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// No direct access to this file");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("defined(\'_JEXEC\') or die;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*if Component-Helper to be used");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*require_once JPATH_ROOT . \'/components/com_<nameOfComponent>/helpers/<nameOfComponentHelper.php\';");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*$baseurl = JUri::base();");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("?>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<ul class=\"");
    String _name = this.extMod.getName();
    _builder.append(_name, "\t");
    _builder.append("<?php echo $moduleclass_sfx; ?>\">");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<?php foreach ($items as $item) : ?>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<li><div class=\"");
    PageReference _pageRef = this.extMod.getPageRef();
    Page _page = _pageRef.getPage();
    String _name_1 = _page.getName();
    _builder.append(_name_1, "\t");
    _builder.append("item\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<?php if (empty($item)) : ?>");
    _builder.newLine();
    {
      EList<Entity> _entities = this.dynpage.getEntities();
      boolean _isEmpty = _entities.isEmpty();
      if (_isEmpty) {
        _builder.append("\t\t");
        _builder.append("<?php// itemlist is empty ;?>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("<!DOCTYPE html><titel></titel>");
        _builder.newLine();
      } else {
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("<?php echo \"itemlist is empty\" ?>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("<!DOCTYPE html><titel></titel>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("<?php else : ?>");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("\t\t\t\t\t\t");
        _builder.newLine();
        {
          EList<ExtendedAttribute> _extendedTableColumnList = this.dynpage.getExtendedTableColumnList();
          for(final ExtendedAttribute attr : _extendedTableColumnList) {
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("<?php $");
            String _name_2 = attr.getName();
            _builder.append(_name_2, "\t\t\t");
            _builder.append(" = $item->");
            String _name_3 = attr.getName();
            String _lowerCase = _name_3.toLowerCase();
            _builder.append(_lowerCase, "\t\t\t");
            _builder.append(";?>");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("<?php echo ");
            PageReference _pageRef_1 = this.extMod.getPageRef();
            Page _page_1 = _pageRef_1.getPage();
            EList<Link> _links = _page_1.getLinks();
            String _checkLinkOfAttributes = this.checkLinkOfAttributes(attr, _links);
            _builder.append(_checkLinkOfAttributes, "\t\t\t");
            _builder.append("; ?>");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t\t");
        _builder.append("\t\t");
        _builder.newLine();
      }
    }
    _builder.append("\t\t");
    _builder.append("<?php endif; ?>");
    _builder.newLine();
    _builder.append("</div></li>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<?php endforeach; ?>");
    _builder.newLine();
    _builder.append("</ul>");
    _builder.newLine();
    return _builder;
  }
  
  public String checkLinkOfAttributes(final ExtendedAttribute attribute, final EList<Link> listLink) {
    String _name = attribute.getName();
    String result = _name.toString();
    boolean _or = false;
    PageReference _pageRef = this.extMod.getPageRef();
    SectionKinds _sect = _pageRef.getSect();
    boolean _equals = Objects.equal(_sect, null);
    if (_equals) {
      _or = true;
    } else {
      PageReference _pageRef_1 = this.extMod.getPageRef();
      Component _pagescr = _pageRef_1.getPagescr();
      boolean _equals_1 = Objects.equal(_pagescr, null);
      _or = _equals_1;
    }
    if (_or) {
      return ("$" + result);
    }
    for (final Link lk : listLink) {
      Attribute _linkedAttribute = lk.getLinkedAttribute();
      String _name_1 = _linkedAttribute.getName();
      String _name_2 = attribute.getName();
      boolean _equalsIgnoreCase = _name_1.equalsIgnoreCase(_name_2);
      if (_equalsIgnoreCase) {
        PageReference _pageRef_2 = this.extMod.getPageRef();
        SectionKinds _sect_1 = _pageRef_2.getSect();
        String _sectioName = Slug.getSectioName(_sect_1);
        PageReference _pageRef_3 = this.extMod.getPageRef();
        Component _pagescr_1 = _pageRef_3.getPagescr();
        String _name_3 = _pagescr_1.getName();
        String _lowerCase = _name_3.toLowerCase();
        LinkGeneratorClient lkClient = new LinkGeneratorClient(lk, _sectioName, _lowerCase, "$item->");
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("JHtml::_(\'link\',");
        CharSequence _generateLink = lkClient.generateLink();
        _builder.append(_generateLink, "");
        _builder.append(", $item->");
        String _name_4 = attribute.getName();
        String _lowerCase_1 = _name_4.toLowerCase();
        _builder.append(_lowerCase_1, "");
        _builder.append(")");
        return _builder.toString();
      }
    }
    return ("$" + result);
  }
  
  public CharSequence helperPHP(final Module modul, final DynamicPage mpage) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?php");
    _builder.newLine();
    CharSequence _generateFileDoc = Slug.generateFileDoc(this.extMod, true);
    _builder.append(_generateFileDoc, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("// No direct access to this file");
    _builder.newLine();
    _builder.append("defined(\'_JEXEC\') or die;");
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*Helper for ");
    String _name = this.extMod.getName();
    _builder.append(_name, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @category");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @package");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @since");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("class ");
    String _name_1 = modul.getName();
    String _substring = _name_1.substring(0, 1);
    String _upperCase = _substring.toUpperCase();
    String _name_2 = modul.getName();
    String _substring_1 = _name_2.substring(1);
    String _lowerCase = _substring_1.toLowerCase();
    String _plus = (_upperCase + _lowerCase);
    _builder.append(_plus, "");
    _builder.append("Helper");
    _builder.newLineIfNotEmpty();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _genGetList = this.genGetList();
    _builder.append(_genGetList, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String ComponentInformation(final Module modul) {
    String _xifexpression = null;
    PageReference _pageRef = modul.getPageRef();
    Component _pagescr = _pageRef.getPagescr();
    boolean _notEquals = (!Objects.equal(_pagescr, null));
    if (_notEquals) {
      String _xblockexpression = null;
      {
        PageReference _pageRef_1 = modul.getPageRef();
        SectionKinds _sect = _pageRef_1.getSect();
        String section = _sect.getName();
        PageReference _pageRef_2 = modul.getPageRef();
        Component c = _pageRef_2.getPagescr();
        boolean _equalsIgnoreCase = section.equalsIgnoreCase("backend");
        if (_equalsIgnoreCase) {
          String _name = c.getName();
          String _lowerCase = _name.toLowerCase();
          String _plus = ("\'/administrator/components/com_" + _lowerCase);
          String _plus_1 = (_plus + "/models\'");
          this.modelPath = _plus_1;
        } else {
          String _name_1 = c.getName();
          String _lowerCase_1 = _name_1.toLowerCase();
          String _plus_2 = ("\'/components/com_" + _lowerCase_1);
          String _plus_3 = (_plus_2 + "/models\'");
          this.modelPath = _plus_3;
        }
        String _name_2 = c.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_2);
        String _plus_4 = ("\"" + _firstUpper);
        String _plus_5 = (_plus_4 + "\"");
        this.modelOfComponent = _plus_5;
        String _name_3 = c.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_3);
        String _plus_6 = ("\"" + _firstUpper_1);
        String _plus_7 = (_plus_6 + "Model\"");
        _xblockexpression = this.modelOfComponent2 = _plus_7;
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  public CharSequence IndexContent(final Module modul) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<html>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<head>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</head>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<body>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</body>");
    _builder.newLine();
    _builder.append("</html>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence genGetList() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @return");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @since");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("**/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static function &getList($params = null)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* placeholder \"<>\" are to be replaced");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("JModelLegacy::addIncludePath(JPATH_ROOT . ");
    _builder.append(this.modelPath, "\t");
    _builder.append(", ");
    _builder.append(this.modelOfComponent, "\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// $app = JFactory::getApplictation();");
    _builder.newLine();
    {
      PageReference _pageRef = this.extMod.getPageRef();
      Component _pagescr = _pageRef.getPagescr();
      boolean _notEquals = (!Objects.equal(_pagescr, null));
      if (_notEquals) {
        _builder.append("\t\t    ");
        _builder.append("$model = JModelLegacy::getInstance(\'");
        PageReference _pageRef_1 = this.extMod.getPageRef();
        Page _page = _pageRef_1.getPage();
        String _name = _page.getName();
        _builder.append(_name, "\t\t    ");
        _builder.append("\', ");
        _builder.append(this.modelOfComponent2, "\t\t    ");
        _builder.append(", array(\'ignore_request\' => true));");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t    ");
        _builder.newLine();
      } else {
        _builder.append("$model = JModelLegacy::getInstance(\'<type>\', <modelOfComponent>, array(\'ignore_request\' => true));");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.newLine();
      }
    }
    _builder.append("\t\t");
    _builder.append("$state = $params->get(\'state\');");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if(!empty($state))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$model->setState(\'filter.state\', $state);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$search = $params->get(\'search\');");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if(!empty($search))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$model->setState(\'filter.search\', $search);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$created_by = $params->get(\'created_by\');");
    _builder.newLine();
    _builder.append("\t\t ");
    _builder.append("if(!empty($created_by))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$model->setState(\'filter.search\',$created_by);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$ordering = $params->get(\'ordering\');");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if(!empty($ordering))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$model->setState(\'list.ordering\',$ordering);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$direction = $params->get(\'direction\');");
    _builder.newLine();
    _builder.append("\t\t ");
    _builder.append("if(!empty($direction))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$model->setState(\'list.direction\', $direction);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$start = $params->get(\'start\');");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if(!empty($start))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$model->setState(\'list.start\', $start);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$limit = $params->get(\'limit\');");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if(!empty($limit))");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$model->setState(\'list.limit\', $limit);");
    _builder.newLine();
    {
      boolean _notEquals_1 = (!Objects.equal(this.dynpage, null));
      if (_notEquals_1) {
        {
          EList<ExtendedAttribute> _extendFiltersList = this.dynpage.getExtendFiltersList();
          for(final ExtendedAttribute attr : _extendFiltersList) {
            _builder.append("\t\t");
            _builder.append("$");
            String _name_1 = attr.getName();
            String _lowerCase = _name_1.toLowerCase();
            _builder.append(_lowerCase, "\t\t");
            _builder.append(" = $params->get(\'");
            String _name_2 = attr.getName();
            String _lowerCase_1 = _name_2.toLowerCase();
            _builder.append(_lowerCase_1, "\t\t");
            _builder.append("\');");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("if(!empty($");
            String _name_3 = attr.getName();
            String _lowerCase_2 = _name_3.toLowerCase();
            _builder.append(_lowerCase_2, "\t\t");
            _builder.append(" ))");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("$model->setState(\'filter.");
            String _name_4 = attr.getName();
            String _lowerCase_3 = _name_4.toLowerCase();
            _builder.append(_lowerCase_3, "\t\t");
            _builder.append("\', $");
            String _name_5 = attr.getName();
            String _lowerCase_4 = _name_5.toLowerCase();
            _builder.append(_lowerCase_4, "\t\t");
            _builder.append(" );");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.append("$items = $model->getItems();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t ");
    _builder.append("return $items;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}\t");
    _builder.newLine();
    return _builder;
  }
}
