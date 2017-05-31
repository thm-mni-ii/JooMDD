package de.thm.icampus.joomdd.ejsl.web

import org.eclipse.xtext.resource.IResourceServiceProvider
import java.util.HashMap
import java.util.Map
import java.io.File
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.BasicEList

class SessionProvider {
	
	var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
		
	new () {
		//var String serverPathString = args.get(0)
		//var String serverPathString = "...de.thm.icampus.joomdd.ejsl.parent/de.thm.icampus.joomdd.ejsl.web/WebRoot/workspace";
		var String serverPathString = "/var/lib/tomcat8/webapps/ROOT/workspace";
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