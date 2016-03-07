package de.thm.icampus.joomdd.ejsl.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;

import com.google.common.io.Files;
import com.google.common.io.InputSupplier;

public class FileWizard extends BasicNewFileResourceWizard implements INewWizard {

	private static final String PAGE_NAME = "Custom Plug-in Project Wizard 1"; //$NON-NLS-1$
	private static final String WIZARD_NAME = "New eJSL File"; //$NON-NLS-1$
	private WizardNewFileCreationPage _pageOne;
	private TemplateSelectionPage _pageTwo;
	private URL imgFolder;

	public FileWizard() {
		super();
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(WIZARD_NAME);
		
		_pageTwo = new TemplateSelectionPage(PAGE_NAME, false);
		 
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

	@Override
	public boolean performFinish() {
		boolean ret = super.performFinish();
		if(ret){
			try {
				Files.copy(new InputSupplier<InputStream>() {
						@Override
						public InputStream getInput() throws IOException {
							return _pageTwo.getSelectedTemplate();
						}
					}, new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString(), _pageOne.getContainerFullPath().toString() + "/" + _pageOne.getFileName()));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e){
				// Handle empty Files
			}
		}
		return ret;
	}

}
