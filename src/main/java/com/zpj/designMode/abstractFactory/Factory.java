package com.zpj.designMode.abstractFactory;

public interface Factory {
	//工厂中的都可以生产这两种产品
	public Computer createCompulter();
	public MobilePhone createMobilePhone();
}
