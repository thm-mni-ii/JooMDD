package de.thm.icampus.joomdd.ejsl.web.database.document

import org.bson.Document
import java.sql.Timestamp
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.SecretKey
import java.security.NoSuchAlgorithmException

class User extends Document {
	private String username;
	private String password;
	private String salt;
	private Timestamp timestamp;
	
	new(String username, String password, Timestamp timestamp) {
		this.username = username;
		this.password = password;
		this.timestamp = timestamp;
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
}