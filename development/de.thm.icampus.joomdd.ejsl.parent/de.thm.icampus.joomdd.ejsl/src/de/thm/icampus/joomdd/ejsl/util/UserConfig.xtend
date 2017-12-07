package de.thm.icampus.joomdd.ejsl.util

import java.util.Properties
import java.util.concurrent.ConcurrentHashMap

class UserConfig {
	private static UserConfig instance;
	private ConcurrentHashMap<String, Properties> users;

	def static UserConfig getInstance()
  	{
    	if (UserConfig.instance === null)
    	{
      		UserConfig.instance = new UserConfig();
    	}
   		return UserConfig.instance;
    }
	
	// Use getInstance()
	private new (){
		users = new ConcurrentHashMap<String, Properties>();
	}
	
	def Properties getConfig(String userID)
	{
		if (!users.containsKey(userID))
		{
			users.put(userID, new Properties)
		}

		return users.get(userID);
	}
}