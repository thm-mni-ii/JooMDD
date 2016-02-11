package de.thm.icampus.mdd.model.extensions

import de.thm.icampus.mdd.model.oo.Folder
import de.thm.icampus.mdd.model.{Language, Manifest}

/**
 * Created by andrej on 14/08/15.
 */
case class LibraryExtension(name: String, manifest: Manifest, folder: Folder) extends Extension {
  override def languages: Set[Language] = Set.empty[Language]
}
