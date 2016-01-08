/**
 * generated by iCampus (JooMDD team) 2.9.1
 */
package de.thm.icampus.joomdd.ejsl.validation;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.Author;
import de.thm.icampus.joomdd.ejsl.eJSL.BackendSection;
import de.thm.icampus.joomdd.ejsl.eJSL.Component;
import de.thm.icampus.joomdd.ejsl.eJSL.Datatype;
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage;
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel;
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.Extension;
import de.thm.icampus.joomdd.ejsl.eJSL.ExtensionPackage;
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair;
import de.thm.icampus.joomdd.ejsl.eJSL.Language;
import de.thm.icampus.joomdd.ejsl.eJSL.Library;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.Method;
import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.eJSL.PageReference;
import de.thm.icampus.joomdd.ejsl.eJSL.Parameter;
import de.thm.icampus.joomdd.ejsl.eJSL.Reference;
import de.thm.icampus.joomdd.ejsl.eJSL.Section;
import de.thm.icampus.joomdd.ejsl.validation.AbstractEJSLValidator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.validation.Check;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@SuppressWarnings("all")
public class EJSLValidator extends AbstractEJSLValidator {
  public final static String AMBIGUOUS_ATTRIBUTE_NAME = "ambiguousAttrName";
  
  public final static String AMBIGUOUS_AUTHOR = "ambiguousAuthor";
  
  public final static String AMBIGUOUS_CLASS = "ambiguousClass";
  
  public final static String AMBIGUOUS_DATATYPE = "ambiguousDatatype";
  
  public final static String AMBIGUOUS_ENTITY = "ambiguousEntity";
  
  public final static String AMBIGUOUS_EXTENSION = "ambiguousExt";
  
  public final static String AMBIGUOUS_GLOBALPARAMETER = "ambiguousGlobalparam";
  
  public final static String AMBIGUOUS_KEY = "ambiguousKey";
  
  public final static String AMBIGUOUS_LANGUAGE = "ambiguousLanguage";
  
  public final static String AMBIGUOUS_LOCALPARAMETER = "ambiguousLocalparam";
  
  public final static String AMBIGUOUS_METHOD = "ambiguousMethod";
  
  public final static String AMBIGUOUS_PAGE = "ambiguousPage";
  
  public final static String ENTITY_USED_MULTIPLE_TIMES = "entityUsedMultipleTimes";
  
  public final static String EXTPACKAGE_CONTAINS_EXTPACKAGE = "extPackageContainsExtPackage";
  
  public final static String INVALID_AUTHOR_EMAIL = "invalidAuthorEmail";
  
  public final static String INVALID_AUTHOR_URL = "invalidAuthorUrl";
  
  public final static String MORE_THAN_ONE_BACKEND = "moreThanOneBackend";
  
  public final static String MORE_THAN_ONE_FRONTEND = "moreThanOneFrontend";
  
  public final static String PAGE_USED_MULTIPLE_TIMES = "pageUsedMultipleTimes";
  
  public final static String MISSING_PRIMARY_ATTRIBUTE = "missingPrimaryAttribute";
  
  public final static String NOT_PRIMARY_REFERENCE = "notPrimaryReference";
  
  public final static String AMBIGUOUS_TABLE_COLUMN_ATTRIBUTE = "ambiguousTableColumnAttribute";
  
  public final static String AMBIGUOUS_FILTER_ATTRIBUTE = "ambiguousFilterAttribute";
  
  public final static String COLUMNS_USED_MULTIPLE_TIMES = "columnsUsedMultipleTimes";
  
  public final static String FILTER_USED_MULTIPLE_TIMES = "filterUsedMultipleTimes";
  
  /**
   * A domain consists of one or more domain parts. A domain part may contain
   * any letter (Unicode), number (0-9) and dash (-) but may not start and
   * end with a dash (constraint not checked for simplicity). Domain parts
   * are separated by dot (.).
   */
  public final static String domainPattern = "([\\p{L}0-9-]+\\.)+[\\p{L}0-9]+";
  
  /**
   * An e-mail address consists of a user and a domain part which are
   * separated by an at-sign (@). The user part may contain any letter
   * (Unicode), number (0-9), dot (.), dash (-), underscore (_) and plus (+).
   */
  public final static Pattern emailPattern = Pattern.compile(("[\\p{L}0-9._+-]+?@" + EJSLValidator.domainPattern));
  
