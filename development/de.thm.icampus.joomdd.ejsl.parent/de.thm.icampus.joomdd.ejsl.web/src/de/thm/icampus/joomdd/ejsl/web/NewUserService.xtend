package de.thm.icampus.joomdd.ejsl.web

import com.google.gson.Gson
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.Map
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.xtext.resource.IResourceServiceProvider

@WebServlet(name = 'NewUserService', urlPatterns = '/new-user-service/*')
class NewUserService  extends HttpServlet  {
	
	var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
	
	override protected doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(resourcesProvider != null){
			new SessionProvider(this.servletContext);
		}
		
		var String name = req.getParameter("name")
		var String email = req.getParameter("email")
		
//		var SecretKeySpec secretKey;
//    	var byte[] key;
//		var encryptedSecret = ""
//		var MessageDigest sha = null;
//		try
//        {
//        	key = email.getBytes("UTF-8");
//            sha = MessageDigest.getInstance("SHA-1");
//            key = sha.digest(key);
//            key = Arrays.copyOf(key, 16); 
//            secretKey = new SecretKeySpec(key, "AES");
//            var Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//            encryptedSecret = Base64.getEncoder().encodeToString(cipher.doFinal(name.getBytes("UTF-8")));
//        } 
//        catch (Exception e) 
//        {
//            System.out.println("Error while encrypting: " + e.toString());
//        }

		
		var Map<String,Object> users = resourcesProvider.contentTypeToFactoryMap.get("mddsessions") as Map<String,Object>
		resp.status = HttpServletResponse.SC_OK
		resp.setHeader('Cache-Control', 'no-cache')
		resp.contentType = 'text/x-json'
		val gson = new Gson
		if(name.empty || email.empty){
			gson.toJson(false, resp.writer)
		}else{
		
		if(users.containsKey(name)==false && !haveSessionInfo(req.cookies)  ){
		users.put(name, new BasicEList<String>);
		println(name)
		var File createsrc = new File(resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String + "/" + name+"/src")
		createsrc.mkdirs;
		var File createrevers = new File(resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String + "/" + name+"/reverse")
		createrevers.mkdirs;
	     
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
			 haveemail = true
		}
		return havename && haveemail
	}
	
}