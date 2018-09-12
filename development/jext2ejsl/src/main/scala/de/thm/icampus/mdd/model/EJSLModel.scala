package de.thm.icampus.mdd.model

import de.thm.icampus.mdd.model.extensions._
import de.thm.icampus.mdd.model.sql.{Entity}

object EJSLModel {
  var attrType =Set("Integer","Boolean", "Short_Text","Text","Integer","Text_Field","Textarea","Select","Datepicker")
  def apply(name: String, extensions: List[Extension]) = {
    var datatypes = Set.empty[(String,String)]
    var pages = Set.empty[Page]
    var entities = Set.empty[Entity]
    var params = Set.empty[JParam]
    var paramGroups = Set.empty[JParamGroup]

    extensions.foreach {
      case c: ComponentExtension ⇒ {


        pages = (c.backend.pages.|(c.frontend.pages)).toSet[Page]

        entities = entities ++ c.entities
        params = params ++ c.params.flatMap(paramGroup ⇒ paramGroup.params)
        paramGroups = paramGroups ++ c.params.filter(df => !df.params.isEmpty)

        c.backend.pages = c.backend.pages.filter(f => pages.contains(f)).toSet
        c.frontend.pages = c.frontend.pages.filter(f => pages.contains(f)).toSet
      }
      case e: Extension ⇒
    }

    //params.foreach(param ⇒ datatypes = datatypes + param.htmltype)
    var unknowType = Set.empty[String]
    entities.foreach(e ⇒ e.attributes.foreach(a ⇒ {
      if(!attrType.contains(a.dataType))
        unknowType = unknowType .+(a.dataType)
    }))
    if(!params.isEmpty ){
      params.foreach(prg=>{
        if(!attrType.contains(prg.htmltype))
          unknowType = unknowType .+(prg.htmltype)
      })
    }
    pages.foreach(p =>{
      paramGroups ++= p.globalParamNames
      p.globalParamNames.foreach(fg =>{
        fg.params.foreach(par =>{
          if(!attrType.contains(par.htmltype))
            unknowType = unknowType .+(par.htmltype)
        })

      })
    })

    paramGroups.foreach(f =>{
      paramGroups.foreach(h =>{
        if(f.params!=h.params && f.name ==h.name)
          h.name = h.name+"_1"
      })
    })
    var pagesMerge = Set.empty[Page]
    val d = pages.foreach(pg =>{
      pages.foreach(gh=>{
        if(pg ==gh && !pagesMerge.contains(pg)){
          pages = pages.-(gh)
          pagesMerge += pg
        }
      })
    })


   // pages = pages.toSet
    paramGroups =paramGroups.toSet
    if(!pagesMerge.isEmpty ){
      pagesMerge.foreach(prg=>{
        prg match{
          case d : DetailsPage=>{
            d.editAttribute.foreach(gh=>{
              if(!attrType.contains(gh.typeName))
                unknowType = unknowType .+(gh.typeName)
            })
          }
          case _=>
        }

      })
    }
    if(!unknowType.isEmpty) {
      unknowType.foreach(f => {
        var typeId = f.split(" ")
        if (typeId.length > 1)
          datatypes += (typeId(0) -> f)
        else datatypes += (f.trim().replaceAll("[()]", "") -> f)

      })
    }

    new EJSLModel(name, datatypes, pagesMerge, entities, params, paramGroups, extensions)
  }


}

case class EJSLModel(name: String, datatypes: Set[(String,String)], pages: Set[Page], entities: Set[Entity], globalParams: Set[JParam], paramGroups: Set[JParamGroup], extensions: List[Extension])
