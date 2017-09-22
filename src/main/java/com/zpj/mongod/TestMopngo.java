package com.zpj.mongod;

public class TestMopngo {

	public static void main(String[] args) {
		
		GoThread thread01= new GoThread("1");
		thread01.run();
		GoThread thread02= new GoThread("2");
		thread02.run();
		GoThread thread03= new GoThread("3");
		thread03.run();
		GoThread thread04= new GoThread("4");
		thread04.run();
	}
}
