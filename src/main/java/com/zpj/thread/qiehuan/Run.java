package com.zpj.thread.qiehuan;

import java.util.Date;

/***
 * @author Perkins Zhu
 * @date 2017年4月10日 下午3:29:08
 */
public class Run {

	public static void main(String[] args) {
		Long startTime = new Date().getTime();
		for (int i = 0; i < 1; i++) {
			Thread thread = new Thread(new MyPrint(i+""));
			thread.start();
		}
		while (Thread.activeCount() != 1) {}
		System.out.println(new Date().getTime() - startTime);
	}
}
