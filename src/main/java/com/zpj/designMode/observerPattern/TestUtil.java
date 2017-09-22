package com.zpj.designMode.observerPattern;

import org.junit.Test;

public class TestUtil {

	@Test
	public void test01() {
		Monitor mo = new Monitor();
		Observer s1 = new Student01();
		Observer s2 = new Student02();
		Observer s3 = new Student03();

		mo.addObserver(s3);
		mo.addObserver(s2);
		mo.addObserver(s1);

		mo.notifyStudent();
	}

	// java 的观察者模式
	@Test
	public void test02() {
		Monitor02 mo02 = new Monitor02();
		java.util.Observer student04 = new Student04();
		mo02.addObserver(student04);
		mo02.teacherIsComming();

	}
}
