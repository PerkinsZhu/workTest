package com.zpj.designMode.builderpattern;

import org.junit.Test;

public class BuilderPatternTest {

	@Test
	public void test01(){
		//给主管一个IBM的员工让主管告诉IBM的员工怎么创建一个IBM电脑
		Director IBMDirector = new Director(new IBMCreator());
		IBMDirector.createComputer();
		//给主管一个Lenovo的员工让主管告诉IBM的员工怎么创建一个IBM电脑
		Director lenovoDirector = new Director(new LenovoCreator());
		lenovoDirector.createComputer();
	}
}
