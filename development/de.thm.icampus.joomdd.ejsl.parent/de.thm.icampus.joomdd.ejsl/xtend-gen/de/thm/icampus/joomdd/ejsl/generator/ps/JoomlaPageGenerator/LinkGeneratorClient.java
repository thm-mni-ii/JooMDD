package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.ContextLink;
import de.thm.icampus.joomdd.ejsl.eJSL.ExternalLink;
import de.thm.icampus.joomdd.ejsl.eJSL.InternalLink;
import de.thm.icampus.joomdd.ejsl.eJSL.Link;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.ContextLinkGen;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.ExternalLinkGen;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.InternalLinkGen;

@SuppressWarnings("all")
public class LinkGeneratorClient {
  private Link link;
  
  private String sect = "";
  
  private String compname;
  
  private String valueF;
  
  public LinkGeneratorClient(final Link link, final String section, final String componentname, final String vaLueFeatures) {
    this.link = link;
    boolean _equalsIgnoreCase = section.equalsIgnoreCase("BackendSection");
    if (_equalsIgnoreCase) {
      this.sect = section;
    }
    this.compname = componentname;
    this.valueF = vaLueFeatures;
  }
  
  public LinkGeneratorClient(final Link link) {
    this.link = link;
  }
  
  public CharSequence generateLink() {
    final Link link = this.link;
    boolean _matched = false;
    if (!_matched) {
      if (link instanceof ExternalLink) {
        _matched=true;
        ExternalLinkGen extern = new ExternalLinkGen(((ExternalLink)this.link));
        return extern.generateLink(this.sect, this.compname);
      }
    }
    if (!_matched) {
      if (link instanceof InternalLink) {
        _matched=true;
        if ((this.link instanceof ContextLink)) {
          ContextLinkGen intern = new ContextLinkGen(((ContextLink)this.link), this.valueF);
          return intern.generateLink(this.sect, this.compname);
        } else {
          InternalLinkGen intern_1 = new InternalLinkGen(((InternalLink)this.link));
          return intern_1.generateLink(this.sect, this.compname);
        }
      }
    }
    return "//to do it is empty !";
  }
}
