package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.InternalLink;
import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.AbstractLinkGenerator;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Internal Link</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see eJSLGenerator.GeneratorTemplatePackage#getInternalLink()
 * @model
 * @generated
 */
@SuppressWarnings("all")
public class InternalLinkGen extends AbstractLinkGenerator {
  private InternalLink link;
  
  public InternalLinkGen(final InternalLink link) {
    this.link = link;
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
    _builder.append("\"index.php?option=");
    String _nameExtensionBind = Slug.nameExtensionBind("com", compname);
    String _lowerCase = _nameExtensionBind.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("&view=");
    Page _target = this.link.getTarget();
    String _name = _target.getName();
    String _lowerCase_1 = _name.toLowerCase();
    _builder.append(_lowerCase_1, "");
    _builder.append("\"");
    return _builder;
  }
}
