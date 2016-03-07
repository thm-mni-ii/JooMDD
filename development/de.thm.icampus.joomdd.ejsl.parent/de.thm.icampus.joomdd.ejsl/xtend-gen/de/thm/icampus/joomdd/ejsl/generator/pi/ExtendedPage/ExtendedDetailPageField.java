package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage;

import de.thm.icampus.joomdd.ejsl.eJSL.DetailPageField;
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public interface ExtendedDetailPageField extends DetailPageField {
  public abstract String getType();
  
  public abstract ExtendedAttribute getExtendedAttribute();
  
  public abstract EList<KeyValuePair> getExtrasKeyValue();
}
