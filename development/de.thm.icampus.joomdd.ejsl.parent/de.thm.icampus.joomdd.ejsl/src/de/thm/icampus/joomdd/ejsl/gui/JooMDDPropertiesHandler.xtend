package de.thm.icampus.joomdd.ejsl.gui

import java.io.StringReader
import java.util.Properties
import org.eclipse.xtext.generator.IFileSystemAccess2

class JooMDDPropertiesHandler   {
	
	public Properties listKonfig
	public boolean defaultSettings = false
	IFileSystemAccess2 writer
	
	new(){
		listKonfig = new Properties
		loadConfig()
		
	}
	
	new(IFileSystemAccess2 access2) {
		writer = access2
		listKonfig = new Properties
		loadConfig()
	}
	
	public def void loadConfig(){
		if(writer.isFile("generatorProperties.properties")){
			var StringBuffer buff = new StringBuffer()
			buff.append(writer.readTextFile("generatorProperties.properties"))
			var StringReader reader = new StringReader(buff.toString)
			
			listKonfig.load(reader)
			
		}else{
			setDefaultValue()
			defaultSettings = true
			writer.generateFile("generatorProperties.properties", "#---Write the Configuration for the Generator here.")
			save()
		}
	}
	
	def save() {
		writer.generateFile("generatorProperties.properties",listKonfig.toString)

	}
	
	def setDefaultValue() {
		listKonfig.setProperty("page","false")
		listKonfig.setProperty("entities","true")
		listKonfig.setProperty("updateFolder","false")
		listKonfig.setProperty("joomla","true")
		listKonfig.setProperty("wordpress","false")
		listKonfig.setProperty("outputFolder","src-gen")
		
	}
	
	public def boolean existsConfiguration() {
	if(writer.isFile("generatorProperties.properties")){
			return true
		}
		return false
	}
	public def relaodsConfiguration(){
var StringBuffer buff = new StringBuffer()
			buff.append(writer.readTextFile("generatorProperties.properties"))
			var StringReader reader = new StringReader(buff.toString)	       
			listKonfig = new Properties
			listKonfig.load(reader)
			
	}
	
	def String getKey(String key) {
		return listKonfig.getProperty(key)
	}
	
}