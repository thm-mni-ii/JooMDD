package de.thm.icampus.mdd.generator

import de.thm.icampus.mdd.model.sql.{Attribute, Entity, Reference}
import de.thm.icampus.mdd.generator.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait EntityTemplate extends BasicTemplate{

  def entityPartial(entity: Entity, newline: Boolean = true, indent: Int = 0) = {
    val ref = ?(!entity.reference.isEmpty,
      s"""
         |    references ${rep(entity.reference, referencePartial)}
       """)
    toTemplate(
      s"""
         |Entity ${entity.name} {
         |    attributes ${rep(entity.attributes, attributePartial)}
         |    $ref
         |}""", newline, indent)
  }
  def referencePartial(ref: Reference, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Reference {
         |   entityAttribute = ${ref.attribute.mkString(",")}
         |   referencedEntity = ${ref.entity}
         |   referencedEntityAttribute = ${ref.reference.mkString(" ")}
         |   min = ${ref.lower}
         |   max = ${ref.upper} }
       """
    , newline, indent)
  }
  def attributePartial(attribute: Attribute, newline: Boolean = true, indent: Int = 0) = {
    val isPrimaryOpt = ?(attribute.isprimary,
      s""" Primary attribute"""
    )
    val notNullOpt = ? (attribute.istnotNul,s"""  Not Null""")
    val defaultOpt = ? (attribute.default != "" && attribute.default.length>0,s"""  Default = "${attribute.default}" """)
    val autoIntOpt = ? (attribute.autoInc,s"""  Auto Increment""")
    val isunique = ?(attribute.isUnique,
      s"""
         |Unique attribute
       """)
    val uniquewith = ?(attribute.withAttr != "",
      s"""
         |with ${attribute.withAttr}
       """)
    toTemplate(
      s"""
         |Attribute ${attribute.name} {
         |    type = ${attribute.dataType}
         |    $notNullOpt
         |    $defaultOpt
         |    $autoIntOpt
         |    $isPrimaryOpt
         |}""", newline, indent)
  }



}
