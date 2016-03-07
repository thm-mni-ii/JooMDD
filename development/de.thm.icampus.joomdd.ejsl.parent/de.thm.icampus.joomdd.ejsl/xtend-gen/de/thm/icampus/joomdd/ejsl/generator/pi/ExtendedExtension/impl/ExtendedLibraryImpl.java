package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.Language;
import de.thm.icampus.joomdd.ejsl.eJSL.Library;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.LibraryImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedLibrary;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil;
import java.util.List;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ExtendedLibraryImpl extends LibraryImpl implements ExtendedLibrary {
  private EList<ExtendedEntity> extendedEntities;
  
  public ExtendedLibraryImpl(final Library lib) {
    EList<Entity> _entities = lib.getEntities();
    this.entities = _entities;
    String _name = lib.getName();
    String _slugify = PlattformIUtil.slugify(_name);
    this.name = _slugify;
    EList<de.thm.icampus.joomdd.ejsl.eJSL.Class> _classes = lib.getClasses();
    this.classes = _classes;
    Manifestation _manifest = lib.getManifest();
    this.manifest = _manifest;
    EList<de.thm.icampus.joomdd.ejsl.eJSL.Package> _packages = lib.getPackages();
    this.packages = _packages;
    EList<Language> _languages = lib.getLanguages();
    this.languages = _languages;
    this.init();
  }
  
  public void init() {
    BasicEList<ExtendedEntity> _basicEList = new BasicEList<ExtendedEntity>();
    this.extendedEntities = _basicEList;
    final Function1<Entity, ExtendedEntityImpl> _function = (Entity t) -> {
      return new ExtendedEntityImpl(t);
    };
    List<ExtendedEntityImpl> _map = ListExtensions.<Entity, ExtendedEntityImpl>map(this.entities, _function);
    this.extendedEntities.addAll(_map);
  }
  
  @Override
  public EList<ExtendedEntity> getExtendedEntities() {
    return this.extendedEntities;
  }
}
