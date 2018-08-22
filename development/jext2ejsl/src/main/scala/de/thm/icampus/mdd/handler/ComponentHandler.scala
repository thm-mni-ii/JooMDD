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
import de.thm.mni.ii.phpparser.ast.Expressions.{MemberCallPropertyAcc, MemberCallStaticAcc, SimpleAssignmentExp}
import de.thm.mni.ii.phpparser.ast.Expressions.SimpleNameVar
import de.thm.mni.ii.phpparser.ast.Statements.ExpressionStmnt

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
               val className =  (fileClass.name match {
                case Some(e) => e.name
                    }).toString
                val parentName =  (fileClass.extend match {
                  case Some(e) => e.name.name
                }).toString
                parentName match {
                 case "ListModel" | "JModelList" =>{
                  val getListItemFunkDT  = DetailsPageHandler.searchMethod("getListQuery",bodyClass)
                   if(getListItemFunkDT != null){
                     val allStmtsItem = getListItemFunkDT.body match {  case Some(e) => e
                     }
                     var dbstatement = DetailsPageHandler.searchStatmentAfterMember("this","getDbo",allStmtsItem.stmnts,true)

                     if(dbstatement != null){
                       val dbexp = dbstatement._1.exp.asInstanceOf[SimpleAssignmentExp]
                       val dbVarName = dbexp.variable.asInstanceOf[SimpleNameVar].name.name
                       val fd = DetailsPageHandler.searchStatmentAfterVariable(dbVarName,dbstatement._2,true)
                       if(fd != null){
                         val expm = fd._1.exp.asInstanceOf[SimpleAssignmentExp]
                         val queryVar = DetailsPageHandler.readVaraibleName(expm.variable)
                         val from = DetailsPageHandler.searchStatmentAfterMember(queryVar,"from",fd._2,true)
                         println(from)
                       }

                       println(fd._1)

                     }
                   }
                  }

                   case "ItemModel" | "JModelAdmin" | "FormModel" | "JModelItem"=>{
                     var tableName = ""
                      val getItemFunk = DetailsPageHandler.searchMethod("getItem",bodyClass)
                     if(getItemFunk != null){

                       val allStmts = getItemFunk.body match {  case Some(e) => e
                       }
                       val statement = DetailsPageHandler.searchStatmentAfterMember("this","getTable",allStmts.stmnts,true)

                       if(statement != null){
                         val expm = statement._1.exp.asInstanceOf[SimpleAssignmentExp]
                         val call = expm.exp.asInstanceOf[MemberCallPropertyAcc]
                         if(call.args.size != 0){
                           tableName = DetailsPageHandler.readMemberCallStaticAccAttr(call.from,call.member,call.args,getItemFunk,"this","getTable",0)
                         }
                       }
                     }
                      if (tableName =="" || tableName == null){
                       val getSave = DetailsPageHandler.searchMethod("save",bodyClass)
                       if(getSave != null){

                         val allStmtsSave = getSave.body match {  case Some(e) => e
                         }
                         val statement = DetailsPageHandler.searchStatmentAfterMember("this","getTable",allStmtsSave.stmnts,true)
                         if(statement != null){
                           val expm =statement._1.exp.asInstanceOf[SimpleAssignmentExp]
                           val call = expm.exp.asInstanceOf[MemberCallPropertyAcc]
                           if(call.args.size != 0){
                             tableName = DetailsPageHandler.readMemberCallStaticAccAttr(call.from,call.member,call.args,getSave,"this","getTable",0)
                           }
                         }
                       }
                     }
                      if (tableName =="" || tableName == null){
                      val getTableFunc = DetailsPageHandler.searchMethod("getTable",bodyClass)
                     if(getTableFunc != null){
                       tableName = DetailsPageHandler.searchTableInMeth(getTableFunc)
                       println(tableName)
                     }
                    }
                      if(tableName =="" || tableName == null){
                       var tempTablePAth = backendPath +"tables" + modelFilename
                       if(Files.exists(tempTablePAth)){
                         tableName = pageName
                       }
                     }
                     var dbName = ""
                     if(tableName != "" && tableName != null){
                       val tablePath = backendPath +"tables" + (tableName.toLowerCase + ".php")
                       if(Files.exists(tablePath)){
                      val tableContentText = Source.fromFile(tablePath).mkString
                       val tableContentParsed = PHPParser.parse(tableContentText)
                       val tableClass = DetailsPageHandler.searchClass(tableContentParsed)
                       val tableBodyClass = tableClass.body
                         val consMeth = DetailsPageHandler.searchMethod("__construct",tableBodyClass)
                         if(consMeth != null){
                           val consMethStmtsSave = consMeth.body match {  case Some(e) => e
                           }
                           val statement = DetailsPageHandler.searchStatmentAfterMember("parent","__construct",consMethStmtsSave.stmnts,true)

                           if(statement != null && statement._1.exp.isInstanceOf[ MemberCallStaticAcc]){
                             val call = statement._1.exp.asInstanceOf[MemberCallStaticAcc]
                             if(call.args.size != 0){
                               dbName  = DetailsPageHandler.readMemberCallStaticAccAttr(call.from,call.member,call.args,consMeth,"parent","__construct",0)
                              println(dbName)
                             }
                           }

                         }

                       }
                     }else{
                       val getItemFunkDT = DetailsPageHandler.searchMethod("getItem",bodyClass)
                       if(getItemFunkDT != null){
                         val allStmtsItem = getItemFunk.body match {  case Some(e) => e
                         }
                         var dbstatement = DetailsPageHandler.searchStatmentAfterMember("this","getDbo",allStmtsItem.stmnts,true)

                         if(dbstatement != null){
                           val dbexp = dbstatement._1.exp.asInstanceOf[SimpleAssignmentExp]
                           val dbVarName = dbexp.variable.asInstanceOf[SimpleNameVar].name.name
                           val fd = DetailsPageHandler.searchStatmentAfterVariable(dbVarName,dbstatement._2,true)
                           if(fd != null){
                             val expm = fd._1.exp.asInstanceOf[SimpleAssignmentExp]
                             val queryVar = DetailsPageHandler.readVaraibleName(expm.variable)
                             val from = DetailsPageHandler.searchStatmentAfterMember(queryVar,"from",fd._2,true)
                             println(from)
                           }

                          println(fd._1)

                         }
                       }
                     }



                   }
                 }




            /** val model = PHPParser2.parseFile(modelpath+modelFilename)
              * val classSchema = model.get(0).asInstanceOf[Clazz];
              * val className = classSchema.name
              * val classParent = classSchema.superClass match {
              * case Some(e) => e
              * }

              **
              *} */

            var pageGroupParamNames = Set.empty[String]
            val paramsPath = viewFolder + "tmpl/default.xml"
            if (Files.exists(paramsPath)) {
              val pageParams = readParams(paramsPath)
              pageGroupParamNames = pageParams.map(p ⇒ p.name)
              paramGroups = paramGroups ++ pageParams
            }

            if (!pageName.startsWith(extensionName)) {
              frontEndPages = frontEndPages + createPage(frontendPath, pageName, pageGroupParamNames)
            }

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
