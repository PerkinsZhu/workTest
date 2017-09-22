package com.zpj.designMode.abstractFactory;

import org.junit.Test;

public class AbstructTest {

	@Test
	public void test01() {
		//各个工厂可以生产各自的产品
		Factory factoryA = new FactoryA();
		Computer compA = factoryA.createCompulter();
		compA.IamComputer();
		MobilePhone mpA = factoryA.createMobilePhone();
		mpA.IamMobilePhone();
		Factory factoryB = new FactoryB();
		Computer compB = factoryB.createCompulter();
		compB.IamComputer();
		MobilePhone mpB = factoryB.createMobilePhone();
		mpB.IamMobilePhone();
	}
}
