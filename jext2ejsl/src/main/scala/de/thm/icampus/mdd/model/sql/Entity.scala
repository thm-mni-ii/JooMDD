package de.thm.icampus.mdd.model.sql

import de.thm.icampus.mdd.model.extensions.ParseName

/**
 * Created by alexheinz1110 on 14.08.15.
 */
class Entity{
  def setOneReference(ref: Reference): Unit ={
    this.reference = this.reference :+ ref
  }

  var name: String =""
  var attributes: List[Attribute] =List.empty[Attribute]
  var reference :List[Reference] = List.empty[Reference]
  var extentionName:String =""
  def this(name: String, attributes: List[Attribute], reference :List[Reference] = List.empty[Reference]){
    this()
    this.name = ParseName.parse(name)
    this.attributes = attributes
    this.reference = reference
  }

  override def equals(obj:Any): Boolean ={
    obj match{
      case f :Entity =>{
        if(f.name == this.name && f.attributes == this.attributes)
          return true
      }
      case _ => false
    }
    return false
  }
  def getAllAttribute():List[String]={
    if(!this.attributes.isEmpty)
      this.attributes.map(r=> r.name).toList
    else
      List.empty[String]
  }
  def setExtentionName(extName:String):Unit={
   this.extentionName = extName
    if(this.name != extName){
      this.name = ParseName.parse(this.name.replaceFirst(extName+"_",""))
      this.reference.foreach(r => r.setEntityName(extName))
    }

  }
  override def hashCode = (41 * this.name.hashCode) + this.attributes.hashCode()

}
