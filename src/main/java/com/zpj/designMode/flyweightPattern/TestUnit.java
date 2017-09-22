package com.zpj.designMode.flyweightPattern;

import org.junit.Test;

public class TestUnit {

	@Test
	public void test01() {
		Car car;
		CarFactory cf = new CarFactory();
		car = cf.getCar("volo");
		car.setSpeed(100);
		car.go();

		car = cf.getCar("bmw");
		car.setSpeed(90);
		car.go();

		System.out.println("这次获取的car和第一个获取的car是同一个共享对象,但是外部状态speed是不一样的。");
		car = cf.getCar("volo");
		car.setSpeed(180);
		car.go();

	}
}
