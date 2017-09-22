package com.zpj.concurrent.lockandcondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * @author Perkins Zhu
 * @date 2017年5月8日 上午11:35:50
 */
public class TestSignal {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Lock lock = new ReentrantLock();
				Condition save = lock.newCondition();
				try {
					lock.lock();
					save.await();
					save.notify();
					save.signal();
					lock.unlock();
					System.out.println("==========");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
