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
