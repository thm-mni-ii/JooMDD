package de.thm.icampus.mdd.parser

import java.nio.file.Path
import java.util.regex.{Matcher, Pattern}

import de.thm.icampus.mdd.model.oo._

import de.thm.icampus.mdd.implicits.Conversions._

import scala.collection.mutable
import scala.io.Source

/**
 * Parse PHP Files with regular expressions.
 * Can only parse Classes, Methods, Attributes and Types.
 * Bug only spezial formatted files.
 *
 * Should be replaced with a real parser.
 */
object PHPParser2 {
  val default: PHPParser2 = new PHPParser2

  def parseFile(path: Path): List[Struct] = default.parseFile(path)
}

/**
 * Parse PHP Files with regular expressions.
 * Can only parse Classes, Methods, Attributes and Types.
 * Bug: only special formatted files.
 *
 * Should be replaced with a real parser.
 */
class PHPParser2 {
  val tabRE = "[\\t]"
  val spaceRE = "\\s"
  val tabOrSpaceRE = s"($tabRE|$spaceRE)"
  val lineBreakRE = "[\\n\\r]"

  // one of the loose extended lookahead expressions...quite dangerous, but so far no alternative solution found
  val mlCommentRE = "(?s:/\\*(((?!\\*/).)*)?\\*/)"
  val classNameRE = s"(abstract)?$spaceRE*class$spaceRE+([a-zA-Z_][a-zA-Z0-9_]*)"
  val extendsFromRE = s"(extends$spaceRE*([a-zA-Z_][a-zA-Z0-9_]*))"
  val interfacedRE = s"(implements$spaceRE*([a-zA-Z_][a-zA-Z0-9_]*)+[^\\{]*)"

  val methodRE = s"$tabOrSpaceRE*$mlCommentRE$tabOrSpaceRE*(public|private|protected)?$spaceRE+(static$spaceRE+)?function$spaceRE+(\\w+)$spaceRE*([(] [^)]* [)])" // /xms
  val attributeRE = s"$tabOrSpaceRE*$mlCommentRE$tabOrSpaceRE*(public|private|protected)?$spaceRE+(static$spaceRE+)?([$$]+[a-zA-Z_][a-zA-Z0-9_]*)+$tabOrSpaceRE*=" // m
  val atFinderRE = s"$tabOrSpaceRE?\\*$tabOrSpaceRE*@%s$tabOrSpaceRE?(\\w+)?$lineBreakRE?" // i
  val paramFinderRE = s"$tabOrSpaceRE?\\*$tabOrSpaceRE*@param$tabOrSpaceRE+([\\S]+)$tabOrSpaceRE+(&?[$$]+[a-zA-Z_][a-zA-Z0-9_]*)?" // i
  val singleParamRE = s"^\\((&?[$$]+[a-zA-Z_][a-zA-Z0-9_]*).*\\)$$" // i
  /**
   * class and interface recognition is limited by the closing bracket. to avoid bracket counting, we only accept classes and interfaces following this criteria:
   * - definition has to start at the beginning of the line!
   * - definition has to end with a closing bracket at the beginning of the line!
   * So:
   * class foo {
   * }
   *
   * will be recognized, while:
   * class foo {}
   * won't.
   */
  val classRE = s"$mlCommentRE*$lineBreakRE?$classNameRE$spaceRE*$extendsFromRE?$spaceRE?$interfacedRE?$spaceRE?$lineBreakRE?\\{(.*?)^\\}" // xms
  val interfaceRE = s"$mlCommentRE?$lineBreakRE?interface$spaceRE+([a-zA-Z_][a-zA-Z0-9_]*)$spaceRE*$interfacedRE?$spaceRE?$lineBreakRE?\\{(.*?)^\\}" // xms

  /**
   * used to establish "readable" group access for the matcher
   */
  val indices = Map[String, Int](
    "class_comment"   -> 1,
    "class_abstract"  -> 3,
    "class_name"      -> 4,
    "class_extends"   -> 6,
    "class_interface" -> 7,
    "class_content"   -> 9,

    "interface_comment"   -> 1,
    "interface_name"      -> 3,
    "interface_interface" -> 4,
    "interface_content"   -> 6,

    "attribute_comment"         -> 2,
    "attribute_access_modifier" -> 5,
    "attribute_name"            -> 6,

    "method_comment"         -> 2,
    "method_access_modifier" -> 5,
    "method_name"            -> 7,
    "method_params"          -> 8,

    "parameter_single_name"  -> 1,
    "parameter_type"         -> 4,
    "parameter_name"         -> 6,

    "at_match" -> 4
  )

