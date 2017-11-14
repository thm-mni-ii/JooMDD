package de.thm.icampus.joomdd.ejsl.web

import com.google.inject.Inject
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter
import org.eclipse.xtext.parser.IEncodingProvider
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.web.server.IServiceContext
import org.eclipse.xtext.web.server.model.IWebDocumentProvider
import org.eclipse.xtext.web.server.model.IWebResourceSetProvider
import org.eclipse.xtext.web.server.model.IXtextWebDocument
import org.eclipse.xtext.web.server.model.XtextWebDocument
import org.eclipse.xtext.web.server.persistence.IServerResourceHandler
import org.eclipse.xtext.web.servlet.HttpSessionWrapper
import de.thm.icampus.joomdd.ejsl.web.database.DatabaseLayer
import de.thm.icampus.joomdd.ejsl.web.database.document.User
import org.eclipse.emf.common.util.URI

class MyResourceHandler implements IServerResourceHandler {
	
	@Inject IWebResourceSetProvider resourceSetProvider
	
	@Inject IWebDocumentProvider documentProvider
	
	@Inject IEncodingProvider encodingProvider
	
	DatabaseLayer db = DatabaseLayer.instance;
	
	override get(String resourceId, IServiceContext serviceContext) throws IOException {
		try
		{
			var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
			var workspacePath = resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String + "/";
			var workspaceUserPart = resourceId.split("/").get(0)
			var resourcePath = workspacePath + resourceId;
			var workspaceUser = workspacePath + workspaceUserPart
		
			var File resourceFile = new File (resourcePath)
			
			var File createsrc = new File(workspaceUser + "/src")
			createsrc.mkdirs;
			var File createrevers = new File(workspaceUser + "/reverse")
			createrevers.mkdirs;
			
			if(!resourceFile.exists)
			{
				resourceFile.createNewFile
			}

			val URI fis = URI.createFileURI (resourcePath)
			
			var resourceSet = resourceSetProvider.get(resourceId, serviceContext)
			val XtextResource resource = resourceSet.getResource(fis, true) as XtextResource
			var XtextWebDocument  doc = documentProvider.get(resourceId, serviceContext)
			doc.input = resource
			
			return doc
		}
		catch (Exception exception)
		{
			throw exception.cause
		}
	}
	
	override put(IXtextWebDocument document, IServiceContext serviceContext) throws IOException {
		var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
		var workspacePath = resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String + "/";
		var resourcePath = workspacePath + document.resourceId;
		
		try
		{
			var File resourcesFile = new File(resourcePath)
			
			if(!resourcesFile.exists)
			{
				 	throw new IOException('The resource not found')
			}
			val URI fis = URI.createFileURI (resourcePath)
           
			val outputStream = document.resource.resourceSet.URIConverter.createOutputStream(fis)
			val writer = new OutputStreamWriter(outputStream, encodingProvider.getEncoding(fis))
			
			writer.write(document.text)
			writer.flush;
			writer.close
			outputStream.flush
			outputStream.close
		}
		catch (Exception exception)
		{
			throw exception.cause
		}
	}
	
	
}