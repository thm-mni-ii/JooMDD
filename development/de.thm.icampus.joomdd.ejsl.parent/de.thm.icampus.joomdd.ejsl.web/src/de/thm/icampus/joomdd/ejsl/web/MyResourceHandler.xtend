package de.thm.icampus.joomdd.ejsl.web

import com.google.inject.Inject
import de.thm.icampus.joomdd.ejsl.web.database.DatabaseLayer
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.parser.IEncodingProvider
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.web.server.IServiceContext
import org.eclipse.xtext.web.server.model.IWebDocumentProvider
import org.eclipse.xtext.web.server.model.IWebResourceSetProvider
import org.eclipse.xtext.web.server.model.IXtextWebDocument
import org.eclipse.xtext.web.server.model.XtextWebDocument
import org.eclipse.xtext.web.server.persistence.IServerResourceHandler

class MyResourceHandler implements IServerResourceHandler {
	
	@Inject IWebResourceSetProvider resourceSetProvider
	
	@Inject IWebDocumentProvider documentProvider
	
	@Inject IEncodingProvider encodingProvider
	
	DatabaseLayer db = DatabaseLayer.instance;
	Config config = Config.instance;
	
	override get(String resourceId, IServiceContext serviceContext) throws IOException {
		try
		{
			var workspacePath = config.properties.getProperty("serverPath") + "/" + config.properties.getProperty("workspaceName")
			var workspaceUserPart = resourceId.split("/").get(0)
			var resourcePath = workspacePath + "/" + resourceId;
			var workspaceUserPath = workspacePath + "/" + workspaceUserPart
		
			var File resourceFile = new File (resourcePath)
			
			var File createsrc = new File(workspaceUserPath + "/src")
			createsrc.mkdirs;
			var File createrevers = new File(workspaceUserPath + "/reverse")
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
		var workspacePath = config.properties.getProperty("serverPath") + "/" + config.properties.getProperty("workspaceName")
		var resourcePath = workspacePath + "/" + document.resourceId;
		
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