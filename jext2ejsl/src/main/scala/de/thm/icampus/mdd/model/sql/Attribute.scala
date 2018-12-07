package de.thm.icampus.mdd.model.sql

import com.sun.xml.internal.fastinfoset.tools.SAXEventSerializer.AttributeValueHolder
import de.thm.icampus.mdd.model.extensions.ParseName

/**
 * Created by alexheinz1110 on 14.08.15.
 */
class Attribute {

  var name: String =""
  var dataType: String=""
  var isprimary: Boolean = false
  var isUnique:Boolean = false
  var withAttr:String=""
  var istnotNul :Boolean = false
  var default:String = ""
  var autoInc = false

  def setAttributeSpec(spec: List[String]) = {
    if(!spec.isEmpty){
      var notIn = spec.indexOf("not")
      if(notIn != -1 && notIn < spec.size-1  && spec(notIn +1) == "null"){
        this.istnotNul = true
      }
      var defaultInd = spec.indexOf("default")
      if(defaultInd != -1 && defaultInd < spec.size-1 ){
        this.default = spec(defaultInd+1)
      }
      if(spec.contains("auto_increment") || spec.contains("AUTO_INCREMENT") ){
        this.autoInc = true
      }
    }
  }

  def this(name: String, dataType: String, isprimary: Boolean = false, isUnique:Boolean = false, withAttr:String="", spec:List[String] = List.empty[String]){
    this()
    this.name = ParseName.parse(name)
    this.isprimary = isprimary
    if(!isprimary){
      this.isUnique =isUnique
      this.withAttr = withAttr
    }else{
      this.isUnique =true

    }
    setAttributeSpec(spec.map(d=> d.toLowerCase).toList)

    this.dataType = mappingDBType(dataType)


  }
  def mappingDBType(sqlType: String): String ={
    sqlType.toLowerCase match {
      case text: String if text.contains("int") || text.contains("float") ⇒ "Integer"
      case text: String if   text.contains("tinyint")⇒ "Boolean"
      case text: String if text.contains("varchar") || text.contains("char")⇒ "Short_Text"
      case text: String if text.contains("text") ⇒ "Text"
      case text: String if text.contains("bit") ⇒ "Integer"
      case text: String if text.contains("date") || text.contains("time") || text.contains("datetime") ⇒ "Short_Text"
      case _: String ⇒ ParseName.parse(sqlType)
    }
  }
  override def equals(obj: Any):Boolean={
    obj match{
      case d : Attribute =>{
        if(d.name == this.name && d.dataType == this.dataType)
          return true
        else
          return true
      }
      case _ => false
    }
  }
  override def hashCode = (41 * this.name.hashCode) + this.dataType.hashCode()

}