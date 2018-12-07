package de.thm.icampus.mdd.model.oo


/**
 * Created by alexheinz1110 on 12.08.15.
 */
case class Clazz(name: String, superClass: Option[String] = None, isGenerated: Boolean = false, interfaces: List[Interface] = List(), attributes: List[Attribute] = List(), methods: List[Method] = List()) extends Struct {}
