/**
 */
package de.thm.icampus.ejsl.eJSL.tests;

import de.thm.icampus.ejsl.eJSL.DetailsPage;
import de.thm.icampus.ejsl.eJSL.EJSLFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Details Page</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class DetailsPageTest extends DynamicPageTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DetailsPageTest.class);
	}

	/**
	 * Constructs a new Details Page test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DetailsPageTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Details Page test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected DetailsPage getFixture() {
		return (DetailsPage)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(EJSLFactory.eINSTANCE.createDetailsPage());
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

} //DetailsPageTest
