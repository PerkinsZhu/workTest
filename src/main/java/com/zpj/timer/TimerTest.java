package com.zpj.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
				System.out.println("----------------" + date);
			}
		}, 1000, 1000);
		
		timer.notifyAll();
		
	}
}
