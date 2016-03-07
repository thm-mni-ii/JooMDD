package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage;

import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public interface ExtendedDynamicPage extends DynamicPage {
  public abstract EList<ExtendedAttribute> getExtendedTableColumnList();
  
  public abstract EList<ExtendedAttribute> getExtendFiltersList();
  
  public abstract EList<ExtendedDetailPageField> getExtendedEditedFieldsList();
  
  public abstract EList<ExtendedEntity> getExtendedEntityList();
  
  public abstract EList<ExtendedParameterGroup> getExtendedParametersGroupsListe();
  
  public abstract EList<ExtendedParameter> getExtendedGlobalParametersListe();
  
  public abstract EList<ExtendedParameter> getExtendedLocalParametersListe();
  
  public abstract EList<ExtendedAttribute> getAllAttributeOfFilterAndColum();
  
  public abstract Boolean isDetailsPage();
  
  public abstract DynamicPage getInstance();
}
