package com.github.vertical_blank.sqlformatter.scala

import org.scalatest.FunSuite

abstract class BaseTest(sqlFormatter: AbstractSqlFormatter) extends FunSuite {

  test("simple") {
    val formatted = """|SELECT
                       |  foo,
                       |  bar,
                       |  CASE
                       |    baz
                       |    WHEN 'one' THEN 1
                       |    WHEN 'two' THEN 2
                       |    ELSE 3
                       |  END
                       |FROM
                       |  table""".stripMargin

    assert(sqlFormatter.format("SELECT foo, bar, CASE baz WHEN 'one' THEN 1 WHEN 'two' THEN 2 ELSE 3 END FROM table") == formatted)
  }

  test("withIndent") {
    val formatted = """|SELECT
                       |    foo,
                       |    bar,
                       |    CASE
                       |        baz
                       |        WHEN 'one' THEN 1
                       |        WHEN 'two' THEN 2
                       |        ELSE 3
                       |    END
                       |FROM
                       |    table""".stripMargin

    assert(sqlFormatter
      .format("SELECT foo, bar, CASE baz WHEN 'one' THEN 1 WHEN 'two' THEN 2 ELSE 3 END FROM table", indent = "    ") == formatted)
  }

  test("withNamedParams") {
    val formatted = """|SELECT
                       |  *
                       |FROM
                       |  table
                       |WHERE
                       |  foo = 'bar'""".stripMargin

    assert(sqlFormatter.formatWithNamedParams("SELECT * FROM table WHERE foo = @foo", params = Map("foo" -> "'bar'")) == formatted)
  }

  test("withIndexedParams") {

    val formatted = """|SELECT
                       |  *
                       |FROM
                       |  table
                       |WHERE
                       |  foo = 'bar'""".stripMargin

    assert(sqlFormatter.formatWithIndexedParams("SELECT * FROM table WHERE foo = ?", params = Seq("'bar'")) == formatted)
  }
}
