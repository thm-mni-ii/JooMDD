package de.thm.icampus.mdd.model.sql

/**
 * Created by alexheinz1110 on 13.08.15.
 */
case class Column(name: String, dataType: String, columnSpecs: List[String], isprimary: Boolean = false) {}
