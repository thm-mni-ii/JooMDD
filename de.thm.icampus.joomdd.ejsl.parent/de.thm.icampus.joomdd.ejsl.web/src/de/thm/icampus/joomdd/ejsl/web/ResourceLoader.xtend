package de.thm.icampus.joomdd.ejsl.web

import com.google.gson.Gson
import java.io.File
import java.io.IOException
import java.util.Scanner
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import de.thm.icampus.joomdd.ejsl.util.Config
import de.thm.icampus.joomdd.ejsl.web.database.document.User
import de.thm.icampus.joomdd.ejsl.web.database.DatabaseLayer
import java.nio.file.Files
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.nio.file.Paths

/**
 * @author Dieudonne Timma
 */
@WebServlet(name = 'ResourceLoader', urlPatterns = '/resource-loader/*')
class ResourceLoader extends HttpServlet {
	
    Config config = Config.instance;
    
	override protected doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var resourceId = req.getParameter("resource")		
		var workspacePath = config.properties.getProperty("serverPath") + "/" + config.properties.getProperty("workspaceName")
        var HttpServletRequest httpRequest = req as HttpServletRequest;
        var sessionID = httpRequest.session.id
        var userPath = sessionID;
        var DatabaseLayer db = DatabaseLayer.instance;  
        var User user = db.getUserBySessionID(sessionID);
        
        if (user !== null)
        {
            userPath = user.username
        }
        
        var resourcePath = newArrayList(workspacePath, userPath, "src", resourceId).join("/")
    
        var File resourceFile = new File (resourcePath)
        
        if(resourceFile.exists)
        {
            resp.status = HttpServletResponse.SC_OK
            resp.setHeader('Cache-Control', 'no-cache')
            resp.contentType = 'text/x-json'
            
            var scanner = new Scanner(resourceFile)

            var fileContent = ""
            while (scanner.hasNextLine()) {
                fileContent += scanner.useDelimiter('\\A').next 
            }
            scanner.close();
            val gson = new Gson         
            gson.toJson(fileContent, resp.writer)
        }
        else
        {
            resp.status = HttpServletResponse.SC_NOT_FOUND
        }
        resp.flushBuffer
	}
	
	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
	}
	
	
	
}