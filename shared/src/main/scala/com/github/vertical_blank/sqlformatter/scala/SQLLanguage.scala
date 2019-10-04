package com.github.vertical_blank.sqlformatter.scala

sealed abstract class SQLLanguage(val name: String) extends Product with Serializable
object SQLLanguage {
  case object StandardSQL extends SQLLanguage("sql")
  case object CouchbaseN1QL extends SQLLanguage("n1ql")
  case object DB2 extends SQLLanguage("db2")
  case object PLSQL extends SQLLanguage("pl/sql")
}
