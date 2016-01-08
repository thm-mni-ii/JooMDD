/**
 * generated by iCampus (JooMDD team) 2.9.1
 */
package de.thm.icampus.joomdd.ejsl.eJSL;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.PageReference#getPage <em>Page</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.PageReference#getPagescr <em>Pagescr</em>}</li>
 *   <li>{@link de.thm.icampus.joomdd.ejsl.eJSL.PageReference#getSect <em>Sect</em>}</li>
 * </ul>
 *
 * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getPageReference()
 * @model
 * @generated
 */
public interface PageReference extends EObject
{
  /**
   * Returns the value of the '<em><b>Page</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Page</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Page</em>' reference.
   * @see #setPage(Page)
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getPageReference_Page()
   * @model
   * @generated
   */
  Page getPage();

  /**
   * Sets the value of the '{@link de.thm.icampus.joomdd.ejsl.eJSL.PageReference#getPage <em>Page</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Page</em>' reference.
   * @see #getPage()
   * @generated
   */
  void setPage(Page value);

  /**
   * Returns the value of the '<em><b>Pagescr</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pagescr</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pagescr</em>' reference.
   * @see #setPagescr(Component)
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getPageReference_Pagescr()
   * @model
   * @generated
   */
  Component getPagescr();

  /**
   * Sets the value of the '{@link de.thm.icampus.joomdd.ejsl.eJSL.PageReference#getPagescr <em>Pagescr</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pagescr</em>' reference.
   * @see #getPagescr()
   * @generated
   */
  void setPagescr(Component value);

  /**
   * Returns the value of the '<em><b>Sect</b></em>' attribute.
   * The literals are from the enumeration {@link de.thm.icampus.joomdd.ejsl.eJSL.SectionReference}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sect</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sect</em>' attribute.
   * @see de.thm.icampus.joomdd.ejsl.eJSL.SectionReference
   * @see #setSect(SectionReference)
   * @see de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage#getPageReference_Sect()
   * @model
   * @generated
   */
  SectionReference getSect();

  /**
   * Sets the value of the '{@link de.thm.icampus.joomdd.ejsl.eJSL.PageReference#getSect <em>Sect</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sect</em>' attribute.
   * @see de.thm.icampus.joomdd.ejsl.eJSL.SectionReference
   * @see #getSect()
   * @generated
   */
  void setSect(SectionReference value);

} // PageReference