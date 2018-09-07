package de.thm.icampus.mdd.model.extensions

import de.thm.icampus.mdd.model.{Language, Manifest}
import de.thm.icampus.mdd.model.sql.{Entity}

case class ComponentExtension(name: String, manifest: Manifest, languages: Set[Language], frontend: Frontend, backend: Backend, entities: List[Entity], params: Set[JParamGroup] = Set.empty[JParamGroup]) extends Extension

case class Backend(pages: Set[Page])
case class Frontend(pages: Set[Page])

abstract class Page {
  def name: String
  def globalParamNames: Set[JParamGroup]

}

abstract class DynamicPage extends Page{
  def setEntityOb(entities:List[Entity])
}


