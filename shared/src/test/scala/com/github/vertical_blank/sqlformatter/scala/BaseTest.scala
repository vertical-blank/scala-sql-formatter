package com.github.vertical_blank.sqlformatter.scala

import org.scalatest.FunSuite

abstract class BaseTest(sqlFormatter: AbstractSqlFormatter) extends FunSuite {

  test("Call SqlFormatter") {
    val formatted = """|SELECT
                       |  *
                       |FROM
                       |  table1""".stripMargin

    assert(sqlFormatter.format("SELECT * FROM table1") == formatted)
  }
}
