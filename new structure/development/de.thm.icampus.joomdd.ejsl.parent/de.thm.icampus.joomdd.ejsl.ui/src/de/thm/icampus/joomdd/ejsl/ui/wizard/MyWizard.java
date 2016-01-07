package de.thm.icampus.joomdd.ejsl.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class MyWizard extends Wizard implements INewWizard {

	private WizardNewProjectCreationPage _pageOne;
	
	public MyWizard() {
		// TODO Auto-generated constructor stub
		setWindowTitle("Window Title");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}
	
	@Override
	 public void addPages() {
	 super.addPages();

	 _pageOne = new WizardNewProjectCreationPage("From Scratch Project Wizard");
	 _pageOne.setTitle("From Scratch Project");
	 _pageOne.setDescription("Creating something from scratch");

	 addPage(_pageOne);
	 }

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}

}
