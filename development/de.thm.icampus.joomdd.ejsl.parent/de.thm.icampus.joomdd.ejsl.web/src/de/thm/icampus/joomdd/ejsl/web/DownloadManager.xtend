package de.thm.icampus.joomdd.ejsl.web

import javax.servlet.http.HttpServlet
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletException
import java.io.IOException
import org.eclipse.xtext.resource.IResourceServiceProvider
import com.google.gson.Gson
import java.io.File
import java.util.zip.ZipOutputStream
import java.io.FileOutputStream
import java.io.FileInputStream
import java.util.zip.ZipEntry

@WebServlet(name = 'DownLoadManager', urlPatterns = '/download-manager/*')
class DownloadManager extends HttpServlet {
	
	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var String name = req.getParameter("name") as String
		var String[] fileTozip = req.getParameterValues("files[]") 
		println(fileTozip.toString)
		req.session
		resp.status = HttpServletResponse.SC_OK
		resp.setHeader('Cache-Control', 'no-cache')
		
		resp.contentType = 'text/x-json'
		val gson = new Gson
		println("hallo " +req.session.getAttribute("joomddusername") as String+"  test    "+name)
		if(name !=null && (req.session.getAttribute("joomddusername") as String).equals(name) && !fileTozip.empty){
			var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
			val byte[] buffer = newByteArrayOfSize(1024)
			var String serverPath =  resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String
			var String filoutName =serverPath + "/temporyFiles/" + name + ".zip"
			var File existOutFile = new File (filoutName)
			if(existOutFile.exists){
				existOutFile.delete
				
			}
		     var FileOutputStream fileOut = new FileOutputStream(filoutName)
			
			var ZipOutputStream zipOut = new ZipOutputStream(fileOut)
			for(String tempFilename: fileTozip){
				print("hallo manager zip" +tempFilename)
				var File  tempfile = new File(serverPath + "/" + tempFilename)
				if(tempfile.exists){
					if(tempfile.file){
	           		putFile(zipOut, tempfile, buffer,"");
	          
	           	}else{
	           		scanDirectorie(zipOut,buffer,tempfile.getPath(),"" )
	           	}
					
				}
				
				zipOut.close
				fileOut.close;
				gson.toJson("/"+resourcesProvider.contentTypeToFactoryMap.get("serverpathname") as String+"/temporyFiles/" + name + ".zip", resp.writer)
				
			}
			
		}else{
			gson.toJson(false, resp.writer)
		}
		}
		public def void putFile( ZipOutputStream zos, File file,byte[] buffer, String root){
		try{
		 var FileInputStream fis = new FileInputStream(file);
         // begin writing a new ZIP entry, positions the stream to the start of the entry data
         zos.putNextEntry(new ZipEntry( root + file.getName()));
	       var  int length;
         while ((length = fis.read(buffer)) > 0) {
             zos.write(buffer, 0, length);
         }
         zos.closeEntry();
         // close the InputStream

         fis.close();
		}catch (IOException ioe) {

	        System.out.println("Error creating zip file" + ioe);

	    }
	}
	public def void scanDirectorie(ZipOutputStream zos, byte[]buffer, String  src, String path){
		 var File dir = new File(src);

	       var  File[] files = dir.listFiles();
           
	        for (File f : files) {
	           if(! f.isDirectory()){
	           	if(path.isEmpty){
	           		 putFile(zos, f, buffer, "");
	           	}else{
	           		putFile(zos, f, buffer,path);
	          
	           	}
	            }else{
	            		if(path.isEmpty){
	            		    scanDirectorie(zos,buffer,f.getPath(),f.getName() +"/" );
	            			
	            			}
	            		else{	            	
	            			
	            			scanDirectorie(zos,buffer,f.getPath(),path+f.getName() +"/" );}
	            		
	            }
	        }
		}
}