package de.thm.icampus.mdd.model.sql
/**
 * Created by alexheinz1110 on 14.08.15.
 */
class Attribute {

  var name: String =""
  var dataType: String=""
  var isprimary: Boolean = false
  var isUnique:Boolean = false
  var withAttr:String=""

  def this(name: String, dataType: String, isprimary: Boolean = false,isUnique:Boolean = false,withAttr:String=""){
    this()
    this.name = name
    this.isprimary = isprimary
    this.isUnique =isUnique
    this.dataType = mappingDBType(dataType)
    this.withAttr = withAttr

  }
  def mappingDBType(sqlType: String): String ={
    sqlType.toLowerCase match {
      case text: String if text.contains("int") ⇒ "Integer"
      case text: String if   text.contains("tinyint")⇒ "Boolean"
      case text: String if text.contains("varchar") ⇒ "Short_Text"
      case text: String if text.contains("bit") ⇒ "Integer"
      case text: String if text.contains("date") || text.contains("time") || text.contains("datetime") ⇒ "Short_Text"
      case _: String ⇒ "Text"
    }
  }

}