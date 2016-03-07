/**
 */
package de.thm.icampus.ejsl.eJSL.tests;

import de.thm.icampus.ejsl.eJSL.DynamicPage;
import de.thm.icampus.ejsl.eJSL.EJSLFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Dynamic Page</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class DynamicPageTest extends PageTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DynamicPageTest.class);
	}

	/**
	 * Constructs a new Dynamic Page test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicPageTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Dynamic Page test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected DynamicPage getFixture() {
		return (DynamicPage)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(EJSLFactory.eINSTANCE.createDynamicPage());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //DynamicPageTest
