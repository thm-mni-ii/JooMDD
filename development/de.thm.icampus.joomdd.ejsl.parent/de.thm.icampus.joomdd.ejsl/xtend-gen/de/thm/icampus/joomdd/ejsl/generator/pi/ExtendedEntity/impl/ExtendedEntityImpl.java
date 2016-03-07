package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.Feature;
import de.thm.icampus.joomdd.ejsl.eJSL.Reference;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.EntityImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedReferenceImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil;
import java.util.List;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ExtendedEntityImpl extends EntityImpl implements ExtendedEntity {
  private Entity instance;
  
  private EList<ExtendedAttribute> extendedAttributeList;
  
  private EList<ExtendedAttribute> extendedParentAttributeList;
  
  private EList<ExtendedAttribute> allAttribute;
  
  private EList<ExtendedReference> extendedReference;
  
  private EList<ExtendedReference> allReferenceToEntity;
  
  public ExtendedEntityImpl(final Entity entity) {
    String _name = entity.getName();
    String _slugify = PlattformIUtil.slugify(_name);
    entity.setName(_slugify);
    String _name_1 = entity.getName();
    this.name = _name_1;
    Entity _supertype = entity.getSupertype();
    this.supertype = _supertype;
    EList<Attribute> _attributes = entity.getAttributes();
    this.attributes = _attributes;
    EList<Reference> _references = entity.getReferences();
    this.references = _references;
    this.instance = entity;
    this.initListen();
  }
  
  @Override
  public EList<ExtendedAttribute> getExtendedAttributeList() {
    return this.extendedAttributeList;
  }
  
  @Override
  public EList<ExtendedAttribute> getExtendedParentAttributeList() {
    return this.extendedParentAttributeList;
  }
  
  @Override
  public Entity getInstance() {
    return this.instance;
  }
  
  @Override
  public EList<ExtendedAttribute> getAllattribute() {
    return this.allAttribute;
  }
  
  public void initListen() {
    BasicEList<ExtendedAttribute> _basicEList = new BasicEList<ExtendedAttribute>();
    this.extendedAttributeList = _basicEList;
    BasicEList<ExtendedAttribute> _basicEList_1 = new BasicEList<ExtendedAttribute>();
    this.allAttribute = _basicEList_1;
    BasicEList<ExtendedReference> _basicEList_2 = new BasicEList<ExtendedReference>();
    this.allReferenceToEntity = _basicEList_2;
    BasicEList<ExtendedReference> _basicEList_3 = new BasicEList<ExtendedReference>();
    this.extendedReference = _basicEList_3;
    final Function1<Attribute, ExtendedAttribute> _function = (Attribute t) -> {
      return PlattformIUtil.transformAttribute(t);
    };
    List<ExtendedAttribute> _map = ListExtensions.<Attribute, ExtendedAttribute>map(this.attributes, _function);
    this.extendedAttributeList.addAll(_map);
    EList<ExtendedAttribute> _searchAttributeParent = this.searchAttributeParent();
    this.extendedParentAttributeList = _searchAttributeParent;
    this.allAttribute.addAll(this.extendedAttributeList);
    this.allAttribute.addAll(this.extendedParentAttributeList);
    final Function1<Reference, ExtendedReferenceImpl> _function_1 = (Reference t) -> {
      return new ExtendedReferenceImpl(t, this.instance);
    };
    List<ExtendedReferenceImpl> _map_1 = ListExtensions.<Reference, ExtendedReferenceImpl>map(this.references, _function_1);
    this.extendedReference.addAll(_map_1);
    EObject _eContainer = this.instance.eContainer();
    EList<Entity> allEntity = ((Feature) _eContainer).getEntities();
    for (final Entity ent : allEntity) {
      EList<Reference> _references = ent.getReferences();
      boolean _notEquals = (!Objects.equal(_references, null));
      if (_notEquals) {
        EList<Reference> _references_1 = ent.getReferences();
        final Function1<Reference, Boolean> _function_2 = (Reference t) -> {
          Entity _entity = t.getEntity();
          String _name = _entity.getName();
          String _name_1 = this.instance.getName();
          return Boolean.valueOf(Objects.equal(_name, _name_1));
        };
        Iterable<Reference> listRef = IterableExtensions.<Reference>filter(_references_1, _function_2);
        for (final Reference ref : listRef) {
          ExtendedReferenceImpl _extendedReferenceImpl = new ExtendedReferenceImpl(ref, ent);
          this.allReferenceToEntity.add(_extendedReferenceImpl);
        }
      }
    }
  }
  
  public EList<ExtendedAttribute> searchAttributeParent() {
    EList<ExtendedAttribute> result = new BasicEList<ExtendedAttribute>();
    Entity parent = this.supertype;
    while ((!Objects.equal(parent, null))) {
      {
        EList<Attribute> _attributes = parent.getAttributes();
        final Function1<Attribute, ExtendedAttribute> _function = (Attribute t) -> {
          return PlattformIUtil.transformAttribute(t);
        };
        List<ExtendedAttribute> _map = ListExtensions.<Attribute, ExtendedAttribute>map(_attributes, _function);
        result.addAll(_map);
        Entity _supertype = parent.getSupertype();
        parent = _supertype;
      }
    }
    return result;
  }
  
  @Override
  public boolean haveIdAttribute() {
    for (final ExtendedAttribute attr : this.extendedAttributeList) {
      String _name = attr.getName();
      String _lowerCase = _name.toLowerCase();
      boolean _equalsIgnoreCase = _lowerCase.equalsIgnoreCase("id");
      if (_equalsIgnoreCase) {
        attr.setName("id");
        return true;
      }
    }
    return false;
  }
  
  @Override
  public void putNewAttributeInEntity(final Attribute e) {
    this.attributes.add(e);
    ExtendedAttribute _transformAttribute = PlattformIUtil.transformAttribute(e);
    this.allAttribute.add(_transformAttribute);
  }
  
  @Override
  public ExtendedAttribute searchIdAttribute() {
    for (final ExtendedAttribute attr : this.extendedAttributeList) {
      String _name = attr.getName();
      String _lowerCase = _name.toLowerCase();
      boolean _equalsIgnoreCase = _lowerCase.equalsIgnoreCase("id");
      if (_equalsIgnoreCase) {
        return attr;
      }
    }
    return null;
  }
  
  @Override
  public EList<ExtendedReference> getExtendedReference() {
    return this.extendedReference;
  }
  
  @Override
  public EList<ExtendedReference> getallReferenceToEntity() {
    return this.allReferenceToEntity;
  }
  
  @Override
  public ExtendedAttribute getAttributeByName(final String name) {
    for (final ExtendedAttribute attr : this.extendedAttributeList) {
      String _name = attr.getName();
      boolean _equalsIgnoreCase = _name.equalsIgnoreCase(name);
      if (_equalsIgnoreCase) {
        return attr;
      }
    }
    return null;
  }
}
