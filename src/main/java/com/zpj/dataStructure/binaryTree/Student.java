package com.zpj.dataStructure.binaryTree;

public class Student implements Comparable{

	private int age = 0;

	
	public Student(int age) {
		super();
		this.age = age;
	}

	@Override
	public int compareTo(Object o) {
		Student stu =(Student)o;
		return this.age - stu.age;
	}

	@Override
	public String toString() {
		return "age=" + age ;
	}
	
	
}
