/**
 */
package de.thm.icampus.ejsl.eJSL.tests;

import de.thm.icampus.ejsl.eJSL.BackendSection;
import de.thm.icampus.ejsl.eJSL.EJSLFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Backend Section</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class BackendSectionTest extends SectionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(BackendSectionTest.class);
	}

	/**
	 * Constructs a new Backend Section test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BackendSectionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Backend Section test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected BackendSection getFixture() {
		return (BackendSection)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(EJSLFactory.eINSTANCE.createBackendSection());
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

} //BackendSectionTest
