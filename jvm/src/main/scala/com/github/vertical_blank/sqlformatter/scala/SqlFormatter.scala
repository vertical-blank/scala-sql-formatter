package com.github.vertical_blank.sqlformatter.scala

import collection.JavaConverters._
import com.github.vertical_blank.sqlformatter.{SqlFormatter => JSqlFormatter}

object SqlFormatter extends AbstractSqlFormatter {
  def format(sql: String,
             dialect: SqlDialect,
             indent: String): String = JSqlFormatter.of(dialect.name).format(sql, indent)

  def formatWithNamedParams(sql: String,
             params: Map[String, Any],
             dialect: SqlDialect,
             indent: String): String = JSqlFormatter.of(dialect.name).format(sql, indent, params.asJava)

  def formatWithIndexedParams(sql: String,
             params: Seq[Any],
             dialect: SqlDialect,
             indent: String): String = JSqlFormatter.of(dialect.name).format(sql, indent, params.asJava)
}
