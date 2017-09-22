package com.zpj.designMode.builderpattern;

public class IBMCreator implements ComputerCreator {
//按照IBM的组件型号创建各个组件
	@Override
	public void createCPU() {
		System.out.println("----- createCPU------");
	}

	@Override
	public void createMainboard() {

	}

	@Override
	public void createHardDisk() {

	}

	@Override
	public void createDisplay() {

	}

}
