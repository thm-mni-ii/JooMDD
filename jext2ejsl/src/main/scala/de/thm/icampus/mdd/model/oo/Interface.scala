package de.thm.icampus.mdd.model.oo

/**
 * Created by alexheinz1110 on 12.08.15.
 */
case class Interface(name: String, isGenerated: Boolean = false, interfaces: List[Interface] = List(), methods: List[Method] = List()) extends Struct {}
