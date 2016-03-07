package de.thm.icampus.mdd.model

import scala.xml.Elem
import de.thm.icampus.mdd.implicits.OptionUtils._

trait ManifestEntry

case class Version(version: String) extends ManifestEntry
case class Link(link: String) extends ManifestEntry
case class CreationDate(data: String) extends ManifestEntry
case class License(license: String) extends ManifestEntry
case class Copyright(copyright: String) extends ManifestEntry
case class Description(description: String) extends ManifestEntry

case class Author(name: String, email: String = "", url: String = "") extends ManifestEntry

case class InstallMethod(method: String)

object Manifest {
  object Type {
    val component = "component"
    val library = "library"
    val language = "language"
    val module = "module"
    val plugin = "plugin"
  }
  object Method {
    val install = "install"
    val upgrade = "upgrade"
  }


  /**
   * Read common informations (common for all extensions) from a manifest file and create a manifest object.
   * Common Informations:
   * - version
   * - method
   * - creationDate
   * - copyright
   * - license
   * - description
   * - one author (author, authorEMail and authorUrl)
   *
   * @param xmlManifest Manifest file
   *
   * @return Manifest object.
   */
  implicit def fromXml(xmlManifest: Elem): Manifest = {

    val version: String = xmlManifest \@ "version"
    val method: String = xmlManifest \@ "method"

    val creationDate: String = (xmlManifest \ "creationDate").headOption.?(n => n.text)("")
    val copyright = (xmlManifest \ "copyright").headOption.?(n => n.text)("")
    val licence = (xmlManifest \ "license").headOption.?(n => n.text)("")
    val description = (xmlManifest \ "description").headOption.?(n => n.text)("")

    val authorName = (xmlManifest \ "author").headOption.?(n => n.text)("")
    val authorEMail = (xmlManifest \ "authorEMail").headOption.?(n => n.text)((xmlManifest \ "authorEmail").headOption.?(n => n.text)(""))
    val authorUrl = (xmlManifest \ "authorUrl").headOption.?(n => n.text)("")

    val manifestation = Manifest(
      Version(version),
      InstallMethod(method),
      CreationDate(creationDate),
      Copyright(copyright),
      License(licence),
      Description(description)
    )

    manifestation.addAuthor(Author(
      name = authorName,
      email = authorEMail,
      url = authorUrl
    ))

    manifestation
  }
}

case class Manifest(version: Version,
                    method: InstallMethod,
                    creationDate: CreationDate,
                    copyright: Copyright,
                    license: License,
                    description: Description
){
  private val authorsCol = collection.mutable.Set[Author]()

  def addAuthor (author: Author): Unit = {
    authorsCol.add(author)
  }

  def authors: Iterator[Author] = authorsCol.iterator

  override def toString: String = s"Manifest($version, $method) {\n${authorsCol.mkString("\n")}}"
}
