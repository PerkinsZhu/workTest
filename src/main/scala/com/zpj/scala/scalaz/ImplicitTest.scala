package com.zpj.scala.scalaz

import com.zpj.scala.bean.Student

/**
  * Created by Administrator on 2017/6/14.
  */
class ImplicitTest {

}

trait Speak{
  def Speaking(info:String):Unit
}
object Speak{
//  import OtherSpeak.tig
//  implicit val dog = new Dog()
//  implicit val tig = new Tig()
}
object OtherSpeak{
  implicit val tig = new Tig()
}
class Dog extends Speak{
  override def Speaking(info: String): Unit = {
    println("--Dog Speaking :"+ info)
  }
}
class Tig extends Speak{
  override def Speaking(info: String): Unit = {
    println("--Tig Speaking :"+ info)
  }
}
object MyRun{
  def main(args: Array[String]): Unit = {
    implicit val dog= new Dog()

    new God().doSpeak("Hello!!!")
  }

}
class God {
  def doSpeak(info:String)(implicit speaker:Speak): Unit ={
    speaker.Speaking(info)
  }
}
object God{
  import OtherSpeak.tig
  //  implicit val dog = new Dog
}

class PackageClass {
  implicit val tig = new Tig()
}

/**
  * 隐式参数查找顺序
  *   1、本类中 = 方法中= 导入到本类/方法中。三者不能同时存在
  *   3、执行类的伴生对象中。该类中导入的隐式变量无效
  *   2、隐式变量类型的伴生对象中
  *   3、导入到隐式变量类型的伴生对象中的隐式变量不起作用，无效。
  *   【汇总】
  *         编译器只会在本类中和隐式变量类型的伴生对象中两个地方查找隐式参数
  *         本类中不能同时存在多个隐式变量，无论是导入的还是自己创建的还是伴生对象中存在的。
  *         伴生对象中也不能同时存在多个隐式参数
  * 在实际生产中，可以把所有需要的隐式参数定义在该隐式参数类型的伴生对象中
  *
  */