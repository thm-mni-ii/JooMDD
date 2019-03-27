package de.thm.icampus.mdd.generator.pages

import de.thm.icampus.mdd.model.extensions.{DetailsPage, DetailsPageAttribute, JParamGroup}
import de.thm.icampus.mdd.generator.EJSLModelTemplate.paramGroupPartial
import de.thm.icampus.mdd.generator.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait DetailsPageTemplate extends BasicTemplate {

  def editAttribuPartial (editAttr:DetailsPageAttribute, newline: Boolean = true, indent: Int = 0): String = {

    val valuOpt = ?(editAttr.value.nonEmpty,
s"""
         |values {
         |${editAttr.value.map(d => s"""${d._1} = "${d._2}"""").toList.mkString(",\n")} }"""
    )
    val fieldOpt = ?(editAttr.attribute.nonEmpty,
s"""
         |  fieldAttributes {
         |   ${editAttr.attribute.map(d => s"""${d._1} = "${d._2}" """).toList.mkString(",\n")} }"""
    )
    toTemplate(
s"""
         |  ${editAttr.entity +"."+editAttr.name} {
         |  htmlType = ${editAttr.typeName}
         |  $valuOpt
         |  $fieldOpt
         |  }
       """
     , newline,indent)
  }

  def detailsPagePartial(detailsPage: DetailsPage, newline: Boolean = true, indent: Int = 0) = {
    val paramGroupOpt = ?(detailsPage.globalParamNames.nonEmpty,
s"""
          |*parameterGroups = ${detailsPage.globalParamNames.filter(d => d.params.nonEmpty).map(f => f.name).toList.mkString(", ")}"""
    )
    val columnpOpt = ?(detailsPage.representationColumns.nonEmpty,
s"""
         | tableColumns = ${detailsPage.representationColumns.map(f=> detailsPage.entity +"."+f).toList.mkString(", ")}"""
    )
    val editFieldpOpt = ?(detailsPage.editAttribute.nonEmpty,
s"""
         | editFields ${ rep (detailsPage.editAttribute,editAttribuPartial,indent,",")}"""
    )

    val links = ?(detailsPage.editAttribute.nonEmpty,
      s"""
         | editFields ${ rep (detailsPage.editAttribute,editAttribuPartial,indent,",")}"""
    )


    toTemplate(
s"""
         |DetailsPage ${detailsPage.name} {
         | *entities = ${detailsPage.entity}
         |    $editFieldpOpt
         |    $paramGroupOpt
         |    $columnpOpt
         |    }
         |""", newline, indent)
  }
 private  def simpleParamGroupNamePartial(paramGroup:JParamGroup , newline: Boolean = true, indent: Int = 0) = {
    val fieldOpt = ?(paramGroup.params.nonEmpty,
s"""
         |  ${paramGroup.name}"""
    )
    toTemplate(
s"""
         |${fieldOpt}""", newline, indent)
  }



}
