package de.thm.icampus.mdd

import java.nio.file.{Files, Path}

import de.thm.icampus.mdd.handler._
import de.thm.icampus.mdd.implicits.Conversions._
import de.thm.icampus.mdd.model.EJSLModel
import de.thm.icampus.mdd.model.extensions.Extension
import de.thm.icampus.mdd.model.extensions.PackageExtension

import scala.io.{Source, Codec}
import scala.xml.{XML, Elem}

/**
 * eJSL Model Builder and Extensions Dispatcher.
 */
object Builder {

  /**
   * Extends a EJSLModel with a view call for eJSL Instanz Model printing
   * @param model The EJSLModel to print
   */
  implicit class EJSLModelFunctionWrapper(val model: EJSLModel) extends AnyVal {
    def asText: String = txt.ejslmodel(model).toString()
  }

  implicit val codec: Codec = Codec.UTF8

  /**
   * Build a EJSLModel from a path to a Joomla Extension Manifest of a Joomla Extension.
   *
   * @param manifestPath Path to an manifest
   *
   * @return The EJSLModel Model
   */
  def build(manifestPath: Path): EJSLModel = {
    val extensionRoot = manifestPath.getParent

    val manifestAsString = Source.fromFile(manifestPath).mkString
    val manifestAsXML = XML.loadString(manifestAsString)

    val extension = Builder.dispatch(extensionRoot, manifestAsXML)

    extension match {
      case pkg: PackageExtension ⇒ EJSLModel(pkg.name, pkg.extensions.toList)
      case ext: Extension ⇒ EJSLModel(ext.name, List(ext))
    }
  }

  /**
   * Dispatch a extension by the manifest type. The Manifest is extepected to be in
   * the extension root with the name manifest.xml or ext_extname.xml
   *
   * @param extensionRoot The path to an extension
   * @return
   */
  def dispatch(extensionRoot: Path): Extension = {
    val manifestPathOpportunity1 = extensionRoot + "manifest.xml"
    val manifestpathOpportunity2 = extensionRoot + s"${extensionRoot.getFileName.toString}.xml"

    val manifestPath = if (Files.exists(manifestPathOpportunity1))
      manifestPathOpportunity1
    else if (Files.exists(manifestpathOpportunity2))
      manifestpathOpportunity2
    else
      throw new IllegalArgumentException(s"Manifest for extension in '${extensionRoot.toString}' not found!")


    val manifestAsXMLString = Source.fromFile(manifestPath).mkString
    val manifestAsXML = XML.loadString(manifestAsXMLString)

    dispatch(extensionRoot, manifestAsXML)
  }

  /**
   * Dispatch a extension by the manifest type.
   *
   * @param extensionRoot root folder of the extension
   * @param manifest the manifest file
   *
   * @return The builded Extension
   */
  def dispatch(extensionRoot: Path, manifest: Elem): Extension = {
    manifest \@ "type" match {
      case "library"    => LibraryHandler.handle(extensionRoot, manifest)
      case "component"  => ComponentHandler.handle(extensionRoot, manifest)
      case "module"     => ModuleHandler.handle(extensionRoot, manifest)
      case "language"   => LanguageHandler.handle(extensionRoot, manifest)
      case "plugin"     => PluginHandler.handle(extensionRoot, manifest)
      case "package"    => PackageHandler.handle(extensionRoot, manifest)
      case t: Any       => throw new RuntimeException(s"unsupported library type '$t'")
    }
  }
}
