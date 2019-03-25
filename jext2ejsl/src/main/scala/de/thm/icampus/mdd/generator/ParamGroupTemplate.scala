package de.thm.icampus.mdd.generator

import de.thm.icampus.mdd.model.extensions.{JParam, JParamGroup}
import de.thm.icampus.mdd.generator.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait ParamGroupTemplate extends BasicTemplate {

  def paramGroupPartial(paramGroup: JParamGroup, newline: Boolean = true, indent: Int = 0) = {
    val paramOpt = ? (paramGroup.params.nonEmpty,
      s"""
         |Parameters ${rep(paramGroup.params,simpleParamPartial, sep="\n")}""")
    val paramNameOpt = ? (paramGroup.params.nonEmpty,
      s"""
         | ParameterGroup ${paramGroup.name} {""")
    val paramTerOpt = ? (paramGroup.params.nonEmpty,
      s"""
         | }""")
    val labelOpt = ? (paramGroup.params.nonEmpty,
      s"""
         |label = "${paramGroup.name}"
         |""")
    toTemplate(
      s"""
         |  ${paramNameOpt}
         |  $labelOpt
         |  ${paramOpt} }
         |  """, newline, indent)
  }

  private def simpleParamPartial(param: JParam, newline: Boolean = true, indent: Int = 0) = {
    val labelOpt  = ?(param.label !=""  && !param.label.isEmpty ,
      s""" label = "${param.label}"
       """)
    val descOpt  = ?(param.description !=""  && !param.description.isEmpty ,
      s""" description ="${param.description}"
       """)
    val valuOpt = ?(param.valueslist.nonEmpty,
      s"""
         |  values {
         |  ${param.valueslist.map(d => s""" ${d._1} = "${d._2}"""").toList.mkString(",\n")} }"""
    )
    val fieldOpt = ?(param.attrlist.nonEmpty,
      s"""
         |  field attributes {
         |  ${param.attrlist.map(d => s""" ${d._1} = "${d._2}" """).toList.mkString(",\n")} }"""
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
