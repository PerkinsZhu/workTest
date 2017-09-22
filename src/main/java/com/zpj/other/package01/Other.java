package com.zpj.other.package01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

public class Other {
	@Test
	public void testDataSimpleFormat(){
			System.out.println(new SimpleDateFormat("mm:ss").format(new Date()).substring(1));
	}
	
	@Test
	public void testNullKeyMap(){
		HashMap<String,Long> map = new HashMap<String,Long>();
		map.put("sd", (long) 12);
		System.out.println(12==map.get("sd"));
	}
	
	@Test
	public void teseString(){
		String[] temp = new String[6];
		System.out.println(temp[0]);
		temp = "场外箱体#川沙#上浦小区＿51034014#湿度#15.19.211.79#nomi".split("#");
		System.out.println(temp[0]);
	}
	
	@Test
	public void testString(){
		String str = "sfwe";
		System.out.println(str);
		
	}
	@Test
	public void testVoid(){
//		showInfo(void);
	}

	private void showInfo(String name){
		System.out.print(name);
	}
	
}
