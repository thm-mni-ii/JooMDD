package de.thm.icampus.mdd.handler

import de.thm.mni.ii.phpparser.PHPParser
import de.thm.mni.ii.phpparser.PHPParser.Result
import de.thm.mni.ii.phpparser.ast.Basic.{DQStringElement, DQStringLiteral, QualifiedName, SQStringLiteral}
import de.thm.mni.ii.phpparser.ast.{Expressions, Statements}
import de.thm.mni.ii.phpparser.ast.Statements._
import de.thm.mni.ii.phpparser.ast.Expressions._
import de.thm.mni.ii.phpparser.ast.Basic

object DetailsPageHandler {

  def searchExpressionAfterFrom(fromName: String, from: Expression): Expression ={

    from match{
      case v :MemberCallPropertyAcc =>{
        val g = searchExpressionAfterFrom(fromName,v.from)
        if(g != null)
          return g

      }
      case f :MemberCallStaticAcc =>{
        val g = searchExpressionAfterFrom(fromName,f.from)
        if(g != null)
          return g
      }
      case b: SimpleNameVar=>{
        if(b.name.name == fromName){
          return from
        }
      }
      case c :QualifiedNameVar =>{
        if(c.name.name.name == fromName)
        return from
      }

      case e: ScopeAccessVar =>{
        if(e.scope.toString.toLowerCase == fromName)
        return from
      }
      case _=>
    }

    return null
  }

  def searchExpressionAfterMember( fromVarName:String,memberName: String, from: Expression): Expression ={

    from match{
      case v :MemberCallPropertyAcc =>{
        if((v.member.asInstanceOf[NameMember]).name.name == memberName && readVaraibleName(v.from) == fromVarName){
          return from
        }
        else{searchExpressionAfterFrom(memberName,v.from)}

      }
      case f :MemberCallStaticAcc =>{
        if((f.member.asInstanceOf[NameMember]).name.name == memberName&& readVaraibleName(f.from) == fromVarName ){
          return from
        }
        else{searchExpressionAfterFrom(memberName,f.from)}

      }
      case b: SimpleNameVar=>{

          return null
      }

      case c :QualifiedNameVar =>{

          return null
      }

      case e: ScopeAccessVar =>{

          return null
      }
      case _=>
    }

    return null
  }

  def searchStatmentAfterVariable(fromName: String, statements: Seq[Statement], checkIFstmnt:Boolean): (ExpressionStmnt, Seq[Statement])={
    for(item <-statements){
      if(item.isInstanceOf[CompoundStmnt]){
        val r = searchStatmentAfterVariable(fromName,(item.asInstanceOf[CompoundStmnt]).stmnts,checkIFstmnt)
        if(r != null && r._1 != null)
          return r
      }else{
        item match {
          case Statements.TryStmnt(stmnt, catches, fin)=>{

            var result =  searchStatmentAfterVariable(fromName,stmnt.stmnts,checkIFstmnt)
            if(result!= null && result._1 != null)
              return result

          }
          case Statements.IfStmnt(exp,stmntsIf,elseifs,elseStmnts)=>{
            if(checkIFstmnt){
              val tempvarValue = searchStatmentAfterVariable(fromName,stmntsIf,checkIFstmnt)
              if(tempvarValue == null && elseStmnts != null && !elseStmnts.isEmpty){
                elseStmnts match {
                  case Some(elseItems)=>{
                    val tempvarValue = searchStatmentAfterVariable(fromName,elseItems,checkIFstmnt)
                  }
                }
              }
              if(tempvarValue != null && tempvarValue._1 != null)
                return  tempvarValue
            }
          }
          case Statements.ExpressionStmnt(exp)=>{
            exp match {
              case c : SimpleAssignmentExp =>{
                c.exp match{
                  case MemberCallPropertyAcc(from,member,args)=>{
                    if(from.isInstanceOf[SimpleNameVar] && (from.asInstanceOf[SimpleNameVar]).name.name == fromName){
                      return (item.asInstanceOf[ExpressionStmnt] , statements);
                    }else{
                      var j = searchExpressionAfterFrom(fromName,from)
                      if(j != null)
                        return (item.asInstanceOf[ExpressionStmnt],statements);
                    }
                  }
                  case _=>
                }
              }
              case v :MemberCallPropertyAcc =>{
                if(v.from.isInstanceOf[SimpleNameVar] && (v.from.asInstanceOf[SimpleNameVar]).name.name == fromName){
                  return (item.asInstanceOf[ExpressionStmnt],statements);
                }else{

                  var j = searchExpressionAfterFrom(fromName,v.from)
                  if(j != null && j != "")
                    return (item.asInstanceOf[ExpressionStmnt],statements);

                }
              }
              case f :MemberCallStaticAcc =>{
                if(f.from.isInstanceOf[SimpleNameVar] && (f.from.asInstanceOf[SimpleNameVar]).name.name == fromName){
                  return (item.asInstanceOf[ExpressionStmnt],statements);
                }else{
                  var j = searchExpressionAfterFrom(fromName,f.from)
                  if(j != null && j != "")
                    return (item.asInstanceOf[ExpressionStmnt],statements);
                }
              }
              case _=>

            }

          }
          case _=>
        }
      }


    }

    return null
  }

