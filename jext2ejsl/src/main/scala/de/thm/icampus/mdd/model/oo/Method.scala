package de.thm.icampus.mdd.model.oo

/**
 * Created by alexheinz1110 on 12.08.15.
 */
case class Method(name: String, accessModifier: String, returnType: Option[String] = None, isGenerated: Boolean = false, params: List[Parameter] = List()) {}
