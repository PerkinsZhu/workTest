package com.zpj.designMode.proxy.cglib;

import java.lang.reflect.Method;

import com.zpj.designMode.proxy.MrLi;
import com.zpj.designMode.proxy.Person;

/***
 @author  Perkins Zhu
 @date  2017年3月13日 上午9:07:38
 */
public class Run {

	public static void main(String[] args) {
		Person person = (Person)new CglibProxy().getInstance(new MrLi());
		Class c = person.getClass();
		System.out.println(c.getName()+"==="+c.getClassLoader().getClass().getName());
		Method[] methods = c.getDeclaredMethods();
		for(int i =0 ;i<methods.length;i++){
			Method item =methods[i];
			System.out.println(item.getName());
		}
		person.doWork();
	}
}
