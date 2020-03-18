package com.github.vertical_blank.sqlformatter.scala.core

/**
  * Manages indentation levels.
  * <p>
  * There are two types of indentation levels:
  * <p>
  * - BLOCK_LEVEL : increased by open-parenthesis
  * - TOP_LEVEL : increased by RESERVED_TOPLEVEL words
  */
class Indentation(indent: String) {

  private[this] var indentTypes: List[IndentTypes] = Nil

  /**
    * Returns current indentation string.
    *
    * @return {String}
    */
  def getIndent: String = indentTypes.indices.map(_ => indent).mkString

  /**
    * Increases indentation by one top-level indent.
    */
  def increaseToplevel(): Unit = {
    indentTypes = IndentTypes.INDENT_TYPE_TOP_LEVEL :: indentTypes
  }

  /**
    * Increases indentation by one block-level indent.
    */
  def increaseBlockLevel(): Unit = {
    indentTypes = IndentTypes.INDENT_TYPE_BLOCK_LEVEL :: indentTypes
  }

  /**
    * Decreases indentation by one top-level indent.
    * Does nothing when the previous indent is not top-level.
    */
  def decreaseTopLevel(): Unit = {
    if (indentTypes.headOption.contains(IndentTypes.INDENT_TYPE_TOP_LEVEL))
      indentTypes = indentTypes.tail
  }

  /**
    * Decreases indentation by one block-level indent.
    * If there are top-level indents within the block-level indent,
    * throws away these as well.
    */
  def decreaseBlockLevel(): Unit = {
    indentTypes = indentTypes.dropWhile(_ == IndentTypes.INDENT_TYPE_TOP_LEVEL)
    indentTypes = indentTypes.drop(1)
  }
}

sealed trait IndentTypes extends Product with Serializable
object IndentTypes {
  case object INDENT_TYPE_TOP_LEVEL extends IndentTypes
  case object INDENT_TYPE_BLOCK_LEVEL extends IndentTypes
}
