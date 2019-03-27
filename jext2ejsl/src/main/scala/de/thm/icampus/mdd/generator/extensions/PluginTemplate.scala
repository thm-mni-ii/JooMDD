package de.thm.icampus.mdd.generator.extensions

import de.thm.icampus.mdd.model.extensions.PluginExtension
import de.thm.icampus.mdd.generator.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait PluginTemplate extends BasicTemplate with ManifestTemplate with LanguageTemplate {

  def pluginPartial(plugin: PluginExtension, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Plugin ${plugin.name}
         |    ${manifestPartial(plugin.manifest, newline = false, indent = 1)}
         |    languages ${rep(plugin.languages, languagePartial)}
         |    pluginType = ${plugin.pluginType}
         |}""", newline, indent)
  }

}
