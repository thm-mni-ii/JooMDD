package de.thm.icampus.joomdd.ejsl.util

import java.util.Properties
import java.io.InputStream
import java.io.FileInputStream
import java.io.IOException

class Config {
	private static Config instance;
	private Properties properties = new Properties();
	private InputStream input = null;

	def static Config getInstance()
  	{
    	if (Config.instance === null)
    	{
      		Config.instance = new Config();
    	}
   		return Config.instance;
    }
	
	new (){
		if (instance === null)
        {
        	try {
        		var String workingDir = System.getProperty("user.dir");
        		var String path = workingDir + "/conf/config.properties";
        		input = new FileInputStream(path);
				// load a properties file
				properties.load(input);
				properties.setProperty("serverPath", workingDir);
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input !== null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
        }
	}
	
	def getProperties()
	{
		return properties;
	}
}