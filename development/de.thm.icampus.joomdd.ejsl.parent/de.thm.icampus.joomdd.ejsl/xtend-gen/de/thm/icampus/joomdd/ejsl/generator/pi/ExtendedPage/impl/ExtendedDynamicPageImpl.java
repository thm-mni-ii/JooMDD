package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.DetailPageField;
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage;
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.Link;
import de.thm.icampus.joomdd.ejsl.eJSL.Parameter;
import de.thm.icampus.joomdd.ejsl.eJSL.ParameterGroup;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.DynamicPageImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl.ExtendedDetailsPageFieldImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterGroupImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterImpl;
import java.util.List;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ExtendedDynamicPageImpl extends DynamicPageImpl implements ExtendedDynamicPage {
  private DynamicPage instance;
  
  private EList<ExtendedAttribute> extendedTableColumnList;
  
  private EList<ExtendedAttribute> extendedFiltersList;
  
  private EList<ExtendedDetailPageField> extendedEditFieldsList;
  
  private EList<ExtendedParameterGroup> extendedParameterGroupList;
  
  private EList<ExtendedParameter> extendedGlobalParameterList;
  
  private EList<ExtendedParameter> extendedLocalParameterList;
  
  private EList<ExtendedAttribute> allAttributeOfFilterAndColum;
  
  private EList<ExtendedEntity> extendedEntity;
  
  private Boolean isDetailsPage = Boolean.valueOf(false);
  
  public ExtendedDynamicPageImpl(final DynamicPage page) {
    this.instance = page;
    EList<Entity> _entities = page.getEntities();
    this.entities = _entities;
    String _name = page.getName();
    this.name = _name;
    EList<ParameterGroup> _parametergroups = page.getParametergroups();
    this.parametergroups = _parametergroups;
    EList<Parameter> _globalparameters = page.getGlobalparameters();
    this.globalparameters = _globalparameters;
    EList<Parameter> _localparameters = page.getLocalparameters();
    this.localparameters = _localparameters;
    EList<Attribute> _tablecolumns = page.getTablecolumns();
    this.tablecolumns = _tablecolumns;
    EList<Attribute> _filters = page.getFilters();
    this.filters = _filters;
    EList<Link> _links = page.getLinks();
    this.links = _links;
    this.initList();
  }
  
  public boolean initList() {
    boolean _xblockexpression = false;
    {
      BasicEList<ExtendedAttribute> _basicEList = new BasicEList<ExtendedAttribute>();
      this.extendedTableColumnList = _basicEList;
      BasicEList<ExtendedAttribute> _basicEList_1 = new BasicEList<ExtendedAttribute>();
      this.extendedFiltersList = _basicEList_1;
      BasicEList<ExtendedDetailPageField> _basicEList_2 = new BasicEList<ExtendedDetailPageField>();
      this.extendedEditFieldsList = _basicEList_2;
      BasicEList<ExtendedAttribute> _basicEList_3 = new BasicEList<ExtendedAttribute>();
      this.allAttributeOfFilterAndColum = _basicEList_3;
      BasicEList<ExtendedEntity> _basicEList_4 = new BasicEList<ExtendedEntity>();
      this.extendedEntity = _basicEList_4;
      final Function1<Attribute, ExtendedAttribute> _function = (Attribute t) -> {
        return PlattformIUtil.transformAttribute(t);
      };
      List<ExtendedAttribute> _map = ListExtensions.<Attribute, ExtendedAttribute>map(this.tablecolumns, _function);
      this.extendedTableColumnList.addAll(_map);
      final Function1<Attribute, ExtendedAttribute> _function_1 = (Attribute t) -> {
        return PlattformIUtil.transformAttribute(t);
      };
      List<ExtendedAttribute> _map_1 = ListExtensions.<Attribute, ExtendedAttribute>map(this.filters, _function_1);
      this.extendedFiltersList.addAll(_map_1);
      final Function1<Entity, ExtendedEntityImpl> _function_2 = (Entity t) -> {
        return new ExtendedEntityImpl(t);
      };
      List<ExtendedEntityImpl> _map_2 = ListExtensions.<Entity, ExtendedEntityImpl>map(this.entities, _function_2);
      this.extendedEntity.addAll(_map_2);
      if ((this.instance instanceof DetailsPage)) {
        this.isDetailsPage = Boolean.valueOf(true);
        DetailsPage dpg = ((DetailsPage) this.instance);
        EList<DetailPageField> _editfields = dpg.getEditfields();
        final Function1<DetailPageField, ExtendedDetailsPageFieldImpl> _function_3 = (DetailPageField t) -> {
          return new ExtendedDetailsPageFieldImpl(t);
        };
        List<ExtendedDetailsPageFieldImpl> _map_3 = ListExtensions.<DetailPageField, ExtendedDetailsPageFieldImpl>map(_editfields, _function_3);
        this.extendedEditFieldsList.addAll(_map_3);
      }
      BasicEList<ExtendedParameter> _basicEList_5 = new BasicEList<ExtendedParameter>();
      this.extendedGlobalParameterList = _basicEList_5;
      final Function1<Parameter, ExtendedParameterImpl> _function_4 = (Parameter t) -> {
        return new ExtendedParameterImpl(t);
      };
      List<ExtendedParameterImpl> _map_4 = ListExtensions.<Parameter, ExtendedParameterImpl>map(this.globalparameters, _function_4);
      this.extendedGlobalParameterList.addAll(_map_4);
      BasicEList<ExtendedParameter> _basicEList_6 = new BasicEList<ExtendedParameter>();
      this.extendedLocalParameterList = _basicEList_6;
      final Function1<Parameter, ExtendedParameterImpl> _function_5 = (Parameter t) -> {
        return new ExtendedParameterImpl(t);
      };
      List<ExtendedParameterImpl> _map_5 = ListExtensions.<Parameter, ExtendedParameterImpl>map(this.localparameters, _function_5);
      this.extendedLocalParameterList.addAll(_map_5);
      BasicEList<ExtendedParameterGroup> _basicEList_7 = new BasicEList<ExtendedParameterGroup>();
      this.extendedParameterGroupList = _basicEList_7;
      final Function1<ParameterGroup, ExtendedParameterGroupImpl> _function_6 = (ParameterGroup t) -> {
        return new ExtendedParameterGroupImpl(t);
      };
      List<ExtendedParameterGroupImpl> _map_6 = ListExtensions.<ParameterGroup, ExtendedParameterGroupImpl>map(this.parametergroups, _function_6);
      this.extendedParameterGroupList.addAll(_map_6);
      for (final ExtendedAttribute colum : this.extendedTableColumnList) {
        {
          boolean isInList = false;
          for (final ExtendedAttribute filter : this.extendedFiltersList) {
            String _name = colum.getName();
            String _name_1 = filter.getName();
            boolean _equalsIgnoreCase = _name.equalsIgnoreCase(_name_1);
            if (_equalsIgnoreCase) {
              isInList = true;
            }
          }
          if ((!isInList)) {
            this.allAttributeOfFilterAndColum.add(colum);
          }
        }
      }
      _xblockexpression = this.allAttributeOfFilterAndColum.addAll(this.extendedFiltersList);
    }
    return _xblockexpression;
  }
  
  @Override
  public EList<ExtendedAttribute> getExtendedTableColumnList() {
    return this.extendedTableColumnList;
  }
  
  @Override
  public EList<ExtendedAttribute> getExtendFiltersList() {
    return this.extendedFiltersList;
  }
  
  @Override
  public EList<ExtendedDetailPageField> getExtendedEditedFieldsList() {
    return this.extendedEditFieldsList;
  }
  
  @Override
  public EList<ExtendedEntity> getExtendedEntityList() {
    return this.extendedEntity;
  }
  
  @Override
  public DynamicPage getInstance() {
    return this.instance;
  }
  
  @Override
  public Boolean isDetailsPage() {
    return this.isDetailsPage;
  }
  
  @Override
  public EList<ExtendedParameterGroup> getExtendedParametersGroupsListe() {
    return this.extendedParameterGroupList;
  }
  
  @Override
  public EList<ExtendedParameter> getExtendedGlobalParametersListe() {
    return this.extendedGlobalParameterList;
  }
  
  @Override
  public EList<ExtendedParameter> getExtendedLocalParametersListe() {
    return this.extendedLocalParameterList;
  }
  
  @Override
  public EList<ExtendedAttribute> getAllAttributeOfFilterAndColum() {
    return this.allAttributeOfFilterAndColum;
  }
}
