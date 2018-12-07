package de.thm.icampus.mdd.handler

import java.nio.file.Path

import de.thm.icampus.mdd.model.extensions.ModuleExtension
import de.thm.icampus.mdd.model.{Language, Manifest}

import scala.io.Codec
import scala.xml.Elem

object ModuleHandler extends Handler {
  def handle(extensionRoot: Path, xmlManifest: Elem)(implicit codec: Codec = Codec.UTF8): ModuleExtension = {
    val manifest: Manifest = Manifest.fromXml(xmlManifest)
    val languages: Set[Language] = Language.fromXmlManifest(xmlManifest, extensionRoot)

    val extensionName = extensionRoot.getFileName.toString.substring(4)

    ModuleExtension(
      extensionName,
      manifest,
      languages
    )
  }
}
