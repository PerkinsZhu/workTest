package com.zpj.scala.collectionConvert

import scala.collection.mutable.ListBuffer
import java.util.{List => JList}
import scala.collection.immutable.{List => SList}

/**
  * Created by Administrator on 2017/6/29.
  */
object JTS {
  implicit def doList[T](list: JList[T]): SList[T] = {
    var listBuffer = new ListBuffer[T]()
    list.forEach(x => listBuffer += x)
    listBuffer.toList
  }
}
