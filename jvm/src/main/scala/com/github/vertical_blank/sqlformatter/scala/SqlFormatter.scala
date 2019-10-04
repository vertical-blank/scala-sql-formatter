package com.github.vertical_blank.sqlformatter.scala

import collection.JavaConverters._
import com.github.vertical_blank.sqlformatter.{SqlFormatter => JSqlFormatter}

object SqlFormatter extends AbstractSqlFormatter {
  def format(sql: String,
             language: SQLLanguage,
             indent: String): String = JSqlFormatter.of(language.name).format(sql, indent)


  def format(sql: String,
             params: Map[String, Any],
             language: SQLLanguage,
             indent: String): String = JSqlFormatter.of(language.name).format(sql, indent, params.asJava)

  def format(sql: String,
             params: Seq[Any],
             language: SQLLanguage,
             indent: String): String = JSqlFormatter.of(language.name).format(sql, indent, params.asJava)
}
