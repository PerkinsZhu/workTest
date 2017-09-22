package com.zpj.scala.scalaz

import com.zpj.scala.bean.{Jump, Student}
import org.junit.Test


/**
  * Created by Administrator on 2017/6/13.
  */
@Test
class TestScalaZ {


  object StructureClass {
    def showInfo(str:String): Unit={
      println(str)
    }
  }
  @Test
  def testStructure(): Unit ={
    testArgs(StructureClass)
    testWith(ST)
  }

  object  ST extends Student(name = "jack",age =12) {}
  def testWith(arg:Student with Jump): Unit ={
    arg.jump(arg.toString)
  }

//该方法接受的参数必须含有名称为showInfo，参数为String，返回值为Unit的方法。
  def testArgs(arg:{def showInfo(str:String):Unit}): Unit ={
    arg.showInfo(" i am showInfo")
  }
  @Test
  def testDemo(): Unit = {
    println(
//      Apply[Option].apply2(some(1), some(2))((a, b) => a + b),
//      Traverse[List].traverse(List(1, 2, 3))(i => some(i)),
//      NonEmptyList(1, 2, 3),
//        List(List(1)).join,
//      List(true, true).ifM(List(0, 1), List(2, 3))
//      NonEmptyList(1, 2, 3,4).cojoin
    )
  }


  @Test
  def testTrait(): Unit = {
    /**
      * 1、实现一个公共的方法,可以各自进行调用tell方法输出信息
      */
    val color = Color("Red")
    val person = Person("Jack")
    println(ColorTeller.tell(color))
    println(PersonTeller.tell(person))
    /**
      * 这样的实现是通过掉用各自的方法。现在想使用一个公共的方法实现tell的调用
      * 现在添加一个公共方法
      */
    println(tell(person)(PersonTeller))
    println(tell(color)(ColorTeller))
    //    这样通过一个方法就可以对所有的对象进行输出，调用期tell方法
    //    下面使用implicit简化程序。直接添加implicit 字段。这样只有在作用域中具有implicit参数即可实现


  }

  //  该方法可以处理
  def tell[T](t: T)(implicit M: Tellable[T]): String = {
    M.tell(t)
  }
}

object ColorTeller extends Tellable[Color] {
  override def tell(t: Color): String = t.descript
}

object PersonTeller extends Tellable[Person] {
  override def tell(t: Person): String = t.name
}

trait Tellable[T] {
  def tell(t: T): String
}

case class Color(descript: String)

case class Person(name: String)


