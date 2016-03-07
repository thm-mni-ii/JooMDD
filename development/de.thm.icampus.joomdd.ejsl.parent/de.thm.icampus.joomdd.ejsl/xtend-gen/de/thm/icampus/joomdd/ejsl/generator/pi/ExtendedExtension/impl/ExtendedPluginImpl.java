package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.Language;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.Parameter;
import de.thm.icampus.joomdd.ejsl.eJSL.Plugin;
import de.thm.icampus.joomdd.ejsl.eJSL.PluginKinds;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.PluginImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPlugin;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterImpl;
import java.util.List;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ExtendedPluginImpl extends PluginImpl implements ExtendedPlugin {
  private EList<ExtendedEntity> extendedEntities;
  
  private EList<ExtendedParameter> extendedParameter;
  
  public ExtendedPluginImpl(final Plugin plug) {
    String _name = plug.getName();
    String _slugify = PlattformIUtil.slugify(_name);
    this.name = _slugify;
    Manifestation _manifest = plug.getManifest();
    this.manifest = _manifest;
    PluginKinds _type = plug.getType();
    this.type = _type;
    EList<Language> _languages = plug.getLanguages();
    this.languages = _languages;
    EList<Entity> _entities = plug.getEntities();
    this.entities = _entities;
    EList<Parameter> _localparameters = plug.getLocalparameters();
    this.localparameters = _localparameters;
    this.init();
  }
  
  public void init() {
    BasicEList<ExtendedEntity> _basicEList = new BasicEList<ExtendedEntity>();
    this.extendedEntities = _basicEList;
    BasicEList<ExtendedParameter> _basicEList_1 = new BasicEList<ExtendedParameter>();
    this.extendedParameter = _basicEList_1;
    final Function1<Entity, ExtendedEntityImpl> _function = (Entity t) -> {
      return new ExtendedEntityImpl(t);
    };
    List<ExtendedEntityImpl> _map = ListExtensions.<Entity, ExtendedEntityImpl>map(this.entities, _function);
    this.extendedEntities.addAll(_map);
    final Function1<Parameter, ExtendedParameterImpl> _function_1 = (Parameter t) -> {
      return new ExtendedParameterImpl(t);
    };
    List<ExtendedParameterImpl> _map_1 = ListExtensions.<Parameter, ExtendedParameterImpl>map(this.localparameters, _function_1);
    this.extendedParameter.addAll(_map_1);
  }
  
  @Override
  public EList<ExtendedEntity> getExtendedEntities() {
    return this.extendedEntities;
  }
  
  @Override
  public EList<ExtendedParameter> getExtendedParameter() {
    return this.extendedParameter;
  }
}
