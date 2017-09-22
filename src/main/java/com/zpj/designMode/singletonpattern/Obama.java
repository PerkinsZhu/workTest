package com.zpj.designMode.singletonpattern;

//7中实现方法:http://cantellow.iteye.com/blog/838473
public class Obama {
	//构造一个私有的静态Obama对象
	private static Obama obama = new Obama();
	//私有化构造器，阻止外部进行实例化
	private Obama(){}
	//public static 公外部调用获取实例
	public static Obama getInstance(){
		return obama;
	}
}
