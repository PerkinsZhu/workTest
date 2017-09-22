package com.zpj.scala

import java.io.File
import java.net.URI

import org.apache.tools.ant.taskdefs.Tstamp
import org.junit.Test

import scala.collection.immutable.HashMap
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.concurrent.Promise
import scala.io.{Codec, Source}
import scala.math.Ordering
import scala.util.matching.Regex
import scala.util.{Failure, Success, Try}
import java.io.{File => jFile}
import java.util

import scala.reflect.io.{File => sFile}

/**
  * Created by Administrator on 2017/6/1.
  */

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import com.zpj.scala.bean.Student
/**
  * scala基础知识学习
  */
class Basics {



  @Test
  def testJList(): Unit = {
    import collection.convert.ImplicitConversions
    val list = new java.util.ArrayList[Int]()
    //  list.synchronized()
    for (x <- 1 to 10) {
      list.add(x)
    }
    list.forEach(x => println(x))
    import com.zpj.scala.collectionConvert.JTS.doList
    showList(list)
  }

  def showList(list: List[Int]): Unit = {
    println(list.filter(_ % 2 == 0).foldLeft(100)((x, y) => x + y))
  }

  @Test
  def testFile(): Unit ={
    //如果文件中有中文的话， 编码将会出错。
    import Codec.charset2codec
//    implicit   val dec = Codec.UTF8
    val source = Source.fromFile("E:\\zhupingjing\\test\\123.txt")
    source.getLines().foreach(x => println(x.getBytes("UTF-8").toString))

    import scala.reflect.io.Directory
    val dir = new jFile("E:\\zhupingjing\\test\\")
    dir.listFiles().filter(_.isDirectory).foreach(x => println (x.getAbsoluteFile))
//    implicit code = new Codec("UTF-8")
    println("==================操作 scala file========================")
    val file = sFile(new jFile("E:\\zhupingjing\\test\\1234.txt"))
      file.writeAll(List.range(1,100).mkString("、"))
    for (elem <- List.range(1, 10000)) {
      file.outputStream(true).write(elem.toString.getBytes("UTF-8"))
    }
    val dirFile = sFile("E:\\zhupingjing\\test\\huikh\\")
    dirFile.toDirectory.deepFiles.foreach(println _)

  }

  @Test
  def testList(): Unit = {
    val list = List(0, 34, 45, 4345, 345, 345)
    println(
      list.forall(_ % 2 == 0),
      list.exists(_ % 2 == 0),
      sum(list:_*)


    )
  }


  @Test
  def testEcho(): Unit ={
    val list = List(12,23,23,23)
    echo(list  : _*)// 把list中的每一项都逐个取出来当做参数传递给函数， 这里面实际执行的是 echo(12,23,23,23）
  }
def echo(num:Int*): Unit ={
  println("--接受到参数---"+num)
  for(item <- num ) println( item )
}

  @Test
  def testRequuire(): Unit ={
    val num = 10
//    require(num != 10)//验证失败将会抛出异常
    require(num != 1)
    println("验证通过！！")
  }

  @Test
  def testVal(): Unit = {
     val stus =  for(x <- 1 to 1000 ) new Student("jack",x)
    println(stus)
  }

@Test
  def testShell(): Unit ={
  import sys.process._
//  "ls -al .." #| "grep Admin" #> new File("files.txt")!
//    "top"!
  "scala -version" !
}

  @Test
  def testSources(): Unit ={
    val json = Source.fromURL("http://www.baidu.com","UTF-8").mkString
    println(json)
  }

