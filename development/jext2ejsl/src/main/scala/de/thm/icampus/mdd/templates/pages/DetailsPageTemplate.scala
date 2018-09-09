package de.thm.icampus.mdd.templates.pages

import de.thm.icampus.mdd.model.extensions.{DetailsPage, DetailsPageAttribute}
import de.thm.icampus.mdd.templates.EJSLModelTemplate.paramGroupPartial
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait DetailsPageTemplate extends BasicTemplate {

  def editAttribuPartial (editAttr:DetailsPageAttribute, newline: Boolean = true, indent: Int = 0): String = {

    val valuOpt = ?(editAttr.value.nonEmpty,
      s"""
         |values {
         |${editAttr.value.map(d => s""" Key ${d._1} = "${d._2}"""").toList.mkString(",\n")} }"""
    )
    val fieldOpt = ?(editAttr.attribute.nonEmpty,
      s"""
         |  field attributes {
         |   ${editAttr.attribute.map(d => s"""Key ${d._1} = "${d._2}" """).toList.mkString(",\n")} }"""
    )
    toTemplate(
      s"""
         |  ${editAttr.entity +"."+editAttr.name} {
         |  type = ${editAttr.typeName}
         |  $valuOpt
         |  $fieldOpt }
       """
     , newline,indent)
  }

  def detailsPagePartial(detailsPage: DetailsPage, newline: Boolean = true, indent: Int = 0) = {
    val paramGroupOpt = ?(detailsPage.globalParamNames.nonEmpty,
      s"""
         |*ParameterGroups ${rep(detailsPage.globalParamNames, paramGroupPartial)}"""
    )
    val columnpOpt = ?(detailsPage.representationColumns.nonEmpty,
      s"""
         | table columns = ${detailsPage.representationColumns.map(f=> detailsPage.entity +"."+f).toList.mkString(", ")}"""
    )
    val editFieldpOpt = ?(detailsPage.editAttribute.nonEmpty,
      s"""
         | editFields ${ rep (detailsPage.editAttribute,editAttribuPartial,indent,",")}"""
    )


    toTemplate(
      s"""
         |DetailsPage ${detailsPage.name} {
         | *Entities ${detailsPage.entity}
         |    $columnpOpt
         |    $editFieldpOpt
         |    $paramGroupOpt
         |}""", newline, indent)
  }

  private def simpleParamGroupPartial(paramGroup: String, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |$paramGroup""", newline, indent)
  }


}
