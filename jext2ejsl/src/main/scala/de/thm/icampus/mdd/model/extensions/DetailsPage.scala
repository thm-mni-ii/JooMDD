package de.thm.icampus.mdd.model.extensions
import de.thm.icampus.mdd.model.sql.{Entity}


class DetailsPage extends DynamicPage {
  var  name: String =""
  var entity: String=""
  var tablePath: String = "";
  var globalParamNames: Set[JParamGroup] = Set.empty[JParamGroup]
  var representationColumns: Set[String] = Set.empty[String]
  var  editAttribute: Set[DetailsPageAttribute] = Set.empty[DetailsPageAttribute]
  var isEdit:Boolean = false
  var entityObjekt :Entity = null
  def this (name: String, entity: String, isEdit:Boolean,globalParamNames: Set[JParamGroup] = Set.empty[JParamGroup],
            representationColumns: Set[String] = Set.empty[String],editAttribute: Set[DetailsPageAttribute] = Set.empty[DetailsPageAttribute]){
    this()
    this.name = ParseName.parse(name)
    this.entity = ParseName.parse(entity)
    this.globalParamNames = globalParamNames
    this.representationColumns = representationColumns.map(d => ParseName.parse(d)).toSet
    this.editAttribute = editAttribute
    this.isEdit = isEdit

  }

  override def equals (obj:Any): Boolean={
   obj match{
     case f : DetailsPage =>{
       if(f.name == this.name){
         if(f.entity != this.entity){
           f.name = f.name + 1
          return false
         }
         this.editAttribute = f.editAttribute.|(this.editAttribute)
         this.representationColumns = f.representationColumns.|(this.representationColumns)
         this.globalParamNames = f.globalParamNames.|(this.globalParamNames)
         f.globalParamNames = this.globalParamNames
         f.representationColumns = this.representationColumns
         f.editAttribute = this.editAttribute
         this.isEdit = f.isEdit
         this.name = f.name
         return true
       }
       if(f.entity == this.entity ){
         if(f.name.diff(this.name)=="edit" || this.name.diff(f.name)=="edit"  ){
           this.editAttribute = f.editAttribute.|(this.editAttribute)
           this.representationColumns = f.representationColumns.|(this.representationColumns)
           this.globalParamNames = f.globalParamNames.|(this.globalParamNames)
           f.globalParamNames = this.globalParamNames
           f.representationColumns = this.representationColumns
           f.editAttribute = this.editAttribute
           this.isEdit = f.isEdit
           if(f.name.diff(this.name)=="edit")
             f.name = this.name
           else  this.name = f.name
           return true
         }

       }
     }
     case _ => return false
   }
    return false
  }

  def verifiedField() = {
    val attr = this.entityObjekt.getAllAttribute()
    this.representationColumns =  this.representationColumns.filter(t => attr.contains(t))
    this.editAttribute = this.editAttribute.filter(t => attr.contains(t.name))
    this.editAttribute.foreach(f => f.setEntity(this.entity))
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
  override def hashCode: Int = {
    val prime = this.getClass.hashCode()
    prime  * this.name.hashCode + this.entity.hashCode +this.representationColumns.hashCode() +this.editAttribute.hashCode()
  }
}
