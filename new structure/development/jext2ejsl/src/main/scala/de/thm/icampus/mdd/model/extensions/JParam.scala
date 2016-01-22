package de.thm.icampus.mdd.model.extensions


case class JParamGroup(name: String, params: Set[JParam])
case class JParam(name: String, htmltype: String, label: String, description: String, default: Option[String] = None, size: Option[Int] = None)
