package de.thm.icampus.mdd.templates.pages

import de.thm.icampus.mdd.model.extensions.IndexPage
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait IndexPageTemplate extends BasicTemplate {

  def indexPagePartial(indexPage: IndexPage, newline: Boolean = true, indent: Int = 0) = {
    val paramGroupOpt = ?(indexPage.globalParamNames.nonEmpty,
      s"""
         |*ParameterGroups ${rep(indexPage.globalParamNames, simpleParamGroupPartial)}"""
    )

    toTemplate(
      s"""
         |IndexPage ${indexPage.name} {
         |    *Entities ${indexPage.entity}
         |    $paramGroupOpt
         |}""", newline, indent)
  }

  private def simpleParamGroupPartial(paramGroup: String, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |$paramGroup""", newline, indent)
  }

}
