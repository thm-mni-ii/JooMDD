package de.thm.icampus.mdd.handler

import java.io.File
import java.nio.charset.MalformedInputException
import java.nio.file.Path
import java.nio.file.Files

import de.thm.icampus.mdd.model._
import de.thm.icampus.mdd.model.extensions.LibraryExtension
import de.thm.icampus.mdd.model.oo.{Folder, Struct, Clazz}
import de.thm.icampus.mdd.parser.PHPParser

import scala.collection.JavaConversions._

import scala.io.Codec
import scala.xml.{Node, Elem}


object LibraryHandler extends Handler {

  def handle(extensionRoot: Path, xmlManifest: Elem)(implicit codec: Codec = Codec.UTF8): LibraryExtension = {
    val manifest: Manifest = Manifest.fromXml(xmlManifest)
    val languages: Set[Language] = Language.fromXmlManifest(xmlManifest, extensionRoot)

    val folder = translate(extensionRoot)

    val libraryName = (xmlManifest \ "libraryname").text

    LibraryExtension(libraryName, manifest, folder)
  }

  private def translate(folderPath: Path): Folder = {
    var structs = List.empty[Struct]
    var folders = List.empty[Folder]

    Files.newDirectoryStream(folderPath).foreach {
      case f: Path if Files.isDirectory(f) => folders = translate(f) :: folders
      case c: Path if c.toString.endsWith(".php") => try {
        val phpFile = PHPParser.parseFile(c)
        structs = structs ::: phpFile
      } catch {
        case e: MalformedInputException => System.err.println("Ignore Malformed PHP File: " + c)
      }
      case a: Path => println("Ignore File: " + a)
    }

    Folder(folderPath.getFileName.toString, folders, structs)
  }
}

class LibraryHandler {

}
