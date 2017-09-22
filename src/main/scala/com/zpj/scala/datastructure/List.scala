package com.zpj.scala.datastructure

import org.junit.Test

/**
  * Created by Administrator on 2017/6/21.
  */
@Test
class List {

  @Test
  def testList(): Unit ={
//    val list = List(List("sdf"))
    var list = List(List(12,45,21),List(56,45,222,5658),List(78,381,243))
    list.flatMap(x => x).foreach(println _)
    list.map(x => x).foreach(println _)
    list.foreach(println _)
    println(list.flatMap(x => x)reduce((x:Int,y:Int) => x * y ))

  }
}
