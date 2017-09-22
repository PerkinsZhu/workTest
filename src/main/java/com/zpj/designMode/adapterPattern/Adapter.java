package com.zpj.designMode.adapterPattern;

//注意此处：extends Animal
public class Adapter extends Animal {
	private Bird bird;

	public Adapter(Bird bird) {
		super();
		this.bird = bird;
	}

	// 调用待适配对象的方法
	@Override
	public void run() {
		bird.fly();
	}

}
