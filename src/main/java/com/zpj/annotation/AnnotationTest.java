package com.zpj.annotation;

import java.lang.reflect.Method;

import org.junit.Test;

public class AnnotationTest {

	@ZhuPingJing(name = "123")
	public void sayHello() {
	}

	@Test
	public void testAnn() {
		Class c;
		try {
			c = Class.forName("com.zpj.annotation.AnnotationTest");

			Method[] method = c.getMethods();
			ZhuPingJing first = (ZhuPingJing) c.getAnnotation(ZhuPingJing.class);
			System.out.println("First Annotation:" + first.name() + "\n");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Class c;
		try {
			c = Class.forName("com.zpj.annotation.AnnotationTest");
			Method[] method = c.getMethods();
			for (int i = 0; i < method.length; i++) {
				ZhuPingJing first = (ZhuPingJing) c.getAnnotation(ZhuPingJing.class);
				if (first != null) {
					System.out.println("First Annotation:" + first.name() + "\n");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
