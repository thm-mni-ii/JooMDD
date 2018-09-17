package de.thm.icampus.mdd.model.extensions

import de.thm.icampus.mdd.model.{Language, Manifest}
import de.thm.icampus.mdd.model.sql.{Entity}

case class ComponentExtension(name: String, manifest: Manifest, languages: Set[Language], frontend: Frontend, backend: Backend, entities: List[Entity], params: Set[JParamGroup] = Set.empty[JParamGroup]) extends Extension

case class Backend(var pages: Set[Page])
case class Frontend(var pages: Set[Page])

abstract class Page {
   var name: String
  var globalParamNames: Set[JParamGroup]

  override def toString: String = this.name

}

abstract class DynamicPage extends Page{
  def setEntityOb(entities:List[Entity])
}


