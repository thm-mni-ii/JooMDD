package de.thm.icampus.joomdd.ejsl.web.listener

import de.thm.icampus.joomdd.ejsl.web.database.DatabaseLayer
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

class ApplicationSessionListener implements HttpSessionListener {

	DatabaseLayer db = DatabaseLayer.instance;

	override sessionCreated(HttpSessionEvent event) {
   		println("Session Created");
 	}

	override sessionDestroyed(HttpSessionEvent event) {
		var sessionID = event.session.id;
	    db.removeSession(sessionID)
	    println("Session Destroyed");
  	}
}