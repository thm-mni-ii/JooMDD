package de.thm.icampus.joomdd.ejsl.web.filter

import de.thm.icampus.joomdd.ejsl.web.database.DatabaseLayer
import de.thm.icampus.joomdd.ejsl.web.database.document.User
import java.io.IOException
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class XtextServiceFilter implements Filter {
  	FilterConfig filterConfig;  
	DatabaseLayer db = DatabaseLayer.instance;                                      

	override doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		var HttpServletRequest httpRequest = request as HttpServletRequest;
		var String resourceParameterName = "resource";
		var ServletRequest requestWrapper = new MyHttpServletRequestWrapper(httpRequest);
		var String resourceName = httpRequest.getParameter(resourceParameterName)
		
        if(resourceName !== null)
        {
			var String requestURI = httpRequest.getRequestURI();
			
			var sessionID = httpRequest.session.id
			var userPath = sessionID;
			var User user = db.getUserBySessionID(sessionID);
			
			if (user !== null)
			{
				userPath = user.username
			}

        	httpRequest.getRequestDispatcher(requestURI + "?" + resourceParameterName + "=" + userPath + resourceName).forward(requestWrapper, response);
        }
        else
        {
        	chain.doFilter(request, response);  
        }
                                         
  	} 

  	override void init(FilterConfig filterConfig) {              
    	this.filterConfig = filterConfig;
 	} 

  	override void destroy() {                                                  
  	}
}