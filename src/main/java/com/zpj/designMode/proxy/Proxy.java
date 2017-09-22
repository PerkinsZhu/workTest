package com.zpj.designMode.proxy;

//静态代理，代理必须和目标类实现共同的接口
public class Proxy implements Person {
	private Person person;// 被代理人

	//这里的目标类型决定了该代理类只能代理实现了Person接口的实例，而不能接收其他类型参数，这也就是静态代理的局限性
	public Proxy(Person person) {
		this.person = person;
	}

	@Override
	public void doWork() {
		System.out.println("doSomething-----start");
		person.doWork();
		System.out.println("doSomething-----end");
	}

}
