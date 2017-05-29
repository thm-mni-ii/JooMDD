package de.thm.icampus.joomdd.ejsl.web

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletException
import java.io.IOException
import java.util.Map
import javax.servlet.http.Cookie
import org.eclipse.xtext.resource.IResourceServiceProvider
import java.util.zip.ZipInputStream
import java.util.zip.ZipEntry
import java.io.File
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import javax.servlet.annotation.WebServlet
import com.google.gson.Gson
import de.thm.icampus.mdd.Main

@WebServlet(name = 'ReverseLoader', urlPatterns = '/reverse-loader/*')
class ReverseLoader extends HttpServlet {
	var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
	
	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var String name = req.session.getAttribute("joomddusername") as String
		var String server = resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String 
		
		var Map<String,Object> users = resourcesProvider.contentTypeToFactoryMap.get("mddsessions") as Map<String,Object>
		if(!checkCookies(req.cookies) || !users.containsKey(req.session.getAttribute("joomddusername")) ){
			resp.sendError(404,"User not im System")
			return
		}
		
	  var ZipInputStream zipIn = new ZipInputStream(req.inputStream)
	 
	  var String folderName = req.getParameter("filename").replace(".zip","").trim
	   println(folderName);
		decompress(zipIn,name,server, folderName)
		var String[] args = #["-m",  server+ "/" +name +"/reverse"+ "/" +folderName+ "/"+folderName+".xml",
			"-o",  server+ "/" +name +"/src/"+folderName+".eJSL","-no-gui"
		]
		Main.main(args)
		resp.status = HttpServletResponse.SC_OK
		resp.setHeader('Cache-Control', 'no-cache')
		resp.contentType = 'text/x-json'
		val gson = new Gson
		gson.toJson(folderName+".eJSL", resp.writer)
	}
	
	private def boolean decompress(ZipInputStream zipIn, String name,String server, String folderName){
		var String revers= server+ "/" +name +"/reverse"+ "/" +folderName 
		var File dest = new File(revers)
		if(!dest.exists){
			dest.mkdir
		}
		println(revers + "hallo")
		var ZipEntry entry = null
		
		while((entry =zipIn.nextEntry)!=null){
			
		  var String filePath = revers + "/" + entry.getName();
            if (!entry.directory) {
            	var String[] folder = entry.name.split("/")
            	var String pathBuilder = new String
            	for(index : 0..<folder.length){
            		pathBuilder = pathBuilder +"/" + folder.get(index)
            		var File tempFolder = new File(revers +pathBuilder )
            		println(pathBuilder)
            		println(revers+pathBuilder)
            		if(!tempFolder.exists){
            			if(index < folder.length-1){
            				tempFolder.mkdir
            				
            			}else{
            				tempFolder.createNewFile
            			}
            		}
            	}

                extractFile(zipIn, filePath);
            } else {
                var File dir = new File(filePath);
                dir.mkdirs();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        return true;
	}
	 private def void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        var BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
      	val byte[] buffer = newByteArrayOfSize(4096)

        var int read = 0;
        while ((read = zipIn.read(buffer)) >0) {
            bos.write(buffer, 0, read);
        }
        bos.close();
    }
	def boolean checkCookies(Cookie[] cookies) {
		var boolean havename = false;
		var boolean haveemail = false
		for(Cookie cook: cookies){
			if(cook.name == "joomddusername" && cook.value != null)
			 havename = true
			 if(cook.name == "joomddemail" && cook.value != null)
			 haveemail = true
		}
		return havename && haveemail
	}
	
}