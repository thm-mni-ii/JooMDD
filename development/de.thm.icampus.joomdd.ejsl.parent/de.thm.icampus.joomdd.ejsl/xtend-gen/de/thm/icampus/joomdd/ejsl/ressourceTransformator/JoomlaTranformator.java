package de.thm.icampus.joomdd.ejsl.ressourceTransformator;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension;
import de.thm.icampus.joomdd.ejsl.eJSL.ContextLink;
import de.thm.icampus.joomdd.ejsl.eJSL.Datatype;
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference;
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage;
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory;
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel;
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPart;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.Feature;
import de.thm.icampus.joomdd.ejsl.eJSL.Link;
import de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter;
import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.eJSL.Reference;
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypeKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes;
import de.thm.icampus.joomdd.ejsl.eJSL.StaticPage;
import de.thm.icampus.joomdd.ejsl.eJSL.Type;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class JoomlaTranformator {
  private EJSLModel instance;
  
  private CMSExtension cmsExtension;
  
  private EList<Entity> entityList;
  
  public JoomlaTranformator(final EJSLModel model) {
    this.instance = model;
  }
  
  public void completeCMSExtension() {
    EJSLPart _ejslPart = this.instance.getEjslPart();
    boolean _matched = false;
    if (!_matched) {
      if (_ejslPart instanceof CMSExtension) {
        _matched=true;
        EJSLPart _ejslPart_1 = this.instance.getEjslPart();
        this.cmsExtension = ((CMSExtension) _ejslPart_1);
        Feature _feature = this.cmsExtension.getFeature();
        EList<Entity> _entities = _feature.getEntities();
        this.entityList = _entities;
        this.completeEntity();
        Feature _feature_1 = this.cmsExtension.getFeature();
        EList<Page> _pages = _feature_1.getPages();
        this.completePage(_pages);
      }
    }
  }
  
  private void completeEntity() {
    for (final Entity ent : this.entityList) {
      this.completeAttributeOfEntity(ent);
    }
    for (final Entity ent_1 : this.entityList) {
      {
        this.completeReferenceOfEntity(ent_1);
        this.setReferenceAttribute(ent_1);
      }
    }
  }
  
  private void completeAttributeOfEntity(final Entity ent) {
    boolean _haveIdAttribute = this.haveIdAttribute(ent);
    boolean _not = (!_haveIdAttribute);
    if (_not) {
      Attribute id = EJSLFactory.eINSTANCE.createAttribute();
      id.setName("id");
      StandardTypes typeid = EJSLFactory.eINSTANCE.createStandardTypes();
      typeid.setType(StandardTypeKinds.INTEGER);
      typeid.setNotnull(true);
      typeid.setAutoincrement(true);
      id.setType(typeid);
      EList<Attribute> _attributes = ent.getAttributes();
      _attributes.add(id);
    }
    Attribute asset_id = EJSLFactory.eINSTANCE.createAttribute();
    asset_id.setName("asset_id");
    StandardTypes type_asset_id = EJSLFactory.eINSTANCE.createStandardTypes();
    type_asset_id.setType(StandardTypeKinds.INTEGER);
    type_asset_id.setNotnull(true);
    type_asset_id.setDefault("0");
    asset_id.setType(type_asset_id);
    EList<Attribute> _attributes_1 = ent.getAttributes();
    _attributes_1.add(asset_id);
    Attribute state = EJSLFactory.eINSTANCE.createAttribute();
    state.setName("state");
    StandardTypes type_state = EJSLFactory.eINSTANCE.createStandardTypes();
    type_state.setType(StandardTypeKinds.BOOLEAN);
    type_state.setNotnull(true);
    state.setType(type_state);
    EList<Attribute> _attributes_2 = ent.getAttributes();
    _attributes_2.add(state);
    Attribute ordering = EJSLFactory.eINSTANCE.createAttribute();
    ordering.setName("ordering");
    StandardTypes type_ordering = EJSLFactory.eINSTANCE.createStandardTypes();
    type_ordering.setType(StandardTypeKinds.INTEGER);
    type_ordering.setNotnull(true);
    ordering.setType(type_ordering);
    EList<Attribute> _attributes_3 = ent.getAttributes();
    _attributes_3.add(ordering);
    Attribute checked_out_time = EJSLFactory.eINSTANCE.createAttribute();
    checked_out_time.setName("checked_out_time");
    StandardTypes type_checked_out_time = EJSLFactory.eINSTANCE.createStandardTypes();
    type_checked_out_time.setType(StandardTypeKinds.DATETIME);
    type_checked_out_time.setNotnull(true);
    type_checked_out_time.setDefault("0000-00-00 00:00:00");
    checked_out_time.setType(type_checked_out_time);
    EList<Attribute> _attributes_4 = ent.getAttributes();
    _attributes_4.add(checked_out_time);
    Attribute checked_out = EJSLFactory.eINSTANCE.createAttribute();
    checked_out.setName("checked_out");
    StandardTypes type_checked_out = EJSLFactory.eINSTANCE.createStandardTypes();
    type_checked_out.setType(StandardTypeKinds.INTEGER);
    type_checked_out.setNotnull(true);
    checked_out.setType(type_checked_out);
    EList<Attribute> _attributes_5 = ent.getAttributes();
    _attributes_5.add(checked_out);
    Attribute created_by = EJSLFactory.eINSTANCE.createAttribute();
    created_by.setName("created_by");
    StandardTypes type_created_by = EJSLFactory.eINSTANCE.createStandardTypes();
    type_created_by.setType(StandardTypeKinds.INTEGER);
    type_created_by.setNotnull(true);
    created_by.setType(type_created_by);
    EList<Attribute> _attributes_6 = ent.getAttributes();
    _attributes_6.add(created_by);
    Attribute published = EJSLFactory.eINSTANCE.createAttribute();
    published.setName("published");
    StandardTypes type_published = EJSLFactory.eINSTANCE.createStandardTypes();
    type_published.setType(StandardTypeKinds.BOOLEAN);
    published.setType(type_published);
    EList<Attribute> _attributes_7 = ent.getAttributes();
    _attributes_7.add(published);
    Attribute params = EJSLFactory.eINSTANCE.createAttribute();
    params.setName("params");
    StandardTypes type_params = EJSLFactory.eINSTANCE.createStandardTypes();
    type_params.setType(StandardTypeKinds.TEXTAREA);
    params.setType(type_params);
    EList<Attribute> _attributes_8 = ent.getAttributes();
    _attributes_8.add(params);
    EList<Attribute> _attributes_9 = ent.getAttributes();
    for (final Attribute attr : _attributes_9) {
      boolean _isId = attr.isId();
      if (_isId) {
        Attribute _searchIdAttribute = this.searchIdAttribute(ent);
        attr.setWithattribute(_searchIdAttribute);
      }
    }
  }
  
  private Attribute searchIdAttribute(final Entity entity) {
    EList<Attribute> _attributes = entity.getAttributes();
    for (final Attribute e : _attributes) {
      String _name = e.getName();
      boolean _equalsIgnoreCase = _name.equalsIgnoreCase("id");
      if (_equalsIgnoreCase) {
        return e;
      }
    }
    return null;
  }
  
  private boolean haveIdAttribute(final Entity entity) {
    EList<Attribute> _attributes = entity.getAttributes();
    for (final Attribute e : _attributes) {
      String _name = e.getName();
      boolean _equalsIgnoreCase = _name.equalsIgnoreCase("id");
      if (_equalsIgnoreCase) {
        return true;
      }
    }
    return false;
  }
  
  private void completeReferenceOfEntity(final Entity ent) {
    EList<Reference> _references = ent.getReferences();
    for (final Reference ref : _references) {
      boolean _isId = ref.isId();
      if (_isId) {
        Entity _entity = ref.getEntity();
        Attribute id = this.searchIdAttribute(_entity);
        boolean _notEquals = (!Objects.equal(id, null));
        if (_notEquals) {
          EList<Attribute> _attributerefereced = ref.getAttributerefereced();
          _attributerefereced.add(id);
          ref.setId(false);
          EList<Attribute> _attributerefereced_1 = ref.getAttributerefereced();
          int _size = _attributerefereced_1.size();
          boolean _greaterThan = (_size > 1);
          if (_greaterThan) {
            Entity _entity_1 = ref.getEntity();
            Attribute _attributeReference = this.getAttributeReference(ent, _entity_1, id);
            boolean _equals = Objects.equal(_attributeReference, null);
            if (_equals) {
              EList<Attribute> _attribute = ref.getAttribute();
              Attribute _setNewGenAttribute = this.setNewGenAttribute(ent, ref, id);
              _attribute.add(_setNewGenAttribute);
            }
          }
        }
      }
    }
  }
  
  public void setReferenceAttribute(final Entity ent) {
    EList<Reference> _references = ent.getReferences();
    for (final Reference ref : _references) {
      {
        Entity referenceEntity = ref.getEntity();
        EList<Attribute> newArttibute = new BasicEList<Attribute>();
        EList<Attribute> _attributerefereced = ref.getAttributerefereced();
        for (final Attribute attrRef : _attributerefereced) {
          {
            Attribute uniqWith = attrRef.getWithattribute();
            boolean _and = false;
            boolean _notEquals = (!Objects.equal(uniqWith, null));
            if (!_notEquals) {
              _and = false;
            } else {
              Attribute _attributeReference = this.getAttributeReference(ent, referenceEntity, uniqWith);
              boolean _equals = Objects.equal(_attributeReference, null);
              _and = _equals;
            }
            if (_and) {
              this.setNewGenAttribute(ent, ref, uniqWith);
              newArttibute.add(uniqWith);
            }
          }
        }
        EList<Attribute> _attributerefereced_1 = ref.getAttributerefereced();
        _attributerefereced_1.addAll(newArttibute);
      }
    }
  }
  
  public Attribute setNewGenAttribute(final Entity ent, final Reference ref, final Attribute attrRef) {
    Entity referenceEntity = ref.getEntity();
    Attribute newAttribute = EJSLFactory.eINSTANCE.createAttribute();
    String _name = referenceEntity.getName();
    String _string = _name.toString();
    String _lowerCase = _string.toLowerCase();
    String _plus = (_lowerCase + "_");
    String _name_1 = attrRef.getName();
    String _plus_1 = (_plus + _name_1);
    newAttribute.setName(_plus_1);
    Type _type = attrRef.getType();
    Type _copyType = this.copyType(_type);
    newAttribute.setType(_copyType);
    EList<Attribute> _attributes = ent.getAttributes();
    _attributes.add(newAttribute);
    EList<Attribute> _attribute = ref.getAttribute();
    _attribute.add(newAttribute);
    return newAttribute;
  }
  
  public Type copyType(final Type type) {
    boolean _matched = false;
    if (!_matched) {
      if (type instanceof DatatypeReference) {
        _matched=true;
        DatatypeReference old_type = ((DatatypeReference) type);
        DatatypeReference new_type = EJSLFactory.eINSTANCE.createDatatypeReference();
        Datatype _type = old_type.getType();
        new_type.setType(_type);
        return new_type;
      }
    }
    if (!_matched) {
      if (type instanceof StandardTypes) {
        _matched=true;
        StandardTypes old_type = ((StandardTypes) type);
        StandardTypes new_type = EJSLFactory.eINSTANCE.createStandardTypes();
        StandardTypeKinds _type = old_type.getType();
        new_type.setType(_type);
        boolean _isNotnull = old_type.isNotnull();
        new_type.setNotnull(_isNotnull);
        String _default = old_type.getDefault();
        new_type.setDefault(_default);
        return new_type;
      }
    }
    return null;
  }
  
  public Attribute getAttributeReference(final Entity ent, final Entity referencedEntity, final Attribute referenced) {
    EList<Attribute> _attributes = ent.getAttributes();
    for (final Attribute a : _attributes) {
      String _name = a.getName();
      String _string = referencedEntity.toString();
      String _lowerCase = _string.toLowerCase();
      String _plus = (_lowerCase + "_");
      String _name_1 = referenced.getName();
      String _plus_1 = (_plus + _name_1);
      boolean _equalsIgnoreCase = _name.equalsIgnoreCase(_plus_1);
      if (_equalsIgnoreCase) {
        return a;
      }
    }
    EList<Reference> _references = ent.getReferences();
    for (final Reference ref : _references) {
      boolean _and = false;
      Entity _entity = ref.getEntity();
      String _name_2 = _entity.getName();
      String _name_3 = referencedEntity.getName();
      boolean _equals = Objects.equal(_name_2, _name_3);
      if (!_equals) {
        _and = false;
      } else {
        EList<Attribute> _attributerefereced = ref.getAttributerefereced();
        boolean _contains = _attributerefereced.contains(referenced);
        _and = _contains;
      }
      if (_and) {
        EList<Attribute> _attributerefereced_1 = ref.getAttributerefereced();
        int index = _attributerefereced_1.indexOf(referenced);
        EList<Attribute> _attribute = ref.getAttribute();
        return _attribute.get(index);
      }
    }
    return null;
  }
  
  public void completePage(final EList<Page> pageList) {
    final Function1<Page, Boolean> _function = (Page t) -> {
      return Boolean.valueOf((!(t instanceof StaticPage)));
    };
    Iterable<Page> _filter = IterableExtensions.<Page>filter(pageList, _function);
    for (final Page pg : _filter) {
      {
        Entity fromEnt = null;
        boolean _matched = false;
        if (!_matched) {
          if (pg instanceof DynamicPage) {
            _matched=true;
            DynamicPage fromDynPage = ((DynamicPage) pg);
            EList<Entity> _entities = fromDynPage.getEntities();
            Entity _get = _entities.get(0);
            fromEnt = _get;
            EList<Link> _links = ((DynamicPage)pg).getLinks();
            final Function1<Link, Boolean> _function_1 = (Link t) -> {
              return Boolean.valueOf((t instanceof ContextLink));
            };
            Iterable<Link> _filter_1 = IterableExtensions.<Link>filter(_links, _function_1);
            for (final Link lk : _filter_1) {
              {
                ContextLink ctLink = ((ContextLink) lk);
                Page _target = ctLink.getTarget();
                if ((_target instanceof DynamicPage)) {
                  Page _target_1 = ctLink.getTarget();
                  DynamicPage toDynPage = ((DynamicPage) _target_1);
                  EList<Entity> _entities_1 = toDynPage.getEntities();
                  Entity toEnt = _entities_1.get(0);
                  EList<LinkParameter> _linkparameters = ctLink.getLinkparameters();
                  int _size = _linkparameters.size();
                  boolean _notEquals = (_size != 0);
                  if (_notEquals) {
                    this.completeContextLink(ctLink, fromEnt, toEnt);
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void completeContextLink(final ContextLink link, final Entity fromEntity, final Entity toEntity) {
    EList<LinkParameter> _linkparameters = link.getLinkparameters();
    for (final LinkParameter lkParam : _linkparameters) {
      boolean _isId = lkParam.isId();
      if (_isId) {
        String _name = fromEntity.getName();
        String _name_1 = toEntity.getName();
        boolean _notEquals = (!Objects.equal(_name, _name_1));
        if (_notEquals) {
          Attribute _searchReferenceOfID = this.searchReferenceOfID(fromEntity, toEntity);
          lkParam.setAttvalue(_searchReferenceOfID);
        } else {
          Attribute _searchIdAttribute = this.searchIdAttribute(fromEntity);
          lkParam.setAttvalue(_searchIdAttribute);
        }
        lkParam.setId(false);
      }
    }
  }
  
  public Attribute searchReferenceOfID(final Entity fromEntity, final Entity toEntity) {
    Attribute id = this.searchIdAttribute(toEntity);
    EList<Reference> _references = fromEntity.getReferences();
    for (final Reference ref : _references) {
      {
        Attribute result = this.getAttributeReference(fromEntity, toEntity, id);
        boolean _notEquals = (!Objects.equal(result, null));
        if (_notEquals) {
          return result;
        }
      }
    }
    return null;
  }
}
