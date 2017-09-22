package com.zpj.other.game;

public class StaticTest {
	static int A = 0;
	static {
		A = 10;
		System.out.println("Static block---1");
		System.out.println(A);
	}
	{
		A = 8;
		System.out.println("Non Static block---2");
		System.out.println(A);
	}

	public StaticTest() {
		this(StaticTest.A);
		System.out.println("Non parametric construction method---4");
		System.out.println(A);
	}

	public StaticTest(int n) {
		System.out.println("construction method---3");
		System.out.println(n);
	}

	public static void main(String[] args) {
		StaticTest st01 = new StaticTest();
		StaticTest st02 = new StaticTest();
	}

}
