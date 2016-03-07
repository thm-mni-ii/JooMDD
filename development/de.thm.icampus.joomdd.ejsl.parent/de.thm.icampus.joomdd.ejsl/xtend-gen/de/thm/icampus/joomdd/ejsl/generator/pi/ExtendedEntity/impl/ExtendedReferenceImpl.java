package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.Reference;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.ReferenceImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedAttributeImpl;
import java.util.List;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ExtendedReferenceImpl extends ReferenceImpl implements ExtendedReference {
  private EList<ExtendedAttribute> extendedAttribute;
  
  private EList<ExtendedAttribute> extendedAttributerefereced;
  
  private Entity toEntity;
  
  private Entity fromEntity;
  
  public ExtendedReferenceImpl(final Reference e, final Entity from) {
    EList<Attribute> _attribute = e.getAttribute();
    this.attribute = _attribute;
    EList<Attribute> _attributerefereced = e.getAttributerefereced();
    this.attributerefereced = _attributerefereced;
    Entity _entity = e.getEntity();
    this.entity = _entity;
    Entity _entity_1 = e.getEntity();
    this.toEntity = _entity_1;
    this.fromEntity = from;
    this.initList();
  }
  
  public boolean initList() {
    boolean _xblockexpression = false;
    {
      BasicEList<ExtendedAttribute> _basicEList = new BasicEList<ExtendedAttribute>();
      this.extendedAttribute = _basicEList;
      BasicEList<ExtendedAttribute> _basicEList_1 = new BasicEList<ExtendedAttribute>();
      this.extendedAttributerefereced = _basicEList_1;
      final Function1<Attribute, ExtendedAttributeImpl> _function = (Attribute t) -> {
        return new ExtendedAttributeImpl(t);
      };
      List<ExtendedAttributeImpl> _map = ListExtensions.<Attribute, ExtendedAttributeImpl>map(this.attribute, _function);
      this.extendedAttribute.addAll(_map);
      final Function1<Attribute, ExtendedAttributeImpl> _function_1 = (Attribute t) -> {
        return new ExtendedAttributeImpl(t);
      };
      List<ExtendedAttributeImpl> _map_1 = ListExtensions.<Attribute, ExtendedAttributeImpl>map(this.attributerefereced, _function_1);
      _xblockexpression = this.extendedAttributerefereced.addAll(_map_1);
    }
    return _xblockexpression;
  }
  
  @Override
  public EList<ExtendedAttribute> getExtendedAttribute() {
    return this.extendedAttribute;
  }
  
  @Override
  public EList<ExtendedAttribute> getExtendedAttributeReferenced() {
    return this.extendedAttributerefereced;
  }
  
  @Override
  public Entity getExtendedToEntity() {
    return this.toEntity;
  }
  
  @Override
  public Entity getExtendedFromEntity() {
    return this.fromEntity;
  }
}
