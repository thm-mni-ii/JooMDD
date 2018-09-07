package de.thm.icampus.mdd.model.extensions

class CustomPage extends Page {

  var name: String =""
  var globalParamNames: Set[JParamGroup] = Set.empty[JParamGroup]
  def this(name: String, globalParamNames: Set[JParamGroup] = Set.empty[JParamGroup]){
    this()
    this.name = name
    this.globalParamNames = globalParamNames
  }

  override def equals(obj: scala.Any): Boolean ={
    obj match{
      case d : CustomPage =>{
        if(d.name == this.name || (this.name.diff(d.name) == "edit") ||  (d.name.diff(this.name) == "edit" )){
          return true
        }
      }
      case _=>
    }
    return false
  }
}
