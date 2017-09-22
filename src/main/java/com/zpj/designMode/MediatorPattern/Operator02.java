package com.zpj.designMode.MediatorPattern;

public class Operator02 extends Operator {

	public Operator02(int basePrice) {
		super(basePrice);
	}

	@Override
	public void sell(int price) {
		mediator.sell(this, price);
	}

	@Override
	public void buy() {
		System.out.println("Operator02-----------我买！");
	}

}
