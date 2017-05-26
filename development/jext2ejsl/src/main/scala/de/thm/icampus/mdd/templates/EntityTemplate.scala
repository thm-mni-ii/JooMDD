package de.thm.icampus.mdd.templates

import de.thm.icampus.mdd.model.extensions.{Attribute, JEntity}
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait EntityTemplate extends BasicTemplate{

  def entityPartial(entity: JEntity, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Entity ${entity.name} {
         |    attributes ${rep(entity.attributes, attributePartial)}
         |}""", newline, indent)
  }

  def attributePartial(attribute: Attribute, newline: Boolean = true, indent: Int = 0) = {
    val isPrimaryOpt = ?(attribute.isPrimary,
      s"""
         |Primary Attribute""""
    )

    toTemplate(
      s"""
         |Attribute ${attribute.name} {
         |    dbtype = "${attribute.dbtype}"
         |    htmltype = "${attribute.htmltype}"
         |    $isPrimaryOpt
         |}""", newline, indent)
  }

}
