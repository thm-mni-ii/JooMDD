package de.thm.icampus.mdd.generator

import de.thm.icampus.mdd.model.extensions.JParam
import de.thm.icampus.mdd.generator.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait ParamTemplate extends BasicTemplate {

  def paramPartial(param: JParam, newline: Boolean = true, indent: Int = 0) = {


    val labelOpt  = ?(param.label !="" && !param.label.isEmpty ,
      s""" label = "${param.label}"
       """)
    val descOpt  = ?(param.description !="" && !param.description.isEmpty,
      s""" description ="${param.description}"
       """)
    val valuOpt = ?(param.valueslist.nonEmpty,
      s"""
         |  values {
         |  ${param.valueslist.map(d => s"""  ${d._1} = "${d._2}"""").toList.mkString(",\n")} }"""
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
