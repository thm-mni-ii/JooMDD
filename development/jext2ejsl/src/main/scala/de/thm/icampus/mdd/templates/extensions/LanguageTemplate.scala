package de.thm.icampus.mdd.templates.extensions

import de.thm.icampus.mdd.model.Language
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait LanguageTemplate extends BasicTemplate {

  def languagePartial(language: Language, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Language ${language.name} {
         |    keyvaluepairs ${rep(language.entries, keyValuePairPartial)}
         |}""", newline, indent)
  }

  private def keyValuePairPartial(entry: (String, String), newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Key ${entry._1} = "${entry._2}"""", newline, indent)
  }

}
