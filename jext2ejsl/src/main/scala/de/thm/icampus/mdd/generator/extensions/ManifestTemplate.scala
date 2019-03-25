package de.thm.icampus.mdd.generator.extensions

import de.thm.icampus.mdd.model.{Author, Manifest}
import de.thm.icampus.mdd.generator.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait ManifestTemplate extends BasicTemplate {

  def manifestPartial(manifest: Manifest, newline: Boolean = true, indent: Int = 0) : String = {
    val authorOpt = ?(true,
      s"""
         |license = "${manifest.license.license}""""
    )

    toTemplate(
      s"""
         |Manifestation {
         |    authors ${rep(manifest.authors, authorPartial)}
         |    copyright = "${manifest.copyright.copyright}"
         |    $authorOpt
         |}""", newline, indent)
  }

  private def authorPartial(author: Author, newline: Boolean = true, indent: Int = 0) : String = {
    toTemplate(
      s"""
         |Author "${author.name}" {
         |    authoremail = "${author.email}"
         |    authorurl = "${author.url}"
         |}""", newline, indent)
  }

}
