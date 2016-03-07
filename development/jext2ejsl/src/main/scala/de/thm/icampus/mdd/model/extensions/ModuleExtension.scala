package de.thm.icampus.mdd.model.extensions

import de.thm.icampus.mdd.model.{Language, Manifest}

/**
 * Created by andrej on 14/08/15.
 */
case class ModuleExtension(name: String, manifest: Manifest, languages: Set[Language], page: String="") extends Extension
