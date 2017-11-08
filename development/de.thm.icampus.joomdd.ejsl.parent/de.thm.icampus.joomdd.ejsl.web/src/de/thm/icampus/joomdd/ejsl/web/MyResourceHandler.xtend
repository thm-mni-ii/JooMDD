package de.thm.icampus.joomdd.ejsl.web

import com.google.inject.Inject
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter
import java.util.Map
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.common.util.WrappedException
import org.eclipse.xtext.parser.IEncodingProvider
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.web.server.IServiceContext
import org.eclipse.xtext.web.server.model.IWebDocumentProvider
import org.eclipse.xtext.web.server.model.IWebResourceSetProvider
import org.eclipse.xtext.web.server.model.IXtextWebDocument
import org.eclipse.xtext.web.server.model.XtextWebDocument
import org.eclipse.xtext.web.server.persistence.IServerResourceHandler
import org.eclipse.xtext.web.servlet.HttpServiceContext
import org.eclipse.xtext.web.servlet.HttpSessionWrapper

class MyResourceHandler implements IServerResourceHandler {
	
	@Inject IWebResourceSetProvider resourceSetProvider
	
	@Inject IWebDocumentProvider documentProvider
	
	@Inject IEncodingProvider encodingProvider
	
	override get(String resourceId, IServiceContext serviceContext) throws IOException {
		try {
			var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
			var HttpSessionWrapper session = serviceContext.session as HttpSessionWrapper
			var sessionID = session.session.id
			
			var fullPath = resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String + "/" + sessionID;
			var File resourceFile = new File (fullPath + resourceId)
			
			var File createsrc = new File(fullPath + "/src")
			createsrc.mkdirs;
			var File createrevers = new File(fullPath + "/reverse")
			createrevers.mkdirs;
			
			if(!resourceFile.exists)
			{
				resourceFile.createNewFile
			}

			val URI fis = URI.createFileURI (fullPath + resourceId)
			
			var resourceSet = resourceSetProvider.get(resourceId, serviceContext)
			val XtextResource resource = resourceSet.getResource(fis, true) as XtextResource
	
			var XtextWebDocument  doc = documentProvider.get(resourceId, serviceContext)
			doc.input = resource
			
			return doc
		} catch (Exception exception) {
			throw exception.cause
		}
	}
	
	override put(IXtextWebDocument document, IServiceContext serviceContext) throws IOException {
	  	var HttpSessionWrapper session = serviceContext.session as HttpSessionWrapper
		var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
		var sessionID = session.session.id
		var serverPath = resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String;
		
		var fullPath = serverPath + "/" + sessionID + document.resourceId;
		
		try {
		var File resourcesFile = new File(fullPath)
		
		if(!resourcesFile.exists){
			 	throw new IOException('The resource not found')
			 }
		val URI fis = URI.createFileURI (fullPath)
           
			val outputStream = document.resource.resourceSet.URIConverter.createOutputStream(fis)
			val writer = new OutputStreamWriter(outputStream, encodingProvider.getEncoding(fis))
			
			writer.write(document.text)
			writer.flush;
			writer.close
			outputStream.flush
			outputStream.close
		} catch (Exception exception) {
			throw exception.cause
		}
	}
	
	
}