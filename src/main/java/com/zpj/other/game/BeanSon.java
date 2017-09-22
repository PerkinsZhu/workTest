package com.zpj.other.game;

public class BeanSon extends Bean {
	static {
		A = 6;
		System.out.println("BeanSon Static block====" + A);
	}

	{
		A = 8;
		System.out.println("BeanSon Non Static block---" + A);
	}

	public void sayHello() {
		System.out.println("Hello ~");
	}
}
