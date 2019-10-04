package com.github.vertical_blank.sqlformatter.scala

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import js.JSConverters._

@js.native
@JSImport("sql-formatter", JSImport.Namespace)
private[scala] object Facade extends js.Object {
  def format(sql: String, obj: js.Any): String = js.native
}

object SqlFormatter extends AbstractSqlFormatter {
  def format(sql: String,
             language: SQLLanguage,
             indent: String): String =
    Facade.format(sql, js.Dictionary("language" -> language.name, "indent" -> indent))

  def format(sql: String,
             params: Map[String, Any],
             language: SQLLanguage,
             indent: String): String =
    Facade.format(sql, js.Dictionary("language" -> language.name, "indent" -> indent, "params" -> params.toJSDictionary))

  def format(sql: String,
             params: Seq[Any],
             language: SQLLanguage,
             indent: String): String =
    Facade.format(sql, js.Dictionary("language" -> language.name, "indent" -> indent, "params" -> params.toJSArray))
}
