package com.github.vertical_blank.sqlformatter.scala

trait AbstractSqlFormatter {
  def format(sql: String): String
}
