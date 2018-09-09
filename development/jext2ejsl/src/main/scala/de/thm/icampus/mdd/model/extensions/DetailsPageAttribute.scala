package de.thm.icampus.mdd.model.extensions

class DetailsPageAttribute{
  var value:Map[String,String] = Map.empty[String,String]
  var attribute:Map[String,String] = Map.empty[String,String]
  var typeName:String =""
  var name :String =""
  var entity = ""

  def mapAttribute(typeName: String): String = {
    typeName.toLowerCase match{
      case "number"|"float"|"int"=> return "Integer"
      case "text"|"image"|"button"|"submit"|"title"|"email"=> return "Text_Field"
      case "textarea" | "editor" => return "Textarea"
      case "list"|"color"|"radio" => return "Select"
      case "checkbox" => return "Yes_No_Buttons "
      case "calendar" | "date"| "datetime"|"time"=> return "Datepicker"
      case _=> return typeName
    }
  }
  def setEntity(entity:String): Unit ={
    this.entity = entity
  }
  def this(name:String,typeName:String, value:Map[String,String], attribute:Map[String,String]){
    this()
    this.value = value
    this.attribute = attribute
    this.typeName = mapAttribute(typeName)
    this.name = name;

  }
  override def toString: String = this.name + "< " + this.typeName + " >"

  override def equals(obj: scala.Any): Boolean = {
    obj match {
      case d : DetailsPageAttribute =>{
        if(d.name == this.name)
          return true
      }
      case _ =>return false
    }
    return false
  }
  override def hashCode = (42 * this.name.hashCode) + this.typeName.hashCode()

}
