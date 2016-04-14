package de.thm.icampus.joomdd.ejsl.gui

import java.io.File
import java.io.FileInputStream
import java.io.FileWriter
import java.util.Properties

class JooMDDPropertiesHandler   {
	
	public Properties listKonfig
	public boolean defaultSettings = false
	
	new(){
		listKonfig = new Properties
		loadConfig()
		
	}
	
	public def void loadConfig(){
		var File source = new File("generatorProperties.properties")
		if(source.exists){
			var FileInputStream input = new FileInputStream("generatorProperties.properties")
			listKonfig.load(input)
			input.close();
		}else{
			setDefaultValue()
			defaultSettings = true
			var FileWriter output = new FileWriter("generatorProperties.properties")
			output.write("#---Write the Configuration for the Generator here.")
			output.flush
			output.close()
			save()
		}
	}
	
	def save() {
		var FileWriter input = new FileWriter("generatorProperties.properties")
		listKonfig.store(input,"---JooMDD---")
		input.flush
		input.close()
	}
	
	def setDefaultValue() {
		listKonfig.setProperty("page","true")
		listKonfig.setProperty("entities","true")
		listKonfig.setProperty("updateFolder","true")
		listKonfig.setProperty("joomla","true")
		listKonfig.setProperty("wordpress","false")
		listKonfig.setProperty("outputFolder","src-gen")
		
	}
	
	public def boolean existsConfiguration() {
		var File source = new File("generatorProperties.properties")
		println(source.absolutePath)
		if(source.exists){
			return true
		}
		return false
	}
	public def relaodsConfiguration(){
		var FileInputStream input = new FileInputStream("generatorProperties.properties")
		listKonfig = listKonfig = new Properties
			listKonfig.load(input)
			input.close();
	}
	
	def String getKey(String key) {
		return listKonfig.getProperty(key)
	}
	
}