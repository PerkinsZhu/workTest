package com.zpj.designMode.chainOfResponsibility;

import org.junit.Test;

public class testUnit {

	@Test
	public void test01() {
		Manager chairman = new Chairman(null);
		Manager ceo = new ChiefExecutiveOfficer(chairman);
		Manager groupLeader = new GroupLeader(ceo);
		//向组长发起请假申请
		groupLeader.dealRequest(26);

	}

}
