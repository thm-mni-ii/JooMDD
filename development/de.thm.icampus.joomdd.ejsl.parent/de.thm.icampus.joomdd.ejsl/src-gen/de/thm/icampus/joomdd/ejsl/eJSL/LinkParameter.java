/**
 * generated by iCampus (JooMDD team) 2.9.1
 */
package de.thm.icampus.joomdd.ejsl.eJSL;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter#getName <em>Name</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter#getAttvalue <em>Attvalue</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter#isId <em>Id</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getLinkParameter()
 * @model
 * @generated
 */
public interface LinkParameter extends EObject
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
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getLinkParameter_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Attvalue</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attvalue</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attvalue</em>' reference.
   * @see #setAttvalue(Attribute)
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getLinkParameter_Attvalue()
   * @model
   * @generated
   */
  Attribute getAttvalue();

  /**
   * Sets the value of the '{@link de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter#getAttvalue <em>Attvalue</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attvalue</em>' reference.
   * @see #getAttvalue()
   * @generated
   */
  void setAttvalue(Attribute value);

  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(boolean)
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getLinkParameter_Id()
   * @model
   * @generated
   */
  boolean isId();

  /**
   * Sets the value of the '{@link de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter#isId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #isId()
   * @generated
   */
  void setId(boolean value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(String)
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getLinkParameter_Value()
   * @model
   * @generated
   */
  String getValue();

  /**
   * Sets the value of the '{@link de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(String value);

} // LinkParameter
