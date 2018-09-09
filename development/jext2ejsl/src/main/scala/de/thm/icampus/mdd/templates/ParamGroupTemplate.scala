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
         | ParameterGroup pg {
         |  label = "${paramGroup.name}"
         |  Parameters ${rep(paramGroup.params,simpleParamPartial, sep="\n")}
         |}""", newline, indent)
  }

  private def simpleParamPartial(param: JParam, newline: Boolean = true, indent: Int = 0) = {
    val labelOpt  = ?(param.label !="",
      s""" label = "${param.label}"
       """)
    val descOpt  = ?(param.description !="",
      s""" description ="${param.description}"
       """)
    val valuOpt = ?(param.valueslist.nonEmpty,
      s"""
         |  values {
         |  ${param.valueslist.map(d => s""" Key ${d._1} = "${d._2}"""").toList.mkString(",\n")} }"""
    )
    val fieldOpt = ?(param.attrlist.nonEmpty,
      s"""
         |  field attributes {
         |  ${param.attrlist.map(d => s"""Key ${d._1} = "${d._2}" """).toList.mkString(",\n")} }"""
    )
    toTemplate(
      s"""
         |Parameter ${param.name}{
         |  type = ${param.htmltype}
         |  ${labelOpt}
         |  ${descOpt}
         |  ${valuOpt}
         |  ${fieldOpt}
         | }""", newline, indent)
  }

}
