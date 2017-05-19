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
	override protected doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
	}
	
	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
	}
}