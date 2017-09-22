package com.zpj.designMode.MediatorPattern;

import org.junit.Test;

public class TestUnit {

	@Test
	public void test01() {
		Mediator mediator = Mediator.getInstance();

		Operator op01 = new Operator01(12);
		Operator op02 = new Operator02(20);

		mediator.addOperators(op01);
		mediator.addOperators(op02);

		// 02卖方
		op02.sell(1);

	}
}
