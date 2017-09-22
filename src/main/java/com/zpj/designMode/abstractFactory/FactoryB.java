package com.zpj.designMode.abstractFactory;

public class FactoryB implements Factory{
	public Computer createCompulter(){
		return new ComputerB();
	};
	public MobilePhone createMobilePhone(){
		return new MobilePhoneB();
	};
}
