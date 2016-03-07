package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.StaticPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.DetailsPageTemplate;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.IndexPageTemplate;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public class PageGeneratorClient {
  private ExtendedPage extPage;
  
  private ExtendedComponent com;
  
  private String pathExt;
  
  private IFileSystemAccess fsa;
  
  private String sectionExt;
  
  public PageGeneratorClient(final ExtendedPage page, final ExtendedComponent component, final String path, final String section, final IFileSystemAccess access) {
    this.extPage = page;
    this.com = component;
    this.pathExt = path;
    this.fsa = access;
    this.sectionExt = section;
  }
  
  public CharSequence generateStaticPage(final StaticPage sp) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public void generateView(final ExtendedDynamicPage page, final ExtendedComponent component, final String sec, final String path, final IFileSystemAccess fsa) {
    Boolean _isDetailsPage = page.isDetailsPage();
    if ((_isDetailsPage).booleanValue()) {
      DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path, fsa);
      dp.generateView();
    } else {
      IndexPageTemplate dp_1 = new IndexPageTemplate(page, component, sec, path, fsa);
      dp_1.generateView();
    }
  }
  
  public void generateController(final ExtendedDynamicPage page, final ExtendedComponent component, final String sec, final String path, final IFileSystemAccess fsa) {
    Boolean _isDetailsPage = page.isDetailsPage();
    if ((_isDetailsPage).booleanValue()) {
      DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path, fsa);
      dp.generateController();
    } else {
      IndexPageTemplate dp_1 = new IndexPageTemplate(page, component, sec, path, fsa);
      dp_1.generateController();
    }
  }
  
  public void generateModel(final ExtendedDynamicPage page, final ExtendedComponent component, final String sec, final String path, final IFileSystemAccess fsa) {
    Boolean _isDetailsPage = page.isDetailsPage();
    if ((_isDetailsPage).booleanValue()) {
      DetailsPageTemplate dp = new DetailsPageTemplate(page, component, sec, path, fsa);
      dp.generateModel();
    } else {
      IndexPageTemplate dp_1 = new IndexPageTemplate(page, component, sec, path, fsa);
      dp_1.generateModel();
    }
  }
  
  public void generate() {
    ExtendedDynamicPage _extendedDynamicPageInstance = this.extPage.getExtendedDynamicPageInstance();
    boolean _notEquals = (!Objects.equal(_extendedDynamicPageInstance, null));
    if (_notEquals) {
      String viewPath = (this.pathExt + "/views");
      ExtendedDynamicPage _extendedDynamicPageInstance_1 = this.extPage.getExtendedDynamicPageInstance();
      ExtendedDynamicPage dynPage = ((ExtendedDynamicPage) _extendedDynamicPageInstance_1);
      this.generateView(dynPage, this.com, this.sectionExt, viewPath, this.fsa);
      String controllerpath = (this.pathExt + "/controllers");
      this.generateController(dynPage, this.com, this.sectionExt, controllerpath, this.fsa);
      String modelpath = (this.pathExt + "/models");
      this.generateModel(dynPage, this.com, this.sectionExt, modelpath, this.fsa);
    } else {
      StaticPage _staticPageInstance = this.extPage.getStaticPageInstance();
      boolean _notEquals_1 = (!Objects.equal(_staticPageInstance, null));
      if (_notEquals_1) {
        StaticPage _staticPageInstance_1 = this.extPage.getStaticPageInstance();
        this.generateStaticPage(_staticPageInstance_1);
      }
    }
  }
}
