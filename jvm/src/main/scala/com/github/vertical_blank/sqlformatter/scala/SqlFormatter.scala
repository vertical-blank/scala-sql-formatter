package com.github.vertical_blank.sqlformatter.scala

import collection.JavaConverters._
import com.github.vertical_blank.sqlformatter.{SqlFormatter => JSqlFormatter}

object SqlFormatter extends AbstractSqlFormatter {
  def format(sql: String,
             config: FormatConfig): String = JSqlFormatter.of(config.dialect.name).format(sql, config.indent)

  def formatWithNamedParams(sql: String,
             params: Map[String, Any],
             config: FormatConfig): String = JSqlFormatter.of(config.dialect.name).format(sql, config.indent, params.asJava)

  def formatWithIndexedParams(sql: String,
             params: Seq[Any],
             config: FormatConfig): String = JSqlFormatter.of(config.dialect.name).format(sql, config.indent, params.asJava)
}
