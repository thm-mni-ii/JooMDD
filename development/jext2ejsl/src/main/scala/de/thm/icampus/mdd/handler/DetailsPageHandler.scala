package de.thm.icampus.mdd.handler

import java.nio.file.{Files, Path}
import java.io.File

import de.thm.icampus.mdd.handler.IndexPageHandler.readParams
import de.thm.mni.ii.phpparser.PHPParser
import de.thm.mni.ii.phpparser.PHPParser.Result
import de.thm.mni.ii.phpparser.ast.Basic.{DQStringElement, DQStringLiteral, QualifiedName, SQStringLiteral}
import de.thm.mni.ii.phpparser.ast.{Expressions, Statements}
import de.thm.mni.ii.phpparser.ast.Statements._
import de.thm.mni.ii.phpparser.ast.Expressions._
import de.thm.mni.ii.phpparser.ast.Basic
import de.thm.icampus.mdd.model.extensions._

import scala.io.Source
import scala.xml.{Node, XML}
//tim
object DetailsPageHandler {
  val toIgnoreAttribute = List("state","created_by","ordering","checked_out_time","checked_out","published","params","asset_id","rules","filter")
  val toIgnoreFiedlAttr= List("label","description","type","name")
  def searchExpressionAfterFrom(fromName: String, from: Expression): Expression = {

    from match {
      case v: MemberCallPropertyAcc => {
        val g = searchExpressionAfterFrom(fromName, v.from)
        if (g != null)
          return g

      }
      case f: MemberPropertyAcc => {
        return searchExpressionAfterFrom(fromName, f.from)
      }
      case f: MemberCallStaticAcc => {
        val g = searchExpressionAfterFrom(fromName, f.from)
        if (g != null)
          return g
      }
      case b: SimpleNameVar => {
        if (b.name.name == fromName) {
          return from
        }
      }
      case c: QualifiedNameVar => {
        if (c.name.name.name == fromName)
          return from
      }

      case e: ScopeAccessVar => {
        if (e.scope.toString.toLowerCase == fromName)
          return from
      }
      case _ =>
    }

    return null
  }

  def searchExpressionAfterFromAndMember(fromVarName: String, memberName: String, from: Expression): Expression = {

    from match {
      case v: MemberCallPropertyAcc => {
        if ((v.member.asInstanceOf[NameMember]).name.name == memberName && readVaraibleName(v.from) == fromVarName) {
          return from
        }
        else {
          if ((v.member.asInstanceOf[NameMember]).name.name == memberName) {
            return searchExpressionAfterFrom(memberName, v.from)
          } else {
            return searchExpressionAfterFromAndMember(fromVarName, memberName, v.from)
          }

        }

      }
      case f: MemberCallStaticAcc => {
        if ((f.member.asInstanceOf[NameMember]).name.name == memberName && readVaraibleName(f.from) == fromVarName) {
          return from
        }
        else {
          if ((f.member.asInstanceOf[NameMember]).name.name == memberName) {
            return searchExpressionAfterFrom(memberName, f.from)
          } else {
            return searchExpressionAfterFromAndMember(fromVarName, memberName, f.from)
          }
        }

      }
      case b: SimpleNameVar => {

        return null
      }

      case c: QualifiedNameVar => {

        return null
      }

      case e: ScopeAccessVar => {

        return null
      }
      case _ =>
    }

    return null
  }

