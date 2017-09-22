package com.zpj.scala.elasticsearch

import java.net.{InetAddress, UnknownHostException}
import java.util.Date

import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.action.update.UpdateRequest
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient

/**
  * Created by PerkinsZhu on 2017/8/17 14:25. 
  */
object Test {
  val host = InetAddress.getByName("127.0.0.1");
  val client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new InetSocketTransportAddress(host, 9300))

  def main(args: Array[String]): Unit = {
//        testAdd()
//    testDelete
//    testUpdate
//    testquery
//    testBulk
//    testquery
    testMulitGet
    client.close();
  }

  def testquery(): Unit = {
    val response = client.prepareGet("twitter", "tweet", "3").setOperationThreaded(false).get // 线程安全
    println(response.getSourceAsString)

  }

  def testAdd(): Unit = {
    val json = "{" + "\"user\":\"jack\"," + "\"postDate\":\"2017-01-30\"," + "\"message\":\"trying out sdfsdfsdf\"" + "}"
    val response = client.prepareIndex("twitter", "tweet","2").setSource(json).get
    println(response.getResult.toString)

  }

  def testDelete(): Unit = {
    val response = client.prepareDelete("twitter", "tweet", "1").get
    val index = response.getIndex
    val `type` = response.getType
    val id = response.getId
    val version = response.getVersion
    System.out.println(index + " : " + `type` + ": " + id + ": " + version)
  }

  import org.elasticsearch.action.update.UpdateResponse
  import org.elasticsearch.common.xcontent.XContentFactory

  @throws[Exception]
  def testUpdate(): Unit = {
    val updateRequest = new UpdateRequest()
    updateRequest.index("twitter").`type`("tweet").id("2").doc(XContentFactory.jsonBuilder.startObject.field("gender", "male").field("message", "hello").endObject)// 对没有的字段添加, 对已有的字段替换
    val response = client.update(updateRequest).get
    val index = response.getIndex
    val `type` = response.getType
    val id = response.getId
    val version = response.getVersion
    System.out.println(index + " : " + `type` + ": " + id + ": " + version)
  }

  def testBulkAdd(): Unit = {
    //批量处理
    val response = client.prepareBulk().add(client.prepareIndex("twitter", "tweet", "3")
      .setSource(XContentFactory.jsonBuilder()
        .startObject()
        .field("user", "kimchy")
        .field("postDate", new Date())
        .field("message", "trying out Elasticsearch")
        .endObject())).add(client.prepareIndex("twitter", "tweet", "4")
      .setSource(XContentFactory.jsonBuilder()
        .startObject()
        .field("user", "kimchy")
        .field("postDate", new Date())
        .field("message", "another post")
        .endObject())).get()
    println(response)
  }
  def testMulitGet(): Unit ={//多条件查询
    client.prepareMultiGet()
      .add("twitter", "tweet", "1")
      .add("twitter", "tweet", "2", "3", "4")
      .add("anothoer", "type", "foo").get().forEach(item =>{
      val dat = item.getResponse;
      if(dat.isExists) println(dat.getSourceAsString)
    })
  }

}
