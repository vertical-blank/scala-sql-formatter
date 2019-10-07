# scala-sql-formatter

[![Build Status](https://travis-ci.org/vertical-blank/scala-sql-formatter.png?branch=master)](https://travis-ci.org/vertical-blank/scala-sql-formatter)

SQL Formatter for Scala.

This is a bridge to these libraries.

- Running on jvm, calls [https://github.com/vertical-blank/sql-formatter](https://github.com/vertical-blank/sql-formatter)
- Running on js, calls [https://github.com/zeroturnaround/sql-formatter](https://github.com/zeroturnaround/sql-formatter)

## Examples

You can easily use `com.github.vertical_blank.sqlformatter.scala.SqlFormatter` :

```scala
SqlFormatter.format("SELECT * FROM table1")
```

This will output:

```sql
SELECT
  *
FROM
  table1
```

### Dialect

You can pass dialect with `FormatConfig` :

```scala
SqlFormatter.format(
  "SELECT *",
  FormatConfig(dialect = SqlDialect.CouchbaseN1QL))
```

Currently just four SQL dialects are supported:

- StandardSQL - [Standard SQL](https://en.wikipedia.org/wiki/SQL:2011)
- CouchbaseN1QL - [Couchbase N1QL](http://www.couchbase.com/n1ql)
- DB2 - [IBM DB2](https://www.ibm.com/analytics/us/en/technology/db2/)
- PLSQL - [Oracle PL/SQL](http://www.oracle.com/technetwork/database/features/plsql/index.html)

### Format

Defaults to two spaces.
You can pass indent string with `FormatConfig` to `format` :

```scala
SqlFormatter.format(
  "SELECT * FROM table1",
  FormatConfig(indent = "    "))
```

This will output:

```sql
SELECT
    *
FROM
    table1
```

### Placeholders replacement

You can pass `Seq` to `formatWithIndexedParams`, or `Map` to `formatWithNamedParams` :

```scala
// Named placeholders
SqlFormatter.formatWithNamedParams("SELECT * FROM tbl WHERE foo = @foo", params = Map("foo" -> "'bar'"))

// Indexed placeholders
SqlFormatter.formatWithIndexedParams("SELECT * FROM tbl WHERE foo = ?", params = Seq("'bar'"))
```

Both result in:

```sql
SELECT
  *
FROM
  tbl
WHERE
  foo = 'bar'
```

Same as the `format` method, `formatWithNamedParams` and `formatWithIndexedParams` accept `dialect` and `indent` parameters.
