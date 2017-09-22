package com.zpj.designMode.decorativePattern;

import org.junit.Test;

public class TestUnit {

	@Test
	public void test01() {
		// 一个正常人，刚生下来会呼吸
		Person per = new NormalPerson();//会呼吸的人
		per = new Decorator(per);
		per = new Run(per);
		per = new Say(per);
		per = new Jump(per);
		per = new Drive(per);
		per.action();
		System.out.println("************************************************");
		// 一个聋哑人 不会说话，不能开车，刚生下来会呼吸
		Person per02 = new UnNormalPerson();//会呼吸的人
		per02 = new Decorator(per02);
		per02 = new Run(per02);
		per02 = new Jump(per02);
		per02.action();
		// 通过此种模式可以对对象随意的进行功能添加、删除，而不像继承那样会继承一些父类的不需要的方法
	}
}
