package de.thm.icampus.mdd.templates

import de.thm.icampus.mdd.model.sql.{Entity}
import de.thm.icampus.mdd.model.sql.Attribute
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait EntityTemplate extends BasicTemplate{

  def entityPartial(entity: Entity, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Entity ${entity.name} {
         |    attributes ${rep(entity.attributes, attributePartial)}
         |}""", newline, indent)
  }

  def attributePartial(attribute: Attribute, newline: Boolean = true, indent: Int = 0) = {
    val isPrimaryOpt = ?(attribute.isprimary,
      s"""
         |Unique attribute
         |Primary attribute"""
    )

    toTemplate(
      s"""
         |Attribute ${attribute.name} {
         |    type = Text
         |    $isPrimaryOpt
         |}""", newline, indent)
  }

}
