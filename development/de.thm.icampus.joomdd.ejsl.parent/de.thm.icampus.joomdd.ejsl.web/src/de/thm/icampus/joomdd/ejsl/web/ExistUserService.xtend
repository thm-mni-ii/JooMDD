package de.thm.icampus.joomdd.ejsl.web

import javax.servlet.http.HttpServlet
import javax.servlet.annotation.WebServlet
import org.eclipse.xtext.resource.IResourceServiceProvider
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletException
import javax.servlet.http.Cookie
import java.io.IOException
import com.google.gson.Gson
import java.util.Map
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

@WebServlet(name = 'ExistUserService', urlPatterns = '/exist-user-service/*')
class ExistUserService extends HttpServlet {
	var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
	
	
	override protected doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var String name = req.getParameter("name")
		var String email = req.getParameter("email")
		val gson = new Gson
		var Map<String,Object> users = resourcesProvider.contentTypeToFactoryMap.get("mddsessions") as Map<String,Object>
		resp.status = HttpServletResponse.SC_OK
		resp.setHeader('Cache-Control', 'no-cache')
		resp.contentType = 'text/x-json'
		if(users.containsKey(name) ){
        try {
            var File secret = new File(resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String + "/" + name+"/secret.txt");
            var BufferedReader input = new BufferedReader(new FileReader(secret));
            if(input.ready){
            	var String mail2 = input.readLine
            	println(mail2)
            	if(mail2.compareTo(email)==0){
            		 var Cookie userNamecookie = new Cookie ("joomddusername", name)
					var Cookie userEmailcookie = new Cookie ("joomddemail", email)
					userNamecookie.path ="/"
					userNamecookie.domain = req.serverName
					userEmailcookie.path ="/"
					req.session.setAttribute("joomddusername", name)
					req.session.setAttribute("joomddemail", email)
					resp.addCookie(userNamecookie)
					resp.addCookie(userEmailcookie)
					gson.toJson(true, resp.writer)
            	}else{
						gson.toJson(false, resp.writer)
						}
            }else{
					gson.toJson(false, resp.writer)
				}
            
        } catch ( IOException e ) {
            e.printStackTrace();
        } 
       
		}else{
			gson.toJson(false, resp.writer)
		}
		
		   
		
	}
	
	
}