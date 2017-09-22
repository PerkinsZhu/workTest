package com.zpj.designMode.MediatorPattern;

public class Operator01 extends Operator {

	public Operator01(int basePrice) {
		super(basePrice);
	}

	@Override
	public void sell(int price) {
		mediator.sell(this, price);
	}

	@Override
	public void buy() {
		System.out.println("Operator01-----------我买！");
	}

}
