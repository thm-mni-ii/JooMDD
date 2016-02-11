package de.thm.icampus.mdd.handler

import java.nio.file.Path

import de.thm.icampus.mdd.model.extensions.PluginExtension
import de.thm.icampus.mdd.model.{Language, Manifest}
import de.thm.icampus.mdd.model.oo.Parameter
import de.thm.icampus.mdd.model.sql.Entity

import scala.io.Codec
import scala.xml.Elem

object PluginHandler extends Handler {
  def handle(extensionRoot: Path, xmlManifest: Elem)(implicit codec: Codec = Codec.UTF8): PluginExtension = {
    val pluginName = xmlManifest \@ "name"
    val manifest: Manifest = Manifest.fromXml(xmlManifest)
    val pluginType = xmlManifest \@ "group"
    val languages: Set[Language] = Language.fromXmlManifest(xmlManifest, extensionRoot)
    val entities = List.empty[Entity]
    val parameters = List.empty[Parameter]

    /**
     * Missing generator for enity relations and parameters -> skipped!
     */


    // run through all install files in manifest end create entities
//    (xmlManifest \\ "install" \\ "sql" \\ "file").foreach(file => {
//      if (file \@ "driver" == "mysql") {
//        entities = entities ::: translate(extensionRoot + file.text)
//      }
//    })

    PluginExtension(pluginName, manifest, pluginType, languages, entities, parameters)
  }

  private def translate(sqlPath: Path): List[Entity] = {
    var entities = List.empty[Entity]

//    var tableName: String = null
//    var dbType: String = null
//    var htmlType: String = null
//    var attribute: Attribute = null
//
//    for (table <- SQLParser.parseFile(sqlPath)) {
//      for (column <- table.columns) {
//        if (column.isprimary == false) {
//          attribute = Attribute(
//            column.name,
//            column.dataType + " " + column.columnSpecs.mkString(" "),
//            null,
//            column.isprimary
//          )
//
//          println(attribute)
//        }
//      }
//    }

    entities
  }
}
