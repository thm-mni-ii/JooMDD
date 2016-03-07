package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension;

import de.thm.icampus.joomdd.ejsl.eJSL.Component;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedPage;
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup;
import org.eclipse.emf.common.util.EList;

@SuppressWarnings("all")
public interface ExtendedComponent extends Component {
  public abstract EList<ExtendedPageReference> getFrontEndExtendedPagerefence();
  
  public abstract EList<ExtendedPageReference> getBackEndExtendedPagerefence();
  
  public abstract EList<ExtendedEntity> getAllExtendedEntity();
  
  public abstract EList<ExtendedParameterGroup> getExtendedParameterGroupList();
  
  public abstract EList<ExtendedPage> getAllExtendedPage();
  
  public abstract Component getInstance();
}