  def searchStatmentAfterMember(fromVarName:String,memberName: String, statements: Seq[Statement], checkIFstmnt:Boolean): (ExpressionStmnt, Seq[Statement])={
   for(item <-statements){
      if(item.isInstanceOf[CompoundStmnt]){
       val r = searchStatmentAfterMember(fromVarName,memberName,(item.asInstanceOf[CompoundStmnt]).stmnts,checkIFstmnt)
        if(r != null && r._1 != null)
          return r
      }else{
        item match {
          case Statements.TryStmnt(stmnt, catches, fin)=>{

            var result =  searchStatmentAfterMember(fromVarName,memberName,stmnt.stmnts,checkIFstmnt)
            if(result!= null && result._1 != null)
              return result

          }
          case Statements.IfStmnt(exp,stmntsIf,elseifs,elseStmnts)=>{
            if(checkIFstmnt){
              val tempvarValue = searchStatmentAfterMember(fromVarName,memberName,stmntsIf,checkIFstmnt)
              if(tempvarValue == null && elseStmnts != null && !elseStmnts.isEmpty){
                elseStmnts match {
                  case Some(elseItems)=>{
                    val tempvarValue = searchStatmentAfterMember(fromVarName,memberName,elseItems,checkIFstmnt)
                  }
                }
              }
              if(tempvarValue != null && tempvarValue._1 != null)
                return  tempvarValue
            }
          }
          case Statements.ExpressionStmnt(exp)=>{
            exp match {
              case c : SimpleAssignmentExp =>{
                c.exp match{
                  case MemberCallPropertyAcc(from,member,args)=>{
                    if(!from.isInstanceOf[SimpleNameVar]){
                      val h = searchExpressionAfterMember(fromVarName,memberName,from)
                      if(h != null)
                        return (item.asInstanceOf[ExpressionStmnt] , statements);
                    }
                    if(member.isInstanceOf[NameMember] && (member.asInstanceOf[NameMember]).name.name == memberName && readVaraibleName(from) == fromVarName){
                      return (item.asInstanceOf[ExpressionStmnt] , statements);
                    }
                  }
                  case _=>
                }
              }
              case v :MemberCallPropertyAcc =>{
                if(!v.from.isInstanceOf[SimpleNameVar]){
                  val h = searchExpressionAfterMember(fromVarName,memberName,v.from)
                  if(h != null)
                    return (item.asInstanceOf[ExpressionStmnt] , statements);
                }
                if(v.member.isInstanceOf[NameMember] && (v.member.asInstanceOf[NameMember]).name.name == memberName && readVaraibleName(v.from) == fromVarName){
                  return (item.asInstanceOf[ExpressionStmnt],statements);
                }
              }

              case f :MemberCallStaticAcc =>{
                if(f.member.isInstanceOf[NameMember] && (f.member.asInstanceOf[NameMember]).name.name == memberName&& readVaraibleName(f.from) == fromVarName){
                  return (item.asInstanceOf[ExpressionStmnt],statements);
                }
              }
              case _=>

            }

          }
          case _=>
        }
      }


     }

    return null
  }


  def searchClass(fileContent:Result): ClassDecl={
    fileContent match {
      case s: PHPParser.Success =>
        val filestruct = s.script.s
        val fileClass = (filestruct.filter(_.isInstanceOf[ClassDecl])).last.asInstanceOf[ClassDecl]
        return  fileClass

      case f: PHPParser.Failure =>
            println(f.fullMsg);

    }
    return null
  }

  def searchMethod(name:String, body : scala.Seq[Statements.MemberDecl]):MethodDecl ={

    for(elemt <- body){
      if(elemt.isInstanceOf[MethodDecl]){
        val meth = elemt.asInstanceOf[MethodDecl]
        val methname = (meth.header.name match {
          case Some(e) => e.name
        }).toString
        if( methname == name){
          return meth;
        }
      }
    }
    return null
  }


