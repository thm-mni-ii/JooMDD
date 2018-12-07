package de.thm.icampus.mdd.model.extensions

import de.thm.icampus.mdd.model.oo.Parameter
import de.thm.icampus.mdd.model.sql.Entity
import de.thm.icampus.mdd.model.{Language, Manifest}

case class PluginExtension(name: String, manifest: Manifest, pluginType: String, languages: Set[Language], entities: List[Entity], parameters: List[Parameter]) extends Extension