  /**
   * parse the given file and delivers a List of structs containing all found classes and interfaces including theire attributes, comment, ...
   *
   * @param   path
   * @return  List[Struct]
   */
  def parseFile(path: Path): List[Struct] = {
    var structs: mutable.MutableList[Struct] = mutable.MutableList()
    val source: String = Source.fromFile(path).mkString

    /**
     * to debug the matcher groups use: MatcherDebug.printGroups([matcher])
     * - be aware of the consuming ability of the debugger...no further matches left after the iteration
     */
    val classMatcher = Pattern.compile(classRE, Pattern.COMMENTS|Pattern.DOTALL|Pattern.MULTILINE).matcher(source)
    while (classMatcher.find()) {
      var attributes = mutable.MutableList[Attribute]()
      var methods = mutable.MutableList[Method]()
      // files with more longer than 50k leads to stack overflow, so here we just reduce the content size
      val content = classMatcher.group(indices.get("class_content").get).replaceAll("=[^;]*;", "=")
      // get attributes
      val attributeMatcher = Pattern.compile(attributeRE, Pattern.MULTILINE).matcher(content)
      while (attributeMatcher.find()) {
        attributes += createAttribute(attributeMatcher)
      }
      // get methods
      val methodMatcher = Pattern.compile(methodRE, Pattern.COMMENTS|Pattern.DOTALL|Pattern.MULTILINE).matcher(content)
      while (methodMatcher.find()) {
        methods += createMethod(methodMatcher)
      }

      structs += Clazz(
        classMatcher.group(indices.get("class_name").get),
        Option(classMatcher.group(indices.get("class_extends").get)),
        isGenerated(classMatcher, indices.get("class_comment").get),
        createInterfaces(classMatcher.group(indices.get("class_interface").get)),
        attributes.toList,
        methods.toList
      )
    }

    val interfaceMatcher = Pattern.compile(interfaceRE, Pattern.COMMENTS|Pattern.DOTALL|Pattern.MULTILINE).matcher(source)
    while (interfaceMatcher.find()) {
      var methods = mutable.MutableList[Method]()
      // get methods
      val methodMatcher = Pattern.compile(methodRE, Pattern.COMMENTS|Pattern.DOTALL|Pattern.MULTILINE).matcher(interfaceMatcher.group(indices.get("interface_content").get))
      while (methodMatcher.find()) {
        methods += createMethod(methodMatcher)
      }

      structs += Interface(
        interfaceMatcher.group(indices.get("interface_name").get),
        isGenerated(interfaceMatcher, indices.get("interface_comment").get),
        createInterfaces(interfaceMatcher.group(indices.get("interface_interface").get)),
        methods.toList
      )
    }

    structs.toList
  }

  def createAttribute(matcher: Matcher): Attribute = {
    val varMatcher = Pattern.compile(atFinderRE.format("var"), Pattern.CASE_INSENSITIVE).matcher(matcher.group(indices.get("attribute_comment").get))
    var dataType = "<unknown>"
    if (varMatcher.find()) {
      dataType = varMatcher.group(indices.get("at_match").get)
    }

    Attribute(
      matcher.group(indices.get("attribute_name").get),
      dataType,
      matcher.group(indices.get("attribute_access_modifier").get)
    )
  }


  def createMethod(matcher: Matcher): Method = {
    var returnType: String = "<unknown>"
    var params = mutable.MutableList[Parameter]()
    val paramMatcher = Pattern.compile(paramFinderRE, Pattern.CASE_INSENSITIVE).matcher(matcher.group(indices.get("method_comment").get))
    while (paramMatcher.find()) {
      params += createParameter(paramMatcher, matcher.group(indices.get("method_params").get))
    }

    val returnTypeMatcher = Pattern.compile(atFinderRE.format("return"), Pattern.CASE_INSENSITIVE).matcher(matcher.group(indices.get("method_comment").get))
    if (returnTypeMatcher.find()) {
      returnType = returnTypeMatcher.group(indices.get("at_match").get)
    }

    Method(
      matcher.group(indices.get("method_name").get),
      matcher.group(indices.get("method_access_modifier").get),
      Some(returnType),
      isGenerated(matcher, indices.get("method_comment").get),
      params.toList
    )
  }

  def createParameter(matcher: Matcher, params: String): Parameter = {
    var name = matcher.group(indices.get("parameter_name").get)
    if (name == null) { // if name is empty, there is only one param!
      val singleParamMatcher = Pattern.compile(singleParamRE).matcher(params)
      if (singleParamMatcher.find()) {
        name = singleParamMatcher.group(indices.get("parameter_single_name").get)
      }
    }

    Parameter(
      name,
      matcher.group(indices.get("parameter_type").get)
    )
  }

  /**
   * extracts the names of interfaces in a class or interface definition
   * - e.g.: class foo implements bar, foobar {
   * } -> " implements bar, foobar" is the input string
   *
   * @param interfaceString
   * @return
   */
  def createInterfaces(interfaceString: String): List[Interface] = interfaceString match {
    case null => List()
    case _ => {
      val _interfaces = mutable.MutableList[Interface]()
      val parts = interfaceString.replace("implements", "").split(",")
      for (part <- parts) {
        if (!part.isEmpty) {
          _interfaces += Interface(part.trim)
        }
      }

      _interfaces.toList
    }
  }

  def isGenerated(matcher: Matcher, commentGroup: Int): Boolean = {
    var isGenerated = false
    if (matcher.group(commentGroup) != null) {
      isGenerated = Pattern.compile(atFinderRE.format("generated")).matcher(matcher.group(commentGroup)).find()
    }

    isGenerated
  }
}