  def searchLastValueInBody(varName: String, stmnts: Seq[Statement], checkIFstmnt:Boolean):Expression ={
    var varValue:Expression = null
    for(item <- stmnts){
      item match {
        case Statements.TryStmnt(stmnt, catches, fin)=>{
        varValue = searchLastValueInBody(varName,stmnt.stmnts,checkIFstmnt)
      }
        case Statements.IfStmnt(exp,stmntsIf,elseifs,elseStmnts)=>{
          if(checkIFstmnt){
            val tempvarValue = searchLastValueInBody(varName,stmntsIf,checkIFstmnt)
             if(tempvarValue == ""&& elseStmnts != null){
               elseStmnts match {
                 case Some(elseItems)=>{
                   val tempvarValue = searchLastValueInBody(varName,elseItems,checkIFstmnt)
                 }
               }
             }
            varValue = tempvarValue
          }
        }
        case Statements.ExpressionStmnt(exp)=>{
          if(exp.isInstanceOf[SimpleAssignmentExp]){
            val c = exp.asInstanceOf[SimpleAssignmentExp]
            if(!c.byRef && c.variable.isInstanceOf[SimpleNameVar] && (c.variable.asInstanceOf[SimpleNameVar]).name.name == varName){
              varValue = c.exp
            }
          }

        }
        case _=>
      }

    }
    return varValue
  }

  def searchLastValueInParam(varName: String, params: Seq[ParameterDecl]): String = {
    for(item <- params){
      item match {
        case SimpleParam(t,desVar,name,defValue) =>{
          if(name.name.name == varName && !defValue.isEmpty){
            defValue match {
              case Some(v)=>{
               return literalToString(v)
              }

            }
          }
        }
        case  _=>
      }

    }
    return null
  }

  def searchTableInMeth(geTabelMeth:MethodDecl ):String={

    val allStmts = geTabelMeth.body match {  case Some(e) => e
    }
    val returnStmt = allStmts.stmnts.filter(_.isInstanceOf[ReturnStmnt]).last.asInstanceOf[ReturnStmnt]
    val exp = returnStmt.exp match {
      case Some(value) => value
    }
    exp match{
      case SimpleNameVar(s) =>{
          val varVal = searchLastValueInBody(s.name,allStmts.stmnts,false)
        varVal match{
            case MemberCallStaticAcc(from,member,args) =>{
              return readMemberCallStaticAccAttr(from,member,args,geTabelMeth,"Table","getInstance",0)
            }
            case _=>
          }
      }
      case MemberCallStaticAcc(from,member,args) =>{

       return readMemberCallStaticAccAttr(from,member,args,geTabelMeth,"Table","getInstance",0)
      }
      case _=>
    }

    return ""
  }

  def readMemberCallStaticAccAttr(from:Variable,member:MemberName,args:scala.Seq[Expressions.ArgumentExpression],geTabelMeth:MethodDecl,fromName:String,memberName:String,argsindex:Integer): String ={
    val allStmts = geTabelMeth.body match {  case Some(e) => e
    }

    var className = readVaraibleName(from)
      val memberVal = (member.asInstanceOf[NameMember]).name.name
      if(className == fromName && memberVal ==memberName){
        val typeTable = args(argsindex).exp;
        typeTable match{
          case SimpleNameVar(s)=>{
            var varValue = searchLastValueInBody(s.name,allStmts.stmnts,false)
            if(varValue == null){
              return searchLastValueInParam(s.name,geTabelMeth.header.params)
            }else{
              return literalToString(varValue )
            }

          }
          case _=> return literalToString(typeTable)
        }

      }


    return null
  }

  def literalToString(exp:Expression):String={

    exp match{
      case DQStringLiteral(prefix,value)=>{
        var res = ""
        val l = value.map( x => res = res + x.asInstanceOf[DQStringElement].s)
        return res
      }
      case SQStringLiteral(g,h)=>{
        return h
      }
      case _=>
        return null
    }
  }
  def readVaraibleName(exp:Expression):String={

    exp match{
      case c :QualifiedNameVar =>{
        return c.name.name.name
      }
      case d :SimpleNameVar=>{
        return d.name.name
      }
      case e: ScopeAccessVar =>{
        return e.scope.toString.toLowerCase
      }
      case _=>
    }
    return ""
  }

}
