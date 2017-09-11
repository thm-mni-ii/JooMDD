package de.thm.icampus.joomdd.ejsl.ui.wizard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.DialogUtil;
import org.eclipse.ui.internal.wizards.newresource.ResourceMessages;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;

public class FileWizard extends BasicNewFileResourceWizard implements INewWizard {

	private static final String PAGE_NAME = "Custom Plug-in Project Wizard 1"; //$NON-NLS-1$
	private static final String WIZARD_NAME = "New eJSL File"; //$NON-NLS-1$
	private WizardNewFileCreationPage _pageOne;
	private TemplateSelectionPage _pageTwo;
	private URL imgFolder;
	private IWorkbench workbench;

	public FileWizard() {
		super();
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(WIZARD_NAME);
		
		_pageTwo = new TemplateSelectionPage(PAGE_NAME, false);
		this.workbench  = workbench;
		 
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
		super.addPages();
		_pageOne = (WizardNewFileCreationPage)super.getPage("newFilePage1");
		_pageOne.setFileName("Model.eJSL");
		_pageOne.setTitle("New eJSL Model File");
		_pageOne.setDescription("Create a new eJSL File.");
		_pageOne.setFileExtension("eJSL");
		
		_pageTwo.setTitle("EJSL Template");
		_pageTwo.setDescription("Select an EJSL template.");
		 
		addPage(_pageTwo);
	}

	@SuppressWarnings("restriction")
	@Override
	public boolean performFinish() {
		IFile ret = _pageOne.createNewFile();
        IWorkbenchWindow dw = getWorkbench().getActiveWorkbenchWindow();

		
		if(ret != null){
			try {
				String fileSep = ret.getLocationURI().getPath();
				File src = new File(fileSep);
				FileWizard.copyFiles(_pageTwo.getSelectedTemplate(),src);
				
				//Files.copy(_pageTwo.getSelectedTemplate(), );
				 if (dw != null) {
		                IWorkbenchPage page = dw.getActivePage();
		                if (page != null) {
		                    IDE.openEditor(page, ret, true);
		                }
		            }
					super.selectAndReveal(ret);

				

		        // Open editor on new file.
		       
			} 
		/**	catch (IOException e) {
				e.printStackTrace();
			}*/ 
			catch (NullPointerException e){
				// Handle empty Files
			}catch (PartInitException e) {
	            DialogUtil.openError(dw.getShell(), ResourceMessages.FileResource_errorMessage,
	                    e.getMessage(), e);
	        }
		}
		
		return true;
	}

	public static void copyFiles(File selectedTemplate, File src) {
		// TODO Auto-generated method stub
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(src));
			BufferedReader br = new BufferedReader(new FileReader(selectedTemplate));
			String sCurrentLine =";";
			while ((sCurrentLine = br.readLine()) != null) {
				writer.append(sCurrentLine + "\n");
			}
			writer.flush();
			writer.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
