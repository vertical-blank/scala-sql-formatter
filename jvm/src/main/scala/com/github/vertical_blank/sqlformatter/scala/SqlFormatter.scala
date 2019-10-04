package com.github.vertical_blank.sqlformatter.scala

import collection.JavaConverters._

object SqlFormatter extends AbstractSqlFormatter {
  def format(sql: String,
             language: SQLLanguage,
             indent: String): String =
    com.github.vertical_blank.sqlformatter.SqlFormatter
      .of(language.name)
      .format(sql, indent)


  def format(sql: String,
             params: Map[String, String],
             language: SQLLanguage,
             indent: String): String =
    com.github.vertical_blank.sqlformatter.SqlFormatter
      .of(language.name)
      .format(sql, indent, params.asJava)

  def format(sql: String,
             params: Seq[String],
             language: SQLLanguage,
             indent: String): String =
    com.github.vertical_blank.sqlformatter.SqlFormatter
      .of(language.name)
      .format(sql, indent, params.asJava)
}
