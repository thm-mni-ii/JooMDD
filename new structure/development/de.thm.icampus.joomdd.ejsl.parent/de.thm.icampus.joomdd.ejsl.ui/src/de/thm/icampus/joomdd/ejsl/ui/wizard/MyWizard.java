package de.thm.icampus.joomdd.ejsl.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.ui.wizard.IProjectInfo;
import org.eclipse.xtext.ui.wizard.Messages;

public class MyWizard extends Wizard implements INewWizard {

	private static final String PAGE_NAME_1 = "Custom Plug-in Project Wizard 1"; //$NON-NLS-1$
	private static final String PAGE_NAME_2 = "Custom Plug-in Project Wizard 2"; //$NON-NLS-1$
	private static final String WIZARD_NAME = "New EJSL Project"; //$NON-NLS-1$
	
	private WizardNewProjectCreationPage _pageOne;
	private ExampleSelectionPage _pageTwo;
	
	private IWorkbench _workbench;
	private IStructuredSelection _selection;
	
	public MyWizard(){
		setWindowTitle(WIZARD_NAME);
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this._workbench = workbench;
		this._selection = selection;
		 _pageOne = new WizardNewProjectCreationPage(PAGE_NAME_1);
		 _pageTwo = new ExampleSelectionPage(PAGE_NAME_2);
	}
	
	@Override
	 public void addPages() {
		 super.addPages();
	
		 _pageOne.setTitle("EJSL Project");
		 _pageOne.setDescription("Create a new EJSL project.");
	
		 _pageTwo.setTitle("EJSL Example");
		 _pageTwo.setDescription("Select an EJSL example.");
		 
		 addPage(_pageOne);
		 addPage(_pageTwo);
	 }

	@Override
	public boolean performFinish() {
		System.out.println(_pageOne.getProjectName() + " " + _pageTwo.getRadio());
		return true;
	}

}
