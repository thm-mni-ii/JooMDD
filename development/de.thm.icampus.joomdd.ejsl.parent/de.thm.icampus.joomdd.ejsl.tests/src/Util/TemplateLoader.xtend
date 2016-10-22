package Util

import java.io.File
import  com.google.common.io.Files
import java.util.Scanner

class TemplateLoader {
	
	static  def getTemplateFiles() {
		val tModels = newArrayList()
		val templateFolder = new File(TemplateLoader.getClassLoader().getResource("").getPath().replace('tests/bin/', 'ui/templates'));
		for ( template : templateFolder.listFiles) {
			if (Files.getFileExtension(template.path) == 'eJSL') {
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
}