package com.zpj.designMode.abstractFactory;

public class FactoryA implements Factory{
	public Computer createCompulter(){
		return new ComputerA();
	};
	public MobilePhone createMobilePhone(){
		return new MobilePhoneA();
	};
}
