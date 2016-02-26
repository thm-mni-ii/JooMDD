package de.thm.icampus.joomdd.ejsl.ui.wizard;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class FileWizard extends Wizard implements INewWizard {

	private static final String PAGE_NAME = "Custom Plug-in Project Wizard 1"; //$NON-NLS-1$
	private static final String WIZARD_NAME = "New EJSL File"; //$NON-NLS-1$
	private IWorkbench _workbench;
	private TemplateSelectionPage _page;
	private URL imgFolder;
	private IStructuredSelection _selection;

	public FileWizard() {
		super();
		setWindowTitle(WIZARD_NAME);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this._workbench = workbench;
		this._selection = selection;
		
		 _page = new TemplateSelectionPage(PAGE_NAME);
		 
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
		_page.setTitle("EJSL Template");
		 _page.setDescription("Select an EJSL template.");
		 
		 addPage(_page);
	}

	@Override
	public boolean performFinish() {
//		Object test = _selection.getFirstElement();//TODO alles
//		IResource test1 = (IResource)test;
//		test1.getProject().
		return true;
	}

}
