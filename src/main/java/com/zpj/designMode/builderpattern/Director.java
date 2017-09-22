package com.zpj.designMode.builderpattern;

public class Director {
	private ComputerCreator creator;

	public Director(ComputerCreator creator) {
		super();
		this.creator = creator;
	}

	//只有Director才知道该如何建造电脑，执行创建顺序是什么，都需要那些步骤。
	//creator是不知道的，员工只会制作一个个组件，但是不知道创建一个电脑需要哪些组件以及创建顺序
	//所有电脑的制造流程是一样的，不一样的是电脑的各个组件型号不同
	public void createComputer() {
		creator.createCPU();
		creator.createDisplay();
		creator.createHardDisk();
		creator.createMainboard();
	}

}
