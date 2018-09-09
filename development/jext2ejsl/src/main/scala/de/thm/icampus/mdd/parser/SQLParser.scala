package de.thm.icampus.mdd.parser

import java.io.StringReader
import java.nio.file.Path
import java.util.regex.Pattern

import de.thm.icampus.mdd.implicits.Conversions._
import de.thm.icampus.mdd.implicits.OptionUtils._
import de.thm.icampus.mdd.model.sql.{Attribute, Reference,Entity}
import net.sf.jsqlparser.JSQLParserException
import net.sf.jsqlparser.parser.CCJSqlParserManager
import net.sf.jsqlparser.statement.Statement
import net.sf.jsqlparser.statement.create.table._

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.io.Source
import net.sf.jsqlparser.parser.ParseException

/**
 * Parse SQL files.
 */
object SQLParser {
  val default: SQLParser = new SQLParser

  def parseFile(path: Path): List[Entity] = default.parseFile(path)
}

/**
 * Parse "create table" statement in SQL files
 *
 * please check https://github.com/JSQLParser/JSqlParser for full parser documentation
 */
class SQLParser {
  val toIgnore = List("asset_id","state","ordering","checked_out_time","checked_out","created_by","published","params")
  val lineBreakRE = "[\\n\\r]"
  val tabOrSpaceRE = "([\\t]|\\s)"

  val comment = "(--[^\\r\\n]*)|(\\/\\*\\*[\\w\\W]*\\*\\/)"
  // used to remove the "...if not exists... "
  val createTableRE = s"$tabOrSpaceRE?create$tabOrSpaceRE+table$tabOrSpaceRE+(if$tabOrSpaceRE+not$tabOrSpaceRE+exists$tabOrSpaceRE+)?([^\\(]*)$tabOrSpaceRE?\\([^;]*;$$"
  // important! do not combine this expressions! one run with replaceAll required per exp, otherwise you'll burn your CPU ;-)
  val namedPKRE =      s"($tabOrSpaceRE?primary$tabOrSpaceRE+key$tabOrSpaceRE+)[^\\(]*(\\([^\\)]*\\))$$"
  val namedPKCommaRE = s"($tabOrSpaceRE?primary$tabOrSpaceRE+key$tabOrSpaceRE+)[^\\(]*(\\([^\\)]*\\),)$$"
  // "unique key [name] ([column name(-s)])" has to be convert into "unique [name] ([column name(-s)])"
  val ukeyRE = s"($tabOrSpaceRE?unique$tabOrSpaceRE+)key$tabOrSpaceRE+([^\\(]*)(\\([^\\)]*\\))"
  val tableNameIndex = 8

  val shortCreateTable = s"create([\\t]|\\s)table.*;"

  def parseTableNew(statment: String,parser: CCJSqlParserManager,path:Path ): Entity={
    try {
      return parseStatement(parser.parse(new StringReader(statment)) )

    } catch {
      case e: JSQLParserException => {
        //println(e.printStackTrace())
        println("-----------------------------------------")
        println(s"Ignoring table definition in file: $path due to malformed statement:")
      //  println("JSQLParserException caught: " + e.getCause)
        println("-----------------------------------------")
        if(e.getCause.isInstanceOf[ParseException]){
          val token = (e.getCause.asInstanceOf[ParseException]).currentToken.next.image
          if(token.length >1){
            val tempparser: CCJSqlParserManager = new CCJSqlParserManager
        return  parseTableNew(statment.replaceAll(token, "##_"+token),tempparser,path)
          }
        }

      }
    }
    return null
  }

  def parseFile(path: Path): List[Entity] = {
    var tables: List[Entity] = List.empty[Entity]
    val sqlAsString = Source.fromFile(path).mkString
    val noCommentContent = sqlAsString.replaceAll(comment,"")
    val sqlAsStringWithoutJoomlaPlaceholder = noCommentContent.replaceAll("#__", "").replaceAll("ON DELETE CASCADE", "").replaceAll("ON UPDATE CASCADE", "")

    val statements: List[String] = extractStatements(sqlAsStringWithoutJoomlaPlaceholder)
    val parser: CCJSqlParserManager = new CCJSqlParserManager
    val preparedStatements = statements.map(prepareStatement)

    for (statementString <- preparedStatements) {
      try {
        tables = tables .:+ (parseStatement(parser.parse(new StringReader(statementString)) ))

      } catch {
        case e: JSQLParserException => {
          //println(e.printStackTrace())
          println("-----------------------------------------")
          println(s"Ignoring table definition in file: $path due to malformed statement:")
         // println("JSQLParserException caught: " + e.getCause)
          println("-----------------------------------------")
          if(e.getCause.isInstanceOf[ParseException]){
            val token = (e.getCause.asInstanceOf[ParseException]).currentToken.next.image
            if(token.length >1){
              val tempparser: CCJSqlParserManager = new CCJSqlParserManager
              tables = tables .:+(parseTableNew(statementString.replaceAll(token, "##_"+token),tempparser,path))
            }

          }

        }
      }
    }

    tables.filter(d => d!=null)toList
  }

