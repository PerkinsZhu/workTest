package com.zpj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

public class FileOperator {

	@Test
	public void testwrite() {

		OutputStream fos;
		try {
			File file = new File("config/config.properties");

			Properties prop = new Properties();
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "");
			map.put("dataPackage", "");
			map.put("handshakeReqCommand", "");
			map.put("handshakeRespInfo", "");
			map.put("versionReqCommand", "");
			map.put("versionRespInfo", "");
			map.put("machinePort", "");
			map.put("sendFinishFlag", "");
			prop.putAll(map);
			String str = "软件名称\nji";
			prop.store(new FileOutputStream(file), str);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	@Test
	public void testread() {
		
		OutputStream fos;
		try {
			File file = new File("config/config.properties");
			
			Properties prop = new Properties();
			prop.load(new FileInputStream(file));
			Map map = new HashMap<String,String>();
			Set<Entry<Object, Object>>  set = prop.entrySet();
			 Iterator<Entry<Object, Object>> it = set.iterator();
		        String key = null, value = null;  
		        while (it.hasNext()) {  
		  
		            Entry<Object, Object> entry = it.next();  
		  
		            key = String.valueOf(entry.getKey());  
		            value = String.valueOf(entry.getValue());  
		  
		            map.put(key, value);
		        }  
			
			System.out.println(map.size());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	@Test
	public void testSubString(){
		String res = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><xml><FromUserName><![CDATA[gh_f11d5fb76648]]></FromUserName><ToUserName><![CDATA[oH9hEwNIZYyx0LpVK38uUqQt9YJk]]></ToUserName><CreateTime>1499154698</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[那我就大发慈悲的满足一下你吧：你好，帅哥]]></Content></xml>";
		System.out.println(res.substring(res.indexOf("?>")+2));
	}

}
