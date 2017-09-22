package com.zpj.scala.mongodb

import java.text.SimpleDateFormat

import com.mongodb.{BasicDBObject, ServerAddress}
import com.mongodb.casbah.{MongoClient, MongoCredential, MongoDB}
import com.mongodb.casbah.commons.MongoDBObject

/**
  * Created with IntelliJ IDEA.
  * Description:
  * User: Perkins Zhu
  * Date: 2017-05-28
  * Time: 19:33
  */
object MongoTool {
  def main(args: Array[String]): Unit = {
//        testInsert
//    testUpdate02
    testSelect
//  testDelete
  }
  def testDelete(): Unit ={
    var query = MongoDBObject("name" -> "user1", "email" -> "user1@test.com")
    println("=========删除之前============")
    collection.find(query).forEach(x => println(x))
    //该参数只是一个查询条件，符合该条件的所有集合都将被删除
    collection.remove(query)
//    collection.findAndRemove()
    println("=========删除之前============")
    collection.find(query).forEach(x => println(x))
  }


  def testUpdate01(): Unit = {
    var query = MongoDBObject("name" -> "user1", "email" -> "user1@test.com")
    var value = MongoDBObject("name" -> "user1", "email" -> "user1@test.com123456")
    println("=========更新之前============")
    var query02 = MongoDBObject("name" -> "user1")
    collection.find(query02).forEach(x => println(x))
//    query:根据此条件进行查询  value：把查询出来结果集的第一条数据设置为value
    collection.update(query,value)
    println("=========更新之后============")
    collection.find(query02).forEach(x => println(x))
  }

  def testUpdate02(): Unit = {
    var query = MongoDBObject("name" -> "user1", "email" -> "user1@test.com123456")
    var value = new BasicDBObject("$set", new BasicDBObject("email", "user1@test.com"))
    //     var value = MongoDBObject("$set",MongoDBObject("name" -> "user1", "email" -> "user1@test.com123"))
    println("=========更新之前============")
    var query02 = MongoDBObject("name" -> "user1")
    collection.find(query02).forEach(x => println(x))
    collection.update(query, value,true, true)
    println("=========更新之后============")
    collection.find(query02).forEach(x => println(x))
  }

  def testSelect(): Unit ={
    println("=========查询所有数据===================")
    collection.find().forEach(x => println(x))
    println("=========查询name = “user1”  同时email=“user1@test.com”===================")
    collection.find(MongoDBObject("name" -> "user1", "email" -> "user1@test.com")).limit(3).forEach(x => println(x))
    //    注意此处不能使用put添加其他查询条件，因为put返回的是HashMap,此处应该使用append进行添加查询条件
    //  var query = new BasicDBObject("name",new BasicDBObject("$in",("user145","user155"))).put("qty",new BasicDBObject("$in",(25.0,105.0)))  该方法错误
    //    查询条件为： (name in ("user145","user155")) && (qty in (25.0,105.0))
    println("=========查询 (name in (\"user145\",\"user155\")) && (qty in (25.0,105.0))===================")
    var query = new BasicDBObject("name", new BasicDBObject("$in", ("user145", "user155"))).append("qty", new BasicDBObject("$in", (25.0, 105.0)))
    collection.find(query).forEach(x => println(x))
    println("=========查询 start >= 10 && end<= 80 的数据===================")
    var query02 = new BasicDBObject("start", new BasicDBObject("$gte", 10)).append("end", new BasicDBObject("$lte", 80))
    collection.find(query02).forEach(x => println(x))
  }

  def testInsert(): Unit = {
    for (i <- 1 to 100)
//      注意与saved的区别
      collection.insert(MongoDBObject("name" -> "Jack%d".format(i), "email" -> "jack%d@sina.com".format(i), "age" -> i % 25, "birthDay" -> new SimpleDateFormat("yyyy-MM-dd").parse("2016-03-25")))
  }

  var collection = createDatabase("localhost", 27017, "mytest", "user", "123456").getCollection("user")

  //验证连接权限
  def createDatabase(url: String, port: Int, dbName: String, loginName: String, password: String): MongoDB = {
    var server = new ServerAddress(url, port)
    //注意：MongoCredential中有6种创建连接方式，这里使用MONGODB_CR机制进行连接。如果选择错误则会发生权限验证失败
    var credentials = MongoCredential.createCredential(loginName, dbName, password.toCharArray)
    var mongoClient = MongoClient(server, List(credentials))
    mongoClient.getDB(dbName)
  }

  //  无权限验证连接
  def createDatabase(url: String, port: Int, dbName: String): MongoDB = {
    MongoClient(url, port).getDB(dbName)
  }
}
