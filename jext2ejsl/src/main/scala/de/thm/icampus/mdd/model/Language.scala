package de.thm.icampus.mdd.model

import java.io.File
import java.nio.file.{Path, Paths}

import de.thm.icampus.mdd.implicits.Conversions._

import scala.collection.immutable.StringLike
import scala.io.{Codec, Source}
import scala.xml.{Elem, Node, NodeSeq}

case class Language(name: String, entries: Set[(String, String)])

object Language {

  implicit def fromXmlManifest(xmlManifest: Elem, extensionRoot: Path)(implicit codec: Codec = Codec.UTF8): Set[Language] = {

    //frontend
    val frontendFromXml = languageTagPairsFromXml(xmlManifest, extensionRoot)
    val frontendFromFolder = languageTagPairsFromFolder(xmlManifest, extensionRoot)

    val frontendTagPairs = (frontendFromXml ++ frontendFromFolder).distinct

    //backend
    val backendFromXml = languageTagPairsFromXml(xmlManifest \ "administration", extensionRoot)
    val backendFromFolder = languageTagPairsFromFolder(xmlManifest \ "administration", extensionRoot)

    val backendTagPairs = (backendFromXml ++ backendFromFolder).distinct

    val tagFilePairs = frontendTagPairs ++ backendTagPairs

    tagFilePairs.map(pair => {
      val entries = Source.fromFile(pair._2).getLines.toSet
      val keyValue = entries.map(_.trim).filter(!_.startsWith(";")).map(e ⇒ e.splitAt(e.indexOf('='))).filter({ case(s1, s2) => !s1.trim.isEmpty})
      val keyValuesWithoutEQ = keyValue.map(e ⇒ {
        (e._1, e._2.substring(1).trim.stripPrefix("\"").stripSuffix("\""))
      })
      Language(pair._1, keyValuesWithoutEQ)
    }).distinct.toSet
  }

  private def languageTagPairsFromXml(xmlPosition: NodeSeq, extensionRoot: Path): Seq[(String, Path)] = {
    if((xmlPosition \ "languages").length > 0) {
      val languageFolder = xmlPosition \ "languages" \@ "folder"
      val languageTags = xmlPosition \ "languages" \ "language"

      var prefix = extensionRoot

      if (!languageFolder.isEmpty) {
        prefix = prefix + languageFolder
      }

      languageTags.map(lt => {
        val tag = lt \@ "tag"
        val fileName = lt.text
        val filePath = Paths.get(prefix + fileName)
        (tag, filePath)
      })
    } else {
      Seq()
    }
  }

  private def languageTagPairsFromFolder(xmlPosition: NodeSeq, extensionRoot: Path): Seq[(String, Path)] = {
    if((xmlPosition \ "files").length > 0 && (xmlPosition \ "files" \ "folder").map(_.text).contains("language")) {
      val languageFolderSuffix = xmlPosition \ "files" \@ "folder" + File.separator + "language"

      val languageFolderPath = extensionRoot + languageFolderSuffix
      val languageFolder = new File(languageFolderPath)

      if(languageFolder.exists && languageFolder.isDirectory) {
        languageFolder.listFiles.flatMap(languageCode => if(languageCode.isDirectory){
          Some(languageCode.listFiles.flatMap(languageFile => if(languageFile.isFile){
            Some(languageCode.getName, Paths.get(languageFile.getAbsolutePath))
          } else {
            None
          }))
        } else {
          None
        }).flatten.toSeq
      } else {
        Seq()
      }
    }
    else {
      Seq()
    }
  }

}
