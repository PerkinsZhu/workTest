package com.zpj.memory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class MemoryTest {

	
	@Test
	public void testHeapSpace(){
		int [] a = new int[100000000];
	}
	
	@Test
	public void testHeapSpace02(){
		List<Student> students = new ArrayList<Student>();
		for(int i = 0;i<1000000;i++){
			Student stu = new Student();
			students.add(stu);
		}
	}
	
	class Student{
		private String name1 ="123";
		private String name2 ="123";
		private String name3 ="123";
		private String name4 ="123";
		private String name5 ="123";
		private String name6 ="123";
	}
	
	@Test
public void testHeapSpace03(){
		while(true){
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
}
