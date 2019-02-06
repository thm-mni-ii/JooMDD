package classes;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;

public class TemplateXMLLoader {
	public static TemplateList loadTemplates(String templatesXML) throws FileNotFoundException{
		return loadTemplates(new File(templatesXML), new File("TemplateList.xsd"));
	}

	public static TemplateList loadTemplates(File templatesXML) throws FileNotFoundException {
		return loadTemplates(templatesXML, new File("TemplateList.xsd"));
	}

	public static TemplateList loadTemplates(String templatesXML, String templatesXSD) throws FileNotFoundException{
		InputStream xmlIS = TemplateXMLLoader.class.getClassLoader().getResourceAsStream(templatesXML);
		InputStream xsdIS = TemplateXMLLoader.class.getClassLoader().getResourceAsStream(templatesXSD);

		File tempXMLFile = null;
		File tempXSDFile = null;

		FileOutputStream outXML = null;
		FileOutputStream outXSD = null;
		
		try {
			tempXMLFile = File.createTempFile("templatesXML", ".tmp");
			tempXMLFile.deleteOnExit();
			tempXSDFile = File.createTempFile("templatesXSD", ".tmp");
			tempXSDFile.deleteOnExit();

			outXML = new FileOutputStream(tempXMLFile);
			outXSD = new FileOutputStream(tempXSDFile);

			byte[] buffer = new byte[1024];
		    while (true) {
		      int bytesRead = xmlIS.read(buffer);
		      if (bytesRead == -1)
		        break;
		      outXML.write(buffer, 0, bytesRead);
		    }
		    
			buffer = new byte[1024];
		    while (true) {
		      int bytesRead = xsdIS.read(buffer);
		      if (bytesRead == -1)
		        break;
		      outXSD.write(buffer, 0, bytesRead);
		    }
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (outXML != null) {
				try {
					outXML.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (outXSD != null) {
				try {
					outXSD.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return loadTemplates(tempXMLFile, tempXSDFile);
	}
	
	public static TemplateList loadTemplates(File templatesXML, File templatesXSD) throws FileNotFoundException{
		InputStream is = new FileInputStream(templatesXML);
		Unmarshaller um;
		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
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
