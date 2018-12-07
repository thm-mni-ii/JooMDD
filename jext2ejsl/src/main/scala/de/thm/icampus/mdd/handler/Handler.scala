package de.thm.icampus.mdd.handler

import java.nio.file.Path

import de.thm.icampus.mdd.model.extensions.Extension

import scala.io.Codec
import scala.xml.Elem

/**
 * Created by andrej on 19/08/15.
 */
trait Handler {
  def handle(extensionRoot: Path, xmlManifest: Elem)(implicit codec: Codec = Codec.UTF8): Extension
}
