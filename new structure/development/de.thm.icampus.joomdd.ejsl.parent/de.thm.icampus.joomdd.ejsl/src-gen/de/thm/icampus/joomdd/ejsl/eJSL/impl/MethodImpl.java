/**
 * generated by iCampus (JooMDD team) 2.9.1
 */
package de.thm.icampus.joomdd.ejsl.eJSL.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage;
import de.thm.icampus.joomdd.ejsl.eJSL.Method;
import de.thm.icampus.joomdd.ejsl.eJSL.MethodParameter;
import de.thm.icampus.joomdd.ejsl.eJSL.Type;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.impl.MethodImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.impl.MethodImpl#getReturnvalue <em>Returnvalue</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.impl.MethodImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.impl.MethodImpl#getMethodparameters <em>Methodparameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MethodImpl extends MinimalEObjectImpl.Container implements Method
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getReturnvalue() <em>Returnvalue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReturnvalue()
   * @generated
   * @ordered
   */
  protected static final String RETURNVALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getReturnvalue() <em>Returnvalue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReturnvalue()
   * @generated
   * @ordered
   */
  protected String returnvalue = RETURNVALUE_EDEFAULT;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected Type type;

  /**
   * The cached value of the '{@link #getMethodparameters() <em>Methodparameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMethodparameters()
   * @generated
   * @ordered
   */
  protected EList<MethodParameter> methodparameters;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MethodImpl()
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
    return EJSLPackage.Literals.METHOD;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EJSLPackage.METHOD__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getReturnvalue()
  {
    return returnvalue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReturnvalue(String newReturnvalue)
  {
    String oldReturnvalue = returnvalue;
    returnvalue = newReturnvalue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EJSLPackage.METHOD__RETURNVALUE, oldReturnvalue, returnvalue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(Type newType, NotificationChain msgs)
  {
    Type oldType = type;
    type = newType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EJSLPackage.METHOD__TYPE, oldType, newType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(Type newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EJSLPackage.METHOD__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EJSLPackage.METHOD__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EJSLPackage.METHOD__TYPE, newType, newType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<MethodParameter> getMethodparameters()
  {
    if (methodparameters == null)
    {
      methodparameters = new EObjectContainmentEList<MethodParameter>(MethodParameter.class, this, EJSLPackage.METHOD__METHODPARAMETERS);
    }
    return methodparameters;
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
      case EJSLPackage.METHOD__TYPE:
        return basicSetType(null, msgs);
      case EJSLPackage.METHOD__METHODPARAMETERS:
        return ((InternalEList<?>)getMethodparameters()).basicRemove(otherEnd, msgs);
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
      case EJSLPackage.METHOD__NAME:
        return getName();
      case EJSLPackage.METHOD__RETURNVALUE:
        return getReturnvalue();
      case EJSLPackage.METHOD__TYPE:
        return getType();
      case EJSLPackage.METHOD__METHODPARAMETERS:
        return getMethodparameters();
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
      case EJSLPackage.METHOD__NAME:
        setName((String)newValue);
        return;
      case EJSLPackage.METHOD__RETURNVALUE:
        setReturnvalue((String)newValue);
        return;
      case EJSLPackage.METHOD__TYPE:
        setType((Type)newValue);
        return;
      case EJSLPackage.METHOD__METHODPARAMETERS:
        getMethodparameters().clear();
        getMethodparameters().addAll((Collection<? extends MethodParameter>)newValue);
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
      case EJSLPackage.METHOD__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EJSLPackage.METHOD__RETURNVALUE:
        setReturnvalue(RETURNVALUE_EDEFAULT);
        return;
      case EJSLPackage.METHOD__TYPE:
        setType((Type)null);
        return;
      case EJSLPackage.METHOD__METHODPARAMETERS:
        getMethodparameters().clear();
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
      case EJSLPackage.METHOD__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EJSLPackage.METHOD__RETURNVALUE:
        return RETURNVALUE_EDEFAULT == null ? returnvalue != null : !RETURNVALUE_EDEFAULT.equals(returnvalue);
      case EJSLPackage.METHOD__TYPE:
        return type != null;
      case EJSLPackage.METHOD__METHODPARAMETERS:
        return methodparameters != null && !methodparameters.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(", returnvalue: ");
    result.append(returnvalue);
    result.append(')');
    return result.toString();
  }

} //MethodImpl