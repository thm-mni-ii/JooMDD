package de.thm.icampus.mdd.handler

import java.nio.file.{Files, Path}

import de.thm.icampus.mdd.handler.DetailsPageHandler.readMemberCallStaticAccAttr
import de.thm.icampus.mdd.implicits.Conversions._
import de.thm.icampus.mdd.parser.SQLParser
import de.thm.mni.ii.phpparser.PHPParser

import scala.collection.JavaConversions._
import de.thm.icampus.mdd.model.{Language, Manifest}
import de.thm.icampus.mdd.model.extensions._
import de.thm.icampus.mdd.model.sql.Table
import de.thm.icampus.mdd.model.sql.Entity
import de.thm.mni.ii.phpparser.ast.Expressions.{MemberCallPropertyAcc, SimpleAssignmentExp}

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

      var viewFiles = Files.newDirectoryStream(viewsPath)
      viewFiles.foreach {
        case viewFolder: Path if Files.isDirectory(viewFolder) ⇒ {
          val viewName: String = viewFolder.getFileName.toString
          val ViewFilename = viewName + "/view.html.php"
          val pageName: String = viewFolder.getFileName.toString
          var modelFilename = pageName + ".php"
          var existModelName = Files.exists(modelpath + modelFilename)
          if(!existModelName && Files.exists(viewsPath + ViewFilename)){
             modelFilename = searchModelNameInView(viewsPath,ViewFilename) + ".php"
            existModelName = Files.exists(modelpath + modelFilename)
          }

          if (existModelName) {
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
              case "JModelLegacy" =>{
                var litsQ = DetailsPageHandler.searchMethod("getListQuery",bodyClass)
                var buildQ = DetailsPageHandler.searchMethod("buildQuery",bodyClass)
                if(litsQ != null | buildQ != null){
                  frontEndPages = frontEndPages.+(IndexPageHandler.createIndexpage(bodyClass, pageName, modelpath, viewFolder))
                }else{
                  frontEndPages = frontEndPages.+(DetailsPageHandler.createDetailsPage(bodyClass,true,modelpath,backendPath,modelFilename,pageName, viewFolder))
                }
              }
              case _ =>{

                frontEndPages = frontEndPages.+(new CustomPage(pageName))
              }
            }
          }
        }
          case ignored: Path ⇒ println("Ignore File: " + ignored)
        }
      viewFiles.close();
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


      var viewsFiles = Files.newDirectoryStream(viewsPath)
      viewsFiles.foreach{
        case viewFolder: Path if Files.isDirectory(viewFolder) ⇒ {
          val viewName: String = viewFolder.getFileName.toString
          val ViewFilename = viewName + "/view.html.php"
          val pageName: String = viewFolder.getFileName.toString
          var modelFilename = pageName + ".php"
          var existModelName = Files.exists(modelpath + modelFilename)
          if(!existModelName && Files.exists(viewsPath + ViewFilename)){
            modelFilename = searchModelNameInView(viewsPath,ViewFilename) + ".php"
            existModelName = Files.exists(modelpath + modelFilename)
          }

          if (existModelName) {
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
              case "JModelLegacy" =>{
                var litsQ = DetailsPageHandler.searchMethod("getListQuery",bodyClass)
                var buildQ = DetailsPageHandler.searchMethod("getListQuery",bodyClass)
                if(litsQ != null | buildQ != null){
                  backEndPages = backEndPages.+(IndexPageHandler.createIndexpage(bodyClass, pageName, modelpath, viewFolder))
                }else{
                  backEndPages = backEndPages.+(DetailsPageHandler.createDetailsPage(bodyClass,true,modelpath,backendPath,modelFilename,pageName, viewFolder))
                }
              }
              case _=> backEndPages = backEndPages.+(new CustomPage(pageName))
            }
          }
        }
        case ignored: Path ⇒ println("Ignore File: " + ignored)
      }
      viewsFiles.close();
    }


    val backendConfigPath = backendPath + "config.xml"
    if (Files.exists(backendConfigPath)) {
      paramGroups = paramGroups ++ IndexPageHandler.readParams(backendConfigPath)
    }
    val frontendConfigPath = frontendPath + "config.xml"
    if (Files.exists(frontendConfigPath)) {
      paramGroups = paramGroups ++ IndexPageHandler.readParams(frontendConfigPath)
    }


    entities.foreach(s=> s.setExtentionName(extensionName))
   if(!frontEndPages.isEmpty)
   frontEndPages.forEach(e => setEntity(e,entities))
    if(!backEndPages.isEmpty)
    backEndPages.foreach(d=> setEntity(d,entities))


    var frontEndPagesEnd = Set.empty[Page]
    val d = frontEndPages.foreach(pg =>{
      frontEndPages.foreach(gh=>{
        if(pg ==gh && !frontEndPagesEnd.contains(pg)){
          frontEndPages = frontEndPages.-(gh)
          frontEndPagesEnd += pg
        }
      })
    })

    ComponentExtension(
       extensionName,
      manifest,
      languages,
      new Frontend(frontEndPagesEnd),
      new Backend(backEndPages),
      entities,
      paramGroups
    )
  }

  def searchModelNameInView(viewsPath:Path, ViewFilename:String):String={
    val viewContenString = Source.fromFile(viewsPath + ViewFilename).mkString
    val viewContentAst = PHPParser.parse(viewContenString)
    val ViewClass = DetailsPageHandler.searchClass(viewContentAst)
    val bodyClass = ViewClass.body
    var getDisplayFunkt  = DetailsPageHandler.searchMethod("display",bodyClass)
    if(getDisplayFunkt != null) {
      val allStmtsItem = getDisplayFunkt.body match {
        case Some(e) => e
      }
      var displayModelStatment = DetailsPageHandler.searchStatmentAfterMember("this","getModel",allStmtsItem.stmnts,true)
      if(displayModelStatment != null){
        var expm = displayModelStatment._1.exp.asInstanceOf[SimpleAssignmentExp]
        val call = expm.exp.asInstanceOf[MemberCallPropertyAcc]
        if (call.args.size != 0) {
          var tempModelName = readMemberCallStaticAccAttr(call.from, call.member, call.args, getDisplayFunkt, "this", "getModel", 0)
          tempModelName
        }
      }
    }
    ""
  }
}
