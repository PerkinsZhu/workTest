package com.zpj.designMode.decorativePattern;

/**
 * 严格意义上讲，被装饰者和装饰者需要实现相同的接口或者继承相同的父类 一个装饰者只能装饰同一类被装饰者，也即是 Decorator只能装饰
 * 实现Person接口的类型对象。
 */
public class Decorator implements Person {
	protected Person per;

	public Decorator(Person per) {
		super();
		this.per = per;
	}

	@Override
	public void action() {
		System.out.println("------基本行为-----哭");
		per.action();
	}

}
