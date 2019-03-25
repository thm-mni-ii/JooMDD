package de.thm.icampus.mdd.generator

import de.thm.icampus.mdd.model.EJSLModel
import de.thm.icampus.mdd.generator.basic.BasicTemplate
import de.thm.icampus.mdd.generator.extensions.ExtensionTemplate
import de.thm.icampus.mdd.generator.pages.PageTemplate

/**
  * Created by tobias on 26.05.17.
  */
object EJSLModelTemplate extends BasicTemplate with ParamTemplate with ParamGroupTemplate with EntityTemplate with PageTemplate with ExtensionTemplate {

  def ejslModelPartial(model: EJSLModel, newline: Boolean = true, indent: Int = 0) : String = {
    val globalParamOpt = ? (model.globalParams.nonEmpty,
      s"""
         |globalparameters ${rep(model.globalParams,paramPartial, sep="\n")}""")
    val dataTypeOpt = ? (model.datatypes.nonEmpty,
      s"""
         |datatypes ${rep(model.datatypes,datatypePartial, sep=",\n")}""")
    val paramGrouOPt = ?(model.paramGroups.nonEmpty,
      s"""
             |parametergroups ${rep(model.paramGroups, paramGroupPartial)}
              """)

    toTemplate(
      s"""
         |eJSLModel "${model.name}" {
         |  eJSL part: CMS Extension {
         |    ${dataTypeOpt}
         |    ${globalParamOpt}
         |    ${paramGrouOPt}
         |    entities ${rep(model.entities, entityPartial)}
         |
         |    pages ${rep(model.pages, pagePartial)}
         |
         |    extensions ${rep(model.extensions, extensionPartial)}
         |  }
         |}""", newline, indent)

  }

  def datatypePartial(datatype: (String,String), newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Datatype ${datatype._1}="${datatype._2}"""", newline, indent)
  }


}