  @Test
  def testAnonyMousClass(): Unit ={

  }
  @Test
  def testPrivateThis(): Unit ={
    /**
      * private[this] 则该字段只能在本类中被访问，即使伴生对象也不能访问private[this] 字段。
      * 但是在伴生对象中可以访问伴生类中的private字段。
      */
    var stu = new Student("Jack",23)
    println(stu)
//    println(stu.basicsName)
    Student.showInfo(stu)

  }
  @ Test
  def testArray(): Unit = {
    val array = new Array[String](10)
    array.map(print(_))
    println("-----------------")
    var buf = ArrayBuffer[String]()
    buf += "Hello"
    buf ++= Array("qizhi", "ShangHai")
    buf += ("12", "234", "453", "322")
    println(buf.toString())

    var intBuf = ArrayBuffer(12, 34, 4, 54, 34, 34, 345, 4, 3, 43, 456, 989)
    println(intBuf.max, intBuf.min)
    println(intBuf.sortBy(x => (-x)))
    println(intBuf.sortWith(_ < _))
    println(intBuf.sorted)
    //TODO 这里的val 或者是 var定义的intArr不会产生影响吗啊？ 快排是对原数组进行的操作。
    val intArr = Array(12, 34, 4, 54, 34, 34, 345, 4, 3, 43, 456, 989)
    System.out.println("--befor---" + intArr)
    scala.util.Sorting.quickSort(intArr)
    System.out.println("--after---" + intArr)
    intArr.foreach(print(_, "、"))
    println("============")
    val arry = Array(12, 45)
    arry(0) = 15
    arry.foreach(println _)
    /**
      *TODO  val 定义的常量是什么不可变？ 长度还是内容数值？？？？
      *  val定义的常量表示该常量所指向的物理地址不可变，但该物理地址的指向其它的引用可以改变。
      *  例如 val array = Array[Studnet](jack,tom,abel)
      *   则 array这个常量的地址[I@2db7a79b不可改变，但是 array(0)指向的Jack可以改变为tom。
      **/
    //    array = Array(154,45,12)
    val array02 = Array(15, 12, 3231)
    //    array = array02
    val jack = new Student("jack", 23)
    val tom = new Student("tom", 33)
    val arr03 = Array(jack)
         arr03(0) = tom
    println(arr03(0))
//    注意，如下测试HashMap使用的是可变HashMap mutable.HashMap.
    val abel = new Student("abel",23)
    var map = mutable.HashMap("jack" -> jack,"tom" -> tom,"abel" ->abel)
    val muMap = mutable.HashMap("jack" -> jack,"tom" -> tom)
//    muMap = map   //val 变量的指向地址不允许改变
    println(map.hashCode(),muMap)
//    val定义的常量内容可以改变。
    muMap("jack") = abel
    println(map.hashCode(),muMap)


  }

  @Test
  def testEither(): Unit ={
    println(Left(456).isRight)
    println(Right(null).value)
  }
  @Test
  def testMapAndFlatMap(){
    /**
      * flatMap 主要就是循环的时候把集合层次去掉一层，
      */
    //    var list = List(List(List(12,45,21),List(56,45,222,5658)),List(List(78,381,243)))
    var list = List(List(12,45,21),List(56,45,222,5658),List(78,381,243))
    println(list.map(x => x))
    println(list.flatMap(x =>  x ))
    println("-------list 操作测试-------")
    var list02 = List(12,45,12,45)
    println("----+:---")
    println(list02 +: List(23))
    println(List(23)+:list02)
    println("----:+---")
    println(list02 :+ List(23))
    println(List(23) :+ list02)
    println("----++---")
    println(list02 ++ List(152))
    println( List(152) ++ list02 )
    println("----:: 类似于 +: ---")
    println(list02 :: List(111))
    println(List(111):: list02 )

    println("-------fold----------")
    val list03 = List(10,10,10,10,10)
    println(list03.fold(100){(x,y) => x + y})
    println()
  }
  @Test
  def testTryAndOption(): Unit = {
    var temp01 = strToInt("41")
    var temp02 = strToInt("41e")
    println(temp01 + "-----" + temp01.getClass)
    println(temp02 + "-----" + temp02.getClass)
    var list = List("sdf", "sdf", "23", "2321")
    //    println(list.map(x => x.toInt))
    println(list.flatten)
    println(divideBy(45, 0).getOrElse(23))

    val vec = Vector(List())
    println(vec)
    println(vec.isEmpty)
    println(vec(0))
    println("------测试 Option---------")
    /**
      * 只有None 的getOrElse返回值才会被使用，其它的Option 和Some的都使用不上设置的default值。
      * 因为getOrElse()方法在三者之间的实现方式都相同：
      *      if (isEmpty) default else this.get
      *  不同的是
      *     some.isEmpty = false
      *     option.isEmpty = false
      *     none.isEmpty = true
      *  ----------------------------------------
      *  Option是 Abstract类
      *     Some和None是他的两个子类，且只有这两个。
      * Option是一个抽象类，所以不能去new Option(),只能使用其伴生对象进行创建。
      * 实际使用过程中，一般只用Optionp[T]去表示方法的返回值类型，而不需要生成Option的对象，只对其子类型Some 和 None 进行实例化
      * */
    var opt= Option()
    println(opt.isEmpty+"----"+opt.getOrElse(12))
    var some = Some(20)
    println(some.isEmpty+"----"+some.getOrElse())
    var none = None
    println(none.isEmpty+"----"+none.getOrElse(12))
//    使用Option

   testUseOption("Hello").getOrElse(Array('s','d','d')).foreach(print(_))
    /**
      *这里如果传入参数为null的时候，Option(null)会自动返回一None值，因此，可以使用Option()来接收未知结果的Java返回值。
      * */
    println(Option(null))

    println(Option("Hello").map(_ + "---"))

    val tryTag= Try(new RuntimeException("RunTime"))
  }

