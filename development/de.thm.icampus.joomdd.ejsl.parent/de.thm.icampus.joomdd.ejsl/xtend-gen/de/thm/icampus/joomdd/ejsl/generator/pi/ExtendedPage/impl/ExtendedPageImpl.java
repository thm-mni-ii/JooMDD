package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage;
import de.thm.icampus.joomdd.ejsl.eJSL.Link;
import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.eJSL.Parameter;
import de.thm.icampus.joomdd.ejsl.eJSL.ParameterGroup;
import de.thm.icampus.joomdd.ejsl.eJSL.StaticPage;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.PageImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl.ExtendedDynamicPageImpl;
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
public class ExtendedPageImpl extends PageImpl implements ExtendedPage {
  private Page instance;
  
  private EList<ExtendedParameter> extendedGlobalParameter;
  
  private EList<ExtendedParameter> extendedLocalParamater;
  
  private EList<ExtendedParameterGroup> extendedParamterGroup;
  
  public ExtendedPageImpl(final Page page) {
    this.instance = page;
    String _name = page.getName();
    String _slugify = PlattformIUtil.slugify(_name);
    this.name = _slugify;
    EList<Link> _links = page.getLinks();
    this.links = _links;
    EList<Parameter> _globalparameters = page.getGlobalparameters();
    this.globalparameters = _globalparameters;
    EList<Parameter> _localparameters = page.getLocalparameters();
    this.localparameters = _localparameters;
    EList<ParameterGroup> _parametergroups = page.getParametergroups();
    this.parametergroups = _parametergroups;
    this.initList();
  }
  
  public boolean initList() {
    boolean _xblockexpression = false;
    {
      BasicEList<ExtendedParameter> _basicEList = new BasicEList<ExtendedParameter>();
      this.extendedGlobalParameter = _basicEList;
      BasicEList<ExtendedParameter> _basicEList_1 = new BasicEList<ExtendedParameter>();
      this.extendedLocalParamater = _basicEList_1;
      BasicEList<ExtendedParameterGroup> _basicEList_2 = new BasicEList<ExtendedParameterGroup>();
      this.extendedParamterGroup = _basicEList_2;
      final Function1<Parameter, ExtendedParameterImpl> _function = (Parameter para) -> {
        return new ExtendedParameterImpl(para);
      };
      List<ExtendedParameterImpl> _map = ListExtensions.<Parameter, ExtendedParameterImpl>map(this.globalparameters, _function);
      this.extendedGlobalParameter.addAll(_map);
      final Function1<Parameter, ExtendedParameterImpl> _function_1 = (Parameter par) -> {
        return new ExtendedParameterImpl(par);
      };
      List<ExtendedParameterImpl> _map_1 = ListExtensions.<Parameter, ExtendedParameterImpl>map(this.localparameters, _function_1);
      this.extendedLocalParamater.addAll(_map_1);
      final Function1<ParameterGroup, ExtendedParameterGroupImpl> _function_2 = (ParameterGroup t) -> {
        return new ExtendedParameterGroupImpl(t);
      };
      List<ExtendedParameterGroupImpl> _map_2 = ListExtensions.<ParameterGroup, ExtendedParameterGroupImpl>map(this.parametergroups, _function_2);
      _xblockexpression = this.extendedParamterGroup.addAll(_map_2);
    }
    return _xblockexpression;
  }
  
  @Override
  public StaticPage getStaticPageInstance() {
    if ((this.instance instanceof StaticPage)) {
      return ((StaticPage) this.instance);
    }
    return null;
  }
  
  @Override
  public ExtendedDynamicPage getExtendedDynamicPageInstance() {
    if ((this.instance instanceof DynamicPage)) {
      return new ExtendedDynamicPageImpl(((DynamicPage)this.instance));
    }
    return null;
  }
  
  @Override
  public EList<ExtendedParameter> getExtendedGlobalParamater() {
    return this.extendedGlobalParameter;
  }
  
  @Override
  public EList<ExtendedParameter> getExtendedLocalParameter() {
    return this.extendedLocalParamater;
  }
  
  @Override
  public EList<ExtendedParameterGroup> getExtendedParameterGroup() {
    return this.extendedParamterGroup;
  }
}
