package de.thm.icampus.joomdd.ejsl.generator

import com.google.inject.Inject
import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel
import de.thm.icampus.joomdd.ejsl.generator.ps.EntityGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.ExtensionGenerator
import de.thm.icampus.joomdd.ejsl.generator.ps.PageGenerator
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
import org.eclipse.emf.common.util.EList

/**
 * This is the main class of the code generator of JooMDD. 
 * It generates code from your eJSL model files.
 * 
 */
class EJSLGenerator extends AbstractGenerator {
    private JavaIoFileSystemAccess genData;
    public final static String DEFAULT_OUTPUT_ONCE = "DEFAULT_OUTPUT_ONCE";

    @Inject
    private IEncodingProvider encodingProvider;

    @Inject
    private IResourceServiceProvider.Registry registry;

    Properties config = new Properties()

    override beforeGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
        super.beforeGenerate(input, fsa, context)
        config = new Properties()
        if (registry.contentTypeToFactoryMap.get("serverpath") != null) {
            println("Server started...")
            return
        }
        if (fsa.isFile("generator.properties")) {
            config.load(fsa.readBinaryFile("generator.properties"))
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
        var String serverPath = (registry.contentTypeToFactoryMap.get("serverpath") as String).replace("\\", "/");
        if (serverPath != null) {
            var resourcePath = resource.URI.toFileString.replace("\\", "/");
            resourcePath = resourcePath.replace(serverPath+"/", "")
            var String[] resourceNameArray = resourcePath.split("/")
            var sessionID = resourceNameArray.get(0)
            outputFolder = serverPath + "/" + sessionID + "/src-gen"
        } else {
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
                    	"Extensions/", 
                    	genData, 
                    	domainModel.name, 
                    	outputFolder)
                    mainExtensionGen.dogenerate()
                    // if(config.getKey("entities").equalsIgnoreCase("true")){
                    var EntityGenerator mainEntitiesGen = new EntityGenerator (
                    	extensionPart.feature.entities,
                        "Entities/", 
                        genData, 
                        domainModel.name)
                    mainEntitiesGen.dogenerate()
                    // }
                    // if(config.getKey("page").equalsIgnoreCase("true")){
                    var PageGenerator mainPageGen = new PageGenerator (
                    	extensionPart.feature.pages, 
                    	genData, 
                    	"Pages/",
                        domainModel.name)
                    mainPageGen.dogenerate()
                // }
                }
            }
        }
        fsa.generateFile("status", "The code generation was successfull...")
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
