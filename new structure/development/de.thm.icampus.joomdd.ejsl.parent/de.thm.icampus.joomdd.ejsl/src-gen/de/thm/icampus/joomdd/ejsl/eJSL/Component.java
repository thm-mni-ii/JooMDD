/**
 * generated by iCampus (JooMDD team) 2.9.1
 */
package de.thm.icampus.joomdd.ejsl.eJSL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.Component#getGlobalParamter <em>Global Paramter</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.Component#getSections <em>Sections</em>}</li>
 * </ul>
 *
 * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getComponent()
 * @model
 * @generated
 */
public interface Component extends Extension
{
  /**
   * Returns the value of the '<em><b>Global Paramter</b></em>' containment reference list.
   * The list contents are of type {@link de.thm.icampus.joomdd.ejsl.eJSL.ParameterGroup}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Global Paramter</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Global Paramter</em>' containment reference list.
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getComponent_GlobalParamter()
   * @model containment="true"
   * @generated
   */
  EList<ParameterGroup> getGlobalParamter();

  /**
   * Returns the value of the '<em><b>Sections</b></em>' containment reference list.
   * The list contents are of type {@link de.thm.icampus.joomdd.ejsl.eJSL.Section}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sections</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sections</em>' containment reference list.
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getComponent_Sections()
   * @model containment="true"
   * @generated
   */
  EList<Section> getSections();

} // Component