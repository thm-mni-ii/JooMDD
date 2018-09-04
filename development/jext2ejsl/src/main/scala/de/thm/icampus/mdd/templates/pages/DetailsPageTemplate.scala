package de.thm.icampus.mdd.templates.pages

import de.thm.icampus.mdd.model.extensions.DetailsPage
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait DetailsPageTemplate extends BasicTemplate {

  /**def detailsPagePartial(detailsPage: DetailsPage, newline: Boolean = true, indent: Int = 0) = {
    val paramGroupOpt = ?(detailsPage.globalParamNames.nonEmpty,
      s"""
         |*ParameterGroups ${rep(detailsPage.globalParamNames, simpleParamGroupPartial)}"""
    )

    toTemplate(
      s"""
         |DetailsPage ${detailsPage.name} {
         |    *Entities ${detailsPage.entity}
         |    $paramGroupOpt
         |}""", newline, indent)
  }

  private def simpleParamGroupPartial(paramGroup: String, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |$paramGroup""", newline, indent)
  }*/

}
