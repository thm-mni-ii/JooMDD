package de.thm.icampus.joomdd.ejsl.web.database.document

import java.sql.Timestamp
import org.bson.Document
import org.bson.types.ObjectId

/**
 * @author Wolf Rost
 */
class User extends Document {
	private ObjectId id = new ObjectId;
	private String username;
	private String password;
	private Timestamp timestamp;
	
	new(String username, String password, Timestamp timestamp) {
		this.username = username;
		this.password = password;
		this.timestamp = timestamp;
	}
	
	def getID() {
		return id;
	}
	
	def getUsername() {
		return username;
	}
	
	def getPassword() {
		return password;
	}
	
	def getTimestamp() {
		return timestamp;
	}
	
	def setID(ObjectId id) {
		this.id = id;
	}
}