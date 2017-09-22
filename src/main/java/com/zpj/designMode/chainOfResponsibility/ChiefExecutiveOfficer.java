package com.zpj.designMode.chainOfResponsibility;

public class ChiefExecutiveOfficer extends Manager {

	public ChiefExecutiveOfficer(Manager leader) {
		super(leader);
	}

	@Override
	public void dealRequest(int request) {
		//有权对>10天的请假进行处理
		if (request <= 10) {
			System.out.println("ChiefExecutiveOfficer--------允许----------");
		} else {
			leader.dealRequest(request);
		}

	}

}
