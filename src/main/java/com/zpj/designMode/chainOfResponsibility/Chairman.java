package com.zpj.designMode.chainOfResponsibility;

public class Chairman extends Manager {

	public Chairman(Manager leader) {
		super(leader);
	}

	@Override
	public void dealRequest(int request) {
		//有权对>20天的请假进行处理
		if (request <= 20) {
			System.out.println("Chairman--------允许----------");
		} else {
			System.out.println("Chairman--------拒绝----------");
		}

	}

}
