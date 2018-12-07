package de.thm.icampus.joomdd.ejsl.web

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Wolf Rost
 */
@WebServlet(name='UserLogoutService', urlPatterns='/logout')
class UserLogoutService extends HttpServlet {
	override protected doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.session.invalidate();
		resp.setStatus(HttpServletResponse.SC_OK)
	}
}
