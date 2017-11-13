package de.thm.icampus.joomdd.ejsl.web.listener

import javax.servlet.http.HttpSessionListener
import javax.servlet.http.HttpSessionEvent
import de.thm.icampus.joomdd.ejsl.web.database.DatabaseLayer

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