  def testUseOption(str: String): Option[Array[Char]] = {
    if (str.length > 0) Some(str.toCharArray) else None
  }
  def divideBy(x: Int, y: Int): Try[Int] = {
    Try(x / y)
  }
  def strToInt(str: String): Option[Int] = {
    try {
      Some(str.toInt)
    } catch {
      //      在这里发生异常的时候依旧需要手动转化为None,只是在调用处不用在担心存在 隐式异常。直接判断是否为None即可
      case e: Exception => None
    }
  }
  @Test
  def testException(): Unit ={
    println("------befor------")
    try{
      println(getAnException().getOrElse(45))
    }catch {
      case ex:RuntimeException => println(ex)
      case _:Runnable=> println("----")
    }finally {
      println("----game over --------")
    }
    println("------after------")
  }
  def getAnException(): Try[Int] ={
    println("____befor__")
    throw new RuntimeException("my Exception")
//    println("___end___")
  }

  @Test
  def testDynamicParameter(): Unit = {
    println(sum(12, 45, 132, 12, 54, 4545))
    println(getInfoFromUnit())
  }

  private def sum(num: Int*): Int = {
    println(num.getClass)
    println(num)
//    num.toList.foldLeft((x,y)=> x+y)
    num.toList.sum
  }

  //这里返回结果为()Unit，由此可以看出，方法定义的的返回值类型的优先级最高的，即使方法体中明确定义的有返回值，该返回值也不回被接受的。
  def getInfoFromUnit(){
    return 35
  }


  @Test
  def testYield(): Unit = {

    var intYield = for (i <- 1 to 100) yield i + 1
    var strYield = for (i <- List("sdf", "sdef", "w34edf", "sdfs")) yield i + "***"
    var boolYield = for (i <- List(true,true,false)) yield i + "***"
    println(intYield.getClass)
    println(strYield.getClass)
    println(boolYield.getClass)
    /*intYield.foreach(println _)
     strYield.foreach(println _)
    boolYield.foreach(println _)*/
    /**
      * 注意，这种循环叫做for推导式， for推导式的结果类型和第一个生成器是类型兼容的 。这里的意思是，存储 yield返回元素的集合是和第一个生成器的集合一致的。
      * 这里所说的生成器是指生成循环体元素的生成器
      * 如下示例
      */
    var temp01 = for(i <- 1 to 10; j <- "hello")yield i
    println(temp01)

    var temp02 = for( j <- "hello";i <- 1 to 10)yield {i+j}
    println(temp02)

    var temp03 = for( j <- "hello"; i <- 1 to 10)yield j
    println(temp03+"----String-----"+temp03.getClass)

    println( 1 to 10 getClass)

    var temp04 = for( j <- Set("sdfsd","sdfesw","sdfswe"))yield j
    println(temp04+"---返回List(String)------")

    println( 1 to 10 getClass)

    var temp05 = for( j <- List("sdfsd","sdfew","sfwe"); i <- 1 to 10)yield i
    println(temp05+"---返回List(Int)------"+temp05.getClass)


  }

