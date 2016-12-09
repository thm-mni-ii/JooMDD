/* createNewProject and performFinish (partialy) copied from package org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard */

package de.thm.icampus.joomdd.ejsl.ui.wizard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.ide.undo.CreateProjectOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import com.google.common.io.Files;
import com.google.common.io.InputSupplier;

public class MyWizard extends Wizard implements INewWizard, IExecutableExtension {

	private static final String PAGE_NAME_1 = "Custom Plug-in Project Wizard 1"; //$NON-NLS-1$
	private static final String PAGE_NAME_2 = "Custom Plug-in Project Wizard 2"; //$NON-NLS-1$
	private static final String PAGE_NAME_3 = "Custom Plug-in Project Wizard 3"; //$NON-NLS-1$
	private static final String WIZARD_NAME = "New EJSL Project"; //$NON-NLS-1$
	
	private WizardNewProjectCreationPage _pageOne;
	private TemplateSelectionPage _pageTwo;
	private TemplateConfigurationPage _pageTree;
	
	
	private EJSLProjectInfo epi;
	private IProject newProject;
	
	private IWorkbench _workbench;

	private URL imgFolder;
	private IConfigurationElement cfge;
	
	public MyWizard(){
		super();
		setWindowTitle(WIZARD_NAME);
		epi = new EJSLProjectInfo();
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this._workbench = workbench;
		
		 _pageOne = new WizardNewProjectCreationPage(PAGE_NAME_1);
		 _pageTwo = new TemplateSelectionPage(PAGE_NAME_2, true);
		 _pageTree = new TemplateConfigurationPage(PAGE_NAME_3, "");
		 
		 try {
			imgFolder = FileLocator.resolve(FileLocator.find(Platform.getBundle("de.thm.icampus.joomdd.ejsl.ui"), new Path("img"), null));
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		 this.setDefaultPageImageDescriptor(new ImageDescriptor() {
			
			@Override
			public ImageData getImageData() {
				try {
					URL u = new URL(imgFolder, "joomdd.PNG");
					ImageData id = new ImageData(u.openStream());
					return id.scaledTo(id.width/6, id.height/6);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
	}
	
	@Override
	 public void addPages() {		 
		 _pageOne.setTitle("EJSL Project");
		 _pageOne.setDescription("Create a new EJSL project.");
	
		 _pageTwo.setTitle("EJSL Template");
		 _pageTwo.setDescription("Select an EJSL template.");
		 _pageTree.setTitle("EJSL Template");
		 _pageTree.setDescription("Select Generator Configuration.");
		 
		 addPage(_pageOne);
		 addPage(_pageTwo);
		 addPage(_pageTree);
	
	 }

	@Override
	public boolean performFinish() {
		createNewProject();
		
		File workspace = _pageOne.getLocationPath().toFile();
		File project = new File(workspace, _pageOne.getProjectName());
		try {	
			File src = new File(project, "src");
			src.mkdir();
			File src_gen = new File(project, "src-gen");
			src_gen.mkdir();
			FileWriter fw = new FileWriter(new File(project, ".classpath"));
			fw.write(epi.classpathFile());
			fw.close();
			Files.copy(new InputSupplier<InputStream>() {
				@Override
				public InputStream getInput() throws IOException {
					return _pageTwo.getSelectedTemplate();
				}
			}, new File(project, "src/" + _pageTwo.getFileName()));	
			_pageTree.saveConfig(src, src_gen);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			//Handling empty file on Files.copy()
		}
		
		if (newProject == null) {
			return false;
		}
		
		try {
			IProjectDescription ipd;
			ipd = newProject.getDescription();
			ipd.setNatureIds(new String[] {"org.eclipse.jdt.core.javanature", "org.eclipse.xtext.ui.shared.xtextNature"});
			newProject.setDescription(ipd, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		IWorkingSet[] workingSets = _pageOne.getSelectedWorkingSets();
		_workbench.getWorkingSetManager().addToWorkingSets(newProject, workingSets);
		
		try {
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(2, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		BasicNewProjectResourceWizard.updatePerspective(cfge);
		BasicNewProjectResourceWizard.selectAndReveal(newProject, _workbench.getActiveWorkbenchWindow());
		
		
		return true;
	}

	private IProject createNewProject() {
		if (newProject != null) {
			return newProject;
		}

		// get a project handle
		final IProject newProjectHandle = _pageOne.getProjectHandle();

		// get a project descriptor
		URI location = null;
		if (!_pageOne.useDefaults()) {
			location = _pageOne.getLocationURI();
		}

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IProjectDescription description = workspace.newProjectDescription(newProjectHandle.getName());
		description.setLocationURI(location);

		//TODO _pageTwo

		// create the new project operation
		IRunnableWithProgress op = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				CreateProjectOperation op = new CreateProjectOperation(description, WIZARD_NAME);
				try {
					// see bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=219901
					// directly execute the operation so that the undo state is
					// not preserved.  Making this undoable resulted in too many
					// accidental file deletions.
					op.execute(monitor, WorkspaceUndoUtil
						.getUIInfoAdapter(getShell()));
				} catch (ExecutionException e) {
					throw new InvocationTargetException(e);
				}
			}
		};

		// run the new project creation operation
		try {
			getContainer().run(true, true, op);
		} catch (InterruptedException e) {
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		newProject = newProjectHandle;

		return newProject;
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException {
		cfge = config;
	}
	
}
