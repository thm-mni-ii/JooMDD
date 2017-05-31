package de.thm.icampus.mdd.templates

import de.thm.icampus.mdd.model.extensions.JParam
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait ParamTemplate extends BasicTemplate {

  def paramPartial(param: JParam, newline: Boolean = true, indent: Int = 0) = {
    val definedOpt = ?(param.default.isDefined,
      s"""
         |defaultvalue = "${param.default.get}""""
    )

    val sizeOpt = ?(param.size.isDefined,
      s"""
         |size = ${param.size.get}"""
    )

    toTemplate(
      s"""
         |Parameter ${param.name} {
         |    type = ${param.htmltype._1}
         |    $definedOpt
         |    label = "${param.label}"
         |    $sizeOpt
         |    description = "${param.description}"
         |}""", newline, indent)
  }

}
