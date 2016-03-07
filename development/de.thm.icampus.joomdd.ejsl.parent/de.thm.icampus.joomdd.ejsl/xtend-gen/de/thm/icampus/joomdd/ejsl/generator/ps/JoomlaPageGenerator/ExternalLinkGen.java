package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.ExternalLink;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.AbstractLinkGenerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Link</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see eJSLGenerator.GeneratorTemplatePackage#getExternalLink()
 * @model
 * @generated
 */
@SuppressWarnings("all")
public class ExternalLinkGen extends AbstractLinkGenerator {
  private ExternalLink link;
  
  public ExternalLinkGen(final ExternalLink link) {
    this.link = link;
  }
  
  @Override
  public CharSequence generateLink(final String sect, final String compname) {
    String _target = this.link.getTarget();
    return _target.toString();
  }
}
