package de.thm.icampus.mdd.generator.extensions

import de.thm.icampus.mdd.model.Language
import de.thm.icampus.mdd.generator.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait LanguageTemplate extends BasicTemplate {

  def languagePartial(language: Language, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Language ${language.name} {
         |    keyValuePairs ${rep(language.entries, keyValuePairPartial)}
         |}""", newline, indent)
  }

  private def keyValuePairPartial(entry: (String, String), newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |${entry._1} = "${entry._2}"""", newline, indent)
  }

}
