package de.thm.icampus.mdd.model.extensions

class Frontend {


  var pages: Set[Page] = Set.empty[Page]
  var numberOfDynPage:Int =0
  var numberOfCustomPage:Int =0
  var numberOfAllPage:Int =0

  var detailModelPagesPath:Seq[String] = Seq.empty[String]
  var detailTablePagesPath:Seq[String] = Seq.empty[String]
  var detailViewPagesPath:Seq[String] = Seq.empty[String]

  var indexModelPagesPath:Seq[String] = Seq.empty[String]
  var indexViewPath:Seq[String] = Seq.empty[String]
  var indexLayoutPath:Seq[String] = Seq.empty[String]

  var customModelPagesPath:Seq[String] = Seq.empty[String]
  var customViewPagesPath:Seq[String] = Seq.empty[String]
  var eJSLAbilityAmount : Float =0;
  var reEngAbilityAmountDetails: Float = 0;
  var reEngAbilityAmountList: Float = 0;
  var reEngAbilityAmountAll: Float = 0;
  var MDREngAbilitymetric : Float = 0;
  var reEngAbilityAmountModelDetails : Float = 0
  var reEngAbilityAmountTableDetails : Float = 0
  var reEngAbilityAmountViewDetails : Float = 0
  var reEngAbilityAmountIndexModel : Float = 0
  var reEngAbilityAmountIndexView : Float = 0
  var reEngAbilityAmountIndexLayout : Float = 0
  def this (pages: Set[Page]){
    this()
    this.pages = pages
    numberOfAllPage = pages.size
    this.init()
  }
  def init(): Unit =
  {
    this.pages.foreach(t => {
      t match {
        case d : DetailsPage =>{
          numberOfDynPage = numberOfDynPage +1
          detailModelPagesPath = detailModelPagesPath:+(d.modelPath)
          detailViewPagesPath=  detailViewPagesPath:+(d.viewPath)
          if(!d.tablePath.isEmpty)
            detailTablePagesPath = detailTablePagesPath :+(d.tablePath)
        }
        case i :IndexPage =>{
          numberOfDynPage = numberOfDynPage +1
          indexModelPagesPath = indexModelPagesPath :+(i.modelPath)
          indexViewPath = indexViewPath :+(i.viewPath)
          indexLayoutPath = indexLayoutPath :+( i.viewLayoutPath)
        }
        case c: CustomPage =>{
          numberOfCustomPage = numberOfCustomPage +1
          customModelPagesPath = customModelPagesPath :+ (c.modelPath)
          customModelPagesPath = customViewPagesPath:+(c.viewPath)
        }
      }
    })
  }
}
