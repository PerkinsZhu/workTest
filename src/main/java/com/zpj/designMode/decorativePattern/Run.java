package com.zpj.designMode.decorativePattern;

public class Run extends Decorator {

	public Run(Person per) {
		super(per);
	}

	@Override
	public void action() {
		System.out.println("--------扩展功能---------跑");
		per.action();
	}

}
