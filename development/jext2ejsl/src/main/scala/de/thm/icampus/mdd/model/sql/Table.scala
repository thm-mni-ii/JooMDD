package de.thm.icampus.mdd.model.sql

/**
 * Created by alexheinz1110 on 13.08.15.
 */
case class Table(name: String, columns: List[Attribute] = List(),references: List[Reference]=List()) {}
