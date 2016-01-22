package de.thm.icampus.mdd.model.extensions

import de.thm.icampus.mdd.model.{Language, Manifest}

case class PackageExtension(name: String, manifest: Manifest, languages: Set[Language], extensions: Set[Extension]) extends Extension
