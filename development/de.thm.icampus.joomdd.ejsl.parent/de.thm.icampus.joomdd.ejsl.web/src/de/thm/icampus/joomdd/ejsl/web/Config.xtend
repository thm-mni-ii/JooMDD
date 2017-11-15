package de.thm.icampus.joomdd.ejsl.web

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
    
    def static Config getInstance(String path)
  	{
    	if (Config.instance === null)
    	{
      		Config.instance = new Config(path);
    	}
   		return Config.instance;
    }
    
    new (){
		
	}
	
	new (String path){
		if (instance === null)
        {
        	try {
        		input = new FileInputStream(path);
				// load a properties file
				properties.load(input);		
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