/**
 */
package de.thm.icampus.ejsl.eJSL.tests;

import de.thm.icampus.ejsl.eJSL.EJSLFactory;
import de.thm.icampus.ejsl.eJSL.ExtensionPackage;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Extension Package</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtensionPackageTest extends ExtensionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ExtensionPackageTest.class);
	}

	/**
	 * Constructs a new Extension Package test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtensionPackageTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Extension Package test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ExtensionPackage getFixture() {
		return (ExtensionPackage)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(EJSLFactory.eINSTANCE.createExtensionPackage());
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

} //ExtensionPackageTest
