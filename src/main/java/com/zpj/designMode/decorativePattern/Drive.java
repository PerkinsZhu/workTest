package com.zpj.designMode.decorativePattern;

public class Drive extends Decorator {

	public Drive(Person per) {
		super(per);
	}

	@Override
	public void action() {
		System.out.println("--------扩展功能--------开车");
		per.action();
	}

}
