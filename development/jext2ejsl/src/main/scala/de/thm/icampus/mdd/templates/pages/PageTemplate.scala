package de.thm.icampus.mdd.templates.pages

import de.thm.icampus.mdd.model.extensions.{CustomPage, DetailsPage, IndexPage, Page}
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait PageTemplate extends BasicTemplate with CustomPageTemplate with IndexPageTemplate with DetailsPageTemplate {

  /**def pagePartial(page: Page, newline: Boolean = true, indent: Int = 0) = page match {
    case customPage: CustomPage => customPagePartial(customPage, newline, indent)
    case indexPage: IndexPage => indexPagePartial(indexPage, newline, indent)
    case detailsPage: DetailsPage => detailsPagePartial(detailsPage, newline, indent)
  }*/

}
