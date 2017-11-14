package de.thm.icampus.joomdd.ejsl.web.filter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.servlet.ServletInputStream
import java.io.ByteArrayInputStream
import javax.servlet.ReadListener
import org.apache.commons.io.IOUtils

/**
 * Code from https://stackoverflow.com/questions/4449096/how-to-read-request-getinputstream-multiple-times/38990042#38990042
 * Transformed to xtend syntax. Changed to use IOUtils.
 */
class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private final byte[] body;
	
	new(HttpServletRequest request) {
		super(request)
	    body = IOUtils.toByteArray(request.getReader(), "UTF-8");
	}
	
	override BufferedReader getReader() throws IOException
	{
	    return new BufferedReader(new InputStreamReader(getInputStream()));
	}
	
	override ServletInputStream getInputStream() throws IOException {
	    val ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
	    return new ServletInputStream() {
	        override int read() throws IOException {
	            return byteArrayInputStream.read();
	        }
	
	        override boolean isFinished() {
	            return false;
	        }
	
	        override boolean isReady() {
	            return false;
	        }
	
	        override void setReadListener(ReadListener arg0) {
	        }
    	};
    }
}