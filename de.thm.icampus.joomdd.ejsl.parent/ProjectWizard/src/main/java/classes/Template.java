package classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;

//Namespace from XSD
@XmlType(namespace="http://www.example.org/TemplateList")
public class Template {
	
	@XmlElement(required=true, namespace="http://www.example.org/TemplateList")
	private String name;
	
	@XmlElement(namespace="http://www.example.org/TemplateList")
	private String description;
	
	@XmlElement(required=true, namespace="http://www.example.org/TemplateList")
	private File src;
	
	@XmlElement(namespace="http://www.example.org/TemplateList")
	private File preview;
	
	/* Default constructor only needed for ClassFactory (XML) */
	@Deprecated
	public Template(){
		this.name = "Shame on you";
		this.src = new File("You used the default constructor");
	}
	
	public Template(String name, String description, File src, File preview){
		if(name == null){
			throw new IllegalArgumentException("name can't be null");
		}
		if(src == null){
			throw new IllegalArgumentException("src can't be null");
		}
		this.name = name;
		this.description = description;
		this.src = src;
		this.preview = preview;
	}
	
	@Override
	public String toString(){
		String returnString = "Name: " + name;
		if(description != null){
			returnString += ", Description: " + description;
		}
		returnString += ", Src: " + src.toString();
		if(preview != null){
			returnString += ", Preview: " + preview.toString();
		}
		return returnString;
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public File getSrc() {
		return src;
	}
	public File getPreview() {
		return preview;
	}
}
