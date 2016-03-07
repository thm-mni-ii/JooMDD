package de.thm.icampus.joomdd.ejsl.generator.pi.util.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.Parameter;
import de.thm.icampus.joomdd.ejsl.eJSL.ParameterGroup;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.ParameterGroupImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterImpl;
import java.util.List;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ExtendedParameterGroupImpl extends ParameterGroupImpl implements ExtendedParameterGroup {
  private ParameterGroup instance;
  
  private EList<ExtendedParameter> extendedParameterList;
  
  public ExtendedParameterGroupImpl(final ParameterGroup group) {
    this.instance = group;
    String _name = group.getName();
    String _slugify = PlattformIUtil.slugify(_name);
    this.name = _slugify;
    String _label = group.getLabel();
    this.label = _label;
    EList<Parameter> _globalparameters = group.getGlobalparameters();
    this.globalparameters = _globalparameters;
    this.initListen();
  }
  
  public boolean initListen() {
    boolean _xblockexpression = false;
    {
      BasicEList<ExtendedParameter> _basicEList = new BasicEList<ExtendedParameter>();
      this.extendedParameterList = _basicEList;
      final Function1<Parameter, ExtendedParameterImpl> _function = (Parameter t) -> {
        return new ExtendedParameterImpl(t);
      };
      List<ExtendedParameterImpl> _map = ListExtensions.<Parameter, ExtendedParameterImpl>map(this.globalparameters, _function);
      _xblockexpression = this.extendedParameterList.addAll(_map);
    }
    return _xblockexpression;
  }
  
  @Override
  public EList<ExtendedParameter> getExtendedParameterList() {
    return this.extendedParameterList;
  }
}