  def searchStatmentAfterVariable(fromName: String, statements: Seq[Statement], checkIFstmnt: Boolean): (ExpressionStmnt, Seq[Statement]) = {
    for (item <- statements) {
      if (item.isInstanceOf[CompoundStmnt]) {
        val r = searchStatmentAfterVariable(fromName, (item.asInstanceOf[CompoundStmnt]).stmnts, checkIFstmnt)
        if (r != null && r._1 != null)
          return r
      } else {
        item match {
          case Statements.TryStmnt(stmnt, catches, fin) => {

            var result = searchStatmentAfterVariable(fromName, stmnt.stmnts, checkIFstmnt)
            if (result != null && result._1 != null)
              return result

          }
          case Statements.IfStmnt(exp, stmntsIf, elseifs, elseStmnts) => {
            if (checkIFstmnt) {
              var tempvarValue = searchStatmentAfterVariable(fromName, stmntsIf, checkIFstmnt)
              if (tempvarValue == null && elseStmnts != null && !elseStmnts.isEmpty) {
                elseStmnts match {
                  case Some(elseItems) => {
                    tempvarValue = searchStatmentAfterVariable(fromName, elseItems, checkIFstmnt)
                  }
                }
              }
              if (tempvarValue != null && tempvarValue._1 != null)
                return tempvarValue
            }
          }
          case Statements.ExpressionStmnt(exp) => {
            exp match {
              case c: SimpleAssignmentExp => {
                c.exp match {
                  case MemberCallPropertyAcc(from, member, args) => {
                    if (from.isInstanceOf[SimpleNameVar] && (from.asInstanceOf[SimpleNameVar]).name.name == fromName) {
                      return (item.asInstanceOf[ExpressionStmnt], statements);
                    } else {
                      var j = searchExpressionAfterFrom(fromName, from)
                      if (j != null)
                        return (item.asInstanceOf[ExpressionStmnt], statements);
                    }
                  }
                  case _ =>
                }
              }
              case v: MemberCallPropertyAcc => {
                if (v.from.isInstanceOf[SimpleNameVar] && (v.from.asInstanceOf[SimpleNameVar]).name.name == fromName) {
                  return (item.asInstanceOf[ExpressionStmnt], statements);
                } else {

                  var j = searchExpressionAfterFrom(fromName, v.from)
                  if (j != null && j != "")
                    return (item.asInstanceOf[ExpressionStmnt], statements);

                }
              }
              case f: MemberCallStaticAcc => {
                if (f.from.isInstanceOf[SimpleNameVar] && (f.from.asInstanceOf[SimpleNameVar]).name.name == fromName) {
                  return (item.asInstanceOf[ExpressionStmnt], statements);
                } else {
                  val j = searchExpressionAfterFrom(fromName, f.from)
                  if (j != null && j != "")
                    return (item.asInstanceOf[ExpressionStmnt], statements);
                }
              }
              case _ =>

            }

          }
          case _ =>
        }
      }


    }

    return null
  }

  def searchStatmentAfterMember(fromVarName: String, memberName: String, statements: Seq[Statement], checkIFstmnt: Boolean): (ExpressionStmnt, Seq[Statement]) = {
    for (item <- statements) {
      if (item.isInstanceOf[CompoundStmnt]) {
        val r = searchStatmentAfterMember(fromVarName, memberName, (item.asInstanceOf[CompoundStmnt]).stmnts, checkIFstmnt)
        if (r != null && r._1 != null)
          return r
      } else {
        item match {
          case Statements.TryStmnt(stmnt, catches, fin) => {

            var result = searchStatmentAfterMember(fromVarName, memberName, stmnt.stmnts, checkIFstmnt)
            if (result != null && result._1 != null)
              return result

          }
          case Statements.IfStmnt(exp, stmntsIf, elseifs, elseStmnts) => {
            if (checkIFstmnt) {
              val tempvarValue = searchStatmentAfterMember(fromVarName, memberName, stmntsIf, checkIFstmnt)
              if (tempvarValue == null && elseStmnts != null && !elseStmnts.isEmpty) {
                elseStmnts match {
                  case Some(elseItems) => {
                    val tempvarValue = searchStatmentAfterMember(fromVarName, memberName, elseItems, checkIFstmnt)
                  }
                }
              }
              if (tempvarValue != null && tempvarValue._1 != null)
                return tempvarValue
            }
          }
          case Statements.ExpressionStmnt(exp) => {
            exp match {
              case c: SimpleAssignmentExp => {
                c.exp match {
                  case MemberCallPropertyAcc(from, member, args) => {
                    if (!from.isInstanceOf[SimpleNameVar]) {
                      val h = searchExpressionAfterFromAndMember(fromVarName, memberName, from)
                      if (h != null)
                        return (item.asInstanceOf[ExpressionStmnt], statements);
                    }
                    if (member.isInstanceOf[NameMember] && (member.asInstanceOf[NameMember]).name.name == memberName && readVaraibleName(from) == fromVarName) {
                      return (item.asInstanceOf[ExpressionStmnt], statements);
                    }
                  }
                  case _ =>
                }
              }
              case v: MemberCallPropertyAcc => {
                if (!v.from.isInstanceOf[SimpleNameVar]) {
                  val h = searchExpressionAfterFromAndMember(fromVarName, memberName, v.from)
                  if (h != null)
                    return (item.asInstanceOf[ExpressionStmnt], statements);
                }
                if (v.member.isInstanceOf[NameMember] && (v.member.asInstanceOf[NameMember]).name.name == memberName && readVaraibleName(v.from) == fromVarName) {
                  return (item.asInstanceOf[ExpressionStmnt], statements);
                }
              }

              case f: MemberCallStaticAcc => {
                if (f.member.isInstanceOf[NameMember] && (f.member.asInstanceOf[NameMember]).name.name == memberName && readVaraibleName(f.from) == fromVarName) {
                  return (item.asInstanceOf[ExpressionStmnt], statements);
                }
              }
              case _ =>

            }

          }
          case _ =>
        }
      }


    }

    return null
  }