  /**
   * An HTTP URL consists of a scheme and a domain. The scheme is limited to
   * http:// and https://
   */
  public final static Pattern httpUrlPattern = Pattern.compile((("https?://" + EJSLValidator.domainPattern) + "(/.*)?"));
  
  /**
   * Validates if the defined Datatypes of the model have different/unique names
   */
  @Check
  public void checkDatatypesAreUnique(final EJSLModel model) {
    HashSet<String> types = new HashSet<String>();
    EList<Datatype> _datatypes = model.getDatatypes();
    for (final Datatype type : _datatypes) {
      String _name = type.getName();
      boolean _add = types.add(_name);
      boolean _not = (!_add);
      if (_not) {
        this.error(
          "Datatype must be unique.", type, 
          EJSLPackage.Literals.DATATYPE__NAME, 
          EJSLValidator.AMBIGUOUS_DATATYPE);
      }
    }
  }
  
  /**
   * Checks if the existing entities of the model have different/unique names
   */
  @Check
  public void checkEntitiesAreUnique(final EJSLModel model) {
    HashSet<String> entities = new HashSet<String>();
    EList<Entity> _entities = model.getEntities();
    for (final Entity entity : _entities) {
      String _name = entity.getName();
      boolean _add = entities.add(_name);
      boolean _not = (!_add);
      if (_not) {
        this.error(
          "Entity names must be unique.", entity, 
          EJSLPackage.Literals.ENTITY__NAME, 
          EJSLValidator.AMBIGUOUS_ENTITY);
      }
    }
  }
  
  /**
   * Checks if the existing attributes from the available entities have different/unique names
   */
  @Check
  public void checkEntityAttributesAreUnique(final Entity entity) {
    HashSet<String> attributes = new HashSet<String>();
    EList<Attribute> _attributes = entity.getAttributes();
    for (final Attribute attribute : _attributes) {
      String _name = attribute.getName();
      boolean _add = attributes.add(_name);
      boolean _not = (!_add);
      if (_not) {
        this.error(
          "Attribute names must be unique.", attribute, 
          EJSLPackage.Literals.ATTRIBUTE__NAME, 
          EJSLValidator.AMBIGUOUS_ATTRIBUTE_NAME);
      }
    }
  }
  
  /**
   * Checks if the existing pages of the model have different/unique names
   */
  @Check
  public void checkPagesAreUnique(final EJSLModel model) {
    HashSet<String> pages = new HashSet<String>();
    EList<Page> _pages = model.getPages();
    for (final Page page : _pages) {
      String _name = page.getName();
      boolean _add = pages.add(_name);
      boolean _not = (!_add);
      if (_not) {
        this.error(
          "Page names must be unique.", page, 
          EJSLPackage.Literals.PAGE__NAME, 
          EJSLValidator.AMBIGUOUS_PAGE);
      }
    }
  }
  
  /**
   * Checks if the existing component conatains max. one backend and one frontend section
   */
  @Check
  public void checkComponentHasOnlyOneSectionInstancePerClass(final Component component) {
    boolean hasBackend = false;
    boolean hasFrontend = false;
    int i = 0;
    EList<Section> _sections = component.getSections();
    for (final Section section : _sections) {
      {
        if ((section instanceof BackendSection)) {
          if (hasBackend) {
            this.error(
              "Component must not have more than one backend.", 
              EJSLPackage.Literals.COMPONENT__SECTIONS, i, 
              EJSLValidator.MORE_THAN_ONE_BACKEND);
          }
          hasBackend = true;
        } else {
          if (hasFrontend) {
            this.error(
              "Component must not have more than one frontend.", 
              EJSLPackage.Literals.COMPONENT__SECTIONS, i, 
              EJSLValidator.MORE_THAN_ONE_FRONTEND);
          }
          hasFrontend = true;
        }
        i++;
      }
    }
  }
  
