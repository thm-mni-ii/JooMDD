package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage;

import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.eJSL.StaticPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public interface ExtendedPage extends Page {
  public abstract StaticPage getStaticPageInstance();
  
  public abstract ExtendedDynamicPage getExtendedDynamicPageInstance();
  
  public abstract EList<ExtendedParameter> getExtendedGlobalParamater();
  
  public abstract EList<ExtendedParameter> getExtendedLocalParameter();
  
  public abstract EList<ExtendedParameterGroup> getExtendedParameterGroup();
}
