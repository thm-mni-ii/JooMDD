package de.thm.icampus.mdd.handler

import java.nio.file.{Files, Path}

import de.thm.icampus.mdd.implicits.Conversions._
import de.thm.icampus.mdd.parser.SQLParser
import de.thm.mni.ii.phpparser.PHPParser

import scala.collection.JavaConversions._
import de.thm.icampus.mdd.model.{Language, Manifest}
import de.thm.icampus.mdd.model.extensions._
import de.thm.icampus.mdd.model.sql.Table
import de.thm.icampus.mdd.model.sql.Entity
import scala.io.{Codec, Source}
import scala.xml.{Elem, XML}

object ComponentHandler extends Handler {


  def setEntity(e: Page, entities: List[Entity]): Unit = {
    e match{
      case d :DetailsPage => d.setEntityOb(entities)
      case i :IndexPage => i.setEntityOb(entities)
      case _=>
    }
  }

  def searchGloSet(allParamsSet: List[List[String]]):List[String] ={

    var diffSet = List.empty[String]
    var unionSet = List.empty[String]
    for(s <-allParamsSet){
     for(e <- s) {
       if (unionSet.contains(e)) {
         diffSet = diffSet.::(e)
       } else {
         unionSet = unionSet.::(e)
       }
     }
    }
    return diffSet
  }

  def jgroupExist(frontPageParams: Set[JParamGroup], df: JParamGroup): Boolean ={
    frontPageParams.foreach(t=>{
      if(t.name == df.name)
        return true
    })
    return false
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
              case "ItemModel" | "JModelAdmin" | "FormModel" | "JModelItem"| "JModelLegacy" => {
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


    var entities = List.empty[Entity]
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
           entities = entities.++(SQLParser.parseFile(sqlInstallPath))
           sqlFileList = sqlFileList.:+(er.text)
         }
       }
      )


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
              case "ItemModel" | "JModelAdmin" | "FormModel" | "JModelItem" | "AdminModel" | "JModelLegacy"=> {
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
    entities.foreach(s=> s.setExtentionName(extensionName))
   if(!frontEndPages.isEmpty)
   frontEndPages.forEach(e => setEntity(e,entities))
    if(!backEndPages.isEmpty)
    backEndPages.foreach(d=> setEntity(d,entities))
    //val allParamsSet = frontEndPages.map(p=> p.globalParamNames.map(x => x.name).toList).toList
    //val globalSet = searchGloSet(allParamsSet)
    //val allParamssObject = frontEndPages.map(p=> p.globalParamNames.map(x => )).toList
    //var frontPageParams : Set[JParamGroup] = Set.empty[JParamGroup]
   //frontEndPages.foreach(f => (f.globalParamNames.foreach(df=>if(!jgroupExist(frontPageParams,df))frontPageParams = frontPageParams .+(df))))
    //val genParam = backEndPages.filter(d => frontPageParams.contains(d))

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
