package de.thm.icampus.mdd.parser

import java.io.StringReader
import java.nio.file.Path
import java.util.regex.Pattern

import de.thm.icampus.mdd.implicits.Conversions._
import de.thm.icampus.mdd.implicits.OptionUtils._
import de.thm.icampus.mdd.model.sql.{Column, Table}
import net.sf.jsqlparser.JSQLParserException
import net.sf.jsqlparser.parser.CCJSqlParserManager
import net.sf.jsqlparser.statement.Statement
import net.sf.jsqlparser.statement.create.table.{Index, CreateTable}

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.io.Source

/**
 * Parse SQL files.
 */
object SQLParser {
  val default: SQLParser = new SQLParser

  def parseFile(path: Path): List[Table] = default.parseFile(path)
}

/**
 * Parse "create table" statement in SQL files
 *
 * please check https://github.com/JSQLParser/JSqlParser for full parser documentation
 */
class SQLParser {
  val lineBreakRE = "[\\n\\r]"
  val tabOrSpaceRE = "([\\t]|\\s)"
  // used to remove the "...if not exists... "
  val createTableRE = s"$tabOrSpaceRE?create$tabOrSpaceRE+table$tabOrSpaceRE+(if$tabOrSpaceRE+not$tabOrSpaceRE+exists$tabOrSpaceRE+)?([^\\(]*)$tabOrSpaceRE?\\([^;]*;$$"
  // important! do not combine this expressions! one run with replaceAll required per exp, otherwise you'll burn your CPU ;-)
  val namedPKRE =      s"($tabOrSpaceRE?primary$tabOrSpaceRE+key$tabOrSpaceRE+)[^\\(]*(\\([^\\)]*\\))$$"
  val namedPKCommaRE = s"($tabOrSpaceRE?primary$tabOrSpaceRE+key$tabOrSpaceRE+)[^\\(]*(\\([^\\)]*\\),)$$"
  // "unique key [name] ([column name(-s)])" has to be convert into "unique [name] ([column name(-s)])"
  val ukeyRE = s"($tabOrSpaceRE?unique$tabOrSpaceRE+)key$tabOrSpaceRE+([^\\(]*)(\\([^\\)]*\\))"
  val tableNameIndex = 8

  def parseFile(path: Path): List[Table] = {
    var tables: mutable.MutableList[Table] = mutable.MutableList()
    val sqlAsString = Source.fromFile(path).mkString
    val sqlAsStringWithoutJoomlaPlaceholder = sqlAsString.replaceAll("#__", "")

    val statements: List[String] = extractStatements(sqlAsStringWithoutJoomlaPlaceholder)
    val parser: CCJSqlParserManager = new CCJSqlParserManager

    for (statementString <- statements) {
      try {
        parseStatement(parser.parse(new StringReader(statementString))) ?=> (table => tables += table)
      } catch {
        case e: JSQLParserException => {
          println(statementString)
          println("-----------------------------------------")
          println(s"Ignoring table definition in file: $path due to malformed statement:")
          println("JSQLParserException caught: " + e.getCause)
          println("-----------------------------------------")
        }
      }
    }

    tables.toList
  }

  def parseStatement(statement: Statement): Option[Table] = statement match {
    case stm: CreateTable => {
      val ctStatement: CreateTable = statement.asInstanceOf[CreateTable]
      val tableName = ctStatement.getTable.toString.replace("`", "")
      var columns: mutable.MutableList[Column] = mutable.MutableList()


      val jIndexes = Option(ctStatement.getIndexes)
      val indexes = if (jIndexes.isDefined) jIndexes.get.toList else List.empty[Index]

      val primaryKey = getPrimaryKey(indexes)

      ctStatement.getColumnDefinitions.foreach(cd => {
        columns += Column(
          cd.getColumnName,
          cd.getColDataType.toString,
          cd.getColumnSpecStrings.toList,
          primaryKey != None && primaryKey.get == cd.getColumnName
        )
      })

      Some(Table(tableName, columns.toList))
    }
    case _ => None
  }

  def getPrimaryKey(indizes: List[Index]): Option[String] = {
    for (index <- indizes) {
      if (index.getType == "PRIMARY KEY") {
        return Option(index.getColumnsNames.get(0))
      }
    }

    None
  }

  def extractStatements(content: String): List[String] = {
    var statements: mutable.MutableList[String] = mutable.MutableList()
    var sanitizedContent = content.replace("`", "")
    /**
     * jSQLParser is not able to handle "primary key [name] ([name])," so the first name occurence got to be removed
     *
     * BEGIN - important
     * do not combine this expressions! one run with replaceAll required per exp, otherwise you'll burn your CPU ;-)
     */
    val namedPKMatcher = Pattern.compile(namedPKRE, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE).matcher(sanitizedContent)
    while (namedPKMatcher.find()) {
      // 1 := "primary key"; 5: "([name])"
      sanitizedContent = namedPKMatcher.replaceAll(namedPKMatcher.group(1) + namedPKMatcher.group(5))
    }
    val namedPKCommaMatcher = Pattern.compile(namedPKCommaRE, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE).matcher(sanitizedContent)
    while (namedPKCommaMatcher.find()) {
      // 1 := "primary key"; 5: "([name]),"
      sanitizedContent = namedPKCommaMatcher.replaceAll(namedPKCommaMatcher.group(1) + namedPKCommaMatcher.group(5))
    }
    /**
     * END - important
     */

    val ukeyMatcher = Pattern.compile(ukeyRE, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE).matcher(sanitizedContent)
    while (ukeyMatcher.find()) {
      if (!ukeyMatcher.group(5).isEmpty) {
        sanitizedContent = ukeyMatcher.replaceAll(ukeyMatcher.group(1) + ukeyMatcher.group(5) + ukeyMatcher.group(6))
      }
    }

    // crap...
    sanitizedContent = sanitizedContent.replace("interval", "`interval`")

    val createMatcher = Pattern.compile(createTableRE, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE).matcher(sanitizedContent)
    var tableName: String = null
    var tableContent: String = null
    var ifNotExists: String = null

    while (createMatcher.find()) {
      tableName = createMatcher.group(8).trim
      tableContent = createMatcher.group(0).trim
      ifNotExists = createMatcher.group(4)
      if (tableName.contains("\"") == false && tableName.contains("'") == false) {
        tableContent = tableContent.replace(tableName, s"`$tableName`")
      }
      if (ifNotExists != null) { // JSQLParser is not able to handle "create table if not exists...." statements...
        tableContent = tableContent.replace(ifNotExists, "")
      }

      statements += tableContent
    }

    statements.toList
  }
}
