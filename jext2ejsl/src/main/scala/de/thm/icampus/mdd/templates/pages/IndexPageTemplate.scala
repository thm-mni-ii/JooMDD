package de.thm.icampus.mdd.templates.pages

import de.thm.icampus.mdd.model.extensions.{IndexPage, JParamGroup}
import de.thm.icampus.mdd.templates.EJSLModelTemplate.paramGroupPartial
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait IndexPageTemplate extends BasicTemplate {

  def indexPagePartial(indexPage: IndexPage, newline: Boolean = true, indent: Int = 0) = {
    val paramGroupOpt = ?(indexPage.globalParamNames.nonEmpty,
      s"""
         |*ParameterGroups ${indexPage.globalParamNames.filter(d => d.params.nonEmpty).map(f => f.name).toList.mkString(", ")}"""
    )
    val columnpOpt = ?(indexPage.representationColumns.nonEmpty,
      s"""
         | representation columns = ${indexPage.representationColumns.map(d=>indexPage.entity +"."+d).toList.mkString(",")}"""
    )
    val filterOpt = ?(indexPage.filters.nonEmpty,
      s"""
         | filters = ${indexPage.filters.map(d=>indexPage.entity +"."+d).toList.mkString(",")}"""
    )

    toTemplate(
      s"""
         |IndexPage ${indexPage.name} {
         |    *Entities ${indexPage.entity}
         |    $paramGroupOpt
         |    ${columnpOpt}
         |    ${filterOpt}
         |}""", newline, indent)
  }

  private def simpleParamGroupNamePartial(paramGroup:JParamGroup , newline: Boolean = true, indent: Int = 0) = {
    val fieldOpt = ?(paramGroup.params.nonEmpty,
      s"""
         |  ${paramGroup.name}"""
    )
    toTemplate(
      s"""
         |${fieldOpt}""", newline, indent)
  }

}