  @Test
  def testUnit(){
//    Unit 对应的是().一个空的占位符  表示void 主要用于方法返回值类型。在Java中void只是一个关键字，而不能当做类型来使用。但是在scala中，Unit是类型值，所以可以用来声明参数的类型。并不是一个关键字
    def showInfo(arg0:Unit):Unit={
      var temp = println(arg0.getClass.getClasses.foreach(println _))
      println(temp)
      println(arg0)
      println(arg0.equals(()))
      println(arg0 == ())
      println(().getClass)
      println(().hashCode())
      println(arg0.hashCode())
      println ().toString
    }
    showInfo(23423)
  }
  @Test
  def testApply(): Unit ={
//    这里说明了一个问题。apply方法并不一定总是返回伴生类的实例对象，具体返回值可以根据实际情况决定，手动在方法中设置的
//    其次apply方法是底层自动寻找调用的
//    对于unapply方法，主要是在进行类型匹配的时候使用的，其次在把对象转换为Some()的时候也会使用的unapply方法。

    val temp = "Hello"(2)
    println(temp)
    var stu = Student("Jack",23)
    stu.age=56
    Student.showInfo(stu)
    println("----"+Student(3423))
    println(Student.unapply(stu))
    stu.canWriteable_(556)
    println(stu.canWriteable)

  }


  @Test
  def testVarAndDef(): Unit = {
    var name01 = "JACK"
    def name02 = "Tom"
    val name03 = "xiaoHong"
    println(name01 + "-------class:" + name01.getClass)
    println(name02 + "-------class:" + name02.getClass)
    println(name03 + "-------class:" + name03.getClass)
  }

  @Test
  def testJurisdiction(): Unit ={
    /**
      * 遇到一个问题，导入包的时候导入不进去。
      * 后来发现是自定义包名和引入的jar包名部分重复。
      * 例如：
      * 有两个包： （第三方jar包）com.appach.json.util.....
      *             (自定义jar包) util.....
      *   那么在使用的时候，如果先导入 com.appach.json._  然后再导入自定义的util._ 就没办法找到了。
      *   如果遇到这种情况，则先导入自定义jar包 util._    然后再导入第三方jar  com.appach.json._
      *
      *   建议：给包命名的时候一定要加入特殊的标识符，不要和其它jar包有冲突
      */
  }
  @Test
  def testMethod(): Unit = {
    println("-------匿名函数-----------")
    //    把一个匿名函数赋值给变量
    var anonymousMethod = (num: Double) => num * 2
    List(12, 415, 21.0).map(anonymousMethod).foreach(println _)
    List(12, 415, 21.0).map(num => (num: Double) => num * 2).foreach(println _)
    List(12, 415, 21.0).map((num: Double) => num * 2).foreach(println _)
    List(12, 415, 21.0).map(num => num * 2).foreach(println _)
//   以上三个操作是等价的，后面的两种是简写
    println("-------带函数参数的函数-----------")
//这理把函数当做参数传给valueAtOneQuery，由于计算值已经写死在f(0.5)中，所以只需要把函数传进去，然后该函数会自动调用该参数函数来触发执行、
//    注意该函数与柯里化函数的区别
    println(valueAtOneQuarter(Math.sqrt(_)))
    println(valueAtOneQuarter(Math.sqrt))
//    println(valueAtOneQuarter(Math.sqrt(25)))   该语句错误，参数函数中不能带有参数值
//该函数只接受函数

    //这里定义了一个接受一个参数的函数
    val myFunction = mulBy(23)
    println(myFunction(2))
//使用柯里化函数

    println(mul(2)(3))
//这里需要注意map和foreach的区别，foreach不反返回任何值，只是将函数应用到每个元素中。
    (1 to 9).map("*" * _).foreach(println _)

    println(kelihua(2)(3))
    println(mul(2)(3))

  }
//  该函数的类型为：((Double)=>Double)=>Double      参数为一个函数，返回值为Double类型的函数，该参数类型为以Double类型为参数以Double类型为返回值的函数
//该种能够返回函数的函数成为高阶函数
  def valueAtOneQuarter(f:(Double)=> Double):Double = f(0.5)
//  该函数也是高阶函数
  def mulBy(factory:Double) = (num:Double) => factory * num

