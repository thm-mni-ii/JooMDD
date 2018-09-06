package de.thm.icampus.mdd.model.sql

/**
 * Created by alexheinz1110 on 14.08.15.
 */
case class Reference(attribute: List[String], entity: String, reference: List[String], lower: String  ="-1", upper: String = "*") {}
