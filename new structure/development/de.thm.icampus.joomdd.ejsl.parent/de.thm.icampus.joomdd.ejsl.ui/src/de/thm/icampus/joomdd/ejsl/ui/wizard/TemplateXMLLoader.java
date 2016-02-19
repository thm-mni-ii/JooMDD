package de.thm.icampus.joomdd.ejsl.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class TemplateXMLLoader {
	public static TemplateList loadTemplates(String templatesXML) throws MalformedURLException, URISyntaxException, IOException{
		return loadTemplates(new File(templatesXML), new File("TemplateList.xsd"));
	}
	public static TemplateList loadTemplates(File templatesXML) throws MalformedURLException, URISyntaxException, IOException {
		return loadTemplates(templatesXML, new File("TemplateList.xsd"));
	}
	public static TemplateList loadTemplates(String templatesXML, String templatesXSD) throws MalformedURLException, URISyntaxException, IOException{
		return loadTemplates(new File(templatesXML), new File(templatesXSD));
	}
	public static TemplateList loadTemplates(URI templatesXML, URI templatesXSD) throws MalformedURLException, IOException{
		return loadTemplates(templatesXML.toURL(), templatesXSD.toURL());
	}
	public static TemplateList loadTemplates(File templatesXML, File templatesXSD) throws URISyntaxException, MalformedURLException, IOException{
		return loadTemplates(templatesXML.toURI(), templatesXSD.toURI());
	}
	
	public static TemplateList loadTemplates(URL templatesXML, URL templatesXSD) throws IOException{
		InputStream is = templatesXML.openStream();
		Unmarshaller um;
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema s;
		
		try {
			s = sf.newSchema(templatesXSD);
			um = JAXBContext.newInstance(TemplateList.class).createUnmarshaller();
			um.setSchema(s);
			return (TemplateList)um.unmarshal(is);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			System.err.println("Warning: TemplateList.xsd not found or faulty, selected XML file could not be validated.");
		
			try{
				um = JAXBContext.newInstance(TemplateList.class).createUnmarshaller();
				return (TemplateList)um.unmarshal(is);
			} catch(JAXBException e1) {
				e1.printStackTrace();
			}
			
		}
		
		return null;
	}
}
