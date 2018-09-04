package de.thm.icampus.mdd.model.extensions


case class JParamGroup(name: String, params: Set[JParam])
case class JParam(name: String, htmltype: (String,String), label: String, description: String, default: Option[String] = None, size: Option[Int] = None)
 class DetailsPageAttribute{
   var value:Map[String,String] = Map.empty[String,String]
   var attribute:Map[String,String] = Map.empty[String,String]
   var typeName:String =""
   var name :String =""

   def mapAttribute(typeName: String): String = {
     typeName match{
       case "number" => return "Integer"
       case "text" => return "Text_Field"
       case "textarea" | "editor" => return "Textarea"
       case "list" => return "Select"
       case "calendar" => return "Datepicker"
       case _=> return typeName
     }
   }

   def this(name:String,typeName:String, value:Map[String,String], attribute:Map[String,String]){
     this()
     this.value = value
     this.attribute = attribute
     this.typeName = mapAttribute(typeName)
     this.name = name;

   }
 }
