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

@WebServlet(name = 'InstanceLoader', urlPatterns = '/instance-loader/*')
class InstanceLoader extends HttpServlet {
	
	override protected doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var ServletContext context = this.servletContext;
		var String fullPath = context.getRealPath("templates");
		println(fullPath)
		var String name = req.getParameter("name")
	    var File temp = new File(fullPath+"/"+ name)
	    
		
		resp.status = HttpServletResponse.SC_OK
		resp.setHeader('Cache-Control', 'no-cache')
		
		resp.contentType = 'text/x-json'
		val gson = new Gson
		gson.toJson(new Scanner(temp).useDelimiter('\\A').next(), resp.writer)
		
	}
	
	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
	}
	
	
	
}