  /**
   * Checks if the languages of an Extension have different/unique names
   */
  @Check
  public void checkComponentLanguageIsUnique(final Extension ext) {
    HashSet<String> langs = new HashSet<String>();
    EList<Language> _languages = ext.getLanguages();
    for (final Language lang : _languages) {
      String _name = lang.getName();
      boolean _add = langs.add(_name);
      boolean _not = (!_add);
      if (_not) {
        this.error(
          "Extension language must be unique.", lang, 
          EJSLPackage.Literals.LANGUAGE__NAME, 
          EJSLValidator.AMBIGUOUS_LANGUAGE);
      }
    }
  }
  
  /**
   * Validates the Keys inside of a Language.
   * Language keys must have different/unique names
   */
  @Check
  public void checkLanguageKeysAreUnique(final Language lang) {
    HashSet<String> keys = new HashSet<String>();
    EList<KeyValuePair> _keyvaluepairs = lang.getKeyvaluepairs();
    for (final KeyValuePair pair : _keyvaluepairs) {
      String _name = pair.getName();
      boolean _add = keys.add(_name);
      boolean _not = (!_add);
      if (_not) {
        this.error(
          "Key must be unique.", pair, 
          EJSLPackage.Literals.KEY_VALUE_PAIR__NAME, 
          EJSLValidator.AMBIGUOUS_KEY);
      }
    }
  }
  
  /**
   * Checks if the name of an author is used more than once in a manifestation.
   */
  @Check
  public void checkManifestationAuthorsAreUnique(final Manifestation manifest) {
    HashSet<String> authors = new HashSet<String>();
    EList<Author> _authors = manifest.getAuthors();
    for (final Author author : _authors) {
      String _name = author.getName();
      boolean _add = authors.add(_name);
      boolean _not = (!_add);
      if (_not) {
        this.warning(
          "Author name is used multiple times.", author, 
          EJSLPackage.Literals.AUTHOR__NAME, 
          EJSLValidator.AMBIGUOUS_AUTHOR);
      }
    }
  }
  
  /**
   * Checks if the classes of a library have different/unique names.
   */
  @Check
  public void checkLibraryClassesAreUnique(final Library lib) {
    HashSet<String> classes = new HashSet<String>();
    EList<de.thm.icampus.joomdd.ejsl.eJSL.Class> _classes = lib.getClasses();
    for (final de.thm.icampus.joomdd.ejsl.eJSL.Class c : _classes) {
      String _name = c.getName();
      boolean _add = classes.add(_name);
      boolean _not = (!_add);
      if (_not) {
        this.error(
          "Class name must be unique per library.", c, 
          EJSLPackage.Literals.CLASS__NAME, 
          EJSLValidator.AMBIGUOUS_CLASS);
      }
    }
  }
  
  /**
   * Validates if the methods of a class have different/unique names.
   */
  @Check
  public void checkClassMethodsAreUnique(final de.thm.icampus.joomdd.ejsl.eJSL.Class c) {
    HashSet<String> methods = new HashSet<String>();
    EList<Method> _methods = c.getMethods();
    for (final Method method : _methods) {
      String _name = method.getName();
      boolean _add = methods.add(_name);
      boolean _not = (!_add);
      if (_not) {
        this.error(
          "Method name must be unique per class.", method, 
          EJSLPackage.Literals.METHOD__NAME, 
          EJSLValidator.AMBIGUOUS_METHOD);
      }
    }
  }
  
  /**
   * Validates if all global parameters of a model have different/unique names.
   */
  @Check
  public void checkPageGlobalparametersAreUnique(final EJSLModel model) {
    HashSet<String> params = new HashSet<String>();
    EList<Parameter> _globalparameters = model.getGlobalparameters();
    for (final Parameter param : _globalparameters) {
      String _name = param.getName();
      boolean _add = params.add(_name);
      boolean _not = (!_add);
      if (_not) {
        this.error(
          "Globalparameter name must be unique.", param, 
          EJSLPackage.Literals.PARAMETER__NAME, 
          EJSLValidator.AMBIGUOUS_GLOBALPARAMETER);
      }
    }
  }
  