  /**
    *  柯里化函数
    *  柯里化函数是一种函数的简写，该函数为接受一个参数 返回一个函数的函数 如函数： kelihua
    *   因为在调用kelihua()的时候需要进行两次调用，第一次得到一个函数，第二次调用得到的函数进行求值
    *   所以可以简写为柯里化函数：mul
    *   如下两个方法调用方式一样。
    */
  def mul(x:Int)(y:Int) = x * y
  def kelihua(x:Double) = (y:Double) => x * y

  //  一个多参数函数的变形
  def manyParameter(x: Int) = (y: Int) => (z: Int) => (m:Int) => x * y * z * m
  @Test
  def useManyParameter(): Unit = {
    println(manyParameter(12)(3)(5)(2))
  }

  @Test
  /**
    * 分析各个集合的()和{}的区别
    */
  def testOthr(): Unit = {
    //这里是调用的list的伴生对象的apply方法，()当做参数使用
    var list = List("hhh", "sdf sdfs分割", "23423")
    //  这里map()会构造一个新的集合，所以调用map的时候必须给定返回值，否则将会生成一个空的集合
    list.map(x => println("----" + x)).foreach(x => println(x + "=="))
    list.map(x => x + "00000000").foreach(x => println(x + "=="))
    //  list.map(x =>println(x);x+"00000000").foreach(println(_,"=="))  该方法错误，在map中 =>后面不能跟多个语句，如果要跟多个语句则必须使用{}包括起来，切最后一个语句为返回的元素
    list.map(x => {println(x); x + "00000000"}).foreach(x => println(x + "=="))
    //() 可以执行语句，但map的返回值会构造一个新的集合，如果没指定返回值则会构造一个长度相等的空元素集合
    println("---------测试 {}---------------")
    list.map { x => println(x); x + "0000000" }.foreach(x => println(x + "==="))
    /**
      * 由此可见()和{}并没有本质上的区别，主要是在表达形式上的不同。两者都是调用
      *final override def map[B, That](f: (A) => B)(implicit bf: CanBuildFrom[List[A], B, That]): That
      * 方法，()或者{}中的代码都是作为参数传入。参数类型为(A) => B 的函数
      */
    println(showMethod(layout,10))
    anyToAny(println("20"))
    for (elem <- anyToAny(List(12, 23, 34, 34).toString())) {println(elem)}

    /**
      *   注意理解：rectangle(12)(2)
      *   rectangle(12) 返回一个(height:Double) => (12 + height) * 2 的函数
      *   然后再次调用该函数，参数为2赋值给height，此时才计算出函数结果。
      */
    println("调用返回值为函数的函数：计算矩形周长："+rectangle(12)(2))
//  上面的函数等同于如下：
    var getPerimeter = rectangle(12)
    var perimeter = getPerimeter(2)
    println(perimeter)

  }

//该函数接受两个参数分别为：输入参数为Int，返回值为String 的函数和一个Int参数。注意=右边的f(v)，这个f就是传入的参数f，v是传入的Int参数
  def showMethod(f: Int => String, v: Int) = f(v)

//  该函数的输入值为任意类型的参数，返回结果为String类型
  def layout[A](x: A): String = {
    "==" + x.toString + "=="
  }

