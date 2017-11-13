package de.thm.icampus.joomdd.ejsl.web.database.document

import org.bson.Document
import java.sql.Timestamp
import org.bson.types.ObjectId

class User extends Document {
	private ObjectId id = new ObjectId;
	private String username;
	private String password;
	private String salt;
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