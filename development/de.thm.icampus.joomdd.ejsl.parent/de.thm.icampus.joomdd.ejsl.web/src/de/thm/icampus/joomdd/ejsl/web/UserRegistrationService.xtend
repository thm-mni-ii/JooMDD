package de.thm.icampus.joomdd.ejsl.web

import de.thm.icampus.joomdd.ejsl.web.database.DatabaseLayer
import de.thm.icampus.joomdd.ejsl.web.database.document.User
import java.io.IOException
import java.math.BigInteger
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.sql.Timestamp
import java.util.Date
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = 'UserRegistrationService', urlPatterns = '/register')
class UserRegistrationService extends HttpServlet {
	
	DatabaseLayer db = DatabaseLayer.instance;
		
	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		val username = req.getParameter("username");
		var password = req.getParameter("password");
		val passwordConfirm = req.getParameter("password-confirmation");
		val timestamp = new Timestamp(new Date().time);
		
		if (!username.empty && !password.empty && password.equals(passwordConfirm))
		{
			var storedUser = db.getUserByUsername(username);
			if (storedUser === null)
			{			    
				val passwordHash = generateStorngPasswordHash(password)
						
				var user = new User(username, passwordHash, timestamp);
				
				var added = db.addUser(user);	
				
				if (added)
				{
					resp.setStatus(HttpServletResponse.SC_OK)
				}
				else
				{
					resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
				}
			}
			else
			{
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
			}
		}
		else
		{
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
		}
	}
	
	/**
	 * From: https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#PBKDF2WithHmacSHA1
	 * Transformed to xtend syntax.
	 */
	def String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        var int iterations = 1000;
        var char[] chars = password.toCharArray();
        var byte[] salt = getSalt();
         
        var PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        var SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        var byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }
     
	/**
	 * From: https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#PBKDF2WithHmacSHA1
	 * Transformed to xtend syntax.
	 */
    def byte[] getSalt() throws NoSuchAlgorithmException
    {
        var SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        var byte[] salt = newByteArrayOfSize(16)
        sr.nextBytes(salt);
        return salt;
    }
     
	/**
	 * From: https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#PBKDF2WithHmacSHA1
	 * Transformed to xtend syntax.
	 */
    def String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        var BigInteger bi = new BigInteger(1, array);
        var String hex = bi.toString(16);
        var int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
}