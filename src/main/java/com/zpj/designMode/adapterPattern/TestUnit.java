package com.zpj.designMode.adapterPattern;

import org.junit.Test;

public class TestUnit {

	@Test
	public void test01() {
		Bird bird = new Bird();
		// 此时可以使用animal抽象类来调用Bird对象
		Animal birdAnimal = new Adapter(bird);
		birdAnimal.run();
	}
}
