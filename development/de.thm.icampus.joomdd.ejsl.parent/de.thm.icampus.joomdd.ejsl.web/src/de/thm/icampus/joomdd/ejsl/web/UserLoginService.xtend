package de.thm.icampus.joomdd.ejsl.web

import de.thm.icampus.joomdd.ejsl.web.database.DatabaseLayer
import de.thm.icampus.joomdd.ejsl.web.database.document.Session
import java.io.IOException
import java.security.NoSuchAlgorithmException
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

/**
 * @author Wolf Rost
 */
@WebServlet(name='UserLoginService', urlPatterns='/login')
class UserLoginService extends HttpServlet {

	DatabaseLayer db = DatabaseLayer.instance;

	// Method to check whether the user is logged in or not.
	override protected doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var sessionID = req.session.id
		var storedUser = db.getUserBySessionID(sessionID);

		if (storedUser !== null) {
			resp.setStatus(HttpServletResponse.SC_OK)
		} else {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
		}
	}

	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		val username = req.getParameter("username");
		var password = req.getParameter("password");

		var storedUser = db.getUserByUsername(username)

		if (storedUser !== null) {
			val valid = validatePassword(password, storedUser.password)
			if (valid) {
				var newSessionID = req.changeSessionId
				val timestamp = new Timestamp(new Date().time);
				var session = new Session(storedUser.ID, newSessionID, timestamp);
				db.addSession(session);
				resp.setStatus(HttpServletResponse.SC_OK)
			} else {
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
		}
	}

	/**
	 * From: https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#PBKDF2WithHmacSHA1
	 * Transformed to xtend syntax.
	 */
	def boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
		var String[] parts = storedPassword.split(":");
		var int iterations = Integer.parseInt(parts.get(0));
		var byte[] salt = fromHex(parts.get(1));
		var byte[] hash = fromHex(parts.get(2));

		var PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
		var SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		var byte[] testHash = skf.generateSecret(spec).getEncoded();

		var int diff = hash.length.bitwiseXor(testHash.length);
		for (var int i = 0; i < hash.length && i < testHash.length; i++) {
			diff = diff.bitwiseOr(hash.get(i).bitwiseXor(testHash.get(i)));
		}
		return diff == 0;
	}

	/**
	 * From: https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#PBKDF2WithHmacSHA1
	 * Transformed to xtend syntax.
	 */
	def byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
		var byte[] bytes = newByteArrayOfSize(hex.length() / 2)
		for (var int i = 0; i < bytes.length; i++) {
			bytes.set(i, Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16) as byte)
		}
		return bytes;
	}

	/**
	 * From: https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#PBKDF2WithHmacSHA1
	 * Transformed to xtend syntax.
	 */
	def boolean logicalXOR(boolean x, boolean y) {
		return ( ( x || y ) && ! ( x && y ) );
	}
}
