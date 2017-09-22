package com.zpj.designMode.observerPattern;

public class Student01 implements Observer {

	@Override
	public void teacherIsComming() {
		System.out.println("--------student01  reading");
	}

}
