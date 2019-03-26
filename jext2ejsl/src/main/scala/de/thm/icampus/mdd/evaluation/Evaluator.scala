package  de.thm.icampus.mdd.evaluation

import java.io.PrintWriter

import collection.JavaConverters._
import de.thm.icampus.mdd.model.EJSLModel
import de.thm.icampus.mdd.model.extensions.{Backend, ComponentExtension, Frontend}
import de.thm.mni.ii.JooMDDCPD
import com.google.gson.Gson


object Evaluator {
  def evaluationOftheComponentsInModel(eJSLModel: EJSLModel, fileName: String) = {
    var components:List[ComponentExtension] = eJSLModel.extensions.filter(t => t.isInstanceOf[ComponentExtension]).map(f => f.asInstanceOf[ComponentExtension])
    var outPutText : StringBuilder = new StringBuilder();
    components.foreach(cp =>{
      calculateBackdendMetric(cp.backend)
      calculateFrontEndMetric(cp.frontend)
       outPutText.append(
         s"""
            |<h1> ${cp.name} </h1>
            | <ul>
            | <li>reEngAbilityAmount for Details Pages:  ${cp.backend.reEngAbilityAmountDetails.formatted("%3.2f")}%  </li>
            | <li>reEngAbilityAmount for List Pages:   ${cp.backend.reEngAbilityAmountList.formatted("%3.2f")}% </li>
            | <li>reEngAbilityAmount for all:  ${cp.backend.reEngAbilityAmountAll.formatted("%3.2f")}% </li>
            | <li>eJSLAbilityAmount:  ${cp.backend.eJSLAbilityAmount.formatted("%3.2f")}% </li>
            | <li>MDREngAbilitymetric:  ${cp.backend.MDREngAbilitymetric.formatted("%3.2f")}%   </li>
            |</ul></p>
            |<p> <h2> Results for FrontEnd </h2>
            | <ul>
            | <li>reEngAbilityAmount for Details Pages:  ${cp.frontend.reEngAbilityAmountDetails.formatted("%3.2f")}%  </li>
            | <li>reEngAbilityAmount for List Pages:   ${cp.frontend.reEngAbilityAmountList.formatted("%3.2f")}% </li>
            | <li>reEngAbilityAmount for all:  ${cp.frontend.reEngAbilityAmountAll.formatted("%3.2f")}%  </li>
            | <li>eJSLAbilityAmount:  ${cp.frontend.eJSLAbilityAmount.formatted("%3.2f")}%  </li>
            | <li>MDREngAbilitymetric:  ${cp.frontend.MDREngAbilitymetric.formatted("%3.2f")}%   </li>
            |</ul></p>
            |
          """.stripMargin)
    })
    new PrintWriter(fileName.toString) {
      write(outPutText.toString()); close()
    }
  }
  def evaluationOftheComponentsInModelJSON(eJSLModel: EJSLModel, fileName: String) = {
    var components:List[ComponentExtension] = eJSLModel.extensions.filter(t => t.isInstanceOf[ComponentExtension]).map(f => f.asInstanceOf[ComponentExtension])
    var outPutText : StringBuilder = new StringBuilder();
    val gson = new Gson
    components.foreach(cp =>{
      calculateBackdendMetric(cp.backend)
      calculateFrontEndMetric(cp.frontend)
      outPutText.append(
        s"""
           |{
           |	"${cp.name}": {
           |		"backend":
           |       ${gson.toJson(cp.backend)}
           |     ,
           |		"frontend":
           |      ${gson.toJson(cp.frontend)}
           |
           |	}
           |}
          """.stripMargin)
    })
    new PrintWriter(fileName.toString) {
      write(outPutText.toString()); close()
    }
  }
  def calculateBackdendMetric(backenEnd:Backend) = {
    var cpd : JooMDDCPD = new JooMDDCPD()

    var detailsCounter:Int = 0;
    if(backenEnd.detailModelPagesPath.size > 1){
      backenEnd.reEngAbilityAmountModelDetails = cpd.searchFromFilesDuplicationProcent(backenEnd.detailModelPagesPath.asJava,2,16)
      detailsCounter +=1;
    }
    if(backenEnd.detailTablePagesPath.size > 1){
      backenEnd.reEngAbilityAmountTableDetails = cpd.searchFromFilesDuplicationProcent(backenEnd.detailTablePagesPath.asJava,2,16);
      detailsCounter +=1;
    }
    if(backenEnd.detailViewPagesPath.size > 1){
      backenEnd.reEngAbilityAmountViewDetails = cpd.searchFromFilesDuplicationProcent(backenEnd.detailViewPagesPath.asJava,2,16)
      detailsCounter +=1;
    }
    if(detailsCounter != 0)
    backenEnd.reEngAbilityAmountDetails = (backenEnd.reEngAbilityAmountModelDetails + backenEnd.reEngAbilityAmountTableDetails+ backenEnd.reEngAbilityAmountViewDetails)/ detailsCounter

    var indexCounter:Int = 0;
    if(backenEnd.indexModelPagesPath.size > 1){
      backenEnd.reEngAbilityAmountIndexModel = cpd.searchFromFilesDuplicationProcent(backenEnd.indexModelPagesPath.asJava,2,16)
      indexCounter +=1;
    }
    if(backenEnd.indexViewPath.size > 1){
      backenEnd.reEngAbilityAmountIndexView  = cpd.searchFromFilesDuplicationProcent(backenEnd.indexViewPath.asJava,2,16)
      indexCounter +=1;
    }
    if(backenEnd.indexLayoutPath.size > 1){
      backenEnd.reEngAbilityAmountIndexLayout  = cpd.searchFromFilesDuplicationProcent(backenEnd.indexLayoutPath.asJava,2,16)
      indexCounter +=1;
    }
    if(indexCounter > 0)
    backenEnd.reEngAbilityAmountList = ( backenEnd.reEngAbilityAmountIndexModel + backenEnd.reEngAbilityAmountIndexView + backenEnd.reEngAbilityAmountIndexLayout)/ indexCounter

    backenEnd.reEngAbilityAmountAll =  (backenEnd.reEngAbilityAmountDetails + backenEnd.reEngAbilityAmountList)/2
    backenEnd.eJSLAbilityAmount = ( 100* backenEnd.numberOfDynPage) /backenEnd.pages.size
    var partOfeJSL = (backenEnd.eJSLAbilityAmount * 70) / 100
    var partOfReg = (backenEnd.reEngAbilityAmountAll * 30)/100
    backenEnd.MDREngAbilitymetric = partOfeJSL + partOfReg;

  }
  def calculateFrontEndMetric(frontEnd:Frontend) = {
    var cpd : JooMDDCPD = new JooMDDCPD()

    var detailsCounter:Int = 0;
    if(frontEnd.detailModelPagesPath.size > 1){
      frontEnd.reEngAbilityAmountModelDetails = cpd.searchFromFilesDuplicationProcent(frontEnd.detailModelPagesPath.asJava,2,16)
      detailsCounter +=1;
    }
    if(frontEnd.detailTablePagesPath.size > 1){
      frontEnd.reEngAbilityAmountTableDetails = cpd.searchFromFilesDuplicationProcent(frontEnd.detailTablePagesPath.asJava,2,16);
      detailsCounter +=1;
    }
    if(frontEnd.detailViewPagesPath.size > 1){
      frontEnd.reEngAbilityAmountViewDetails = cpd.searchFromFilesDuplicationProcent(frontEnd.detailViewPagesPath.asJava,2,16)
      detailsCounter +=1;
    }
    if(detailsCounter != 0)
      frontEnd.reEngAbilityAmountDetails = (frontEnd.reEngAbilityAmountModelDetails + frontEnd.reEngAbilityAmountTableDetails+ frontEnd.reEngAbilityAmountViewDetails)/ detailsCounter

    var indexCounter:Int = 0;
    if(frontEnd.indexModelPagesPath.size > 1){
      frontEnd.reEngAbilityAmountIndexModel = cpd.searchFromFilesDuplicationProcent(frontEnd.indexModelPagesPath.asJava,2,16)
      indexCounter +=1;
    }
    if(frontEnd.indexViewPath.size > 1){
      frontEnd.reEngAbilityAmountIndexView  = cpd.searchFromFilesDuplicationProcent(frontEnd.indexViewPath.asJava,2,16)
      indexCounter +=1;
    }
    if(frontEnd.indexLayoutPath.size > 1){
      frontEnd.reEngAbilityAmountIndexLayout  = cpd.searchFromFilesDuplicationProcent(frontEnd.indexLayoutPath.asJava,2,16)
      indexCounter +=1;
    }
    if(indexCounter > 0)
      frontEnd.reEngAbilityAmountList = ( frontEnd.reEngAbilityAmountIndexModel + frontEnd.reEngAbilityAmountIndexView + frontEnd.reEngAbilityAmountIndexLayout)/ indexCounter

    frontEnd.reEngAbilityAmountAll =  (frontEnd.reEngAbilityAmountDetails + frontEnd.reEngAbilityAmountList)/2
    frontEnd.eJSLAbilityAmount =( 100* frontEnd.numberOfDynPage) /frontEnd.pages.size
    var partOfeJSL = (frontEnd.eJSLAbilityAmount * 70) / 100
    var partOfReg = (frontEnd.reEngAbilityAmountAll * 30)/100
    frontEnd.MDREngAbilitymetric = partOfeJSL + partOfReg;
  }

}
