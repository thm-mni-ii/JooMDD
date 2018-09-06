package de.thm.icampus.mdd.model

import de.thm.icampus.mdd.model.extensions._
import de.thm.icampus.mdd.model.sql.{Entity}

object EJSLModel {
  def apply(name: String, extensions: List[Extension]) = {
    var datatypes = Set.empty[(String,String)]
    var pages = Set.empty[Page]
    var entities = Set.empty[Entity]
    var params = Set.empty[JParam]
    var paramGroups = Set.empty[JParamGroup]

    extensions.foreach {
      case c: ComponentExtension ⇒ {
        c.entities.foreach(e ⇒ e.attributes.foreach(a ⇒ {
          datatypes = datatypes + ((a.dataType.replace(" (","_").replace(")",""),a.dataType))

        }))

        pages = mergePages (c.backend.pages, c.frontend.pages)
        entities = entities ++ c.entities
        params = params ++ c.params.flatMap(paramGroup ⇒ paramGroup.params)
        paramGroups = paramGroups ++ c.params
      }
      case e: Extension ⇒
    }

    params.foreach(param ⇒ datatypes = datatypes + param.htmltype)

    new EJSLModel(name.drop(1), datatypes, pages, entities, params, paramGroups, extensions)
  }

  private def mergePages(a: Set[Page], b: Set[Page]): Set[Page] = {
    var result = a.intersect(b)
    val nA = a.diff(result)
    val nB = b.diff(result)

    var removeFromA = Set.empty[Page]
    var removeFromB = Set.empty[Page]
    nA.foreach(pA ⇒ {
      val page = nB.find(p ⇒ p.name.equals(pA.name))
      if (page.isDefined && pA.getClass.equals(page.get.getClass) && (pA.globalParamNames.nonEmpty || page.get.globalParamNames.nonEmpty)) {
        val mergedPage = pA.getClass.getConstructors()(0).newInstance(pA.name, pA.globalParamNames ++ page.get.globalParamNames).asInstanceOf[Page]

        result = result + mergedPage

      } else {
        result = result + pA
      }
      removeFromA = removeFromA + pA
      if (page.isDefined) removeFromB = removeFromB + page.get
    })

    val nnA = nA diff removeFromA
    val nnB = nB diff removeFromB

    result ++ (nnA ++ nnB)
  }
}

case class EJSLModel(name: String, datatypes: Set[(String,String)], pages: Set[Page], entities: Set[Entity], globalParams: Set[JParam], paramGroups: Set[JParamGroup], extensions: List[Extension])