  //  该函数的参数为： 没有参数，返回值为B的函数。由于该函数的返回结果为Unit,所以该函数主要用来执行操作
  def anyToAny[B](f: => B): Unit = {
    f
  }
//
  def anyToAny(f: => String): List[Char] = {
    f.toCharArray.toList
  }
//  定义了一个参数为Double，返回值为函数的函数，返回值类型为 (Double)=> Double
def rectangle(length:Double) = (height:Double) => (length + height) * 2



  @Test
  def testListTOString(): Unit = {
    /**
      * mkString对最小的单个元素进行组合，如果是String调用模块String则对每一个字符进行字符拼接，把单个字符当做最先的执行单位
      */
    val list = List("hhh", "sdf sdfs分割", "23423")
    println(list)
    println(list.toStream.toString())
    println(list.toString())
    println(list.mkString("*"))
    println(list.map(x => x.mkString("*")))
    println("-----------")
    list.map { x => println(x.mkString("*")); x + "=" }.foreach(println _)
    println("-----------")
    list.map { x => println(x.mkString("*")) }.foreach(println _)
    println("-----------")
    list.map(x => println(x))
    println("==================")
    println("sdfsd".mkString("*"))
    println(Array(12, 45, 12, 15, 12).mkString("*"))
    println(Array("sdf", "sd", "werwe", "奇智智能语音").mkString("*"))


  }

  @Test
  def replace(): Unit = {
    def str = "中国，。、?>,,;'][dsdsdfhue234%$^3423erf#$"

    //  "\\pP" 表示所有的Unicode标点符号 “\\pS”表示所有的Unicode的货币符号和数字符号。详情见：http://www.cnblogs.com/qixuejia/p/4211428.html
    def pattern = new Regex("\\pP|\\pS")

    println(pattern.replaceAllIn(str, ""))
  }


  @Test
  def testSort(): Unit = {
    val list = List("j今天时间", "明天时间", "按啊今天时间", "现在是几点？？？")
    list.sortBy((x) => (x.charAt(0), x.size)).foreach(println _)
  }

  @Test
  def testFold(): Unit = {
    val fu01 = Future {
      6 + 5
    }
    val fu02 = Future {
      6 + 2
    }
    val fuList = List(fu01, fu02)
    val foled = Future.foldLeft(fuList)(56) {
      (x, y) => x + y
    }
    Thread.sleep(1000)
    println(foled)
  }

  @Test
  def testPromise(): Unit = {
    val promise = Promise[Int]
    val f = promise.future
    Thread.sleep(2000)
    println(f.isCompleted)
    // 同一个Promise只允许完成一次
    promise.success(45)
    println(f.isCompleted)
    println(f.value)


  }

  @Test
  def testFutureResult(): Unit = {
    val fu = Future.failed {
      new RuntimeException("+_++")
    }
    fu onComplete {
      case Failure(ex) => ex.printStackTrace()
      case Success(res) => println(res)
    }

  }

  @Test
  def testTakeAndDrop(): Unit = {
    val fu01 = Future {
      56
    }
    val fu02 = Future {
      526
    }
    val fu03 = Future {
      516
    }
    var temp = for {
      v1 <- fu01
      v2 <- fu02
      v3 <- fu03
    } yield (v1, v2, v3)
    se
    println(temp)
  }

  def se = {
    Thread.sleep(2000)
  }

  @Test
  def testRecover(): Unit = {
    var temp = futureFailed.recover {
      case e => println(e)
    }
    futureSuccess.recover {
      case e => println(e)
    }
    Thread.sleep(1000)
    println("----" + temp)
    //    这里注意recover和recoverWith的区别，前者返回
    val newFuture = futureFailed recoverWith {
      case e => Future.failed[Int](new RuntimeException("EXCEPTION"))
      case _ => Future.successful("success!!!")
    }
    newFuture.onComplete {
      case Success(res) => println(res)
      case Failure(ex) => ex.printStackTrace()
    }
  }

