package de.thm.icampus.mdd.model

import java.nio.file.{Path, Paths}

import de.thm.icampus.mdd.implicits.Conversions._

import scala.io.{Codec, Source}
import scala.xml.Elem

case class Language(name: String, entries: Set[(String, String)])

object Language {

  implicit def fromXmlManifest(xmlManifest: Elem, extensionRoot: Path)(implicit codec: Codec = Codec.UTF8): Set[Language] = {

    val languageTags = xmlManifest \\ "language"
    val folder = xmlManifest \\ "languages" \@ "folder"

    var prefix = extensionRoot

    if (!folder.isEmpty) {
      prefix = prefix + folder
    }

    val tagFilePairs = languageTags.map(lt => {
      val tag = lt \@ "tag"
      val fileName = lt.text
      val filePath = Paths.get(prefix + fileName)
      (tag, filePath)
    })

    val languages = tagFilePairs.map(pair => {
      val entries = Source.fromFile(pair._2).getLines.toSet
      val keyValue = entries.map(e ⇒ e.splitAt(e.indexOf('=')))
      val keyValuesWithoutEQ = keyValue.map(e ⇒ (e._1, e._2.substring(1).trim.stripPrefix("\"").stripSuffix("\"")))
      Language(pair._1, keyValuesWithoutEQ)
    }).distinct

    languages.toSet
  }

}
