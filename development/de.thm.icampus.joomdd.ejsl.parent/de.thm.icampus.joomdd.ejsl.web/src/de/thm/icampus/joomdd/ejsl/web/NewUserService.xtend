package de.thm.icampus.joomdd.ejsl.web

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletException
import java.io.IOException
import org.eclipse.xtext.resource.IResourceServiceProvider
import java.util.Map
import org.eclipse.emf.common.util.BasicEList
import java.io.File
import org.eclipse.emf.common.util.EList
import com.google.gson.Gson
import javax.servlet.http.Cookie
import java.io.BufferedWriter
import java.io.FileWriter

@WebServlet(name = 'NewUserService', urlPatterns = '/new-user-service/*')
class NewUserService  extends HttpServlet  {
	
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
		if(users.containsKey(name)==false && !haveSessionInfo(req.cookies)  ){
		users.put(name, new BasicEList<String>);
		println(name)
		var File createsrc = new File(resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String + "/" + name+"/src")
		createsrc.mkdirs;
	     
        try {
            var File secret = new File(resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String + "/" + name+"/secret.txt");
            var BufferedWriter output = new BufferedWriter(new FileWriter(secret));
            output.write(email + "\n\r");
            output.flush;
            output.close;
        } catch ( IOException e ) {
            e.printStackTrace();
        } 
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
		
		   
		
	}
	
	def boolean haveSessionInfo(Cookie[] cookies) {
		var boolean havename = false;
		var boolean haveemail = false
		if(cookies == null)
		return false
		for(Cookie cook: cookies){
			if(cook.name == "joomddusername" && cook.value != null)
			 havename = true
			 if(cook.name == "joomddemail" && cook.value != null)
			 havename = true
		}
		return havename && haveemail
	}
	
}