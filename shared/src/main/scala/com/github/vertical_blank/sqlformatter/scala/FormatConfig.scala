package com.github.vertical_blank.sqlformatter.scala

case class FormatConfig(
  dialect: SqlDialect = SqlDialect.StandardSQL,
  indent: String = "  ")
