package de.thm.icampus.ejsl.ui.handler;

import java.io.IOException;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class SerializationHandler extends AbstractHandler implements IHandler {

	@Inject
	private Injector injector;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {	
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object firstElement = structuredSelection.getFirstElement();
			if (firstElement instanceof IFile) {
				IFile model = (IFile) firstElement;
				
				// create a model URI for the selected xtext-file
				URI xtextModelURI = URI.createPlatformResourceURI(model.getFullPath().toString(), true);
				
				// create a model URI for the xmi-file, which has to be generated or updated
				String fullXMIModelName = model.getFullPath().toString().substring(0,
						model.getFullPath().toString().length() - xtextModelURI.fileExtension().length() - 1)
						+ ".xmi";				
				URI xmiModelURI = URI.createPlatformResourceURI(fullXMIModelName, true);
				
				try {
					// run the serializiation
					serializeXtext(xtextModelURI, xmiModelURI);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	/**
	 * Serialization of the parsed DSL-Model which is represented in an .eJSL file
	 * 
	 * @param xtextModelURI
	 * @param xmiModelURI
	 * @throws IOException
	 */
	protected void serializeXtext(URI xtextModelURI, URI xmiModelURI) throws IOException {
		
		// load the xtext model to an xtextresourceset
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		Resource xtextResource = resourceSet.getResource(xtextModelURI, true);
		EcoreUtil.resolveAll(xtextResource);

		// store in a xmi-resoure
		Resource xmiResource = resourceSet.createResource(xmiModelURI);
		System.out.println(xmiResource);
		xmiResource.getContents().add(xtextResource.getContents().get(0));
		try {
			xmiResource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
