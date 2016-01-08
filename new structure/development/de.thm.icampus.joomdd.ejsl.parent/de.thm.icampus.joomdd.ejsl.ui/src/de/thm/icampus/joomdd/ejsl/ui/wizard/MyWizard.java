package de.thm.icampus.joomdd.ejsl.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class MyWizard extends Wizard implements INewWizard {

	private static final String PAGE_NAME = "Custom Plug-in Project Wizard"; //$NON-NLS-1$
	private static final String WIZARD_NAME = "New Custom Plug-in Project"; //$NON-NLS-1$
	
	private WizardNewProjectCreationPage _pageOne;
	
	public MyWizard() {
		// TODO Auto-generated constructor stub
		setWindowTitle(WIZARD_NAME);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}
	
	@Override
	 public void addPages() {
	 super.addPages();

	 _pageOne = new WizardNewProjectCreationPage(PAGE_NAME);
	 _pageOne.setTitle("EJSL Project");
	 _pageOne.setDescription("Create a new EJSL project.");

	 addPage(_pageOne);
	 }

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}

}
