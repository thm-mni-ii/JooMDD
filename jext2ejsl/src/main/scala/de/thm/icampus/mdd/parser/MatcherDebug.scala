package de.thm.icampus.mdd.parser

import java.util.regex.Matcher

/**
 * Util for printing regex debug messages.
 */
object MatcherDebug {
  def printGroups(matcher: Matcher): Unit = {
    while (matcher.find()) {
      for (i <- 0 to matcher.groupCount()) {
        println(s"-----------$i-----------")
        println(matcher.group(i))
      }
    }
  }
}
