package de.thm.icampus.joomdd.ejsl.web

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = 'ThemesLoader', urlPatterns = '/themes-loader/*')
class ThemesLoader extends HttpServlet {
	override protected doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
	}
	
	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
	}
}