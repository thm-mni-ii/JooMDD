/*
 * generated by Xtext
 */
package de.thm.icampus.cjsl.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import de.thm.icampus.cjsl.cjsl.Application
import de.thm.icampus.cjsl.generator.ConfigGenerator
import org.eclipse.xtext.generator.JavaIoFileSystemAccess
import org.eclipse.xtext.generator.OutputConfiguration
import java.util.Set
import java.util.Map
import java.util.HashSet
import java.util.HashMap
import org.eclipse.xtext.parser.IEncodingProvider
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.inject.Inject;
import org.eclipse.xtext.resource.IResourceServiceProvider
import java.io.File
import java.io.FileNotFoundException

/**
 * Generates code from your model files on save.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#TutorialCodeGeneration
 */
class CjslGenerator implements IGenerator {
	

private JavaIoFileSystemAccess genData;
public final static String DEFAULT_OUTPUT_ONCE = "DEFAULT_OUTPUT_ONCE";

	@Inject
	private IEncodingProvider encodingProvider;

	@Inject
	private IResourceServiceProvider.Registry registry;
	
	public FileUtil fileUtil = new FileUtil 
	
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
	
	genData = new JavaIoFileSystemAccess(registry,encodingProvider)
	
	

	 for(e: resource.allContents.toIterable.filter(typeof(Application))) {
	 	
	 	
	 	
	 	var File joomlaPath = new File(e.joomlaPath)
	 	var String appsname = e.name
	 	var File applicationDestinatination = new File(e.applicationPath + "/" + appsname) 
	 	var File installFile = new File(applicationDestinatination.path + "/" +"mddinstallation/com")
	 	var boolean newInstallation = false
	 	
	 	if(!applicationDestinatination.exists){
	 	newInstallation = true
	 	 }else{
	 	var int counter = 1
	 	while((installFile.exists == false)  && (applicationDestinatination.exists)){
	 		appsname = e.name + "_" + counter
	 		applicationDestinatination = new File(e.applicationPath + "/" + appsname)
	 		installFile = new File(applicationDestinatination.path + "/" +"mddinstallation/com") 
	 		counter = counter +1
	 		
	 	}
	 	if(installFile.exists==false)
	 	newInstallation = true
	 	}
	 	var ConfigGenerator conf = new ConfigGenerator(genData, e)
	 	var OneInstanzGenerator sqldata = new OneInstanzGenerator(genData, e,appsname,newInstallation ) 
	 	genData.setOutputConfigurations(mapOutputConfigurations(appsname, e.applicationPath))
	 	if(joomlaPath.exists){
	 	if(newInstallation){
	 	fileUtil.extractArchive(joomlaPath,applicationDestinatination)
	 	}
	 	conf.generateConfig
	 	sqldata.generateInstanz
	 	}
	 	else{
	 		throw new FileNotFoundException("The Source Path of Joomla not found")
	 	}
	 	
	}
			
			
}



def Map<String, OutputConfiguration> mapOutputConfigurations(String order, String path) {
    var OutputConfiguration defaultOutput = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
    defaultOutput.setDescription("Output Folder");
    defaultOutput.setOutputDirectory(path + "/" + order);
    defaultOutput.setOverrideExistingResources(true);
    defaultOutput.setCreateOutputDirectory(true);
    defaultOutput.setCleanUpDerivedResources(true);
    defaultOutput.setSetDerivedProperty(true);   
    var Map<String, OutputConfiguration> mapconfig = new HashMap()
    mapconfig.put("DEFAULT_OUTPUT",defaultOutput)
    
    return mapconfig
}


	
	
    
    
}
