package de.thm.icampus.mdd.handler

import java.nio.file.{Files, Path}

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.ExtensionType
import de.thm.icampus.mdd.Builder
import de.thm.icampus.mdd.model.{Language, Manifest}
import de.thm.icampus.mdd.model.extensions.PackageExtension
import de.thm.icampus.mdd.implicits.Conversions._
import de.thm.icampus.mdd.implicits.OptionUtils._
import de.thm.icampus.mdd.model.extensions.Extension
import scala.collection.JavaConversions._

import scala.io.Codec
import scala.xml.Elem

object PackageHandler extends Handler {
  def handle(extensionRoot: Path, xmlManifest: Elem)(implicit codec: Codec = Codec.UTF8): PackageExtension = {
    val manifest: Manifest = Manifest.fromXml(xmlManifest)
    val languages: Set[Language] = Language.fromXmlManifest(xmlManifest, extensionRoot)
    val name = (xmlManifest \ "packagename").text


    val files = xmlManifest \ "files"
    val packageFolder = files \@ "folder"

    val pathToPackages = extensionRoot + packageFolder

    val extensionNames = (files \ "file").map(node ⇒ {
      val folderName = node.text
      if (folderName.contains('.'))
        folderName.substring(0, folderName.lastIndexOf('.'))
      else
        folderName
    })

    val extensionRoots = extensionNames.map(en ⇒ pathToPackages + en)
    var extensions = Set.empty[Extension]

    extensionRoots.foreach(root ⇒ extensions = extensions + Builder.dispatch(root))

    PackageExtension(name, manifest, languages, extensions)
  }
}