  def checkUniqProperties(cd: ColumnDefinition, uniQueList: List[Index]):(Boolean,String) = {

    for(ind <- uniQueList){
      val temp = ind.asInstanceOf[NamedConstraint]
      if(temp.getColumnsNames.contains(cd.getColumnName)){
        var filt = temp.getColumnsNames.filter(w => w != cd.getColumnName)
        if(filt.size >0)
          return (true,filt.last)
        else return (true,"")
      }
    }
    return(false,"")
  }

  def parseStatement(statement: Statement): Entity= statement match {
    case stm: CreateTable => {
      val ctStatement: CreateTable = statement.asInstanceOf[CreateTable]
      val tableName = ctStatement.getTable.toString.replace("`", "")
      var columns: mutable.MutableList[Attribute] = mutable.MutableList()
      var reference: List[Reference] = List.empty[Reference]


      val jIndexes = Option(ctStatement.getIndexes)
      val indexes = if (jIndexes.isDefined) jIndexes.get.toList else List.empty[Index]

      val primaryKey = getPrimaryKey(indexes)
      val uniQueList = stm.getIndexes.filter(f=>f.isInstanceOf[NamedConstraint]).toList
      val refConstList = stm.getIndexes.filter(f=>f.isInstanceOf[ForeignKeyIndex])


      ctStatement.getColumnDefinitions.foreach(cd => {
        val uniProperties = checkUniqProperties(cd,uniQueList)
        var gb = List.empty[String]
        if(cd.getColumnSpecStrings != null)
          gb = gb.++(cd.getColumnSpecStrings)
        columns +=  new Attribute(
           (cd.getColumnName.replaceAll("##_","")),
          cd.getColDataType.toString, primaryKey == (cd.getColumnName),uniProperties._1,uniProperties._2,gb
        )
      })
      reference = reference.++(refConstList.map(r => {
        val foreign = r.asInstanceOf[ForeignKeyIndex]
        val foreignTable = foreign.getTable.getName.replace("`", "")
         new Reference(foreign.getColumnsNames.toList,foreignTable,foreign.getReferencedColumnNames.toList)
      }))
      new Entity(tableName, columns.filter(r=> !toIgnore.contains(r.name)).toList,reference)
    }
    case _ => null
  }

  def getPrimaryKey(indizes: List[Index]): String = {
    for (index <- indizes) {
      if (index.getType == "PRIMARY KEY") {
        return index.getColumnsNames.get(0)
      }
    }

    ""
  }


  /**
    * Extracts all create-statement-strings of an sql string.
    * Skips all other statements.
    *
    * @param content cotains all statements
    * @return a list of create-statement-strings
    */
  def extractStatements(content: String): List[String] = {
//    var statements: mutable.MutableList[String] = mutable.MutableList
//    var sanitizedContent = content.replace("`", "")
//    /**
//     * jSQLParser is not able to handle "primary key [name] ([name])," so the first name occurence got to be removed
//     *
//     * BEGIN - important
//     * do not combine this expressions! one run with replaceAll required per exp, otherwise you'll burn your CPU ;-)
//     */
//    val namedPKMatcher = Pattern.compile(namedPKRE, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE).matcher(sanitizedContent)
//    while (namedPKMatcher.find()) {
//      // 1 := "primary key"; 5: "([name])"
//      sanitizedContent = namedPKMatcher.replaceAll(namedPKMatcher.group(1) + namedPKMatcher.group(5))
//    }
//    val namedPKCommaMatcher = Pattern.compile(namedPKCommaRE, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE).matcher(sanitizedContent)
//    while (namedPKCommaMatcher.find()) {
//      // 1 := "primary key"; 5: "([name]),"
//      sanitizedContent = namedPKCommaMatcher.replaceAll(namedPKCommaMatcher.group(1) + namedPKCommaMatcher.group(5))
//    }
//    /**
//     * END - important
//     */
//
//    val ukeyMatcher = Pattern.compile(ukeyRE, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE).matcher(sanitizedContent)
//    while (ukeyMatcher.find()) {
//      if (!ukeyMatcher.group(5).isEmpty) {
//        sanitizedContent = ukeyMatcher.replaceAll(ukeyMatcher.group(1) + ukeyMatcher.group(5) + ukeyMatcher.group(6))
//      }
//    }
//
//
//    val createMatcher = Pattern.compile(createTableRE, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE).matcher(sanitizedContent)
//    var tableName: String = null
//    var tableContent: String = null
//    var ifNotExists: String = null
//
//    while (createMatcher.find()) {
//      tableName = createMatcher.group(8).trim
//      tableContent = createMatcher.group(0).trim
//      ifNotExists = createMatcher.group(4)
//      if (tableName.contains("\"") == false && tableName.contains("'") == false) {
//        tableContent = tableContent.replace(tableName, s"`$tableName`")
//      }
//
//      statements += tableContent
//    }

    val allStatements = content.split("(?<=;)").map(_.trim)
    val createPattern = Pattern.compile(createTableRE, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.DOTALL)

    val statements = allStatements.filter(s => createPattern.matcher(s).matches())
    statements.toList
  }

  /**
    * Prepare an create-statement to work with JSqlParser.
    *
    * @param statement to prepare
    * @return prepared statement
    */
  def prepareStatement(statement: String) : String = {
    statement.replace("`", "").replace("\"", "'")
      .lines.filter(s => !(s.trim.matches(s"(?i)(unique$tabOrSpaceRE+)?index.*"))).mkString("\n")
  }
}
