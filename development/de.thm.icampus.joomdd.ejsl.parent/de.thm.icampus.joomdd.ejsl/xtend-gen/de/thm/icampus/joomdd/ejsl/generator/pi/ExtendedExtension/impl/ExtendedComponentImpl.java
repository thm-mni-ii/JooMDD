package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.BackendSection;
import de.thm.icampus.joomdd.ejsl.eJSL.Component;
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage;
import de.thm.icampus.joomdd.ejsl.eJSL.FrontendSection;
import de.thm.icampus.joomdd.ejsl.eJSL.Language;
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation;
import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.eJSL.PageReference;
import de.thm.icampus.joomdd.ejsl.eJSL.ParameterGroup;
import de.thm.icampus.joomdd.ejsl.eJSL.Section;
import de.thm.icampus.joomdd.ejsl.eJSL.impl.ComponentImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.impl.ExtendedPageReferenceImpl;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformIUtil;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterGroupImpl;
import java.util.List;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ExtendedComponentImpl extends ComponentImpl implements ExtendedComponent {
  private Component instance;
  
  private EList<ExtendedPageReference> backEndPagesReference;
  
  private EList<ExtendedPageReference> fronEndpagesReference;
  
  private EList<ExtendedEntity> allextendedEntity;
  
  private EList<ExtendedParameterGroup> extendedParamaterGroups;
  
  private EList<ExtendedPage> allExtendedPage;
  
  public ExtendedComponentImpl(final Component comp) {
    this.instance = comp;
    String _name = comp.getName();
    String _slugify = PlattformIUtil.slugify(_name);
    this.name = _slugify;
    EList<Language> _languages = comp.getLanguages();
    this.languages = _languages;
    Manifestation _manifest = comp.getManifest();
    this.manifest = _manifest;
    EList<ParameterGroup> _globalParamter = comp.getGlobalParamter();
    this.globalParamter = _globalParamter;
    EList<Section> _sections = comp.getSections();
    this.sections = _sections;
    this.initListen();
  }
  
  public boolean initListen() {
    boolean _xblockexpression = false;
    {
      BasicEList<ExtendedPageReference> _basicEList = new BasicEList<ExtendedPageReference>();
      this.backEndPagesReference = _basicEList;
      BasicEList<ExtendedPageReference> _basicEList_1 = new BasicEList<ExtendedPageReference>();
      this.fronEndpagesReference = _basicEList_1;
      BasicEList<ExtendedPage> _basicEList_2 = new BasicEList<ExtendedPage>();
      this.allExtendedPage = _basicEList_2;
      BasicEList<ExtendedEntity> _basicEList_3 = new BasicEList<ExtendedEntity>();
      this.allextendedEntity = _basicEList_3;
      for (final Section s : this.sections) {
        boolean _matched = false;
        if (!_matched) {
          if (s instanceof BackendSection) {
            _matched=true;
            EList<PageReference> _pageRef = ((BackendSection)s).getPageRef();
            final Function1<PageReference, ExtendedPageReferenceImpl> _function = (PageReference t) -> {
              return new ExtendedPageReferenceImpl(t);
            };
            List<ExtendedPageReferenceImpl> _map = ListExtensions.<PageReference, ExtendedPageReferenceImpl>map(_pageRef, _function);
            this.backEndPagesReference.addAll(_map);
            final Function1<ExtendedPageReference, Boolean> _function_1 = (ExtendedPageReference t) -> {
              Page _page = t.getPage();
              return Boolean.valueOf((_page instanceof DynamicPage));
            };
            Iterable<ExtendedPageReference> _filter = IterableExtensions.<ExtendedPageReference>filter(this.backEndPagesReference, _function_1);
            for (final ExtendedPageReference pf : _filter) {
              {
                ExtendedPage _extendedPage = pf.getExtendedPage();
                ExtendedDynamicPage extp = _extendedPage.getExtendedDynamicPageInstance();
                EList<ExtendedEntity> _extendedEntityList = extp.getExtendedEntityList();
                for (final ExtendedEntity entBackend : _extendedEntityList) {
                  final Function1<ExtendedEntity, Boolean> _function_2 = (ExtendedEntity t) -> {
                    String _name = t.getName();
                    String _name_1 = entBackend.getName();
                    return Boolean.valueOf(Objects.equal(_name, _name_1));
                  };
                  Iterable<ExtendedEntity> _filter_1 = IterableExtensions.<ExtendedEntity>filter(this.allextendedEntity, _function_2);
                  int _size = IterableExtensions.size(_filter_1);
                  boolean _equals = (_size == 0);
                  if (_equals) {
                    this.allextendedEntity.add(entBackend);
                  }
                }
              }
            }
          }
        }
        if (!_matched) {
          if (s instanceof FrontendSection) {
            _matched=true;
            EList<PageReference> _pageRef = ((FrontendSection)s).getPageRef();
            final Function1<PageReference, ExtendedPageReferenceImpl> _function = (PageReference t) -> {
              return new ExtendedPageReferenceImpl(t);
            };
            List<ExtendedPageReferenceImpl> _map = ListExtensions.<PageReference, ExtendedPageReferenceImpl>map(_pageRef, _function);
            this.fronEndpagesReference.addAll(_map);
            final Function1<ExtendedPageReference, Boolean> _function_1 = (ExtendedPageReference t) -> {
              Page _page = t.getPage();
              return Boolean.valueOf((_page instanceof DynamicPage));
            };
            Iterable<ExtendedPageReference> _filter = IterableExtensions.<ExtendedPageReference>filter(this.fronEndpagesReference, _function_1);
            for (final ExtendedPageReference pf : _filter) {
              {
                ExtendedPage _extendedPage = pf.getExtendedPage();
                ExtendedDynamicPage extp = _extendedPage.getExtendedDynamicPageInstance();
                EList<ExtendedEntity> _extendedEntityList = extp.getExtendedEntityList();
                for (final ExtendedEntity entFrontend : _extendedEntityList) {
                  final Function1<ExtendedEntity, Boolean> _function_2 = (ExtendedEntity t) -> {
                    String _name = t.getName();
                    String _name_1 = entFrontend.getName();
                    return Boolean.valueOf(Objects.equal(_name, _name_1));
                  };
                  Iterable<ExtendedEntity> _filter_1 = IterableExtensions.<ExtendedEntity>filter(this.allextendedEntity, _function_2);
                  int _size = IterableExtensions.size(_filter_1);
                  boolean _equals = (_size == 0);
                  if (_equals) {
                    this.allextendedEntity.add(entFrontend);
                  }
                }
              }
            }
          }
        }
      }
      BasicEList<ExtendedParameterGroup> _basicEList_4 = new BasicEList<ExtendedParameterGroup>();
      this.extendedParamaterGroups = _basicEList_4;
      final Function1<ParameterGroup, ExtendedParameterGroupImpl> _function = (ParameterGroup t) -> {
        return new ExtendedParameterGroupImpl(t);
      };
      List<ExtendedParameterGroupImpl> _map = ListExtensions.<ParameterGroup, ExtendedParameterGroupImpl>map(this.globalParamter, _function);
      this.extendedParamaterGroups.addAll(_map);
      final Function1<ExtendedPageReference, ExtendedPage> _function_1 = (ExtendedPageReference t) -> {
        return t.getExtendedPage();
      };
      List<ExtendedPage> _map_1 = ListExtensions.<ExtendedPageReference, ExtendedPage>map(this.backEndPagesReference, _function_1);
      this.allExtendedPage.addAll(_map_1);
      final Function1<ExtendedPageReference, ExtendedPage> _function_2 = (ExtendedPageReference t) -> {
        ExtendedPage _xifexpression = null;
        boolean _contains = this.allExtendedPage.contains(t);
        boolean _not = (!_contains);
        if (_not) {
          _xifexpression = t.getExtendedPage();
        }
        return _xifexpression;
      };
      List<ExtendedPage> _map_2 = ListExtensions.<ExtendedPageReference, ExtendedPage>map(this.fronEndpagesReference, _function_2);
      _xblockexpression = this.allExtendedPage.addAll(_map_2);
    }
    return _xblockexpression;
  }
  
  @Override
  public EList<ExtendedPageReference> getFrontEndExtendedPagerefence() {
    return this.fronEndpagesReference;
  }
  
  @Override
  public EList<ExtendedPageReference> getBackEndExtendedPagerefence() {
    return this.backEndPagesReference;
  }
  
  @Override
  public EList<ExtendedEntity> getAllExtendedEntity() {
    return this.allextendedEntity;
  }
  
  @Override
  public Component getInstance() {
    return this.instance;
  }
  
  @Override
  public EList<ExtendedParameterGroup> getExtendedParameterGroupList() {
    return this.extendedParamaterGroups;
  }
  
  @Override
  public EList<ExtendedPage> getAllExtendedPage() {
    return this.allExtendedPage;
  }
}
