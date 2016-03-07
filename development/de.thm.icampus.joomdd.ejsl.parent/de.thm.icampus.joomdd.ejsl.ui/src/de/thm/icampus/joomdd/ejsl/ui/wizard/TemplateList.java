package de.thm.icampus.joomdd.ejsl.ui.wizard;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Namespace from XSD
@XmlRootElement(name="templateList", namespace="http://www.example.org/TemplateList")
public class TemplateList {
	
	@XmlElement(name="template", namespace="http://www.example.org/TemplateList")
	private List<Template> list;
	
	public TemplateList(){
		list = new ArrayList<Template>();
	}
	
	public boolean add(Template template){
		return list.add(template);
	}
	
	public int size(){
		return list.size();
	}
	
	public Template[] getTemplates(){
		Object[] tempArray = list.toArray();
		Template[] returnArray = new Template[tempArray.length];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (Template)tempArray[i];
		}
		return returnArray;
	}
	
	@Override
	public String toString(){
		String returnString = "TemplateList:\n";
		for(Template t: list){
			returnString += t.toString() + "\n";
		}
		return returnString;
	}
}
