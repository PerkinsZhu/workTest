package com.zpj.designMode.observerPattern;

public class Monitor02 extends java.util.Observable {

	public void teacherIsComming() {
		// 设置changed = true,因为当changed== ture的时候才会调用观察者的update（）方法。
		setChanged();
		// 通知观察者被观察者发生改变
		notifyObservers("teacher is comming ！！！！");
		;
	}

}
