package com.zpj.enumTest;

public enum Color {

	RED(0, "红色"), GREEN(1, "绿色"), BLACK(2, "黑色"),

	BLUE(3,"蓝色");// 注意最后面的分号
	
	private int index;
	private String name;

	Color(int index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public String toString(){
		return this.name+" : "+this.index;
	}
}