  /**
   * Check if all local parameters of a page have different/unique names.
   */
  @Check
  public void checkPageLocalparametersAreUnique(final Page p) {
    HashSet<String> params = new HashSet<String>();
    EList<Parameter> _localparameters = p.getLocalparameters();
    for (final Parameter param : _localparameters) {
      String _name = param.getName();
      boolean _add = params.add(_name);
      boolean _not = (!_add);
      if (_not) {
        this.error(
          "Localparameter name must be unique per page.", param, 
          EJSLPackage.Literals.PARAMETER__NAME, 
          EJSLValidator.AMBIGUOUS_LOCALPARAMETER);
      }
    }
  }
  
  /**
   * Check if all extensions of a Model have different/unique names.
   */
  @Check
  public void checkExtensionsAreUniquePerClass(final EJSLModel model) {
    HashMap<Class<? extends EObject>, Set<String>> exts = new HashMap<Class<? extends EObject>, Set<String>>();
    EList<Extension> _extensions = model.getExtensions();
    for (final Extension ext : _extensions) {
      {
        Class<? extends EObject> type = ext.getClass();
        Set<String> specializedExts = exts.get(type);
        boolean _equals = Objects.equal(null, specializedExts);
        if (_equals) {
          HashSet<String> _hashSet = new HashSet<String>();
          specializedExts = _hashSet;
          exts.put(type, specializedExts);
        }
        String _name = ext.getName();
        boolean _add = specializedExts.add(_name);
        boolean _not = (!_add);
        if (_not) {
          Class<? extends Extension> _class = ext.getClass();
          String _simpleName = _class.getSimpleName();
          String _plus = ("Extension name must be unique for type " + _simpleName);
          String _plus_1 = (_plus + ".");
          this.error(_plus_1, ext, 
            EJSLPackage.Literals.EXTENSION__NAME, 
            EJSLValidator.AMBIGUOUS_EXTENSION);
        }
      }
    }
  }
  
  /**
   * Check if the existing extensions don't contain another extensions.
   */
  @Check
  public void checkExtensionPackagesDoNotContainExtensionPackages(final ExtensionPackage extPackage) {
    int i = 0;
    EList<Extension> _extensions = extPackage.getExtensions();
    for (final Extension ext : _extensions) {
      {
        if ((ext instanceof ExtensionPackage)) {
          this.error(
            "Extension package must not contain more extension packages.", 
            EJSLPackage.Literals.EXTENSION_PACKAGE__EXTENSIONS, i, 
            EJSLValidator.EXTPACKAGE_CONTAINS_EXTPACKAGE);
        }
        i++;
      }
    }
  }
  
  /**
   * Validate that an entity can only used once per Page and not multiple times.
   */
  @Check
  public void checkEntitysAreUsedOnlyOncePerPage(final DynamicPage page) {
    HashSet<String> entities = new HashSet<String>();
    int i = 0;
    EList<Entity> _entities = page.getEntities();
    for (final Entity entity : _entities) {
      {
        String _name = entity.getName();
        boolean _add = entities.add(_name);
        boolean _not = (!_add);
        if (_not) {
          this.warning(
            "Entity is used multiple times for this page.", 
            EJSLPackage.Literals.DYNAMIC_PAGE__ENTITIES, i, 
            EJSLValidator.ENTITY_USED_MULTIPLE_TIMES);
        }
        i++;
      }
    }
  }
  
  /**
   * Validate that an entity can only used once per section and not multiple times.
   */
  @Check
  public void checkPagesAreUsedOnlyOncePerSection(final Section section) {
    HashSet<String> pages = new HashSet<String>();
    int i = 0;
    EList<PageReference> _pageRef = section.getPageRef();
    for (final PageReference page : _pageRef) {
      {
        Page _page = page.getPage();
        String _name = _page.getName();
        boolean _add = pages.add(_name);
        boolean _not = (!_add);
        if (_not) {
          this.warning(
            "Page is used multiple times for this section.", 
            EJSLPackage.Literals.PAGE_REFERENCE__PAGESCR, i, 
            EJSLValidator.PAGE_USED_MULTIPLE_TIMES);
        }
        i++;
      }
    }
  }
  
  /**
   * Checks if the author uses a valid email adress
   */
  @Check
  public void checkAuthorEmailIsValid(final Author author) {
    String _authoremail = author.getAuthoremail();
    boolean _isEmailAdressValid = this.isEmailAdressValid(_authoremail);
    boolean _not = (!_isEmailAdressValid);
    if (_not) {
      this.error(
        "Invalid e-mail address. Should be in this format: xxx@xx.xx", 
        EJSLPackage.Literals.AUTHOR__AUTHOREMAIL, 
        EJSLValidator.INVALID_AUTHOR_EMAIL);
    }
  }
  
