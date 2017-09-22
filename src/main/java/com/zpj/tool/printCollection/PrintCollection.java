package com.zpj.tool.printCollection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * 集合打印
 * 
 * @author Perkins Zhu
 */
public class PrintCollection {

	/**
	 * 迭代输出集合
	 * 
	 * @param colle
	 */
	public static <T> void printByIterator(Collection<T> colle) {
		Iterator<T> ite = colle.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next().toString());
		}
	}

	/**
	 * 打印数组
	 * @param info 说明参数
	 * @param array
	 */
	public static <T> void printArray(String arrayIinfo, T[] array) {
		System.out.println(arrayIinfo);
		printArray(array);
	}

	public static <T> void printArray(T[] array) {
		for (T item : array) {
			System.out.println(item.toString());
		}
	}

	public static void printMap(Map map) {
		if(map == null){
			return ;
		}
		System.out.println("-----------------print map---------------------");
		Iterator ite = map.entrySet().iterator();
		while(ite.hasNext()){
			System.out.println(ite.next().toString());
		}
	}

}
