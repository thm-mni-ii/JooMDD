package de.thm.icampus.joomdd.ejsl.web

class Instance {
	private String ressourceName
	private String properties
	
	new (String ressource, String properties){
		this.ressourceName  = ressource
		this.properties = properties
	}
	def void setProperties(String properties){
		this.properties = properties
	}
	def String getProperties(){ 
		return this.properties
	}
	def String getTessourceName(){
		return this.ressourceName
	}
}