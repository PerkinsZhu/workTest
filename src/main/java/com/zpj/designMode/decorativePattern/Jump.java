package com.zpj.designMode.decorativePattern;

public class Jump extends Decorator {

	public Jump(Person per) {
		super(per);
	}

	@Override
	public void action() {
		System.out.println("--------扩展功能--------跳");
		per.action();
	}

}
