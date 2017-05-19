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

class MyResourceHandler implements IServerResourceHandler {
	
	
	@Inject IWebResourceSetProvider resourceSetProvider
	
	@Inject IWebDocumentProvider documentProvider
	
	@Inject IEncodingProvider encodingProvider
	
	var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
	
	override get(String resourceId, IServiceContext serviceContext) throws IOException {
		try {
			var String id = serviceContext.session.get("joomddusername")
			var Map<String,EList<String>> users = resourcesProvider.contentTypeToFactoryMap.get("mddsessions") as Map<String,EList<String>>
			
			var File resourceFile = new File (resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String +"/"+ resourceId)
			if(!resourceFile.exists){
				var String[]resourcearrays = resourceId.split("/");
			var File resourcesFolder = new File(resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String +"/"+ resourcearrays.get(0) +"/src/")
			
			 if(resourcesFolder.exists && resourcesFolder.listFiles.length >=10){
			 	throw new IOException('The folder of resources is full.')
			 }
			 resourceFile.createNewFile
			 
			 
			}
			if(!users.get(id).contains(resourceId)){
				users.get(id).add(resourceId)
			}
			val URI fis = URI.createFileURI ( resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String +"/"+resourceId)
			
			var resourceSet = resourceSetProvider.get(resourceId, serviceContext)
			val XtextResource resource = resourceSet.getResource(fis, true) as XtextResource
	
			var XtextWebDocument  doc = documentProvider.get(resourceId, serviceContext)
			doc.input = resource
			
			return doc
		} catch (WrappedException exception) {
			throw exception.cause
		}
	}
	
	override put(IXtextWebDocument document, IServiceContext serviceContext) throws IOException {
	   
		
		try {
			 var String id = serviceContext.session.get("joomddusername")
			 var Map<String,EList<String>> users = resourcesProvider.contentTypeToFactoryMap.get("mddsessions") as Map<String,EList<String>>
			 if(!users.get(id).contains(document.resourceId)){
				throw new IOException('You can save only your own resources')
			}
		var File resourcesFile = new File(resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String +"/"+document.resourceId)
		if(!resourcesFile.exists){
			 	throw new IOException('The resource not found')
			 }
		val URI fis = URI.createFileURI ( resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String +"/"+ document.resourceId)

			val outputStream = document.resource.resourceSet.URIConverter.createOutputStream(fis)
			val writer = new OutputStreamWriter(outputStream, encodingProvider.getEncoding(fis))
			writer.write(document.text)
			writer.close
		} catch (WrappedException exception) {
			throw exception.cause
		}
	}
	
	
}