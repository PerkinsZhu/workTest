package com.zpj.scala.bean

/**
  * Created by Administrator on 2017/6/7.
  */
trait Jump{
  def jump(str:String): Unit ={
    println(" i am jump "+str)
  }
}

class Student(var name:String,var age:Int) extends Jump {

  private val studentType = "Student"
  private var _canWriteable = 23
  private[this] val screat = "I am a boy"
//  private[] val basicsName = "I am basic"
  private val privScreat = "yes i am boy"
  var publicParmar = 15
  println("-----this------"+this)
  def canWriteable=_canWriteable
  def canWriteable_(par:Int) ={_canWriteable = par}
  override def toString: String = {
  "toString:--"+this.name+"-"+this.age+"--"+this.screat
  }



}
object Student{
  def showInfo(stu:Student): Unit ={
    println("-----------"+stu.toString)
    println(stu._canWriteable+"---"+stu.studentType+"---"+stu.privScreat)
  }

  def apply(name: String, age: Int): Student = new Student(name, age)

  def apply(age: Int): Int = age

  def unapply(stu: Student): Option[(String, Int,String)] ={
    Some(stu.name,stu.age,stu.studentType)
  }
}
