package de.thm.icampus.joomdd.ejsl.web.util

import de.thm.icampus.joomdd.ejsl.web.database.DatabaseLayer
import de.thm.icampus.joomdd.ejsl.web.Config
import de.thm.icampus.joomdd.ejsl.web.database.document.User

class Helper {
	
	static DatabaseLayer db = DatabaseLayer.instance;
	static Config config = Config.instance;
	
	def static getWorkspaceUserPath(String sessionID)
	{		
		var userPath = sessionID;
		var User user = db.getUserBySessionID(sessionID);
		var workspacePath = config.properties.getProperty("serverPath") + "/" + config.properties.getProperty("workspaceName")
		
		if (user !== null)
		{
			userPath = user.username
		}
		
		var workspaceUserPath = workspacePath + "/" + userPath;
		return workspaceUserPath;
	}
	
}