  def searchClass(fileContent: Result): ClassDecl = {
    fileContent match {
      case s: PHPParser.Success =>
        val filestruct = s.script.s
        val fileClass = (filestruct.filter(_.isInstanceOf[ClassDecl])).last.asInstanceOf[ClassDecl]
        return fileClass

      case f: PHPParser.Failure =>
        println(f.fullMsg);

    }
    return null
  }

  def searchMethod(name: String, body: scala.Seq[Statements.MemberDecl]): MethodDecl = {

    for (elemt <- body) {
      if (elemt.isInstanceOf[MethodDecl]) {
        val meth = elemt.asInstanceOf[MethodDecl]
        val methname = (meth.header.name match {
          case Some(e) => e.name
        }).toString
        if (methname == name) {
          return meth;
        }
      }
    }
    return null
  }


  def searchLastValueInBody(varName: String, stmnts: Seq[Statement], checkIFstmnt: Boolean): Expression = {
    var varValue: Expression = null
    for (item <- stmnts) {
      item match {
        case Statements.TryStmnt(stmnt, catches, fin) => {
          varValue = searchLastValueInBody(varName, stmnt.stmnts, checkIFstmnt)
        }
        case Statements.IfStmnt(exp, stmntsIf, elseifs, elseStmnts) => {
          if (checkIFstmnt) {
            val tempvarValue = searchLastValueInBody(varName, stmntsIf, checkIFstmnt)
            if (tempvarValue == "" && elseStmnts != null) {
              elseStmnts match {
                case Some(elseItems) => {
                  val tempvarValue = searchLastValueInBody(varName, elseItems, checkIFstmnt)
                }
              }
            }
            varValue = tempvarValue
          }
        }
        case Statements.ExpressionStmnt(exp) => {
          if (exp.isInstanceOf[SimpleAssignmentExp]) {
            val c = exp.asInstanceOf[SimpleAssignmentExp]
            if (!c.byRef && c.variable.isInstanceOf[SimpleNameVar] && (c.variable.asInstanceOf[SimpleNameVar]).name.name == varName) {
              varValue = c.exp
            }
          }

        }
        case _ =>
      }

    }
    return varValue
  }

  def searchLastValueInParam(varName: String, params: Seq[ParameterDecl]): String = {
    for (item <- params) {
      item match {
        case SimpleParam(t, desVar, name, defValue) => {
          if (name.name.name == varName && !defValue.isEmpty) {
            defValue match {
              case Some(v) => {
                return literalToString(v)
              }

            }
          }
        }
        case _ =>
      }

    }
    return null
  }

