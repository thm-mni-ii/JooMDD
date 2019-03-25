package de.thm.icampus.mdd.generator.extensions

import de.thm.icampus.mdd.model.extensions.ModuleExtension
import de.thm.icampus.mdd.generator.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait ModuleTemplate extends BasicTemplate with ManifestTemplate with LanguageTemplate {

  def modulePartial(module: ModuleExtension, newline: Boolean = true, indent: Int = 0) = {
    val pageOpt = ?(module.page.nonEmpty,
      s"""
         |*Page ${module.page}"""
    )

    toTemplate(
      s"""
         |Module ${module.name}
         |    ${manifestPartial(module.manifest, newline = false, indent = 1)}
         |    languages ${rep(module.languages, languagePartial)}
         |    $pageOpt
         |}""", newline, indent)
  }

}
