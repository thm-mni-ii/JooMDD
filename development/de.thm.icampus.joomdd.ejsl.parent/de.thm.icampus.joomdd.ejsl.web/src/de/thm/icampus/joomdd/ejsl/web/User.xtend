package de.thm.icampus.joomdd.ejsl.web

import javax.servlet.http.HttpSession

class User {
	String name
	String pass
	String email
	boolean activ
	HttpSession  seesion
	new(String name, String pass){
		this.name = name
		this.pass = pass
	}
}