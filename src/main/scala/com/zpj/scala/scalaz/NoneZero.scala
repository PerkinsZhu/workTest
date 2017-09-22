package com.zpj.scala.scalaz

/**
  * Created by Administrator on 2017/6/13.
  */
trait NoneZero[A] {
  def noneZero(a: A): Boolean
}

object NoneZero  {
  def create[A](f: A => Boolean): NoneZero[A] = new NoneZero[A] {
    override def noneZero(a: A): Boolean = f(a)
  }

//  注意这里定义的是一个隐式常量。【val】
  implicit val initNZInstanced: NoneZero[Int] = create {
    case 0 => false;
    case _ => true;
  }
  implicit val strNZInstanced: NoneZero[String] = create {str =>
    if(str.length > 2) true else false
  }

}
class NoneZeroOps[A](a: A)(implicit ev: NoneZero[A]) {
  def isNonZero: Boolean = ev.noneZero(a)
  def showInfo = println("I AM show Info ")
}

object ToNoneZeroOps {
//  隐式转换，把Int转换为NoneZeroOps[Int]类型
  implicit def toNoneZeroOps[A](a: A)(implicit ev: NoneZero[A]) = new NoneZeroOps[A](a)
}

object MyRunner {
//TODO 详情见：http://www.cnblogs.com/tiger-xc/p/4821819.html#undefined
  def main(args: Array[String]): Unit = {
    import ToNoneZeroOps.toNoneZeroOps  //这个导入的目的主要是导入隐式转换方法的
    println(10.isNonZero)/*这里能够调用NoneZeroOps的方法是因为在ToNoneZeroOps中存在隐式转换方法，直接把Int类型转化为NoneZeroOps[Int]类型*/
    println(0.isNonZero)
    println("hello".isNonZero)
    3.showInfo
  }
}