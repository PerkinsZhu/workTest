package com.zpj.scala.actor

/**
  * Created by Administrator on 2017/6/12.
  */

import akka.actor.Actor
import akka.actor.{ActorSystem, Props}
object ActorTest {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HelloSystem")
    // 缺省的Actor构造函数
    val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
    helloActor ! "hello"
    helloActor ! "喂"
  }
}

class HelloActor extends Actor {
  def receive = {
    case "hello" => println("您好！")
    case _       => println("您是?")
  }
}