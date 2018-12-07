package de.thm.icampus.joomdd.ejsl.generator.pi.util

import de.thm.icampus.joomdd.ejsl.eJSL.Parameter

interface ExtendedParameter extends Parameter {
	
	def String generatorType()
	def Parameter getInstance()
}