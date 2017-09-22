package com.zpj.concurrent;

/***
 @author  Perkins Zhu
 @date  2017年5月4日 上午11:31:26
 */
public class MyRunner implements Runnable {

	@Override
	public void run() {
		sleep();
	}

	private void sleep() {
		try {
			Thread.currentThread().sleep(100000);
			// Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("===========01");
			// Thread.currentThread().interrupt();
			System.out.println("===========02");
		}
	}
}
