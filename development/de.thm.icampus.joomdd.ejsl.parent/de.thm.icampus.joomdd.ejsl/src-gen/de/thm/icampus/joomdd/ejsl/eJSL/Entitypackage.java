/**
 * generated by iCampus (JooMDD team) 2.9.1
 */
package de.thm.icampus.joomdd.ejsl.eJSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entitypackage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.Entitypackage#getName <em>Name</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.Entitypackage#getEntitypackages <em>Entitypackages</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.Entitypackage#getEntities <em>Entities</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.Entitypackage#getDatatypes <em>Datatypes</em>}</li>
 * </ul>
 *
 * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getEntitypackage()
 * @model
 * @generated
 */
public interface Entitypackage extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getEntitypackage_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.thm.icampus.joomdd.ejsl.eJSL.Entitypackage#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Entitypackages</b></em>' containment reference list.
   * The list contents are of type {@link de.thm.icampus.joomdd.ejsl.eJSL.Entitypackage}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entitypackages</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entitypackages</em>' containment reference list.
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getEntitypackage_Entitypackages()
   * @model containment="true"
   * @generated
   */
  EList<Entitypackage> getEntitypackages();

  /**
   * Returns the value of the '<em><b>Entities</b></em>' containment reference list.
   * The list contents are of type {@link de.thm.icampus.joomdd.ejsl.eJSL.Entity}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entities</em>' containment reference list.
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getEntitypackage_Entities()
   * @model containment="true"
   * @generated
   */
  EList<Entity> getEntities();

  /**
   * Returns the value of the '<em><b>Datatypes</b></em>' containment reference list.
   * The list contents are of type {@link de.thm.icampus.joomdd.ejsl.eJSL.Datatype}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Datatypes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Datatypes</em>' containment reference list.
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getEntitypackage_Datatypes()
   * @model containment="true"
   * @generated
   */
  EList<Datatype> getDatatypes();

} // Entitypackage
