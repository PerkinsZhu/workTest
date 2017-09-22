package com.zpj.dataStructure.LinkedList;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LinkedListTest {

	private LinkedList list = new LinkedList();
	
	@Before
	public void testAdd(){
		for(int i = 0;i<10;i++){
			list.add(i);
		}
	}
	
	@Test
	public void testGet(){
		System.out.println(list.get(2));
	}
	
	
	
	@After
	public void disPlay(){
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}	
	
	
}
