package com.zpj.designMode.flyweightPattern;

public class Car {
	// 内部状态
	private String type;
	// 外部状态，可以进行改变
	private int speed;

	public Car(String type) {
		this.type = type;
	}

	public void go() {
		System.out.println(this.type + "---go--" + speed + "------" + this);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
