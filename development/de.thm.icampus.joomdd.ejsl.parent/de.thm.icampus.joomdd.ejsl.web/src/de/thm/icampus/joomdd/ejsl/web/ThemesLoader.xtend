package de.thm.icampus.joomdd.ejsl.web

import javax.servlet.http.HttpServlet
import com.google.gson.Gson
import javax.servlet.http.HttpServletRequest
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletException
import java.io.IOException
import java.io.File
import javax.servlet.annotation.WebServlet
import java.util.Scanner

@WebServlet(name = 'ThemesLoader', urlPatterns = '/themes-loader/*')
class ThemesLoader extends HttpServlet {
	val gson = new Gson
	override protected doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var ServletContext context = this.servletContext;
		var String fullPath = context.getRealPath("theme");
		var File templates = new File(fullPath)
		println(fullPath)
		var String name = req.getParameter("name")
		var StringBuffer buff = new StringBuffer
		
//	    if(name.equalsIgnoreCase("all")){
//	    	for(File theme :templates.listFiles.filter[t | t.name.endsWith(".js")]){
//	    		println(theme.name);
//	    		var String nameTheme = theme.name.split("\\.").get(0)
//	    		if(nameTheme.equalsIgnoreCase("chrome")){
//	    		buff.append('''<option  value="«nameTheme»" >«nameTheme»</option>''')
//	    		}else{
//	    			buff.append('''<option value="«nameTheme»" >«nameTheme»</option>''')
//	    		}
//	    	}
//	    }
//	    
//		
//		resp.status = HttpServletResponse.SC_OK
//		resp.setHeader('Cache-Control', 'no-cache')
//		
//		resp.contentType = 'text/x-json'
//		gson.toJson(buff.toString, resp.writer)
		
	}
	
	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
	}
}