  def searchTableInMeth(geTabelMeth: MethodDecl): String = {

    val allStmts = geTabelMeth.body match {
      case Some(e) => e
    }
    val returnStmt = allStmts.stmnts.filter(_.isInstanceOf[ReturnStmnt]).last.asInstanceOf[ReturnStmnt]
    val exp = returnStmt.exp match {
      case Some(value) => value
    }
    exp match {
      case SimpleNameVar(s) => {
        val varVal = searchLastValueInBody(s.name, allStmts.stmnts, false)
        varVal match {
          case MemberCallStaticAcc(from, member, args) => {
            var tableStr= readMemberCallStaticAccAttr(from, member, args, geTabelMeth, "Table", "getInstance", 0)
            if(tableStr =="" || tableStr ==null)
              return readMemberCallStaticAccAttr(from, member, args, geTabelMeth, "JTable", "getInstance", 0)
           else return tableStr
          }
          case _ =>
        }
      }
      case MemberCallStaticAcc(from, member, args) => {
        var tableStr =readMemberCallStaticAccAttr(from, member, args, geTabelMeth, "Table", "getInstance", 0)
        if(tableStr ==""  || tableStr ==null)
        return readMemberCallStaticAccAttr(from, member, args, geTabelMeth, "JTable", "getInstance", 0)
        else return tableStr
      }
      case _ =>
    }

    return ""
  }

  def readMemberCallStaticAccAttr(from: Variable, member: MemberName, args: scala.Seq[Expressions.ArgumentExpression], geTabelMeth: MethodDecl, fromName: String, memberName: String, argsindex: Integer): String = {
    val allStmts = geTabelMeth.body match {
      case Some(e) => e
    }

    var className = readVaraibleName(from)
    val memberVal = (member.asInstanceOf[NameMember]).name.name
    if (className == fromName && memberVal == memberName) {
      val typeTable = args(argsindex).exp;
      typeTable match {
        case SimpleNameVar(s) => {
          var varValue = searchLastValueInBody(s.name, allStmts.stmnts, false)
          if (varValue == null) {
            return searchLastValueInParam(s.name, geTabelMeth.header.params)
          } else {
            return literalToString(varValue)
          }

        }

        case _ => return literalToString(typeTable)
      }

    }


    return null
  }

  def literalToString(exp: Expression): String = {

    exp match {
      case DQStringLiteral(prefix, value) => {
        var res = ""
        val l = value.map(x => {
          x match {
            case c: DQStringElement => {
              res = res + c.s
            }
            case _ =>
          }
        })
        return res
      }
      case SQStringLiteral(g, h) => {
        return h
      }
      case SubExp(exp1, exp2) => {
        return literalToString(exp1) + literalToString(exp2)
      }
      case MemberCallPropertyAcc(from,mem,args) =>{
        return (args.map(h => literalToString(h.exp))).mkString
      }
      case _ =>
        return ""
    }
  }

  def readVaraibleName(exp: Expression): String = {

    exp match {
      case c: QualifiedNameVar => {
        return c.name.name.name
      }
      case d: SimpleNameVar => {
        return d.name.name
      }
      case e: ScopeAccessVar => {
        return e.scope.toString.toLowerCase
      }
      case _ =>
    }
    return ""
  }

  def createAttribut(tg: Node):DetailsPageAttribute  = {
    val name = tg \@ "name"
    if(!toIgnoreAttribute.contains(name)){
      val htmlType = tg \@ "type"
      var attr = Map.empty[String,String]
     for(hv <- tg.attributes){
       if(!toIgnoreFiedlAttr.contains(hv.key))
         attr = attr.+(hv.key->hv.value.toString())
     }
      val option = tg \\"option"
      var valueK = Map.empty[String,String]
      if(option !=null && !option.isEmpty){
        for(opt <-option){
          valueK = valueK.+ (opt.text -> opt \@ "value")
        }
      }
      return new DetailsPageAttribute(name,htmlType,valueK,attr)
    }
    return null
  }

