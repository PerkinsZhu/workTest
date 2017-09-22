package com.zpj.scala.future

import java.util

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Administrator on 2017/6/2.
  */
class MyFuture {

  def myFuture: Future[String] = Future {
    Thread.sleep(5000); "Hello !!"
  }

  def getInfo(): String = {
//    println(myFuture)
    Thread.sleep(6000)
    myFuture.value.toString
    //  ""
  }
}
