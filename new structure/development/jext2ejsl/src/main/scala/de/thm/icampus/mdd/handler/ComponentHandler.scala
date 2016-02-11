package de.thm.icampus.mdd.handler

import java.nio.file.{Files, Path}

import de.thm.icampus.mdd.implicits.Conversions._
import de.thm.icampus.mdd.implicits.OptionUtils._
import de.thm.icampus.mdd.parser.SQLParser

import scala.collection.JavaConversions._

import de.thm.icampus.mdd.model.{Language, Manifest}

import de.thm.icampus.mdd.model.extensions._

import scala.io.{Source, Codec}
import scala.xml.{XML, Elem}

object ComponentHandler extends Handler {

  private def readParams(configPath: Path): Set[JParamGroup] = {
    val configAsXMLString = Source.fromFile(configPath).mkString
    val configAsXML = XML.loadString(configAsXMLString)

    val fieldSets = configAsXML \\ "fieldset"

    fieldSets.map(fieldSet ⇒ {
      val fields = fieldSet \\ "field"

      val params = fields.map(field ⇒ {
        val name = field \@ "name"
        val htmltype = field \@ "type"
        val default = (field \@ "default") asOpt
        val label = field \@ "label"
        val description = field \@ "description"
        val size = ((field \@ "size") asOpt).?[Option[Int]] (e ⇒ Option(Integer.parseInt(e)))(None)

        JParam(name, htmltype, label, description, default, size)
      }).toSet

      val fieldSetName = fieldSet \@ "name"
      JParamGroup(fieldSetName, params)
    }).toSet
  }

  def handle(extensionRoot: Path, xmlManifest: Elem)(implicit codec: Codec = Codec.UTF8): ComponentExtension = {
    val manifest: Manifest = Manifest.fromXml(xmlManifest)
    val languages: Set[Language] = Language.fromXmlManifest(xmlManifest, extensionRoot)

    val extensionName = extensionRoot.getFileName.toString.substring(4) // com_name -> name

    val frontendPath = extensionRoot + "site"
    val backendPath = extensionRoot + "admin"

    var frontEndPages = Set.empty[Page]
    var backEndPages = Set.empty[Page]

    var paramGroups = Set.empty[JParamGroup]

    if (Files.exists(frontendPath)) { // Handle frontendSection
      val viewsPath = frontendPath + "views"
      Files.newDirectoryStream(viewsPath).foreach {
        case viewFolder: Path if Files.isDirectory(viewFolder) ⇒ {
          val fileName = viewFolder.getFileName.toString

          var pageGroupParamNames = Set.empty[String]
          val paramsPath = viewFolder + "tmpl/default.xml"
          if (Files.exists(paramsPath)) {
            val pageParams = readParams(paramsPath)
            pageGroupParamNames = pageParams.map(p ⇒ p.name)
            paramGroups = paramGroups ++ pageParams
          }

          if (!fileName.startsWith(extensionName))
            frontEndPages = frontEndPages + (if (fileName.endsWith("s")) IndexPage(fileName, pageGroupParamNames) else DetailsPage(fileName, pageGroupParamNames))
        }
        case ignored: Path ⇒ println("Ignore File: " + ignored)
      }
    }



    if (Files.exists(backendPath)) { // Handle backendSection
      val viewsPath = backendPath + "views"

      Files.newDirectoryStream(viewsPath).foreach{
        case viewFolder: Path if Files.isDirectory(viewFolder) ⇒ {
          val fileName = viewFolder.getFileName.toString

          var pageGroupParamNames = Set.empty[String]
          val paramsPath = viewFolder + "tmpl/default.xml"
          if (Files.exists(paramsPath)) {
            val pageParams = readParams(paramsPath)
            pageGroupParamNames = pageParams.map(p ⇒ p.name)
            paramGroups = paramGroups ++ pageParams
          }

          if (!fileName.startsWith(extensionName))
            backEndPages = backEndPages + (if (fileName.endsWith("s")) IndexPage(fileName, pageGroupParamNames) else DetailsPage(fileName, pageGroupParamNames))

        }
        case ignored: Path ⇒ println("Ignore File: " + ignored)
      }
    }

    // Read Sql Create Statements and create entities
    val sqlInstallPathString = (xmlManifest \ "install" \ "sql" \ "file").text
    val sqlInstallPath = extensionRoot + "admin" + sqlInstallPathString

    val sqlTables = SQLParser.parseFile(sqlInstallPath)

    val entities = sqlTables.map(t ⇒ JEntity(
      t.name,
      t.columns.map(c ⇒ Attribute(c.name, c.dataType, c.isprimary))
    ))

    val backendConfigPath = backendPath + "config.xml"
    if (Files.exists(backendConfigPath)) {
      paramGroups = paramGroups ++ readParams(backendConfigPath)
    }
    val frondendConfigPath = frontendPath + "config.xml"
    if (Files.exists(frondendConfigPath)) {
      paramGroups = paramGroups ++ readParams(frondendConfigPath)
    }

    /*
    // Read admin forms xml definitions and fill html type in entities
    val xmlFormsRootPath = backendPath + "models" + "forms"

    // -- Search for entities as xml form and read the html type for each attribute defined in entities (read from sql)
         entities.foreach(entity ⇒ {
         val entityPath = xmlFormsRootPath + s"${entity.name}.xml"
          if (Files.exists(entityPath)) {
            val entityString = Source.fromFile(entityPath).mkString
            val entityXML = XML.loadString(entityString)
            val fields = entityXML \\ "field"
            //val fieldNameToType =

          } else {
            println(s"Ignore html entity in '$entityPath' reason 'not found' ")
          }
        })*/

    ComponentExtension(
      extensionName,
      manifest,
      languages,
      Frontend(frontEndPages),
      Backend(backEndPages),
      entities,
      paramGroups
    )
  }
}
