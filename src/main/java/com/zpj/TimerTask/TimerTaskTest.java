package com.zpj.TimerTask;

import java.util.Date;
import java.util.TimerTask;

public class TimerTaskTest extends TimerTask{

	@Override
	public void run() {
		System.out.println("-----------"+new Date().toString());
	}

}
