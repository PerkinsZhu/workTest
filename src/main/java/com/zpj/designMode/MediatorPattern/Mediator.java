package com.zpj.designMode.MediatorPattern;

import java.util.ArrayList;
import java.util.List;

public class Mediator {
	List<Operator> opers = new ArrayList<Operator>();

	public void addOperators(Operator oper) {
		opers.add(oper);
	}

	private static Mediator mediator = new Mediator();

	private Mediator() {
	}

	public static Mediator getInstance() {
		return mediator;
	}

	public void sell(Operator oper, int price) {
		System.out.println("---------出售：" + price + "$");
		for (int i = 0; i < opers.size(); i++) {
			Operator op = opers.get(i);
			if (oper != op) {
				if (op.topPrice > price) {
					op.buy();
				}
			}
		}

	}

}
