package com.github.vertical_blank.sqlformatter.scala.core

sealed trait Params extends Product with Serializable {

  /**
    * Returns param value that matches given placeholder with param key.
    *
    * @param token token.key Placeholder key
    *              token.value Placeholder value
    * @return param or token.value when params are missing
    */
  def get(token: Token): Any
}

object Params {
  case class NamedParams(params: Map[String, _]) extends Params {
    def get(token: Token): Any =
      token.key.flatMap(params.get).getOrElse(token.value)
  }
  case class IndexedParams(_params: Seq[_]) extends Params {
    private[this] var params = _params
    def get(token: Token): Any = params match {
      case Nil => token.value
      case head +: tail =>
        params = tail
        head
    }
  }

  case object EmptyParams extends Params {
    def get(token: Token): Any = token.value
  }
}
