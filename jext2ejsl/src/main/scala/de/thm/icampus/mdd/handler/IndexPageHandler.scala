package de.thm.icampus.mdd.handler

import java.nio.file.Path

import de.thm.icampus.mdd.handler
import de.thm.icampus.mdd.handler.DetailsPageHandler.{readVaraibleName, toIgnoreAttribute}
import de.thm.mni.ii.phpparser.PHPParser
import de.thm.mni.ii.phpparser.PHPParser.Result
import de.thm.mni.ii.phpparser.ast.Basic.{DQStringElement, DQStringLiteral, QualifiedName, SQStringLiteral}
import de.thm.mni.ii.phpparser.ast.{Expressions, Statements}
import de.thm.mni.ii.phpparser.ast.Statements._
import de.thm.mni.ii.phpparser.ast.Expressions._
import de.thm.mni.ii.phpparser.ast.Basic
import java.nio.file.Files
import java.io.File

import de.thm.icampus.mdd.model.extensions._
import de.thm.icampus.mdd.implicits.OptionUtils._

import scala.io.{Codec, Source}
import scala.xml.{Node, XML}
//tim
object IndexPageHandler {
  val toIgnoreFilter = List("search","state","created_by","id","limit")

  def readMemberFromMemberPropertyAcc(sd: Statement): String = {
    sd match{
      case ExpressionStmnt(exp)=>{
       exp match{
         case MemberPropertyAcc(from,member)=>{
          if(member.isInstanceOf[NameMember])
            return member.asInstanceOf[NameMember].name.name
        }
        case _ =>
       }
    }
      case _ =>
    }
    return null
  }



  def searchAttribute(path: Path): Seq[String] ={
   if(Files.exists(path)){
     val configAsXMLString = Source.fromFile(path.toFile).mkString
      var allValueString = Seq.empty[String]
     PHPParser.parse(configAsXMLString) match {
       case s: PHPParser.Success => {
         val default = s.script.s
         val forStmntAll = searchStatmentAfterType("ForeachStmnt",default)
         if(!forStmntAll.isEmpty){
           for(stmnt <- forStmntAll) {
             var forStmnt = stmnt.asInstanceOf[ForeachStmnt]
             val value = readVaraibleName(forStmnt.value)
             val allValueUse = searchStatmentAfterVariable(value, forStmnt.stmnts, true)
            allValueString = allValueString.++ (allValueUse.map(sd => {
               readMemberFromMemberPropertyAcc(sd)
             }).toSeq  )

           }
               /** var allRouteObjeckt = searchStatmentAfterMember("Route","_",forStmnt.stmnts,true)
                 * if(allRouteObjeckt)
                 * allRouteObjeckt = searchStatmentAfterMember("JRoute","_",forStmnt.stmnts,true)
                var allroute = allRouteObjeckt.map(g => readLink(g)).toSeq*/
           return allValueString
         }



       }
       case f: PHPParser.Failure =>{
       println(f.fullMsg)
       }

     }
   }


  return Seq.empty[String]
  }
  def searchStatmentAfterType(typeName:String,statements:Seq[Statement]):  Seq[Statement]={
    var result = Seq.empty[Statement]
    for(item <-statements){
      if(item.getClass.getSimpleName == typeName)   {
        result = result .:+(item)
      }
      else{
      if(item.isInstanceOf[CompoundStmnt]){
        result = result .++(searchStatmentAfterType(typeName,(item.asInstanceOf[CompoundStmnt]).stmnts))

      }else{
        item.getClass.getSimpleName match {
          case "TryStmnt"=>{
             var trystm = item.asInstanceOf[TryStmnt]
            result =  result.++(searchStatmentAfterType(typeName,trystm.stmnt.stmnts))


          }
          case "IfStmnt"=>{

            val ifstm = item.asInstanceOf[IfStmnt]
              var tempvarValue = searchStatmentAfterType(typeName,ifstm.stmnts)
              if(ifstm.elseStmnts != null && !ifstm.elseStmnts.isEmpty){
                ifstm.elseStmnts match {
                  case Some(elseItems)=>{
                    tempvarValue = tempvarValue .++(searchStatmentAfterType(typeName,elseItems) )
                  }
                }

              result =  result.++(tempvarValue)
            }
          }
          case _=>
        }

      }
      }

    }

    return result
  }


