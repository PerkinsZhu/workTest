package com.zpj.designMode.chainOfResponsibility;

public abstract class Manager {
	protected Manager leader;

	// 处理方法
	public abstract void dealRequest(int request);

	public Manager(Manager leader) {
		super();
		this.leader = leader;
	}

}
