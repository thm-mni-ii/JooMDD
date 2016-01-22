package de.thm.icampus.mdd.model.extensions

import de.thm.icampus.mdd.model.{Language, Manifest}

case class LanguageExtension(name: String, manifest: Manifest, languages: Set[Language]) extends Extension
