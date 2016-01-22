package de.thm.icampus.mdd.parser

/**
 * Created by alexheinz1110 on 13.08.15.
 */
trait Parser {
  def parseFile[T](path: String): List[T]
}
