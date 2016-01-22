package de.thm.icampus.mdd.handler

import java.nio.file.Path

import de.thm.icampus.mdd.model.extensions.{LanguageExtension, Extension}

import scala.io.Codec
import scala.xml.Elem

object LanguageHandler extends Handler {
  def handle(extensionRoot: Path, manifestXML: Elem)(implicit codec: Codec = Codec.UTF8): LanguageExtension = {
    // language extensions has not been modelized in eJSL!
    ???
  }
}
