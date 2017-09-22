package com.zpj.scala.datastructure

import java.util

import org.junit.Test

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created with IntelliJ IDEA.
  * Description: 
  * User: Perkins Zhu
  * Date: 2017-05-29
  * Time: 11:39
  */
class DataStructureTest {

  def testMakString(): Unit ={
    var list = List("")
  }

@Test
  def testMap(): Unit = {
    import scala.collection.mutable.HashMap
    var map01 = new HashMap[String, Int]()
    var map02 = HashMap("jew" -> 234, "sdf" -> 12)
    var map03 = HashMap(("wer", 122), ("weqw", 232))
    println(map01.getOrElseUpdate("hekls", 123))
    map01 += ("sdf" -> 34, "w3erw" -> 234)
    map01("sdf") = 1245
    map01.foreach(println _)
    for(x <- map01.values) println(x + "werwe")

//    排序map
    var sortMap = mutable.SortedMap("sdf"->123,"aswe" -> 23234)
    var sortMap02 = mutable.SortedMap("sdf"->123,"aswe" -> 23234)
    sortMap.foreach(println _)
//    import scala.collection.JavaConverters.propertiesAsScalaMap
//    var map:Map[String,String] = System.getProperties()

//    import scala.collection.JavaConverters.mapAsJavaMap
    var mapwe:Map[String ,Int]= Map("23df"->23)
    getJavaMap(map01)
  }
  def getJavaMap(map:java.util.Map[String,Int]):Unit= {
    println(map.size())
  }
  implicit  def sMapToJMap[K,V](map:mutable.Map[K,V]):java.util.Map[K,V]={
    new util.HashMap[K,V]()
  }

  def testArrayBuffer(): Unit = {
    //  ArrayBuffer 相当于java中的ArrayList
    var temp01 = ArrayBuffer[Int](123, 45, 21, 523, 54)
    var temp02 = new ArrayBuffer[String]
    temp01 += 44
    temp01 += (234, 234, 23, 233, 232345, 234222)
    temp01 ++= Array(234, 234, 256, 567, 89567)
    temp01 trimEnd 12
    temp01 insert(2, 223, 34, 235, 34563, 23)
    //    移除指定位置数据
    temp01 remove 3
    //   从指定位置2开始，移除3个
    temp01.foreach(println _)
    \()
    temp01 remove(2, 3)
    temp01.foreach(println _)
  }

  def testArray(): Unit = {
    //    有小数默认为Double类型
    var tem01 = Array(12, 23, 34, 23, 234.3, 2342)
    //    在浮点数后面加f指定为Float类型
    var tem01_float = Array(12, 23, 34, 23, 234.3f, 2342)
    //    各个类型默认值：
    // String ：null Int：0 Boolean: false Float/Double:0.0
    var tem02 = new Array[Double](5)
    tem01.foreach(println _)
    \()
    tem02(2) = 23.3
    tem02.foreach(println _)
    \()
    for (x <- 0 until tem01.size)
      println(tem01(x))
    \()
    for (x <- 0 to tem01.size - 1)
      println(tem01(x))
    \()
    //    倒序输出
    for (x <- (0 to tem01.size - 1).reverse)
      println(tem01(x))

    var newArray = for (x <- tem01) yield x * 2
    \()
    //    倒序输出
    for (x <- (0 to newArray.size - 1).reverse)
      println(newArray(x))
    \()
    newArray.filter(_ % 2 == 0).map(_ + 1).foreach(println _)
    \()
    newArray.filterNot(_ % 2 == 0).map(_ + 1).foreach(println _)

  }

  def \(info: String = "--------我是一条华丽的分割线---------------") = {
    println(info)
  }
def other(): Unit ={

}
}
