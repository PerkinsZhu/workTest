package com.zpj.jvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompileTest {

	public static void main(String[] args) {
		List<String> lis = new ArrayList<String>();
		lis.add("sdfs");
		List<Map<String,String>> lis2 = new ArrayList<Map<String,String>>();
		Map<String ,String> map = new HashMap<String,String>();
		map.put("key", "Value");
		lis2.add(map);
		printInfo(lis);
		print(lis2);
	}

	public static String printInfo(List<String> list) {
		System.out.println("String---"+list.get(0));
		return null;
	}

	public static Object print(List<Map<String,String>> list) {
		System.out.println("map"+list.get(0));
		return null;
	}

}
