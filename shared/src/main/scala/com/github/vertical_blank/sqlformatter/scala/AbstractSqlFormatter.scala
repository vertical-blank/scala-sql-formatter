package com.github.vertical_blank.sqlformatter.scala

trait AbstractSqlFormatter {

  def format(sql: String, config: FormatConfig = FormatConfig()): String

  def formatWithNamedParams(sql: String,
             params: Map[String, Any],
             config: FormatConfig = FormatConfig()): String

  def formatWithIndexedParams(sql: String,
             params: Seq[Any],
             config: FormatConfig = FormatConfig()): String
}
