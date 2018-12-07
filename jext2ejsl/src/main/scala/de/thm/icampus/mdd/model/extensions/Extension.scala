package de.thm.icampus.mdd.model.extensions

import de.thm.icampus.mdd.model.{Language, Manifest}

trait Extension {
  def name: String
  def manifest: Manifest
  def languages: Set[Language]
}
