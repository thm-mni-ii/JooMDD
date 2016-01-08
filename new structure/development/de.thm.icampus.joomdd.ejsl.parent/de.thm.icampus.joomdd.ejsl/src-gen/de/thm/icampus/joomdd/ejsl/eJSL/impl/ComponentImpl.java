/**
 * generated by iCampus (JooMDD team) 2.9.1
 */
package de.thm.icampus.joomdd.ejsl.eJSL.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.Component;
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage;
import de.thm.icampus.joomdd.ejsl.eJSL.ParameterGroup;
import de.thm.icampus.joomdd.ejsl.eJSL.Section;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.impl.ComponentImpl#getGlobalParamter <em>Global Paramter</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.impl.ComponentImpl#getSections <em>Sections</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComponentImpl extends ExtensionImpl implements Component
{
  /**
   * The cached value of the '{@link #getGlobalParamter() <em>Global Paramter</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGlobalParamter()
   * @generated
   * @ordered
   */
  protected EList<ParameterGroup> globalParamter;

  /**
   * The cached value of the '{@link #getSections() <em>Sections</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSections()
   * @generated
   * @ordered
   */
  protected EList<Section> sections;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComponentImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return EJSLPackage.Literals.COMPONENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ParameterGroup> getGlobalParamter()
  {
    if (globalParamter == null)
    {
      globalParamter = new EObjectContainmentEList<ParameterGroup>(ParameterGroup.class, this, EJSLPackage.COMPONENT__GLOBAL_PARAMTER);
    }
    return globalParamter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Section> getSections()
  {
    if (sections == null)
    {
      sections = new EObjectContainmentEList<Section>(Section.class, this, EJSLPackage.COMPONENT__SECTIONS);
    }
    return sections;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case EJSLPackage.COMPONENT__GLOBAL_PARAMTER:
        return ((InternalEList<?>)getGlobalParamter()).basicRemove(otherEnd, msgs);
      case EJSLPackage.COMPONENT__SECTIONS:
        return ((InternalEList<?>)getSections()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case EJSLPackage.COMPONENT__GLOBAL_PARAMTER:
        return getGlobalParamter();
      case EJSLPackage.COMPONENT__SECTIONS:
        return getSections();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case EJSLPackage.COMPONENT__GLOBAL_PARAMTER:
        getGlobalParamter().clear();
        getGlobalParamter().addAll((Collection<? extends ParameterGroup>)newValue);
        return;
      case EJSLPackage.COMPONENT__SECTIONS:
        getSections().clear();
        getSections().addAll((Collection<? extends Section>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case EJSLPackage.COMPONENT__GLOBAL_PARAMTER:
        getGlobalParamter().clear();
        return;
      case EJSLPackage.COMPONENT__SECTIONS:
        getSections().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case EJSLPackage.COMPONENT__GLOBAL_PARAMTER:
        return globalParamter != null && !globalParamter.isEmpty();
      case EJSLPackage.COMPONENT__SECTIONS:
        return sections != null && !sections.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ComponentImpl