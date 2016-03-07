package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair;
import de.thm.icampus.joomdd.ejsl.eJSL.Language;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator.AbstractExtensionGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class LanguageGenerator extends AbstractExtensionGenerator {
  public LanguageGenerator(final IFileSystemAccess fileAccess) {
    this.setFsa(fileAccess);
  }
  
  public void genComponentLanguage(final ExtendedComponent component, final String root) {
    EList<Language> _languages = component.getLanguages();
    for (final Language lang : _languages) {
      {
        final String ldir = lang.getName();
        IFileSystemAccess _fsa = this.getFsa();
        String _name = component.getName();
        String _nameExtensionBind = Slug.nameExtensionBind("com", _name);
        String _lowerCase = _nameExtensionBind.toLowerCase();
        String _plus = ((((((root + "/language/site/") + ldir) + "/") + ldir) + ".") + _lowerCase);
        String _plus_1 = (_plus + ".ini");
        EList<ExtendedPageReference> _frontEndExtendedPagerefence = component.getFrontEndExtendedPagerefence();
        CharSequence _languageFileContent = this.languageFileContent(component, lang, _frontEndExtendedPagerefence);
        _fsa.generateFile(_plus_1, _languageFileContent);
        IFileSystemAccess _fsa_1 = this.getFsa();
        String _name_1 = component.getName();
        String _nameExtensionBind_1 = Slug.nameExtensionBind("com", _name_1);
        String _lowerCase_1 = _nameExtensionBind_1.toLowerCase();
        String _plus_2 = ((((((root + "/language/site/") + ldir) + "/") + ldir) + ".") + _lowerCase_1);
        String _plus_3 = (_plus_2 + ".sys.ini");
        EList<ExtendedPageReference> _frontEndExtendedPagerefence_1 = component.getFrontEndExtendedPagerefence();
        CharSequence _languageFileContent_1 = this.languageFileContent(component, lang, _frontEndExtendedPagerefence_1);
        _fsa_1.generateFile(_plus_3, _languageFileContent_1);
        IFileSystemAccess _fsa_2 = this.getFsa();
        String _name_2 = component.getName();
        String _nameExtensionBind_2 = Slug.nameExtensionBind("com", _name_2);
        String _lowerCase_2 = _nameExtensionBind_2.toLowerCase();
        String _plus_4 = ((((((root + "/language/admin/") + ldir) + "/") + ldir) + ".") + _lowerCase_2);
        String _plus_5 = (_plus_4 + ".ini");
        EList<ExtendedPageReference> _backEndExtendedPagerefence = component.getBackEndExtendedPagerefence();
        CharSequence _languageFileContent_2 = this.languageFileContent(component, lang, _backEndExtendedPagerefence);
        _fsa_2.generateFile(_plus_5, _languageFileContent_2);
        IFileSystemAccess _fsa_3 = this.getFsa();
        String _name_3 = component.getName();
        String _nameExtensionBind_3 = Slug.nameExtensionBind("com", _name_3);
        String _lowerCase_3 = _nameExtensionBind_3.toLowerCase();
        String _plus_6 = ((((((root + "/language/admin/") + ldir) + "/") + ldir) + ".") + _lowerCase_3);
        String _plus_7 = (_plus_6 + ".sys.ini");
        EList<ExtendedPageReference> _backEndExtendedPagerefence_1 = component.getBackEndExtendedPagerefence();
        CharSequence _languageFileContent_3 = this.languageFileContent(component, lang, _backEndExtendedPagerefence_1);
        _fsa_3.generateFile(_plus_7, _languageFileContent_3);
      }
    }
  }
  
  private CharSequence languageFileContent(final ExtendedComponent com, final Language language, final EList<ExtendedPageReference> pagerefList) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = com.getName();
    String _nameExtensionBind = Slug.nameExtensionBind("com", _name);
    String _upperCase = _nameExtensionBind.toUpperCase();
    _builder.append(_upperCase, "");
    _builder.append("_LABEL=\"");
    String _name_1 = com.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    String _name_2 = com.getName();
    String _nameExtensionBind_1 = Slug.nameExtensionBind("com", _name_2);
    String _upperCase_1 = _nameExtensionBind_1.toUpperCase();
    _builder.append(_upperCase_1, "");
    _builder.append("_DESC=\"");
    String _name_3 = com.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_3);
    _builder.append(_firstUpper_1, "");
    _builder.append(" ");
    Manifestation _manifest = com.getManifest();
    String _description = _manifest.getDescription();
    _builder.append(_description, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    String _name_4 = com.getName();
    String _nameExtensionBind_2 = Slug.nameExtensionBind("com", _name_4);
    String _upperCase_2 = _nameExtensionBind_2.toUpperCase();
    _builder.append(_upperCase_2, "");
    _builder.append("=\"");
    String _name_5 = com.getName();
    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_5);
    _builder.append(_firstUpper_2, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    String _name_6 = com.getName();
    String _nameExtensionBind_3 = Slug.nameExtensionBind("com", _name_6);
    String _upperCase_3 = _nameExtensionBind_3.toUpperCase();
    _builder.append(_upperCase_3, "");
    _builder.append("_HOME=\"Home\"");
    _builder.newLineIfNotEmpty();
    String _name_7 = com.getName();
    String _nameExtensionBind_4 = Slug.nameExtensionBind("com", _name_7);
    String _upperCase_4 = _nameExtensionBind_4.toUpperCase();
    _builder.append(_upperCase_4, "");
    _builder.append("_FORM_LBL_NONE_ID=\"ID\"");
    _builder.newLineIfNotEmpty();
    String _name_8 = com.getName();
    String _nameExtensionBind_5 = Slug.nameExtensionBind("com", _name_8);
    String _upperCase_5 = _nameExtensionBind_5.toUpperCase();
    _builder.append(_upperCase_5, "");
    _builder.append("_FORM_LBL_NONE_CHECKED_OUT=\"Checked out\"");
    _builder.newLineIfNotEmpty();
    String _name_9 = com.getName();
    String _nameExtensionBind_6 = Slug.nameExtensionBind("com", _name_9);
    String _upperCase_6 = _nameExtensionBind_6.toUpperCase();
    _builder.append(_upperCase_6, "");
    _builder.append("_FORM_LBL_NONE_CHECKED_OUT_TIME=\"Checked out Time\"");
    _builder.newLineIfNotEmpty();
    String _name_10 = com.getName();
    String _nameExtensionBind_7 = Slug.nameExtensionBind("com", _name_10);
    String _upperCase_7 = _nameExtensionBind_7.toUpperCase();
    _builder.append(_upperCase_7, "");
    _builder.append("_FORM_LBL_NONE_ORDERING=\"Ordering\"");
    _builder.newLineIfNotEmpty();
    String _name_11 = com.getName();
    String _nameExtensionBind_8 = Slug.nameExtensionBind("com", _name_11);
    String _upperCase_8 = _nameExtensionBind_8.toUpperCase();
    _builder.append(_upperCase_8, "");
    _builder.append("_FORM_LBL_NONE_CREATED_BY=\"Created By\"");
    _builder.newLineIfNotEmpty();
    String _name_12 = com.getName();
    String _nameExtensionBind_9 = Slug.nameExtensionBind("com", _name_12);
    String _upperCase_9 = _nameExtensionBind_9.toUpperCase();
    _builder.append(_upperCase_9, "");
    _builder.append("_FORM_LBL_NONE_STATE=\"state\"");
    _builder.newLineIfNotEmpty();
    String _name_13 = com.getName();
    String _nameExtensionBind_10 = Slug.nameExtensionBind("com", _name_13);
    String _upperCase_10 = _nameExtensionBind_10.toUpperCase();
    _builder.append(_upperCase_10, "");
    _builder.append("_JSTATUS=\"state\"");
    _builder.newLineIfNotEmpty();
    String _name_14 = com.getName();
    String _nameExtensionBind_11 = Slug.nameExtensionBind("com", _name_14);
    String _upperCase_11 = _nameExtensionBind_11.toUpperCase();
    _builder.append(_upperCase_11, "");
    _builder.append("_JFIELD_PUBLISHED_DESC=\"State Description\"");
    _builder.newLineIfNotEmpty();
    String _name_15 = com.getName();
    String _nameExtensionBind_12 = Slug.nameExtensionBind("com", _name_15);
    String _upperCase_12 = _nameExtensionBind_12.toUpperCase();
    _builder.append(_upperCase_12, "");
    _builder.append("_EDIT_ITEM=\"Edit\"");
    _builder.newLineIfNotEmpty();
    String _name_16 = com.getName();
    String _nameExtensionBind_13 = Slug.nameExtensionBind("com", _name_16);
    String _upperCase_13 = _nameExtensionBind_13.toUpperCase();
    _builder.append(_upperCase_13, "");
    _builder.append("_DELETE_ITEM=\"Delete\"");
    _builder.newLineIfNotEmpty();
    String _name_17 = com.getName();
    String _nameExtensionBind_14 = Slug.nameExtensionBind("com", _name_17);
    String _upperCase_14 = _nameExtensionBind_14.toUpperCase();
    _builder.append(_upperCase_14, "");
    _builder.append("_ADD_ITEM=\"Add\"");
    _builder.newLineIfNotEmpty();
    String _name_18 = com.getName();
    String _nameExtensionBind_15 = Slug.nameExtensionBind("com", _name_18);
    String _upperCase_15 = _nameExtensionBind_15.toUpperCase();
    _builder.append(_upperCase_15, "");
    _builder.append("_ITEM_SAVED_SUCCESSFULLY=\"The data are saved sucessfully\"");
    _builder.newLineIfNotEmpty();
    String _name_19 = com.getName();
    String _nameExtensionBind_16 = Slug.nameExtensionBind("com", _name_19);
    String _upperCase_16 = _nameExtensionBind_16.toUpperCase();
    _builder.append(_upperCase_16, "");
    _builder.append("_ITEM_DELETED_SUCCESSFULLY=\"The data are deleted sucessfully\"");
    _builder.newLineIfNotEmpty();
    String _name_20 = com.getName();
    String _nameExtensionBind_17 = Slug.nameExtensionBind("com", _name_20);
    String _upperCase_17 = _nameExtensionBind_17.toUpperCase();
    _builder.append(_upperCase_17, "");
    _builder.append("_DELETE_MESSAGE=\"Do you want to delete the Data?\"");
    _builder.newLineIfNotEmpty();
    String _name_21 = com.getName();
    String _nameExtensionBind_18 = Slug.nameExtensionBind("com", _name_21);
    String _upperCase_18 = _nameExtensionBind_18.toUpperCase();
    _builder.append(_upperCase_18, "");
    _builder.append("_N_ITEMS_PUBLISHED=\"The data are published sucessfully\"");
    _builder.newLineIfNotEmpty();
    String _name_22 = com.getName();
    String _nameExtensionBind_19 = Slug.nameExtensionBind("com", _name_22);
    String _upperCase_19 = _nameExtensionBind_19.toUpperCase();
    _builder.append(_upperCase_19, "");
    _builder.append("_TEMPLATE_LAYOUT=\"Template Layout\"");
    _builder.newLineIfNotEmpty();
    String _name_23 = com.getName();
    String _nameExtensionBind_20 = Slug.nameExtensionBind("com", _name_23);
    String _upperCase_20 = _nameExtensionBind_20.toUpperCase();
    _builder.append(_upperCase_20, "");
    _builder.append("_TEMPLATE_LAYOUT_DESC=\"Choice a Layout for the Indexpage\"");
    _builder.newLineIfNotEmpty();
    _builder.append("JTEMPLATE_LAYOUT_LIST=\"List Layout\"");
    _builder.newLine();
    _builder.append("JTEMPLATE_LAYOUT_TABLE=\"Table layout\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("JPUBLISHED=\"published\"");
    _builder.newLine();
    _builder.append("JUNPUBLISHED=\"unpublished\"");
    _builder.newLine();
    _builder.append("JARCHIVED=\"archived\"");
    _builder.newLine();
    _builder.append("JTRASHED=\"trashed\"");
    _builder.newLine();
    _builder.append("JOPTION_SELECT_CREATED_BY = \"Select a user\"");
    _builder.newLine();
    _builder.append("JFIELD_RULES_LABEL= \"Rules\"");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    {
      for(final ExtendedPageReference pag : pagerefList) {
        String _name_24 = com.getName();
        String _nameExtensionBind_21 = Slug.nameExtensionBind("com", _name_24);
        String _upperCase_21 = _nameExtensionBind_21.toUpperCase();
        _builder.append(_upperCase_21, "");
        _builder.append("_TITLE_");
        Page _page = pag.getPage();
        String _name_25 = _page.getName();
        String _slugify = Slug.slugify(_name_25);
        String _upperCase_22 = _slugify.toUpperCase();
        _builder.append(_upperCase_22, "");
        _builder.append("=\"");
        Page _page_1 = pag.getPage();
        String _name_26 = _page_1.getName();
        String _firstUpper_3 = StringExtensions.toFirstUpper(_name_26);
        _builder.append(_firstUpper_3, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        String _name_27 = com.getName();
        String _nameExtensionBind_22 = Slug.nameExtensionBind("com", _name_27);
        String _upperCase_23 = _nameExtensionBind_22.toUpperCase();
        _builder.append(_upperCase_23, "");
        _builder.append("_VIEW_");
        Page _page_2 = pag.getPage();
        String _name_28 = _page_2.getName();
        String _slugify_1 = Slug.slugify(_name_28);
        String _upperCase_24 = _slugify_1.toUpperCase();
        _builder.append(_upperCase_24, "");
        _builder.append("_TITLE=\"");
        Page _page_3 = pag.getPage();
        String _name_29 = _page_3.getName();
        String _firstUpper_4 = StringExtensions.toFirstUpper(_name_29);
        _builder.append(_firstUpper_4, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        String _name_30 = com.getName();
        String _nameExtensionBind_23 = Slug.nameExtensionBind("com", _name_30);
        String _upperCase_25 = _nameExtensionBind_23.toUpperCase();
        _builder.append(_upperCase_25, "");
        _builder.append("_VIEW_");
        Page _page_4 = pag.getPage();
        String _name_31 = _page_4.getName();
        String _slugify_2 = Slug.slugify(_name_31);
        String _upperCase_26 = _slugify_2.toUpperCase();
        _builder.append(_upperCase_26, "");
        _builder.append("_DESC=\"");
        Page _page_5 = pag.getPage();
        String _name_32 = _page_5.getName();
        String _firstUpper_5 = StringExtensions.toFirstUpper(_name_32);
        _builder.append(_firstUpper_5, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        String _name_33 = com.getName();
        String _nameExtensionBind_24 = Slug.nameExtensionBind("com", _name_33);
        String _upperCase_27 = _nameExtensionBind_24.toUpperCase();
        _builder.append(_upperCase_27, "");
        _builder.append("_ALIAS_");
        Page _page_6 = pag.getPage();
        String _name_34 = _page_6.getName();
        String _upperCase_28 = _name_34.toUpperCase();
        _builder.append(_upperCase_28, "");
        _builder.append("=\"");
        Page _page_7 = pag.getPage();
        String _name_35 = _page_7.getName();
        String _firstUpper_6 = StringExtensions.toFirstUpper(_name_35);
        _builder.append(_firstUpper_6, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      final Function1<ExtendedPageReference, Boolean> _function = (ExtendedPageReference t) -> {
        ExtendedPage _extendedPage = t.getExtendedPage();
        ExtendedDynamicPage _extendedDynamicPageInstance = _extendedPage.getExtendedDynamicPageInstance();
        return Boolean.valueOf((!Objects.equal(_extendedDynamicPageInstance, null)));
      };
      Iterable<ExtendedPageReference> _filter = IterableExtensions.<ExtendedPageReference>filter(pagerefList, _function);
      for(final ExtendedPageReference dynamicPagereference : _filter) {
        _builder.append("\t");
        _builder.newLine();
        {
          ExtendedPage _extendedPage = dynamicPagereference.getExtendedPage();
          ExtendedDynamicPage _extendedDynamicPageInstance = _extendedPage.getExtendedDynamicPageInstance();
          EList<ExtendedEntity> _extendedEntityList = _extendedDynamicPageInstance.getExtendedEntityList();
          for(final ExtendedEntity ent : _extendedEntityList) {
            {
              EList<ExtendedAttribute> _allattribute = ent.getAllattribute();
              for(final ExtendedAttribute attr : _allattribute) {
                String _name_36 = com.getName();
                String _nameExtensionBind_25 = Slug.nameExtensionBind("com", _name_36);
                String _upperCase_29 = _nameExtensionBind_25.toUpperCase();
                _builder.append(_upperCase_29, "");
                _builder.append("_FORM_LBL_");
                String _name_37 = ent.getName();
                String _slugify_3 = Slug.slugify(_name_37);
                String _upperCase_30 = _slugify_3.toUpperCase();
                _builder.append(_upperCase_30, "");
                _builder.append("_");
                String _name_38 = attr.getName();
                String _slugify_4 = Slug.slugify(_name_38);
                String _upperCase_31 = _slugify_4.toUpperCase();
                _builder.append(_upperCase_31, "");
                _builder.append("=\"");
                String _name_39 = attr.getName();
                String _slugify_5 = Slug.slugify(_name_39);
                String _firstUpper_7 = StringExtensions.toFirstUpper(_slugify_5);
                _builder.append(_firstUpper_7, "");
                _builder.append("\"");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    {
      final Function1<ExtendedPageReference, Boolean> _function_1 = (ExtendedPageReference t) -> {
        ExtendedPage _extendedPage_1 = t.getExtendedPage();
        ExtendedDynamicPage _extendedDynamicPageInstance_1 = _extendedPage_1.getExtendedDynamicPageInstance();
        return Boolean.valueOf((!Objects.equal(_extendedDynamicPageInstance_1, null)));
      };
      Iterable<ExtendedPageReference> _filter_1 = IterableExtensions.<ExtendedPageReference>filter(pagerefList, _function_1);
      for(final ExtendedPageReference dynamicPagereference_1 : _filter_1) {
        {
          ExtendedPage _extendedPage_1 = dynamicPagereference_1.getExtendedPage();
          ExtendedDynamicPage _extendedDynamicPageInstance_1 = _extendedPage_1.getExtendedDynamicPageInstance();
          EList<ExtendedAttribute> _extendFiltersList = _extendedDynamicPageInstance_1.getExtendFiltersList();
          for(final ExtendedAttribute attr_1 : _extendFiltersList) {
            _builder.append("JOPTION_SELECT_");
            String _name_40 = attr_1.getName();
            String _slugify_6 = Slug.slugify(_name_40);
            String _upperCase_32 = _slugify_6.toUpperCase();
            _builder.append(_upperCase_32, "");
            _builder.append("=\"Select a ");
            String _name_41 = attr_1.getName();
            String _slugify_7 = Slug.slugify(_name_41);
            String _firstUpper_8 = StringExtensions.toFirstUpper(_slugify_7);
            _builder.append(_firstUpper_8, "");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("   ");
        String _name_42 = com.getName();
        String _nameExtensionBind_26 = Slug.nameExtensionBind("com", _name_42);
        String _upperCase_33 = _nameExtensionBind_26.toUpperCase();
        _builder.append(_upperCase_33, "   ");
        _builder.append("_");
        ExtendedPage _extendedPage_2 = dynamicPagereference_1.getExtendedPage();
        String _name_43 = _extendedPage_2.getName();
        String _upperCase_34 = _name_43.toUpperCase();
        _builder.append(_upperCase_34, "   ");
        _builder.append("_ACTIONS=\"Actions\"");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<KeyValuePair> _keyvaluepairs = language.getKeyvaluepairs();
      for(final KeyValuePair e : _keyvaluepairs) {
        String _name_44 = e.getName();
        String _generateKeysName = Slug.generateKeysName(com, _name_44);
        _builder.append(_generateKeysName, "");
        _builder.append("=\"");
        String _value = e.getValue();
        _builder.append(_value, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  @Override
  public CharSequence generate() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void genModuletLanguage(final ExtendedModule extmod, final String root) {
    String _name = extmod.getName();
    String _nameExtensionBind = Slug.nameExtensionBind("mod", _name);
    String name = _nameExtensionBind.toLowerCase();
    EList<Language> _languages = extmod.getLanguages();
    for (final Language lang : _languages) {
      {
        final String ldir = lang.getName();
        IFileSystemAccess _fsa = this.getFsa();
        EList<KeyValuePair> _keyvaluepairs = lang.getKeyvaluepairs();
        CharSequence _languageModuleFileGen = this.languageModuleFileGen(extmod, _keyvaluepairs);
        _fsa.generateFile((((((((root + "/language/") + ldir) + "/") + ldir) + ".") + name) + ".ini"), _languageModuleFileGen);
        IFileSystemAccess _fsa_1 = this.getFsa();
        EList<KeyValuePair> _keyvaluepairs_1 = lang.getKeyvaluepairs();
        CharSequence _languageModuleFileGen_1 = this.languageModuleFileGen(extmod, _keyvaluepairs_1);
        _fsa_1.generateFile((((((((root + "/language/") + ldir) + "/") + ldir) + ".") + name) + ".sys.ini"), _languageModuleFileGen_1);
      }
    }
  }
  
  public CharSequence languageModuleFileGen(final ExtendedModule extmod, final EList<KeyValuePair> list) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = extmod.getName();
    String _nameExtensionBind = Slug.nameExtensionBind("mod", _name);
    String _upperCase = _nameExtensionBind.toUpperCase();
    _builder.append(_upperCase, "");
    _builder.append("_LABEL=\"");
    String _name_1 = extmod.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    String _name_2 = extmod.getName();
    String _nameExtensionBind_1 = Slug.nameExtensionBind("mod", _name_2);
    String _upperCase_1 = _nameExtensionBind_1.toUpperCase();
    _builder.append(_upperCase_1, "");
    _builder.append("_DESC=\"");
    String _name_3 = extmod.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_3);
    _builder.append(_firstUpper_1, "");
    _builder.append(" ");
    Manifestation _manifest = extmod.getManifest();
    String _description = _manifest.getDescription();
    _builder.append(_description, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("COM_MODULES_FILTER_FIELDSET_LABEL = \"Filter\"");
    _builder.newLine();
    _builder.append("MOD_");
    String _name_4 = extmod.getName();
    String _upperCase_2 = _name_4.toUpperCase();
    _builder.append(_upperCase_2, "");
    _builder.append("_ORDERING = \"Ordering\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_5 = extmod.getName();
    String _upperCase_3 = _name_5.toUpperCase();
    _builder.append(_upperCase_3, "");
    _builder.append("_JFIELD_ORDERING_DESC = \"Ordering description\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_6 = extmod.getName();
    String _upperCase_4 = _name_6.toUpperCase();
    _builder.append(_upperCase_4, "");
    _builder.append("_DIRECTION = \"Direction\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_7 = extmod.getName();
    String _upperCase_5 = _name_7.toUpperCase();
    _builder.append(_upperCase_5, "");
    _builder.append("_JFIELD_DIRECTION_DESC = \"Sort the result in a Direction\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_8 = extmod.getName();
    String _upperCase_6 = _name_8.toUpperCase();
    _builder.append(_upperCase_6, "");
    _builder.append("_ASC=\"ASC\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_9 = extmod.getName();
    String _upperCase_7 = _name_9.toUpperCase();
    _builder.append(_upperCase_7, "");
    _builder.append("_DESC=\"DESC\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_10 = extmod.getName();
    String _upperCase_8 = _name_10.toUpperCase();
    _builder.append(_upperCase_8, "");
    _builder.append("_START_LABEL=\"Start Index\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_11 = extmod.getName();
    String _upperCase_9 = _name_11.toUpperCase();
    _builder.append(_upperCase_9, "");
    _builder.append("_START_DESC=\"The index of First data Item\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_12 = extmod.getName();
    String _upperCase_10 = _name_12.toUpperCase();
    _builder.append(_upperCase_10, "");
    _builder.append("_LIMIT_LABEL=\"Limit\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_13 = extmod.getName();
    String _upperCase_11 = _name_13.toUpperCase();
    _builder.append(_upperCase_11, "");
    _builder.append("_LIMIT_DESC=\"Limit the number of Data in view\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_14 = extmod.getName();
    String _upperCase_12 = _name_14.toUpperCase();
    _builder.append(_upperCase_12, "");
    _builder.append("_SEARCH_LABEL=\"Search\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_15 = extmod.getName();
    String _upperCase_13 = _name_15.toUpperCase();
    _builder.append(_upperCase_13, "");
    _builder.append("_SEARCH_DESC=\"Search Data\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_16 = extmod.getName();
    String _upperCase_14 = _name_16.toUpperCase();
    _builder.append(_upperCase_14, "");
    _builder.append("_JSTATUS=\"Status\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_17 = extmod.getName();
    String _upperCase_15 = _name_17.toUpperCase();
    _builder.append(_upperCase_15, "");
    _builder.append("_JFIELD_PUBLISHED_DESC=\"Status\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_18 = extmod.getName();
    String _upperCase_16 = _name_18.toUpperCase();
    _builder.append(_upperCase_16, "");
    _builder.append("_FILTER_CREATED_BY=\"Created by\"");
    _builder.newLineIfNotEmpty();
    _builder.append("MOD_");
    String _name_19 = extmod.getName();
    String _upperCase_17 = _name_19.toUpperCase();
    _builder.append(_upperCase_17, "");
    _builder.append("_FILTER_CREATED_BY=\"Created by\"");
    _builder.newLineIfNotEmpty();
    _builder.append("JOPTION_SELECT_CREATED_BY=\"select a user\"");
    _builder.newLine();
    {
      ExtendedPageReference _extendedPageReference = extmod.getExtendedPageReference();
      ExtendedPage _extendedPage = _extendedPageReference.getExtendedPage();
      ExtendedDynamicPage _extendedDynamicPageInstance = _extendedPage.getExtendedDynamicPageInstance();
      EList<ExtendedAttribute> _extendFiltersList = _extendedDynamicPageInstance.getExtendFiltersList();
      for(final ExtendedAttribute attr : _extendFiltersList) {
        _builder.append("MOD_");
        String _name_20 = extmod.getName();
        String _upperCase_18 = _name_20.toUpperCase();
        _builder.append(_upperCase_18, "");
        _builder.append("_FORM_LBL_");
        String _name_21 = attr.getName();
        String _upperCase_19 = _name_21.toUpperCase();
        _builder.append(_upperCase_19, "");
        _builder.append(" = \"");
        String _name_22 = attr.getName();
        String _firstUpper_2 = StringExtensions.toFirstUpper(_name_22);
        _builder.append(_firstUpper_2, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("MOD_");
        String _name_23 = extmod.getName();
        String _upperCase_20 = _name_23.toUpperCase();
        _builder.append(_upperCase_20, "");
        _builder.append("_FILTER_");
        String _name_24 = attr.getName();
        String _upperCase_21 = _name_24.toUpperCase();
        _builder.append(_upperCase_21, "");
        _builder.append(" = \"");
        String _name_25 = attr.getName();
        String _firstUpper_3 = StringExtensions.toFirstUpper(_name_25);
        _builder.append(_firstUpper_3, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("MOD_");
        String _name_26 = extmod.getName();
        String _upperCase_22 = _name_26.toUpperCase();
        _builder.append(_upperCase_22, "");
        _builder.append("_FILTER_");
        String _name_27 = attr.getName();
        String _upperCase_23 = _name_27.toUpperCase();
        _builder.append(_upperCase_23, "");
        _builder.append("_DESC = \"");
        String _name_28 = attr.getName();
        String _firstUpper_4 = StringExtensions.toFirstUpper(_name_28);
        _builder.append(_firstUpper_4, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("JOPTION_SELECT_");
        String _name_29 = attr.getName();
        String _upperCase_24 = _name_29.toUpperCase();
        _builder.append(_upperCase_24, "");
        _builder.append("=\"select a ");
        String _name_30 = attr.getName();
        _builder.append(_name_30, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    return _builder;
  }
}
