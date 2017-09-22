package com.zpj.dataStructure.HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

public class HashTableTest {

	@Test
		public void testHashTable(){
			Hashtable table = new Hashtable();
			for(int i = 0;i<10; i++){
				table.put(i,"---------------------"+i);
			}
			table.put(null, "======");
			System.out.println(table.hashCode());
			for(int j = 0; j<table.size();j++){
				System.out.println(table.get(j));
			}
			
		}
	@Test
	public void testHashCode(){
		Hashtable table = new Hashtable();
		System.out.println("123".hashCode());
	}
	
	@Test
	public void testHashMap(){
		HashMap map = new HashMap();
		map.put("asd","-----------------asd");
		Set set = new HashSet();
		
	}
	@Test
	public void testHashSet(){
		Set set = new HashSet();
		set.add("2");		
		set.add("2");		
		System.out.println(set.size());
	}
	@Test
	public void testTreeMap(){
		TreeMap map = new TreeMap();
		map.put("asd","-----------------asd");
		map.put("dsd","-----------------asd");
		map.put("bsd","-----------------asd");

		Iterator ite=map.keySet().iterator();
		while(ite.hasNext()){
			System.out.println(ite.next());
		}
	}
	@Test
	public void testList(){
		List list = new ArrayList();
		list.add("5");
		list.add("2");
		for(int i = 0; i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
				//	矩阵相乘
		@Test
		public void testArray(){
			int [][] data1 = {{2,3,5,1},
										 {5,4,6,9}};
			
			int [][] data2 = {{5,4},
										  {4,7},
										  {9,3},
										  {2,4}};
			int [][] result = new int[2][2];
			for(int i = 0 ; i< data1.length;i++){//控制行
				int [] data1a = data1[i];
				for(int  j = 0 ;j<data1.length;j++){//控制列
					int num = 0;
					for(int k = 0;k <data1a.length;k++) {//循环汇总数据
						num += data1a[k]*data2[k][j];
					}
					result[i][j] = num;
				}
			}
			for(int i = 0; i<result.length;i++){
				for(int j = 0; j<result.length;j++){
					System.out.println(result[i][j]);
				}
			}
		}
}
