package de.thm.icampus.mdd.handler

import java.nio.file.{Files, Path}
import java.io.File

import de.thm.icampus.mdd.implicits.Conversions._
import de.thm.icampus.mdd.implicits.OptionUtils._
import de.thm.icampus.mdd.parser.SQLParser

import scala.collection.JavaConversions._
import de.thm.icampus.mdd.model.{Language, Manifest}
import de.thm.icampus.mdd.model.extensions._

import scala.io.{Codec, Source}
import scala.xml.{Elem, XML}

object ComponentHandler extends Handler {

  private def readParams(configPath: Path): Set[JParamGroup] = {
    val configAsXMLString = Source.fromFile(configPath).mkString
    val configAsXML = XML.loadString(configAsXMLString)

    val fieldSets = configAsXML \\ "fieldset"

    fieldSets.map(fieldSet ⇒ {
      val fields = fieldSet \\ "field"

      val params = fields.map(field ⇒ {
        val name = "^" + (field \@ "name")
        val htmltype = field \@ "type"
        val default = (field \@ "default") asOpt
        val label = field \@ "label"
        val description = field \@ "description"
        val size = ((field \@ "size") asOpt).?[Option[Int]] (e ⇒ Option(Integer.parseInt(e)))(None)

        JParam(name, ("^" + htmltype.replace(" (","_").replace(")",""),htmltype), label, description, default, size)
      }).toSet

      val fieldSetName = fieldSet \@ "name"
      JParamGroup(fieldSetName, params)
    }).toSet
  }

  private def createPage(path: Path, fileName: String, pageGroupParamNames: Set[String]) : Page = {
    val name = "^" + fileName
    if (fileName.endsWith("s")) {
      if(Files.exists(path + ("tables" + File.separator + fileName.dropRight(1) + ".php"))) {
        IndexPage(name, name.dropRight(1), pageGroupParamNames)
      } else {
        CustomPage(name, pageGroupParamNames)
      }
    } else {
      if(Files.exists(path + ("tables" + File.separator + fileName + ".php"))){
        DetailsPage(name, name, pageGroupParamNames)
      } else {
        CustomPage(name, pageGroupParamNames)
      }
    }
  }

  def handle(extensionRoot: Path, xmlManifest: Elem)(implicit codec: Codec = Codec.UTF8): ComponentExtension = {
    val manifest: Manifest = Manifest.fromXml(xmlManifest)
    val languages: Set[Language] = Language.fromXmlManifest(xmlManifest, extensionRoot)

    val xmlName = (xmlManifest \ "name").text.toLowerCase // com_name -> name
    val extensionName = if(xmlName.startsWith("com_")) xmlName.substring(4) else xmlName

    val frontendSuffix = xmlManifest \ "files" \@ "folder"
    val frontendPath = extensionRoot + frontendSuffix

    val backendSuffix = xmlManifest \ "administration" \ "files" \@ "folder"
    val backendPath = extensionRoot + backendSuffix

    var frontEndPages = Set.empty[Page]
    var backEndPages = Set.empty[Page]

    var paramGroups = Set.empty[JParamGroup]

    if (!frontendSuffix.isEmpty && Files.exists(frontendPath)) { // Handle frontendSection
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

          if (!fileName.startsWith(extensionName)){
            frontEndPages = frontEndPages + createPage(frontendPath, fileName, pageGroupParamNames)
          }

        }
        case ignored: Path ⇒ println("Ignore File: " + ignored)
      }
    }



    if (!backendSuffix.isEmpty && Files.exists(backendPath)) { // Handle backendSection
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

          if (!fileName.startsWith(extensionName)){
            backEndPages = backEndPages + createPage(backendPath, fileName, pageGroupParamNames)
          }
        }
        case ignored: Path ⇒ println("Ignore File: " + ignored)
      }
    }

    // Read Sql Create Statements and create entities
    val sqlInstallPathString = (xmlManifest \ "install" \ "sql" \ "file").text
    val sqlInstallPath = backendPath + sqlInstallPathString

    val sqlTables = SQLParser.parseFile(sqlInstallPath)

    val entities = sqlTables.map(t => {
      var newName = if(t.name.startsWith(extensionName + "_")) t.name.substring(extensionName.length + 1) else t.name
      newName = if(newName.endsWith("s")) newName.dropRight(1) else newName
      JEntity("^" + newName, t.columns.map(c ⇒ Attribute(c.name, c.dataType, c.isprimary)))
    })

    val backendConfigPath = backendPath + "config.xml"
    if (Files.exists(backendConfigPath)) {
      paramGroups = paramGroups ++ readParams(backendConfigPath)
    }
    val frontendConfigPath = frontendPath + "config.xml"
    if (Files.exists(frontendConfigPath)) {
      paramGroups = paramGroups ++ readParams(frontendConfigPath)
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
      "^" + extensionName,
      manifest,
      languages,
      Frontend(frontEndPages),
      Backend(backEndPages),
      entities,
      paramGroups
    )
  }
}
