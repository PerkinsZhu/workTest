package com.zpj.innerClass;

public class Student {
	private int age;
	private static String sname;
	static class Book {
		private String name;

		public Book(String name) {
			super();
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	
	
	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public static String getSname() {
		return sname;
	}



	public static void setSname(String sname) {
		Student.sname = sname;
	}



	public Book myBook() {
		return new Book("book02");
	}

}
