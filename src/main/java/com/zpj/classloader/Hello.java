package com.zpj.classloader;

import java.io.Serializable;

/***
 * @author Perkins Zhu
 * @date 2017年4月1日 上午10:09:11
 */
public class Hello implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;

	public Hello() {
		super();
	}

	public Hello(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void hello() {
		System.out.println("==============");
	}

	public void run(String name) {
		System.out.println("==============" + name);
	}

	@Override
	public String toString() {
		return "Hello [name=" + name + ", age=" + age + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
