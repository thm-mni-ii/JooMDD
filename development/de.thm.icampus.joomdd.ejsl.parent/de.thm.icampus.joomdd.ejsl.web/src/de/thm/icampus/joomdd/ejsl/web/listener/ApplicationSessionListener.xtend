package de.thm.icampus.joomdd.ejsl.web.listener

import de.thm.icampus.joomdd.ejsl.web.database.DatabaseLayer
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

/**
 * @author Wolf Rost
 */
class ApplicationSessionListener implements HttpSessionListener {

	override sessionCreated(HttpSessionEvent event) {
   		println("Session Created");
 	}

	override sessionDestroyed(HttpSessionEvent event) {
		var DatabaseLayer db = DatabaseLayer.instance;
		var sessionID = event.session.id;
	    db.removeSession(sessionID)
	    println("Session Destroyed");
  	}
}