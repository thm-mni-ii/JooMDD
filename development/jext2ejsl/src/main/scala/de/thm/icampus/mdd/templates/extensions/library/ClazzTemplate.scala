package de.thm.icampus.mdd.templates.extensions.library

import de.thm.icampus.mdd.model.oo.{Clazz, Method, Parameter}
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait ClazzTemplate extends BasicTemplate {

  def clazzPartial(clazz: Clazz, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Clazz ${clazz.name} {
         |    methods ${rep(clazz.methods, methodPartial)}
         |}""", newline, indent)
  }

  private def methodPartial(method: Method, newline: Boolean = true, indent: Int = 0) = {
    val returnValueOpt = ?(method.returnType.isDefined,
      s"""
         |Returnvalue result:${method.returnType.get}"""
    )

    val paramsOpt = ?(method.params.nonEmpty,
      s"""
         |methodparameters ${rep(method.params, methodParamPartial, sep="\n")}"""
    )

    toTemplate(
      s"""
         |Method ${method.name} {
         |   $returnValueOpt
         |   $paramsOpt
         |}""", newline, indent)
  }

  private def methodParamPartial(param: Parameter, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |MethodParameter ${param.name}:${param.dataType}""", newline, indent)
  }

}
