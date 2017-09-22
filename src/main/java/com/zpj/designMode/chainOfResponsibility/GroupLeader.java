package com.zpj.designMode.chainOfResponsibility;

public class GroupLeader extends Manager {

	public GroupLeader(Manager leader) {
		super(leader);
	}

	@Override
	public void dealRequest(int request) {
		//有权对>5天的请假进行处理
		if (request <= 5) {
			System.out.println("GroupLeader--------允许----------");
		} else {
			leader.dealRequest(request);
		}
	}

}
