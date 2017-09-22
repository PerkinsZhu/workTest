package com.zpj.test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.JsonNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.libs.Json;

public class MyTest {
	protected long startTime;

	@Before
	public void init() {
		startTime = new Date().getTime();
	}

	@After
	public void stop() {
		System.out.println("运行时间：" + (new Date().getTime() - startTime) + " 毫秒");
	}
	
	
	
	
	@Test
	public void testOther(){
		Random	x=new Random();
		for(int i = 0; i<10;i++)
		System.out.println( x.nextInt(20));
	}

	@Test
	public  void testQuery(){
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		for(int i =0 ;i <100;i++){
			queue.add("hello -- "+ i );
		}

		String message =null;
		while((message = queue.poll()) != null){
			System.out.println(message);
		}

		System.out.println();
		for(String it : queue) {
			System.out.print(it+"、" );
		}
	}

	public void testJson(){
		String str = "API string : {\"chatId\":\"59658f204a00004a0010403f\",\"channel\":\"weixin\",\"userId\":\"oqNWL01BKffnF8W22wH3KLxL4J-U\",\"sessionId\":\"oqNWL01BKffnF8W22wH3KLxL4J-U\",\"question\":\"f\",\"answers\":[{\"respond\":\"目前，从广州直达 揭阳有两个产品：\\n1.广州直飞揭阳3天2晚自由行（含税往返机票+2晚汕头国际大酒店酒店），您可以点击下面网址进行预订：\\n<a href=\\\"https://trip.csair.com/module/newgroupon/D1612160000002.html \\\">点击查看</a>\\n2.广 州直飞揭阳3天2晚自由行（往返机票+2晚精品酒店），您可以点击下面网址进行预订：\\n<a href=\\\"https://trip.csair.com/module/newgroupon/D1610250000449.html \\\">点击查看</a>\",\"score\":0,\"choices\":[],\"suggestions\":[]}]}";
//		JsonNode tempNode= JSONObject.parse(str);
	}

}
