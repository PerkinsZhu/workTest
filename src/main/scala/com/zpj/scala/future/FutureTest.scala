package com.zpj.scala.future

import scala.concurrent.Future

/**
  * Created by Administrator on 2017/6/2.
  */
object FutureTest {
  def main(args: Array[String]): Unit = {
    testFuture()
  }

  def testFuture(): Unit = {
    var myFuture = new MyFuture()
    myFuture.getInfo()
  }
}