  /**
   * Checks if the author uses a valid url
   */
  @Check
  public void checkAuthorUrlIsValid(final Author author) {
    String _authorurl = author.getAuthorurl();
    boolean _isUrlValid = this.isUrlValid(_authorurl);
    boolean _not = (!_isUrlValid);
    if (_not) {
      this.warning(
        "Invalid URL. Should be in this format: http(s)//:www.xxx.xx", 
        EJSLPackage.Literals.AUTHOR__AUTHORURL, 
        EJSLValidator.INVALID_AUTHOR_URL);
    }
  }
  
  /**
   * Method for email matching
   */
  public boolean isEmailAdressValid(final String address) {
    Matcher _matcher = EJSLValidator.emailPattern.matcher(address);
    return _matcher.matches();
  }
  
  /**
   * Method for url matching
   */
  public boolean isUrlValid(final String url) {
    Matcher _matcher = EJSLValidator.httpUrlPattern.matcher(url);
    return _matcher.matches();
  }
  
  /**
   * Checks if at least one Primary attribute exists in the attributes of an entity
   */
  @Check
  public void checkPrimaryAttributeExist(final Entity entity) {
    boolean hasPrimary = false;
    EList<Attribute> _attributes = entity.getAttributes();
    for (final Attribute attribute : _attributes) {
      boolean _isIsunique = attribute.isIsunique();
      if (_isIsunique) {
        hasPrimary = true;
      }
    }
    if ((!hasPrimary)) {
      this.error(
        "Attributes must have a primary attribute.", entity, 
        EJSLPackage.Literals.ENTITY__NAME, 
        EJSLValidator.MISSING_PRIMARY_ATTRIBUTE);
    }
  }
  
  /**
   * Validates if the reference to an attribute leads on a primary attribtue
   */
  @Check
  public void refToAttributeMustBePrimary(final Reference reference) {
    Attribute _attributerefereced = reference.getAttributerefereced();
    boolean _isIsunique = _attributerefereced.isIsunique();
    boolean _not = (!_isIsunique);
    if (_not) {
      this.error(
        "The referenced attribute has to be a primary attribute.", reference, 
        EJSLPackage.Literals.REFERENCE__ATTRIBUTEREFERECED, 
        EJSLValidator.NOT_PRIMARY_REFERENCE);
    }
  }
  
  /**
   * Checks if the name of an attribute fulfills certain conventions.
   * The name can't be id, ordering, state, checked_out_time, created_by and checked_out
   */
  @Check
  public void checkAttributename(final Entity entity) {
    EList<Attribute> _attributes = entity.getAttributes();
    for (final Attribute attribute : _attributes) {
      {
        String _name = attribute.getName();
        boolean _equals = Objects.equal(_name, "id");
        if (_equals) {
          this.error(
            "\"id\" is not a valid attribute name!", attribute, 
            EJSLPackage.Literals.ATTRIBUTE__NAME, 
            EJSLValidator.AMBIGUOUS_ATTRIBUTE_NAME);
        }
        boolean _or = false;
        boolean _or_1 = false;
        boolean _or_2 = false;
        boolean _or_3 = false;
        String _name_1 = attribute.getName();
        boolean _equals_1 = Objects.equal(_name_1, "ordering");
        if (_equals_1) {
          _or_3 = true;
        } else {
          String _name_2 = attribute.getName();
          boolean _equals_2 = Objects.equal(_name_2, "state");
          _or_3 = _equals_2;
        }
        if (_or_3) {
          _or_2 = true;
        } else {
          String _name_3 = attribute.getName();
          boolean _equals_3 = Objects.equal(_name_3, "checked_out");
          _or_2 = _equals_3;
        }
        if (_or_2) {
          _or_1 = true;
        } else {
          String _name_4 = attribute.getName();
          boolean _equals_4 = Objects.equal(_name_4, "checked_out_time");
          _or_1 = _equals_4;
        }
        if (_or_1) {
          _or = true;
        } else {
          String _name_5 = attribute.getName();
          boolean _equals_5 = Objects.equal(_name_5, "created_by");
          _or = _equals_5;
        }
        if (_or) {
          String _name_6 = attribute.getName();
          String _plus = ("Attribute name should not be: " + _name_6);
          String _plus_1 = (_plus + "!");
          this.warning(_plus_1, attribute, 
            EJSLPackage.Literals.ATTRIBUTE__NAME, 
            EJSLValidator.AMBIGUOUS_ATTRIBUTE_NAME);
        }
      }
    }
  }
  
