package com.zpj.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/***
 @author  Perkins Zhu
 @date  2017年4月1日 上午10:09:33
 */
public class Testhello {

	
	
	@Test
	public void testReflex(){
		try {
			Class c = Class.forName("com.zpj.classloader.Hello");
			Method[] methods = c.getDeclaredMethods();
			for(int i =0 ;i<methods.length;i++){
				Method item =methods[i];
				System.out.println(item.getName());
			}
			Hello hello = (Hello)c.newInstance();
			System.out.println(hello.getClass().getClassLoader().toString());
			hello.hello();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHello(){
		Hello hello = new Hello();
		System.out.println(hello.getClass().getMethods());
	}
	
	
}
