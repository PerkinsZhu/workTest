package com.zpj.innerClass;

import org.junit.Test;

import com.zpj.innerClass.Student.Book;

public class InnerClassTest {

	@Test
	public void test(){
		Book bo = new Book("book01");
		Book bo02 = new Book("book02");
		System.out.println(bo);
		System.out.println(bo02);
		
		
		Student stu= new Student();
		stu.setSname("student01");
		Student stu02= new Student();
		stu02.setSname("student02");
		
		System.out.println(stu.getSname());
		System.out.println(stu02.getSname());
		
		System.out.println(stu);
		System.out.println(stu02);
		
	}
}
