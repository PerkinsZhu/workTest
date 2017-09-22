package com.zpj.other.game;

public class Bean {
	static int A = 2;
	{
		A = 3;
		System.out.println("Bean Static block====" + A);
	}
	static {
		A = 5;
		System.out.println("Bean Non Static block---" + A);
	}

	public Bean() {
		this(A);
		System.out.println("Bean construction method");
	}

	public Bean(int num) {
		System.out.println("Bean parametric construction method");
		System.out.println(Bean.A);
		this.A = num;
		System.out.println(num);
	}
}
