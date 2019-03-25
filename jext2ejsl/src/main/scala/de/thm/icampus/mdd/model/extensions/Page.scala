package de.thm.icampus.mdd.model.extensions

abstract class Page {
  var name: String
  var globalParamNames: Set[JParamGroup]

  override def toString: String = this.name

}
