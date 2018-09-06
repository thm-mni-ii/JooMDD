package de.thm.icampus.mdd.model.sql

/**
 * Created by alexheinz1110 on 14.08.15.
 */
class Entity{
  var name: String =""
  var attributes: List[Attribute] =List.empty[Attribute]
  var reference :List[Reference] = List.empty[Reference]
  var extentionName:String =""
  def this(name: String, attributes: List[Attribute], reference :List[Reference] = List.empty[Reference]){
    this()
    this.name = name
    this.attributes = attributes
    this.reference = reference
  }

  def getAllAttribute():List[String]={
    if(!this.attributes.isEmpty)
      this.attributes.map(r=> r.name).toList
    else
      List.empty[String]
  }
  def setExtentionName(extName:String):Unit={
   this.extentionName = extName
    if(this.name != extName)
      this.name = this.name.replaceFirst(extName+"_","")
  }
}
