package de.thm.icampus.mdd.templates

import de.thm.icampus.mdd.model.extensions.JParam
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait ParamTemplate extends BasicTemplate {

  def paramPartial(param: JParam, newline: Boolean = true, indent: Int = 0) = {


    toTemplate(
      s"""
         |Parameter ${param.name} {
         |    type = ${param.htmltype}
         |    label = "${param.label}"
         |    description = "${param.description}"
         |}""", newline, indent)
  }

}
