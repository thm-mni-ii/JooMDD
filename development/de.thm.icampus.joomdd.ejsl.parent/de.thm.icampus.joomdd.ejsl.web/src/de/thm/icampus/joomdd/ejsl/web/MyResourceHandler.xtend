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
import java.io.FileOutputStream
import org.eclipse.xtext.web.servlet.HttpServiceContext

class MyResourceHandler implements IServerResourceHandler {
	
	@Inject IWebResourceSetProvider resourceSetProvider
	
	@Inject IWebDocumentProvider documentProvider
	
	@Inject IEncodingProvider encodingProvider
	
	override get(String resourceId, IServiceContext serviceContext) throws IOException {
		try {
			var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
			var httpServiceContext = serviceContext as HttpServiceContext;
			var session = httpServiceContext.request.cookies.findFirst[c|c.name.equals("JSESSIONID")];
			var sessionID = session.value;
			var fullPath = resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String + "/" + sessionID + resourceId;
			var File resourceFile = new File (fullPath)
			if(!resourceFile.exists)
			{
				resourceFile.createNewFile
			}

			val URI fis = URI.createFileURI (fullPath)
			
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
	   var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
		
		var httpServiceContext = serviceContext as HttpServiceContext;
		var session = httpServiceContext.request.cookies.findFirst[c|c.name.equals("JSESSIONID")];
		var sessionID = session.value;
		var fullPath = resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String + "/" + sessionID + document.resourceId;
		
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