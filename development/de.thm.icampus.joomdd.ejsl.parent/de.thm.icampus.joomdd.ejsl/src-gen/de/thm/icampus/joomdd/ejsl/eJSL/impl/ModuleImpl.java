/**
 * generated by iCampus (JooMDD team) 2.9.1
 */
package de.thm.icampus.joomdd.ejsl.eJSL.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage;
import de.thm.icampus.joomdd.ejsl.eJSL.Module;
import de.thm.icampus.joomdd.ejsl.eJSL.PageReference;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.impl.ModuleImpl#getPageRef <em>Page Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModuleImpl extends ExtensionImpl implements Module
{
  /**
   * The cached value of the '{@link #getPageRef() <em>Page Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPageRef()
   * @generated
   * @ordered
   */
  protected PageReference pageRef;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModuleImpl()
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
    return EJSLPackage.Literals.MODULE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PageReference getPageRef()
  {
    return pageRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPageRef(PageReference newPageRef, NotificationChain msgs)
  {
    PageReference oldPageRef = pageRef;
    pageRef = newPageRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EJSLPackage.MODULE__PAGE_REF, oldPageRef, newPageRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPageRef(PageReference newPageRef)
  {
    if (newPageRef != pageRef)
    {
      NotificationChain msgs = null;
      if (pageRef != null)
        msgs = ((InternalEObject)pageRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EJSLPackage.MODULE__PAGE_REF, null, msgs);
      if (newPageRef != null)
        msgs = ((InternalEObject)newPageRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EJSLPackage.MODULE__PAGE_REF, null, msgs);
      msgs = basicSetPageRef(newPageRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EJSLPackage.MODULE__PAGE_REF, newPageRef, newPageRef));
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
      case EJSLPackage.MODULE__PAGE_REF:
        return basicSetPageRef(null, msgs);
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
      case EJSLPackage.MODULE__PAGE_REF:
        return getPageRef();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case EJSLPackage.MODULE__PAGE_REF:
        setPageRef((PageReference)newValue);
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
      case EJSLPackage.MODULE__PAGE_REF:
        setPageRef((PageReference)null);
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
      case EJSLPackage.MODULE__PAGE_REF:
        return pageRef != null;
    }
    return super.eIsSet(featureID);
  }

} //ModuleImpl
