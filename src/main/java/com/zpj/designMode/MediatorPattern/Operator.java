package com.zpj.designMode.MediatorPattern;

public abstract class Operator {
	protected Mediator mediator = Mediator.getInstance();
	protected int topPrice = 0;

	public Operator(int topPrice) {
		super();
		this.topPrice = topPrice;
	}

	public abstract void sell(int price);

	public abstract void buy();

}
