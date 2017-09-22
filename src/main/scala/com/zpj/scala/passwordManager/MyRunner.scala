package com.zpj.scala.passwordManager

import java.io._

/**
  * Created by Administrator on 2017/7/7.
  */
object MyRunner {
  val file = new File("");
  val output = new ObjectOutputStream(new FileOutputStream(file))
  val input = new ObjectInputStream(new FileInputStream(file))
  def main(args: Array[String]): Unit = {
    val counts = input.readObject().asInstanceOf[List[Count]]
    counts.foreach(count => println(count.id+" : "+count.name+" : "+count.info))
  }
  case class Count(id:Int,name:String,loginName:String,loginPass:String,url:String,info:String)extends Serializable;
}
