/**
 * generated by iCampus (JooMDD team) 2.9.1
 */
package de.thm.icampus.joomdd.ejsl.eJSL.impl;

import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage;
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair;
import de.thm.icampus.joomdd.ejsl.eJSL.PositionParameter;

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
 * An implementation of the model object '<em><b>Position Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.impl.PositionParameterImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.impl.PositionParameterImpl#getDivid <em>Divid</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.impl.PositionParameterImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.impl.PositionParameterImpl#getKeyvaluepairs <em>Keyvaluepairs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PositionParameterImpl extends MinimalEObjectImpl.Container implements PositionParameter
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
   * The default value of the '{@link #getDivid() <em>Divid</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDivid()
   * @generated
   * @ordered
   */
  protected static final String DIVID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDivid() <em>Divid</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDivid()
   * @generated
   * @ordered
   */
  protected String divid = DIVID_EDEFAULT;

  /**
   * The default value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected static final String TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected String type = TYPE_EDEFAULT;

  /**
   * The cached value of the '{@link #getKeyvaluepairs() <em>Keyvaluepairs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKeyvaluepairs()
   * @generated
   * @ordered
   */
  protected EList<KeyValuePair> keyvaluepairs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PositionParameterImpl()
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
    return EJSLPackage.Literals.POSITION_PARAMETER;
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
      eNotify(new ENotificationImpl(this, Notification.SET, EJSLPackage.POSITION_PARAMETER__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDivid()
  {
    return divid;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDivid(String newDivid)
  {
    String oldDivid = divid;
    divid = newDivid;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EJSLPackage.POSITION_PARAMETER__DIVID, oldDivid, divid));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(String newType)
  {
    String oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EJSLPackage.POSITION_PARAMETER__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<KeyValuePair> getKeyvaluepairs()
  {
    if (keyvaluepairs == null)
    {
      keyvaluepairs = new EObjectContainmentEList<KeyValuePair>(KeyValuePair.class, this, EJSLPackage.POSITION_PARAMETER__KEYVALUEPAIRS);
    }
    return keyvaluepairs;
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
      case EJSLPackage.POSITION_PARAMETER__KEYVALUEPAIRS:
        return ((InternalEList<?>)getKeyvaluepairs()).basicRemove(otherEnd, msgs);
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
      case EJSLPackage.POSITION_PARAMETER__NAME:
        return getName();
      case EJSLPackage.POSITION_PARAMETER__DIVID:
        return getDivid();
      case EJSLPackage.POSITION_PARAMETER__TYPE:
        return getType();
      case EJSLPackage.POSITION_PARAMETER__KEYVALUEPAIRS:
        return getKeyvaluepairs();
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
      case EJSLPackage.POSITION_PARAMETER__NAME:
        setName((String)newValue);
        return;
      case EJSLPackage.POSITION_PARAMETER__DIVID:
        setDivid((String)newValue);
        return;
      case EJSLPackage.POSITION_PARAMETER__TYPE:
        setType((String)newValue);
        return;
      case EJSLPackage.POSITION_PARAMETER__KEYVALUEPAIRS:
        getKeyvaluepairs().clear();
        getKeyvaluepairs().addAll((Collection<? extends KeyValuePair>)newValue);
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
      case EJSLPackage.POSITION_PARAMETER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EJSLPackage.POSITION_PARAMETER__DIVID:
        setDivid(DIVID_EDEFAULT);
        return;
      case EJSLPackage.POSITION_PARAMETER__TYPE:
        setType(TYPE_EDEFAULT);
        return;
      case EJSLPackage.POSITION_PARAMETER__KEYVALUEPAIRS:
        getKeyvaluepairs().clear();
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
      case EJSLPackage.POSITION_PARAMETER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EJSLPackage.POSITION_PARAMETER__DIVID:
        return DIVID_EDEFAULT == null ? divid != null : !DIVID_EDEFAULT.equals(divid);
      case EJSLPackage.POSITION_PARAMETER__TYPE:
        return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
      case EJSLPackage.POSITION_PARAMETER__KEYVALUEPAIRS:
        return keyvaluepairs != null && !keyvaluepairs.isEmpty();
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
    result.append(", divid: ");
    result.append(divid);
    result.append(", type: ");
    result.append(type);
    result.append(')');
    return result.toString();
  }

} //PositionParameterImpl