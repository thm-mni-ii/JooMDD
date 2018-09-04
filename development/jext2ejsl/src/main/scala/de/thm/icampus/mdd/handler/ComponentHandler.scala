package de.thm.icampus.mdd.handler

import java.nio.file.{Files, Path}
import java.io.File

import de.thm.icampus.mdd.implicits.Conversions._
import de.thm.icampus.mdd.implicits.OptionUtils._
import de.thm.icampus.mdd.parser.SQLParser
import de.thm.mni.ii.phpparser.PHPParser
import de.thm.mni.ii.phpparser.ast.Statements.{ClassDecl, CompoundStmnt, MethodDecl}

import scala.collection.JavaConversions._
import de.thm.icampus.mdd.model.{Language, Manifest}
import de.thm.icampus.mdd.model.extensions._
import de.thm.icampus.mdd.model.sql.Table
import de.thm.mni.ii.phpparser.ast.Expressions.{MemberCallPropertyAcc, MemberCallStaticAcc, SimpleAssignmentExp}
import de.thm.mni.ii.phpparser.ast.Expressions.SimpleNameVar
import de.thm.mni.ii.phpparser.ast.Statements.ExpressionStmnt

import scala.io.{Codec, Source}
import scala.xml.{Elem, XML}

object ComponentHandler extends Handler {




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
      val modelpath = frontendPath + "models"

      Files.newDirectoryStream(viewsPath).foreach {
        case viewFolder: Path if Files.isDirectory(viewFolder) ⇒ {
          val pageName: String = viewFolder.getFileName.toString
          val modelFilename = pageName + ".php"
          if (Files.exists(modelpath + modelFilename)) {
            val ts = Source.fromFile(modelpath + modelFilename).mkString
            val fileResult = PHPParser.parse(ts)
            val fileClass = DetailsPageHandler.searchClass(fileResult)
            val bodyClass = fileClass.body
            val className = (fileClass.name match {
              case Some(e) => e.name
            }).toString
            val parentName = (fileClass.extend match {
              case Some(e) => e.name.name
            }).toString
            parentName match {
              case "ListModel" | "JModelList" => {
                frontEndPages = frontEndPages.+(IndexPageHandler.createIndexpage(bodyClass, pageName, modelpath, viewFolder))
              }
              case "ItemModel" | "JModelAdmin" | "FormModel" | "JModelItem" => {
                if(parentName == "ItemModel" || parentName == "JModelItem" )
                frontEndPages = frontEndPages.+(DetailsPageHandler.createDetailsPage(bodyClass,false,modelpath,backendPath,modelFilename,pageName, viewFolder))
                else
                  frontEndPages = frontEndPages.+(DetailsPageHandler.createDetailsPage(bodyClass,true,modelpath,backendPath,modelFilename,pageName, viewFolder))

              }
              case _ =>  frontEndPages = frontEndPages.+(new CustomPage(className))
            }
          }
        }
          case ignored: Path ⇒ println("Ignore File: " + ignored)
        }
      }


    var entities = List.empty[JEntity]
    if (!backendSuffix.isEmpty && Files.exists(backendPath)) { // Handle backendSection
      val viewsPath = backendPath + "views"
      val modelpath = backendPath + "models"
      // Read Sql Create Statements and create entities
      var sqlTables = List.empty[Table]
      var sqlFileList = List.empty[String]
      val sqlInstallPathString = (xmlManifest \ "install" \ "sql" \ "file").foreach(
       er =>{
         if(!sqlFileList.contains(er.text)){
         val sqlInstallPath = backendPath + er.text
         sqlTables = sqlTables.++(SQLParser.parseFile(sqlInstallPath))
           sqlFileList = sqlFileList.:+(er.text)
         }
       }
      )

       entities = sqlTables.map(t => {
        var newName = if(t.name.startsWith(extensionName + "_")) t.name.substring(extensionName.length + 1) else t.name
        newName = if(newName.endsWith("s")) newName.dropRight(1) else newName
        JEntity("^" + newName, t.columns.map(c ⇒ Attribute(c.name, c.dataType, c.isprimary)))
      })

      Files.newDirectoryStream(viewsPath).foreach{
        case viewFolder: Path if Files.isDirectory(viewFolder) ⇒ {
          val pageName: String = viewFolder.getFileName.toString
          val modelFilename = pageName + ".php"
          if (Files.exists(modelpath + modelFilename)) {
            val ts = Source.fromFile(modelpath + modelFilename).mkString
            val fileResult = PHPParser.parse(ts)
            val fileClass = DetailsPageHandler.searchClass(fileResult)
            val bodyClass = fileClass.body
            val className = (fileClass.name match {
              case Some(e) => e.name
            }).toString
            val parentName = (fileClass.extend match {
              case Some(e) => e.name.name
            }).toString
            parentName match {
              case "ListModel" | "JModelList" => {
                backEndPages = backEndPages.+(IndexPageHandler.createIndexpage(bodyClass, pageName, modelpath, viewFolder))
              }
              case "ItemModel" | "JModelAdmin" | "FormModel" | "JModelItem" | "AdminModel"=> {
                if(parentName == "ItemModel" || parentName == "JModelItem" )
                  backEndPages = backEndPages.+(DetailsPageHandler.createDetailsPage(bodyClass,false,modelpath,backendPath,modelFilename,pageName, viewFolder))
                else
                  backEndPages = backEndPages.+(DetailsPageHandler.createDetailsPage(bodyClass,true,modelpath,backendPath,modelFilename,pageName, viewFolder))

              }
              case _=> backEndPages = backEndPages.+(new CustomPage(className))
            }
          }
        }
        case ignored: Path ⇒ println("Ignore File: " + ignored)
      }
    }


    val backendConfigPath = backendPath + "config.xml"
    if (Files.exists(backendConfigPath)) {
      paramGroups = paramGroups ++ IndexPageHandler.readParams(backendConfigPath)
    }
    val frontendConfigPath = frontendPath + "config.xml"
    if (Files.exists(frontendConfigPath)) {
      paramGroups = paramGroups ++ IndexPageHandler.readParams(frontendConfigPath)
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
