package com.github.vertical_blank.sqlformatter.scala

trait AbstractSqlFormatter {
  final def format(sql: String): String =
    format(sql, SQLLanguage.StandardSQL, indent = "  ")

  final def format(sql: String, indent: String): String =
    format(sql, SQLLanguage.StandardSQL, indent = indent)

  final def format(sql: String, params: Map[String, Any]): String =
    format(sql, params, SQLLanguage.StandardSQL, indent = "  ")

  final def format(sql: String, params: Seq[Any]): String =
    format(sql, params, SQLLanguage.StandardSQL, indent = "  ")

  def format(sql: String,
             language: SQLLanguage,
             indent: String): String

  def format(sql: String,
             params: Map[String, Any],
             language: SQLLanguage,
             indent: String): String

  def format(sql: String,
             params: Seq[Any],
             language: SQLLanguage ,
             indent: String): String
}
