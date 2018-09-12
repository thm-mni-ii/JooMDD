package de.thm.icampus.mdd.model.extensions

class JParamGroup extends Comparable[JParamGroup]{

  var name: String =""
  var params: Set[JParam] = Set.empty[JParam]
  def this(name: String, params: Set[JParam] = Set.empty[JParam]){
    this()
    this.name = ParseName.parse(name)
    this.params = params.filter(f=> f!=null)
  }

  override def toString: String = this.name + " params " + this.params
  override def equals(obj:scala.Any): Boolean =
  {
    obj match{
      case d :JParamGroup =>{
        if(d.name == this.name ){
          if(this.params != d.params){
            d.name = d.name + "_"+ d.params.size
          }
          return true
        }
      }
      case _ =>return false
    }
    return false
  }
  override def hashCode: Int = {
    val prime = 459
    var result = 1
    prime * result * this.name.hashCode +this.params.hashCode()
  }
  override def compareTo(o: JParamGroup): Int ={
    if(o.name == this.name && this.params ==o.params)
      return 0
    return 1
  }
}
class JParam extends {
  var name: String =""
  var htmltype: String=""
  var label: String =""
  var description: String=""
  var attrlist: Map[String,String] = Map.empty[String,String]
  var valueslist: Map[String,String] = Map.empty[String,String]
  def this(name: String, htmltype:String, label: String, description: String, attrlist: Map[String,String] = Map.empty[String,String]
           , valueslist: Map[String,String] = Map.empty[String,String] ){
    this()
    this.name = ParseName.parse(name)
    this.htmltype = mapAttribute(htmltype)
    this.label = label
    this.description = description
    this.attrlist = attrlist.map(k => (ParseName.parse(k._1)->ParseName.parseValue(k._2))).toMap[String,String]
    this.valueslist = valueslist.map(k => (ParseName.parse(k._1)->ParseName.parseValue(k._2))).toMap[String,String]
  }

  override def toString: String = this.name + "< " + this.htmltype + " >"
  override def equals(obj: scala.Any): Boolean = {
    obj match {
      case d : JParam =>{
        if(d.name == this.name && this.htmltype == d.htmltype)
          return true
      }
      case _ =>return false
    }
    return false
  }
  override def hashCode: Int = {
    val prime = 423
    var result = 1
    prime * result * this.name.hashCode + this.htmltype.hashCode
  }
  def mapAttribute(typeName: String): String = {
    typeName.toLowerCase match{
      case "number"|"float" | "int"=> return "Integer"
      case "text"|"image"|"button"|"submit"|"title"|"email"=> return "Text_Field"
      case "textarea" | "editor" => return "Textarea"
      case "list"|"color"|"radio" => return "Select"
      case "checkbox" => return "Yes_No_Buttons "
      case "calendar" | "date"| "datetime"|"time"=> return "Datepicker"
      case _=> return ParseName.parse(typeName)
    }
  }



}

