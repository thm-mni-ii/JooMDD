package de.thm.icampus.joomdd.ejsl.validation.elements

import de.thm.icampus.joomdd.ejsl.eJSL.Author
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage
import de.thm.icampus.joomdd.ejsl.eJSL.Manifestation
import java.util.HashSet
import java.util.regex.Pattern
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

/**
 * This class contains custom validation rules about manifests.
 *
 */
class ManifestValidator extends AbstractDeclarativeValidator {
	
	public static val MANIFEST_AUTHOR_AMBIGUOUS = 'ambiguousAuthor'
	public static val MANIFEST_AUTHOR_EMAIL_INVALID = 'invalidAuthorEmail'
	public static val MANIFEST_AURHOR_URL_INVALID = 'invalidAuthorUrl'
	
	/**
	 * A domain consists of one or more domain parts. A domain part may contain
	 * any letter (Unicode), number (0-9) and dash (-) but may not start and
	 * end with a dash (constraint not checked for simplicity). Domain parts
	 * are separated by dot (.).
	 */
	public static val domainPattern = "([\\p{L}0-9-]+\\.)+[\\p{L}0-9]+"

	/**
	 * An e-mail address consists of a user and a domain part which are
	 * separated by an at-sign (@). The user part may contain any letter
	 * (Unicode), number (0-9), dot (.), dash (-), underscore (_) and plus (+).
	 */
	public static val emailPattern = Pattern.compile("[\\p{L}0-9._+-]+?@" + domainPattern)

	/**
	 * An HTTP URL consists of a scheme and a domain. The scheme is limited to
	 * http:// and https://
	 */
	public static val httpUrlPattern = Pattern.compile("https?://" + domainPattern + "(/.*)?")
	
    public override register(EValidatorRegistrar registrar) {}
    
    /**
	 * Checks if the name of an author is used more than once in a manifestation.
	 */
	@Check
	def checkManifestationAuthorsAreUnique(Manifestation manifest) {
		var authors = new HashSet<String>

		for (author : manifest.authors) {
			if (!authors.add(author.name)) {
				warning(
					'Author name is used multiple times.',
					author,
					EJSLPackage.Literals.AUTHOR__NAME,
					de.thm.icampus.joomdd.ejsl.validation.elements.ManifestValidator.MANIFEST_AUTHOR_AMBIGUOUS
				)
			}
		}
	}
	
	/**
	 * Checks if the author uses a valid email adress
	 */	
	@Check
	def checkAuthorEmailIsValid(Author author) {
	    var authorEmail = author.authoremail
	    
	    if(authorEmail !== null) {
	        if (!authorEmail.isEmailAdressValid) {
                error(
                    'Invalid e-mail address. Should be in this format: xxx@xx.xx',
                    EJSLPackage.Literals.AUTHOR__AUTHOREMAIL,
                    de.thm.icampus.joomdd.ejsl.validation.elements.ManifestValidator.MANIFEST_AUTHOR_EMAIL_INVALID
                )
            }
	    }
	}

	/**
	 * Checks if the author uses a valid url
	 */	
	@Check
	def checkAuthorUrlIsValid(Author author) {
	    var authorUrl = author.authorurl
	    
	    if (authorUrl !== null){
	        if (!authorUrl.isUrlValid) {
                warning(
                    'Invalid URL. Should be in this format: http(s)//:www.xxx.xx',
                    EJSLPackage.Literals.AUTHOR__AUTHORURL,
                    de.thm.icampus.joomdd.ejsl.validation.elements.ManifestValidator.MANIFEST_AURHOR_URL_INVALID
                )
            }
	    }
	}

	/**
	 * Method for email matching
	 */	
	def isEmailAdressValid(String address) {
		return emailPattern.matcher(address).matches
	}
	
	/**
	 * Method for url matching
	 */	
	def isUrlValid(String url) {
		return httpUrlPattern.matcher(url).matches
	}
	
}
