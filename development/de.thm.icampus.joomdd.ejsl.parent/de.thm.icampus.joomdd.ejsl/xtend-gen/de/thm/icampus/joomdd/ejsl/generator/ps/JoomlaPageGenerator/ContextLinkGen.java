package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.ContextLink;
import de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter;
import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.AbstractLinkGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context Link</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see eJSLGenerator.GeneratorTemplatePackage#getContextLink()
 * @model
 * @generated
 */
@SuppressWarnings("all")
public class ContextLinkGen extends AbstractLinkGenerator {
  private ContextLink lk;
  
  private String valueF;
  
  public ContextLinkGen(final ContextLink link, final String valueFeatures) {
    this.lk = link;
    this.valueF = valueFeatures;
  }
  
  @Override
  public CharSequence generateLink(final String sect, final String compname) {
    StringConcatenation _builder = new StringConcatenation();
    String _xifexpression = null;
    boolean _isEmpty = sect.isEmpty();
    if (_isEmpty) {
      _xifexpression = "";
    } else {
      _xifexpression = (sect + "/");
    }
    _builder.append(_xifexpression, "");
    _builder.append("\'index.php?option=");
    String _nameExtensionBind = Slug.nameExtensionBind("com", compname);
    String _lowerCase = _nameExtensionBind.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("&view=");
    Page _target = this.lk.getTarget();
    String _name = _target.getName();
    String _lowerCase_1 = _name.toLowerCase();
    _builder.append(_lowerCase_1, "");
    _builder.append("\' ");
    EList<LinkParameter> _linkparameters = this.lk.getLinkparameters();
    CharSequence _genLinkOption = this.genLinkOption(_linkparameters);
    _builder.append(_genLinkOption, "");
    return _builder;
  }
  
  public CharSequence genLinkOption(final EList<LinkParameter> params) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final LinkParameter lp : params) {
        _builder.append(". \'&");
        String _name = lp.getName();
        _builder.append(_name, "");
        _builder.append("=\' . ");
        String _xifexpression = null;
        Attribute _attvalue = lp.getAttvalue();
        boolean _notEquals = (!Objects.equal(_attvalue, null));
        if (_notEquals) {
          Attribute _attvalue_1 = lp.getAttvalue();
          String _name_1 = _attvalue_1.getName();
          String _lowerCase = _name_1.toLowerCase();
          _xifexpression = (this.valueF + _lowerCase);
        } else {
          _xifexpression = lp.getValue();
        }
        _builder.append(_xifexpression, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
}
