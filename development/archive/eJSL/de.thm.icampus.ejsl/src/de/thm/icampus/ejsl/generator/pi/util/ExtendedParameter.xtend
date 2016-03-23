package de.thm.icampus.ejsl.generator.pi.util

import de.thm.icampus.ejsl.eJSL.Parameter

interface ExtendedParameter extends Parameter {
	
	def String generatorType()
	def Parameter getInstance()
}