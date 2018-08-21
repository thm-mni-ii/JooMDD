package de.thm.icampus.mdd.handler

import de.thm.mni.ii.phpparser.PHPParser
import de.thm.mni.ii.phpparser.PHPParser.Result
import de.thm.mni.ii.phpparser.ast.Basic.{DQStringElement, DQStringLiteral, QualifiedName, SQStringLiteral}
import de.thm.mni.ii.phpparser.ast.{Expressions, Statements}
import de.thm.mni.ii.phpparser.ast.Statements._
import de.thm.mni.ii.phpparser.ast.Expressions._
import de.thm.mni.ii.phpparser.ast.Basic

object DetailsPageHandler {
  def searchStatment(str: String, statements: Seq[Statement], checkIFstmnt:Boolean): Statement={
   for(item <-statements){
       item match {
         case Statements.TryStmnt(stmnt, catches, fin)=>{
          return searchStatment(str,stmnt.stmnts,checkIFstmnt)
         }
         case Statements.IfStmnt(exp,stmntsIf,elseifs,elseStmnts)=>{
           if(checkIFstmnt){
             val tempvarValue = searchStatment(str,stmntsIf,checkIFstmnt)
             if(tempvarValue == null && elseStmnts != null){
               elseStmnts match {
                 case Some(elseItems)=>{
                   val tempvarValue = searchStatment(str,elseItems,checkIFstmnt)
                 }
               }
             }
             return tempvarValue
           }
         }
         case Statements.ExpressionStmnt(exp)=>{
           if(exp.isInstanceOf[SimpleAssignmentExp]){
             val c = exp.asInstanceOf[SimpleAssignmentExp]
             c.exp match{
               case MemberCallPropertyAcc(from,member,args)=>{
                 if(member.isInstanceOf[NameMember] && (member.asInstanceOf[NameMember]).name.name == str){
                   return item;
                 }
               }
               case _=>
             }
           }

         }
         case _=>
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
    var className = ""
    if(from.isInstanceOf[QualifiedNameVar]){
      className = (from.asInstanceOf[QualifiedNameVar]).name.name.name
    }

    else{
      className = (from.asInstanceOf[SimpleNameVar]).name.name
    }
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

}
