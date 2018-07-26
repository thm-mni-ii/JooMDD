package de.thm.icampus.joomdd.ejsl.generator

import com.google.inject.Inject
import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel
//import de.thm.icampus.joomdd.ejsl.generator.ps.EntityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.ExtensionGenerator
//import de.thm.icampus.joomdd.ejsl.generator.ps.PageGenerator
import de.thm.icampus.joomdd.ejsl.ressourceTransformator.RessourceTransformer
import java.util.HashMap
import java.util.Map
import java.util.Properties
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.xtext.generator.JavaIoFileSystemAccess
import org.eclipse.xtext.generator.OutputConfiguration
import org.eclipse.xtext.parser.IEncodingProvider
import org.eclipse.xtext.resource.IResourceServiceProvider
import de.thm.icampus.joomdd.ejsl.gui.ConfigGUI
import java.awt.EventQueue
import java.util.regex.Pattern
import java.util.regex.Matcher
import de.thm.icampus.joomdd.ejsl.util.Config
import java.io.InputStream
import de.thm.icampus.joomdd.ejsl.util.UserConfig

/**
 * This is the main class of the code generator of JooMDD. 
 * It generates code from your eJSL model files.
 * 
 * @author Dennis Priefer, Dieudonne Timma Meyatchie
 */
class EJSLGenerator extends AbstractGenerator {
    private JavaIoFileSystemAccess genData;
    public final static String DEFAULT_OUTPUT_ONCE = "DEFAULT_OUTPUT_ONCE";

    @Inject
    private IEncodingProvider encodingProvider;

    @Inject
    private IResourceServiceProvider.Registry registry;

    Properties config
    
	Config webConfig = Config.instance;
	
  	UserConfig userConfig = UserConfig.instance;

    override beforeGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
        super.beforeGenerate(input, fsa, context)
        config = new Properties()
        if (webConfig.properties.getProperty("serverPath") !== null) {
            println("Server started...")
            return
        }
        if (fsa.isFile("generator.properties")) {
            var InputStream is = fsa.readBinaryFile("generator.properties")
            config.load(is)
        } else {
            defaultSettings(fsa)
        }
    }

    def defaultSettings(IFileSystemAccess2 fsa) {
        val ConfigGUI conf = new ConfigGUI
        EventQueue.invokeLater(new Runnable() {
            public override void run() {
                try {

                    conf.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        while (conf.configList.empty) {
        }
        config = conf.configList
        fsa.generateFile("generator.properties", getWriteProperties)
    }

    def CharSequence getWriteProperties() '''
        «FOR prop : config.keySet»
            «IF config.getProperty(prop as String).contains("\\") »
                «prop»=«config.getProperty(prop as String).replaceAll(Pattern.quote("\\"), 
                	Matcher.quoteReplacement("\\\\")
                )»
            «ELSE»
                «prop»=«config.getProperty(prop as String)»
            «ENDIF»
        «ENDFOR»
    '''

    override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
        genData = new JavaIoFileSystemAccess(registry, encodingProvider)
        var String outputFolder 
        var String platform;
                
        if (webConfig.properties.getProperty("serverPath") !== null) {
            var String serverPath = webConfig.properties.getProperty("serverPath").replace("\\", "/"); 
            if (serverPath !== null) {
            	var workspacePath = serverPath + "/" + webConfig.properties.getProperty("workspaceName")
                var resourcePath = resource.URI.toFileString.replace("\\", "/");
                resourcePath = resourcePath.replace(workspacePath + "/", "")
                var String[] resourceNameArray = resourcePath.split("/")
                var sessionID = resourceNameArray.get(0)
        
       	        platform = userConfig.getConfig(sessionID).getProperty("platform")
       	        
       	        println("Platform version: " + platform)
       	        
                outputFolder = workspacePath + "/" + sessionID + "/src-gen"
            }
        } else {
        	platform = config.getProperty("platform", "j3")
            outputFolder = config.getProperty("outputFolder")
        }
        
        println(outputFolder)
        genData.setOutputConfigurations(mapOutputConfigurations(outputFolder))

        for (e : resource.allContents.toIterable.filter(typeof(EJSLModel))) {
            var EJSLModel domainModel = e as EJSLModel;
            switch (domainModel.ejslPart) {
                CMSExtension: {
                    var RessourceTransformer trans = new RessourceTransformer(e)

                    trans.dotransformation
                    var CMSExtension extensionPart = domainModel.ejslPart as CMSExtension

                    var ExtensionGenerator mainExtensionGen = new ExtensionGenerator (
                    	extensionPart.extensions, 
                    	"", 
                    	genData, 
                    	domainModel.name, 
                    	outputFolder,
                    	platform)
                    mainExtensionGen.dogenerate()
                    
                    // ------- Optional entity generator ------------------
                    // if(config.getKey("entities").equalsIgnoreCase("true")){
                    /*var EntityGenerator mainEntitiesGen = new EntityGenerator (
                    	extensionPart.feature.entities,
                        "Entities/", 
                        genData, 
                        domainModel.name)
                    mainEntitiesGen.dogenerate()
                    // }*/
                    // ----------------------------------------------------
                    // ------- Optional page generator --------------------
                    // if(config.getKey("page").equalsIgnoreCase("true")){
                    /*var PageGenerator mainPageGen = new PageGenerator (
                    	extensionPart.feature.pages, 
                    	genData, 
                    	"Pages/",
                        domainModel.name)
                    mainPageGen.dogenerate()
                    // }*/
                    // ---------------------------------------------------
                }
            }
        }
        fsa.generateFile("status", "Code successfully generated.")
    }

    override afterGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
        super.afterGenerate(input, fsa, context)
        fsa.generateFile("generator.properties", getWriteProperties)
        config = new Properties()
    }

    def Map<String, OutputConfiguration> mapOutputConfigurations(String path) {
        var OutputConfiguration defaultOutput;
        defaultOutput = new OutputConfiguration(IFileSystemAccess2.DEFAULT_OUTPUT);
        defaultOutput.setDescription("Output Folder");
        defaultOutput.setOutputDirectory(path);
        defaultOutput.setOverrideExistingResources(true);
        defaultOutput.setCreateOutputDirectory(true);
        defaultOutput.setCleanUpDerivedResources(true);
        defaultOutput.setSetDerivedProperty(true);
        var Map<String, OutputConfiguration> mapconfig = new HashMap()
        mapconfig.put("DEFAULT_OUTPUT", defaultOutput)

        return mapconfig
    }
}
