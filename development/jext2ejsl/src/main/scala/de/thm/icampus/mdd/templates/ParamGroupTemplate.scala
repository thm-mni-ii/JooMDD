package de.thm.icampus.mdd.templates

import de.thm.icampus.mdd.model.extensions.{JParam, JParamGroup}
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait ParamGroupTemplate extends BasicTemplate {

  def paramGroupPartial(paramGroup: JParamGroup, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Parametergroup ${rep(paramGroup.params,simpleParamPartial, sep="\n", indent=0)}""", newline, indent)
  }

  private def simpleParamPartial(param: JParam, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |${param.name}""", newline, indent)
  }

}
