package com.zpj.concurrent.threadState;

/***
 @author  Perkins Zhu
 @date  2017年5月10日 下午3:03:33
 */
public class StateTest {
	public static void main(String[] args) {
		MyThread thread = new MyThread();
		System.out.println(thread.getState());
		thread.start();
		System.out.println(thread.getState());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(thread.getState());
	}
}

class MyThread extends Thread {
	public MyThread() {
		super();
	}

	@Override
	public void run() {
		System.out.println("i am run--------" + Thread.currentThread().getState());
	}
}
