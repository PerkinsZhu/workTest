package com.zpj.designMode.observerPattern;

import java.util.Observable;

public class Student04 implements java.util.Observer {

	// 实现update抽象方法，在被观察者发生变化之后又在该方法中进行操作
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("------------student04- -----------" + arg);
	}

}