  /**
   * Check if the entity in the filter is declared in the page
   */
  @Check
  public void nonDeclaredFilterAttribute(final DynamicPage p) {
    EList<Attribute> _filters = p.getFilters();
    for (final Attribute filt : _filters) {
      {
        EObject _eContainer = filt.eContainer();
        final Entity enti = ((Entity) _eContainer);
        EList<Entity> _entities = p.getEntities();
        boolean _contains = _entities.contains(enti);
        boolean _not = (!_contains);
        if (_not) {
          EReference _eOpposite = EJSLPackage.Literals.DYNAMIC_PAGE__FILTERS.getEOpposite();
          this.error(
            "Entity for the filter attribute must be declared before.", p, _eOpposite, 
            EJSLValidator.AMBIGUOUS_FILTER_ATTRIBUTE);
        }
      }
    }
  }
  
  /**
   * Check if the entity in the table column is declared in the page
   */
  @Check
  public void nonDeclaredColumnAttribute(final DynamicPage p) {
    EList<Attribute> _tablecolumns = p.getTablecolumns();
    for (final Attribute column : _tablecolumns) {
      {
        EObject _eContainer = column.eContainer();
        final Entity enti = ((Entity) _eContainer);
        EList<Entity> _entities = p.getEntities();
        boolean _contains = _entities.contains(enti);
        boolean _not = (!_contains);
        if (_not) {
          EReference _eOpposite = EJSLPackage.Literals.DYNAMIC_PAGE__TABLECOLUMNS.getEOpposite();
          this.error(
            "Entity for the table column attribute must be declared before.", p, _eOpposite, 
            EJSLValidator.AMBIGUOUS_TABLE_COLUMN_ATTRIBUTE);
        }
      }
    }
  }
  
  /**
   * Check table column are only once in a page
   */
  @Check
  public void checkTableColumnsAreUnique(final DynamicPage p) {
    HashSet<String> enticolumns = new HashSet<String>();
    EList<Attribute> _tablecolumns = p.getTablecolumns();
    for (final Attribute column : _tablecolumns) {
      {
        EObject _eContainer = column.eContainer();
        final Entity enti = ((Entity) _eContainer);
        String _name = enti.getName();
        String _name_1 = column.getName();
        String _plus = (_name + _name_1);
        boolean _add = enticolumns.add(_plus);
        boolean _not = (!_add);
        if (_not) {
          EReference _eOpposite = EJSLPackage.Literals.DYNAMIC_PAGE__TABLECOLUMNS.getEOpposite();
          this.error(
            "table column used multiple times in this Page.", p, _eOpposite, 
            EJSLValidator.COLUMNS_USED_MULTIPLE_TIMES);
        }
      }
    }
  }
  
  /**
   * Check Filters are only once in a page
   */
  @Check
  public void checkFiltersAreUnique(final DynamicPage p) {
    HashSet<String> entifilters = new HashSet<String>();
    EList<Attribute> _filters = p.getFilters();
    for (final Attribute filter : _filters) {
      {
        EObject _eContainer = filter.eContainer();
        final Entity enti = ((Entity) _eContainer);
        String _name = enti.getName();
        String _name_1 = filter.getName();
        String _plus = (_name + _name_1);
        boolean _add = entifilters.add(_plus);
        boolean _not = (!_add);
        if (_not) {
          EReference _eOpposite = EJSLPackage.Literals.DYNAMIC_PAGE__FILTERS.getEOpposite();
          this.error(
            "Filter used multiple times in this Page!", p, _eOpposite, 
            EJSLValidator.FILTER_USED_MULTIPLE_TIMES);
        }
      }
    }
  }
}