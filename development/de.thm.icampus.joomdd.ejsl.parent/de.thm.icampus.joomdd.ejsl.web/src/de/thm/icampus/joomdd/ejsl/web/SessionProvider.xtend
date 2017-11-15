package de.thm.icampus.joomdd.ejsl.web

import java.io.File
import java.util.HashMap
import java.util.Map
import javax.servlet.ServletContext
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.resource.IResourceServiceProvider

class SessionProvider{
	
	var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
		
	new (ServletContext context) {
  		var String serverPathString = context.getRealPath("/") + "/workspace";
  		
		resourcesProvider.contentTypeToFactoryMap.put("serverpath",serverPathString)
	    resourcesProvider.contentTypeToFactoryMap.put("mddsessions",new HashMap<String, Object>)
	    var Map<String,Object> users = resourcesProvider.contentTypeToFactoryMap.get("mddsessions") as Map<String,Object>
	    var File serverPath = new File(serverPathString)
	    resourcesProvider.contentTypeToFactoryMap.put("serverpathname",serverPath.name)
	    for(File userworkspace: serverPath.listFiles){
	    	var String username = userworkspace.name
	    	var File userfiles = new File(serverPathString+"/"+username+"/src/");
	    	if(userfiles.exists){
	    	var EList<String> resourceName = new BasicEList<String>()
	    	for(File resc: userfiles.listFiles){
	    		resourceName.add(username + "/src/"+resc.name)
	    	}
	    	
	    	users.put(username,resourceName)
	    	
	    	}
	    }
	    var File temporyFile = new File(serverPathString+ "/temporyFiles")
	    if(!temporyFile.exists){
	    	  temporyFile.createNewFile
	    }
	}	
}