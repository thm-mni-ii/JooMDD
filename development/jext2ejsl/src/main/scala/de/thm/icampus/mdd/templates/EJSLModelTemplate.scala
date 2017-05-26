package de.thm.icampus.mdd.templates

import de.thm.icampus.mdd.model.EJSLModel
import de.thm.icampus.mdd.templates.basic.BasicTemplate
import de.thm.icampus.mdd.templates.extensions.ExtensionTemplate
import de.thm.icampus.mdd.templates.pages.PageTemplate

/**
  * Created by tobias on 26.05.17.
  */
object EJSLModelTemplate extends BasicTemplate with ParamTemplate with ParamGroupTemplate with EntityTemplate with PageTemplate with ExtensionTemplate {

  def ejslModelPartial(model: EJSLModel, newline: Boolean = true, indent: Int = 0) : String = {
    toTemplate(
      s"""
         |ejslModel "${model.name}" {
         |    datatypes ${rep(model.datatypes, datatypePartial)}
         |
         |    globalparameters ${rep(model.globalParams, paramPartial)}
         |
         |    parametergroup ${rep(model.paramGroups, paramGroupPartial)}
         |
         |    entities ${rep(model.entities, entityPartial)}
         |
         |    pages ${rep(model.pages, pagePartial)}
         |
         |    extensions ${rep(model.extensions, extensionPartial)}
         |}""", newline, indent)
  }

  def datatypePartial(datatype: String, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Datatype $datatype""", newline, indent)
  }


}
