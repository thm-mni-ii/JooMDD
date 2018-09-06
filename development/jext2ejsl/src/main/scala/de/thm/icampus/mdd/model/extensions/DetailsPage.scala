package de.thm.icampus.mdd.model.extensions
import de.thm.icampus.mdd.model.sql.{Entity}
class DetailsPage extends DynamicPage {
  var  name: String =""
  var entity: String=""
  var globalParamNames: Set[JParamGroup] = Set.empty[JParamGroup]
  var representationColumns: Set[String] = Set.empty[String]
  var  editAttribute: Set[DetailsPageAttribute] = Set.empty[DetailsPageAttribute]
  var entityObjekt :Entity = null
  def this (name: String, entity: String, isEdit:Boolean,globalParamNames: Set[JParamGroup] = Set.empty[JParamGroup],
            representationColumns: Set[String] = Set.empty[String],editAttribute: Set[DetailsPageAttribute] = Set.empty[DetailsPageAttribute]){
    this()
    this.name = name
    this.entity = entity
    this.globalParamNames = globalParamNames
    this.representationColumns = representationColumns
    this.editAttribute = editAttribute


  }

  def verifiedField() = {
    val attr = this.entityObjekt.getAllAttribute()
    this.representationColumns =  this.representationColumns.filter(t => attr.contains(t))
    this.editAttribute = this.editAttribute.filter(t => attr.contains(t.name))
  }

  def setEntityOb(entities:List[Entity]): Unit ={
    for(r <- entities){
      if(r.name == this.entity){
        this.entityObjekt = r
        verifiedField()
        return
      }
    }
  }
}
