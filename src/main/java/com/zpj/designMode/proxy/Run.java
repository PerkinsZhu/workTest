package com.zpj.designMode.proxy;

public class Run {
	public static void main(String[] args) {
		MrLi li = new MrLi();
		Proxy proxy = new Proxy(li);
		//调用处直接调用代理进行目标方法的操作。
		proxy.doWork();
	}
}
