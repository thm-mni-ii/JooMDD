package classes;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TemplateXMLLoader {
	public static TemplateList loadTemplates(String templatesXML) throws FileNotFoundException{
		return loadTemplates(new File(templatesXML), new File("TemplateList.xsd"));
	}
	public static TemplateList loadTemplates(File templatesXML) throws FileNotFoundException {
		return loadTemplates(templatesXML, new File("TemplateList.xsd"));
	}
	public static TemplateList loadTemplates(String templatesXML, String templatesXSD) throws FileNotFoundException{
		return loadTemplates(new File(templatesXML), new File(templatesXSD));
	}
	
	public static TemplateList loadTemplates(File templatesXML, File templatesXSD) throws FileNotFoundException{
		InputStream is = new FileInputStream(templatesXML);
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
