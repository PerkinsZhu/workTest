package com.zpj.dataStructure.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import com.zpj.bean.Student;
import com.zpj.test.MyTest;
import com.zpj.tool.creator.Creator;
import com.zpj.tool.printCollection.PrintCollection;

public class ArrayResearch extends MyTest {

	/**
	 * Arrays ArraysUtils
	 */

	Integer[] arrayNum = new Integer[5];
	Student[] stus = new Student[5];

	@Test
	public void test() {
		Arrays.fill(arrayNum, 5);
		Arrays.fill(stus, new Student());
		PrintCollection.printArray("student:", stus);
		PrintCollection.printArray("int:", arrayNum);

		System.out.println(Arrays.deepToString(stus));
		System.out.println(Arrays.deepToString(arrayNum));
		PrintCollection.printArray("----", Arrays.copyOf(stus, 3));

		Integer[] arrayNum02 =Creator.createIntegerArray(5,100);
		PrintCollection.printArray("------createArray:", arrayNum02);
		ArrayUtils.reverse(arrayNum02);
		PrintCollection.printArray("int:", arrayNum02);
		
		Student st = new Student();
		ArrayUtils.add(stus, 2, st);
		System.out.println(Arrays.deepToString(stus));
		
		Student [] sts =(Student[]) Array.newInstance(Student.class, 3);//可以动态创建数组
		System.out.println(sts.length);
		
	}
	
	@Test
	public void testArrayCopyOf(){
		/*Integer[] array = Creator.createIntegerArray(5,10);
		PrintCollection.printArray(array);
		PrintCollection.printArray(Arrays.copyOf(array, 20));*/
		
		Integer [] src= new Integer [10];
		src[1]=5;
		System.out.println();
//		PrintCollection.printArray(Arrays.copyOf(src, 5));
		
	}
	
	
	
	
	
	@Test
	public void testArrayList(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("String-----------");
	}
	
	

}