  def testZipFuture(): Unit = {
    val fut = Future {
      "hello !!"
    } zip (futureSuccess)
    fut.onComplete {
      case Success(res) => println(res);
      case Failure(res) => println(res)
    }
    futureSuccess.foreach(println(_))
    fut.foreach(println(_))
  }

  def testFallBackTo(): Unit = {
    val future = futureFailed fallbackTo (futureSuccess)
    futureFailed onComplete {
      case Failure(ex) => ex.printStackTrace()
    }

    future onComplete {
      case Success(res) => println(res)
    }
    Thread.sleep(1000)
  }


  def useFuture(): Unit = {

    /*    Future {
          println("---------")
          val result = "hello"
          result + "world"
        } andThen {
          //       如果Future没有complete则无法和Case进行匹配
          case Success(result) => println("complete with success: " + result)
          case Failure(exception) => println("complete with failure: " + exception)
        } andThen {
          case result => println("here: " + result)
        }
        Thread.sleep(1000)*/

    val future = Future {
      62 + 56
    }
    future.onComplete {
      case Failure(ex) => println("====="); ex.printStackTrace()
      case Success(res) => println("====="); println(res)
      case _ => println("----")
    }

    //    注意睡眠的目的是让主程序不要结束，否则如果主程序结束的时候Future还没有完成则Future线程也会被关闭掉，则就不会执行Case匹配后面的方法
    Thread.sleep(1000)
  }

  def testOther(): Unit = {
    val fu = Future {
      "sdfsdf"
    }
    Thread.sleep(1000)
    println(fu.value)
  }

  def testfailure(): Unit = {

    Thread.sleep(1000)
    println(futureSuccess.value)
    var resFuture = futureSuccess.failed
    println(resFuture.value)
    println("=================")
    println(futureFailed.value)
    val resFuture02 = futureFailed.failed
    println(resFuture02.value)
    println("=======other test==========")
    println(resFuture02.failed.value)
    println(resFuture02.failed.failed.value)
    println(futureSuccess.fallbackTo(futureFailed).value)
    println("=======recvover test==========")
    val recover = futureSuccess.recover {
      case ex: Exception => 10
    }
    while (!recover.isCompleted) {}
    println(recover.value)

    println("=======对执行结果通过TransForm 把执行结果进行转化==========")
    val transResult = futureFailed.transform {
      case Success(res) => Success(res * -1)
      case Failure(ex) => Failure(new Exception("经过转换的failure"))
    }
    while (!transResult.isCompleted) {}
    println(transResult)
    println("=======zip fold  reduce sequence==========")
    var zipResult = futureSuccess.zip(Future {
      56 + 456
    })
    println(zipResult)
    val fu01 = Future {
      12 + 12
    }
    val fu02 = Future {
      13 + 13
    }
    val fuLit = List(fu01, fu02)
    //    这里的20做为何第一个元素进行操作的初始值。
    val folded = Future.fold(fuLit)(20) {
      (x, y) => x + y
    }
    Thread.sleep(1000)
    println(fuLit)
    println(folded)
    //    reduce不需要初始值
    val reduce = Future.reduce(fuLit) {
      (X, Y) => X * Y
    }
    while (!reduce.isCompleted) {}
    println(reduce)
    println("-------sequence-----把list[Future] 转化为 Future(Try[List])---")
    val fuList02 = List(futureSuccess, futureFailed)
    //    只要有一个Future是failed 则结果就是Future(Failure(java.lang.ArithmeticException: / by zero)  使用fuList02进行测试
    val sequence = Future.sequence(fuLit)
    while (!sequence.isCompleted) {}
    println(fuLit)
    println(sequence)
    println("-----traverse------------------")
    val traversed = Future.traverse(List(1, 2, 3, 4, 5, 6)) { i => Future(i) }
    println(traversed)
    println("-----foreach------------------")
    futureFailed.foreach(x => println(x))
    futureSuccess.foreach(x => println(x))
    println("-----zip------------------")
    var ziped = Future {
      12 + 152
    }.zip(Future {
      "werff" + "wewe"
    })
    while (!ziped.isCompleted) {}
    println(ziped)
    println(ziped.value)
    val maped = ziped map {
      case (num, str) => s"$num is $str"
    }
    while (!maped.isCompleted) {}
    println(maped)
    println(maped.value)
    println("-----Await.result--------")
    var awaitRes = Await.result(Future {
      123 + 56
    }, 1 microsecond)
    println(awaitRes)

  }

