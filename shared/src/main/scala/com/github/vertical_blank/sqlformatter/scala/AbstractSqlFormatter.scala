package com.github.vertical_blank.sqlformatter.scala

trait AbstractSqlFormatter {

  def format(sql: String,
             dialect: SqlDialect = SqlDialect.StandardSQL,
             indent: String = "  "): String

  def formatWithNamedParams(sql: String,
             params: Map[String, Any],
             dialect: SqlDialect = SqlDialect.StandardSQL,
             indent: String = "  "): String

  def formatWithIndexedParams(sql: String,
             params: Seq[Any],
             dialect: SqlDialect = SqlDialect.StandardSQL,
             indent: String = "  "): String
}
