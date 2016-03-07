package de.thm.icampus.mdd.model.extensions

import de.thm.icampus.mdd.model.{Language, Manifest}

case class ComponentExtension(name: String, manifest: Manifest, languages: Set[Language], frontend: Frontend, backend: Backend, entities: List[JEntity], params: Set[JParamGroup] = Set.empty[JParamGroup]) extends Extension

case class Backend(pages: Set[Page])
case class Frontend(pages: Set[Page])

abstract class Page {
  def name: String
  def globalParamNames: Set[String]
}

abstract class DynamicPage extends Page

case class IndexPage(name: String, globalParamNames: Set[String] = Set.empty[String]) extends DynamicPage
case class DetailsPage(name: String, globalParamNames: Set[String] = Set.empty[String]) extends DynamicPage