  def searchStatmentAfterVariable(fromName: String, statements: Seq[Statement], checkIFstmnt:Boolean):  Seq[Statement]={
    var result = Seq.empty[Statement]
    for(item <-statements){
      if(item.isInstanceOf[CompoundStmnt]){
        result = result .++(searchStatmentAfterVariable(fromName,(item.asInstanceOf[CompoundStmnt]).stmnts,checkIFstmnt))

      }else{
        item match {
          case Statements.TryStmnt(stmnt, catches, fin)=>{

             result =  result.++(searchStatmentAfterVariable(fromName,stmnt.stmnts,checkIFstmnt))


          }
          case Statements.IfStmnt(exp,stmntsIf,elseifs,elseStmnts)=>{
            if(checkIFstmnt){
              var tempvarValue = searchStatmentAfterVariable(fromName,stmntsIf,checkIFstmnt)
              if(elseStmnts != null && !elseStmnts.isEmpty){
                elseStmnts match {
                  case Some(elseItems)=>{
                     tempvarValue = tempvarValue .++(searchStatmentAfterVariable(fromName,elseItems,checkIFstmnt))
                  }
                }
              }
              result =  result.++(tempvarValue)
            }
          }
          case Statements.ExpressionStmnt(exp)=>{
            exp match {
              case c : SimpleAssignmentExp =>{
                c.exp match{
                  case MemberCallPropertyAcc(from,member,args)=>{
                    if(from.isInstanceOf[SimpleNameVar] && (from.asInstanceOf[SimpleNameVar]).name.name == fromName){
                      result = result.:+(item.asInstanceOf[ExpressionStmnt]);
                    }else{
                      var j = DetailsPageHandler.searchExpressionAfterFrom(fromName,from)
                      if(j != null){
                        result = result.:+(item.asInstanceOf[ExpressionStmnt])
                      }else{
                        val argsStmnt = args.map(c => new ExpressionStmnt(c.exp) ).toSeq
                        result = result.++(searchStatmentAfterVariable(fromName,argsStmnt,true))
                      }

                    }
                  }
                  case _=>
                }
              }
              case v :MemberCallPropertyAcc =>{
                if(v.from.isInstanceOf[SimpleNameVar] && (v.from.asInstanceOf[SimpleNameVar]).name.name == fromName){
                  result = result.:+(item.asInstanceOf[ExpressionStmnt]);
                }else{

                  var j = DetailsPageHandler.searchExpressionAfterFrom(fromName,v.from)
                  if(j != null && j != "")
                    result = result.:+(item.asInstanceOf[ExpressionStmnt]);
                  else{
                    val argsStmnt = v.args.map(c => new ExpressionStmnt(c.exp) ).toSeq
                    result = result.++(searchStatmentAfterVariable(fromName,argsStmnt,true))
                  }
                }
              }
              case c :CastExp=>{
                result = result.++(searchStatmentAfterVariable(fromName,Seq(new ExpressionStmnt(c.exp) ),true))
              }
              case b :EchoIntrinsic =>{
                val stmt = b.exps.map(t => new ExpressionStmnt(t)).toSeq
                result = result.++(searchStatmentAfterVariable(fromName,stmt,true))
            }
              case  t : TernaryExp =>{
                var tf : Expression = t.first match{
                  case Some(e) => e.asInstanceOf[Expression]
                  case _=> null
                }
                result = result.++(searchStatmentAfterVariable(fromName,Seq(new ExpressionStmnt(tf),new ExpressionStmnt(t.second)),true))
              }
              case f :MemberCallStaticAcc =>{
                if(f.from.isInstanceOf[SimpleNameVar] && (f.from.asInstanceOf[SimpleNameVar]).name.name == fromName){
                  result = result.:+(item.asInstanceOf[ExpressionStmnt]);
                }else{
                  val j = DetailsPageHandler.searchExpressionAfterFrom(fromName,f.from)
                  if(j != null && j != "")
                    result =  result.:+(item.asInstanceOf[ExpressionStmnt]);
                  else{
                    val argsStmnt = f.args.map(c => new ExpressionStmnt(c.exp) ).toSeq
                    result = result.++(searchStatmentAfterVariable(fromName,argsStmnt,true))
                  }
                }
              }
              case f :MemberPropertyAcc=>{
                if(f.from.isInstanceOf[SimpleNameVar] && (f.from.asInstanceOf[SimpleNameVar]).name.name == fromName){
                  result = result.:+(item.asInstanceOf[ExpressionStmnt]);
                }else{
                  val j = DetailsPageHandler.searchExpressionAfterFrom(fromName,f.from)
                  if(j != null && j != "")
                    result =  result.:+(item.asInstanceOf[ExpressionStmnt]);

                }
              }
              case _=>

            }

          }
          case _=>
        }
      }


    }

    return result
  }
  def searchStatmentAfterMember(fromVarName:String,memberName: String, statements: Seq[Statement], checkIFstmnt:Boolean):Seq[ExpressionStmnt]={
    var result:Seq[ExpressionStmnt] = Seq.empty[ExpressionStmnt]
    for(item <-statements){
      if(item.isInstanceOf[CompoundStmnt]){
       result = result ++ searchStatmentAfterMember(fromVarName,memberName,(item.asInstanceOf[CompoundStmnt]).stmnts,checkIFstmnt)

      }else{
        item match {
          case Statements.TryStmnt(stmnt, catches, fin)=>{

            result = result ++searchStatmentAfterMember(fromVarName,memberName,stmnt.stmnts,checkIFstmnt)


          }
          case Statements.IfStmnt(exp,stmntsIf,elseifs,elseStmnts)=>{
            if(checkIFstmnt){
              result = result ++searchStatmentAfterMember(fromVarName,memberName,stmntsIf,checkIFstmnt)
              if( elseStmnts != null && !elseStmnts.isEmpty){
                elseStmnts match {
                  case Some(elseItems)=>{
                    result = result ++ searchStatmentAfterMember(fromVarName,memberName,elseItems,checkIFstmnt)
                  }
                }
              }

            }
          }
          case Statements.ExpressionStmnt(exp)=>{
            exp match {
              case c : SimpleAssignmentExp =>{
                c.exp match{
                  case MemberCallPropertyAcc(from,member,args)=>{
                    if(!from.isInstanceOf[SimpleNameVar]){
                      val h = searchExpressionAfterFromAndMember(fromVarName,memberName,from)
                      if(h != null && !h.isEmpty)
                        result = result:+(item.asInstanceOf[ExpressionStmnt]);
                    }
                    if(member.isInstanceOf[NameMember] && (member.asInstanceOf[NameMember]).name.name == memberName && DetailsPageHandler.readVaraibleName(from) == fromVarName){
                      result =  result:+ (item.asInstanceOf[ExpressionStmnt]);
                    }
                  }
                  case _=>
                }
              }
              case v :MemberCallPropertyAcc =>{
                if(!v.from.isInstanceOf[SimpleNameVar]){
                  val h = searchExpressionAfterFromAndMember(fromVarName,memberName,v.from)
                  if(h != null && !h.isEmpty)
                   result =  result:+(item.asInstanceOf[ExpressionStmnt])
                }
                if(v.member.isInstanceOf[NameMember] && (v.member.asInstanceOf[NameMember]).name.name == memberName && DetailsPageHandler.readVaraibleName(v.from) == fromVarName){
                  result =  result:+ (item.asInstanceOf[ExpressionStmnt]);
                }
              }

              case f :MemberCallStaticAcc =>{
                if(f.member.isInstanceOf[NameMember] && (f.member.asInstanceOf[NameMember]).name.name == memberName&& DetailsPageHandler.readVaraibleName(f.from) == fromVarName){
                  result = result:+(item.asInstanceOf[ExpressionStmnt]);
                }
              }
              case c :CastExp=>{
                result = result.++(searchStatmentAfterMember(fromVarName,memberName,Seq(new ExpressionStmnt(c.exp) ),true))
              }
              case b :EchoIntrinsic =>{
                val stmt = b.exps.map(t => new ExpressionStmnt(t)).toSeq
                result = result.++(searchStatmentAfterMember(fromVarName,memberName,stmt,true))
              }
              case  t : TernaryExp =>{
                var tf : Expression = t.first match{
                  case Some(e) => e.asInstanceOf[Expression]
                  case _=> null
                }
                result = result.++(searchStatmentAfterMember(fromVarName,memberName,Seq(new ExpressionStmnt(tf),new ExpressionStmnt(t.second)),true))
              }
              case _=>

            }

          }
          case _=>
        }
      }


    }
    return result
  }
  def searchExpressionAfterFromAndMember( fromVarName:String,memberName: String, from: Expression): Seq[Expression] ={
  var result :Seq[Expression] = Seq.empty[Expression]
    from match{
      case v :MemberCallPropertyAcc =>{
        if((v.member.asInstanceOf[NameMember]).name.name == memberName && readVaraibleName(v.from) == fromVarName){
         result =  result.:+(from)
        }
        else{
          if((v.member.asInstanceOf[NameMember]).name.name == memberName ){
          val fromSrc = DetailsPageHandler.searchExpressionAfterFrom(memberName,v.from)
            if(fromSrc != null){
              result.:+(new MemberCallPropertyAcc(fromSrc.asInstanceOf[Variable], v.member,v.args) )
             return result.++(searchExpressionAfterFromAndMember(fromVarName,memberName,v.from))
            }
          }else{
            return  result.++(searchExpressionAfterFromAndMember(fromVarName,memberName,v.from))
          }

        }

      }
      case f :MemberCallStaticAcc =>{
        if((f.member.asInstanceOf[NameMember]).name.name == memberName&& readVaraibleName(f.from) == fromVarName ){
          result = result.:+(from)
        }
        else{
          if((f.member.asInstanceOf[NameMember]).name.name == memberName ){
            val fromSrc = DetailsPageHandler.searchExpressionAfterFrom(memberName,f.from)
            if(fromSrc != null){
              result.:+(new MemberCallPropertyAcc(fromSrc.asInstanceOf[Variable], f.member,f.args) )
              return result.++(searchExpressionAfterFromAndMember(fromVarName,memberName,f.from))
            }
          }else{
            return  result.++(searchExpressionAfterFromAndMember(fromVarName,memberName,f.from))
          }

        }

      }
      case b: SimpleNameVar=>{

        return result
      }

      case c :QualifiedNameVar =>{

        return result
      }

      case e: ScopeAccessVar =>{

        return result
      }
      case _=>
    }

    return result
  }
 def createIndexpage(bodyClass:Seq[Statements.MemberDecl],pageName:String,modelpath:Path,viewFolder:Path): Page ={
    var dbName:String =""
   var nodbo = true
    var getListItemFunkDT  = DetailsPageHandler.searchMethod("getListQuery",bodyClass)
   if(getListItemFunkDT == null){
     getListItemFunkDT  = DetailsPageHandler.searchMethod("buildQuery",bodyClass)
   }
    if(getListItemFunkDT != null){
      val allStmtsItem = getListItemFunkDT.body match {  case Some(e) => e
      }
      var dbstatement = DetailsPageHandler.searchStatmentAfterMember("this","getDbo",allStmtsItem.stmnts,true)
      if(dbstatement == null){
        dbstatement = DetailsPageHandler.searchStatmentAfterMember("this","db",allStmtsItem.stmnts,true)
        nodbo = false
      }
      if(dbstatement != null){
        var fd:(ExpressionStmnt, Seq[Statement]) = null
        var dbVarName = ""
        if(nodbo){
          val dbexp = dbstatement._1.exp.asInstanceOf[SimpleAssignmentExp]
           dbVarName = dbexp.variable.asInstanceOf[SimpleNameVar].name.name
         fd = DetailsPageHandler.searchStatmentAfterMember(dbVarName,"getQuery",dbstatement._2,true)
        }else{
          fd = dbstatement
        }
        if(fd != null){
          var expm = fd._1.exp.asInstanceOf[SimpleAssignmentExp]
          val queryVar = DetailsPageHandler.readVaraibleName(expm.variable)
          val from = DetailsPageHandler.searchStatmentAfterMember(queryVar,"from",dbstatement._2,true)
          if(from != null){
            from._1 match{
              case ExpressionStmnt(ept) =>{
                if(ept.isInstanceOf[MemberCallPropertyAcc]){
                  val r = ept.asInstanceOf[MemberCallPropertyAcc]
                  if(r.args.size >0){
                    dbName = DetailsPageHandler.readMemberCallStaticAccAttr(r.from,r.member,r.args,getListItemFunkDT,queryVar,"from",0)

                    val sqlParsew = "#[a-zA-Z0-9\\_]*".r
                    val tableAsArr = dbName.split(" ")
                    var tableAs :String =""
                    if(tableAsArr.length >1)
                      tableAs = tableAsArr.last

                    val dbTablew = sqlParsew.findFirstMatchIn(dbName) match{
                      case Some(value)=> value.matched
                      case _=>
                    }
                    var result = Seq.empty[ExpressionStmnt]
                    val attrRegex = s"$tableAs\\.[a-zA-Z0-9_]+".r
                    val allqueryFilterObject = IndexPageHandler.searchStatmentAfterMember(queryVar,"where",dbstatement ._2,true)
                    var allqueryFilterString = allqueryFilterObject.map(f => {
                      if(f.exp.isInstanceOf[MemberCallPropertyAcc]){
                        val h = f.exp.asInstanceOf[MemberCallPropertyAcc];
                        val res = DetailsPageHandler.readMemberCallStaticAccAttr(h.from,h.member,h.args,getListItemFunkDT,queryVar,"where",0)
                        if(res != null  && !res.isEmpty){
                          attrRegex.findFirstMatchIn(res)match{
                            case Some(value)=> value.matched.split("\\.").last
                            case _=>
                          }
                        }
                      }
                    })
                    allqueryFilterString =  allqueryFilterString.distinct
                    allqueryFilterString =  allqueryFilterString.filter( t => !toIgnoreFilter.contains(t)).toSeq
                    val filterName = "filter_"+ pageName +".xml"
                    val filterPath = new File (modelpath + "/forms/"+filterName)
                    var filters: Seq[String] = Seq.empty[String]
                    allqueryFilterString.foreach(f => filters = filters.:+(f.toString))
                    var colums =  Seq.empty[String]
                    if(Files.exists(filterPath.toPath)){
                      val configAsXMLString = Source.fromFile(filterPath).mkString
                      val configAsXML = XML.loadString(configAsXMLString)
                      val form = configAsXML \\ "form"
                      val fieldsSet = form \\ "fields"
                      val fields = fieldsSet \\ "field"
                      filters = fields.map(r => r \@ "name").toSeq
                    }
                    filters.distinct
                    filters = filters.filter(t => !toIgnoreFilter.contains(t))
                    val kl = new File(viewFolder.toString + "/tmpl/default.php")
                    colums = IndexPageHandler.searchAttribute(kl.toPath)
                    var pageGroupParamNames = Set.empty[String]
                    val paramsPath = new File(viewFolder.toString + "/tmpl/default.xml")
                    var pageParams = Set.empty[JParamGroup]
                    if (Files.exists(paramsPath.toPath)) {
                       pageParams = readParams(paramsPath.toPath)
                    }
                    var resultIndexPage = new IndexPage(pageName,dbTablew.toString.split("_").last,pageParams,colums.toSet,filters.toSet)
                    resultIndexPage.modelPath = (modelpath ).toString + File.separator  + pageName + ".php"
                    resultIndexPage.viewPath = (viewFolder).toString + File.separator  +"view.html.php"
                    resultIndexPage.viewLayoutPath = viewFolder.toString + File.separator +"tmpl"+ File.separator + "default.php"


                    return resultIndexPage


                  }
                }
              }
              case _=>
            }
          }
        }
      }

    }
   var resultCustom = new CustomPage(pageName)
   resultCustom.modelPath = (modelpath ).toString + File.separator  + pageName + ".php"
   resultCustom.viewPath = (viewFolder).toString + File.separator  +"view.html.php"
   resultCustom.viewFolder = (viewFolder).toString
   return resultCustom
  }
  val toIgnoreFiedlAttr= List("label","description","type","name")
  def createParam(tg: Node):JParam  = {
    val name = tg \@ "name"
    if(name != "" && !name.toString.isEmpty && !toIgnoreAttribute.contains(name) ) {

      val htmlType = tg \@ "type"
      val desc = tg \@ "description"
      val label = tg \@ "label"
      var attr = Map.empty[String, String]
      for (hv <- tg.attributes) {
        if (!toIgnoreFiedlAttr.contains(hv.key))
          attr = attr.+(hv.key -> hv.value.toString())
      }
      val option = tg \\ "option"
      var valueK = Map.empty[String, String]
      if (option != null && !option.isEmpty) {
        for (opt <- option) {
          valueK = valueK.+(opt.text -> opt \@ "value")
        }
      }
      return new JParam(name.trim, htmlType, label, desc, valueK, attr)
    }
    return null
  }
   def readParams(configPath: Path): Set[JParamGroup] = {
    val configAsXMLString = Source.fromFile(configPath.toString).mkString
    val configAsXML = XML.loadString(configAsXMLString)

    val fieldSets = configAsXML \\ "fieldset"

    fieldSets.map(fieldSet ⇒ {
      val fields = fieldSet \\ "field"

      val params = fields.map(field ⇒ {
        createParam(field)
      }).toSet

      val fieldSetName = fieldSet \@ "name"
     new  JParamGroup(fieldSetName, params)
    }).toSet
  }
}