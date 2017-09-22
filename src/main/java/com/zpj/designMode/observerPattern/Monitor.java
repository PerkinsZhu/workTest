package com.zpj.designMode.observerPattern;

import java.util.ArrayList;
import java.util.List;

public class Monitor {
	private List<Observer> obers = new ArrayList<Observer>();

	public void notifyStudent() {
		for (Observer ob : obers) {
			ob.teacherIsComming();
		}
	}

	public void addObserver(Observer ob) {
		if (ob != null)
			obers.add(ob);
	}

	public void removeObserver(Observer ob) {
		if (ob != null)
			obers.remove(ob);
	}

}