  val futureSuccess = Future {
    50 / 1
  }
  val futureFailed = Future {
    50 / 0
  }

  def testPromiseUse(): Unit = {
    //TODO  如何使用Promise? 如何设置Future的执行体
    val promise = Promise[Int]
    val future = promise.future

  }

  def testPromiseAndFuture(): Unit = {
    val fut = Future.successful(23 + 65)
    println(fut)
    val futFaild = Future.failed(new Exception("========="))
    println(futFaild.value)
    val futTry01 = Future.fromTry(Success {
      1233 + 56
    })
    val futTry02 = Future.fromTry(Failure(new Exception("fail")))
    println(futTry01.value)
    println(futTry02.value)
    val prom = Promise[Int]
    val fuPro = prom.future
    println(fuPro.value)

    //    prom.success(345)
    //    println(fuPro.value)
    //    同一个Promise只能完成一次
    prom.failure(new RuntimeException(" exception "))
    println(fuPro.value)

    println("========test filter and collect============")
    val futFilt = Future {
      30
    }
    //    如果没有验证通过，则返回NoSuchElementsException
    //    验证通过则返回Value
    val futFileResu = futFilt.filter(item => item > 100)
    while (!futFileResu.isCompleted) {
    }
    futFileResu.value.map(println(_))
    println("===collect Test===")
    val futFileColl = futFilt.collect {
      //      case resu if resu > 100 => resu + 100
      case resu if resu < 100 => resu + 100
    }
    while (!futFileColl.isCompleted) {}
    println(futFileColl.value)

  }

  @Test
  def testFutureOperation(): Unit = {
    val fu01 = Future {
      4 + 2
    }
    val fu02 = Future {
      2 + 3
    }
    val fuSum = for {
      x <- fu01
      y <- fu02
    } yield x + y + 2
    fuSum.onComplete {
      case Success(result) => println("==========="); println(result)
      case Failure(e) => println("==========="); e.printStackTrace()
    }
    println(fuSum)

  }

  def testFutureUserMap(): Unit = {
    //    运行该方法则会开启三个线程，主线程main 线程1计算 12+56 线程而计算map。
    val fu = Future {
      Thread.sleep(2000)
      12 + 56
    }
    Thread.sleep(2100)
    println(fu.value)
    //    注意，该语句同样是开启一个线程进行计算，所以这里也需要进行等待，如果不加sleep 则在没有计算结束之后就输出则为Nonr
    val result = fu.map(x => x + 2)
    //    Thread.sleep(2100)
    println(result.value)
  }

  def testFutureTryException(): Unit = {
    //  future 的错误在执行的时候才会发现？
    val fut = Future {
      Thread.sleep(5000)
      21 / 0
    }
    Thread.sleep(6000)
    //  如果加载失败则输出：Some(Failure(java.lang.ArithmeticException: / by zero))
    println(fut.value)

  }

  def testFuture(): Unit = {
    //    import scala.concurrent.ExecutionContext.Implicits.global
    val future = Future {
      Thread.sleep(5000)
      323 + 26
    }
    Thread.sleep(6000)
    println(future.isCompleted)
    var result = future.value
    println(future.value)
  }


  /*def showInfo(str:String,age:Int): Unit ={
    val array = ("werwe","wedsew","wegdew23")
    val future2: Future[String] =Patterns.ask(myActor, AskNameMessage).mapTo[String]
    val result2 = Await.result(future2, 1 second)
    println(result2)

  }*/
}
