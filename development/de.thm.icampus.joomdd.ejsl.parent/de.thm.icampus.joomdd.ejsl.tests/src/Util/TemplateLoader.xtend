package Util

import java.io.File
import  com.google.common.io.Files
import java.util.Scanner
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage
import org.eclipse.emf.ecore.resource.Resource
import java.util.Map
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl

class TemplateLoader {
	
	static  def getTemplateFiles() {
		val tModels = newArrayList()
		val templateFolder = new File(TemplateLoader.getClassLoader().getResource("").getPath().replace('tests/bin/', 'ui/templates'));
		for ( template : templateFolder.listFiles) {
			if (Files.getFileExtension(template.path) == 'eJSL' && !Files.getNameWithoutExtension(template.path).equalsIgnoreCase('empty')) {
				tModels.add(new Scanner(template).useDelimiter('\\A').next())
			}
		}
		return tModels
	}
	static  def String getOneTemplateFile(String name) {
	
		val templateFolder = new File(TemplateLoader.getClassLoader().getResource("").getPath().replace('tests/bin/', 'ui/templates'));
		for ( template : templateFolder.listFiles) {
			if (Files.getFileExtension(template.path) == 'eJSL' && Files.getNameWithoutExtension(template.path).equalsIgnoreCase(name)) {
				return new Scanner(template).useDelimiter('\\A').next()
			}
		}
		return ""
	}
	static def EJSLModel loadXMIModel(String name){
		 // Initialize the model
		   		val templateFolder = new File(TemplateLoader.getClassLoader().getResource("").getPath().replace('tests/bin/', 'ui/templates'));
		   
                EJSLPackage.eINSTANCE.eClass()

                // Register the XMI resource factory for the .xmi extension

                var Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE
                var Map<String, Object> m = reg.getExtensionToFactoryMap();
                m.put("xmi", new XMIResourceFactoryImpl());

                // Obtain a new resource set
               var ResourceSet resSet = new ResourceSetImpl();

                // Get the resource
               var  Resource resource = resSet.getResource(URI
                                .createFileURI(	templateFolder.path + "\\"+ name +".xmi"), true);
               
               return resource.getContents().get(0) as EJSLModel;
	}

}