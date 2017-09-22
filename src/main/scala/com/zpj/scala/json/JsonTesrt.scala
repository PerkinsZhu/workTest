package com.zpj.scala.json

import com.alibaba.fastjson.JSON
import play.api.libs.json.{JsObject, Json}

/**
  * Created by Administrator on 2017/6/14.
  */
object JsonTesrt {
  def main(args: Array[String]): Unit = {
    import play.api.libs.json._

    val json: JsValue = Json.parse(
      """
{
  "name" : "Watership Down",
  "location" : {
    "lat" : 51.235685,
    "long" : -1.309197
  },
  "residents" : [ {
    "name" : "Fiver",
    "age" : 4,
    "role" : null
  }, {
    "name" : "Bigwig",
    "age" : 6,
    "role" : "Owsla"
  } ]
}
""")
    val latPath = JsPath \ "location" \ "lat"
    println(JsPath)

  }
}
