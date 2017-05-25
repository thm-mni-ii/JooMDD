package de.thm.icampus.mdd

import de.thm.icampus.mdd.model.extensions._
import de.thm.icampus.mdd.model.{Author, EJSLModel, Language, Manifest}

/**
  * Created by tobias on 23.05.17.
  */
object TemplateBuilder {

  def authorPartial(author: Author, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Author "${author.name}" {
         |    authoremail = "${author.email}"
         |    authorurl" = "${author.url}"
         |}""", newline, indent)
  }

  def manifestPartial(manifest: Manifest, newline: Boolean = true, indent: Int = 0) = {
    val authorOpt = ?(true,
      s"""
         |licence = "${manifest.license.license}""""
    )

    toTemplate(
      s"""
         |Manifestation {
         |    authors ${rep(manifest.authors, authorPartial)}
         |    copyright "${manifest.copyright.copyright}"
         |    licence = "${manifest.license.license}"
         |    $authorOpt
         |}""", newline, indent)
  }

  def ejslModel(model: EJSLModel, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |ejslModel "${model.name}" {
         |    datatypes ${rep(model.datatypes, datatypePartial)}
         |    globalparameters ${rep(model.globalParams, paramPartial)}
         |    parametergroup ${rep(model.paramGroups, paramGroupPartial)}
         |    entities ${rep(model.entities, entityPartial)}
         |    pages ${rep(model.pages, pagePartial)}
         |    extensions ${rep(model.extensions, extensionPartial)}
         |}""", newline, indent)
  }

  def datatypePartial(datatype: String, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Datatype $datatype""", newline, indent)
  }

  def paramPartial(param: JParam, newline: Boolean = true, indent: Int = 0) = {
    val definedOpt = ?(param.default.isDefined,
      s"""
         |defaultvalue = "${param.default.get}""""
    )

    val sizeOpt = ?(param.size.isDefined,
      s"""
         |size = "${param.size.get}""""
    )

    toTemplate(
      s"""
         |Parameter ${param.name} {
         |    type = "${param.htmltype}"
         |    $definedOpt
         |    label = "${param.label}"
         |    $sizeOpt
         |    description = "${param.description}"
         |}""", newline, indent)
  }

  def paramGroupPartial(paramGroup: JParamGroup, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Parametergroup ${rep(paramGroup.params,simpleParamPartial, sep="\n", indent=0)}""", newline, indent)
  }

  def simpleParamPartial(param: JParam, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |${param.name}""", newline, indent)
  }

  def entityPartial(entity: JEntity, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Entity ${entity.name} {
         |    attributes ${rep(entity.attributes, attributePartial)}
         |}""", newline, indent)
  }

  def attributePartial(attribute: Attribute, newline: Boolean = true, indent: Int = 0) = {
    val isPrimaryOpt = ?(attribute.isPrimary,
      s"""
         |Primary Attribute""""
    )

    toTemplate(
      s"""
         |Attribute ${attribute.name} {
         |    dbtype = "${attribute.dbtype}"
         |    htmltype = "${attribute.htmltype}"
         |    $isPrimaryOpt
         |}""", newline, indent)
  }

  def pagePartial(page: Page, newline: Boolean = true, indent: Int = 0) = page match {
    case customPage: CustomPage => customPagePartial(customPage, newline, indent)
    case indexPage: IndexPage => indexPagePartial(indexPage, newline, indent)
    case detailsPage: DetailsPage => detailsPagePartial(detailsPage, newline, indent)
  }

  def customPagePartial(customPage: CustomPage, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |CustomPage ${customPage.name} {}""", newline, indent)
  }

  def indexPagePartial(indexPage: IndexPage, newline: Boolean = true, indent: Int = 0) = {
    val paramGroupOpt = ?(indexPage.globalParamNames.nonEmpty,
      s"""
         |*ParameterGroups ${rep(indexPage.globalParamNames, simpleParamGroupPartial)}"""
    )

    toTemplate(
      s"""
         |IndexPage ${indexPage.name} {
         |    *Entities ${indexPage.entity}
         |    $paramGroupOpt
         |}""", newline, indent)
  }

  def detailsPagePartial(detailsPage: DetailsPage, newline: Boolean = true, indent: Int = 0) = {
    val paramGroupOpt = ?(detailsPage.globalParamNames.nonEmpty,
      s"""
         |*ParameterGroups ${rep(detailsPage.globalParamNames, simpleParamGroupPartial)}"""
    )

    toTemplate(
      s"""
         |DetailsPage ${detailsPage.name} {
         |    *Entities ${detailsPage.entity}
         |    $paramGroupOpt
         |}""", newline, indent)
  }

  def simpleParamGroupPartial(paramGroup: String, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |$paramGroup""", newline, indent)
  }

  def languagePartial(language: Language, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Language ${language.name} {
         |    keyvaluepairs ${rep(language.entries, keyValuePairPartial)}
         |}""", newline, indent)
  }

  def keyValuePairPartial(entry: (String, String), newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Key ${entry._1} "${entry._2}"""", newline, indent)
  }

  def componentPartial(component: ComponentExtension, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Component ${component.name} {
         |    ${manifestPartial(component.manifest, newline = false, indent = 1)}
         |    languages ${rep(component.languages, languagePartial)}
         |    sections {
         |        Frontend section {
         |            *Pages ${rep(component.frontend.pages, shortPagePartial, sep="\n", indent=3)}
         |        }
         |        Backend section {
         |            *Pages ${rep(component.backend.pages, shortPagePartial, sep="\n", indent=3)}
         |        }
         |    }
         |}""", newline, indent)
  }

  def shortPagePartial(page: Page, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |*Page : ${page.name}""", newline, indent)
  }

  def pluginPartial(datatype: PluginExtension, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Datatype
         |}""", newline, indent)
  }

  def libraryPartial(datatype: LibraryExtension, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Datatype
         |}""", newline, indent)
  }

  def modulePartial(datatype: ModuleExtension, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Datatype
         |}""", newline, indent)
  }

  def extensionPartial(extension: Extension, newline: Boolean = true, indent: Int = 0) = extension match {
    case component: ComponentExtension => componentPartial(component, newline, indent)
    case module: ModuleExtension => modulePartial(module, newline, indent)
    case library: LibraryExtension => libraryPartial(library, newline, indent)
    case plugin: PluginExtension => pluginPartial(plugin, newline, indent)
  }

  implicit def toIterator[T](iterable: Iterable[T]) :Iterator[T] = iterable.toIterator


  def rep[T](elems: Iterator[T], elemToString: (T, Boolean, Int) => String, indent: Int = 1, sep: String =",\n") : String = {
    val res = if(elems.isEmpty) "{}"
    else {
      val begin = "{"
      val content = elems.map(elemToString(_, true, indent + 1)).mkString(sep)
      val end = tabs(indent) + "}"
      begin + "\n" + content + "\n" + end
    }
    res
  }

  def tabs(amount : Int) : String = {
      (0 until amount).map(_ => "    ").mkString
  }

  val DELETE = "xXxXx"

  def ?(cond: Boolean, out: => String): String = {
    if(cond) out.stripMargin.drop(1) else DELETE
  }

  def toTemplate(input: String, newline: Boolean = true, indent: Int) : String = {
    if(newline) {
      input.stripMargin.lines.drop(1).filter(!_.contains(DELETE)).map(tabs(indent) + _).mkString("\n")
    } else {
      val all = input.stripMargin.lines.drop(1).filter(!_.contains(DELETE))
      val first =
        if(indent > 0) tabs(indent - 1) + all.take(1).next
        else tabs(indent) + all.take(1).next
      val res = first + "\n" + all.map(tabs(indent) + _).mkString("\n")
      res
    }
  }
}
