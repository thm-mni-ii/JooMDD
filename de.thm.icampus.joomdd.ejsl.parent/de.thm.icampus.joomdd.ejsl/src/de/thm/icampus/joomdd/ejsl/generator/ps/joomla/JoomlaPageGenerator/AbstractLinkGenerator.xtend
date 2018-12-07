package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaPageGenerator;

/**
 * Abstract class for Joomla page links generator classes.
 * 
 * @author Dennis Priefer, Dieudonne Timma Meyatchie
 */
abstract public class AbstractLinkGenerator  {
	
	public def CharSequence generateLink(String sect, String compname);
	
}
