package com.github.vertical_blank.sqlformatter.scala

import org.scalatest.FunSuite

class Test extends FunSuite {

  test("Call SqlFormatter") {
    val formatted = """|SELECT
                       |  *
                       |FROM
                       |  table1""".stripMargin

    assert(SqlFormatter.format("SELECT * FROM table1") == formatted)
  }

}
