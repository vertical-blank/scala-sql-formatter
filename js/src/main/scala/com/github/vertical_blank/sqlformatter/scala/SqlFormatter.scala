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
             config: FormatConfig): String =
    Facade.format(sql, js.Dictionary("language" -> config.dialect.name, "indent" -> config.indent))

  def formatWithNamedParams(sql: String,
             params: Map[String, Any],
             config: FormatConfig): String =
    Facade.format(sql, js.Dictionary("language" -> config.dialect.name, "indent" -> config.indent, "params" -> params.toJSDictionary))

  def formatWithIndexedParams(sql: String,
             params: Seq[Any],
             config: FormatConfig): String =
    Facade.format(sql, js.Dictionary("language" -> config.dialect.name, "indent" -> config.indent, "params" -> params.toJSArray))
}
