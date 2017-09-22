package com.zpj.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/***
 * @author Perkins Zhu
 * @date 2017年5月8日 上午10:59:56
 */
public class SemaPhoreTest {

	public static void main(String[] args) {
		Semaphore se = new Semaphore(5);
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			exec.execute(new MyRunner(i, se));
		}
		System.out.println("================");
		exec.shutdown();
		System.out.println("***********");
	}
}

class MyRunner implements Runnable {
	int num ;
	Semaphore se;
	public MyRunner(int num,Semaphore se) {
		super();
		this.num = num;
		this.se = se;
	}

	@Override
	public void run() {
		try {
		se.acquire();
		System.out.println(num+"：获取--访问权限");
		Thread.sleep(2000);
		System.out.println(num+"：释放");
		se.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
