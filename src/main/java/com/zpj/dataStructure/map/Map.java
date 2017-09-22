package com.zpj.dataStructure.map;

import java.util.HashMap;

import org.junit.Test;

public class Map {
	@Test
	public void printMaxInteger(){
		System.out.println(Integer.MAX_VALUE);
	}
	
	@Test
	public void testNullKey(){
		HashMap <String,Integer> map = new HashMap<String,Integer>();
		map.put(null, 10);
		map.put(null, 20);
		map.put(null, 30);
		map.put(null, 40);
		System.out.println(map.get(null));
	}
	
	
	
}
