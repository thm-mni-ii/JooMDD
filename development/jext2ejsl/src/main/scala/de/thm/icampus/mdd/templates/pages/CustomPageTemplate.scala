package de.thm.icampus.mdd.templates.pages

import de.thm.icampus.mdd.model.extensions.CustomPage
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait CustomPageTemplate extends BasicTemplate{

  def customPagePartial(customPage: CustomPage, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |CustomPage ${customPage.name} {}""", newline, indent)
  }

}