  def readAttribute(formPath: File): Set[DetailsPageAttribute] = {
    if(formPath.exists()){
      var allattr = Set.empty[DetailsPageAttribute]
      val attrAsXMLString = Source.fromFile(formPath.toString).mkString
      val attrAsXML = XML.loadString(attrAsXMLString)
      val attrSet = attrAsXML \\"field"
      allattr=  allattr.++(attrSet.map(tg => {
        createAttribut(tg)
      }).toSet)
      val setAttr = attrAsXML \\"fieldset"
    /**var f = setAttr.map(t =>{
        var sAttr = t \\ "field"
      allattr=  allattr.++(sAttr.map(tg => {
        createAttribut(tg)
      }))
      })*/
      return allattr

    }

    return Set.empty[DetailsPageAttribute]


  }

  def createDetailsPage(bodyClass: Seq[Statements.MemberDecl],isEdit:Boolean, modelPath:Path, backendPath: Path, modelFilename: String, pageName:String, viewFolder:Path): Page = {
    var tableName = ""
    var dbName = ""
    val getItemFunk = DetailsPageHandler.searchMethod("getItem", bodyClass)
    if (getItemFunk != null) {

      val allStmts = getItemFunk.body match {
        case Some(e) => e
      }
      val statement = DetailsPageHandler.searchStatmentAfterMember("this", "getTable", allStmts.stmnts, true)

      if (statement != null) {
        val expm = statement._1.exp.asInstanceOf[SimpleAssignmentExp]
        val call = expm.exp.asInstanceOf[MemberCallPropertyAcc]
        if (call.args.size != 0) {
          tableName = DetailsPageHandler.readMemberCallStaticAccAttr(call.from, call.member, call.args, getItemFunk, "this", "getTable", 0)
        }
      }
    }
    if (tableName == "" ) {
      val getSave = searchMethod("save", bodyClass)
      if (getSave != null) {
        val allStmtsSave = getSave.body match {
          case Some(e) => e
        }
        val statement = searchStatmentAfterMember("this", "getTable", allStmtsSave.stmnts, true)
        if (statement != null) {
          val expm = statement._1.exp.asInstanceOf[SimpleAssignmentExp]
          val call = expm.exp.asInstanceOf[MemberCallPropertyAcc]
          if (call.args.size != 0) {
            tableName = readMemberCallStaticAccAttr(call.from, call.member, call.args, getSave, "this", "getTable", 0)
          }
        }
      }
    }
    if (tableName == "" ) {
      val getTableFunc = searchMethod("getTable", bodyClass)
      if (getTableFunc != null) {
        tableName = searchTableInMeth(getTableFunc)
        // println(tableName)
      }
    }
    if (tableName == "") {
      var tempTablePAth = new File (backendPath.toString + "tables" + modelFilename)
      if (tempTablePAth.exists()) {
        tableName = pageName
      }
    }
    if (tableName != "" ) {
      val tablePath = new File (backendPath.toString + "/tables/" + (tableName.toLowerCase + ".php"))
      if (tablePath.exists()) {
        val tableContentText = Source.fromFile(tablePath).mkString
        val tableContentParsed = PHPParser.parse(tableContentText)
        val tableClass = DetailsPageHandler.searchClass(tableContentParsed)
        val tableBodyClass = tableClass.body
        val consMeth = DetailsPageHandler.searchMethod("__construct", tableBodyClass)
        if (consMeth != null) {
          val consMethStmtsSave = consMeth.body match {
            case Some(e) => e
          }
          val statement = DetailsPageHandler.searchStatmentAfterMember("parent", "__construct", consMethStmtsSave.stmnts, true)

          if (statement != null && statement._1.exp.isInstanceOf[MemberCallStaticAcc]) {
            val call = statement._1.exp.asInstanceOf[MemberCallStaticAcc]
            if (call.args.size != 0) {
              dbName = DetailsPageHandler.readMemberCallStaticAccAttr(call.from, call.member, call.args, consMeth, "parent", "__construct", 0)

            }
          }
        }
      }
    } else {
      val getItemFunkDT = DetailsPageHandler.searchMethod("getItem", bodyClass)
      if (getItemFunkDT != null) {
        val allStmtsItem = getItemFunk.body match {
          case Some(e) => e
        }
        var dbstatement = DetailsPageHandler.searchStatmentAfterMember("this", "getDbo", allStmtsItem.stmnts, true)
        if (dbstatement != null) {
          val dbexp = dbstatement._1.exp.asInstanceOf[SimpleAssignmentExp]
          val dbVarName = dbexp.variable.asInstanceOf[SimpleNameVar].name.name
          val fd = DetailsPageHandler.searchStatmentAfterVariable(dbVarName, dbstatement._2, true)
          if (fd != null) {
            val expm = fd._1.exp.asInstanceOf[SimpleAssignmentExp]
            val queryVar = DetailsPageHandler.readVaraibleName(expm.variable)
            val from = DetailsPageHandler.searchStatmentAfterMember(queryVar, "from", fd._2, true)
            if (from != null) {
              from._1 match {
                case ExpressionStmnt(ept) => {
                  if (ept.isInstanceOf[MemberCallPropertyAcc]) {
                    val r = ept.asInstanceOf[MemberCallPropertyAcc]
                    if (r.args.size > 0) {
                      dbName = DetailsPageHandler.readMemberCallStaticAccAttr(r.from, r.member, r.args, getItemFunkDT, queryVar, "from", 0)
                    }
                  }
                }
                case _ =>
              }
            }
          }
        }
      }
    }
    println(dbName)
    val sqlParse = "#[a-zA-Z0-9\\_]*".r
    val dbTable = sqlParse.findFirstMatchIn(dbName) match {
      case Some(value) => value.matched
      case _ =>
    }
    if(dbName != "" && dbName != null){
      var formPath = new File (modelPath.toString + "/forms/" +pageName +".xml")
      if(!formPath.exists()){
        formPath = new File (backendPath.toString + "/models/forms/" +pageName +".xml")
      }
      var formAttributes = Set.empty[DetailsPageAttribute]
      var column = Set.empty[String]
      var pageParams = Set.empty[JParamGroup]
      var formName = pageName
      if(!formPath.exists()){
        formName = null
        val formMeth = searchMethod("getForm",bodyClass)
        if(formMeth != null){
          val allStmts = formMeth.body match {
            case Some(e) => e
          }
          val loadFormVarStmt = searchStatmentAfterMember("this","loadForm",allStmts.stmnts,true)
          if (loadFormVarStmt != null) {
            val expm = loadFormVarStmt._1.exp.asInstanceOf[SimpleAssignmentExp]
            val call = expm.exp.asInstanceOf[MemberCallPropertyAcc]
            if (call.args.size != 0) {
               formName = readMemberCallStaticAccAttr(call.from, call.member, call.args, formMeth, "this", "loadForm", 1)
            }
          }
        }


      }
      if(formName != null && formName != ""){
        formPath = new File (modelPath.toString + "/forms/" +formName +".xml")
        if(!formPath.exists()){
          formPath = new File (backendPath.toString + "/models/forms/" +formName +".xml")
        }
        formAttributes = readAttribute(formPath)
        formAttributes = formAttributes.filter(d => d != null)
        column = formAttributes.map(r => r.name).toSet
      }
      val paramsPath = new File(viewFolder.toString + "/tmpl/default.xml")
      if (Files.exists(paramsPath.toPath)) {
        pageParams = readParams(paramsPath.toPath)
      }
      return new DetailsPage(pageName,dbTable.toString.split("_").last,isEdit,pageParams,column,formAttributes)
    }


    return new CustomPage(pageName)
  }
}
