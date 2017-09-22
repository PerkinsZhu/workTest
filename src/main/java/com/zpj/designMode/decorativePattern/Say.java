package com.zpj.designMode.decorativePattern;

public class Say extends Decorator {

	public Say(Person per) {
		super(per);
	}

	@Override
	public void action() {
		System.out.println("--------扩展功能---------说");
		per.action();
	}

}
