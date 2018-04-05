package de.thm.icampus.joomdd.ejsl.web.filter

import javax.servlet.Filter
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.FilterChain
import java.io.IOException
import javax.servlet.ServletException

/**
 * @author Wolf Rost
 */
class CharsetFilter implements Filter {

    private String encoding;

    override void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding === null) encoding = "UTF-8";
    }
    
	override doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Respect the client-specified character encoding
        // (see HTTP specification section 3.4.1)
        if (null === request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }

        // Set the default response content type and encoding
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }

    override void destroy() {
    }
}